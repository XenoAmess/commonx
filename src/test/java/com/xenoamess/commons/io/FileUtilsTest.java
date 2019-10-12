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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.xenoamess.commons.io.FileUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XenoAmess
 */
public class FileUtilsTest {
    public static final String testFilePath = "/com/xenoamess/commons/io/中文路径测试/中文路径测试/中文读取测试";
    public static final String testFileContent = "中文读取测试";

    @Test
    public void test() {
        System.out.println(FileUtils.createFileDirectoryIfAbsent("/com/xenoamess/commons/io/中文测试0/中文测试0/中文测试0/中文测试0")
                .getAbsolutePath());
        System.out.println(FileUtils.createFileDirectoryIfAbsent("/com/xenoamess/commons/io/中文测试0/中文测试0/中文测试0/中文测试0")
                .getAbsolutePath());
        System.out.println(FileUtils.createFileDirectoryIfAbsent("/com/xenoamess/commons/io/b/b/b/b/").getAbsolutePath());
//        System.out.println(FileUtils.createFolderIfAbsent("/").getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("/com/xenoamess/commons/io/c/c/c/c").getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("/com/xenoamess/commons/io/中文测试0/中文测试0/中文测试0/中文测试0/d")
                .getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("/com/xenoamess/commons/io/中文测试0/中文测试0/中文测试0/中文测试0/d")
                .getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("/com/xenoamess/commons/io/d").getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("d").getAbsolutePath());
        try {
            System.out.println(FileUtils.createFileDirectoryIfAbsent("/com/xenoamess/commons/io/d/").getAbsolutePath());
            assertTrue(false);
        } catch (IllegalArgumentException e) {
        }
        try {
            System.out.println(FileUtils.createFileIfAbsent("/com/xenoamess/commons/io/中文测试0/").getAbsolutePath());
            assertTrue(false);
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void loadBufferTest() {
        ByteBuffer byteBuffer = loadBuffer(getFile(this.getClass(), testFilePath));
        byte[] content = new byte[byteBuffer.capacity()];
        byteBuffer.get(content, 0, byteBuffer.limit());
        String a = new String(content);
        assertEquals(a, testFileContent);
    }

    @Test
    public void loadBufferTestPath() {
        ByteBuffer byteBuffer = loadBuffer(getPath(this.getClass(), testFilePath));
        byte[] content = new byte[byteBuffer.capacity()];
        byteBuffer.get(content, 0, byteBuffer.limit());
        String a = new String(content);
        assertEquals(a, testFileContent);
    }

    @Test
    public void getFileTest() throws IOException {
        assertEquals(
                new String(Files.readAllBytes(Paths.get(getFile(testFilePath).toURI()))).intern(),
                testFileContent
        );
    }

    @Test
    public void getPathTest() throws IOException {
        assertEquals(
                new String(Files.readAllBytes(getPath(testFilePath))).intern(),
                testFileContent
        );
    }

    @Test
    public void containsFileTest() {
        assertTrue(containsFile(testFilePath));
    }

    @Test
    public void getURLTest() {
        getURL(testFilePath).getPath();
    }

    @Test
    public void containsURLTest() {
        assertTrue(containsURL(testFilePath));
    }

    @Test
    public void getURITest() {
        getURI(testFilePath).getPath();
    }

    @Test
    public void containsURITest() {
        assertTrue(containsURI(testFilePath));
    }

    @Test
    public void createFileIfAbsentTest() {
        final String createFileIfAbsentTestFilePath = "/com/xenoamess/commons/io" +
                "/中文路径测试/中文路径测试/中文路径测试/createFileIfAbsentTestFile";
        if (containsFile(createFileIfAbsentTestFilePath)) {
            getFile(createFileIfAbsentTestFilePath).delete();
        }
        File result = createFileIfAbsent(createFileIfAbsentTestFilePath);
        assertNotNull(result);
        result.delete();
        try {
            Files.delete(Paths.get("/com/xenoamess/commons/io" +
                    "/中文路径测试"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled("The function works weirdly on linux but fixing it is meaningless. After all that class is deprecated...")
    public void createPathIfAbsentTest() {
        final String createFileIfAbsentTestFilePath = "/com/xenoamess/commons/io" +
                "/中文路径测试/中文路径测试/中文路径测试/createPathIfAbsentTestFile";
        if (containsFile(createFileIfAbsentTestFilePath)) {
            getFile(createFileIfAbsentTestFilePath).delete();
        }
        Path result = createPathIfAbsent(createFileIfAbsentTestFilePath);
        assertNotNull(result);
        try {
            Files.delete(result);
            Files.delete(Paths.get("/com/xenoamess/commons/io" +
                    "/中文路径测试"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadStringTest() {
        assertEquals(loadString(testFilePath), testFileContent);
    }


    @Test
    public void saveFileTest() {
        final String createFileIfAbsentTestFilePath = "/com/xenoamess/commons/io/中文路径测试/中文路径测试/中文路径测试/saveFileTest";
        final String content = "一些鬼知道什么意思的中文";
        File result = createFileIfAbsent(createFileIfAbsentTestFilePath);
        saveFile(createFileIfAbsentTestFilePath, content);
        String loadedContent = loadString(createFileIfAbsentTestFilePath);
        assertEquals(content, loadedContent);
        result.delete();
    }


}
