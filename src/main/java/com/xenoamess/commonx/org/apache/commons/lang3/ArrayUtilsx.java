/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xenoamess.commonx.org.apache.commons.lang3;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

/**
 * @author XenoAmess
 */
public class ArrayUtilsx {
    /**
     * <p>Instances of this class should NOT be constructed in standard programming.
     *
     * <p>This constructor is public to permit tools that require a JavaBean instance
     * to operate.
     */
    public ArrayUtilsx() {
        super();
    }

    // Reverse
    //-----------------------------------------------------------------------

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(Object[] array)
     */
    public static <T> T[] reverse(final T[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(long[] array)
     */
    public static long[] reverse(final long[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(int[] array)
     */
    public static int[] reverse(final int[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(short[] array)
     */
    public static short[] reverse(final short[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(char[] array)
     */
    public static char[] reverse(final char[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(byte[] array)
     */
    public static byte[] reverse(final byte[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(double[] array)
     */
    public static double[] reverse(final double[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(float[] array)
     */
    public static float[] reverse(final float[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     * @return the original array
     * @see ArrayUtils#reverse(boolean[] array)
     */
    public static boolean[] reverse(final boolean[] array) {
        ArrayUtils.reverse(array);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(boolean[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static boolean[] reverse(final boolean[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(byte[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static byte[] reverse(final byte[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(char[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static char[] reverse(final char[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(double[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static double[] reverse(final double[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(float[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static float[] reverse(final float[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(int[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static int[] reverse(final int[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(long[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static long[] reverse(final long[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(short[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static short[] reverse(final short[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Under value (&lt;0) is promoted to 0, over value (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Under value (&lt; start index)
     *                            results in no
     *                            change. Over value (&gt;array.length) is demoted to array length.
     * @return the original array
     * @see ArrayUtils#reverse(Object[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static <T> T[] reverse(final T[] array, final int startIndexInclusive, final int endIndexExclusive) {
        ArrayUtils.reverse(array, startIndexInclusive, endIndexExclusive);
        return array;
    }

    // Swap
    //-----------------------------------------------------------------------

    /**
     * Swaps two elements in the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap(["1", "2", "3"], 0, 2) -&gt; ["3", "2", "1"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3"], 0, 0) -&gt; ["1", "2", "3"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3"], 1, 0) -&gt; ["2", "1", "3"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3"], 0, 5) -&gt; ["1", "2", "3"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3"], -1, 1) -&gt; ["2", "1", "3"]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(Object[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static <T> T[] swap(final T[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps two elements in the given long array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([true, false, true], 0, 2) -&gt; [true, false, true]</li>
     * <li>ArrayUtils.swap([true, false, true], 0, 0) -&gt; [true, false, true]</li>
     * <li>ArrayUtils.swap([true, false, true], 1, 0) -&gt; [false, true, true]</li>
     * <li>ArrayUtils.swap([true, false, true], 0, 5) -&gt; [true, false, true]</li>
     * <li>ArrayUtils.swap([true, false, true], -1, 1) -&gt; [false, true, true]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(long[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static long[] swap(final long[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps two elements in the given int array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(int[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static int[] swap(final int[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps two elements in the given short array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(short[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static short[] swap(final short[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps two elements in the given char array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(char[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static char[] swap(final char[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps two elements in the given byte array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(byte[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static byte[] swap(final byte[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps two elements in the given double array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(double[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static double[] swap(final double[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps two elements in the given float array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(float[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static float[] swap(final float[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps two elements in the given boolean array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     * <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     * @return the original array
     * @see ArrayUtils#swap(boolean[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static boolean[] swap(final boolean[] array, final int offset1, final int offset2) {
        ArrayUtils.swap(array, offset1, offset2);
        return array;
    }

    /**
     * Swaps a series of elements in the given boolean array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([true, false, true, false], 0, 2, 1) -&gt; [true, false, true, false]</li>
     * <li>ArrayUtils.swap([true, false, true, false], 0, 0, 1) -&gt; [true, false, true, false]</li>
     * <li>ArrayUtils.swap([true, false, true, false], 0, 2, 2) -&gt; [true, false, true, false]</li>
     * <li>ArrayUtils.swap([true, false, true, false], -3, 2, 2) -&gt; [true, false, true, false]</li>
     * <li>ArrayUtils.swap([true, false, true, false], 0, 3, 3) -&gt; [false, false, true, true]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(boolean[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static boolean[] swap(final boolean[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }

    /**
     * Swaps a series of elements in the given byte array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(byte[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static byte[] swap(final byte[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }

    /**
     * Swaps a series of elements in the given char array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(char[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static char[] swap(final char[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }

    /**
     * Swaps a series of elements in the given double array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(double[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static double[] swap(final double[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }

    /**
     * Swaps a series of elements in the given float array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(float[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static float[] swap(final float[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }

    /**
     * Swaps a series of elements in the given int array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(int[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static int[] swap(final int[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }

    /**
     * Swaps a series of elements in the given long array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(long[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static long[] swap(final long[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }

    /**
     * Swaps a series of elements in the given short array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     * <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(short[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static short[] swap(final short[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }

    /**
     * Swaps a series of elements in the given array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     * <li>ArrayUtils.swap(["1", "2", "3", "4"], 0, 2, 1) -&gt; ["3", "2", "1", "4"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3", "4"], 0, 0, 1) -&gt; ["1", "2", "3", "4"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3", "4"], 2, 0, 2) -&gt; ["3", "4", "1", "2"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3", "4"], -3, 2, 2) -&gt; ["3", "4", "1", "2"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3", "4"], 0, 3, 3) -&gt; ["4", "2", "3", "1"]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     * @return the original array
     * @see ArrayUtils#swap(Object[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static <T> T[] swap(final T[] array, int offset1, int offset2, int len) {
        ArrayUtils.swap(array, offset1, offset2, len);
        return array;
    }


    // Shift
    //-----------------------------------------------------------------------

    /**
     * Shifts the order of the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(Object[] array, int offset)
     * @since 3.5
     */
    public static <T> T[] shift(final T[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of the given long array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(long[] array, int offset)
     * @since 3.5
     */
    public static long[] shift(final long[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of the given int array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(int[] array, int offset)
     * @since 3.5
     */
    public static int[] shift(final int[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of the given short array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(short[] array, int offset)
     * @since 3.5
     */
    public static short[] shift(final short[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of the given char array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(char[] array, int offset)
     * @since 3.5
     */
    public static char[] shift(final char[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of the given byte array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(byte[] array, int offset)
     * @since 3.5
     */
    public static byte[] shift(final byte[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of the given double array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(double[] array, int offset)
     * @since 3.5
     */
    public static double[] shift(final double[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of the given float array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(float[] array, int offset)
     * @since 3.5
     */
    public static float[] shift(final float[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of the given boolean array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of
     *               elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(boolean[] array, int offset)
     * @since 3.5
     */
    public static boolean[] shift(final boolean[] array, final int offset) {
        ArrayUtils.shift(array, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given boolean array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(boolean[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static boolean[] shift(final boolean[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given byte array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(byte[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static byte[] shift(final byte[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given char array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(char[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static char[] shift(final char[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given double array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(double[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static double[] shift(final double[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given float array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(float[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static float[] shift(final float[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given int array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(int[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static int[] shift(final int[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given long array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(long[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static long[] shift(final long[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(Object[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static <T> T[] shift(final T[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    /**
     * Shifts the order of a series of elements in the given short array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array
     *                            .length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index)
     *                            results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the
     *                            number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     * @return the original array
     * @see ArrayUtils#shift(short[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static short[] shift(final short[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    // Shuffle
    // ----------------------------------------------------------------------

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(Object[] array)
     * @since 3.6
     */
    public static <T> T[] shuffle(final T[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(Object[] array, Random random)
     * @since 3.6
     */
    public static <T> T[] shuffle(final T[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(boolean[] array)
     * @since 3.6
     */
    public static boolean[] shuffle(final boolean[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(boolean[] array, Random random)
     * @since 3.6
     */
    public static boolean[] shuffle(final boolean[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(byte[] array)
     * @since 3.6
     */
    public static byte[] shuffle(final byte[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(byte[] array, Random random)
     * @since 3.6
     */
    public static byte[] shuffle(final byte[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(char[] array)
     * @since 3.6
     */
    public static char[] shuffle(final char[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(char[] array, Random random)
     * @since 3.6
     */
    public static char[] shuffle(final char[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(short[] array)
     * @since 3.6
     */
    public static short[] shuffle(final short[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(short[] array, Random random)
     * @since 3.6
     */
    public static short[] shuffle(final short[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(int[] array)
     * @since 3.6
     */
    public static int[] shuffle(final int[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(int[] array, Random random)
     * @since 3.6
     */
    public static int[] shuffle(final int[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(long[] array)
     * @since 3.6
     */
    public static long[] shuffle(final long[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(long[] array, Random random)
     * @since 3.6
     */
    public static long[] shuffle(final long[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(float[] array)
     * @since 3.6
     */
    public static float[] shuffle(final float[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(float[] array, Random random)
     * @since 3.6
     */
    public static float[] shuffle(final float[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(double[] array)
     * @since 3.6
     */
    public static double[] shuffle(final double[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see ArrayUtils#shuffle(double[] array, Random random)
     * @since 3.6
     */
    public static double[] shuffle(final double[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }
}
