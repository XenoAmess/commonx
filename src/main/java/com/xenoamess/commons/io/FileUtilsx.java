/*
 * MIT License
 *
 * Copyright (c) 2019 XenoAmess
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.xenoamess.commons.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

/**
 * <p>FileUtil class.</p>
 *
 * @author XenoAmess
 * @deprecated This class is of low quality and is badly designed. And it only works 100% right on Windows. I do not
 * recommend anybody use it.
 */
public class FileUtilsx {
    /**
     * Don't let anyone instantiate this class.
     */
    private FileUtilsx() {
    }

    /**
     * Resize buffer.
     * Do never use this to resize a buffer from MemUtil,
     * If you want to resize MemUtil's Buffer, please learn about it first.
     *
     * @param buffer      old buffer to resize
     * @param newCapacity new buffer's capacity
     * @return resized new buffer
     */
    public static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        final ByteBuffer newBuffer = ByteBuffer.allocateDirect(newCapacity).order(ByteOrder.nativeOrder());
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     *
     * @param resourceFile the resource file to read
     * @return the resource data
     */
    public static ByteBuffer loadBuffer(File resourceFile) {
        return loadBuffer(resourceFile, false);
    }

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     *
     * @param resourcePath the resource path to read
     * @return the resource data
     */
    public static ByteBuffer loadBuffer(Path resourcePath) {
        return loadBuffer(resourcePath, false);
    }

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     * if ifUsingMemoryUtil == false, then use BufferUtil.
     * else, allocate it using MemoryUtil.
     *
     * @param resourceFile      the resource file to read
     * @param ifUsingMemoryUtil if using MemoryUtil here
     * @return the resource data
     */
    public static ByteBuffer loadBuffer(File resourceFile, boolean ifUsingMemoryUtil) {
        if (resourceFile == null || !resourceFile.exists() || !resourceFile.isFile()) {
            //if is not a file.
            throw new IllegalArgumentException("FileUtils.loadBuffer(File resourceFile, boolean ifUsingMemoryUtil) " +
                    "fails:" + resourceFile +
                    "," + ifUsingMemoryUtil);
        }
        return loadBuffer(resourceFile.toPath(), ifUsingMemoryUtil);
    }

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     * if ifUsingMemoryUtil == false, then use BufferUtil.
     * else, allocate it using MemoryUtil.
     *
     * @param resourcePath      the resource path to read
     * @param ifUsingMemoryUtil if using MemoryUtil here
     * @return the resource data
     */
    public static ByteBuffer loadBuffer(Path resourcePath, boolean ifUsingMemoryUtil) {
        boolean success;
        ByteBuffer buffer = null;
        Path path = resourcePath;
        if (Files.isReadable(path)) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                if (ifUsingMemoryUtil) {
                    buffer = MemoryUtil.memAlloc((int) fc.size() + 1);
                } else {
                    buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);
                }

                while (fc.read(buffer) != -1) {
                }
                success = true;
            } catch (IOException e) {
                if (buffer != null) {
                    buffer.clear();
                }
                success = false;
            }
            if (success) {
                buffer.flip();
                return buffer.slice();
            }
        }


        try (
                InputStream source = Files.newInputStream(resourcePath);
                ReadableByteChannel rbc = Channels.newChannel(source)
        ) {

            if (ifUsingMemoryUtil) {
                buffer = MemoryUtil.memAlloc((int) Files.size(resourcePath) + 1);
            } else {
                buffer = BufferUtils.createByteBuffer((int) Files.size(resourcePath) + 1);
            }

            while (true) {
                int bytes = rbc.read(buffer);
                if (bytes == -1) {
                    break;
                }
            }
            success = true;
        } catch (IOException e) {
            success = false;
        }
        if (success) {
            buffer.flip();
            return buffer.slice();
        } else {
            throw new IllegalArgumentException(
                    "FileUtils.loadFileBuffer(File resourceFile, boolean ifUsingMemoryUtil) fails:" +
                            resourcePath + "," + ifUsingMemoryUtil
            );
        }
    }


    /**
     * <p>getFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File getFile(Class callerClassObject, String resourceFilePath) {
        URL url = getURL(callerClassObject, resourceFilePath);
        String path = url.getPath();
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("FileUtils.getFile(String resourceFilePath) fail:" + resourceFilePath + " , url = " + url, e);
        }
        if (path == null) {
            throw new IllegalArgumentException("FileUtils.getFile(String resourceFilePath) fail:" + resourceFilePath + " , url = " + url);
        }
        return new File(path);
    }

    /**
     * <p>getFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File getFile(String resourceFilePath) {
        return getFile(FileUtilsx.class, resourceFilePath);
    }

    /**
     * <p>getFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static Path getPath(Class callerClassObject, String resourceFilePath) {
        URI uri = getURI(callerClassObject, resourceFilePath);
//        String path = uri.getPath();
//        try {
//            path = URLDecoder.decode(path, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new IllegalArgumentException("FileUtils.getFile(String resourceFilePath) fail:" + resourceFilePath
//            + " , url = " + url, e);
//        }
//        if (path == null) {
//            throw new IllegalArgumentException("FileUtils.getFile(String resourceFilePath) fail:" + resourceFilePath
//            + " , url = " + url);
//        }
        return Paths.get(uri);
    }

    /**
     * <p>getFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static Path getPath(String resourceFilePath) {
        return getPath(FileUtilsx.class, resourceFilePath);
    }


    /**
     * <p>detect if file exist.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static boolean containsFile(Class callerClassObject, String resourceFilePath) {
        boolean result = true;
        File resultFile = null;
        try {
            resultFile = getFile(callerClassObject, resourceFilePath);
        } catch (Exception e) {
            result = false;
        }
        if (resultFile == null || !resultFile.exists() || !resultFile.isFile()) {
            result = false;
        }
        return result;
    }

    /**
     * <p>detect if file exist.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static boolean containsFile(String resourceFilePath) {
        return containsFile(FileUtilsx.class, resourceFilePath);
    }

    /**
     * <p>detect if folder exist.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static boolean containsFolder(Class callerClassObject, String resourceFilePath) {
        boolean result = true;
        File resultFile = null;
        try {
            resultFile = getFile(callerClassObject, resourceFilePath);
        } catch (Exception e) {
            result = false;
        }
        if (resultFile == null || !resultFile.exists() || !resultFile.isDirectory()) {
            result = false;
        }
        return result;
    }

    /**
     * <p>detect if folder exist.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static boolean containsFolder(String resourceFilePath) {
        return containsFolder(FileUtilsx.class, resourceFilePath);
    }

    /**
     * <p>getURL.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static URL getURL(Class callerClassObject, String resourceFilePath) {
        URL res = callerClassObject.getResource(resourceFilePath);
        if (res == null) {
            throw new IllegalArgumentException("FileUtils.getURL(String resourceFilePath) fail:" + resourceFilePath);
        }
        return res;
    }

    /**
     * <p>getURL.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static URL getURL(String resourceFilePath) {
        return getURL(FileUtilsx.class, resourceFilePath);
    }

    /**
     * <p>detect if URL exist.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static boolean containsURL(Class callerClassObject, String resourceFilePath) {
        boolean result = true;
        URL resultURL = null;
        try {
            resultURL = getURL(callerClassObject, resourceFilePath);
        } catch (Exception e) {
            result = false;
        }
        if (resultURL == null) {
            result = false;
        }
        return result;
    }

    /**
     * <p>detect if URL exist.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static boolean containsURL(String resourceFilePath) {
        return containsURL(FileUtilsx.class, resourceFilePath);
    }

    /**
     * <p>getURI.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static URI getURI(Class callerClassObject, String resourceFilePath) {
        URI res;
        try {
            res = getURL(callerClassObject, resourceFilePath).toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("FileUtils.getURL(String resourceFilePath) fail:" + resourceFilePath, e);
        }
        return res;
    }

    /**
     * <p>getURI.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static URI getURI(String resourceFilePath) {
        URI res;
        try {
            res = getURL(resourceFilePath).toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("FileUtils.getURL(String resourceFilePath) fail:" + resourceFilePath, e);
        }
        return res;
    }

    /**
     * <p>detect if file exist.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static boolean containsURI(Class callerClassObject, String resourceFilePath) {
        boolean result = true;
        URI resultRUI = null;
        try {
            resultRUI = getURI(callerClassObject, resourceFilePath);
        } catch (Exception e) {
            result = false;
        }
        if (resultRUI == null) {
            result = false;
        }
        return result;
    }

    /**
     * <p>detect if file exist.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static boolean containsURI(String resourceFilePath) {
        boolean result = true;
        URI resultRUI = null;
        try {
            resultRUI = getURI(resourceFilePath);
        } catch (Exception e) {
            result = false;
        }
        if (resultRUI == null) {
            result = false;
        }
        return result;
    }

    /**
     * create file if a file is not exist.
     * absolute path is strongly suggested here.
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File createFileIfAbsent(Class callerClassObject, String resourceFilePath) {
        File file;
        try {
            file = getFile(callerClassObject, resourceFilePath);
        } catch (IllegalArgumentException e) {
            int indexSlash = resourceFilePath.lastIndexOf('/');
            File parentFolder = createFileDirectoryIfAbsent(callerClassObject, resourceFilePath.substring(0,
                    indexSlash));
            file = new File(parentFolder.getAbsolutePath() + resourceFilePath.substring(indexSlash));
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new IllegalArgumentException("FileUtils.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath, ex);
            }
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("FileUtils.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath + " exist and is not a file.");
        }
        return file;
    }

    /**
     * create file if a file is not exist.
     * absolute path is strongly suggested here.
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File createFileIfAbsent(String resourceFilePath) {
        return createFileIfAbsent(FileUtilsx.class, resourceFilePath);
    }

    /**
     * create folder if a folder is not exist.
     * absolute path is strongly suggested here.
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File createFileDirectoryIfAbsent(Class callerClassObject, String resourceFilePath) {
        File folder = null;
        try {
            folder = getFile(callerClassObject, resourceFilePath);
        } catch (IllegalArgumentException e) {
            int indexSlash = resourceFilePath.lastIndexOf('/');
            String parentFolderPath = resourceFilePath.substring(0, indexSlash);
            if (parentFolderPath.isEmpty()) {
                folder = getFile(parentFolderPath);
            } else {
                File parentFolder = createFileDirectoryIfAbsent(callerClassObject, parentFolderPath);
                folder = new File(parentFolder.getAbsolutePath() + resourceFilePath.substring(indexSlash));
                folder.mkdirs();
            }
        }
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("FileUtils.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath + " exist and is not a folder.");
        }
        return folder;
    }

    /**
     * create folder if a folder is not exist.
     * absolute path is strongly suggested here.
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File createFileDirectoryIfAbsent(String resourceFilePath) {
        return createFileDirectoryIfAbsent(FileUtilsx.class, resourceFilePath);
    }

    /**
     * create file if a file is not exist.
     * absolute path is strongly suggested here.
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static Path createPathIfAbsent(Class callerClassObject, String resourceFilePath) {
        Path path;
        try {
            path = getPath(callerClassObject, resourceFilePath);
        } catch (IllegalArgumentException e) {
            int indexSlash = resourceFilePath.lastIndexOf('/');
            Path parentFolder = createPathDirectoryIfAbsent(callerClassObject, resourceFilePath.substring(0,
                    indexSlash));
            path = parentFolder.resolve(resourceFilePath.substring(indexSlash));
            try {
                Files.createFile(path);
            } catch (IOException ex) {
                throw new IllegalArgumentException("FileUtils.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath, ex);
            }
        }
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("FileUtils.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath + " exist and is not a file.");
        }
        return path;
    }

    /**
     * create file if a file is not exist.
     * absolute path is strongly suggested here.
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     * @deprecated this function works weirdly on Linux. I know how to fix it but fixing it will cause we change all
     * functions in this class, so finally I decided just deprecate this whole class instead.
     */
    @Deprecated
    public static Path createPathIfAbsent(String resourceFilePath) {
        return createPathIfAbsent(FileUtilsx.class, resourceFilePath);
    }

    /**
     * create folder if a folder is not exist.
     * absolute path is strongly suggested here.
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     * @deprecated this function works weirdly on Linux. I know how to fix it but fixing it will cause we change all
     * functions in this class, so finally I decided just deprecate this whole class instead.
     */
    @Deprecated
    public static Path createPathDirectoryIfAbsent(Class callerClassObject, String resourceFilePath) {
        Path folder = null;
        try {
            folder = getPath(callerClassObject, resourceFilePath);
        } catch (IllegalArgumentException e) {
            int indexSlash = resourceFilePath.lastIndexOf('/');
            String parentFolderPath = resourceFilePath.substring(0, indexSlash);
            if (parentFolderPath.isEmpty()) {
                folder = getPath(parentFolderPath);
            } else {
                Path parentFolder = createPathDirectoryIfAbsent(callerClassObject, parentFolderPath);
                folder = parentFolder.resolve(resourceFilePath.substring(indexSlash));
                try {
                    Files.createDirectory(folder);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (!Files.isDirectory(folder)) {
            throw new IllegalArgumentException("FileUtils.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath + " exist and is not a folder.");
        }
        return folder;
    }

    /**
     * create folder if a folder is not exist.
     * absolute path is strongly suggested here.
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     * @deprecated this function works weirdly on Linux. I know how to fix it but fixing it will cause we change all
     * functions in this class, so finally I decided just deprecate this whole class instead.
     */
    @Deprecated
    public static Path createPathDirectoryIfAbsent(String resourceFilePath) {
        return createPathDirectoryIfAbsent(FileUtilsx.class, resourceFilePath);
    }

    /**
     * <p>loadFile.</p>
     *
     * @param inputStream inputStream
     * @return return
     */
    public static String loadString(InputStream inputStream) {
        assert (inputStream != null);
        String res;
        try (
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream))
        ) {
            byte[] bytes = new byte[0];
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            res = new String(bytes).intern();
        } catch (Exception e) {
            throw new IllegalArgumentException("FileUtils.loadString(InputStream inputStream) fails:" + inputStream, e);
        }
        return res;
    }

    /**
     * <p>loadString.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static String loadString(Class callerClassObject, String resourceFilePath) {
        String res;
        try (InputStream inputStream = getURL(callerClassObject, resourceFilePath).openStream()) {
            res = loadString(inputStream);
        } catch (Exception e) {
            throw new IllegalArgumentException("FileUtils.loadString(String resourceFilePath) fails:" + resourceFilePath
                    , e);
        }
        return res;
    }

    /**
     * <p>loadString.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static String loadString(String resourceFilePath) {
        return loadString(FileUtilsx.class, resourceFilePath);
    }

    /**
     * <p>loadString.</p>
     *
     * @param file file
     * @return return
     */
    public static String loadString(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("FileUtils.loadString(File file) fails:" + file);
        }
        URI uri = file.toURI();
        Path path = Paths.get(uri);
        String res = loadString(path);
        return res;
    }

    /**
     * <p>loadString.</p>
     *
     * @param path path
     * @return return
     */
    public static String loadString(Path path) {
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("FileUtils.loadString(Path path) fails:" + path);
        }
        String res;
        try {
            byte[] bytes = Files.readAllBytes(path);
            res = new String(bytes).intern();
        } catch (IOException e) {
            throw new IllegalArgumentException("FileUtils.loadString(File file) fails:" + path, e);
        }
        return res;
    }

    /**
     * <p>saveFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @param contentString    a {@link String} object.
     */
    public static void saveFile(Class callerClassObject, String resourceFilePath, String contentString) {
        File file = createFileIfAbsent(callerClassObject, resourceFilePath);
        saveFile(file, contentString);
    }

    /**
     * <p>saveFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @param contentString    a {@link String} object.
     */
    public static void saveFile(String resourceFilePath, String contentString) {
        saveFile(FileUtilsx.class, resourceFilePath, contentString);
    }

    /**
     * <p>saveFile.</p>
     *
     * @param file          a {@link File} object.
     * @param contentString contentString
     */
    public static void saveFile(File file, String contentString) {
        if (file == null) {
            //if is not a file.
            throw new IllegalArgumentException("FileUtils.saveFile(File file, String contentString) fails:" + file +
                    "," + contentString);
        }
        try (
                FileWriter fileWriter = new FileWriter(file)
        ) {
            fileWriter.write(contentString);
        } catch (IOException e) {
            throw new IllegalArgumentException("FileUtils.saveFile(File file, String contentString) fails:" + file +
                    "," + contentString, e);
        }
    }

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     * if ifUsingMemoryUtil == false, then use BufferUtil.
     * else, allocate it using MemoryUtil.
     *
     * @param resourceFileObject the resource file object to read
     * @param ifUsingMemoryUtil  if using MemoryUtil here
     * @return the resource data
     */
    @Deprecated
    public static ByteBuffer loadBuffer(FileObject resourceFileObject, boolean ifUsingMemoryUtil) {
        return FileObjectUtilsx.loadBuffer(resourceFileObject, ifUsingMemoryUtil);
    }

    @Deprecated
    public static File toFile(FileObject fileObject) throws FileSystemException {
        return FileObjectUtilsx.toFile(fileObject);
    }
}
