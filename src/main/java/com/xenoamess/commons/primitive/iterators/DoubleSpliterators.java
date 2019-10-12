/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
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

package com.xenoamess.commons.primitive.iterators;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.collections.DoubleCollection;
import com.xenoamess.commons.primitive.functions.DoubleConsumer;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Static classes and methods for operating on or creating instances of
 * {@link com.xenoamess.commons.primitive.iterators.DoubleSpliterators}
 *
 * @author XenoAmess
 * @version 0.8.0
 * @see DoubleSpliterators
 * @see java.util.Spliterators
 * @since 1.8
 */
public final class DoubleSpliterators implements Primitive {

    // Suppresses default constructor, ensuring non-instantiability.
    private DoubleSpliterators() {
    }

    /**
     * Creates an empty {@code DoubleSpliterator.DoubleOfDouble}
     *
     * The empty spliterator reports {@link java.util.Spliterator#SIZED} and
     * {@link java.util.Spliterator#SUBSIZED}.  Calls to
     * {@link java.util.Spliterator#trySplit()} always return {@code null}.
     *
     * @return An empty spliterator
     */
    public static DoubleSpliterator.DoubleOfDouble emptyDoubleSpliterator() {
        return EMPTY_DOUBLE_SPLITERATOR;
    }

    private static final DoubleSpliterator.DoubleOfDouble EMPTY_DOUBLE_SPLITERATOR =
            new EmptySpliterator.DoubleOfDouble();

    // Array-based spliterators

//    /**
//     * Creates a {@code Spliterator} covering the elements of a given array,
//     * using a customized set of spliterator characteristics.
//     *
//     * This method is provided as an implementation convenience for
//     * Spliterators which store portions of their elements in arrays, and need
//     * fine control over Spliterator characteristics.  Most other situations in
//     * which a Spliterator for an array is needed should use
//     * {@link Arrays#spliterator(Object[])}.
//     *
//     * The returned spliterator always reports the characteristics
//     * {@code SIZED} and {@code SUBSIZED}.  The caller may provide additional
//     * characteristics for the spliterator to report; it is common to
//     * additionally specify {@code IMMUTABLE} and {@code ORDERED}.
//     *
//     * @param array                     The array, assumed to be unmodified during use
//     * @param additionalCharacteristics Additional spliterator characteristics
//     *                                  of this spliterator's source or elements beyond {@code SIZED} and
//     *                                  {@code SUBSIZED} which are always reported
//     * @return A spliterator for an array
//     * @throws NullPointerException if the given array is {@code null}
//     * @see Arrays#spliterator(Object[])
//     */
//    public static DoubleSpliterator spliterator(Object[] array,
//                                                int additionalCharacteristics) {
//        return new DoubleArraySpliterator(Objects.requireNonNull(array),
//                additionalCharacteristics);
//    }

//    /**
//     * Creates a {@code Spliterator} covering a range of elements of a given
//     * array, using a customized set of spliterator characteristics.
//     *
//     * This method is provided as an implementation convenience for
//     * Spliterators which store portions of their elements in arrays, and need
//     * fine control over Spliterator characteristics.  Most other situations in
//     * which a Spliterator for an array is needed should use
//     * {@link Arrays#spliterator(Object[])}.
//     *
//     * The returned spliterator always reports the characteristics
//     * {@code SIZED} and {@code SUBSIZED}.  The caller may provide additional
//     * characteristics for the spliterator to report; it is common to
//     * additionally specify {@code IMMUTABLE} and {@code ORDERED}.
//     *
//     * @param array                     The array, assumed to be unmodified during use
//     * @param fromIndex                 The least index (inclusive) to cover
//     * @param toIndex                   One past the greatest index to cover
//     * @param additionalCharacteristics Additional spliterator characteristics
//     *                                  of this spliterator's source or elements beyond {@code SIZED} and
//     *                                  {@code SUBSIZED} which are always reported
//     * @return A spliterator for an array
//     * @throws NullPointerException           if the given array is {@code null}
//     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex} is negative,
//     *                                        {@code toIndex} is less than {@code fromIndex}, or
//     *                                        {@code toIndex} is greater than the array size
//     * @see Arrays#spliterator(Object[], int, int)
//     */
//    public static DoubleSpliterator spliterator(Object[] array, int fromIndex, int toIndex,
//                                                int additionalCharacteristics) {
//        checkFromToBounds(Objects.requireNonNull(array).length, fromIndex, toIndex);
//        return new DoubleArraySpliterator(array, fromIndex, toIndex, additionalCharacteristics);
//    }


    /**
     * Creates a {@code DoubleSpliterator.DoubleOfDouble} covering the elements of a given array,
     * using a customized set of spliterator characteristics.
     *
     * This method is provided as an implementation convenience for
     * Spliterators which store portions of their elements in arrays, and need
     * fine control over Spliterator characteristics.
     *
     * The returned spliterator always reports the characteristics
     * {@code SIZED} and {@code SUBSIZED}.  The caller may provide additional
     * characteristics for the spliterator to report; it is common to
     * additionally specify {@code IMMUTABLE} and {@code ORDERED}.
     *
     * @param array                     The array, assumed to be unmodified during use
     * @param additionalCharacteristics Additional spliterator characteristics
     *                                  of this spliterator's source or elements beyond {@code SIZED} and
     *                                  {@code SUBSIZED} which are always reported
     * @return A spliterator for an array
     * @throws java.lang.NullPointerException if the given array is {@code null}
     */
    public static DoubleSpliterator.DoubleOfDouble spliterator(double[] array,
                                                               int additionalCharacteristics) {
        return new DoubleArraySpliterator(Objects.requireNonNull(array), additionalCharacteristics);
    }

    /**
     * Creates a {@code DoubleSpliterator.DoubleOfDouble} covering a range of elements of a
     * given array, using a customized set of spliterator characteristics.
     *
     * This method is provided as an implementation convenience for
     * Spliterators which store portions of their elements in arrays, and need
     * fine control over Spliterator characteristics.
     *
     * The returned spliterator always reports the characteristics
     * {@code SIZED} and {@code SUBSIZED}.  The caller may provide additional
     * characteristics for the spliterator to report.  (For example, if it is
     * known the array will not be further modified, specify {@code IMMUTABLE};
     * if the array data is considered to have an encounter order, specify
     * {@code ORDERED}).  The method {@link java.util.Arrays#spliterator(long[], int, int)} can
     * often be used instead, which returns a spliterator that reports
     * {@code SIZED}, {@code SUBSIZED}, {@code IMMUTABLE}, and {@code ORDERED}.
     *
     * @param array                     The array, assumed to be unmodified during use
     * @param fromIndex                 The least index (inclusive) to cover
     * @param toIndex                   One past the greatest index to cover
     * @param additionalCharacteristics Additional spliterator characteristics
     *                                  of this spliterator's source or elements beyond {@code SIZED} and
     *                                  {@code SUBSIZED} which are always reported
     * @return A spliterator for an array
     * @throws java.lang.NullPointerException           if the given array is {@code null}
     * @throws java.lang.ArrayIndexOutOfBoundsException if {@code fromIndex} is negative,
     *                                                  {@code toIndex} is less than {@code fromIndex}, or
     *                                                  {@code toIndex} is greater than the array size
     */
    public static DoubleSpliterator.DoubleOfDouble spliterator(double[] array, int fromIndex, int toIndex,
                                                               int additionalCharacteristics) {
        checkFromToBounds(Objects.requireNonNull(array).length, fromIndex, toIndex);
        return new DoubleArraySpliterator(array, fromIndex, toIndex, additionalCharacteristics);
    }

    /**
     * Validate inclusive start index and exclusive end index against the length
     * of an array.
     *
     * @param arrayLength The length of the array
     * @param origin      The inclusive start index
     * @param fence       The exclusive end index
     * @throws ArrayIndexOutOfBoundsException if the start index is greater than
     *                                        the end index, if the start index is negative, or the end index is
     *                                        greater than the array length
     */
    private static void checkFromToBounds(int arrayLength, int origin, int fence) {
        if (origin > fence) {
            throw new ArrayIndexOutOfBoundsException(
                    "origin(" + origin + ") > fence(" + fence + ")");
        }
        if (origin < 0) {
            throw new ArrayIndexOutOfBoundsException(origin);
        }
        if (fence > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(fence);
        }
    }

    // Iterator-based spliterators

    /**
     * Creates a {@code Spliterator} using the given collection's
     * {@link java.util.Collection#iterator()} as the source of elements, and
     * reporting its {@link java.util.Collection#size()} as its initial size.
     *
     * The spliterator is
     * <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the collection's iterator, and
     * implements {@code trySplit} to permit limited parallelism.
     *
     * @param c               The collection
     * @param characteristics Characteristics of this spliterator's source or
     *                        elements.  The characteristics {@code SIZED} and {@code SUBSIZED}
     *                        are additionally reported unless {@code CONCURRENT} is supplied.
     * @return A spliterator from an iterator
     * @throws java.lang.NullPointerException if the given collection is {@code null}
     */
    public static DoubleSpliterator spliterator(DoubleCollection c,
                                                int characteristics) {
        return new DoubleIteratorSpliterator(Objects.requireNonNull(c),
                characteristics);
    }

//    /**
//     * Creates a {@code Spliterator} using a given {@code Iterator}
//     * as the source of elements, and with a given initially reported size.
//     *
//     * The spliterator is not
//     * <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
//     * the <em>fail-fast</em> properties of the iterator, and implements
//     * {@code trySplit} to permit limited parallelism.
//     *
//     * Traversal of elements should be accomplished through the DoubleSpliterator.
//     * The behaviour of splitting and traversal is undefined if the iterator is
//     * operated on after the spliterator is returned, or the initially reported
//     * size is not equal to the actual number of elements in the source.
//     *
//     * @param <T>             Type of elements
//     * @param iterator        The iterator for the source
//     * @param size            The number of elements in the source, to be reported as
//     *                        initial {@code estimateSize}
//     * @param characteristics Characteristics of this spliterator's source or
//     *                        elements.  The characteristics {@code SIZED} and {@code SUBSIZED}
//     *                        are additionally reported unless {@code CONCURRENT} is supplied.
//     * @return A spliterator from an iterator
//     * @throws NullPointerException if the given iterator is {@code null}
//     */
//    public static <T> DoubleSpliterator spliterator(Iterator<? extends T> iterator,
//                                                    long size,
//                                                    int characteristics) {
//        return new DoubleIteratorSpliterator(Objects.requireNonNull(iterator), size,
//                characteristics);
//    }

//    /**
//     * Creates a {@code Spliterator} using a given {@code Iterator}
//     * as the source of elements, with no initial size estimate.
//     *
//     * The spliterator is not
//     * <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
//     * the <em>fail-fast</em> properties of the iterator, and implements
//     * {@code trySplit} to permit limited parallelism.
//     *
//     * Traversal of elements should be accomplished through the DoubleSpliterator.
//     * The behaviour of splitting and traversal is undefined if the iterator is
//     * operated on after the spliterator is returned.
//     *
//     * @param <T>             Type of elements
//     * @param iterator        The iterator for the source
//     * @param characteristics Characteristics of this spliterator's source
//     *                        or elements ({@code SIZED} and {@code SUBSIZED}, if supplied, are
//     *                        ignored and are not reported.)
//     * @return A spliterator from an iterator
//     * @throws NullPointerException if the given iterator is {@code null}
//     */
//    public static DoubleSpliterator spliteratorUnknownSize(Iterator<? extends Double> iterator,
//                                                           int characteristics) {
//        return new DoubleIteratorSpliterator(Objects.requireNonNull(iterator), characteristics);
//    }


    /**
     * Creates a {@code DoubleSpliterator.DoubleOfDouble} using a given
     * {@code DoubleStream.DoubleIterator} as the source of elements, and with a
     * given initially reported size.
     *
     * The spliterator is not
     * <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements
     * {@code trySplit} to permit limited parallelism.
     *
     * Traversal of elements should be accomplished through the DoubleSpliterator.
     * The behaviour of splitting and traversal is undefined if the iterator is
     * operated on after the spliterator is returned, or the initially reported
     * size is not equal to the actual number of elements in the source.
     *
     * @param iterator        The iterator for the source
     * @param size            The number of elements in the source, to be reported as
     *                        initial {@code estimateSize}
     * @param characteristics Characteristics of this spliterator's source or
     *                        elements.  The characteristics {@code SIZED} and {@code SUBSIZED}
     *                        are additionally reported unless {@code CONCURRENT} is supplied.
     * @return A spliterator from an iterator
     * @throws java.lang.NullPointerException if the given iterator is {@code null}
     */
    public static DoubleSpliterator.DoubleOfDouble spliterator(DoubleIterator iterator,
                                                               long size,
                                                               int characteristics) {
        return new DoubleIteratorSpliterator(Objects.requireNonNull(iterator),
                size, characteristics);
    }

    /**
     * Creates a {@code DoubleSpliterator.DoubleOfDouble} using a given
     * {@code DoubleStream.DoubleIterator} as the source of elements, with no
     * initial size estimate.
     *
     * The spliterator is not
     * <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements
     * {@code trySplit} to permit limited parallelism.
     *
     * Traversal of elements should be accomplished through the DoubleSpliterator.
     * The behaviour of splitting and traversal is undefined if the iterator is
     * operated on after the spliterator is returned.
     *
     * @param iterator        The iterator for the source
     * @param characteristics Characteristics of this spliterator's source
     *                        or elements ({@code SIZED} and {@code SUBSIZED}, if supplied, are
     *                        ignored and are not reported.)
     * @return A spliterator from an iterator
     * @throws java.lang.NullPointerException if the given iterator is {@code null}
     */
    public static DoubleSpliterator.DoubleOfDouble spliteratorUnknownSize(DoubleIterator iterator,
                                                                          int characteristics) {
        return new DoubleIteratorSpliterator(Objects.requireNonNull(iterator), characteristics);
    }

    // Iterators from Spliterators

    /**
     * Creates an {@code Iterator} from a {@code Spliterator}.
     *
     * Traversal of elements should be accomplished through the iterator.
     * The behaviour of traversal is undefined if the spliterator is operated
     * after the iterator is returned.
     *
     * @param doubleSpliterator The spliterator
     * @return An iterator
     * @throws java.lang.NullPointerException if the given spliterator is {@code null}
     */
    public static DoubleIterator iterator(DoubleSpliterator doubleSpliterator) {
        Objects.requireNonNull(doubleSpliterator);
        class DoubleAdapter implements DoubleIterator, DoubleConsumer {
            boolean valueReady = false;
            double nextElement;

            @Override
            public void acceptPrimitive(double t) {
                valueReady = true;
                nextElement = t;
            }

            @Override
            public boolean hasNext() {
                if (!valueReady) {
                    doubleSpliterator.tryAdvance(this);
                }
                return valueReady;
            }

            @Override
            public double nextPrimitive() {
                if (!valueReady && !hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    valueReady = false;
                    return nextElement;
                }
            }
        }

        return new DoubleAdapter();
    }

    /**
     * Creates an {@code DoubleIterator} from a
     * {@code DoubleSpliterator.DoubleOfDouble}.
     *
     * Traversal of elements should be accomplished through the iterator.
     * The behaviour of traversal is undefined if the spliterator is operated
     * after the iterator is returned.
     *
     * @param doubleSpliterator a {@link com.xenoamess.commons.primitive.iterators.DoubleSpliterator.DoubleOfDouble}
     *                          object.
     * @return An iterator
     * @throws java.lang.NullPointerException if the given spliterator is {@code null}
     */
    public static DoubleIterator iterator(DoubleSpliterator.DoubleOfDouble doubleSpliterator) {
        Objects.requireNonNull(doubleSpliterator);
        class Adapter implements DoubleIterator, DoubleConsumer {
            boolean valueReady = false;
            double nextElement;

            @Override
            public void acceptPrimitive(double t) {
                valueReady = true;
                nextElement = t;
            }

            @Override
            public boolean hasNext() {
                if (!valueReady) {
                    doubleSpliterator.tryAdvance(this);
                }
                return valueReady;
            }

            @Override
            public double nextPrimitive() {
                if (!valueReady && !hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    valueReady = false;
                    return nextElement;
                }
            }
        }

        return new Adapter();
    }

    // Implementations

    private abstract static class EmptySpliterator<S extends DoubleSpliterator, C> {

        EmptySpliterator() {
        }

        public S trySplit() {
            return null;
        }

        public boolean tryAdvance(C consumer) {
            Objects.requireNonNull(consumer);
            return false;
        }

        public void forEachRemaining(C consumer) {
            Objects.requireNonNull(consumer);
        }

        public long estimateSize() {
            return 0;
        }

        public int characteristics() {
            return DoubleSpliterator.SIZED | DoubleSpliterator.SUBSIZED;
        }

        private static final class DoubleOfDouble
                extends DoubleSpliterators.EmptySpliterator<DoubleSpliterator.DoubleOfDouble, DoubleConsumer>
                implements DoubleSpliterator.DoubleOfDouble {
            DoubleOfDouble() {
            }
        }
    }

}
