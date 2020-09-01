package com.xenoamess.commons.io;

import java.io.File;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.junit.jupiter.api.Test;
import static com.xenoamess.commons.io.FileUtilsx.toFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSpace {
    private static StandardFileSystemManager loadFileSystemManager() throws FileSystemException {
        StandardFileSystemManager fileSystemManager = new org.apache.commons.vfs2.impl.StandardFileSystemManager();
        fileSystemManager.setLogger(null);
        fileSystemManager.init();
        fileSystemManager.setBaseFile(new File(System.getProperty("user.dir")));
        return fileSystemManager;
    }

    @Test
    public void test() throws FileSystemException {
        StandardFileSystemManager standardFileSystemManager = loadFileSystemManager();
        File file1 = toFile(standardFileSystemManager.resolveFile("resources/test/1 1"));
        assertNotNull(file1);
        assertEquals("aaa", FileUtilsx.loadString(file1));
        System.out.println(file1.getAbsolutePath());
        File file2 = toFile(standardFileSystemManager.resolveFile("resources/test/好"));
        assertNotNull(file2);
        assertEquals("aaa", FileUtilsx.loadString(file2));
        System.out.println(file2.getAbsolutePath());
    }
}