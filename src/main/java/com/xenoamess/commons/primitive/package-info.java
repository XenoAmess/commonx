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

/**
 * Primitive collections that compatible with generic interfaces.
 * These classes are aimed to run far faster than normal classes from jdk,
 * while be able to work with them.
 * <p>
 * For example, DoubleArrayList is an AbstractList&lt;Double&gt; and List&lt;Double&gt;, but in core it is primitive
 * implement,
 * and provides primitive method choice, and is very fast.
 * <p>
 * The basic usage of such classes is:
 * 1. when you want to use a method that accept or return generic, use methodname + Primitive instead.
 * for example, add -&gt; addPrimitive, set -&gt; setPrimitive
 * 2. do not use foreach, use iterator and nextPrimitive instead.
 * <p>
 * Most classes in this folders are reformed from java.util, and thus follows
 * GPLv2 + Classpath Exception.
 *
 * @since 4.0
 */
package com.xenoamess.commons.primitive;
