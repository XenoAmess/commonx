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

package com.xenoamess.commons.primitive.collections.lists.linked_lists;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.collections.FloatCollection;
import com.xenoamess.commons.primitive.collections.lists.AbstractFloatSequentialList;
import com.xenoamess.commons.primitive.collections.lists.FloatList;
import com.xenoamess.commons.primitive.collections.queues.FloatDeque;
import com.xenoamess.commons.primitive.functions.FloatConsumer;
import com.xenoamess.commons.primitive.iterators.FloatIterator;
import com.xenoamess.commons.primitive.iterators.FloatListIterator;
import com.xenoamess.commons.primitive.iterators.FloatSpliterator;
import com.xenoamess.commons.primitive.iterators.FloatSpliterators;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Doubly-linked list implementation of the {@code List} and {@code Deque}
 * interfaces.  Implements all optional list operations, and permits all
 * elements (including {@code null}).
 *
 * <p>All of the operations perform as could be expected for a doubly-linked
 * list.  Operations that index into the list will traverse the list from
 * the beginning or the end, whichever is closer to the specified index.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a linked list concurrently, and at least
 * one of the threads modifies the list structurally, it <i>must</i> be
 * synchronized externally.  (A structural modification is any operation
 * that adds or deletes one or more elements; merely setting the value of
 * an element is not a structural modification.)  This is typically
 * accomplished by synchronizing on some object that naturally
 * encapsulates the list.
 * <p>
 * If no such object exists, the list should be "wrapped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the list:<pre>
 *   List list = Collections.synchronizedList(new FloatLinkedList(...));</pre>
 *
 * <p>The iterators returned by this class's {@code iterator} and
 * {@code listIterator} methods are <i>fail-fast</i>: if the list is
 * structurally modified at any time after the iterator is created, in
 * any way except through the Iterator's own {@code remove} or
 * {@code add} methods, the iterator will throw a {@link
 * ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than
 * risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw {@code ConcurrentModificationException} on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:   <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @see List
 * @see ArrayList
 * @since 1.2
 */

public class FloatLinkedList
        extends AbstractFloatSequentialList
        implements FloatList, FloatDeque, Cloneable, java.io.Serializable {
    transient int size = 0;

    /**
     * Pointer to first node.
     */
    transient FloatNode first;

    /**
     * Pointer to last node.
     */
    transient FloatNode last;

    /*
    void dataStructureInvariants() {
        assert (size == 0)
            ? (first == null && last == null)
            : (first.prev == null && last.next == null);
    }
    */

    /**
     * Constructs an empty list.
     */
    public FloatLinkedList() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public FloatLinkedList(Collection<? extends Float> c) {
        this();
        addAll(c);
    }

    /**
     * Links e as first element.
     */
    final void linkFirst(Float e) {
        this.linkFirstPrimitive(e);
    }

    /**
     * Links e as first element.
     */
    final void linkFirst(float e) {
        this.linkFirstPrimitive(e);
    }

    /**
     * Links e as first element.
     */
    void linkFirstPrimitive(float e) {
        final FloatNode f = first;
        final FloatNode newNode = new FloatNode(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Links e as last element.
     */
    final void linkLast(Float e) {
        this.linkLastPrimitive(e);
    }

    /**
     * Links e as last element.
     */
    final void linkLast(float e) {
        this.linkLastPrimitive(e);
    }

    /**
     * Links e as last element.
     */
    void linkLastPrimitive(float e) {
        final FloatNode l = last;
        final FloatNode newNode = new FloatNode(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    final void linkBefore(Float e, FloatNode succ) {
        this.linkBeforePrimitive(e, succ);
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    final void linkBefore(float e, FloatNode succ) {
        this.linkBeforePrimitive(e, succ);
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    void linkBeforePrimitive(float e, FloatNode succ) {
        // assert succ != null;
        final FloatNode pred = succ.prev;
        final FloatNode newNode = new FloatNode(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Unlinks non-null first node f.
     */
    final Float unlinkFirst(FloatNode f) {
        return this.unlinkFirstPrimitive(f);
    }

    /**
     * Unlinks non-null first node f.
     */
    float unlinkFirstPrimitive(FloatNode f) {
        // assert f == first && f != null;
        final float element = f.item;
        final FloatNode next = f.next;
        f.item = Primitive.FLOAT_DEFAULT;
        f.next = null; // help GC
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null last node l.
     */
    final Float unlinkLast(FloatNode l) {
        return unlinkLastPrimitive(l);
    }

    /**
     * Unlinks non-null last node l.
     */
    float unlinkLastPrimitive(FloatNode l) {
        // assert l == last && l != null;
        final float element = l.item;
        final FloatNode prev = l.prev;
        l.item = Primitive.FLOAT_DEFAULT;
        l.prev = null; // help GC
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null node x.
     */
    final Float unlink(FloatNode x) {
        return this.unlinkPrimitive(x);
    }

    /**
     * Unlinks non-null node x.
     */
    float unlinkPrimitive(FloatNode x) {
        // assert x != null;
        final float element = x.item;
        final FloatNode next = x.next;
        final FloatNode prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = Primitive.FLOAT_DEFAULT;
        size--;
        modCount++;
        return element;
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public final Float getFirst() {
        return getFirstPrimitive();
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public float getFirstPrimitive() {
        final FloatNode f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public final Float getLast() {
        return this.getLastPrimitive();
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public float getLastPrimitive() {
        final FloatNode l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public final Float removeFirst() {
        return this.removeFirstPrimitive();
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public float removeFirstPrimitive() {
        final FloatNode f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirstPrimitive(f);
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public final Float removeLast() {
        return this.removeLastPrimitive();
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public float removeLastPrimitive() {
        final FloatNode l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unlinkLastPrimitive(l);
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    @Override
    public final void addFirst(Float e) {
        this.addFirstPrimitive(e);
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    @Override
    public final void addFirst(float e) {
        this.addFirstPrimitive(e);
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    @Override
    public void addFirstPrimitive(float e) {
        linkFirstPrimitive(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    @Override
    public final void addLast(Float e) {
        this.addLastPrimitive(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    @Override
    public final void addLast(float e) {
        this.addLastPrimitive(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    @Override
    public void addLastPrimitive(float e) {
        linkLastPrimitive(e);
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    @Override
    public boolean containsPrimitive(float o) {
        return indexOfPrimitive(o) >= 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #addLast}.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean addPrimitive(float e) {
        linkLastPrimitive(e);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    @Override
    public boolean removeByContentPrimitive(float o) {
        for (FloatNode x = first; x != null; x = x.next) {
            if (o == x.item) {
                unlinkPrimitive(x);
                return true;
            }
        }
        return false;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator.  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in
     * progress.  (Note that this will occur if the specified collection is
     * this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends Float> c) {
        return addAll(size, c);
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element
     *              from the specified collection
     * @param c     collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException      if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection<? extends Float> c) {
        checkPositionIndex(index);

        if (c instanceof FloatCollection) {
            float[] a = ((FloatCollection) c).toArrayPrimitive();
            int numNew = a.length;
            if (numNew == 0) {
                return false;
            }

            FloatNode pred, succ;
            if (index == size) {
                succ = null;
                pred = last;
            } else {
                succ = node(index);
                pred = succ.prev;
            }

            for (float o : a) {
                FloatNode newNode = new FloatNode(pred, o, null);
                if (pred == null) {
                    first = newNode;
                } else {
                    pred.next = newNode;
                }
                pred = newNode;
            }

            if (succ == null) {
                last = pred;
            } else {
                pred.next = succ;
                succ.prev = pred;
            }

            size += numNew;
            modCount++;
            return true;
        } else {
            Object[] a = c.toArray();
            int numNew = a.length;
            if (numNew == 0) {
                return false;
            }

            FloatNode pred, succ;
            if (index == size) {
                succ = null;
                pred = last;
            } else {
                succ = node(index);
                pred = succ.prev;
            }

            for (Object o : a) {
                @SuppressWarnings("unchecked") Float e = (Float) o;
                FloatNode newNode = new FloatNode(pred, e, null);
                if (pred == null) {
                    first = newNode;
                } else {
                    pred.next = newNode;
                }
                pred = newNode;
            }

            if (succ == null) {
                last = pred;
            } else {
                pred.next = succ;
                succ.prev = pred;
            }

            size += numNew;
            modCount++;
            return true;
        }
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (FloatNode x = first; x != null; ) {
            FloatNode next = x.next;
            x.item = Primitive.FLOAT_DEFAULT;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }


    // Positional Access Operations

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public float getPrimitive(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public float setPrimitive(int index, float element) {
        checkElementIndex(index);
        FloatNode x = node(index);
        float oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void addPrimitive(int index, float element) {
        checkPositionIndex(index);

        if (index == size) {
            linkLastPrimitive(element);
        } else {
            linkBeforePrimitive(element, node(index));
        }
    }

    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public float removePrimitive(int index) {
        checkElementIndex(index);
        return unlinkPrimitive(node(index));
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     */
    FloatNode node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            FloatNode x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            FloatNode x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    // Search Operations

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOfPrimitive(float o) {
        int index = 0;
        for (FloatNode x = first; x != null; x = x.next) {
            if (o == x.item) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOfPrimitive(float o) {
        int index = size;
        for (FloatNode x = last; x != null; x = x.prev) {
            index--;
            if (o == x.item) {
                return index;
            }
        }
        return -1;
    }

    // Queue operations.

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list, or {@code 0} if this list is empty
     * @since 1.5
     */
    @Override
    public final Float peek() {
        return FloatDeque.super.peek();
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list, or {@code 0} if this list is empty
     * @since 1.5
     */
    @Override
    public float peekPrimitive() {
        final FloatNode f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    @Override
    public final Float element() {
        return FloatDeque.super.element();
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    @Override
    public float elementPrimitive() {
        return getFirstPrimitive();
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list, or {@code 0} if this list is empty
     * @since 1.5
     */
    @Override
    public final Float poll() {
        return FloatDeque.super.poll();
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list, or {@code 0} if this list is empty
     * @since 1.5
     */
    @Override
    public float pollPrimitive() {
        final FloatNode f = first;
        return (f == null) ? Primitive.FLOAT_DEFAULT : unlinkFirstPrimitive(f);
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    @Override
    public final Float remove() {
        return FloatDeque.super.remove();
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    @Override
    public float removePrimitive() {
        return removeFirstPrimitive();
    }

    /**
     * Adds the specified element as the tail (last element) of this list.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Queue#offer})
     * @since 1.5
     */
    @Override
    public final boolean offer(Float e) {
        return FloatDeque.super.offer(e);
    }

    /**
     * Adds the specified element as the tail (last element) of this list.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Queue#offer})
     * @since 1.5
     */
    @Override
    public final boolean offer(float e) {
        return FloatDeque.super.offer(e);
    }


    /**
     * Adds the specified element as the tail (last element) of this list.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Queue#offer})
     * @since 1.5
     */
    @Override
    public boolean offerPrimitive(float e) {
        return addPrimitive(e);
    }

    // Deque operations

    /**
     * Inserts the specified element at the front of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    @Override
    public boolean offerFirst(Float e) {
        return FloatDeque.super.offerFirst(e);
    }

    /**
     * Inserts the specified element at the front of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    @Override
    public boolean offerFirst(float e) {
        return FloatDeque.super.offerFirst(e);
    }

    /**
     * Inserts the specified element at the front of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    @Override
    public boolean offerFirstPrimitive(float e) {
        addFirstPrimitive(e);
        return true;
    }

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @since 1.6
     */
    @Override
    public final boolean offerLast(Float e) {
        return FloatDeque.super.offerLast(e);
    }

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @since 1.6
     */
    @Override
    public final boolean offerLast(float e) {
        return FloatDeque.super.offerLast(e);
    }

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @since 1.6
     */
    @Override
    public boolean offerLastPrimitive(float e) {
        addLastPrimitive(e);
        return true;
    }

    /**
     * Retrieves, but does not remove, the first element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the first element of this list, or {@code 0}
     * if this list is empty
     * @since 1.6
     */
    @Override
    public final Float peekFirst() {
        return FloatDeque.super.peekFirst();
    }

    /**
     * Retrieves, but does not remove, the first element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the first element of this list, or {@code 0}
     * if this list is empty
     * @since 1.6
     */
    @Override
    public float peekFirstPrimitive() {
        final FloatNode f = first;
        return (f == null) ? Primitive.FLOAT_DEFAULT : f.item;
    }

    /**
     * Retrieves, but does not remove, the last element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the last element of this list, or {@code 0}
     * if this list is empty
     * @since 1.6
     */
    @Override
    public final Float peekLast() {
        return FloatDeque.super.peekLast();
    }

    /**
     * Retrieves, but does not remove, the last element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the last element of this list, or {@code 0}
     * if this list is empty
     * @since 1.6
     */
    @Override
    public float peekLastPrimitive() {
        final FloatNode l = last;
        return (l == null) ? Primitive.FLOAT_DEFAULT : l.item;
    }

    /**
     * Retrieves and removes the first element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the first element of this list, or {@code 0} if
     * this list is empty
     * @since 1.6
     */
    @Override
    public final Float pollFirst() {
        return FloatDeque.super.pollFirst();
    }

    /**
     * Retrieves and removes the first element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the first element of this list, or {@code 0} if
     * this list is empty
     * @since 1.6
     */
    @Override
    public float pollFirstPrimitive() {
        final FloatNode f = first;
        return (f == null) ? Primitive.FLOAT_DEFAULT : unlinkFirst(f);
    }

    /**
     * Retrieves and removes the last element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the last element of this list, or {@code 0} if
     * this list is empty
     * @since 1.6
     */
    @Override
    public final Float pollLast() {
        return FloatDeque.super.pollLast();
    }

    /**
     * Retrieves and removes the last element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the last element of this list, or {@code 0} if
     * this list is empty
     * @since 1.6
     */
    @Override
    public float pollLastPrimitive() {
        final FloatNode l = last;
        return (l == null) ? Primitive.FLOAT_DEFAULT : unlinkLast(l);
    }

    /**
     * Pushes an element onto the stack represented by this list.  In other
     * words, inserts the element at the front of this list.
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @since 1.6
     */
    @Override
    public final void push(Float e) {
        FloatDeque.super.push(e);
    }

    /**
     * Pushes an element onto the stack represented by this list.  In other
     * words, inserts the element at the front of this list.
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @since 1.6
     */
    @Override
    public final void push(float e) {
        FloatDeque.super.push(e);
    }

    /**
     * Pushes an element onto the stack represented by this list.  In other
     * words, inserts the element at the front of this list.
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @since 1.6
     */
    @Override
    public void pushPrimitive(float e) {
        addFirstPrimitive(e);
    }

    /**
     * Pops an element from the stack represented by this list.  In other
     * words, removes and returns the first element of this list.
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this list (which is the top
     * of the stack represented by this list)
     * @throws NoSuchElementException if this list is empty
     * @since 1.6
     */
    @Override
    public final Float pop() {
        return FloatDeque.super.pop();
    }

    /**
     * Pops an element from the stack represented by this list.  In other
     * words, removes and returns the first element of this list.
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this list (which is the top
     * of the stack represented by this list)
     * @throws NoSuchElementException if this list is empty
     * @since 1.6
     */
    @Override
    public float popPrimitive() {
        return removeFirstPrimitive();
    }

    /**
     * Removes the first occurrence of the specified element in this
     * list (when traversing the list from head to tail).  If the list
     * does not contain the element, it is unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if the list contained the specified element
     * @since 1.6
     */
    @Override
    public final boolean removeFirstOccurrence(Object o) {
        return FloatDeque.super.removeFirstOccurrence(o);
    }

    /**
     * Removes the first occurrence of the specified element in this
     * list (when traversing the list from head to tail).  If the list
     * does not contain the element, it is unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if the list contained the specified element
     * @since 1.6
     */
    @Override
    public final boolean removeFirstOccurrence(float o) {
        return FloatDeque.super.removeFirstOccurrence(o);
    }

    /**
     * Removes the first occurrence of the specified element in this
     * list (when traversing the list from head to tail).  If the list
     * does not contain the element, it is unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if the list contained the specified element
     * @since 1.6
     */
    @Override
    public boolean removeFirstOccurrencePrimitive(float o) {
        return removeByContentPrimitive(o);
    }

    /**
     * Removes the last occurrence of the specified element in this
     * list (when traversing the list from head to tail).  If the list
     * does not contain the element, it is unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if the list contained the specified element
     * @since 1.6
     */
    @Override
    public final boolean removeLastOccurrence(Object o) {
        return FloatDeque.super.removeLastOccurrence(o);
    }

    /**
     * Removes the last occurrence of the specified element in this
     * list (when traversing the list from head to tail).  If the list
     * does not contain the element, it is unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if the list contained the specified element
     * @since 1.6
     */
    @Override
    public final boolean removeLastOccurrence(float o) {
        return FloatDeque.super.removeLastOccurrence(o);
    }

    /**
     * Removes the last occurrence of the specified element in this
     * list (when traversing the list from head to tail).  If the list
     * does not contain the element, it is unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if the list contained the specified element
     * @since 1.6
     */
    @Override
    public boolean removeLastOccurrencePrimitive(float o) {
        for (FloatNode x = last; x != null; x = x.prev) {
            if (o == x.item) {
                unlinkPrimitive(x);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list-iterator of the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * Obeys the general contract of {@code List.listIterator(int)}.<p>
     * <p>
     * The list-iterator is <i>fail-fast</i>: if the list is structurally
     * modified at any time after the Iterator is created, in any way except
     * through the list-iterator's own {@code remove} or {@code add}
     * methods, the list-iterator will throw a
     * {@code ConcurrentModificationException}.  Thus, in the face of
     * concurrent modification, the iterator fails quickly and cleanly, rather
     * than risking arbitrary, non-deterministic behavior at an undetermined
     * time in the future.
     *
     * @param index index of the first element to be returned from the
     *              list-iterator (by a call to {@code next})
     * @return a ListIterator of the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see List#listIterator(int)
     */
    @Override
    public FloatListIterator listIterator(int index) {
        checkPositionIndex(index);
        return new FloatListItr(index);
    }

    private class FloatListItr implements FloatListIterator {
        private FloatNode lastReturned;
        private FloatNode next;
        private int nextIndex;
        private int expectedModCount = modCount;

        FloatListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public float nextPrimitive() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public float previousPrimitive() {
            checkForComodification();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            checkForComodification();
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            FloatNode lastNext = lastReturned.next;
            unlinkPrimitive(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
            expectedModCount++;
        }

        @Override
        public void setPrimitive(float e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            checkForComodification();
            lastReturned.item = e;
        }

        @Override
        public void addPrimitive(float e) {
            checkForComodification();
            lastReturned = null;
            if (next == null) {
                linkLastPrimitive(e);
            } else {
                linkBeforePrimitive(e, next);
            }
            nextIndex++;
            expectedModCount++;
        }

        @Override
        public void forEachRemaining(Consumer<? super Float> action) {
            Objects.requireNonNull(action);

            if (action instanceof FloatConsumer) {
                while (modCount == expectedModCount && nextIndex < size) {
                    action.accept(next.item);
                    lastReturned = next;
                    next = next.next;
                    nextIndex++;
                }
                checkForComodification();
            } else {
                FloatConsumer actionFloatConsumer = (FloatConsumer) action;
                while (modCount == expectedModCount && nextIndex < size) {
                    actionFloatConsumer.acceptPrimitive(next.item);
                    lastReturned = next;
                    next = next.next;
                    nextIndex++;
                }
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private static class FloatNode {
        float item;
        FloatNode next;
        FloatNode prev;

        FloatNode(FloatNode prev, float element, FloatNode next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * @since 1.6
     */
    @Override
    public FloatIterator descendingIterator() {
        return new FloatDescendingIterator();
    }

    /**
     * Adapter to provide descending iterators via ListItr.previous
     */
    private class FloatDescendingIterator implements FloatIterator {
        private final FloatListItr itr = new FloatListItr(size());

        @Override
        public boolean hasNext() {
            return itr.hasPrevious();
        }

        @Override
        public float nextPrimitive() {
            return itr.previousPrimitive();
        }

        @Override
        public void remove() {
            itr.remove();
        }
    }

    @SuppressWarnings("unchecked")
    private FloatLinkedList superClone() {
        try {
            return (FloatLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     * Returns a shallow copy of this {@code FloatLinkedList}. (The elements
     * themselves are not cloned.)
     *
     * @return a shallow copy of this {@code FloatLinkedList} instance
     */
    @Override
    public Object clone() {
        FloatLinkedList clone = superClone();

        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initialize clone with our elements
        for (FloatNode x = first; x != null; x = x.next) {
            clone.addPrimitive(x.item);
        }

        return clone;
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list
     * in proper sequence
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (FloatNode x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list
     * in proper sequence
     */
    @Override
    public float[] toArrayPrimitive() {
        float[] result = new float[size];
        int i = 0;
        for (FloatNode x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     * <p>
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (FloatNode x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     * <p>
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public float[] toArrayPrimitive(float[] a) {
        if (a.length < size) {
            a = new float[size];
        }
        int i = 0;
        float[] result = a;
        for (FloatNode x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }

        if (a.length > size) {
            a[size] = Primitive.FLOAT_DEFAULT;
        }

        return a;
    }

    /**
     * Saves the state of this {@code FloatLinkedList} instance to a stream
     * (that is, serializes it).
     *
     * @serialData The size of the list (the number of elements it
     * contains) is emitted (int), followed by all of its
     * elements (each an Object) in the proper order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out size
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (FloatNode x = first; x != null; x = x.next) {
            s.writeFloat(x.item);
        }
    }

    /**
     * Reconstitutes this {@code FloatLinkedList} instance from a stream
     * (that is, deserializes it).
     */
    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Read in size
        int size = s.readInt();

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++) {
            linkLastPrimitive(s.readFloat());
        }
    }

    /**
     * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
     * list.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
     * {@link Spliterator#ORDERED}.  Overriding implementations should document
     * the reporting of additional characteristic values.
     *
     * @return a {@code Spliterator} over the elements in this list
     * @implNote The {@code Spliterator} additionally reports {@link Spliterator#SUBSIZED}
     * and implements {@code trySplit} to permit limited parallelism..
     * @since 1.8
     */
    @Override
    public FloatSpliterator spliterator() {
        return new FloatLLSpliterator(this, -1, 0);
    }

    /**
     * A customized variant of Spliterators.IteratorSpliterator
     */
    static final class FloatLLSpliterator implements FloatSpliterator {
        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        final FloatLinkedList list; // null OK unless traversed
        FloatNode current;      // current node; null until initialized
        int est;              // size estimate; -1 until first needed
        int expectedModCount; // initialized when est set
        int batch;            // batch size for splits

        FloatLLSpliterator(FloatLinkedList list, int est, int expectedModCount) {
            this.list = list;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getEst() {
            int s; // force initialization
            final FloatLinkedList lst;
            if ((s = est) < 0) {
                if ((lst = list) == null) {
                    s = est = 0;
                } else {
                    expectedModCount = lst.modCount;
                    current = lst.first;
                    s = est = lst.size;
                }
            }
            return s;
        }

        @Override
        public long estimateSize() {
            return (long) getEst();
        }

        @Override
        public FloatSpliterator trySplit() {
            FloatNode p;
            int s = getEst();
            if (s > 1 && (p = current) != null) {
                int n = batch + BATCH_UNIT;
                if (n > s) {
                    n = s;
                }
                if (n > MAX_BATCH) {
                    n = MAX_BATCH;
                }
                float[] a = new float[n];
                int j = 0;
                do {
                    a[j++] = p.item;
                } while ((p = p.next) != null && j < n);
                current = p;
                batch = j;
                est = s - j;
                return FloatSpliterators.spliterator(a, 0, j, FloatSpliterator.ORDERED);
            }
            return null;
        }

        @Override
        public void forEachRemaining(Consumer<? super Float> action) {
            FloatNode p;
            int n;
            if (action == null) {
                throw new NullPointerException();
            }

            if (action instanceof FloatConsumer) {
                FloatConsumer actionFloatConsumer = (FloatConsumer) action;
                if ((n = getEst()) > 0 && (p = current) != null) {
                    current = null;
                    est = 0;
                    do {
                        float e = p.item;
                        p = p.next;
                        actionFloatConsumer.acceptPrimitive(e);
                    } while (p != null && --n > 0);
                }
                if (list.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            } else {
                if ((n = getEst()) > 0 && (p = current) != null) {
                    current = null;
                    est = 0;
                    do {
                        Float e = p.item;
                        p = p.next;
                        action.accept(e);
                    } while (p != null && --n > 0);
                }
                if (list.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super Float> action) {
            FloatNode p;
            if (action == null) {
                throw new NullPointerException();
            }
            if (action instanceof FloatConsumer) {
                FloatConsumer actionFloatConsumer = (FloatConsumer) action;
                if (getEst() > 0 && (p = current) != null) {
                    --est;
                    float e = p.item;
                    current = p.next;
                    actionFloatConsumer.acceptPrimitive(e);
                    if (list.modCount != expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                    return true;
                }
                return false;
            } else {
                if (getEst() > 0 && (p = current) != null) {
                    --est;
                    Float e = p.item;
                    current = p.next;
                    action.accept(e);
                    if (list.modCount != expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                    return true;
                }
                return false;
            }

        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }
}
