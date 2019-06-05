/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.xenoamess.commonx.java.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

/**
 * <p>Arraysx class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 * @see java.util.Arrays
 */
public class Arraysx {
    /**
     * <p>Instances of this class should NOT be constructed in standard programming.
     *
     * <p>This constructor is public to permit tools that require a JavaBean instance
     * to operate.
     */
    public Arraysx() {
        super();
    }

    /*
     * Sorting methods. Note that all public "sort" methods take the
     * same form: Performing argument checks if necessary, and then
     * expanding arguments into those required for the internal
     * implementation methods residing in other package-private
     * classes (except for legacyMergeSort, included in this class).
     */

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     * @return the original array
     */
    public static int[] sortSelf(final int[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static int[] sortSelf(int[] a, int fromIndex, int toIndex) {
        Arrays.sort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     * @return the original array
     */
    public static long[] sortSelf(long[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static long[] sortSelf(long[] a, int fromIndex, int toIndex) {
        Arrays.sort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     * @return the original array
     */
    public static short[] sortSelf(short[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static short[] sortSelf(short[] a, int fromIndex, int toIndex) {
        Arrays.sort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     * @return the original array
     */
    public static char[] sortSelf(char[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static char[] sortSelf(char[] a, int fromIndex, int toIndex) {
        Arrays.sort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     * @return the original array
     */
    public static byte[] sortSelf(byte[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static byte[] sortSelf(byte[] a, int fromIndex, int toIndex) {
        Arrays.sort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * <p>The {@code <} relation does not provide a total order on all float
     * values: {@code -0.0f == 0.0f} is {@code true} and a {@code Float.NaN}
     * value compares neither less than, greater than, nor equal to any value,
     * even itself. This method uses the total order imposed by the method
     * {@link java.lang.Float#compareTo}: {@code -0.0f} is treated as less than value
     * {@code 0.0f} and {@code Float.NaN} is considered greater than any
     * other value and all {@code Float.NaN} values are considered equal.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     * @return the original array
     */
    public static float[] sortSelf(float[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * <p>The {@code <} relation does not provide a total order on all float
     * values: {@code -0.0f == 0.0f} is {@code true} and a {@code Float.NaN}
     * value compares neither less than, greater than, nor equal to any value,
     * even itself. This method uses the total order imposed by the method
     * {@link java.lang.Float#compareTo}: {@code -0.0f} is treated as less than value
     * {@code 0.0f} and {@code Float.NaN} is considered greater than any
     * other value and all {@code Float.NaN} values are considered equal.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static float[] sortSelf(float[] a, int fromIndex, int toIndex) {
        Arrays.sort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * <p>The {@code <} relation does not provide a total order on all double
     * values: {@code -0.0d == 0.0d} is {@code true} and a {@code Double.NaN}
     * value compares neither less than, greater than, nor equal to any value,
     * even itself. This method uses the total order imposed by the method
     * {@link java.lang.Double#compareTo}: {@code -0.0d} is treated as less than value
     * {@code 0.0d} and {@code Double.NaN} is considered greater than any
     * other value and all {@code Double.NaN} values are considered equal.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     * @return the original array
     */
    public static double[] sortSelf(double[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * <p>The {@code <} relation does not provide a total order on all double
     * values: {@code -0.0d == 0.0d} is {@code true} and a {@code Double.NaN}
     * value compares neither less than, greater than, nor equal to any value,
     * even itself. This method uses the total order imposed by the method
     * {@link java.lang.Double#compareTo}: {@code -0.0d} is treated as less than value
     * {@code 0.0d} and {@code Double.NaN} is considered greater than any
     * other value and all {@code Double.NaN} values are considered equal.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static double[] sortSelf(double[] a, int fromIndex, int toIndex) {
        Arrays.sort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @return the original array
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(byte[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(byte[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static byte[] parallelSortSelf(byte[] a) {
        Arrays.parallelSort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending numerical order.
     * The range to be sorted extends from the index {@code fromIndex},
     * inclusive, to the index {@code toIndex}, exclusive. If
     * {@code fromIndex == toIndex}, the range to be sorted is empty.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(byte[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(byte[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static byte[] parallelSortSelf(byte[] a, int fromIndex, int toIndex) {
        Arrays.parallelSort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @return the original array
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(char[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(char[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static char[] parallelSortSelf(char[] a) {
        Arrays.parallelSort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending numerical order.
     * The range to be sorted extends from the index {@code fromIndex},
     * inclusive, to the index {@code toIndex}, exclusive. If
     * {@code fromIndex == toIndex}, the range to be sorted is empty.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(char[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(char[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static char[] parallelSortSelf(char[] a, int fromIndex, int toIndex) {
        Arrays.parallelSort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @return the original array
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(short[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(short[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static short[] parallelSortSelf(short[] a) {
        Arrays.parallelSort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending numerical order.
     * The range to be sorted extends from the index {@code fromIndex},
     * inclusive, to the index {@code toIndex}, exclusive. If
     * {@code fromIndex == toIndex}, the range to be sorted is empty.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(short[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(short[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static short[] parallelSortSelf(short[] a, int fromIndex, int toIndex) {
        Arrays.parallelSort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @return the original array
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(int[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(int[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static int[] parallelSortSelf(int[] a) {
        Arrays.parallelSort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending numerical order.
     * The range to be sorted extends from the index {@code fromIndex},
     * inclusive, to the index {@code toIndex}, exclusive. If
     * {@code fromIndex == toIndex}, the range to be sorted is empty.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(int[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(int[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static int[] parallelSortSelf(int[] a, int fromIndex, int toIndex) {
        Arrays.parallelSort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @return the original array
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(long[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(long[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static long[] parallelSortSelf(long[] a) {
        Arrays.parallelSort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending numerical order.
     * The range to be sorted extends from the index {@code fromIndex},
     * inclusive, to the index {@code toIndex}, exclusive. If
     * {@code fromIndex == toIndex}, the range to be sorted is empty.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(long[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(long[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static long[] parallelSortSelf(long[] a, int fromIndex, int toIndex) {
        Arrays.parallelSort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @return the original array
     * <p>The {@code <} relation does not provide a total order on all float
     * values: {@code -0.0f == 0.0f} is {@code true} and a {@code Float.NaN}
     * value compares neither less than, greater than, nor equal to any value,
     * even itself. This method uses the total order imposed by the method
     * {@link java.lang.Float#compareTo}: {@code -0.0f} is treated as less than value
     * {@code 0.0f} and {@code Float.NaN} is considered greater than any
     * other value and all {@code Float.NaN} values are considered equal.
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(float[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(float[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static float[] parallelSortSelf(float[] a) {
        Arrays.parallelSort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending numerical order.
     * The range to be sorted extends from the index {@code fromIndex},
     * inclusive, to the index {@code toIndex}, exclusive. If
     * {@code fromIndex == toIndex}, the range to be sorted is empty.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * <p>The {@code <} relation does not provide a total order on all float
     * values: {@code -0.0f == 0.0f} is {@code true} and a {@code Float.NaN}
     * value compares neither less than, greater than, nor equal to any value,
     * even itself. This method uses the total order imposed by the method
     * {@link java.lang.Float#compareTo}: {@code -0.0f} is treated as less than value
     * {@code 0.0f} and {@code Float.NaN} is considered greater than any
     * other value and all {@code Float.NaN} values are considered equal.
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(float[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(float[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static float[] parallelSortSelf(float[] a, int fromIndex, int toIndex) {
        Arrays.parallelSort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     * @return the original array
     * <p>The {@code <} relation does not provide a total order on all double
     * values: {@code -0.0d == 0.0d} is {@code true} and a {@code Double.NaN}
     * value compares neither less than, greater than, nor equal to any value,
     * even itself. This method uses the total order imposed by the method
     * {@link java.lang.Double#compareTo}: {@code -0.0d} is treated as less than value
     * {@code 0.0d} and {@code Double.NaN} is considered greater than any
     * other value and all {@code Double.NaN} values are considered equal.
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(double[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(double[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static double[] parallelSortSelf(double[] a) {
        Arrays.parallelSort(a);
        return a;
    }

    /**
     * Sorts the specified range of the array into ascending numerical order.
     * The range to be sorted extends from the index {@code fromIndex},
     * inclusive, to the index {@code toIndex}, exclusive. If
     * {@code fromIndex == toIndex}, the range to be sorted is empty.
     *
     * <p>The {@code <} relation does not provide a total order on all double
     * values: {@code -0.0d == 0.0d} is {@code true} and a {@code Double.NaN}
     * value compares neither less than, greater than, nor equal to any value,
     * even itself. This method uses the total order imposed by the method
     * {@link java.lang.Double#compareTo}: {@code -0.0d} is treated as less than value
     * {@code 0.0d} and {@code Double.NaN} is considered greater than any
     * other value and all {@code Double.NaN} values are considered equal.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex   the index of the last element, exclusive, to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(double[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(double[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static double[] parallelSortSelf(double[] a, int fromIndex, int toIndex) {
        Arrays.parallelSort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array of objects into ascending order, according
     * to the {@linkplain Comparable natural ordering} of its elements.
     * All elements in the array must implement the {@link java.lang.Comparable}
     * interface.  Furthermore, all elements in the array must be
     * <i>mutually comparable</i> (that is, {@code e1.compareTo(e2)} must
     * not throw a {@code ClassCastException} for any elements {@code e1}
     * and {@code e2} in the array).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * @param a the array to be sorted
     * @return the original array
     * @throws java.lang.ClassCastException       if the array contains elements that are not
     *                                            <i>mutually comparable</i> (for example, strings and integers)
     * @throws java.lang.IllegalArgumentException (optional) if the natural
     *                                            ordering of the array elements is found to violate the
     *                                            {@link java.lang.Comparable} contract
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(Object[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(Object[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static <T extends Comparable<? super T>> T[] parallelSortSelf(T[] a) {
        Arrays.parallelSort(a);
        return a;
    }

    /**
     * Sorts the specified range of the specified array of objects into
     * ascending order, according to the
     * {@linkplain Comparable natural ordering} of its
     * elements.  The range to be sorted extends from index
     * {@code fromIndex}, inclusive, to index {@code toIndex}, exclusive.
     * (If {@code fromIndex==toIndex}, the range to be sorted is empty.)  All
     * elements in this range must implement the {@link java.lang.Comparable}
     * interface.  Furthermore, all elements in this range must be <i>mutually
     * comparable</i> (that is, {@code e1.compareTo(e2)} must not throw a
     * {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the array).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element (inclusive) to be
     *                  sorted
     * @param toIndex   the index of the last element (exclusive) to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex} or
     *                                                  (optional) if the natural ordering of the array elements is
     *                                                  found to violate the {@link java.lang.Comparable} contract
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @throws java.lang.ClassCastException             if the array contains elements that are
     *                                                  not <i>mutually comparable</i> (for example, strings and
     *                                                  integers).
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(Object[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(Object[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static <T extends Comparable<? super T>> T[] parallelSortSelf(
            T[] a, int fromIndex, int toIndex) {
        Arrays.parallelSort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array of objects according to the order induced by
     * the specified comparator.  All elements in the array must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the array).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * @param <T> the class of the objects to be sorted
     * @param a   the array to be sorted
     * @param cmp the comparator to determine the order of the array.  A
     *            {@code null} value indicates that the elements'
     *            {@linkplain Comparable natural ordering} should be used.
     * @return the original array
     * @throws java.lang.ClassCastException       if the array contains elements that are
     *                                            not <i>mutually comparable</i> using the specified comparator
     * @throws java.lang.IllegalArgumentException (optional) if the comparator is
     *                                            found to violate the {@link java.util.Comparator} contract
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(Object[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(Object[]) Arrays.sort} method. The algorithm requires a
     * working space no greater than the size of the original array. The
     * {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is used to
     * execute any parallel tasks.
     * @since 1.8
     */
    public static <T> T[] parallelSortSelf(T[] a, Comparator<? super T> cmp) {
        Arrays.parallelSort(a, cmp);
        return a;
    }

    /**
     * Sorts the specified range of the specified array of objects according
     * to the order induced by the specified comparator.  The range to be
     * sorted extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be sorted is empty.)  All elements in the range must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the range).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * @param <T>       the class of the objects to be sorted
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element (inclusive) to be
     *                  sorted
     * @param toIndex   the index of the last element (exclusive) to be sorted
     * @param cmp       the comparator to determine the order of the array.  A
     *                  {@code null} value indicates that the elements'
     *                  {@linkplain Comparable natural ordering} should be used.
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex} or
     *                                                  (optional) if the natural ordering of the array elements is
     *                                                  found to violate the {@link java.lang.Comparable} contract
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @throws java.lang.ClassCastException             if the array contains elements that are
     *                                                  not <i>mutually comparable</i> (for example, strings and
     *                                                  integers).
     * @implNote The sorting algorithm is a parallel sort-merge that breaks the
     * array into sub-arrays that are themselves sorted and then merged. When
     * the sub-array length reaches a minimum granularity, the sub-array is
     * sorted using the appropriate {@link java.util.Arrays#sort(Object[]) Arrays.sort}
     * method. If the length of the specified array is less than the minimum
     * granularity, then it is sorted using the appropriate {@link
     * Arrays#sort(Object[]) Arrays.sort} method. The algorithm requires a working
     * space no greater than the size of the specified range of the original
     * array. The {@link java.util.concurrent.ForkJoinPool#commonPool() ForkJoin common pool} is
     * used to execute any parallel tasks.
     * @since 1.8
     */
    public static <T> T[] parallelSortSelf(
            T[] a, int fromIndex, int toIndex, Comparator<? super T> cmp) {
        Arrays.parallelSort(a, fromIndex, toIndex, cmp);
        return a;
    }

    /*
     * Sorting of complex type arrays.
     */

    /**
     * Sorts the specified array of objects into ascending order, according
     * to the {@linkplain Comparable natural ordering} of its elements.
     * All elements in the array must implement the {@link java.lang.Comparable}
     * interface.  Furthermore, all elements in the array must be
     * <i>mutually comparable</i> (that is, {@code e1.compareTo(e2)} must
     * not throw a {@code ClassCastException} for any elements {@code e1}
     * and {@code e2} in the array).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * <p>Implementation note: This implementation is a stable, adaptive,
     * iterative mergesort that requires far fewer than n lg(n) comparisons
     * when the input array is partially sorted, while offering the
     * performance of a traditional mergesort when the input array is
     * randomly ordered.  If the input array is nearly sorted, the
     * implementation requires approximately n comparisons.  Temporary
     * storage requirements vary from a small constant for nearly sorted
     * input arrays to n/2 object references for randomly ordered input
     * arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     *
     * @param a the array to be sorted
     * @return the original array
     * @throws java.lang.ClassCastException       if the array contains elements that are not
     *                                            <i>mutually comparable</i> (for example, strings and integers)
     * @throws java.lang.IllegalArgumentException (optional) if the natural
     *                                            ordering of the array elements is found to violate the
     *                                            {@link java.lang.Comparable} contract
     */
    public static Object[] sortSelf(Object[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Sorts the specified range of the specified array of objects into
     * ascending order, according to the
     * {@linkplain Comparable natural ordering} of its
     * elements.  The range to be sorted extends from index
     * {@code fromIndex}, inclusive, to index {@code toIndex}, exclusive.
     * (If {@code fromIndex==toIndex}, the range to be sorted is empty.)  All
     * elements in this range must implement the {@link java.lang.Comparable}
     * interface.  Furthermore, all elements in this range must be <i>mutually
     * comparable</i> (that is, {@code e1.compareTo(e2)} must not throw a
     * {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the array).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * <p>Implementation note: This implementation is a stable, adaptive,
     * iterative mergesort that requires far fewer than n lg(n) comparisons
     * when the input array is partially sorted, while offering the
     * performance of a traditional mergesort when the input array is
     * randomly ordered.  If the input array is nearly sorted, the
     * implementation requires approximately n comparisons.  Temporary
     * storage requirements vary from a small constant for nearly sorted
     * input arrays to n/2 object references for randomly ordered input
     * arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element (inclusive) to be
     *                  sorted
     * @param toIndex   the index of the last element (exclusive) to be sorted
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex} or
     *                                                  (optional) if the natural ordering of the array elements is
     *                                                  found to violate the {@link java.lang.Comparable} contract
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @throws java.lang.ClassCastException             if the array contains elements that are
     *                                                  not <i>mutually comparable</i> (for example, strings and
     *                                                  integers).
     */
    public static Object[] sortSelf(Object[] a, int fromIndex, int toIndex) {
        Arrays.sort(a, fromIndex, toIndex);
        return a;
    }

    /**
     * Sorts the specified array of objects according to the order induced by
     * the specified comparator.  All elements in the array must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the array).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * <p>Implementation note: This implementation is a stable, adaptive,
     * iterative mergesort that requires far fewer than n lg(n) comparisons
     * when the input array is partially sorted, while offering the
     * performance of a traditional mergesort when the input array is
     * randomly ordered.  If the input array is nearly sorted, the
     * implementation requires approximately n comparisons.  Temporary
     * storage requirements vary from a small constant for nearly sorted
     * input arrays to n/2 object references for randomly ordered input
     * arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     *
     * @param <T> the class of the objects to be sorted
     * @param a   the array to be sorted
     * @param c   the comparator to determine the order of the array.  A
     *            {@code null} value indicates that the elements'
     *            {@linkplain Comparable natural ordering} should be used.
     * @return the original array
     * @throws java.lang.ClassCastException       if the array contains elements that are
     *                                            not <i>mutually comparable</i> using the specified comparator
     * @throws java.lang.IllegalArgumentException (optional) if the comparator is
     *                                            found to violate the {@link java.util.Comparator} contract
     */
    public static <T> T[] sortSelf(T[] a, Comparator<? super T> c) {
        Arrays.sort(a, c);
        return a;
    }


    /**
     * Sorts the specified range of the specified array of objects according
     * to the order induced by the specified comparator.  The range to be
     * sorted extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be sorted is empty.)  All elements in the range must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the range).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * <p>Implementation note: This implementation is a stable, adaptive,
     * iterative mergesort that requires far fewer than n lg(n) comparisons
     * when the input array is partially sorted, while offering the
     * performance of a traditional mergesort when the input array is
     * randomly ordered.  If the input array is nearly sorted, the
     * implementation requires approximately n comparisons.  Temporary
     * storage requirements vary from a small constant for nearly sorted
     * input arrays to n/2 object references for randomly ordered input
     * arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     *
     * @param <T>       the class of the objects to be sorted
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element (inclusive) to be
     *                  sorted
     * @param toIndex   the index of the last element (exclusive) to be sorted
     * @param c         the comparator to determine the order of the array.  A
     *                  {@code null} value indicates that the elements'
     *                  {@linkplain Comparable natural ordering} should be used.
     * @return the original array
     * @throws java.lang.ClassCastException             if the array contains elements that are not
     *                                                  <i>mutually comparable</i> using the specified comparator.
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex} or
     *                                                  (optional) if the comparator is found to violate the
     *                                                  {@link java.util.Comparator} contract
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     */
    public static <T> T[] sortSelf(
            T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
        Arrays.sort(a, fromIndex, toIndex, c);
        return a;
    }

    // Parallel prefix

    /**
     * Cumulates, in parallel, each element of the given array in place,
     * using the supplied function. For example if the array initially
     * holds {@code [2, 1, 0, 3]} and the operation performs addition,
     * then upon return the array holds {@code [2, 3, 3, 6]}.
     * Parallel prefix computation is usually more efficient than
     * sequential loops for large arrays.
     *
     * @param <T>   the class of the objects in the array
     * @param array the array, which is modified in-place by this method
     * @param op    a side-effect-free, associative function to perform the
     *              cumulation
     * @return the original array
     * @throws java.lang.NullPointerException if the specified array or function is null
     * @since 1.8
     */
    public static <T> T[] parallelPrefixSelf(T[] array, BinaryOperator<T> op) {
        Arrays.parallelPrefix(array, op);
        return array;
    }

    /**
     * Performs {@link #parallelPrefixSelf(Object[], BinaryOperator)}
     * for the given subrange of the array.
     *
     * @param <T>       the class of the objects in the array
     * @param array     the array
     * @param fromIndex the index of the first element, inclusive
     * @param toIndex   the index of the last element, exclusive
     * @param op        a side-effect-free, associative function to perform the
     *                  cumulation
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > array.length}
     * @throws java.lang.NullPointerException           if the specified array or function is null
     * @since 1.8
     */
    public static <T> T[] parallelPrefixSelf(
            T[] array, int fromIndex, int toIndex, BinaryOperator<T> op) {
        Arrays.parallelPrefix(array, fromIndex, toIndex, op);
        return array;
    }

    /**
     * Cumulates, in parallel, each element of the given array in place,
     * using the supplied function. For example if the array initially
     * holds {@code [2, 1, 0, 3]} and the operation performs addition,
     * then upon return the array holds {@code [2, 3, 3, 6]}.
     * Parallel prefix computation is usually more efficient than
     * sequential loops for large arrays.
     *
     * @param array the array, which is modified in-place by this method
     * @param op    a side-effect-free, associative function to perform the
     *              cumulation
     * @return the original array
     * @throws java.lang.NullPointerException if the specified array or function is null
     * @since 1.8
     */
    public static long[] parallelPrefixSelf(long[] array, LongBinaryOperator op) {
        Arrays.parallelPrefix(array, op);
        return array;
    }

    /**
     * Performs {@link #parallelPrefixSelf(long[], LongBinaryOperator)}
     * for the given subrange of the array.
     *
     * @param array     the array
     * @param fromIndex the index of the first element, inclusive
     * @param toIndex   the index of the last element, exclusive
     * @param op        a side-effect-free, associative function to perform the
     *                  cumulation
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > array.length}
     * @throws java.lang.NullPointerException           if the specified array or function is null
     * @since 1.8
     */
    public static long[] parallelPrefixSelf(
            long[] array, int fromIndex, int toIndex, LongBinaryOperator op) {
        Arrays.parallelPrefix(array, fromIndex, toIndex, op);
        return array;
    }

    /**
     * Cumulates, in parallel, each element of the given array in place,
     * using the supplied function. For example if the array initially
     * holds {@code [2.0, 1.0, 0.0, 3.0]} and the operation performs addition,
     * then upon return the array holds {@code [2.0, 3.0, 3.0, 6.0]}.
     * Parallel prefix computation is usually more efficient than
     * sequential loops for large arrays.
     *
     * <p> Because floating-point operations may not be strictly associative,
     * the returned result may not be identical to the value that would be
     * obtained if the operation was performed sequentially.
     *
     * @param array the array, which is modified in-place by this method
     * @param op    a side-effect-free function to perform the cumulation
     * @return the original array
     * @throws java.lang.NullPointerException if the specified array or function is null
     * @since 1.8
     */
    public static double[] parallelPrefixSelf(double[] array, DoubleBinaryOperator op) {
        Arrays.parallelPrefix(array, op);
        return array;
    }

    /**
     * Performs {@link #parallelPrefixSelf(double[], DoubleBinaryOperator)}
     * for the given subrange of the array.
     *
     * @param array     the array
     * @param fromIndex the index of the first element, inclusive
     * @param toIndex   the index of the last element, exclusive
     * @param op        a side-effect-free, associative function to perform the
     *                  cumulation
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > array.length}
     * @throws java.lang.NullPointerException           if the specified array or function is null
     * @since 1.8
     */
    public static double[] parallelPrefixSelf(
            double[] array, int fromIndex, int toIndex, DoubleBinaryOperator op) {
        Arrays.parallelPrefix(array, fromIndex, toIndex, op);
        return array;
    }

    /**
     * Cumulates, in parallel, each element of the given array in place,
     * using the supplied function. For example if the array initially
     * holds {@code [2, 1, 0, 3]} and the operation performs addition,
     * then upon return the array holds {@code [2, 3, 3, 6]}.
     * Parallel prefix computation is usually more efficient than
     * sequential loops for large arrays.
     *
     * @param array the array, which is modified in-place by this method
     * @param op    a side-effect-free, associative function to perform the
     *              cumulation
     * @return the original array
     * @throws java.lang.NullPointerException if the specified array or function is null
     * @since 1.8
     */
    public static int[] parallelPrefixSelf(int[] array, IntBinaryOperator op) {
        Arrays.parallelPrefix(array, op);
        return array;
    }

    /**
     * Performs {@link #parallelPrefixSelf(int[], IntBinaryOperator)}
     * for the given subrange of the array.
     *
     * @param array     the array
     * @param fromIndex the index of the first element, inclusive
     * @param toIndex   the index of the last element, exclusive
     * @param op        a side-effect-free, associative function to perform the
     *                  cumulation
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > array.length}
     * @throws java.lang.NullPointerException           if the specified array or function is null
     * @since 1.8
     */
    public static int[] parallelPrefixSelf(
            int[] array, int fromIndex, int toIndex, IntBinaryOperator op) {
        Arrays.parallelPrefix(array, fromIndex, toIndex, op);
        return array;
    }


    // Filling

    /**
     * Assigns the specified long value to each element of the specified array
     * of longs.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @return the original array
     * @see Arrays#fill(long[] a, long val)
     */
    public static long[] fillSelf(long[] a, long val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified long value to each element of the specified
     * range of the specified array of longs.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @see Arrays#fill(long[] a, int fromIndex, int toIndex, long val)
     */
    public static long[] fillSelf(long[] a, int fromIndex, int toIndex, long val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }

    /**
     * Assigns the specified int value to each element of the specified array
     * of ints.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @return the original array
     * @see Arrays#fill(int[] a, int val)
     */
    public static int[] fillSelf(int[] a, int val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified int value to each element of the specified
     * range of the specified array of ints.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @see Arrays#fill(int[] a, int fromIndex, int toIndex, int val)
     */
    public static int[] fillSelf(int[] a, int fromIndex, int toIndex, int val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }

    /**
     * Assigns the specified short value to each element of the specified array
     * of shorts.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @return the original array
     * @see Arrays#fill(short[] a, short val)
     */
    public static short[] fillSelf(short[] a, short val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified short value to each element of the specified
     * range of the specified array of shorts.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @see Arrays#fill(short[] a, int fromIndex, int toIndex, short val)
     */
    public static short[] fillSelf(short[] a, int fromIndex, int toIndex, short val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }


    /**
     * Assigns the specified char value to each element of the specified array
     * of chars.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @return the original array
     * @see Arrays#fill(char[] a, char val)
     */
    public static char[] fillSelf(char[] a, char val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified char value to each element of the specified
     * range of the specified array of chars.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @see Arrays#fill(char[] a, int fromIndex, int toIndex, char val)
     */
    public static char[] fillSelf(char[] a, int fromIndex, int toIndex, char val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }

    /**
     * Assigns the specified byte value to each element of the specified array
     * of bytes.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @return the original array
     * @see Arrays#fill(byte[] a, byte val)
     */
    public static byte[] fillSelf(byte[] a, byte val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified byte value to each element of the specified
     * range of the specified array of bytes.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @see Arrays#fill(byte[] a, int fromIndex, int toIndex, byte val)
     */
    public static byte[] fillSelf(byte[] a, int fromIndex, int toIndex, byte val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }

    /**
     * Assigns the specified boolean value to each element of the specified
     * array of booleans.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @return the original array
     * @see Arrays#fill(boolean[] a, boolean val)
     */
    public static boolean[] fillSelf(boolean[] a, boolean val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified boolean value to each element of the specified
     * range of the specified array of booleans.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @see Arrays#fill(boolean[] a, int fromIndex, int toIndex, boolean val)
     */
    public static boolean[] fillSelf(boolean[] a, int fromIndex, int toIndex,
                                     boolean val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }

    /**
     * Assigns the specified double value to each element of the specified
     * array of doubles.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @return the original array
     * @see Arrays#fill(double[] a, double val)
     */
    public static double[] fillSelf(double[] a, double val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified double value to each element of the specified
     * range of the specified array of doubles.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @see Arrays#fill(double[] a, int fromIndex, int toIndex, double val)
     */
    public static double[] fillSelf(double[] a, int fromIndex, int toIndex, double val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }

    /**
     * Assigns the specified float value to each element of the specified array
     * of floats.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @return the original array
     * @see Arrays#fill(float[] a, float val)
     */
    public static float[] fillSelf(float[] a, float val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified float value to each element of the specified
     * range of the specified array of floats.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @see Arrays#fill(float[] a, int fromIndex, int toIndex, float val)
     */
    public static float[] fillSelf(float[] a, int fromIndex, int toIndex, float val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }

    /**
     * Assigns the specified Object reference to each element of the specified
     * array of Objects.
     *
     * @param a   the array to be filled
     * @param val the value to be stored in all elements of the array
     * @param <T> a T object.
     * @return the original array
     * @throws java.lang.ArrayStoreException if the specified value is not of a
     *                                       runtime type that can be stored in the specified array
     * @see Arrays#fill(Object[] a, Object val)
     */
    public static <T> T[] fillSelf(T[] a, T val) {
        Arrays.fill(a, val);
        return a;
    }

    /**
     * Assigns the specified Object reference to each element of the specified
     * range of the specified array of Objects.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a         the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *                  filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be
     *                  filled with the specified value
     * @param val       the value to be stored in all elements of the array
     * @param <T>       a T object.
     * @return the original array
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     * @throws java.lang.ArrayStoreException            if the specified value is not of a
     *                                                  runtime type that can be stored in the specified array
     * @see Arrays#fill(Object[] a, int fromIndex, int toIndex, Object val)
     */
    public static <T> T[] fillSelf(T[] a, int fromIndex, int toIndex, T val) {
        Arrays.fill(a, fromIndex, toIndex, val);
        return a;
    }

    // Cloning

    /**
     * Copies the specified array, truncating or padding with nulls (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code null}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     * The resulting array is of exactly the same class as the original array.
     *
     * @param <T>      the class of the objects in the array
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with nulls
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(Object[] original, int newLength)
     * @since 1.6
     */
    public static <T> T[] copyOf(T[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Copies the specified array, truncating or padding with nulls (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code null}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     * The resulting array is of the class {@code newType}.
     *
     * @param <U>      the class of the objects in the original array
     * @param <T>      the class of the objects in the returned array
     * @param original the array to be copied
     * @param newType  the class of the copy to be returned
     * @return a copy of the original array, truncated or padded with nulls
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @throws java.lang.ArrayStoreException        if an element copied from
     *                                              {@code original} is not of a runtime type that can be stored in
     *                                              an array of class {@code newType}
     * @see Arrays#copyOf(Object[] original, int newLength, Class newType)
     * @since 1.6
     */
    public static <T, U> T[] copyOf(U[] original, Class<? extends T[]> newType) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length, newType);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code (byte)0}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with zeros
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(byte[] original, int newLength)
     * @since 1.6
     */
    public static byte[] copyOf(byte[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code (short)0}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with zeros
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(short[] original, int newLength)
     * @since 1.6
     */
    public static short[] copyOf(short[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code 0}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with zeros
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(int[] original, int newLength)
     * @since 1.6
     */
    public static int[] copyOf(int[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code 0L}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with zeros
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(long[] original, int newLength)
     * @since 1.6
     */
    public static long[] copyOf(long[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Copies the specified array, truncating or padding with null characters (if necessary)
     * so the copy has the specified length.  For all indices that are valid
     * in both the original array and the copy, the two arrays will contain
     * identical values.  For any indices that are valid in the copy but not
     * the original, the copy will contain {@code '\\u000'}.  Such indices
     * will exist if and only if the specified length is greater than that of
     * the original array.
     *
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with null characters
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(char[] original, int newLength)
     * @since 1.6
     */
    public static char[] copyOf(char[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code 0f}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with zeros
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(float[] original, int newLength)
     * @since 1.6
     */
    public static float[] copyOf(float[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code 0d}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with zeros
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(double[] original, int newLength)
     * @since 1.6
     */
    public static double[] copyOf(double[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Copies the specified array, truncating or padding with {@code false} (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code false}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @return a copy of the original array, truncated or padded with false elements
     * to obtain the specified length
     * @throws java.lang.NegativeArraySizeException if {@code newLength} is negative
     * @throws java.lang.NullPointerException       if {@code original} is null
     * @see Arrays#copyOf(boolean[] original, int newLength)
     * @since 1.6
     */
    public static boolean[] copyOf(boolean[] original) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, original.length);
    }


    // Extra

    /**
     * <p>Fill a array with creating new instances of the component class
     * using constructor that accept 0 arguments.
     *
     * <pre>
     *     private final AtomicBoolean[][] rawKeys =
     *             new AtomicBoolean[][]{
     *                     fillNew(new AtomicBoolean[1000]),
     *                     fillNew(new AtomicBoolean[1000]),
     *                     fillNew(new AtomicBoolean[1000]),
     *                     fillNew(new AtomicBoolean[1000])};
     * </pre>
     *
     * @param <T>   the component type of the array
     * @param array the array to be filled
     * @throws java.lang.IllegalArgumentException if array is null,
     *                                            or if T have no such constructor,
     *                                            or the constructor is not accessible,
     *                                            or the class cannot be instantiated.
     * @author XenoAmess
     * @since 3.10
     */
    @SuppressWarnings("unchecked")
    public static <T> void fillNew(T[] array) {
        com.xenoamess.commonx.java.lang.IllegalArgumentExceptionUtilsx.isAnyNullInParamsThenThrowIllegalArgumentException(array);
        if (array == null) {
            throw new IllegalArgumentException("The input array must not be null.");
        }
        Class arrayClass = array.getClass();
        Class componentClass = arrayClass.getComponentType();
        try {
            Constructor defaultConstructor = componentClass.getConstructor();
            for (int i = 0, len = array.length; i < len; i++) {
                array[i] = (T) defaultConstructor.newInstance();
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    "The class must have an constructor that accept 0 arguments, but not : "
                            + componentClass.getCanonicalName()
            );
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(
                    "The class's constructor that accept 0 arguments must be accessible by this class, but not : "
                            + componentClass.getCanonicalName()
            );
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(
                    "The class must be able to be instantiated, but not : "
                            + componentClass.getCanonicalName()
            );
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(
                    "The class's constructor that accept 0 arguments must really can accept 0 arguments, but not : "
                            + componentClass.getCanonicalName()
            );
        }
    }

    /**
     * <p>Fill a array with creating new instances of the component class
     * using constructor that accept 0 arguments.
     *
     * <pre>
     *     private final AtomicBoolean[][] rawKeys =
     *             new AtomicBoolean[][]{
     *                     fillNew(new AtomicBoolean[1000]),
     *                     fillNew(new AtomicBoolean[1000]),
     *                     fillNew(new AtomicBoolean[1000]),
     *                     fillNew(new AtomicBoolean[1000])};
     * </pre>
     *
     * @param <T>   the component type of the array
     * @param array the array to be filled
     * @return the original array
     * @throws java.lang.IllegalArgumentException if array is null,
     *                                            or if T have no such constructor,
     *                                            or the constructor is not accessible,
     *                                            or the class cannot be instantiated.
     * @author XenoAmess
     * @since 3.10
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] fillNewSelf(T[] array) {
        fillNew(array);
        return array;
    }

    /**
     * Checks that {@code fromIndex} and {@code toIndex} are in
     * the range and throws an exception if they aren't.
     *
     * @param arrayLength a int.
     * @param fromIndex   a int.
     * @param toIndex     a int.
     */
    public static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    /**
     * Sorts the specified range of the specified array of objects according
     * to the order induced by the specified comparator.  The range to be
     * sorted extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be sorted is empty.)  All elements in the range must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the range).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * <p>Implementation note: This implementation is a stable, adaptive,
     * iterative mergesort that requires far fewer than n lg(n) comparisons
     * when the input array is partially sorted, while offering the
     * performance of a traditional mergesort when the input array is
     * randomly ordered.  If the input array is nearly sorted, the
     * implementation requires approximately n comparisons.  Temporary
     * storage requirements vary from a small constant for nearly sorted
     * input arrays to n/2 object references for randomly ordered input
     * arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     *
     * @param a         the array to be sorted
     * @param fromIndex the index of the first element (inclusive) to be
     *                  sorted
     * @param toIndex   the index of the last element (exclusive) to be sorted
     * @param c         the comparator to determine the order of the array.  A
     *                  {@code null} value indicates that the elements'
     *                  {@linkplain Comparable natural ordering} should be used.
     * @throws java.lang.ClassCastException             if the array contains elements that are not
     *                                                  <i>mutually comparable</i> using the specified comparator.
     * @throws java.lang.IllegalArgumentException       if {@code fromIndex > toIndex} or
     *                                                  (optional) if the comparator is found to violate the
     *                                                  {@link java.util.Comparator} contract
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *                                                  {@code toIndex > a.length}
     */
    public static void sort(double[] a, int fromIndex, int toIndex,
                            Comparator<? super Double> c) {
        if (c == null) {
            Arrays.sort(a, fromIndex, toIndex);
        } else {
            rangeCheck(a.length, fromIndex, toIndex);
            DoubleTimSort.sort(a, fromIndex, toIndex, c, null, 0, 0);
        }
    }


//    public <T> T[] distinct(T[] originalArray) {
//        return (T[]) Arrays.stream(originalArray).distinct().toArray();
//    }
//
//    public long[] distinct(long[] originalArray) {
//        return Arrays.stream(originalArray).distinct().toArray();
//    }
//
//    public int[] distinct(int[] originalArray) {
//        return Arrays.stream(originalArray).distinct().toArray();
//    }
//
//    public short[] distinct(short[] originalArray) {
//        return Arrays.stream(originalArray).distinct().toArray();
//    }
//
//    public char[] distinct(char[] originalArray) {
//        Arrays.asList()
//        return Arrays.stream(originalArray).distinct().toArray();
//    }

}
