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
import com.xenoamess.commons.primitive.comparators.BooleanComparator;
import com.xenoamess.commons.primitive.functions.BooleanConsumer;
import java.util.Collection;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * An object for traversing and partitioning elements of a source.  The source
 * of elements covered by a Spliterator could be, for example, an array, a
 * {@link java.util.Collection}, an IO channel, or a generator function.
 *
 * <p>A Spliterator may traverse elements individually ({@link
 * #tryAdvance tryAdvance()}) or sequentially in bulk
 * ({@link #forEachRemaining forEachRemaining()}).
 *
 * <p>A Spliterator may also partition off some of its elements (using
 * {@link #trySplit}) as another Spliterator, to be used in
 * possibly-parallel operations.  Operations using a Spliterator that
 * cannot split, or does so in a highly imbalanced or inefficient
 * manner, are unlikely to benefit from parallelism.  Traversal
 * and splitting exhaust elements; each Spliterator is useful for only a single
 * bulk computation.
 *
 * <p>A Spliterator also reports a set of {@link #characteristics()} of its
 * structure, source, and elements from among {@link #ORDERED},
 * {@link #DISTINCT}, {@link #SORTED}, {@link #SIZED}, {@link #NONNULL},
 * {@link #IMMUTABLE}, {@link #CONCURRENT}, and {@link #SUBSIZED}. These may
 * be employed by Spliterator clients to control, specialize or simplify
 * computation.  For example, a Spliterator for a {@link java.util.Collection} would
 * report {@code SIZED}, a Spliterator for a {@link java.util.Set} would report
 * {@code DISTINCT}, and a Spliterator for a {@link java.util.SortedSet} would also
 * report {@code SORTED}.  Characteristics are reported as a simple unioned bit
 * set.
 * <p>
 * Some characteristics additionally constrain method behavior; for example if
 * {@code ORDERED}, traversal methods must conform to their documented ordering.
 * New characteristics may be defined in the future, so implementors should not
 * assign meanings to unlisted values.
 *
 * <p><a id="binding">A Spliterator that does not report {@code IMMUTABLE} or
 * {@code CONCURRENT} is expected to have a documented policy concerning:
 * when the spliterator <em>binds</em> to the element source; and detection of
 * structural interference of the element source detected after binding.</a>  A
 * <em>late-binding</em> Spliterator binds to the source of elements at the
 * point of first traversal, first split, or first query for estimated size,
 * rather than at the time the Spliterator is created.  A Spliterator that is
 * not <em>late-binding</em> binds to the source of elements at the point of
 * construction or first invocation of any method.  Modifications made to the
 * source prior to binding are reflected when the Spliterator is traversed.
 * After binding a Spliterator should, on a best-effort basis, throw
 * {@link java.util.ConcurrentModificationException} if structural interference is
 * detected.  Spliterators that do this are called <em>fail-fast</em>.  The
 * bulk traversal method ({@link #forEachRemaining forEachRemaining()}) of a
 * Spliterator may optimize traversal and check for structural interference
 * after all elements have been traversed, rather than checking per-element and
 * failing immediately.
 *
 * <p>Spliterators can provide an estimate of the number of remaining elements
 * via the {@link #estimateSize} method.  Ideally, as reflected in characteristic
 * {@link #SIZED}, this value corresponds exactly to the number of elements
 * that would be encountered in a successful traversal.  However, even when not
 * exactly known, an estimated value may still be useful to operations
 * being performed on the source, such as helping to determine whether it is
 * preferable to split further or traverse the remaining elements sequentially.
 *
 * <p>Despite their obvious utility in parallel algorithms, spliterators are not
 * expected to be thread-safe; instead, implementations of parallel algorithms
 * using spliterators should ensure that the spliterator is only used by one
 * thread at a time.  This is generally easy to attain via <em>serial
 * thread-confinement</em>, which often is a natural consequence of typical
 * parallel algorithms that work by recursive decomposition.  A thread calling
 * {@link #trySplit()} may hand over the returned Spliterator to another thread,
 * which in turn may traverse or further split that Spliterator.  The behaviour
 * of splitting and traversal is undefined if two or more threads operate
 * concurrently on the same spliterator.  If the original thread hands a
 * spliterator off to another thread for processing, it is best if that handoff
 * occurs before any elements are consumed with {@link #tryAdvance(Consumer)
 * tryAdvance()}, as certain guarantees (such as the accuracy of
 * {@link #estimateSize()} for {@code SIZED} spliterators) are only valid before
 * traversal has begun.
 *
 * @author XenoAmess
 * @version 0.8.0
 * @apiNote <p>Spliterators, like {@code Iterator}s, are for traversing the elements of
 * a source.  The {@code Spliterator} API was designed to support efficient
 * parallel traversal in addition to sequential traversal, by supporting
 * decomposition as well as single-element iteration.  In addition, the
 * protocol for accessing elements via a Spliterator is designed to impose
 * smaller per-element overhead than {@code Iterator}, and to avoid the inherent
 * race involved in having separate methods for {@code hasNext()} and
 * {@code next()}.
 *
 * <p>For mutable sources, arbitrary and non-deterministic behavior may occur if
 * the source is structurally interfered with (elements added, replaced, or
 * removed) between the time that the Spliterator binds to its data source and
 * the end of traversal.  For example, such interference will produce arbitrary,
 * non-deterministic results when using the {@code java.util.stream} framework.
 *
 * <p>Structural interference of a source can be managed in the following ways
 * (in approximate order of decreasing desirability):
 * <ul>
 * <li>The source cannot be structurally interfered with.
 * <br>For example, an instance of
 * {@link java.util.concurrent.CopyOnWriteArrayList} is an immutable source.
 * A Spliterator created from the source reports a characteristic of
 * {@code IMMUTABLE}.</li>
 * <li>The source manages concurrent modifications.
 * <br>For example, a key set of a {@link java.util.concurrent.ConcurrentHashMap}
 * is a concurrent source.  A Spliterator created from the source reports a
 * characteristic of {@code CONCURRENT}.</li>
 * <li>The mutable source provides a late-binding and fail-fast Spliterator.
 * <br>Late binding narrows the window during which interference can affect
 * the calculation; fail-fast detects, on a best-effort basis, that structural
 * interference has occurred after traversal has commenced and throws
 * {@link java.util.ConcurrentModificationException}.  For example, {@link java.util.ArrayList},
 * and many other non-concurrent {@code Collection} classes in the JDK, provide
 * a late-binding, fail-fast spliterator.</li>
 * <li>The mutable source provides a non-late-binding but fail-fast Spliterator.
 * <br>The source increases the likelihood of throwing
 * {@code ConcurrentModificationException} since the window of potential
 * interference is larger.</li>
 * <li>The mutable source provides a late-binding and non-fail-fast Spliterator.
 * <br>The source risks arbitrary, non-deterministic behavior after traversal
 * has commenced since interference is not detected.
 * </li>
 * <li>The mutable source provides a non-late-binding and non-fail-fast
 * Spliterator.
 * <br>The source increases the risk of arbitrary, non-deterministic behavior
 * since non-detected interference may occur after construction.
 * </li>
 * </ul>
 *
 * <p><b>Example.</b> Here is a class (not a very useful one, except
 * for illustration) that maintains an array in which the actual data
 * are held in even locations, and unrelated tag data are held in odd
 * locations. Its Spliterator ignores the tags.
 *
 * <pre> {@code
 * class TaggedArray<T> {
 *   private final Object[] elements; // immutable after construction
 *   TaggedArray(T[] data, Object[] tags) {
 *     int size = data.length;
 *     if (tags.length != size) throw new IllegalArgumentException();
 *     this.elements = new Object[2 * size];
 *     for (int i = 0, j = 0; i < size; ++i) {
 *       elements[j++] = data[i];
 *       elements[j++] = tags[i];
 *     }
 *   }
 *
 *   public Spliterator<T> spliterator() {
 *     return new TaggedArraySpliterator<>(elements, 0, elements.length);
 *   }
 *
 *   static class TaggedArraySpliterator<T> implements Spliterator<T> {
 *     private final Object[] array;
 *     private int origin; // current index, advanced on split or traversal
 *     private final int fence; // one past the greatest index
 *
 *     TaggedArraySpliterator(Object[] array, int origin, int fence) {
 *       this.array = array; this.origin = origin; this.fence = fence;
 *     }
 *
 *     public void forEachRemaining(Consumer<? super T> action) {
 *       for (; origin < fence; origin += 2)
 *         action.accept((T) array[origin]);
 *     }
 *
 *     public boolean tryAdvance(Consumer<? super T> action) {
 *       if (origin < fence) {
 *         action.accept((T) array[origin]);
 *         origin += 2;
 *         return true;
 *       }
 *       else // cannot advance
 *         return false;
 *     }
 *
 *     public Spliterator<T> trySplit() {
 *       int lo = origin; // divide range in half
 *       int mid = ((lo + fence) >>> 1) & ~1; // force midpoint to be even
 *       if (lo < mid) { // split out left half
 *         origin = mid; // reset this Spliterator's origin
 *         return new TaggedArraySpliterator<>(array, lo, mid);
 *       }
 *       else       // too small to split
 *         return null;
 *     }
 *
 *     public long estimateSize() {
 *       return (long)((fence - origin) / 2);
 *     }
 *
 *     public int characteristics() {
 *       return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
 *     }
 *   }
 * }}</pre>
 *
 * <p>As an example how a parallel computation framework, such as the
 * {@code java.util.stream} package, would use Spliterator in a parallel
 * computation, here is one way to implement an associated parallel forEach,
 * that illustrates the primary usage idiom of splitting off subtasks until
 * the estimated amount of work is small enough to perform
 * sequentially. Here we assume that the order of processing across
 * subtasks doesn't matter; different (forked) tasks may further split
 * and process elements concurrently in undetermined order.  This
 * example uses a {@link java.util.concurrent.CountedCompleter};
 * similar usages apply to other parallel task constructions.
 *
 * <pre>{@code
 * static <T> void parEach(TaggedArray<T> a, Consumer<T> action) {
 *   Spliterator<T> s = a.spliterator();
 *   long targetBatchSize = s.estimateSize() / (ForkJoinPool.getCommonPoolParallelism() * 8);
 *   new ParEach(null, s, action, targetBatchSize).invoke();
 * }
 *
 * static class ParEach<T> extends CountedCompleter<Void> {
 *   final Spliterator<T> spliterator;
 *   final Consumer<T> action;
 *   final long targetBatchSize;
 *
 *   ParEach(ParEach<T> parent, Spliterator<T> spliterator,
 *           Consumer<T> action, long targetBatchSize) {
 *     super(parent);
 *     this.spliterator = spliterator; this.action = action;
 *     this.targetBatchSize = targetBatchSize;
 *   }
 *
 *   public void compute() {
 *     Spliterator<T> sub;
 *     while (spliterator.estimateSize() > targetBatchSize &&
 *            (sub = spliterator.trySplit()) != null) {
 *       addToPendingCount(1);
 *       new ParEach<>(this, sub, action, targetBatchSize).fork();
 *     }
 *     spliterator.forEachRemaining(action);
 *     propagateCompletion();
 *   }
 * }}</pre>
 * @implNote If the boolean system property {@code org.openjdk.java.util.stream.tripwire}
 * is set to {@code true} then diagnostic warnings are reported if boxing of
 * primitive values occur when operating on primitive subtype specializations.
 * @see Collection
 * @see Spliterator
 * @since 1.8
 */
public interface BooleanSpliterator extends Spliterator<Boolean>, Primitive {
    /**
     * {@inheritDoc}
     * <p>
     * If this spliterator can be partitioned, returns a Spliterator
     * covering elements, that will, upon return from this method, not
     * be covered by this Spliterator.
     *
     * <p>If this Spliterator is {@link #ORDERED}, the returned Spliterator
     * must cover a strict prefix of the elements.
     *
     * <p>Unless this Spliterator covers an infinite number of elements,
     * repeated calls to {@code trySplit()} must eventually return {@code null}.
     * Upon non-null return:
     * <ul>
     * <li>the value reported for {@code estimateSize()} before splitting,
     * must, after splitting, be greater than or equal to {@code estimateSize()}
     * for this and the returned Spliterator; and</li>
     * <li>if this Spliterator is {@code SUBSIZED}, then {@code estimateSize()}
     * for this spliterator before splitting must be equal to the sum of
     * {@code estimateSize()} for this and the returned Spliterator after
     * splitting.</li>
     * </ul>
     *
     * <p>This method may return {@code null} for any reason,
     * including emptiness, inability to split after traversal has
     * commenced, data structure constraints, and efficiency
     * considerations.
     *
     * @apiNote An ideal {@code trySplit} method efficiently (without
     * traversal) divides its elements exactly in half, allowing
     * balanced parallel computation.  Many departures from this ideal
     * remain highly effective; for example, only approximately
     * splitting an approximately balanced tree, or for a tree in
     * which leaf nodes may contain either one or two elements,
     * failing to further split these nodes.  However, large
     * deviations in balance and/or overly inefficient {@code
     * trySplit} mechanics typically result in poor parallel
     * performance.
     */
    @Override
    BooleanSpliterator trySplit();

    /**
     * {@inheritDoc}
     * <p>
     * If this Spliterator's source is {@link #SORTED} by a {@link Comparator},
     * returns that {@code Comparator}. If the source is {@code SORTED} in
     * {@linkplain Comparable natural order}, returns {@code null}.  Otherwise,
     * if the source is not {@code SORTED}, throws {@link IllegalStateException}.
     *
     * @implSpec The default implementation always throws {@link java.lang.IllegalStateException}.
     */
    @Override
    default BooleanComparator getComparator() {
        throw new IllegalStateException();
    }

    /**
     * A Spliterator specialized for primitive values.
     *
     * @param <T_CONS>   the type of primitive consumer.  The type must be a
     *                   primitive specialization of {@link java.util.function.Consumer} for
     *                   {@code T}, such as {@link java.util.function.IntConsumer} for
     *                   {@code Integer}.
     * @param <T_SPLITR> the type of primitive Spliterator.  The type must be
     *                   a primitive specialization of Spliterator for {@code T}, such as
     *                   {@link Spliterator.OfInt} for {@code Integer}.
     * @since 1.8
     */
    interface BooleanOfPrimitive<T_CONS, T_SPLITR extends BooleanSpliterator.BooleanOfPrimitive<T_CONS, T_SPLITR>>
            extends BooleanSpliterator, Spliterator.OfPrimitive<Boolean, T_CONS, T_SPLITR> {
        @Override
        T_SPLITR trySplit();

        /**
         * If a remaining element exists, performs the given action on it,
         * returning {@code true}; else returns {@code false}.  If this
         * Spliterator is {@link #ORDERED} the action is performed on the
         * next element in encounter order.  Exceptions thrown by the
         * action are relayed to the caller.
         *
         * @param action The action
         * @return {@code false} if no remaining elements existed
         * upon entry to this method, else {@code true}.
         * @throws NullPointerException if the specified action is null
         */
        @Override
        @SuppressWarnings("overloads")
        boolean tryAdvance(T_CONS action);

        /**
         * Performs the given action for each remaining element, sequentially in
         * the current thread, until all elements have been processed or the
         * action throws an exception.  If this Spliterator is {@link #ORDERED},
         * actions are performed in encounter order.  Exceptions thrown by the
         * action are relayed to the caller.
         *
         * @param action The action
         * @throws NullPointerException if the specified action is null
         * @implSpec The default implementation repeatedly invokes {@link #tryAdvance}
         * until it returns {@code false}.  It should be overridden whenever
         * possible.
         */
        @Override
        @SuppressWarnings("overloads")
        default void forEachRemaining(T_CONS action) {
            do {
            } while (tryAdvance(action));
        }
    }

    /**
     * A Spliterator specialized for {@code boolean} values.
     *
     * @since 1.8
     */
    interface BooleanOfBoolean
            extends BooleanSpliterator.BooleanOfPrimitive
            <BooleanConsumer, BooleanSpliterator.BooleanOfBoolean> {

        @Override
        BooleanOfBoolean trySplit();

        @Override
        boolean tryAdvance(BooleanConsumer action);

        @Override
        default void forEachRemaining(BooleanConsumer action) {
            do {
            } while (tryAdvance(action));
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec If the action is an instance of {@code BooleanConsumer} then it is
         * cast to {@code BooleanConsumer} and passed to
         * {@link #tryAdvance(BooleanConsumer)}; otherwise
         * the action is adapted to an instance of {@code BooleanConsumer}, by
         * boxing the argument of {@code BooleanConsumer}, and then passed to
         * {@link #tryAdvance(BooleanConsumer)}.
         */
        @Override
        default boolean tryAdvance(Consumer<? super Boolean> action) {
            if (action instanceof BooleanConsumer) {
                return tryAdvance((BooleanConsumer) action);
            } else {
//                if (Tripwire.ENABLED) {
//                    Tripwire.trip(getClass(),
//                            "{0} calling Spliterator.OfBoolean.tryAdvance((BooleanConsumer) action::accept)");
//                }
                return tryAdvance(action::accept);
            }
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec If the action is an instance of {@code BooleanConsumer} then it is
         * cast to {@code BooleanConsumer} and passed to
         * {@link #forEachRemaining(BooleanConsumer)};
         * otherwise the action is adapted to an instance of
         * {@code BooleanConsumer}, by boxing the argument of
         * {@code BooleanConsumer}, and then passed to
         * {@link #forEachRemaining(BooleanConsumer)}.
         */
        @Override
        default void forEachRemaining(Consumer<? super Boolean> action) {
            if (action instanceof BooleanConsumer) {
                forEachRemaining((BooleanConsumer) action);
            } else {
//                if (Tripwire.ENABLED) {
//                    Tripwire.trip(getClass(),
//                            "{0} calling Spliterator.OfBoolean.forEachRemaining((BooleanConsumer) action::accept)");
//                }
                forEachRemaining(action::accept);
            }
        }
    }
}
