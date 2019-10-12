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

package com.xenoamess.commons.primitive.collections;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.iterators.CharIterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * This class provides a skeletal implementation of the {@code Collection}
 * interface, to minimize the effort required to implement this interface.
 * <p>
 * To implement an unmodifiable collection, the programmer needs only to
 * extend this class and provide implementations for the {@code iterator} and
 * {@code size} methods.  (The iterator returned by the {@code iterator}
 * method must implement {@code hasNext} and {@code next}.)
 * <p>
 * To implement a modifiable collection, the programmer must additionally
 * override this class's {@code add} method (which otherwise throws an
 * {@code UnsupportedOperationException}), and the iterator returned by the
 * {@code iterator} method must additionally implement its {@code remove}
 * method.
 * <p>
 * The programmer should generally provide a void (no argument) and
 * {@code Collection} constructor, as per the recommendation in the
 * {@code Collection} interface specification.
 * <p>
 * The documentation for each non-abstract method in this class describes its
 * implementation in detail.  Each of these methods may be overridden if
 * the collection being implemented admits a more efficient implementation.
 * <p>
 * This class is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @version 0.8.0
 * @see Collection
 * @see java.util.AbstractCollection
 * @since 1.2
 */
public interface AbstractCharCollection extends CharCollection,
        Primitive {

    // Query Operations

    /**
     * {@inheritDoc}
     * <p>
     * Returns an iterator over the elements contained in this collection.
     */
    @Override
    CharIterator iterator();

    /**
     * {@inheritDoc}
     */
    @Override
    int size();

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns {@code size() == 0}.
     */
    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over the elements in the collection,
     * checking each element in turn for equality with the specified element.
     */
    @Override
    default boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Character)) {
            return false;
        }
        return this.containsPrimitive((Character) o);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over the elements in the collection,
     * checking each element in turn for equality with the specified element.
     */
    @Override
    default boolean containsPrimitive(char o) {
        CharIterator it = iterator();
        while (it.hasNext()) {
            if (o == it.nextPrimitive()) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns an array containing all the elements
     * returned by this collection's iterator, in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * The length of the returned array is equal to the number of elements
     * returned by the iterator, even if the size of this collection changes
     * during iteration, as might happen if the collection permits
     * concurrent modification during iteration.  The {@code size} method is
     * called only as an optimization hint; the correct result is returned
     * even if the iterator returns a different number of elements.
     *
     * This method is equivalent to:
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray();
     * }</pre>
     */
    @Override
    default Object[] toArray() {
        // Estimate size of array; be prepared to see more or fewer elements
        Object[] r = new Object[size()];
        CharIterator it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) {
                // fewer elements than expected
                return Arrays.copyOf(r, i);
            }
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * {@inheritDoc}
     *
     * @return an array of {@link char} objects.
     * @implSpec This implementation returns an array containing all the elements
     * returned by this collection's iterator, in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * The length of the returned array is equal to the number of elements
     * returned by the iterator, even if the size of this collection changes
     * during iteration, as might happen if the collection permits
     * concurrent modification during iteration.  The {@code size} method is
     * called only as an optimization hint; the correct result is returned
     * even if the iterator returns a different number of elements.
     *
     * This method is equivalent to:
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray();
     * }</pre>
     */
    @Override
    default char[] toArrayPrimitive() {
        // Estimate size of array; be prepared to see more or fewer elements
        char[] r = new char[size()];
        CharIterator it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) {
                // fewer elements than expected
                return Arrays.copyOf(r, i);
            }
            r[i] = it.nextPrimitive();
        }
        return it.hasNext() ? finishToArrayPrimitive(r, it) : r;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns an array containing all the elements
     * returned by this collection's iterator in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * If the number of elements returned by the iterator is too large to
     * fit into the specified array, then the elements are returned in a
     * newly allocated array with length equal to the number of elements
     * returned by the iterator, even if the size of this collection
     * changes during iteration, as might happen if the collection permits
     * concurrent modification during iteration.  The {@code size} method is
     * called only as an optimization hint; the correct result is returned
     * even if the iterator returns a different number of elements.
     *
     * This method is equivalent to:
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray(a);
     * }</pre>
     */
    @Override
    @SuppressWarnings("unchecked")
    default <T> T[] toArray(T[] a) {
        // Estimate size of array; be prepared to see more or fewer elements
        int size = size();
        T[] r = a.length >= size ? a :
                (T[]) java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), size);
        CharIterator it = iterator();

        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) { // fewer elements than expected
                if (a == r) {
                    r[i] = null; // null-terminate
                } else if (a.length < i) {
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            r[i] = (T) it.next();
        }
        // more elements than expected
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * {@inheritDoc}
     *
     * @param a an array of {@link char} objects.
     * @return an array of {@link char} objects.
     * @throws java.lang.ArrayStoreException  {@inheritDoc}
     * @throws java.lang.NullPointerException {@inheritDoc}
     * @implSpec This implementation returns an array containing all the elements
     * returned by this collection's iterator in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * If the number of elements returned by the iterator is too large to
     * fit into the specified array, then the elements are returned in a
     * newly allocated array with length equal to the number of elements
     * returned by the iterator, even if the size of this collection
     * changes during iteration, as might happen if the collection permits
     * concurrent modification during iteration.  The {@code size} method is
     * called only as an optimization hint; the correct result is returned
     * even if the iterator returns a different number of elements.
     *
     * This method is equivalent to:
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray(a);
     * }</pre>
     */
    @Override
    default char[] toArrayPrimitive(char[] a) {
        // Estimate size of array; be prepared to see more or fewer elements
        int size = size();
        char[] r = a.length >= size ? a : new char[size];
        CharIterator it = iterator();

        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) { // fewer elements than expected
                if (a == r) {
                    r[i] = Primitive.CHAR_DEFAULT; // null-terminate
                } else if (a.length < i) {
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = Primitive.CHAR_DEFAULT;
                    }
                }
                return a;
            }
            r[i] = it.nextPrimitive();
        }
        // more elements than expected
        return it.hasNext() ? finishToArrayPrimitive(r, it) : r;
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Reallocates the array being used within toArray when the iterator
     * returned more elements than expected, and finishes filling it from
     * the iterator.
     *
     * @param r   the array, replete with previously stored elements
     * @param it  the in-progress iterator over this collection
     * @param <T> a T object.
     * @return array containing the elements in the given array, plus any
     * further elements returned by the iterator, trimmed to size
     */
    @SuppressWarnings("unchecked")
    static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            if (i == cap) {
                int newCap = cap + (cap >> 1) + 1;
                // overflow-conscious code
                if (newCap - MAX_ARRAY_SIZE > 0) {
                    newCap = hugeCapacity(cap + 1);
                }
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T) it.next();
        }
        // trim if overallocated
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }


    /**
     * Reallocates the array being used within toArray when the iterator
     * returned more elements than expected, and finishes filling it from
     * the iterator.
     *
     * @param r  the array, replete with previously stored elements
     * @param it the in-progress iterator over this collection
     * @return array containing the elements in the given array, plus any
     * further elements returned by the iterator, trimmed to size
     */
    static char[] finishToArrayPrimitive(char[] r, CharIterator it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            if (i == cap) {
                int newCap = cap + (cap >> 1) + 1;
                // overflow-conscious code
                if (newCap - MAX_ARRAY_SIZE > 0) {
                    newCap = hugeCapacity(cap + 1);
                }
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = it.nextPrimitive();
        }
        // trim if overallocated
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    /**
     * hugeCapacity.</p>
     *
     * @param minCapacity a int.
     * @return a int.
     */
    static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
        {
            throw new OutOfMemoryError
                    ("Required array size too large");
        }
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    // Modification Operations

    /**
     * {@inheritDoc}
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @implSpec This implementation iterates over the collection looking for the
     * specified element.  If it finds the element, it removes the element
     * from the collection using the iterator's remove method.
     *
     * Note that this implementation throws an
     * {@code UnsupportedOperationException} if the iterator returned by this
     * collection's iterator method does not implement the {@code remove}
     * method and this collection contains the specified object.
     */
    @Override
    default boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Character)) {
            return false;
        }
        return this.removeByContentPrimitive((Character) o);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over the collection looking for the
     * specified element.  If it finds the element, it removes the element
     * from the collection using the iterator's remove method.
     *
     * Note that this implementation throws an
     * {@code UnsupportedOperationException} if the iterator returned by this
     * collection's iterator method does not implement the {@code remove}
     * method and this collection contains the specified object.
     */
    @Override
    default boolean removeByContentPrimitive(char o) {
        CharIterator it = iterator();
        while (it.hasNext()) {
            if (o == it.nextPrimitive()) {
                it.remove();
                return true;
            }
        }
        return false;
    }


    // Bulk Operations

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over the specified collection,
     * checking each element returned by the iterator in turn to see
     * if it's contained in this collection.  If all elements are so
     * contained {@code true} is returned, otherwise {@code false}.
     * @see #contains(Object)
     */
    @Override
    default boolean containsAll(Collection<?> c) {
        if (c instanceof CharCollection) {
            CharCollection cCharCollection = (CharCollection) c;
            CharIterator cCharCollectionIterator = cCharCollection.iterator();
            while (cCharCollectionIterator.hasNext()) {
                if (!containsPrimitive(cCharCollectionIterator.nextPrimitive())) {
                    return false;
                }
            }
        } else {
            for (Object e : c) {
                if (!contains(e)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over the specified collection, and adds
     * each object returned by the iterator to this collection, in turn.
     *
     * Note that this implementation will throw an
     * {@code UnsupportedOperationException} unless {@code add} is
     * overridden (assuming the specified collection is non-empty).
     * @see #add(Character)
     */
    @Override
    default boolean addAll(Collection<? extends Character> c) {
        boolean modified = false;
        if (c instanceof CharCollection) {
            CharCollection cCharCollection = (CharCollection) c;
            CharIterator cCharCollectionIterator = cCharCollection.iterator();
            while (cCharCollectionIterator.hasNext()) {
                if (add(cCharCollectionIterator.nextPrimitive())) {
                    modified = true;
                }
            }
        } else {
            for (Character e : c) {
                if (add(e)) {
                    modified = true;
                }
            }
        }
        return modified;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over this collection, checking each
     * element returned by the iterator in turn to see if it's contained
     * in the specified collection.  If it's so contained, it's removed from
     * this collection with the iterator's {@code remove} method.
     *
     * Note that this implementation will throw an
     * {@code UnsupportedOperationException} if the iterator returned by the
     * {@code iterator} method does not implement the {@code remove} method
     * and this collection contains one or more elements in common with the
     * specified collection.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    default boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        CharIterator it = iterator();
        if (c instanceof CharCollection) {
            CharCollection cCharCollection = (CharCollection) c;
            while (it.hasNext()) {
                if (cCharCollection.containsPrimitive(it.nextPrimitive())) {
                    it.remove();
                    modified = true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
        }
        return modified;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over this collection, checking each
     * element returned by the iterator in turn to see if it's contained
     * in the specified collection.  If it's not so contained, it's removed
     * from this collection with the iterator's {@code remove} method.
     *
     * Note that this implementation will throw an
     * {@code UnsupportedOperationException} if the iterator returned by the
     * {@code iterator} method does not implement the {@code remove} method
     * and this collection contains one or more elements not present in the
     * specified collection.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    default boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        CharIterator it = iterator();
        if (c instanceof CharCollection) {
            CharCollection cCharCollection = (CharCollection) c;
            while (it.hasNext()) {
                if (!cCharCollection.containsPrimitive(it.nextPrimitive())) {
                    it.remove();
                    modified = true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (!c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
        }
        return modified;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over this collection, removing each
     * element using the {@code Iterator.remove} operation.  Most
     * implementations will probably choose to override this method for
     * efficiency.
     *
     * Note that this implementation will throw an
     * {@code UnsupportedOperationException} if the iterator returned by this
     * collection's {@code iterator} method does not implement the
     * {@code remove} method and this collection is non-empty.
     */
    @Override
    default void clear() {
        CharIterator it = iterator();
        while (it.hasNext()) {
            it.nextPrimitive();
            it.remove();
        }
    }


    //  String conversion

    /**
     * Returns a string representation of this collection.  The string
     * representation consists of a list of the collection's elements in the
     * order they are returned by its iterator, enclosed in square brackets
     * ({@code "[]"}).  Adjacent elements are separated by the characters
     * {@code ", "} (comma and space).  Elements are converted to strings as
     * by {@link java.lang.String#valueOf(Object)}.
     *
     * @param abstractCharCollection a {@link com.xenoamess.commons.primitive.collections.AbstractCharCollection}
     *                               object.
     * @return a string representation of this collection
     */
    static String toString(AbstractCharCollection abstractCharCollection) {
        CharIterator it = abstractCharCollection.iterator();
        if (!it.hasNext()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            char e = it.nextPrimitive();
            sb.append(e);
            if (!it.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }
}
