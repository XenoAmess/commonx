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

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * An resizable-array implementation of the {@code List} interface.
 * <p>
 * This class aims to reduce performance issue of autoboxing and unboxing.
 * <p>
 * This class is designed to be a replacement to {@code ArrayList<Short>}
 * <p>
 * It shall be far faster in using.
 * <p>
 * It have functions dealing with {@code Short} for being a {@code List},
 * but nearly all of its functions have a replacement named XXXPrimitive.
 * The basic idea is use XXXPrimitive functions whenever possible, and only use
 * other functions when you have to.
 * <p>
 * Most of its functions are modified from {@code rrayList}, so as documents.
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @author XenoAmess
 * @version 0.6.0
 * @see ArrayList
 * @see Collection
 * @see List
 * @see LinkedList
 * @see Vector
 * @since 1.2
 */
public class ShortArrayList extends PrimitiveArrayList<Short> {

    /**
     * function to copy from {@code Object[]} to {@code short[]}
     *
     * @param src     an array of {@link java.lang.Object} objects.
     * @param srcPos  a int.
     * @param dest    an array of {@link short} objects.
     * @param destPos a int.
     * @param length  a int.
     */
    public static void arraycopy(Object[] src, int srcPos,
                                 short[] dest, int destPos,
                                 int length) {
        for (int i = srcPos, j = destPos, limit = i + length; i < limit; i++, j++) {
            dest[j] = (Short) src[i];
        }
    }

    /**
     * function to copy from  {@code short[]} to {@code Object[]}
     *
     * @param src     an array of {@link short} objects.
     * @param srcPos  a int.
     * @param dest    an array of {@link java.lang.Object} objects.
     * @param destPos a int.
     * @param length  a int.
     */
    public static void arraycopy(short[] src, int srcPos,
                                 Object[] dest, int destPos,
                                 int length) {
        for (int i = srcPos, j = destPos, limit = i + length; i < limit; i++, j++) {
            dest[j] = src[i];
        }
    }

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final short[] EMPTY_ELEMENTDATA = {};

    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     */
    private static final short[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * The array buffer into which the elements of the {@code ShortArrayList} are stored.
     * The capacity of the {@code ShortArrayList} is the length of this array buffer. Any
     * empty {@code ShortArrayList} with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will be expanded to DEFAULT_CAPACITY when the first element is added.
     */
    transient short[] elementData; // non-private to simplify nested class access

    /**
     * The size of the {@code ShortArrayList} (the number of elements it contains).
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
    public ShortArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new short[initialCapacity];
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
    public ShortArrayList() {
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
    public ShortArrayList(Collection<? extends Short> c) {
        this(ArrayUtils.toPrimitive((Short[]) c.toArray()));
    }

    /**
     * Constructs an empty ShortArrayList with a {@code short[]}
     * <p>
     * This {@code ShortArrayList} will use this {@code short[]} directly as its {@code elementData}, and will use
     * the array until the {@code ShortArrayList} have more content than this array can hold.
     * <p>
     * we will create a full ShortArrayList, and the initial size will be shortArray.length.
     * <p>
     * When we need more space than the shortArray can hold, the {@code ShortArrayList} will create another
     * (larger) array and use it as {@code elementData} instead.
     *
     * @param shortArray the array which will be used as initial {@code elementData} of this class.
     * @throws java.lang.NullPointerException if the specified collection is null
     */
    public ShortArrayList(short[] shortArray) {
        this(shortArray, false);
    }

    /**
     * Constructs a ShortArrayList with a {@code short[]}
     * <p>
     * This {@code ShortArrayList} will use this {@code short[]} directly as its {@code elementData}, and will use
     * the array until the {@code ShortArrayList} have more content than this array can hold.
     * <p>
     * if ifEmpty == true, then will create an empty ShortArrayList, and the initial size will be 0, the current
     * content of shortArray will be ignored.
     * <p>
     * otherwise, we will create a full ShortArrayList, and the initial size will be shortArray.length.
     * <p>
     * When we need more space than the shortArray can hold,, the {@code ShortArrayList} will create another
     * (larger) array and use it as {@code elementData} instead.
     *
     * @param shortArray the array which will be used as initial {@code elementData} of this class.
     * @param ifEmpty     if we will create a full ShortArrayList.
     * @throws java.lang.NullPointerException if the specified collection is null
     */
    public ShortArrayList(short[] shortArray, boolean ifEmpty) {
        elementData = shortArray;
        size = ifEmpty ? 0 : elementData.length;
    }

    /**
     * Trims the capacity of this {@code ShortArrayList} instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an {@code ShortArrayList} instance.
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
     * Increases the capacity of this {@code ShortArrayList} instance, if
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
    private short[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                newCapacity(minCapacity));
    }

    private short[] grow() {
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
     * Primitive replacement of {@code ShortArrayList.contains(Object o)}
     *
     * @param o element whose presence in this collection is to be tested
     * @return {@code true} if this collection contains the specified
     * element
     * @see ShortArrayList#contains(Object o)
     */
    public boolean contains(short o) {
        return this.containsPrimitive(o);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.contains(Object o)}
     *
     * @param o element whose presence in this collection is to be tested
     * @return {@code true} if this collection contains the specified
     * element
     * @see ShortArrayList#contains(Object o)
     */
    public boolean containsPrimitive(short o) {
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
     * Primitive replacement of {@code ShortArrayList.indexOf(Object o)}
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @see ShortArrayList#indexOf(Object o)
     */
    public int indexOf(short o) {
        return this.indexOfPrimitive(o);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.indexOf(Object o)}
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @see ShortArrayList#indexOf(Object o)
     */
    public int indexOfPrimitive(short o) {
        return indexOfRangePrimitive(o, 0, size);
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this range of this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o     the object to search for.
     * @param start start index of this search, inclusive.
     * @param end   end index of this, exclusive.
     * @return the first index.
     */
    public int indexOfRange(Object o, int start, int end) {
        if (o == null) {
            return -1;
        }
        if (!(o instanceof Short)) {
            return -1;
        }
        return this.indexOfRangePrimitive((Short) o, start, end);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.indexOf(Object o)}
     *
     * @param o     the object to search for.
     * @param start start index of this search, inclusive.
     * @param end   end index of this, exclusive.
     * @return the first index.
     * @see ShortArrayList#indexOfRange(Object o, int start, int end)
     */
    public int indexOfRange(short o, int start, int end) {
        return this.indexOfRangePrimitive(o, start, end);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.indexOf(Object o)}
     *
     * @param o     the object to search for.
     * @param start start index of this search, inclusive.
     * @param end   end index of this, exclusive.
     * @return the first index.
     * @see ShortArrayList#indexOfRange(Object o, int start, int end)
     */
    public int indexOfRangePrimitive(short o, int start, int end) {
        short[] es = elementData;
        for (int i = start; i < end; i++) {
            if (es[i] == o) {
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
     * Primitive replacement of {@code ShortArrayList.lastIndexOf(Object o)}
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @see ShortArrayList#lastIndexOf(Object o)
     */
    public int lastIndexOf(short o) {
        return this.lastIndexOfPrimitive(o);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.lastIndexOf(Object o)}
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @see ShortArrayList#lastIndexOf(Object o)
     */
    public int lastIndexOfPrimitive(short o) {
        return this.lastIndexOfRangePrimitive(o, 0, size);
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this range of this list, or -1 if this list does not contain the element.
     * More formally, returns the largest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o     the object to search for.
     * @param start start index of this search, inclusive.
     * @param end   end index of this, exclusive.
     * @return the last index.
     */
    public int lastIndexOfRange(Object o, int start, int end) {
        if (o == null) {
            return -1;
        }
        if (!(o instanceof Short)) {
            return -1;
        }
        return this.lastIndexOfRangePrimitive((Short) o, start, end);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.lastIndexOfRange(Object o, int start, int end)}
     *
     * @param o     the object to search for.
     * @param start start index of this search, inclusive.
     * @param end   end index of this, exclusive.
     * @return the last index.
     * @see ShortArrayList#lastIndexOfRange(Object o, int start, int end)
     */
    public int lastIndexOfRange(short o, int start, int end) {
        return this.lastIndexOfRangePrimitive(o, start, end);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.lastIndexOfRange(Object o, int start, int end)}
     *
     * @param o     the object to search for.
     * @param start start index of this search, inclusive.
     * @param end   end index of this, exclusive.
     * @return the last index.
     * @see ShortArrayList#lastIndexOfRange(Object o, int start, int end)
     */
    public int lastIndexOfRangePrimitive(short o, int start, int end) {
        short tmpShortValue = o;
        short[] es = elementData;
        for (int i = end - 1; i >= start; i--) {
            if (tmpShortValue == es[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a shallow copy of this {@code ShortArrayList} instance.  (The
     * elements themselves are not copied.)
     */
    @Override
    public Object clone() {
        try {
            ShortArrayList v = (ShortArrayList) super.clone();
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
     * @return an array of {@link short}.
     */
    public short[] toArrayPrimitive() {
        return Arraysx.copyOf(elementData);
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
        ShortArrayList.arraycopy(elementData, 0, a, 0, size);
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
     * @param a an array of {@link short} objects.
     * @return an array of {@link short} objects.
     */
    public short[] toArray(short[] a) {
        return this.toArray(a);
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
     * @param a an array of {@link short} objects.
     * @return an array of {@link short} objects.
     */
    public short[] toArrayPrimitive(short[] a) {
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
     * get the raw content array from the ShortArrayList.
     * <p>This method acts as bridge between array-based and collection-based APIs.
     *
     * @return this.elementData
     */
    public short[] getElementData() {
        return this.elementData;
    }

    /**
     * get this.elementData[index] without any check.
     *
     * @param index index
     * @return this.elementData[index]
     */
    public short elementData(int index) {
        return elementData[index];
    }

    /**
     * get es[index] without any check.
     *
     * @param es    an array of {@link short} objects.
     * @param index index.
     * @return es[index].
     */
    public static short elementAt(short[] es, int index) {
        return es[index];
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the element at the specified position in this list.
     */
    @Override
    public Short get(int index) {
        return getPrimitive(index);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.get(int index)}
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     * @see ShortArrayList#get(int index)
     */
    public short getPrimitive(int index) {
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
    public Short set(int index, Short element) {
        return setPrimitive(index, element);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.set(int index, Short element)}
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     * @see ShortArrayList#set(int index, Short element)
     */
    public short set(int index, short element) {
        return this.setPrimitive(index, element);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.set(int index, Short element)}
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     * @see ShortArrayList#set(int index, Short element)
     */
    public short setPrimitive(int index, short element) {
        checkIndex(index, size);
        short oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * This helper method split out from add(E) to keep method
     * bytecode size under 35 (the -XX:MaxInlineSize default value),
     * which helps when add(E) is called in a C1-compiled loop.
     */
    private void add(short e, short[] elementData, int s) {
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
    public boolean add(Short e) {
        return addPrimitive(e);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.add(Short e)}
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link java.util.Collection#add})
     * @see ShortArrayList#add(Short e)
     */
    public boolean add(short e) {
        return this.addPrimitive(e);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.add(Short e)}
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link java.util.Collection#add})
     * @see ShortArrayList#add(Short e)
     */
    public boolean addPrimitive(short e) {
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
    public void add(int index, Short element) {
        addPrimitive(index, element);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.add(int index, Short element)}
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     * @see ShortArrayList#add(int index, Short element)
     */
    public void add(int index, short element) {
        this.addPrimitive(index, element);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.add(int index, Short element)}
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws java.lang.IndexOutOfBoundsException {@inheritDoc}
     * @see ShortArrayList#add(int index, Short element)
     */
    public void addPrimitive(int index, short element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        short[] elementData;
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
    public Short remove(int index) {
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
    public short removePrimitive(int index) {
        checkIndex(index, size);
        final short[] es = elementData;

        @SuppressWarnings("unchecked") short oldValue = es[index];
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
        // {@code ShortArrayList} can be subclassed and given arbitrary behavior, but we can
        // still deal with the common case where o is {@code ShortArrayList} precisely
        boolean equal = (o.getClass() == ShortArrayList.class)
                ? equalsShortArrayList((ShortArrayList) o)
                : equalsRange((List<?>) o, 0, size);

        checkForComodification(expectedModCount);
        return equal;
    }

    boolean equalsRange(List<?> other, int from, int to) {
        final short[] es = elementData;
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

    private boolean equalsShortArrayList(ShortArrayList other) {
        final int otherModCount = other.modCount;
        final int s = size;
        boolean equal;
        if (equal = (s == other.size)) {
            final short[] otherEs = other.elementData;
            final short[] es = elementData;
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
     * get hashcode from range.
     *
     * @param from start index, inclusive
     * @param to   end index, exclusive.
     * @return hashcode
     */
    public int hashCodeRange(int from, int to) {
        final short[] es = elementData;
        if (to > es.length) {
            throw new ConcurrentModificationException();
        }
        int hashCode = 1;
        for (int i = from; i < to; i++) {
            short e = es[i];
            hashCode = 31 * hashCode + Short.hashCode(e);
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
        if (!(o instanceof Short)) {
            return false;
        }
        return this.removePrimitive((Short) o);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.remove(Object o)}
     *
     * @param o element to be removed from this collection, if present
     * @return {@code true} if an element was removed as a result of this call
     * @see ShortArrayList#remove(Object o)
     */
    public boolean remove(short o) {
        return this.removePrimitive(o);
    }

    /**
     * Primitive replacement of {@code ShortArrayList.remove(Object o)}
     *
     * @param o element to be removed from this collection, if present
     * @return {@code true} if an element was removed as a result of this call
     * @see ShortArrayList#remove(Object o)
     */
    public boolean removePrimitive(short o) {
        final short[] es = elementData;
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
     * @param es an array of {@link short} objects.
     * @param i  a int.
     */
    public void fastRemove(short[] es, int i) {
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
        final short[] es = elementData;
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
    public boolean addAll(Collection<? extends Short> c) {
        if (c instanceof ShortArrayList) {
            return this.addAll((ShortArrayList) c);
        }
        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        short[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }

        ShortArrayList.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    /**
     * Appends all of the elements in the specified ShortArrayList to the end of
     * this list, in the order that they are returned by the
     * specified ShortArrayList's Iterator.  The behavior of this operation is
     * undefined if the specified ShortArrayList is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the specified ShortArrayList is this list, and this
     * list is nonempty.)
     *
     * @param c ShortArrayList containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws java.lang.UnsupportedOperationException if the {@code addAll} operation
     *                                                 is not supported by this list
     * @throws java.lang.ClassCastException            if the class of an element of the specified
     *                                                 collection prevents it from being added to this list
     * @throws java.lang.NullPointerException          if the specified collection contains one
     *                                                 or more null elements and this list does not permit null
     *                                                 elements, or if the specified collection is null
     * @throws java.lang.IllegalArgumentException      if some property of an element of the
     *                                                 specified collection prevents it from being added to this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index > size()})
     */
    public boolean addAll(ShortArrayList c) {
        final short[] a = c.getElementData();
        modCount++;
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        short[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    /**
     * Appends all of the elements in the specified short[] to the end of
     * this list.
     * <p>
     * The behavior of this operation is
     * undefined if the specified short[] is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the specified short[] is this list's elementData, and this list is nonempty.)
     *
     * @param shortArray collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws java.lang.UnsupportedOperationException if the {@code addAll} operation
     *                                                 is not supported by this list
     * @throws java.lang.ClassCastException            if the class of an element of the specified
     *                                                 collection prevents it from being added to this list
     * @throws java.lang.NullPointerException          if the specified collection contains one
     *                                                 or more null elements and this list does not permit null
     *                                                 elements, or if the specified collection is null
     * @throws java.lang.IllegalArgumentException      if some property of an element of the
     *                                                 specified collection prevents it from being added to this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index > size()})
     */
    public boolean addAll(short[] shortArray) {
        return this.addAll(new ShortArrayList(shortArray));
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
    public boolean addAll(int index, Collection<? extends Short> c) {
        if (c instanceof ShortArrayList) {
            return this.addAll(index, (ShortArrayList) c);
        }
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        short[] elementData;
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
        ShortArrayList.arraycopy(a, 0, elementData, index, numNew);
        size = s + numNew;
        return true;
    }

    /**
     * Inserts all of the elements in the specified ShortArrayList into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     ShortArrayList containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws java.lang.UnsupportedOperationException if the {@code addAll} operation
     *                                                 is not supported by this list
     * @throws java.lang.ClassCastException            if the class of an element of the specified
     *                                                 collection prevents it from being added to this list
     * @throws java.lang.NullPointerException          if the specified collection contains one
     *                                                 or more null elements and this list does not permit null
     *                                                 elements, or if the specified collection is null
     * @throws java.lang.IllegalArgumentException      if some property of an element of the
     *                                                 specified collection prevents it from being added to this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index > size()})
     */
    public boolean addAll(int index, ShortArrayList c) {
        rangeCheckForAdd(index);
        final short[] a = c.getElementData();
        modCount++;
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        short[] elementData;
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
     * Inserts all of the elements in the specified short[] into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     short[] containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws java.lang.UnsupportedOperationException if the {@code addAll} operation
     *                                                 is not supported by this list
     * @throws java.lang.ClassCastException            if the class of an element of the specified
     *                                                 collection prevents it from being added to this list
     * @throws java.lang.NullPointerException          if the specified collection contains one
     *                                                 or more null elements and this list does not permit null
     *                                                 elements, or if the specified collection is null
     * @throws java.lang.IllegalArgumentException      if some property of an element of the
     *                                                 specified collection prevents it from being added to this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index > size()})
     */
    public boolean addAll(int index, short[] c) {
        return this.addAll(index, new ShortArrayList(c));
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
     * @param es an array of {@link short} objects.
     * @param lo a int.
     * @param hi a int.
     */
    public void shiftTailOverGap(short[] es, int lo, int hi) {
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
        final short[] es = elementData;
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
            for (short e; r < end; r++) {
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
     * Saves the state of the {@code ShortArrayList} instance to a stream
     * (that is, serializes it).
     *
     * @param s the stream
     * @throws java.io.IOException if an I/O error occurs
     * @serialData The length of the array backing the {@code ShortArrayList}
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
            s.writeShort(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * Reconstitutes the {@code ShortArrayList} instance from a stream (that is,
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
            short[] elements = new short[size];

            // Read in all elements in the proper order.
            for (int i = 0; i < size; i++) {
                elements[i] = s.readShort();
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
     * returned by an initial call to {@link ShortListIterator#next next}.
     * An initial call to {@link ShortListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     */
    @Override
    public ShortListIterator listIterator(int index) {
        rangeCheckForAdd(index);
        return new ShortArrayList.ListItr(index);
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
    public ShortListIterator listIterator() {
        return new ShortArrayList.ListItr(0);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     */
    @Override
    public Iterator<Short> iterator() {
        return new ShortArrayList.Itr();
    }

    /**
     * An optimized version of AbstractList.Itr
     */
    private class Itr implements ShortIterator {
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
        public short nextPrimitive() {
            checkForComodification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            short[] elementData = ShortArrayList.this.elementData;
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
                ShortArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super Short> action) {
            Objects.requireNonNull(action);
            final int size = ShortArrayList.this.size;
            int i = cursor;
            if (i < size) {
                final short[] es = elementData;
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
    private class ListItr extends ShortArrayList.Itr implements ShortListIterator {
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
        public short previousPrimitive() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            short[] elementData = ShortArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i;
            return elementData[lastRet = i];
        }

        @Override
        public void setPrimitive(short e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                ShortArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void addPrimitive(short e) {
            checkForComodification();

            try {
                int i = cursor;
                ShortArrayList.this.add(i, e);
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
    public List<Short> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new ShortArrayList.ShortSubList(this, fromIndex, toIndex);
    }

    private static class ShortSubList extends AbstractList<Short> implements RandomAccess {
        private final ShortArrayList root;
        private final ShortArrayList.ShortSubList parent;
        private final int offset;
        private int size;

        /**
         * Constructs a sublist of an arbitrary ShortArrayList.
         */
        public ShortSubList(ShortArrayList root, int fromIndex, int toIndex) {
            this.root = root;
            this.parent = null;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        /**
         * Constructs a sublist of another SubList.
         */
        private ShortSubList(ShortArrayList.ShortSubList parent, int fromIndex, int toIndex) {
            this.root = parent.root;
            this.parent = parent;
            this.offset = parent.offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        public short setPrimitive(int index, short element) {
            checkIndex(index, size);
            checkForComodification();
            short oldValue = root.elementData(offset + index);
            root.elementData[offset + index] = element;
            return oldValue;
        }

        @Override
        public Short get(int index) {
            return this.getPrimitive(index);
        }

        public short getPrimitive(int index) {
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
        public void add(int index, Short element) {
            this.addPrimitive(index, element);
        }

        public void addPrimitive(int index, short element) {
            rangeCheckForAdd(index);
            checkForComodification();
            root.add(offset + index, element);
            updateSizeAndModCount(1);
        }

        @Override
        public Short remove(int index) {
            return this.removePrimitive(index);
        }

        public short removePrimitive(int index) {
            checkIndex(index, size);
            checkForComodification();
            short result = root.remove(offset + index);
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
        public boolean addAll(Collection<? extends Short> c) {
            return addAll(this.size, c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends Short> c) {
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
        public void replaceAll(UnaryOperator<Short> operator) {
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
        public boolean removeIf(Predicate<? super Short> filter) {
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

        public short[] toArrayPrimitive() {
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
            ShortArrayList.arraycopy(root.elementData, offset, a, 0, size);
            if (a.length > size) {
                a[size] = null;
            }
            return a;
        }

        public short[] toArrayPrimitive(short[] a) {
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

        public int indexOf(short o) {
            return this.indexOfPrimitive(o);
        }

        public int indexOfPrimitive(short o) {
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

        public int lastIndexOf(short o) {
            return this.lastIndexOfPrimitive(o);
        }

        public int lastIndexOfPrimitive(short o) {
            int index = root.lastIndexOfRangePrimitive(o, offset, offset + size);
            checkForComodification();
            return index >= 0 ? index - offset : -1;
        }

        @Override
        public boolean contains(Object o) {
            return indexOf(o) >= 0;
        }

        public boolean contains(short o) {
            return this.containsPrimitive(o);
        }

        public boolean containsPrimitive(short o) {
            return indexOfPrimitive(o) >= 0;
        }


        @Override
        public Iterator<Short> iterator() {
            return listIterator();
        }

        @Override
        public ShortListIterator listIterator(int index) {
            checkForComodification();
            rangeCheckForAdd(index);

            return new ShortListIterator() {
                int cursor = index;
                int lastRet = -1;
                int expectedModCount = root.modCount;

                @Override
                public boolean hasNext() {
                    return cursor != ShortArrayList.ShortSubList.this.size;
                }

                @Override
                public Short next() {
                    return nextPrimitive();
                }

                @Override
                public short nextPrimitive() {
                    checkForComodification();
                    int i = cursor;
                    if (i >= ShortArrayList.ShortSubList.this.size) {
                        throw new NoSuchElementException();
                    }
                    short[] elementData = root.elementData;
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
                public Short previous() {
                    return previousPrimitive();
                }

                @Override
                public short previousPrimitive() {
                    checkForComodification();
                    int i = cursor - 1;
                    if (i < 0) {
                        throw new NoSuchElementException();
                    }
                    short[] elementData = root.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    cursor = i;
                    return elementData[offset + (lastRet = i)];
                }

                @Override
                public void forEachRemaining(Consumer<? super Short> action) {
                    Objects.requireNonNull(action);
                    final int size = ShortArrayList.ShortSubList.this.size;
                    int i = cursor;
                    if (i < size) {
                        final short[] es = root.elementData;
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
                        ShortArrayList.ShortSubList.this.remove(lastRet);
                        cursor = lastRet;
                        lastRet = -1;
                        expectedModCount = root.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public void set(Short e) {
                    setPrimitive(e);
                }

                @Override
                public void setPrimitive(short e) {
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
                public void add(Short e) {
                    addPrimitive(e);
                }

                @Override
                public void addPrimitive(short e) {
                    checkForComodification();

                    try {
                        int i = cursor;
                        ShortArrayList.ShortSubList.this.add(i, e);
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
        public List<Short> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new ShortArrayList.ShortSubList(this, fromIndex, toIndex);
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
            ShortArrayList.ShortSubList slist = this;
            do {
                slist.size += sizeChange;
                slist.modCount = root.modCount;
                slist = slist.parent;
            } while (slist != null);
        }

        @Override
        public Spliterator<Short> spliterator() {
            checkForComodification();

            // ShortArrayListSpliterator not used here due to late-binding
            return new Spliterator<Short>() {
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
                public ShortArrayList.ShortArrayListSpliterator trySplit() {
                    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
                    // ShortArrayListSpliterator can be used here as the source is already bound
                    return (lo >= mid) ? null : // divide range in half unless too small
                            root.new ShortArrayListSpliterator(lo, index = mid, expectedModCount);
                }

                @Override
                public boolean tryAdvance(Consumer<? super Short> action) {
                    Objects.requireNonNull(action);
                    int hi = getFence(), i = index;
                    if (i < hi) {
                        index = i + 1;
                        short e = root.elementData[i];
                        action.accept(e);
                        if (root.modCount != expectedModCount) {
                            throw new ConcurrentModificationException();
                        }
                        return true;
                    }
                    return false;
                }

                @Override
                public void forEachRemaining(Consumer<? super Short> action) {
                    Objects.requireNonNull(action);
                    int i, hi, mc; // hoist accesses and checks from loop
                    ShortArrayList lst = root;
                    short[] a;
                    if ((a = lst.elementData) != null) {
                        if ((hi = fence) < 0) {
                            mc = modCount;
                            hi = offset + size;
                        } else {
                            mc = expectedModCount;
                        }
                        if ((i = index) >= 0 && (index = hi) <= a.length) {
                            for (; i < hi; ++i) {
                                short e = a[i];
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
    public void forEach(Consumer<? super Short> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        final short[] es = elementData;
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
    public Spliterator<Short> spliterator() {
        return new ShortArrayList.ShortArrayListSpliterator(0, -1, 0);
    }

    /**
     * Index-based split-by-two, lazily initialized Spliterator
     */
    final class ShortArrayListSpliterator implements Spliterator<Short> {

        /*
         * If ShortArrayLists were immutable, or structurally immutable (no
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
        ShortArrayListSpliterator(int origin, int fence, int expectedModCount) {
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
        public ShortArrayList.ShortArrayListSpliterator trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : // divide range in half unless too small
                    new ShortArrayList.ShortArrayListSpliterator(lo, index = mid, expectedModCount);
        }

        @Override
        public boolean tryAdvance(Consumer<? super Short> action) {
            if (action == null) {
                throw new NullPointerException();
            }
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                short e = elementData[i];
                action.accept(e);
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }

        @Override
        public void forEachRemaining(Consumer<? super Short> action) {
            int i, hi, mc; // hoist accesses and checks from loop
            short[] a;
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
                        short e = a[i];
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
    public boolean removeIf(Predicate<? super Short> filter) {
        return removeIf(filter, 0, size);
    }

    /**
     * Removes all elements satisfying the given predicate, from index
     * i (inclusive) to index end (exclusive).
     */
    boolean removeIf(Predicate<? super Short> filter, int i, final int end) {
        Objects.requireNonNull(filter);
        int expectedModCount = modCount;
        final short[] es = elementData;
        // Optimize for initial run of survivors
        for (; i < end && !filter.test(elementAt(es, i)); i++) {
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
    public void replaceAll(UnaryOperator<Short> operator) {
        replaceAllRange(operator, 0, size);
        modCount++;
    }

    private void replaceAllRange(UnaryOperator<Short> operator, int i, int end) {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final short[] es = elementData;
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
    public void sort(Comparator<? super Short> c) {
        if (!(c instanceof ShortComparator)) {
            throw new IllegalArgumentException("For more performance, this class only accept ShortComparator as " +
                    "comparator.");
        }
        this.sort((ShortComparator) c);
    }

    /**
     * <p>sort.</p>
     *
     * @param c a {@link com.xenoamess.commons.collections.list.primitive_array_lists.ShortComparator} object.
     */
    public void sort(ShortComparator c) {
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
