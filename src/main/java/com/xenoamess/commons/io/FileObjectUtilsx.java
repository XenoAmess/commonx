package com.xenoamess.commons.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.lwjgl.system.MemoryUtil;

public class FileObjectUtilsx {

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     * if ifUsingMemoryUtil == false, then use BufferUtil.
     * else, allocate it using MemoryUtil.
     *
     * @param resourceFileObject the resource file object to read
     * @param ifUsingMemoryUtil  if using MemoryUtil here
     * @return the resource data
     */
    public static ByteBuffer loadBuffer(FileObject resourceFileObject, boolean ifUsingMemoryUtil) {
        if (resourceFileObject == null) {
            //if is not a file.
            throw new IllegalArgumentException("FileUtils.loadBuffer(File resourceFile, boolean ifUsingMemoryUtil) " +
                    "fails:" + resourceFileObject +
                    "," + ifUsingMemoryUtil);
        }

        try {
            File file = toFile(resourceFileObject);
            if (file != null) {
                return FileUtilsx.loadBuffer(file, ifUsingMemoryUtil);
            }
        } catch (Exception ignored) {

        }
        byte[] bytes = null;
        try {
            bytes = resourceFileObject.getContent().getByteArray();
        } catch (FileSystemException fileSystemException) {
            try {
                InputStream inputStream = resourceFileObject.getContent().getInputStream();
                bytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Cannot load from " + resourceFileObject, e);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Cannot load from " + resourceFileObject, ex);
        }
        if (bytes == null) {
            throw new IllegalArgumentException("Cannot load from " + resourceFileObject);
        }
        ByteBuffer buffer;
        if (ifUsingMemoryUtil) {
            buffer = MemoryUtil.memAlloc(bytes.length).put(bytes);
        } else {
            buffer = ByteBuffer.allocateDirect(bytes.length).put(bytes);
        }
        buffer.flip();
        return buffer.slice();
    }

    public static File toFile(FileObject fileObject) throws FileSystemException {
        if (fileObject == null || !"file".equals(fileObject.getURL().getProtocol())) {
            return null;
        }
        return new File(fileObject.getName().getPathDecoded());
    }
}
