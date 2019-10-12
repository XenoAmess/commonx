/*
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

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file:
 *
 * Written by Doug Lea and Josh Bloch with assistance from members of
 * JCP JSR-166 Expert Group and released to the public domain, as explained
 * at http://creativecommons.org/publicdomain/zero/1.0/
 */

package com.xenoamess.commons.primitive.collections.queues;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.collections.lists.LongList;
import com.xenoamess.commons.primitive.iterators.LongIterator;

import java.util.Collection;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A linear collection that supports element insertion and removal at
 * both ends.  The name <i>deque</i> is short for "long ended queue"
 * and is usually pronounced "deck".  Most {@code Deque}
 * implementations place no fixed limits on the number of elements
 * they may contain, but this interface supports capacity-restricted
 * deques as well as those with no fixed size limit.
 *
 * This interface defines methods to access the elements at both
 * ends of the deque.  Methods are provided to insert, remove, and
 * examine the element.  Each of these methods exists in two forms:
 * one throws an exception if the operation fails, the other returns a
 * special value (either {@code null} or {@code false}, depending on
 * the operation).  The latter form of the insert operation is
 * designed specifically for use with capacity-restricted
 * {@code Deque} implementations; in most implementations, insert
 * operations cannot fail.
 *
 * The twelve methods described above are summarized in the
 * following table:
 *
 * <table class="striped">
 * <caption>Summary of Deque methods</caption>
 * <thead>
 * <tr>
 * <td rowspan="2"></td>
 * <th scope="col" colspan="2"> First Element (Head)</th>
 * <th scope="col" colspan="2"> Last Element (Tail)</th>
 * </tr>
 * <tr>
 * <th scope="col" style="font-weight:normal; font-style:italic">Throws exception</th>
 * <th scope="col" style="font-weight:normal; font-style:italic">Special value</th>
 * <th scope="col" style="font-weight:normal; font-style:italic">Throws exception</th>
 * <th scope="col" style="font-weight:normal; font-style:italic">Special value</th>
 * </tr>
 * </thead>
 * <tbody>
 * <tr>
 * <th scope="row">Insert</th>
 * <td>{@link #addFirst(Long) addFirst(e)}</td>
 * <td>{@link #offerFirst(Long) offerFirst(e)}</td>
 * <td>{@link #addLast(Long) addLast(e)}</td>
 * <td>{@link #offerLast(Long) offerLast(e)}</td>
 * </tr>
 * <tr>
 * <th scope="row">Remove</th>
 * <td>{@link #removeFirst() removeFirst()}</td>
 * <td>{@link #pollFirst() pollFirst()}</td>
 * <td>{@link #removeLast() removeLast()}</td>
 * <td>{@link #pollLast() pollLast()}</td>
 * </tr>
 * <tr>
 * <th scope="row">Examine</th>
 * <td>{@link #getFirst() getFirst()}</td>
 * <td>{@link #peekFirst() peekFirst()}</td>
 * <td>{@link #getLast() getLast()}</td>
 * <td>{@link #peekLast() peekLast()}</td>
 * </tr>
 * </tbody>
 * </table>
 *
 * This interface extends the {@link LongQueue} interface.  When a deque is
 * used as a queue, FIFO (First-In-First-Out) behavior results.  Elements are
 * added at the end of the deque and removed from the beginning.  The methods
 * inherited from the {@code Queue} interface are precisely equivalent to
 * {@code Deque} methods as indicated in the following table:
 *
 * <table class="striped">
 * <caption>Comparison of Queue and Deque methods</caption>
 * <thead>
 * <tr>
 * <th scope="col"> {@code Queue} Method</th>
 * <th scope="col"> Equivalent {@code Deque} Method</th>
 * </tr>
 * </thead>
 * <tbody>
 * <tr>
 * <th scope="row">{@link #add(Long) add(e)}</th>
 * <td>{@link #addLast(Long) addLast(e)}</td>
 * </tr>
 * <tr>
 * <th scope="row">{@link #offer(Long) offer(e)}</th>
 * <td>{@link #offerLast(Long) offerLast(e)}</td>
 * </tr>
 * <tr>
 * <th scope="row">{@link #remove() remove()}</th>
 * <td>{@link #removeFirst() removeFirst()}</td>
 * </tr>
 * <tr>
 * <th scope="row">{@link #poll() poll()}</th>
 * <td>{@link #pollFirst() pollFirst()}</td>
 * </tr>
 * <tr>
 * <th scope="row">{@link #element() element()}</th>
 * <td>{@link #getFirst() getFirst()}</td>
 * </tr>
 * <tr>
 * <th scope="row">{@link #peek() peek()}</th>
 * <td>{@link #peekFirst() peekFirst()}</td>
 * </tr>
 * </tbody>
 * </table>
 *
 * Deques can also be used as LIFO (Last-In-First-Out) stacks.  This
 * interface should be used in preference to the legacy {@link Stack} class.
 * When a deque is used as a stack, elements are pushed and popped from the
 * beginning of the deque.  Stack methods are equivalent to {@code Deque}
 * methods as indicated in the table below:
 *
 * <table class="striped">
 * <caption>Comparison of Stack and Deque methods</caption>
 * <thead>
 * <tr>
 * <th scope="col"> Stack Method</th>
 * <th scope="col"> Equivalent {@code Deque} Method</th>
 * </tr>
 * </thead>
 * <tbody>
 * <tr>
 * <th scope="row">{@link #push(Long) push(e)}</th>
 * <td>{@link #addFirst(Long) addFirst(e)}</td>
 * </tr>
 * <tr>
 * <th scope="row">{@link #pop() pop()}</th>
 * <td>{@link #removeFirst() removeFirst()}</td>
 * </tr>
 * <tr>
 * <th scope="row">{@link #peek() peek()}</th>
 * <td>{@link #getFirst() getFirst()}</td>
 * </tr>
 * </tbody>
 * </table>
 *
 * Note that the {@link #peek peek} method works equally well when
 * a deque is used as a queue or a stack; in either case, elements are
 * drawn from the beginning of the deque.
 *
 * This interface provides two methods to remove interior
 * elements, {@link #removeFirstOccurrence removeFirstOccurrence} and
 * {@link #removeLastOccurrence removeLastOccurrence}.
 *
 * Unlike the {@link LongList} interface, this interface does not
 * provide support for indexed access to elements.
 *
 * While {@code Deque} implementations are not strictly required
 * to prohibit the insertion of null elements, they are strongly
 * encouraged to do so.  Users of any {@code Deque} implementations
 * that do allow null elements are strongly encouraged <i>not</i> to
 * take advantage of the ability to insert nulls.  This is so because
 * {@code null} is used as a special return value by various methods
 * to indicate that the deque is empty.
 *
 * <p>{@code Deque} implementations generally do not define
 * element-based versions of the {@code equals} and {@code hashCode}
 * methods, but instead inherit the identity-based versions from class
 * {@code Object}.
 *
 * This interface is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Doug Lea
 * @author Josh Bloch
 * @since 1.6
 */
public interface LongDeque extends Deque<Long>, LongQueue, Primitive {
    /**
     * Inserts the specified element at the front of this deque if it is
     * possible to do so immediately without violating capacity restrictions,
     * throwing an {@code IllegalStateException} if no space is currently
     * available.  When using a capacity-restricted deque, it is generally
     * preferable to use method {@link #offerFirst}.
     *
     * @param e the element to add
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     */
    @Override
    default void addFirst(Long e) {
        this.addFirstPrimitive(e);
    }

    /**
     * Primitive replacement of addFirst(Long e)
     *
     * @param e the element to add
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #addFirst(Long e)
     */
    default void addFirst(long e) {
        this.addFirstPrimitive(e);
    }

    /**
     * Primitive replacement of addFirst(Long e)
     *
     * @param e the element to add
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #addFirst(Long e)
     */
    void addFirstPrimitive(long e);

    /**
     * Inserts the specified element at the end of this deque if it is
     * possible to do so immediately without violating capacity restrictions,
     * throwing an {@code IllegalStateException} if no space is currently
     * available.  When using a capacity-restricted deque, it is generally
     * preferable to use method {@link #offerLast}.
     *
     * This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     */
    @Override
    default void addLast(Long e) {
        this.addLastPrimitive(e);
    }

    /**
     * Primitive replacement of addLast(Long e)
     *
     * @param e the element to add
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #addLast(Long e)
     */
    default void addLast(long e) {
        this.addLastPrimitive(e);
    }

    /**
     * Primitive replacement of addLast(Long e)
     *
     * @param e the element to add
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #addLast(Long e)
     */
    void addLastPrimitive(long e);

    /**
     * Inserts the specified element at the front of this deque unless it would
     * violate capacity restrictions.  When using a capacity-restricted deque,
     * this method is generally preferable to the {@link #addFirst} method,
     * which can fail to insert an element only by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     */
    @Override
    default boolean offerFirst(Long e) {
        return this.offerFirstPrimitive(e);
    }

    /**
     * Primitive replacement of offerFirst(Long e)
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #offerFirst(Long e)
     */
    default boolean offerFirst(long e) {
        return this.offerFirstPrimitive(e);
    }

    /**
     * Primitive replacement of offerFirst(Long e)
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #offerFirst(Long e)
     */
    boolean offerFirstPrimitive(long e);

    /**
     * Inserts the specified element at the end of this deque unless it would
     * violate capacity restrictions.  When using a capacity-restricted deque,
     * this method is generally preferable to the {@link #addLast} method,
     * which can fail to insert an element only by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     */
    @Override
    default boolean offerLast(Long e) {
        return this.offerLastPrimitive(e);
    }

    /**
     * Primitive replacement of offerLast(Long e)
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #offerLast(Long e)
     */
    default boolean offerLast(long e) {
        return this.offerLastPrimitive(e);
    }

    /**
     * Primitive replacement of offerLast(Long e)
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #offerLast(Long e)
     */
    boolean offerLastPrimitive(long e);

    /**
     * Retrieves and removes the first element of this deque.  This method
     * differs from {@link #pollFirst pollFirst} only in that it throws an
     * exception if this deque is empty.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Long removeFirst() {
        return removeFirstPrimitive();
    }

    /**
     * Primitive replacement of removeFirst()
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     * @see #removeFirst()
     */
    long removeFirstPrimitive();

    /**
     * Retrieves and removes the last element of this deque.  This method
     * differs from {@link #pollLast pollLast} only in that it throws an
     * exception if this deque is empty.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Long removeLast() {
        return removeLastPrimitive();
    }

    /**
     * Primitive replacement of removeLast()
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     * @see #removeLast()
     */
    long removeLastPrimitive();

    /**
     * Retrieves and removes the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    @Override
    default Long pollFirst() {
        return this.pollFirstPrimitive();
    }

    /**
     * Primitive replacement of pollFirst()
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     * @see #pollFirst()
     */
    long pollFirstPrimitive();

    /**
     * Retrieves and removes the last element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     */
    @Override
    default Long pollLast() {
        return this.pollLastPrimitive();
    }

    /**
     * Primitive replacement of pollLast()
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     * @see #pollLast()
     */
    long pollLastPrimitive();

    /**
     * Retrieves, but does not remove, the first element of this deque.
     * <p>
     * This method differs from {@link #peekFirst peekFirst} only in that it
     * throws an exception if this deque is empty.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Long getFirst() {
        return this.getFirstPrimitive();
    }

    /**
     * Primitive replacement of getFirst()
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     * @see #getFirst()
     */
    long getFirstPrimitive();

    /**
     * Retrieves, but does not remove, the last element of this deque.
     * This method differs from {@link #peekLast peekLast} only in that it
     * throws an exception if this deque is empty.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Long getLast() {
        return this.getLastPrimitive();
    }

    /**
     * Primitive replacement of getLast()
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     * @see #getLast()
     */
    long getLastPrimitive();

    /**
     * Retrieves, but does not remove, the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    @Override
    default Long peekFirst() {
        return this.peekFirstPrimitive();
    }

    /**
     * Primitive replacement of peekFirst()
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     * @see #peekFirst()
     */
    long peekFirstPrimitive();

    /**
     * Retrieves, but does not remove, the last element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     */
    @Override
    default Long peekLast() {
        return this.peekLastPrimitive();
    }

    /**
     * Primitive replacement of peekLast()
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     * @see #peekLast()
     */
    long peekLastPrimitive();

    /**
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contain the element, it is unchanged.
     * More formally, removes the first element {@code e} such that
     * {@code Objects.equals(o, e)} (if such an element exists).
     * Returns {@code true} if this deque contained the specified element
     * (or equivalently, if this deque changed as a result of the call).
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException   if the class of the specified element
     *                              is incompatible with this deque
     * @throws NullPointerException if the specified element is null and this
     *                              deque does not permit null elements
     */
    @Override
    default boolean removeFirstOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Long)) {
            return false;
        }
        return this.removeFirstOccurrencePrimitive((Long) o);
    }

    /**
     * Primitive replacement of removeFirstOccurrence(Object o)
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException   if the class of the specified element
     *                              is incompatible with this deque
     * @throws NullPointerException if the specified element is null and this
     *                              deque does not permit null elements
     * @see #removeFirstOccurrence(Object o)
     */
    default boolean removeFirstOccurrence(long o) {
        return this.removeFirstOccurrencePrimitive(o);
    }

    /**
     * Primitive replacement of removeFirstOccurrence(Object o)
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException   if the class of the specified element
     *                              is incompatible with this deque
     * @throws NullPointerException if the specified element is null and this
     *                              deque does not permit null elements
     * @see #removeFirstOccurrence(Object o)
     */
    boolean removeFirstOccurrencePrimitive(long o);

    /**
     * Removes the last occurrence of the specified element from this deque.
     * If the deque does not contain the element, it is unchanged.
     * More formally, removes the last element {@code e} such that
     * {@code Objects.equals(o, e)} (if such an element exists).
     * Returns {@code true} if this deque contained the specified element
     * (or equivalently, if this deque changed as a result of the call).
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException   if the class of the specified element
     *                              is incompatible with this deque
     * @throws NullPointerException if the specified element is null and this
     *                              deque does not permit null elements
     */
    @Override
    default boolean removeLastOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Long)) {
            return false;
        }
        return removeLastOccurrencePrimitive((Long) o);
    }

    /**
     * Primitive replacement of removeFirstOccurrence(Object o)
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException   if the class of the specified element
     *                              is incompatible with this deque
     * @throws NullPointerException if the specified element is null and this
     *                              deque does not permit null elements
     * @see #removeLastOccurrencePrimitive(long o)
     */
    default boolean removeLastOccurrence(long o) {
        return removeLastOccurrencePrimitive(o);
    }

    /**
     * Primitive replacement of removeFirstOccurrence(Object o)
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException   if the class of the specified element
     *                              is incompatible with this deque
     * @throws NullPointerException if the specified element is null and this
     *                              deque does not permit null elements
     * @see #removeLastOccurrencePrimitive(long o)
     */
    boolean removeLastOccurrencePrimitive(long o);

    // *** Queue methods ***

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, at the tail of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an
     * {@code IllegalStateException} if no space is currently available.
     * When using a capacity-restricted deque, it is generally preferable to
     * use {@link #offer(Long) offer}.
     *
     * This method is equivalent to {@link #addLast}.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     */
    @Override
    default boolean add(Long e) {
        return LongQueue.super.add(e);
    }

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, at the tail of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and {@code false} if no space is currently
     * available.  When using a capacity-restricted deque, this method is
     * generally preferable to the {@link #add} method, which can fail to
     * insert an element only by throwing an exception.
     *
     * This method is equivalent to {@link #offerLast}.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     */
    @Override
    default boolean offer(Long e) {
        return LongQueue.super.offer(e);
    }

    /**
     * Retrieves and removes the head of the queue represented by this deque
     * (in other words, the first element of this deque).
     * This method differs from {@link #poll() poll()} only in that it
     * throws an exception if this deque is empty.
     *
     * This method is equivalent to {@link #removeFirst()}.
     *
     * @return the head of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Long remove() {
        return LongQueue.super.remove();
    }

    /**
     * Retrieves and removes the head of the queue represented by this deque
     * (in other words, the first element of this deque), or returns
     * {@code null} if this deque is empty.
     *
     * This method is equivalent to {@link #pollFirst()}.
     *
     * @return the first element of this deque, or {@code null} if
     * this deque is empty
     */
    @Override
    default Long poll() {
        return LongQueue.super.poll();
    }

    /**
     * Retrieves, but does not remove, the head of the queue represented by
     * this deque (in other words, the first element of this deque).
     * This method differs from {@link #peek peek} only in that it throws an
     * exception if this deque is empty.
     *
     * This method is equivalent to {@link #getFirst()}.
     *
     * @return the head of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Long element() {
        return LongQueue.super.element();
    }

    /**
     * Retrieves, but does not remove, the head of the queue represented by
     * this deque (in other words, the first element of this deque), or
     * returns {@code null} if this deque is empty.
     *
     * This method is equivalent to {@link #peekFirst()}.
     *
     * @return the head of the queue represented by this deque, or
     * {@code null} if this deque is empty
     */
    @Override
    default Long peek() {
        return LongQueue.super.peek();
    }

    /**
     * Adds all of the elements in the specified collection at the end
     * of this deque, as if by calling {@link #addLast} on each one,
     * in the order that they are returned by the collection's iterator.
     *
     * When using a capacity-restricted deque, it is generally preferable
     * to call {@link #offer(Long) offer} separately on each element.
     *
     * An exception encountered while trying to add an element may result
     * in only some of the elements having been successfully added when
     * the associated exception is thrown.
     *
     * @param c the elements to be inserted into this deque
     * @return {@code true} if this deque changed as a result of the call
     * @throws IllegalStateException    if not all the elements can be added at
     *                                  this time due to insertion restrictions
     * @throws ClassCastException       if the class of an element of the specified
     *                                  collection prevents it from being added to this deque
     * @throws NullPointerException     if the specified collection contains a
     *                                  null element and this deque does not permit null elements,
     *                                  or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     *                                  specified collection prevents it from being added to this deque
     */
    @Override
    boolean addAll(Collection<? extends Long> c);

    // *** Stack methods ***

    /**
     * Pushes an element onto the stack represented by this deque (in other
     * words, at the head of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, throwing an
     * {@code IllegalStateException} if no space is currently available.
     *
     * This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     */
    @Override
    default void push(Long e) {
        this.pushPrimitive(e);
    }

    /**
     * Primitive replacement of push(Long e)
     *
     * @param e the element to push
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #push(Long e)
     */
    default void push(long e) {
        this.pushPrimitive(e);
    }

    /**
     * Primitive replacement of push(Long e)
     *
     * @param e the element to push
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this deque
     * @throws NullPointerException     if the specified element is null and this
     *                                  deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this deque
     * @see #push(Long e)
     */
    void pushPrimitive(long e);

    /**
     * Pops an element from the stack represented by this deque.  In other
     * words, removes and returns the first element of this deque.
     *
     * This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this deque (which is the top
     * of the stack represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Long pop() {
        return this.popPrimitive();
    }

    /**
     * Primitive replacement of pop();
     *
     * @return the element at the front of this deque (which is the top
     * of the stack represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     * @see #pop()
     */
    long popPrimitive();


    // *** Collection methods ***

    /**
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contain the element, it is unchanged.
     * More formally, removes the first element {@code e} such that
     * {@code Objects.equals(o, e)} (if such an element exists).
     * Returns {@code true} if this deque contained the specified element
     * (or equivalently, if this deque changed as a result of the call).
     *
     * This method is equivalent to {@link #removeFirstOccurrence(Object)}.
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException   if the class of the specified element
     *                              is incompatible with this deque
     * @throws NullPointerException if the specified element is null and this
     *                              deque does not permit null elements
     */
    @Override
    default boolean remove(Object o) {
        return LongQueue.super.remove(o);
    }

    /**
     * Returns {@code true} if this deque contains the specified element.
     * More formally, returns {@code true} if and only if this deque contains
     * at least one element {@code e} such that {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this deque is to be tested
     * @return {@code true} if this deque contains the specified element
     * @throws ClassCastException   if the class of the specified element
     *                              is incompatible with this deque
     * @throws NullPointerException if the specified element is null and this
     *                              deque does not permit null elements
     */
    @Override
    default boolean contains(Object o) {
        return LongQueue.super.contains(o);
    }

    /**
     * Returns the number of elements in this deque.
     *
     * @return the number of elements in this deque
     */
    @Override
    int size();

    /**
     * Returns an iterator over the elements in this deque in proper sequence.
     * The elements will be returned in order from first (head) to last (tail).
     *
     * @return an iterator over the elements in this deque in proper sequence
     */
    @Override
    LongIterator iterator();

    /**
     * Returns an iterator over the elements in this deque in reverse
     * sequential order.  The elements will be returned in order from
     * last (tail) to first (head).
     *
     * @return an iterator over the elements in this deque in reverse
     * sequence
     */
    @Override
    LongIterator descendingIterator();

}
