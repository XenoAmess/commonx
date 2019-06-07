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
package com.xenoamess.commons.collections.list.primitive_array_lists;

import com.xenoamess.commonx.java.util.Arraysx;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Resizable-array implementation of the {@code List} interface.  Implements
 * all optional list operations, and permits all elements, including
 * {@code null}.  In addition to implementing the {@code List} interface,
 * this class provides methods to manipulate the size of the array that is
 * used internally to store the list.  (This class is roughly equivalent to
 * {@code Vector}, except that it is unsynchronized.)
 *
 * <p>The {@code size}, {@code isEmpty}, {@code get}, {@code set},
 * {@code iterator}, and {@code listIterator} operations run in constant
 * time.  The {@code add} operation runs in <i>amortized constant time</i>,
 * that is, adding n elements requires O(n) time.  All of the other operations
 * run in linear time (roughly speaking).  The constant factor is low compared
 * to that for the {@code LinkedList} implementation.
 *
 * <p>Each {@code ArrayList} instance has a <i>capacity</i>.  The capacity is
 * the size of the array used to store the elements in the list.  It is always
 * at least as large as the list size.  As elements are added to an ArrayList,
 * its capacity grows automatically.  The details of the growth policy are not
 * specified beyond the fact that adding an element has constant amortized
 * time cost.
 *
 * <p>An application can increase the capacity of an {@code ArrayList} instance
 * before adding a large number of elements using the {@code ensureCapacity}
 * operation.  This may reduce the amount of incremental reallocation.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access an {@code ArrayList} instance concurrently,
 * and at least one of the threads modifies the list structurally, it
 * <i>must</i> be synchronized externally.  (A structural modification is
 * any operation that adds or deletes one or more elements, or explicitly
 * resizes the backing array; merely setting the value of an element is not
 * a structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the list.
 * <p>
 * If no such object exists, the list should be "wrapped" using the
 * {@link java.util.Collections#synchronizedList Collections.synchronizedList}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the list:<pre>
 *   List list = Collections.synchronizedList(new ArrayList(...));</pre>
 *
 * <p id="fail-fast">
 * The iterators returned by this class's {@link #iterator() iterator} and
 * {@link #listIterator(int) listIterator} methods are <em>fail-fast</em>:
 * if the list is structurally modified at any time after the iterator is
 * created, in any way except through the iterator's own
 * {@link com.xenoamess.commons.collections.list.primitive_array_lists.ByteListIterator#remove() remove} or
 * {@link com.xenoamess.commons.collections.list.primitive_array_lists.ByteListIterator#add(Object) add} methods, the
 * iterator will throw a
 * {@link java.util.ConcurrentModificationException}.  Thus, in the face of
 * concurrent modification, the iterator fails quickly and cleanly, rather
 * than risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw {@code ConcurrentModificationException} on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:  <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @version 0.6.0
 * @see Collection
 * @see List
 * @see LinkedList
 * @see Vector
 * @since 1.2
 */
public class ByteArrayList extends PrimitiveArrayList<Byte> {
    /**
     * Shared empty array instance used for empty instances.
     */
    private static final byte[] EMPTY_ELEMENTDATA = {};

    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     */
    private static final byte[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer. Any
     * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will be expanded to DEFAULT_CAPACITY when the first element is added.
     */
    transient byte[] elementData; // non-private to simplify nested class access

    /**
     * The size of the ArrayList (the number of elements it contains).
     *
     * @serial
     */
    private int size;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws java.lang.IllegalArgumentException if the specified initial capacity
     *                                            is negative
     */
    public ByteArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new byte[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ByteArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws java.lang.NullPointerException if the specified collection is null
     */
    public ByteArrayList(Collection<? extends Byte> c) {
        this(ArrayUtils.toPrimitive((Byte[]) c.toArray()));
    }

    /**
     * Constructs a ByteArrayList with a
     *
     * @param byteArray the collection whose elements are to be placed into this list
     * @throws java.lang.NullPointerException if the specified collection is null
     */
    public ByteArrayList(byte[] byteArray) {
        elementData = byteArray;
        size = elementData.length;
        if (size == 0) {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * Trims the capacity of this {@code ArrayList} instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an {@code ArrayList} instance.
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * Increases the capacity of this {@code ArrayList} instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length
                && !(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
                && minCapacity <= DEFAULT_CAPACITY)) {
            modCount++;
            grow(minCapacity);
        }
    }

    /**
     * The maximum size of array to allocate (unless necessary).
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     * @throws OutOfMemoryError if minCapacity is less than zero
     */
    private byte[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                newCapacity(minCapacity));
    }

    private byte[] grow() {
        return grow(size + 1);
    }

    /**
     * Returns a capacity at least as large as the given minimum capacity.
     * Returns the current capacity increased by 50% if that suffices.
     * Will not return a capacity greater than MAX_ARRAY_SIZE unless
     * the given minimum capacity is greater than MAX_ARRAY_SIZE.
     *
     * @param minCapacity the desired minimum capacity
     * @throws OutOfMemoryError if minCapacity is less than zero
     */
    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            if (minCapacity < 0) // overflow
            {
                throw new OutOfMemoryError();
            }
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
        {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns {@code true} if this list contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o a byte.
     * @return a boolean.
     */
    public boolean contains(byte o) {
        return this.containsPrimitive(o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o a byte.
     * @return a boolean.
     */
    public boolean containsPrimitive(byte o) {
        return indexOfPrimitive(o) >= 0;
    }


    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     */
    @Override
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o a byte.
     * @return a int.
     */
    public int indexOf(byte o) {
        return this.indexOfPrimitive(o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o a byte.
     * @return a int.
     */
    public int indexOfPrimitive(byte o) {
        return indexOfRangePrimitive(o, 0, size);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o     a {@link java.lang.Object} object.
     * @param start a int.
     * @param end   a int.
     * @return a int.
     */
    public int indexOfRange(Object o, int start, int end) {
        if (o == null) {
            return -1;
        }
        if (!(o instanceof Byte)) {
            return -1;
        }
        return this.indexOfRangePrimitive((Byte) o, start, end);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o     a byte.
     * @param start a int.
     * @param end   a int.
     * @return a int.
     */
    public int indexOfRange(byte o, int start, int end) {
        return this.indexOfRangePrimitive(o, start, end);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o     a byte.
     * @param start a int.
     * @param end   a int.
     * @return a int.
     */
    public int indexOfRangePrimitive(byte o, int start, int end) {
        byte tmpByteValue = o;
        byte[] es = elementData;
        for (int i = start; i < end; i++) {
            if (es[i] == tmpByteValue) {
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     */
    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o a byte.
     * @return a int.
     */
    public int lastIndexOf(byte o) {
        return this.lastIndexOfPrimitive(o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o a byte.
     * @return a int.
     */
    public int lastIndexOfPrimitive(byte o) {
        return this.lastIndexOfRangePrimitive(o, 0, size);
    }

    /**
     * <p>lastIndexOfRange.</p>
     *
     * @param o     a {@link java.lang.Object} object.
     * @param start a int.
     * @param end   a int.
     * @return a int.
     */
    public int lastIndexOfRange(Object o, int start, int end) {
        if (o == null) {
            return -1;
        }
        if (!(o instanceof Byte)) {
            return -1;
        }
        return this.lastIndexOfRangePrimitive(o, start, end);
    }

    /**
     * <p>lastIndexOfRange.</p>
     *
     * @param o     a byte.
     * @param start a int.
     * @param end   a int.
     * @return a int.
     */
    public int lastIndexOfRange(byte o, int start, int end) {
        return this.lastIndexOfRangePrimitive(o, start, end);
    }

    /**
     * <p>lastIndexOfRangePrimitive.</p>
     *
     * @param o     a {@link java.lang.Object} object.
     * @param start a int.
     * @param end   a int.
     * @return a int.
     */
    public int lastIndexOfRangePrimitive(Object o, int start, int end) {
        if (o == null) {
            return -1;
        }
        if (!(o instanceof Byte)) {
            return -1;
        }
        byte tmpByteValue = (Byte) o;
        byte[] es = elementData;
        for (int i = end - 1; i >= start; i--) {
            if (tmpByteValue == es[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a shallow copy of this {@code ArrayList} instance.  (The
     * elements themselves are not copied.)
     */
    @Override
    public Object clone() {
        try {
            ByteArrayList v = (ByteArrayList) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     */
    @Override
    public Object[] toArray() {
        return ArrayUtils.toObject(this.toArrayPrimitive());
    }

    /**
     * {@inheritDoc}
     * <p>
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
     * @return an array of {@link byte} objects.
     */
    public byte[] toArrayPrimitive() {
        return elementData;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array.  If the list fits in the
     * specified array, it is returned therein.  Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of
     * this list.
     *
     * <p>If the list fits in the specified array with room to spare
     * (i.e., the array has more elements than the list), the element in
     * the array immediately following the end of the collection is set to
     * {@code null}.  (This is useful in determining the length of the
     * list <i>only</i> if the caller knows that the list does not contain
     * any null elements.)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Make a new array of a's runtime type, but my contents:
            return (T[]) ArrayUtils.toObject(elementData);
        }
        System.arraycopy(ArrayUtils.toObject(elementData), 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array.  If the list fits in the
     * specified array, it is returned therein.  Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of
     * this list.
     *
     * <p>If the list fits in the specified array with room to spare
     * (i.e., the array has more elements than the list), the element in
     * the array immediately following the end of the collection is set to
     * {@code null}.  (This is useful in determining the length of the
     * list <i>only</i> if the caller knows that the list does not contain
     * any null elements.)
     *
     * @param a an array of {@link byte} objects.
     * @return an array of {@link byte} objects.
     */
    public byte[] toArrayPrimitive(byte[] a) {
        if (a.length < size) {
            // Make a new array of a's runtime type, but my contents:
            return Arrays.copyOf(elementData, size);
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size) {
            a[size] = 0;
        }
        return a;
    }

    // Positional Access Operations

    /**
     * <p>elementData.</p>
     *
     * @param index a int.
     * @return a byte.
     */
    public byte elementData(int index) {
        return elementData[index];
    }

    /**
     * <p>elementAt.</p>
     *
     * @param es    an array of {@link byte} objects.
     * @param index a int.
     * @return a byte.
     */
    public static byte elementAt(byte[] es, int index) {
        return es[index];
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the element at the specified position in this list.
     */
    @Override
    public Byte get(int index) {
        return getPrimitive(index);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     */
    public byte getPrimitive(int index) {
        checkIndex(index, size);
        return elementData(index);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Replaces the element at the specified position in this list with
     * the specified element.
     */
    @Override
    public Byte set(int index, Byte element) {
        return setPrimitive(index, element);
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     */
    public byte setPrimitive(int index, byte element) {
        checkIndex(index, size);
        byte oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * This helper method split out from add(E) to keep method
     * bytecode size under 35 (the -XX:MaxInlineSize default value),
     * which helps when add(E) is called in a C1-compiled loop.
     */
    private void add(byte e, byte[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = e;
        size = s + 1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Appends the specified element to the end of this list.
     */
    @Override
    public boolean add(Byte e) {
        return addPrimitive(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link java.util.Collection#add})
     */
    public boolean addPrimitive(byte e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     */
    @Override
    public void add(int index, Byte element) {
        addPrimitive(index, element);
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     */
    public void addPrimitive(int index, byte element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        byte[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     */
    @Override
    public Byte remove(int index) {
        return removePrimitive(index);
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     */
    public byte removePrimitive(int index) {
        checkIndex(index, size);
        final byte[] es = elementData;

        @SuppressWarnings("unchecked") byte oldValue = es[index];
        fastRemove(es, index);

        return oldValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof List)) {
            return false;
        }

        final int expectedModCount = modCount;
        // ArrayList can be subclassed and given arbitrary behavior, but we can
        // still deal with the common case where o is ArrayList precisely
        boolean equal = (o.getClass() == ByteArrayList.class)
                ? equalsByteArrayList((ByteArrayList) o)
                : equalsRange((List<?>) o, 0, size);

        checkForComodification(expectedModCount);
        return equal;
    }

    boolean equalsRange(List<?> other, int from, int to) {
        final byte[] es = elementData;
        if (to > es.length) {
            throw new ConcurrentModificationException();
        }
        Iterator<?> oit = other.iterator();
        for (; from < to; from++) {
            if (!oit.hasNext() || !Objects.equals(es[from], oit.next())) {
                return false;
            }
        }
        return !oit.hasNext();
    }

    private boolean equalsByteArrayList(ByteArrayList other) {
        final int otherModCount = other.modCount;
        final int s = size;
        boolean equal;
        if (equal = (s == other.size)) {
            final byte[] otherEs = other.elementData;
            final byte[] es = elementData;
            if (s > es.length || s > otherEs.length) {
                throw new ConcurrentModificationException();
            }
            for (int i = 0; i < s; i++) {
                if (!Objects.equals(es[i], otherEs[i])) {
                    equal = false;
                    break;
                }
            }
        }
        other.checkForComodification(otherModCount);
        return equal;
    }

    private void checkForComodification(final int expectedModCount) {
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int expectedModCount = modCount;
        int hash = hashCodeRange(0, size);
        checkForComodification(expectedModCount);
        return hash;
    }

    /**
     * <p>hashCodeRange.</p>
     *
     * @param from a int.
     * @param to   a int.
     * @return a int.
     */
    public int hashCodeRange(int from, int to) {
        final byte[] es = elementData;
        if (to > es.length) {
            throw new ConcurrentModificationException();
        }
        int hashCode = 1;
        for (int i = from; i < to; i++) {
            byte e = es[i];
            hashCode = 31 * hashCode + Byte.hashCode(e);
        }
        return hashCode;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Byte)) {
            return false;
        }
        return this.removePrimitive((Byte) o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o a byte.
     * @return a boolean.
     */
    public boolean removePrimitive(byte o) {
        final byte[] es = elementData;
        final int size = this.size;
        int i = 0;
        found:
        {
            for (; i < size; i++) {
                if (o == es[i]) {
                    break found;
                }
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    /**
     * Private remove method that skips bounds checking and does not
     * return the value removed.
     *
     * @param es an array of {@link byte} objects.
     * @param i  a int.
     */
    public void fastRemove(byte[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        modCount++;
        final byte[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++) {
            es[i] = 0;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the specified collection is this list, and this
     * list is nonempty.)
     */
    @Override
    public boolean addAll(Collection<? extends Byte> c) {
        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        byte[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     */
    @Override
    public boolean addAll(int index, Collection<? extends Byte> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        byte[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }

        int numMoved = s - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index,
                    elementData, index + numNew,
                    numMoved);
        }
        System.arraycopy(a, 0, elementData, index, numNew);
        size = s + numNew;
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes from this list all of the elements whose index is between
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
     * Shifts any succeeding elements to the left (reduces their index).
     * This call shortens the list by {@code (toIndex - fromIndex)} elements.
     * (If {@code toIndex==fromIndex}, this operation has no effect.)
     */
    @Override
    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(
                    outOfBoundsMsg(fromIndex, toIndex));
        }
        modCount++;
        shiftTailOverGap(elementData, fromIndex, toIndex);
    }

    /**
     * Erases the gap from lo to hi, by sliding down following elements.
     *
     * @param es an array of {@link byte} objects.
     * @param lo a int.
     * @param hi a int.
     */
    public void shiftTailOverGap(byte[] es, int lo, int hi) {
        System.arraycopy(es, hi, es, lo, size - hi);
        for (int to = size, i = (size -= hi - lo); i < to; i++) {
            es[i] = 0;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * A version of rangeCheck used by add and addAll.
     */
    public void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    public String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * A version used in checking (fromIndex &gt; toIndex) condition
     *
     * @param fromIndex a int.
     * @param toIndex   a int.
     * @return a {@link java.lang.String} object.
     */
    public static String outOfBoundsMsg(int fromIndex, int toIndex) {
        return "From Index: " + fromIndex + " > To Index: " + toIndex;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @see Collection#contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return batchRemove(c, false, 0, size);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Retains only the elements in this list that are contained in the
     * specified collection.  In other words, removes from this list all
     * of its elements that are not contained in the specified collection.
     *
     * @see Collection#contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return batchRemove(c, true, 0, size);
    }

    /**
     * <p>batchRemove.</p>
     *
     * @param c          a {@link java.util.Collection} object.
     * @param complement a boolean.
     * @param from       a int.
     * @param end        a int.
     * @return a boolean.
     */
    public boolean batchRemove(Collection<?> c, boolean complement,
                               final int from, final int end) {
        Objects.requireNonNull(c);
        final byte[] es = elementData;
        int r;
        // Optimize for initial run of survivors
        for (r = from; ; r++) {
            if (r == end) {
                return false;
            }
            if (c.contains(es[r]) != complement) {
                break;
            }
        }
        int w = r++;
        try {
            for (byte e; r < end; r++) {
                if (c.contains(e = es[r]) == complement) {
                    es[w++] = e;
                }
            }
        } catch (Throwable ex) {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            System.arraycopy(es, r, es, w, end - r);
            w += end - r;
            throw ex;
        } finally {
            modCount += end - w;
            shiftTailOverGap(es, w, end);
        }
        return true;
    }

    /**
     * Saves the state of the {@code ArrayList} instance to a stream
     * (that is, serializes it).
     *
     * @param s the stream
     * @throws java.io.IOException if an I/O error occurs
     * @serialData The length of the array backing the {@code ArrayList}
     * instance is emitted (int), followed by all of its elements
     * (each an {@code Object}) in the proper order.
     */
    public void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioral compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i = 0; i < size; i++) {
            s.writeByte(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * Reconstitutes the {@code ArrayList} instance from a stream (that is,
     * deserializes it).
     *
     * @param s the stream
     * @throws java.io.IOException              if an I/O error occurs
     * @throws java.lang.ClassNotFoundException if any.
     */
    public void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // like clone(), allocate array based upon size not capacity
            byte[] elements = new byte[size];

            // Read in all elements in the proper order.
            for (int i = 0; i < size; i++) {
                elements[i] = s.readByte();
            }

            elementData = elements;
        } else if (size == 0) {
            elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new java.io.InvalidObjectException("Invalid size: " + size);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ByteListIterator#next next}.
     * An initial call to {@link ByteListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     */
    @Override
    public ByteListIterator listIterator(int index) {
        rangeCheckForAdd(index);
        return new ByteArrayList.ListItr(index);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @see #listIterator(int)
     */
    @Override
    public ByteListIterator listIterator() {
        return new ByteArrayList.ListItr(0);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     */
    @Override
    public Iterator<Byte> iterator() {
        return new ByteArrayList.Itr();
    }

    /**
     * An optimized version of AbstractList.Itr
     */
    private class Itr implements ByteIterator {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Byte next() {
            return this.nextPrimitive();
        }

        @Override
        @SuppressWarnings("unchecked")
        public byte nextPrimitive() {
            checkForComodification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            byte[] elementData = ByteArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                ByteArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super Byte> action) {
            Objects.requireNonNull(action);
            final int size = ByteArrayList.this.size;
            int i = cursor;
            if (i < size) {
                final byte[] es = elementData;
                if (i >= es.length) {
                    throw new ConcurrentModificationException();
                }
                for (; i < size && modCount == expectedModCount; i++) {
                    action.accept(elementAt(es, i));
                }
                // update once at end to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * An optimized version of AbstractList.ListItr
     */
    private class ListItr extends ByteArrayList.Itr implements ByteListIterator {
        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Byte previous() {
            return this.previousPrimitive();
        }

        @Override
        @SuppressWarnings("unchecked")
        public byte previousPrimitive() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            byte[] elementData = ByteArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i;
            return elementData[lastRet = i];
        }

        @Override
        public void set(Byte e) {
            setPrimitive(e);
        }

        @Override
        public void setPrimitive(byte e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                ByteArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(Byte e) {
            addPrimitive(e);
        }

        @Override
        public void addPrimitive(byte e) {
            checkForComodification();

            try {
                int i = cursor;
                ByteArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations.
     *
     * <p>This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>
     *      list.subList(from, to).clear();
     * </pre>
     * Similar idioms may be constructed for {@link #indexOf(Object)} and
     * {@link #lastIndexOf(Object)}, and all of the algorithms in the
     * {@link Collections} class can be applied to a subList.
     *
     * <p>The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     */
    @Override
    public List<Byte> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new ByteArrayList.ByteSubList(this, fromIndex, toIndex);
    }

    private static class ByteSubList extends AbstractList<Byte> implements RandomAccess {
        private final ByteArrayList root;
        private final ByteArrayList.ByteSubList parent;
        private final int offset;
        private int size;

        /**
         * Constructs a sublist of an arbitrary ByteArrayList.
         */
        public ByteSubList(ByteArrayList root, int fromIndex, int toIndex) {
            this.root = root;
            this.parent = null;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        /**
         * Constructs a sublist of another SubList.
         */
        private ByteSubList(ByteArrayList.ByteSubList parent, int fromIndex, int toIndex) {
            this.root = parent.root;
            this.parent = parent;
            this.offset = parent.offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        @Override
        public Byte set(int index, Byte element) {
            return this.setPrimitive(index, element);
        }

        public byte setPrimitive(int index, byte element) {
            checkIndex(index, size);
            checkForComodification();
            byte oldValue = root.elementData(offset + index);
            root.elementData[offset + index] = element;
            return oldValue;
        }

        @Override
        public Byte get(int index) {
            return this.getPrimitive(index);
        }

        public byte getPrimitive(int index) {
            checkIndex(index, size);
            checkForComodification();
            return root.elementData(offset + index);
        }

        @Override
        public int size() {
            checkForComodification();
            return size;
        }

        @Override
        public void add(int index, Byte element) {
            this.addPrimitive(index, element);
        }

        public void addPrimitive(int index, byte element) {
            rangeCheckForAdd(index);
            checkForComodification();
            root.add(offset + index, element);
            updateSizeAndModCount(1);
        }

        @Override
        public Byte remove(int index) {
            return this.removePrimitive(index);
        }

        public byte removePrimitive(int index) {
            checkIndex(index, size);
            checkForComodification();
            byte result = root.remove(offset + index);
            updateSizeAndModCount(-1);
            return result;
        }

        @Override
        protected void removeRange(int fromIndex, int toIndex) {
            checkForComodification();
            root.removeRange(offset + fromIndex, offset + toIndex);
            updateSizeAndModCount(fromIndex - toIndex);
        }

        @Override
        public boolean addAll(Collection<? extends Byte> c) {
            return addAll(this.size, c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends Byte> c) {
            rangeCheckForAdd(index);
            int cSize = c.size();
            if (cSize == 0) {
                return false;
            }
            checkForComodification();
            root.addAll(offset + index, c);
            updateSizeAndModCount(cSize);
            return true;
        }

        @Override
        public void replaceAll(UnaryOperator<Byte> operator) {
            root.replaceAllRange(operator, offset, offset + size);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return batchRemove(c, false);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return batchRemove(c, true);
        }

        private boolean batchRemove(Collection<?> c, boolean complement) {
            checkForComodification();
            int oldSize = root.size;
            boolean modified =
                    root.batchRemove(c, complement, offset, offset + size);
            if (modified) {
                updateSizeAndModCount(root.size - oldSize);
            }
            return modified;
        }

        @Override
        public boolean removeIf(Predicate<? super Byte> filter) {
            checkForComodification();
            int oldSize = root.size;
            boolean modified = root.removeIf(filter, offset, offset + size);
            if (modified) {
                updateSizeAndModCount(root.size - oldSize);
            }
            return modified;
        }

        @Override
        public Object[] toArray() {
            return ArrayUtils.toObject(this.toArrayPrimitive());
        }

        public byte[] toArrayPrimitive() {
            checkForComodification();
            return Arrays.copyOfRange(root.elementData, offset, offset + size);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a) {
            checkForComodification();
            if (a.length < size) {
                return (T[]) ArrayUtils.toObject(Arrays.copyOfRange(root.elementData, offset, offset + size));
            }
            System.arraycopy(ArrayUtils.toObject(root.elementData), offset, a, 0, size);
            if (a.length > size) {
                a[size] = null;
            }
            return a;
        }

        public byte[] toArrayPrimitive(byte[] a) {
            checkForComodification();
            if (a.length < size) {
                return Arrays.copyOfRange(root.elementData, offset, offset + size);
            }
            System.arraycopy(root.elementData, offset, a, 0, size);
            if (a.length > size) {
                a[size] = 0;
            }
            return a;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof List)) {
                return false;
            }

            boolean equal = root.equalsRange((List<?>) o, offset, offset + size);
            checkForComodification();
            return equal;
        }

        @Override
        public int hashCode() {
            int hash = root.hashCodeRange(offset, offset + size);
            checkForComodification();
            return hash;
        }

        @Override
        public int indexOf(Object o) {
            int index = root.indexOfRange(o, offset, offset + size);
            checkForComodification();
            return index >= 0 ? index - offset : -1;
        }

        public int indexOfPrimitive(byte o) {
            int index = root.indexOfRangePrimitive(o, offset, offset + size);
            checkForComodification();
            return index >= 0 ? index - offset : -1;
        }

        @Override
        public int lastIndexOf(Object o) {
            int index = root.lastIndexOfRange(o, offset, offset + size);
            checkForComodification();
            return index >= 0 ? index - offset : -1;
        }

        public int lastIndexOfPrimitive(byte o) {
            int index = root.lastIndexOfRangePrimitive(o, offset, offset + size);
            checkForComodification();
            return index >= 0 ? index - offset : -1;
        }

        @Override
        public boolean contains(Object o) {
            return indexOf(o) >= 0;
        }

        @Override
        public Iterator<Byte> iterator() {
            return listIterator();
        }

        @Override
        public ByteListIterator listIterator(int index) {
            checkForComodification();
            rangeCheckForAdd(index);

            return new ByteListIterator() {
                int cursor = index;
                int lastRet = -1;
                int expectedModCount = root.modCount;

                @Override
                public boolean hasNext() {
                    return cursor != ByteArrayList.ByteSubList.this.size;
                }

                @Override
                public Byte next() {
                    return nextPrimitive();
                }

                @Override
                public byte nextPrimitive() {
                    checkForComodification();
                    int i = cursor;
                    if (i >= ByteArrayList.ByteSubList.this.size) {
                        throw new NoSuchElementException();
                    }
                    byte[] elementData = root.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    cursor = i + 1;
                    return elementData[offset + (lastRet = i)];
                }

                @Override
                public boolean hasPrevious() {
                    return cursor != 0;
                }

                @Override
                public Byte previous() {
                    return previousPrimitive();
                }

                @Override
                public byte previousPrimitive() {
                    checkForComodification();
                    int i = cursor - 1;
                    if (i < 0) {
                        throw new NoSuchElementException();
                    }
                    byte[] elementData = root.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    cursor = i;
                    return elementData[offset + (lastRet = i)];
                }

                @Override
                public void forEachRemaining(Consumer<? super Byte> action) {
                    Objects.requireNonNull(action);
                    final int size = ByteArrayList.ByteSubList.this.size;
                    int i = cursor;
                    if (i < size) {
                        final byte[] es = root.elementData;
                        if (offset + i >= es.length) {
                            throw new ConcurrentModificationException();
                        }
                        for (; i < size && modCount == expectedModCount; i++) {
                            action.accept(elementAt(es, offset + i));
                        }
                        // update once at end to reduce heap write traffic
                        cursor = i;
                        lastRet = i - 1;
                        checkForComodification();
                    }
                }

                @Override
                public int nextIndex() {
                    return cursor;
                }

                @Override
                public int previousIndex() {
                    return cursor - 1;
                }

                @Override
                public void remove() {
                    if (lastRet < 0) {
                        throw new IllegalStateException();
                    }
                    checkForComodification();

                    try {
                        ByteArrayList.ByteSubList.this.remove(lastRet);
                        cursor = lastRet;
                        lastRet = -1;
                        expectedModCount = root.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public void set(Byte e) {
                    setPrimitive(e);
                }

                @Override
                public void setPrimitive(byte e) {
                    if (lastRet < 0) {
                        throw new IllegalStateException();
                    }
                    checkForComodification();

                    try {
                        root.set(offset + lastRet, e);
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public void add(Byte e) {
                    addPrimitive(e);
                }

                @Override
                public void addPrimitive(byte e) {
                    checkForComodification();

                    try {
                        int i = cursor;
                        ByteArrayList.ByteSubList.this.add(i, e);
                        cursor = i + 1;
                        lastRet = -1;
                        expectedModCount = root.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                final void checkForComodification() {
                    if (root.modCount != expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }
            };
        }

        @Override
        public List<Byte> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new ByteArrayList.ByteSubList(this, fromIndex, toIndex);
        }

        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            }
        }

        private String outOfBoundsMsg(int index) {
            return "Index: " + index + ", Size: " + this.size;
        }

        private void checkForComodification() {
            if (root.modCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        private void updateSizeAndModCount(int sizeChange) {
            ByteArrayList.ByteSubList slist = this;
            do {
                slist.size += sizeChange;
                slist.modCount = root.modCount;
                slist = slist.parent;
            } while (slist != null);
        }

        @Override
        public Spliterator<Byte> spliterator() {
            checkForComodification();

            // ArrayListSpliterator not used here due to late-binding
            return new Spliterator<Byte>() {
                private int index = offset; // current index, modified on advance/split
                private int fence = -1; // -1 until used; then one past last index
                private int expectedModCount; // initialized when fence set

                private int getFence() { // initialize fence to size on first use
                    int hi; // (a specialized variant appears in method forEach)
                    if ((hi = fence) < 0) {
                        expectedModCount = modCount;
                        hi = fence = offset + size;
                    }
                    return hi;
                }

                @Override
                public ByteArrayList.ArrayListSpliterator trySplit() {
                    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
                    // ArrayListSpliterator can be used here as the source is already bound
                    return (lo >= mid) ? null : // divide range in half unless too small
                            root.new ArrayListSpliterator(lo, index = mid, expectedModCount);
                }

                @Override
                public boolean tryAdvance(Consumer<? super Byte> action) {
                    Objects.requireNonNull(action);
                    int hi = getFence(), i = index;
                    if (i < hi) {
                        index = i + 1;
                        byte e = root.elementData[i];
                        action.accept(e);
                        if (root.modCount != expectedModCount) {
                            throw new ConcurrentModificationException();
                        }
                        return true;
                    }
                    return false;
                }

                @Override
                public void forEachRemaining(Consumer<? super Byte> action) {
                    Objects.requireNonNull(action);
                    int i, hi, mc; // hoist accesses and checks from loop
                    ByteArrayList lst = root;
                    byte[] a;
                    if ((a = lst.elementData) != null) {
                        if ((hi = fence) < 0) {
                            mc = modCount;
                            hi = offset + size;
                        } else {
                            mc = expectedModCount;
                        }
                        if ((i = index) >= 0 && (index = hi) <= a.length) {
                            for (; i < hi; ++i) {
                                byte e = a[i];
                                action.accept(e);
                            }
                            if (lst.modCount == mc) {
                                return;
                            }
                        }
                    }
                    throw new ConcurrentModificationException();
                }

                @Override
                public long estimateSize() {
                    return getFence() - index;
                }

                @Override
                public int characteristics() {
                    return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
                }
            };
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Consumer<? super Byte> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        final byte[] es = elementData;
        final int size = this.size;
        for (int i = 0; modCount == expectedModCount && i < size; i++) {
            action.accept(elementAt(es, i));
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
     * list.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED},
     * {@link Spliterator#SUBSIZED}, and {@link Spliterator#ORDERED}.
     * Overriding implementations should document the reporting of additional
     * characteristic values.
     *
     * @since 1.8
     */
    @Override
    public Spliterator<Byte> spliterator() {
        return new ByteArrayList.ArrayListSpliterator(0, -1, 0);
    }

    /**
     * Index-based split-by-two, lazily initialized Spliterator
     */
    final class ArrayListSpliterator implements Spliterator<Byte> {

        /*
         * If ArrayLists were immutable, or structurally immutable (no
         * adds, removes, etc), we could implement their spliterators
         * with Arrays.spliterator. Instead we detect as much
         * interference during traversal as practical without
         * sacrificing much performance. We rely primarily on
         * modCounts. These are not guaranteed to detect concurrency
         * violations, and are sometimes overly conservative about
         * within-thread interference, but detect enough problems to
         * be worthwhile in practice. To carry this out, we (1) lazily
         * initialize fence and expectedModCount until the latest
         * point that we need to commit to the state we are checking
         * against; thus improving precision.  (This doesn't apply to
         * SubLists, that create spliterators with current non-lazy
         * values).  (2) We perform only a single
         * ConcurrentModificationException check at the end of forEach
         * (the most performance-sensitive method). When using forEach
         * (as opposed to iterators), we can normally only detect
         * interference after actions, not before. Further
         * CME-triggering checks apply to all other possible
         * violations of assumptions for example null or too-small
         * elementData array given its size(), that could only have
         * occurred due to interference.  This allows the inner loop
         * of forEach to run without any further checks, and
         * simplifies lambda-resolution. While this does entail a
         * number of checks, note that in the common case of
         * list.stream().forEach(a), no checks or other computation
         * occur anywhere other than inside forEach itself.  The other
         * less-often-used methods cannot take advantage of most of
         * these streamlinings.
         */

        private int index; // current index, modified on advance/split
        private int fence; // -1 until used; then one past last index
        private int expectedModCount; // initialized when fence set

        /**
         * Creates new spliterator covering the given range.
         */
        ArrayListSpliterator(int origin, int fence, int expectedModCount) {
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence() { // initialize fence to size on first use
            int hi; // (a specialized variant appears in method forEach)
            if ((hi = fence) < 0) {
                expectedModCount = modCount;
                hi = fence = size;
            }
            return hi;
        }

        @Override
        public ByteArrayList.ArrayListSpliterator trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : // divide range in half unless too small
                    new ByteArrayList.ArrayListSpliterator(lo, index = mid, expectedModCount);
        }

        @Override
        public boolean tryAdvance(Consumer<? super Byte> action) {
            if (action == null) {
                throw new NullPointerException();
            }
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                byte e = elementData[i];
                action.accept(e);
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }

        @Override
        public void forEachRemaining(Consumer<? super Byte> action) {
            int i, hi, mc; // hoist accesses and checks from loop
            byte[] a;
            if (action == null) {
                throw new NullPointerException();
            }
            if ((a = elementData) != null) {
                if ((hi = fence) < 0) {
                    mc = modCount;
                    hi = size;
                } else {
                    mc = expectedModCount;
                }
                if ((i = index) >= 0 && (index = hi) <= a.length) {
                    for (; i < hi; ++i) {
                        byte e = a[i];
                        action.accept(e);
                    }
                    if (modCount == mc) {
                        return;
                    }
                }
            }
            throw new ConcurrentModificationException();
        }

        @Override
        public long estimateSize() {
            return getFence() - index;
        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

    }

    // A tiny bit set implementation

    private static long[] nBits(int n) {
        return new long[((n - 1) >> 6) + 1];
    }

    private static void setBit(long[] bits, int i) {
        bits[i >> 6] |= 1L << i;
    }

    private static boolean isClear(long[] bits, int i) {
        return (bits[i >> 6] & (1L << i)) == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeIf(Predicate<? super Byte> filter) {
        return removeIf(filter, 0, size);
    }

    /**
     * Removes all elements satisfying the given predicate, from index
     * i (inclusive) to index end (exclusive).
     */
    boolean removeIf(Predicate<? super Byte> filter, int i, final int end) {
        Objects.requireNonNull(filter);
        int expectedModCount = modCount;
        final byte[] es = elementData;
        // Optimize for initial run of survivors
        for (; i < end && !filter.test(elementAt(es, i)); i++) {
            ;
        }
        // Tolerate predicates that reentrantly access the collection for
        // read (but writers still get CME), so traverse once to find
        // elements to delete, a second pass to physically expunge.
        if (i < end) {
            final int beg = i;
            final long[] deathRow = nBits(end - beg);
            deathRow[0] = 1L;   // set bit 0
            for (i = beg + 1; i < end; i++) {
                if (filter.test(elementAt(es, i))) {
                    setBit(deathRow, i - beg);
                }
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            modCount++;
            int w = beg;
            for (i = beg; i < end; i++) {
                if (isClear(deathRow, i - beg)) {
                    es[w++] = es[i];
                }
            }
            shiftTailOverGap(es, w, end);
            return true;
        } else {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void replaceAll(UnaryOperator<Byte> operator) {
        replaceAllRange(operator, 0, size);
        modCount++;
    }

    private void replaceAllRange(UnaryOperator<Byte> operator, int i, int end) {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final byte[] es = elementData;
        for (; modCount == expectedModCount && i < end; i++) {
            es[i] = operator.apply(elementAt(es, i));
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(Comparator<? super Byte> c) {
        if (!(c instanceof ByteComparator)) {
            throw new NotImplementedException("For more performance, this class only accept ByteComparator as " +
                    "comparator.");
        }
        this.sort((ByteComparator) c);
    }

    /**
     * <p>sort.</p>
     *
     * @param c a {@link com.xenoamess.commons.collections.list.primitive_array_lists.ByteComparator} object.
     */
    public void sort(ByteComparator c) {
        final int expectedModCount = modCount;
        Arraysx.sort(elementData, 0, size, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

    void checkInvariants() {
        // assert size >= 0;
        // assert size == elementData.length || elementData[size] == null;
    }
}
