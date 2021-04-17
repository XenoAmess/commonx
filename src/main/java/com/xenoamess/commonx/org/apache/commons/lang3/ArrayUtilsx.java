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

import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;

/**
 * <p>ArrayUtilsx class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 * @see org.apache.commons.lang3.ArrayUtils
 */
public class ArrayUtilsx extends ArrayUtils {
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
     * @param <T>   a T object.
     * @return the original array
     * @see org.apache.commons.lang3.ArrayUtils#reverse(Object[] array)
     */
    public static <T> T[] reverseSelf(final T[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(long[] array)
     */
    public static long[] reverseSelf(final long[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(int[] array)
     */
    public static int[] reverseSelf(final int[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(short[] array)
     */
    public static short[] reverseSelf(final short[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(char[] array)
     */
    public static char[] reverseSelf(final char[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(byte[] array)
     */
    public static byte[] reverseSelf(final byte[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(double[] array)
     */
    public static double[] reverseSelf(final double[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(float[] array)
     */
    public static float[] reverseSelf(final float[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(boolean[] array)
     */
    public static boolean[] reverseSelf(final boolean[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(boolean[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static boolean[] reverseSelf(final boolean[] array, final int startIndexInclusive,
                                        final int endIndexExclusive) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(byte[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static byte[] reverseSelf(final byte[] array, final int startIndexInclusive, final int endIndexExclusive) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(char[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static char[] reverseSelf(final char[] array, final int startIndexInclusive, final int endIndexExclusive) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(double[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static double[] reverseSelf(final double[] array, final int startIndexInclusive,
                                       final int endIndexExclusive) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(float[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static float[] reverseSelf(final float[] array, final int startIndexInclusive, final int endIndexExclusive) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(int[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static int[] reverseSelf(final int[] array, final int startIndexInclusive, final int endIndexExclusive) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(long[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static long[] reverseSelf(final long[] array, final int startIndexInclusive, final int endIndexExclusive) {
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
     * @see org.apache.commons.lang3.ArrayUtils#reverse(short[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static short[] reverseSelf(final short[] array, final int startIndexInclusive, final int endIndexExclusive) {
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
     * @param <T>                 a T object.
     * @return the original array
     * @see org.apache.commons.lang3.ArrayUtils#reverse(Object[] array, int startIndexInclusive, int endIndexExclusive)
     * @since 3.2
     */
    public static <T> T[] reverseSelf(final T[] array, final int startIndexInclusive, final int endIndexExclusive) {
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
     * @param <T>     a T object.
     * @return the original array
     * @see org.apache.commons.lang3.ArrayUtils#swap(Object[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static <T> T[] swapSelf(final T[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(long[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static long[] swapSelf(final long[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(int[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static int[] swapSelf(final int[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(short[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static short[] swapSelf(final short[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(char[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static char[] swapSelf(final char[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(byte[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static byte[] swapSelf(final byte[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(double[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static double[] swapSelf(final double[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(float[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static float[] swapSelf(final float[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(boolean[] array, int offset1, int offset2)
     * @since 3.5
     */
    public static boolean[] swapSelf(final boolean[] array, final int offset1, final int offset2) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(boolean[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static boolean[] swapSelf(final boolean[] array, int offset1, int offset2, int len) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(byte[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static byte[] swapSelf(final byte[] array, int offset1, int offset2, int len) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(char[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static char[] swapSelf(final char[] array, int offset1, int offset2, int len) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(double[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static double[] swapSelf(final double[] array, int offset1, int offset2, int len) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(float[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static float[] swapSelf(final float[] array, int offset1, int offset2, int len) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(int[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static int[] swapSelf(final int[] array, int offset1, int offset2, int len) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(long[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static long[] swapSelf(final long[] array, int offset1, int offset2, int len) {
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
     * @see org.apache.commons.lang3.ArrayUtils#swap(short[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static short[] swapSelf(final short[] array, int offset1, int offset2, int len) {
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
     * @param <T>     a T object.
     * @return the original array
     * @see org.apache.commons.lang3.ArrayUtils#swap(Object[] array, int offset1, int offset2, int len)
     * @since 3.5
     */
    public static <T> T[] swapSelf(final T[] array, int offset1, int offset2, int len) {
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
     * @param <T>    a T object.
     * @return the original array
     * @see org.apache.commons.lang3.ArrayUtils#shift(Object[] array, int offset)
     * @since 3.5
     */
    public static <T> T[] shiftSelf(final T[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(long[] array, int offset)
     * @since 3.5
     */
    public static long[] shiftSelf(final long[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(int[] array, int offset)
     * @since 3.5
     */
    public static int[] shiftSelf(final int[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(short[] array, int offset)
     * @since 3.5
     */
    public static short[] shiftSelf(final short[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(char[] array, int offset)
     * @since 3.5
     */
    public static char[] shiftSelf(final char[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(byte[] array, int offset)
     * @since 3.5
     */
    public static byte[] shiftSelf(final byte[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(double[] array, int offset)
     * @since 3.5
     */
    public static double[] shiftSelf(final double[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(float[] array, int offset)
     * @since 3.5
     */
    public static float[] shiftSelf(final float[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(boolean[] array, int offset)
     * @since 3.5
     */
    public static boolean[] shiftSelf(final boolean[] array, final int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(boolean[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static boolean[] shiftSelf(final boolean[] array, int startIndexInclusive, int endIndexExclusive,
                                      int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(byte[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static byte[] shiftSelf(final byte[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(char[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static char[] shiftSelf(final char[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(double[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static double[] shiftSelf(final double[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(float[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static float[] shiftSelf(final float[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(int[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static int[] shiftSelf(final int[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(long[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static long[] shiftSelf(final long[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
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
     * @param <T>                 a T object.
     * @return the original array
     * @see org.apache.commons.lang3.ArrayUtils#shift(Object[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static <T> T[] shiftSelf(final T[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shift(short[] array, int offset, int endIndexExclusive, int offset)
     * @since 3.5
     */
    public static short[] shiftSelf(final short[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        ArrayUtils.shift(array, startIndexInclusive, endIndexExclusive, offset);
        return array;
    }

    // Shuffle
    // ----------------------------------------------------------------------

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @param <T>   a T object.
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(Object[] array)
     * @since 3.6
     */
    public static <T> T[] shuffleSelf(final T[] array) {
        ArrayUtils.shuffle(array);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     * @param <T>    a T object.
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(Object[] array, Random random)
     * @since 3.6
     */
    public static <T> T[] shuffleSelf(final T[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(boolean[] array)
     * @since 3.6
     */
    public static boolean[] shuffleSelf(final boolean[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(boolean[] array, Random random)
     * @since 3.6
     */
    public static boolean[] shuffleSelf(final boolean[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(byte[] array)
     * @since 3.6
     */
    public static byte[] shuffleSelf(final byte[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(byte[] array, Random random)
     * @since 3.6
     */
    public static byte[] shuffleSelf(final byte[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(char[] array)
     * @since 3.6
     */
    public static char[] shuffleSelf(final char[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(char[] array, Random random)
     * @since 3.6
     */
    public static char[] shuffleSelf(final char[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(short[] array)
     * @since 3.6
     */
    public static short[] shuffleSelf(final short[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(short[] array, Random random)
     * @since 3.6
     */
    public static short[] shuffleSelf(final short[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(int[] array)
     * @since 3.6
     */
    public static int[] shuffleSelf(final int[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(int[] array, Random random)
     * @since 3.6
     */
    public static int[] shuffleSelf(final int[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(long[] array)
     * @since 3.6
     */
    public static long[] shuffleSelf(final long[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(long[] array, Random random)
     * @since 3.6
     */
    public static long[] shuffleSelf(final long[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(float[] array)
     * @since 3.6
     */
    public static float[] shuffleSelf(final float[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(float[] array, Random random)
     * @since 3.6
     */
    public static float[] shuffleSelf(final float[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     * @return the original array
     * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Fisher-Yates shuffle algorithm</a>
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(double[] array)
     * @since 3.6
     */
    public static double[] shuffleSelf(final double[] array) {
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
     * @see org.apache.commons.lang3.ArrayUtils#shuffle(double[] array, Random random)
     * @since 3.6
     */
    public static double[] shuffleSelf(final double[] array, final Random random) {
        ArrayUtils.shuffle(array, random);
        return array;
    }

    /**
     * <p>Checks whether several arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * <p>Any multi-dimensional aspects of the arrays are ignored.
     *
     * @param arrays arrays of the arrays, must not be {@code null}
     * @return {@code true} if length of all arrays matches
     * @throws IllegalArgumentException if any object in arrays argument is not an array.
     */
    public static boolean isSameLength(final Object... arrays) {
        if (arrays.length <= 1) {
            return true;
        }
        int length0 = ArrayUtils.getLength(arrays[0]);
        for (int i = 1, len = arrays.length; i < len; i++) {
            if (length0 != ArrayUtils.getLength(arrays[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if an array is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final Object array) {
        return ArrayUtils.getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of Objects is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final Object array) {
        return !isEmpty(array);
    }
}
