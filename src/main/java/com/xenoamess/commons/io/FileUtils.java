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

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>FileUtil class.</p>
 *
 * @author XenoAmess
 * @version 0.143.0
 */
public class FileUtils {
    /**
     * Don't let anyone instantiate this class.
     */
    private FileUtils() {
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
    public static ByteBuffer loadFileBuffer(File resourceFile) {
        return loadFileBuffer(resourceFile, false);
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
    public static ByteBuffer loadFileBuffer(File resourceFile, boolean ifUsingMemoryUtil) {
        boolean success;

        ByteBuffer buffer = null;
        final String absolutePath = resourceFile.getAbsolutePath();
        Path path = Paths.get(absolutePath);
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
                InputStream source = new FileInputStream(resourceFile);
                ReadableByteChannel rbc = Channels.newChannel(source)
        ) {

            if (ifUsingMemoryUtil) {
                buffer = MemoryUtil.memAlloc((int) resourceFile.length() + 1);
            } else {
                buffer = BufferUtils.createByteBuffer((int) resourceFile.length() + 1);
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
            throw new IllegalArgumentException("FileUtil.loadFileBuffer(File resourceFile, boolean ifUsingMemoryUtil)" +
                    " fails:" + resourceFile + "," + ifUsingMemoryUtil);
        }
    }


    /**
     * <p>getFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File getFile(Class callerClassObject, String resourceFilePath) {
        final URL resUrl = getURL(callerClassObject, resourceFilePath);
        return new File(resUrl.getFile().replaceAll("%20", " "));
    }

    /**
     * <p>getFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File getFile(String resourceFilePath) {
        final URL resUrl = getURL(FileUtils.class, resourceFilePath);
        return new File(resUrl.getFile().replaceAll("%20", " "));
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
            throw new IllegalArgumentException("FileUtil.getURL(String resourceFilePath) fail:" + resourceFilePath);
        }
        return res;
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
            throw new IllegalArgumentException("FileUtil.getURL(String resourceFilePath) fail:" + resourceFilePath, e);
        }
        return res;
    }

    /**
     * <p>createFileIfAbsent.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File createFileIfAbsent(String resourceFilePath) {
        File file;
        try {
            file = getFile(resourceFilePath);
        } catch (IllegalArgumentException e) {
            int indexSlash = resourceFilePath.lastIndexOf('/');
            File parentFolder = createFolderIfAbsent(resourceFilePath.substring(0, indexSlash));
            file = new File(parentFolder.getAbsolutePath() + resourceFilePath.substring(indexSlash));
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new IllegalArgumentException("FileUtil.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath, ex);
            }
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("FileUtil.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath + " exist and is not a file.");
        }
        return file;
    }

    /**
     * <p>createFolderIfAbsent.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static File createFolderIfAbsent(String resourceFilePath) {
        File folder;
        try {
            folder = getFile(resourceFilePath);
        } catch (IllegalArgumentException e) {
            int indexSlash = resourceFilePath.lastIndexOf('/');
            File parentFolder = createFolderIfAbsent(resourceFilePath.substring(0, indexSlash));
            folder = new File(parentFolder.getAbsolutePath() + resourceFilePath.substring(indexSlash));
            folder.mkdirs();
        }
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("FileUtil.createFileIfAbsent(String resourceFilePath) fail:" + resourceFilePath + " exist and is not a folder.");
        }
        return folder;
    }

    /**
     * <p>loadFile.</p>
     *
     * @param inputStream inputStream
     * @return return
     */
    public static String loadFile(InputStream inputStream) {
        assert (inputStream != null);
        String res;
        try (
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream))
        ) {
            final StringBuilder sb = new StringBuilder();
            String tmp;
            while (true) {
                tmp = bufferedReader.readLine();
                if (tmp == null) {
                    break;
                }
                sb.append(tmp);
                sb.append("\n");
            }
            res = sb.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("FileUtil.loadFile(InputStream inputStream) fails:" + inputStream, e);
        }
        return res;
    }

    /**
     * <p>loadFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @return return
     */
    public static String loadFile(Class callerClassObject, String resourceFilePath) {
        String res;
        try (InputStream inputStream = getURL(callerClassObject, resourceFilePath).openStream()) {
            res = loadFile(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("FileUtil.loadFile(String resourceFilePath) fails:" + resourceFilePath
                    , e);
        }
        return res;
    }

    /**
     * <p>loadFile.</p>
     *
     * @param file file
     * @return return
     */
    public static String loadFile(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("FileUtil.loadFile(File file) fails:" + file);
        }
        String res;
        try (FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile())) {
            res = loadFile(fileInputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("FileUtil.loadFile(File file) fails:" + file, e);
        }
        return res;
    }

    /**
     * <p>saveFile.</p>
     *
     * @param resourceFilePath resourceFilePath
     * @param contentString    a {@link String} object.
     */
    public static void saveFile(String resourceFilePath, String contentString) {
        saveFile(getFile(resourceFilePath), contentString);
    }

    /**
     * <p>saveFile.</p>
     *
     * @param file          a {@link File} object.
     * @param contentString contentString
     */
    public static void saveFile(File file, String contentString) {
        //if is not a file.
        if (file == null || !file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("FileUtil.saveFile(File file, String contentString) fails:" + file +
                    "," + contentString);
        }
        try (
                FileWriter fileWriter = new FileWriter(file)
        ) {
            fileWriter.write(contentString);
        } catch (IOException e) {
            throw new IllegalArgumentException("FileUtil.saveFile(File file, String contentString) fails:" + file +
                    "," + contentString, e);
        }
    }

}