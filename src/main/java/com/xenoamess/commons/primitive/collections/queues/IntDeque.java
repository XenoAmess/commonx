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
import com.xenoamess.commons.primitive.collections.lists.IntList;
import com.xenoamess.commons.primitive.iterators.IntIterator;

import java.util.Collection;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A linear collection that supports element insertion and removal at
 * both ends.  The name <i>deque</i> is short for "int ended queue"
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
 * <td>{@link #addFirst(Integer) addFirst(e)}</td>
 * <td>{@link #offerFirst(Integer) offerFirst(e)}</td>
 * <td>{@link #addLast(Integer) addLast(e)}</td>
 * <td>{@link #offerLast(Integer) offerLast(e)}</td>
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
 * This interface extends the {@link IntQueue} interface.  When a deque is
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
 * <th scope="row">{@link #add(Integer) add(e)}</th>
 * <td>{@link #addLast(Integer) addLast(e)}</td>
 * </tr>
 * <tr>
 * <th scope="row">{@link #offer(Integer) offer(e)}</th>
 * <td>{@link #offerLast(Integer) offerLast(e)}</td>
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
 * <th scope="row">{@link #push(Integer) push(e)}</th>
 * <td>{@link #addFirst(Integer) addFirst(e)}</td>
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
 * Unlike the {@link IntList} interface, this interface does not
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
public interface IntDeque extends Deque<Integer>, IntQueue, Primitive {
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
    default void addFirst(Integer e) {
        this.addFirstPrimitive(e);
    }

    /**
     * Primitive replacement of addFirst(Integer e)
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
     * @see #addFirst(Integer e)
     */
    default void addFirst(int e) {
        this.addFirstPrimitive(e);
    }

    /**
     * Primitive replacement of addFirst(Integer e)
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
     * @see #addFirst(Integer e)
     */
    void addFirstPrimitive(int e);

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
    default void addLast(Integer e) {
        this.addLastPrimitive(e);
    }

    /**
     * Primitive replacement of addLast(Integer e)
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
     * @see #addLast(Integer e)
     */
    default void addLast(int e) {
        this.addLastPrimitive(e);
    }

    /**
     * Primitive replacement of addLast(Integer e)
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
     * @see #addLast(Integer e)
     */
    void addLastPrimitive(int e);

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
    default boolean offerFirst(Integer e) {
        return this.offerFirstPrimitive(e);
    }

    /**
     * Primitive replacement of offerFirst(Integer e)
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
     * @see #offerFirst(Integer e)
     */
    default boolean offerFirst(int e) {
        return this.offerFirstPrimitive(e);
    }

    /**
     * Primitive replacement of offerFirst(Integer e)
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
     * @see #offerFirst(Integer e)
     */
    boolean offerFirstPrimitive(int e);

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
    default boolean offerLast(Integer e) {
        return this.offerLastPrimitive(e);
    }

    /**
     * Primitive replacement of offerLast(Integer e)
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
     * @see #offerLast(Integer e)
     */
    default boolean offerLast(int e) {
        return this.offerLastPrimitive(e);
    }

    /**
     * Primitive replacement of offerLast(Integer e)
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
     * @see #offerLast(Integer e)
     */
    boolean offerLastPrimitive(int e);

    /**
     * Retrieves and removes the first element of this deque.  This method
     * differs from {@link #pollFirst pollFirst} only in that it throws an
     * exception if this deque is empty.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Integer removeFirst() {
        return removeFirstPrimitive();
    }

    /**
     * Primitive replacement of removeFirst()
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     * @see #removeFirst()
     */
    int removeFirstPrimitive();

    /**
     * Retrieves and removes the last element of this deque.  This method
     * differs from {@link #pollLast pollLast} only in that it throws an
     * exception if this deque is empty.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Integer removeLast() {
        return removeLastPrimitive();
    }

    /**
     * Primitive replacement of removeLast()
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     * @see #removeLast()
     */
    int removeLastPrimitive();

    /**
     * Retrieves and removes the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    @Override
    default Integer pollFirst() {
        return this.pollFirstPrimitive();
    }

    /**
     * Primitive replacement of pollFirst()
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     * @see #pollFirst()
     */
    int pollFirstPrimitive();

    /**
     * Retrieves and removes the last element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     */
    @Override
    default Integer pollLast() {
        return this.pollLastPrimitive();
    }

    /**
     * Primitive replacement of pollLast()
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     * @see #pollLast()
     */
    int pollLastPrimitive();

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
    default Integer getFirst() {
        return this.getFirstPrimitive();
    }

    /**
     * Primitive replacement of getFirst()
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     * @see #getFirst()
     */
    int getFirstPrimitive();

    /**
     * Retrieves, but does not remove, the last element of this deque.
     * This method differs from {@link #peekLast peekLast} only in that it
     * throws an exception if this deque is empty.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    default Integer getLast() {
        return this.getLastPrimitive();
    }

    /**
     * Primitive replacement of getLast()
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     * @see #getLast()
     */
    int getLastPrimitive();

    /**
     * Retrieves, but does not remove, the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    @Override
    default Integer peekFirst() {
        return this.peekFirstPrimitive();
    }

    /**
     * Primitive replacement of peekFirst()
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     * @see #peekFirst()
     */
    int peekFirstPrimitive();

    /**
     * Retrieves, but does not remove, the last element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     */
    @Override
    default Integer peekLast() {
        return this.peekLastPrimitive();
    }

    /**
     * Primitive replacement of peekLast()
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     * @see #peekLast()
     */
    int peekLastPrimitive();

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
        if (!(o instanceof Integer)) {
            return false;
        }
        return this.removeFirstOccurrencePrimitive((Integer) o);
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
    default boolean removeFirstOccurrence(int o) {
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
    boolean removeFirstOccurrencePrimitive(int o);

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
        if (!(o instanceof Integer)) {
            return false;
        }
        return removeLastOccurrencePrimitive((Integer) o);
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
     * @see #removeLastOccurrencePrimitive(int o)
     */
    default boolean removeLastOccurrence(int o) {
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
     * @see #removeLastOccurrencePrimitive(int o)
     */
    boolean removeLastOccurrencePrimitive(int o);

    // *** Queue methods ***

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, at the tail of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an
     * {@code IllegalStateException} if no space is currently available.
     * When using a capacity-restricted deque, it is generally preferable to
     * use {@link #offer(Integer) offer}.
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
    default boolean add(Integer e) {
        return IntQueue.super.add(e);
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
    default boolean offer(Integer e) {
        return IntQueue.super.offer(e);
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
    default Integer remove() {
        return IntQueue.super.remove();
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
    default Integer poll() {
        return IntQueue.super.poll();
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
    default Integer element() {
        return IntQueue.super.element();
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
    default Integer peek() {
        return IntQueue.super.peek();
    }

    /**
     * Adds all of the elements in the specified collection at the end
     * of this deque, as if by calling {@link #addLast} on each one,
     * in the order that they are returned by the collection's iterator.
     *
     * When using a capacity-restricted deque, it is generally preferable
     * to call {@link #offer(Integer) offer} separately on each element.
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
    boolean addAll(Collection<? extends Integer> c);

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
    default void push(Integer e) {
        this.pushPrimitive(e);
    }

    /**
     * Primitive replacement of push(Integer e)
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
     * @see #push(Integer e)
     */
    default void push(int e) {
        this.pushPrimitive(e);
    }

    /**
     * Primitive replacement of push(Integer e)
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
     * @see #push(Integer e)
     */
    void pushPrimitive(int e);

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
    default Integer pop() {
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
    int popPrimitive();


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
        return IntQueue.super.remove(o);
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
        return IntQueue.super.contains(o);
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
    IntIterator iterator();

    /**
     * Returns an iterator over the elements in this deque in reverse
     * sequential order.  The elements will be returned in order from
     * last (tail) to first (head).
     *
     * @return an iterator over the elements in this deque in reverse
     * sequence
     */
    @Override
    IntIterator descendingIterator();

}
