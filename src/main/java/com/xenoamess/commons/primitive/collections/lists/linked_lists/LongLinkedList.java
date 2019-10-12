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
import com.xenoamess.commons.primitive.collections.LongCollection;
import com.xenoamess.commons.primitive.collections.lists.AbstractLongSequentialList;
import com.xenoamess.commons.primitive.collections.lists.LongList;
import com.xenoamess.commons.primitive.collections.queues.LongDeque;
import com.xenoamess.commons.primitive.functions.LongConsumer;
import com.xenoamess.commons.primitive.iterators.LongIterator;
import com.xenoamess.commons.primitive.iterators.LongListIterator;
import com.xenoamess.commons.primitive.iterators.LongSpliterator;
import com.xenoamess.commons.primitive.iterators.LongSpliterators;

import java.util.*;
import java.util.function.Consumer;

/**
 * Doubly-linked list implementation of the {@code List} and {@code Deque}
 * interfaces.  Implements all optional list operations, and permits all
 * elements (including {@code null}).
 *
 * All of the operations perform as could be expected for a doubly-linked
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
 *   List list = Collections.synchronizedList(new LongLinkedList(...));</pre>
 *
 * The iterators returned by this class's {@code iterator} and
 * {@code listIterator} methods are <i>fail-fast</i>: if the list is
 * structurally modified at any time after the iterator is created, in
 * any way except through the Iterator's own {@code remove} or
 * {@code add} methods, the iterator will throw a {@link
 * ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than
 * risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 *
 * Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw {@code ConcurrentModificationException} on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:   <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * This class is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @see List
 * @see ArrayList
 * @since 1.2
 */

public class LongLinkedList
        extends AbstractLongSequentialList
        implements LongList, LongDeque, Cloneable, java.io.Serializable {
    transient int size = 0;

    /**
     * Pointer to first node.
     */
    transient LongNode first;

    /**
     * Pointer to last node.
     */
    transient LongNode last;

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
    public LongLinkedList() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public LongLinkedList(Collection<? extends Long> c) {
        this();
        addAll(c);
    }

    /**
     * Links e as first element.
     */
    final void linkFirst(Long e) {
        this.linkFirstPrimitive(e);
    }

    /**
     * Links e as first element.
     */
    final void linkFirst(long e) {
        this.linkFirstPrimitive(e);
    }

    /**
     * Links e as first element.
     */
    void linkFirstPrimitive(long e) {
        final LongNode f = first;
        final LongNode newNode = new LongNode(null, e, f);
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
    final void linkLast(Long e) {
        this.linkLastPrimitive(e);
    }

    /**
     * Links e as last element.
     */
    final void linkLast(long e) {
        this.linkLastPrimitive(e);
    }

    /**
     * Links e as last element.
     */
    void linkLastPrimitive(long e) {
        final LongNode l = last;
        final LongNode newNode = new LongNode(l, e, null);
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
    final void linkBefore(Long e, LongNode succ) {
        this.linkBeforePrimitive(e, succ);
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    final void linkBefore(long e, LongNode succ) {
        this.linkBeforePrimitive(e, succ);
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    void linkBeforePrimitive(long e, LongNode succ) {
        // assert succ != null;
        final LongNode pred = succ.prev;
        final LongNode newNode = new LongNode(pred, e, succ);
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
    final Long unlinkFirst(LongNode f) {
        return this.unlinkFirstPrimitive(f);
    }

    /**
     * Unlinks non-null first node f.
     */
    long unlinkFirstPrimitive(LongNode f) {
        // assert f == first && f != null;
        final long element = f.item;
        final LongNode next = f.next;
        f.item = Primitive.LONG_DEFAULT;
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
    final Long unlinkLast(LongNode l) {
        return unlinkLastPrimitive(l);
    }

    /**
     * Unlinks non-null last node l.
     */
    long unlinkLastPrimitive(LongNode l) {
        // assert l == last && l != null;
        final long element = l.item;
        final LongNode prev = l.prev;
        l.item = Primitive.LONG_DEFAULT;
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
    final Long unlink(LongNode x) {
        return this.unlinkPrimitive(x);
    }

    /**
     * Unlinks non-null node x.
     */
    long unlinkPrimitive(LongNode x) {
        // assert x != null;
        final long element = x.item;
        final LongNode next = x.next;
        final LongNode prev = x.prev;

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

        x.item = Primitive.LONG_DEFAULT;
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
    public final Long getFirst() {
        return getFirstPrimitive();
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public long getFirstPrimitive() {
        final LongNode f = first;
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
    public final Long getLast() {
        return this.getLastPrimitive();
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public long getLastPrimitive() {
        final LongNode l = last;
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
    public final Long removeFirst() {
        return this.removeFirstPrimitive();
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public long removeFirstPrimitive() {
        final LongNode f = first;
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
    public final Long removeLast() {
        return this.removeLastPrimitive();
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public long removeLastPrimitive() {
        final LongNode l = last;
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
    public final void addFirst(Long e) {
        this.addFirstPrimitive(e);
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    @Override
    public final void addFirst(long e) {
        this.addFirstPrimitive(e);
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    @Override
    public void addFirstPrimitive(long e) {
        linkFirstPrimitive(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    @Override
    public final void addLast(Long e) {
        this.addLastPrimitive(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    @Override
    public final void addLast(long e) {
        this.addLastPrimitive(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    @Override
    public void addLastPrimitive(long e) {
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
    public boolean containsPrimitive(long o) {
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
     * This method is equivalent to {@link #addLast}.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean addPrimitive(long e) {
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
    public boolean removeByContentPrimitive(long o) {
        for (LongNode x = first; x != null; x = x.next) {
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
    public boolean addAll(Collection<? extends Long> c) {
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
    public boolean addAll(int index, Collection<? extends Long> c) {
        checkPositionIndex(index);

        if (c instanceof LongCollection) {
            long[] a = ((LongCollection) c).toArrayPrimitive();
            int numNew = a.length;
            if (numNew == 0) {
                return false;
            }

            LongNode pred, succ;
            if (index == size) {
                succ = null;
                pred = last;
            } else {
                succ = node(index);
                pred = succ.prev;
            }

            for (long o : a) {
                LongNode newNode = new LongNode(pred, o, null);
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

            LongNode pred, succ;
            if (index == size) {
                succ = null;
                pred = last;
            } else {
                succ = node(index);
                pred = succ.prev;
            }

            for (Object o : a) {
                @SuppressWarnings("unchecked") Long e = (Long) o;
                LongNode newNode = new LongNode(pred, e, null);
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
        for (LongNode x = first; x != null; ) {
            LongNode next = x.next;
            x.item = Primitive.LONG_DEFAULT;
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
    public long getPrimitive(int index) {
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
    public long setPrimitive(int index, long element) {
        checkElementIndex(index);
        LongNode x = node(index);
        long oldVal = x.item;
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
    public void addPrimitive(int index, long element) {
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
    public long removePrimitive(int index) {
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
    LongNode node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            LongNode x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            LongNode x = last;
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
    public int indexOfPrimitive(long o) {
        int index = 0;
        for (LongNode x = first; x != null; x = x.next) {
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
    public int lastIndexOfPrimitive(long o) {
        int index = size;
        for (LongNode x = last; x != null; x = x.prev) {
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
    public final Long peek() {
        return LongDeque.super.peek();
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list, or {@code 0} if this list is empty
     * @since 1.5
     */
    @Override
    public long peekPrimitive() {
        final LongNode f = first;
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
    public final Long element() {
        return LongDeque.super.element();
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    @Override
    public long elementPrimitive() {
        return getFirstPrimitive();
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list, or {@code 0} if this list is empty
     * @since 1.5
     */
    @Override
    public final Long poll() {
        return LongDeque.super.poll();
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list, or {@code 0} if this list is empty
     * @since 1.5
     */
    @Override
    public long pollPrimitive() {
        final LongNode f = first;
        return (f == null) ? Primitive.LONG_DEFAULT : unlinkFirstPrimitive(f);
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    @Override
    public final Long remove() {
        return LongDeque.super.remove();
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    @Override
    public long removePrimitive() {
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
    public final boolean offer(Long e) {
        return LongDeque.super.offer(e);
    }

    /**
     * Adds the specified element as the tail (last element) of this list.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Queue#offer})
     * @since 1.5
     */
    @Override
    public final boolean offer(long e) {
        return LongDeque.super.offer(e);
    }


    /**
     * Adds the specified element as the tail (last element) of this list.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Queue#offer})
     * @since 1.5
     */
    @Override
    public boolean offerPrimitive(long e) {
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
    public boolean offerFirst(Long e) {
        return LongDeque.super.offerFirst(e);
    }

    /**
     * Inserts the specified element at the front of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    @Override
    public boolean offerFirst(long e) {
        return LongDeque.super.offerFirst(e);
    }

    /**
     * Inserts the specified element at the front of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    @Override
    public boolean offerFirstPrimitive(long e) {
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
    public final boolean offerLast(Long e) {
        return LongDeque.super.offerLast(e);
    }

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @since 1.6
     */
    @Override
    public final boolean offerLast(long e) {
        return LongDeque.super.offerLast(e);
    }

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @since 1.6
     */
    @Override
    public boolean offerLastPrimitive(long e) {
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
    public final Long peekFirst() {
        return LongDeque.super.peekFirst();
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
    public long peekFirstPrimitive() {
        final LongNode f = first;
        return (f == null) ? Primitive.LONG_DEFAULT : f.item;
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
    public final Long peekLast() {
        return LongDeque.super.peekLast();
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
    public long peekLastPrimitive() {
        final LongNode l = last;
        return (l == null) ? Primitive.LONG_DEFAULT : l.item;
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
    public final Long pollFirst() {
        return LongDeque.super.pollFirst();
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
    public long pollFirstPrimitive() {
        final LongNode f = first;
        return (f == null) ? Primitive.LONG_DEFAULT : unlinkFirst(f);
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
    public final Long pollLast() {
        return LongDeque.super.pollLast();
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
    public long pollLastPrimitive() {
        final LongNode l = last;
        return (l == null) ? Primitive.LONG_DEFAULT : unlinkLast(l);
    }

    /**
     * Pushes an element onto the stack represented by this list.  In other
     * words, inserts the element at the front of this list.
     *
     * This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @since 1.6
     */
    @Override
    public final void push(Long e) {
        LongDeque.super.push(e);
    }

    /**
     * Pushes an element onto the stack represented by this list.  In other
     * words, inserts the element at the front of this list.
     *
     * This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @since 1.6
     */
    @Override
    public final void push(long e) {
        LongDeque.super.push(e);
    }

    /**
     * Pushes an element onto the stack represented by this list.  In other
     * words, inserts the element at the front of this list.
     *
     * This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @since 1.6
     */
    @Override
    public void pushPrimitive(long e) {
        addFirstPrimitive(e);
    }

    /**
     * Pops an element from the stack represented by this list.  In other
     * words, removes and returns the first element of this list.
     *
     * This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this list (which is the top
     * of the stack represented by this list)
     * @throws NoSuchElementException if this list is empty
     * @since 1.6
     */
    @Override
    public final Long pop() {
        return LongDeque.super.pop();
    }

    /**
     * Pops an element from the stack represented by this list.  In other
     * words, removes and returns the first element of this list.
     *
     * This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this list (which is the top
     * of the stack represented by this list)
     * @throws NoSuchElementException if this list is empty
     * @since 1.6
     */
    @Override
    public long popPrimitive() {
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
        return LongDeque.super.removeFirstOccurrence(o);
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
    public final boolean removeFirstOccurrence(long o) {
        return LongDeque.super.removeFirstOccurrence(o);
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
    public boolean removeFirstOccurrencePrimitive(long o) {
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
        return LongDeque.super.removeLastOccurrence(o);
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
    public final boolean removeLastOccurrence(long o) {
        return LongDeque.super.removeLastOccurrence(o);
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
    public boolean removeLastOccurrencePrimitive(long o) {
        for (LongNode x = last; x != null; x = x.prev) {
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
    public LongListIterator listIterator(int index) {
        checkPositionIndex(index);
        return new LongListItr(index);
    }

    private class LongListItr implements LongListIterator {
        private LongNode lastReturned;
        private LongNode next;
        private int nextIndex;
        private int expectedModCount = modCount;

        LongListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public long nextPrimitive() {
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
        public long previousPrimitive() {
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

            LongNode lastNext = lastReturned.next;
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
        public void setPrimitive(long e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            checkForComodification();
            lastReturned.item = e;
        }

        @Override
        public void addPrimitive(long e) {
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
        public void forEachRemaining(Consumer<? super Long> action) {
            Objects.requireNonNull(action);

            if (action instanceof LongConsumer) {
                while (modCount == expectedModCount && nextIndex < size) {
                    action.accept(next.item);
                    lastReturned = next;
                    next = next.next;
                    nextIndex++;
                }
                checkForComodification();
            } else {
                LongConsumer actionLongConsumer = (LongConsumer) action;
                while (modCount == expectedModCount && nextIndex < size) {
                    actionLongConsumer.acceptPrimitive(next.item);
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

    private static class LongNode {
        long item;
        LongNode next;
        LongNode prev;

        LongNode(LongNode prev, long element, LongNode next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * @since 1.6
     */
    @Override
    public LongIterator descendingIterator() {
        return new LongDescendingIterator();
    }

    /**
     * Adapter to provide descending iterators via ListItr.previous
     */
    private class LongDescendingIterator implements LongIterator {
        private final LongListItr itr = new LongListItr(size());

        @Override
        public boolean hasNext() {
            return itr.hasPrevious();
        }

        @Override
        public long nextPrimitive() {
            return itr.previousPrimitive();
        }

        @Override
        public void remove() {
            itr.remove();
        }
    }

    @SuppressWarnings("unchecked")
    private LongLinkedList superClone() {
        try {
            return (LongLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     * Returns a shallow copy of this {@code LongLinkedList}. (The elements
     * themselves are not cloned.)
     *
     * @return a shallow copy of this {@code LongLinkedList} instance
     */
    @Override
    public Object clone() {
        LongLinkedList clone = superClone();

        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initialize clone with our elements
        for (LongNode x = first; x != null; x = x.next) {
            clone.addPrimitive(x.item);
        }

        return clone;
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list
     * in proper sequence
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (LongNode x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list
     * in proper sequence
     */
    @Override
    public long[] toArrayPrimitive() {
        long[] result = new long[size];
        int i = 0;
        for (LongNode x = first; x != null; x = x.next) {
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
     * If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * Suppose {@code x} is a list known to contain only strings.
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
        for (LongNode x = first; x != null; x = x.next) {
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
     * If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * Suppose {@code x} is a list known to contain only strings.
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
    public long[] toArrayPrimitive(long[] a) {
        if (a.length < size) {
            a = new long[size];
        }
        int i = 0;
        long[] result = a;
        for (LongNode x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }

        if (a.length > size) {
            a[size] = Primitive.LONG_DEFAULT;
        }

        return a;
    }

    /**
     * Saves the state of this {@code LongLinkedList} instance to a stream
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
        for (LongNode x = first; x != null; x = x.next) {
            s.writeLong(x.item);
        }
    }

    /**
     * Reconstitutes this {@code LongLinkedList} instance from a stream
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
            linkLastPrimitive(s.readLong());
        }
    }

    /**
     * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
     * list.
     *
     * The {@code Spliterator} reports {@link Spliterator#SIZED} and
     * {@link Spliterator#ORDERED}.  Overriding implementations should document
     * the reporting of additional characteristic values.
     *
     * @return a {@code Spliterator} over the elements in this list
     * @implNote The {@code Spliterator} additionally reports {@link Spliterator#SUBSIZED}
     * and implements {@code trySplit} to permit limited parallelism..
     * @since 1.8
     */
    @Override
    public LongSpliterator spliterator() {
        return new LongLLSpliterator(this, -1, 0);
    }

    /**
     * A customized variant of Spliterators.IteratorSpliterator
     */
    static final class LongLLSpliterator implements LongSpliterator {
        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        final LongLinkedList list; // null OK unless traversed
        LongNode current;      // current node; null until initialized
        int est;              // size estimate; -1 until first needed
        int expectedModCount; // initialized when est set
        int batch;            // batch size for splits

        LongLLSpliterator(LongLinkedList list, int est, int expectedModCount) {
            this.list = list;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getEst() {
            int s; // force initialization
            final LongLinkedList lst;
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
        public LongSpliterator trySplit() {
            LongNode p;
            int s = getEst();
            if (s > 1 && (p = current) != null) {
                int n = batch + BATCH_UNIT;
                if (n > s) {
                    n = s;
                }
                if (n > MAX_BATCH) {
                    n = MAX_BATCH;
                }
                long[] a = new long[n];
                int j = 0;
                do {
                    a[j++] = p.item;
                } while ((p = p.next) != null && j < n);
                current = p;
                batch = j;
                est = s - j;
                return LongSpliterators.spliterator(a, 0, j, LongSpliterator.ORDERED);
            }
            return null;
        }

        @Override
        public void forEachRemaining(Consumer<? super Long> action) {
            LongNode p;
            int n;
            if (action == null) {
                throw new NullPointerException();
            }

            if (action instanceof LongConsumer) {
                LongConsumer actionLongConsumer = (LongConsumer) action;
                if ((n = getEst()) > 0 && (p = current) != null) {
                    current = null;
                    est = 0;
                    do {
                        long e = p.item;
                        p = p.next;
                        actionLongConsumer.acceptPrimitive(e);
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
                        Long e = p.item;
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
        public boolean tryAdvance(Consumer<? super Long> action) {
            LongNode p;
            if (action == null) {
                throw new NullPointerException();
            }
            if (action instanceof LongConsumer) {
                LongConsumer actionLongConsumer = (LongConsumer) action;
                if (getEst() > 0 && (p = current) != null) {
                    --est;
                    long e = p.item;
                    current = p.next;
                    actionLongConsumer.acceptPrimitive(e);
                    if (list.modCount != expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                    return true;
                }
                return false;
            } else {
                if (getEst() > 0 && (p = current) != null) {
                    --est;
                    Long e = p.item;
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
