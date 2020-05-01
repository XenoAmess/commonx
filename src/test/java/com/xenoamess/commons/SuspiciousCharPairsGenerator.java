/*
 * MIT License
 *
 * Copyright (c) 2020 XenoAmess
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
package com.xenoamess.commons;

import org.apache.commons.lang3.StringUtils;


public class SuspiciousCharPairsGenerator {
    public void gen() {
        for (char i = (char) (0); i < Character.MAX_VALUE; i++) {
            if (i % 1000 == 0) {
                System.out.println((int) i);
            }
            for (char j = (char) (0); j <= i; j++) {
                String si = "" + i;
                String sj = "" + j;
                boolean res1 = si.equalsIgnoreCase(sj);
                CharSequence ci = new StringBuilder(si);
                CharSequence cj = new StringBuilder(sj);
                boolean res2 = StringUtils.startsWithIgnoreCase(ci, cj);
                if (res1 != res2) {
                    System.out.println("i : " + ((int) i) + " j : " + ((int) j));
                }
            }
        }
    }
}
