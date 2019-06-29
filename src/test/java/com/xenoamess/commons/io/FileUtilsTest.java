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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author XenoAmess
 */
public class FileUtilsTest {
    @Test
    public void test() {
        System.out.println(FileUtils.createFolderIfAbsent("/a/a/a/a").getAbsolutePath());
        System.out.println(FileUtils.createFolderIfAbsent("/a/a/a/a").getAbsolutePath());
        System.out.println(FileUtils.createFolderIfAbsent("/b/b/b/b/").getAbsolutePath());
        System.out.println(FileUtils.getFile("/"));
//        System.out.println(FileUtils.createFolderIfAbsent("/").getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("/c/c/c/c").getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("/a/a/a/a/d").getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("/a/a/a/a/d").getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("/d").getAbsolutePath());
        System.out.println(FileUtils.createFileIfAbsent("d").getAbsolutePath());
        try {
            System.out.println(FileUtils.createFileIfAbsent("/d/").getAbsolutePath());
            assertTrue(false);
        } catch (IllegalArgumentException e) {
        }
        try {
            System.out.println(FileUtils.createFileIfAbsent("/a/").getAbsolutePath());
            assertTrue(false);
        } catch (IllegalArgumentException e) {
        }
    }
}
