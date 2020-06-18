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

package com.xenoamess.commons.primitive.collections.lists;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.collections.IntCollection;
import com.xenoamess.commons.primitive.comparators.IntComparator;
import com.xenoamess.commons.primitive.functions.IntUnaryOperator;
import com.xenoamess.commons.primitive.iterators.IntListIterator;
import com.xenoamess.commons.primitive.iterators.IntRandomAccessSpliterator;
import com.xenoamess.commons.primitive.iterators.IntSpliterator;
import com.xenoamess.commons.primitive.iterators.IntSpliterators;
import com.xenoamess.commonx.java.util.Arraysx;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * An ordered collection (also known as a <i>sequence</i>).  The user of this
 * interface has precise control over where in the list each element is
 * inserted.  The user can access elements by their integer index (position in
 * the list), and search for elements in the list.
 * <p>
 * Unlike sets, lists typically allow duplicate elements.  More formally,
 * lists typically allow pairs of elements {@code e1} and {@code e2}
 * such that {@code e1.equals(e2)}, and they typically allow multiple
 * null elements if they allow null elements at all.  It is not inconceivable
 * that someone might wish to implement a list that prohibits duplicates, by
 * throwing runtime exceptions when the user attempts to insert them, but we
 * expect this usage to be rare.
 * <p>
 * The {@code List} interface places additional stipulations, beyond those
 * specified in the {@code Collection} interface, on the contracts of the
 * {@code iterator}, {@code add}, {@code remove}, {@code equals}, and
 * {@code hashCode} methods.  Declarations for other inherited methods are
 * also included here for convenience.
 * <p>
 * The {@code List} interface provides four methods for positional (indexed)
 * access to list elements.  Lists (like Java arrays) are zero based.  Note
 * that these operations may execute in time proportional to the index value
 * for some implementations (the {@code IntLinkedList} class, for
 * example). Thus, iterating over the elements in a list is typically
 * preferable to indexing through it if the caller does not know the
 * implementation.
 * <p>
 * The {@code List} interface provides a special iterator, called a
 * {@code ListIterator}, that allows element insertion and replacement, and
 * bidirectional access in addition to the normal operations that the
 * {@code Iterator} interface provides.  A method is provided to obtain a
 * list iterator that starts at a specified position in the list.
 * <p>
 * The {@code List} interface provides two methods to search for a specified
 * object.  From a performance standpoint, these methods should be used with
 * caution.  In many implementations they will perform costly linear
 * searches.
 * <p>
 * The {@code List} interface provides two methods to efficiently insert and
 * remove multiple elements at an arbitrary point in the list.
 * <p>
 * Note: While it is permissible for lists to contain themselves as elements,
 * extreme caution is advised: the {@code equals} and {@code hashCode}
 * methods are no longer well defined on such a list.
 *
 * <p>Some list implementations have restrictions on the elements that
 * they may contain.  For example, some implementations prohibit null elements,
 * and some have restrictions on the types of their elements.  Attempting to
 * add an ineligible element throws an unchecked exception, typically
 * {@code NullPointerException} or {@code ClassCastException}.  Attempting
 * to query the presence of an ineligible element may throw an exception,
 * or it may simply return false; some implementations will exhibit the former
 * behavior and some will exhibit the latter.  More generally, attempting an
 * operation on an ineligible element whose completion would not result in
 * the insertion of an ineligible element into the list may throw an
 * exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @author XenoAmess
 * @version 0.8.0
 * @see Collection
 * @see Set
 * @see ArrayList
 * @see LinkedList
 * @see Vector
 * @see Arrays#asList(Object[])
 * @see Collections#nCopies(int, Object)
 * @see Collections#EMPTY_LIST
 * @see AbstractList
 * @see AbstractIntSequentialList
 * @see List
 * @since 1.2
 */
public interface IntList extends List<Integer>, IntCollection, Primitive {
    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     * @see Arrays#asList(Object[])
     */
    @Override
    int[] toArrayPrimitive();

    /**
     * {@inheritDoc}
     * <p>
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     */
    @Override
    default boolean contains(Object o) {
        return IntCollection.super.contains(o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes a single instance of the specified element from this
     * collection, if it is present (optional operation).  More formally,
     * removes an element {@code e} such that
     * {@code Objects.equals(o, e)}, if
     * this collection contains one or more such elements.  Returns
     * {@code true} if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     */
    @Override
    default boolean remove(Object o) {
        return IntCollection.super.remove(o);
    }


    /**
     * {@inheritDoc}
     * <p>
     * Ensures that this collection contains the specified element (optional
     * operation).  Returns {@code true} if this collection changed as a
     * result of the call.  (Returns {@code false} if this collection does
     * not permit duplicates and already contains the specified element.)
     * <p>
     * Collections that support this operation may place limitations on what
     * elements may be added to this collection.  In particular, some
     * collections will refuse to add {@code null} elements, and others will
     * impose restrictions on the type of elements that may be added.
     * Collection classes should clearly specify in their documentation any
     * restrictions on what elements may be added.
     * <p>
     * If a collection refuses to add a particular element for any reason
     * other than that it already contains the element, it <i>must</i> throw
     * an exception (rather than returning {@code false}).  This preserves
     * the invariant that a collection always contains the specified element
     * after this call returns.
     */
    @Override
    default boolean add(Integer e) {
        return IntCollection.super.add(e);
    }


    // Modification Operations

//    /**
//     * Appends the specified element to the end of this list (optional
//     * operation).
//     *
//     * <p>Lists that support this operation may place limitations on what
//     * elements may be added to this list.  In particular, some
//     * lists will refuse to add null elements, and others will impose
//     * restrictions on the type of elements that may be added.  List
//     * classes should clearly specify in their documentation any restrictions
//     * on what elements may be added.
//     *
//     * @param e element to be appended to this list
//     * @return {@code true} (as specified by {@link Collection#add})
//     * @throws UnsupportedOperationException if the {@code add} operation
//     *                                       is not supported by this list
//     * @throws ClassCastException            if the class of the specified element
//     *                                       prevents it from being added to this list
//     * @throws NullPointerException          if the specified element is null and this
//     *                                       list does not permit null elements
//     * @throws IllegalArgumentException      if some property of this element
//     *                                       prevents it from being added to this list
//     */
//    @Override
//    default boolean add(Integer e) {
//        return this.addPrimitive(e);
//    }
//
//    /**
//     * Primitive replacement of add(Integer e)
//     *
//     * @param e element to be appended to this list
//     * @return {@code true} (as specified by {@link Collection#add})
//     * @throws UnsupportedOperationException if the {@code add} operation
//     *                                       is not supported by this list
//     * @throws ClassCastException            if the class of the specified element
//     *                                       prevents it from being added to this list
//     * @throws NullPointerException          if the specified element is null and this
//     *                                       list does not permit null elements
//     * @throws IllegalArgumentException      if some property of this element
//     *                                       prevents it from being added to this list
//     * @implSpec This implementation calls {@code add(size(), e)}.
//     *
//     * <p>Note that this implementation throws an
//     * {@code UnsupportedOperationException} unless
//     * {@link #add(int, Object) add(int, E)} is overridden.
//     * @see #add(Integer e)
//     */
//    @Override
//    default boolean add(int e) {
//        return this.addPrimitive(e);
//    }
//
//    /**
//     * Primitive replacement of add(Integer e)
//     *
//     * @param e element to be appended to this list
//     * @return {@code true} (as specified by {@link Collection#add})
//     * @throws UnsupportedOperationException if the {@code add} operation
//     *                                       is not supported by this list
//     * @throws ClassCastException            if the class of the specified element
//     *                                       prevents it from being added to this list
//     * @throws NullPointerException          if the specified element is null and this
//     *                                       list does not permit null elements
//     * @throws IllegalArgumentException      if some property of this element
//     *                                       prevents it from being added to this list
//     * @implSpec This implementation calls {@code add(size(), e)}.
//     *
//     * <p>Note that this implementation throws an
//     * {@code UnsupportedOperationException} unless
//     * {@link #add(int, Object) add(int, E)} is overridden.
//     * @see #add(Integer e)
//     */
//    @Override
//    boolean addPrimitive(int e);

//    /**
////     * Removes the first occurrence of the specified element from this list,
////     * if it is present (optional operation).  If this list does not contain
////     * the element, it is unchanged.  More formally, removes the element with
////     * the lowest index {@code i} such that
////     * {@code Objects.equals(o, get(i))}
////     * (if such an element exists).  Returns {@code true} if this list
////     * contained the specified element (or equivalently, if this list changed
////     * as a result of the call).
////     *
////     * @param o element to be removed from this list, if present
////     * @return {@code true} if this list contained the specified element
////     * @throws ClassCastException            if the type of the specified element
////     *                                       is incompatible with this list
////     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
////     * @throws NullPointerException          if the specified element is null and this
////     *                                       list does not permit null elements
////     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
////     * @throws UnsupportedOperationException if the {@code remove} operation
////     *                                       is not supported by this list
////     */
////    @Override
////    default boolean remove(Object o) {
////        if (o == null) {
////            return false;
////        }
////        if (!(o instanceof Integer)) {
////            return false;
////        }
////        return this.removePrimitive((Integer) o);
////    }
////
////    /**
////     * Primitive replacement of {@code remove(Object o)}
////     *
////     * @param o element to be removed from this list, if present
////     * @return {@code true} if this list contained the specified element
////     * @throws ClassCastException            if the type of the specified element
////     *                                       is incompatible with this list
////     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
////     * @throws NullPointerException          if the specified element is null and this
////     *                                       list does not permit null elements
////     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
////     * @throws UnsupportedOperationException if the {@code remove} operation
////     *                                       is not supported by this list
////     * @see #remove(Object o)
////     */
////    @Override
////    default boolean remove(int o) {
////        return this.removePrimitive(o);
////    }
////
////    /**
////     * Primitive replacement of {@code remove(Object o)}
////     *
////     * @param o element to be removed from this list, if present
////     * @return {@code true} if this list contained the specified element
////     * @throws ClassCastException            if the type of the specified element
////     *                                       is incompatible with this list
////     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
////     * @throws NullPointerException          if the specified element is null and this
////     *                                       list does not permit null elements
////     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
////     * @throws UnsupportedOperationException if the {@code remove} operation
////     *                                       is not supported by this list
////     * @see #remove(Object o)
////     */
////    @Override
////    boolean removePrimitive(int o);


    // Bulk Modification Operations

//    /**
//     * Returns {@code true} if this list contains all of the elements of the
//     * specified collection.
//     *
//     * @param c collection to be checked for containment in this list
//     * @return {@code true} if this list contains all of the elements of the
//     * specified collection
//     * @throws ClassCastException   if the types of one or more elements
//     *                              in the specified collection are incompatible with this
//     *                              list
//     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
//     * @throws NullPointerException if the specified collection contains one
//     *                              or more null elements and this list does not permit null
//     *                              elements
//     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
//     *                              or if the specified collection is null
//     * @see #contains(Object)
//     */
//    @Override
//    boolean containsAll(Collection<?> c);
//    /**
//     * Appends all of the elements in the specified collection to the end of
//     * this list, in the order that they are returned by the specified
//     * collection's iterator (optional operation).  The behavior of this
//     * operation is undefined if the specified collection is modified while
//     * the operation is in progress.  (Note that this will occur if the
//     * specified collection is this list, and it's nonempty.)
//     *
//     * @param c collection containing elements to be added to this list
//     * @return {@code true} if this list changed as a result of the call
//     * @throws UnsupportedOperationException if the {@code addAll} operation
//     *                                       is not supported by this list
//     * @throws ClassCastException            if the class of an element of the specified
//     *                                       collection prevents it from being added to this list
//     * @throws NullPointerException          if the specified collection contains one
//     *                                       or more null elements and this list does not permit null
//     *                                       elements, or if the specified collection is null
//     * @throws IllegalArgumentException      if some property of an element of the
//     *                                       specified collection prevents it from being added to this list
//     * @see #add(Object)
//     */
//    boolean addAll(Collection<? extends Integer> c);
//
//    /**
//     * Inserts all of the elements in the specified collection into this
//     * list at the specified position (optional operation).  Shifts the
//     * element currently at that position (if any) and any subsequent
//     * elements to the right (increases their indices).  The new elements
//     * will appear in this list in the order that they are returned by the
//     * specified collection's iterator.  The behavior of this operation is
//     * undefined if the specified collection is modified while the
//     * operation is in progress.  (Note that this will occur if the specified
//     * collection is this list, and it's nonempty.)
//     *
//     * @param index index at which to insert the first element from the
//     *              specified collection
//     * @param c     collection containing elements to be added to this list
//     * @return {@code true} if this list changed as a result of the call
//     * @throws UnsupportedOperationException if the {@code addAll} operation
//     *                                       is not supported by this list
//     * @throws ClassCastException            if the class of an element of the specified
//     *                                       collection prevents it from being added to this list
//     * @throws NullPointerException          if the specified collection contains one
//     *                                       or more null elements and this list does not permit null
//     *                                       elements, or if the specified collection is null
//     * @throws IllegalArgumentException      if some property of an element of the
//     *                                       specified collection prevents it from being added to this list
//     * @throws IndexOutOfBoundsException     if the index is out of range
//     *                                       ({@code index < 0 || index > size()})
//     */
//    boolean addAll(int index, Collection<? extends E> c);
//
//    /**
//     * Removes from this list all of its elements that are contained in the
//     * specified collection (optional operation).
//     *
//     * @param c collection containing elements to be removed from this list
//     * @return {@code true} if this list changed as a result of the call
//     * @throws UnsupportedOperationException if the {@code removeAll} operation
//     *                                       is not supported by this list
//     * @throws ClassCastException            if the class of an element of this list
//     *                                       is incompatible with the specified collection
//     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
//     * @throws NullPointerException          if this list contains a null element and the
//     *                                       specified collection does not permit null elements
//     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
//     *                                       or if the specified collection is null
//     * @see #remove(Object)
//     * @see #contains(Object)
//     */
//    boolean removeAll(Collection<?> c);
//
//    /**
//     * Retains only the elements in this list that are contained in the
//     * specified collection (optional operation).  In other words, removes
//     * from this list all of its elements that are not contained in the
//     * specified collection.
//     *
//     * @param c collection containing elements to be retained in this list
//     * @return {@code true} if this list changed as a result of the call
//     * @throws UnsupportedOperationException if the {@code retainAll} operation
//     *                                       is not supported by this list
//     * @throws ClassCastException            if the class of an element of this list
//     *                                       is incompatible with the specified collection
//     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
//     * @throws NullPointerException          if this list contains a null element and the
//     *                                       specified collection does not permit null elements
//     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
//     *                                       or if the specified collection is null
//     * @see #remove(Object)
//     * @see #contains(Object)
//     */
//    boolean retainAll(Collection<?> c);

    /**
     * {@inheritDoc}
     * <p>
     * Replaces each element of this list with the result of applying the
     * operator to that element.  Errors or runtime exceptions thrown by
     * the operator are relayed to the caller.
     *
     * @implSpec The default implementation is equivalent to, for this {@code list}:
     * <pre>{@code
     *     final ListIterator<E> li = list.listIterator();
     *     while (li.hasNext()) {
     *         li.set(operator.apply(li.next()));
     *     }
     * }</pre>
     * <p>
     * If the list's list-iterator does not support the {@code set} operation
     * then an {@code UnsupportedOperationException} will be thrown when
     * replacing the first element.
     * @since 1.8
     */
    @Override
    default void replaceAll(UnaryOperator<Integer> operator) {
        Objects.requireNonNull(operator);
        final IntListIterator li = this.listIterator();
        if (operator instanceof IntUnaryOperator) {
            IntUnaryOperator operatorIntUnaryOperator = (IntUnaryOperator) operator;
            while (li.hasNext()) {
                li.setPrimitive(operatorIntUnaryOperator.applyPrimitive(li.nextPrimitive()));
            }
        } else {
            while (li.hasNext()) {
                li.set(operator.apply(li.next()));
            }
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.  The sort is <i>stable</i>: this method must not
     * reorder equal elements.
     *
     * <p>All elements in this list must be <i>mutually comparable</i> using the
     * specified comparator (that is, {@code c.compare(e1, e2)} must not throw
     * a {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the list).
     *
     * <p>If the specified comparator is {@code null} then all elements in this
     * list must implement the {@link Comparable} interface and the elements'
     * {@linkplain Comparable natural ordering} should be used.
     *
     * <p>This list must be modifiable, but need not be resizable.
     *
     * @implSpec The default implementation obtains an array containing all elements in
     * this list, sorts the array, and iterates over this list resetting each
     * element from the corresponding position in the array. (This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.)
     * @implNote This implementation is a stable, adaptive, iterative mergesort that
     * requires far fewer than n lg(n) comparisons when the input array is
     * partially sorted, while offering the performance of a traditional
     * mergesort when the input array is randomly ordered.  If the input array
     * is nearly sorted, the implementation requires approximately n
     * comparisons.  Temporary storage requirements vary from a small constant
     * for nearly sorted input arrays to n/2 object references for randomly
     * ordered input arrays.
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
     * @since 1.8
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    default void sort(Comparator<? super Integer> c) {
        if (c instanceof IntComparator) {
            int[] a = this.toArrayPrimitive();
            Arraysx.sort(a, 0, a.length, (IntComparator) c);
            IntListIterator i = this.listIterator();
            for (int e : a) {
                i.nextPrimitive();
                i.setPrimitive(e);
            }
        } else {
            Object[] a = this.toArray();
            Arrays.sort(a, (Comparator) c);
            IntListIterator i = this.listIterator();
            for (Object e : a) {
                i.nextPrimitive();
                i.set((Integer) e);
            }
        }
    }

    // Positional Access Operations

    /**
     * {@inheritDoc}
     * <p>
     * Returns the element at the specified position in this list.
     */
    @Override
    default Integer get(int index) {
        return this.getPrimitive(index);
    }

    /**
     * Primitive replacement of get(int index)
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws java.lang.IndexOutOfBoundsException if the index is out of range
     *                                             ({@code index < 0 || index >= size()})
     * @see #get(int index)
     */
    int getPrimitive(int index);

    /**
     * {@inheritDoc}
     * <p>
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     */
    @Override
    default Integer set(int index, Integer element) {
        return this.setPrimitive(index, element);
    }

    /**
     * Primitive replacement of set(int index, Integer element)
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws java.lang.UnsupportedOperationException if the {@code set} operation
     *                                                 is not supported by this list
     * @throws java.lang.ClassCastException            if the class of the specified element
     *                                                 prevents it from being added to this list
     * @throws java.lang.NullPointerException          if the specified element is null and
     *                                                 this list does not permit null elements
     * @throws java.lang.IllegalArgumentException      if some property of the specified
     *                                                 element prevents it from being added to this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index >= size()})
     * @see #set(int index, Integer element)
     */
    default int set(int index, int element) {
        return this.setPrimitive(index, element);
    }

    /**
     * Primitive replacement of set(int index, Integer element)
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws java.lang.UnsupportedOperationException if the {@code set} operation
     *                                                 is not supported by this list
     * @throws java.lang.ClassCastException            if the class of the specified element
     *                                                 prevents it from being added to this list
     * @throws java.lang.NullPointerException          if the specified element is null and
     *                                                 this list does not permit null elements
     * @throws java.lang.IllegalArgumentException      if some property of the specified
     *                                                 element prevents it from being added to this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index >= size()})
     * @see #set(int index, Integer element)
     */
    int setPrimitive(int index, int element);

    /**
     * {@inheritDoc}
     * <p>
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     */
    @Override
    default void add(int index, Integer element) {
        this.addPrimitive(index, element);
    }

    /**
     * Primitive replacement of add(int index, Integer element)
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws java.lang.UnsupportedOperationException if the {@code add} operation
     *                                                 is not supported by this list
     * @throws java.lang.ClassCastException            if the class of the specified element
     *                                                 prevents it from being added to this list
     * @throws java.lang.NullPointerException          if the specified element is null and
     *                                                 this list does not permit null elements
     * @throws java.lang.IllegalArgumentException      if some property of the specified
     *                                                 element prevents it from being added to this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index > size()})
     * @see #add(int index, Integer element)
     */
    default void add(int index, int element) {
        this.addPrimitive(index, element);
    }

    /**
     * Primitive replacement of add(int index, Integer element)
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws java.lang.UnsupportedOperationException if the {@code add} operation
     *                                                 is not supported by this list
     * @throws java.lang.ClassCastException            if the class of the specified element
     *                                                 prevents it from being added to this list
     * @throws java.lang.NullPointerException          if the specified element is null and
     *                                                 this list does not permit null elements
     * @throws java.lang.IllegalArgumentException      if some property of the specified
     *                                                 element prevents it from being added to this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index > size()})
     * @see #add(int index, Integer element)
     */
    void addPrimitive(int index, int element);

    /**
     * {@inheritDoc}
     * <p>
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     */
    @Override
    default Integer remove(int index) {
        return this.removeByIndexPrimitive(index);
    }

    /**
     * Primitive replacement of remove(int index)
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws java.lang.UnsupportedOperationException if the {@code remove} operation
     *                                                 is not supported by this list
     * @throws java.lang.IndexOutOfBoundsException     if the index is out of range
     *                                                 ({@code index < 0 || index >= size()})
     * @see #remove(int index)
     */
    int removeByIndexPrimitive(int index);

    // Search Operations

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
    default int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        if (!(o instanceof Integer)) {
            return -1;
        }
        return this.indexOfPrimitive((Integer) o);
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
    default int indexOf(int o) {
        return this.indexOfPrimitive(o);
    }

    /**
     * Primitive replacement of indexOf(Object o)
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws java.lang.ClassCastException   if the type of the specified element
     *                                        is incompatible with this list
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified element is null and this
     *                                        list does not permit null elements
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @see #indexOf(Object o)
     */
    int indexOfPrimitive(int o);


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
    default int lastIndexOf(Object o) {
        if (o == null) {
            return -1;
        }
        if (!(o instanceof Integer)) {
            return -1;
        }
        return this.lastIndexOfPrimitive((Integer) o);
    }

    /**
     * Primitive replacement of lastIndexOf(Object o)
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws java.lang.ClassCastException   if the type of the specified element
     *                                        is incompatible with this list
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified element is null and this
     *                                        list does not permit null elements
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @see #lastIndexOf(Object o)
     */
    default int lastIndexOf(int o) {
        return this.lastIndexOfPrimitive(o);
    }


    /**
     * Primitive replacement of lastIndexOf(Object o)
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws java.lang.ClassCastException   if the type of the specified element
     *                                        is incompatible with this list
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified element is null and this
     *                                        list does not permit null elements
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @see #lastIndexOf(Object o)
     */
    int lastIndexOfPrimitive(int o);


    // List Iterators

    /**
     * {@inheritDoc}
     * <p>
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     */
    @Override
    IntListIterator listIterator();

    /**
     * {@inheritDoc}
     * <p>
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     */
    @Override
    IntListIterator listIterator(int index);

    // View

    /**
     * {@inheritDoc}
     * <p>
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for {@code indexOf} and
     * {@code lastIndexOf}, and all of the algorithms in the
     * {@code Collections} class can be applied to a subList.
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     */
    @Override
    IntList subList(int fromIndex, int toIndex);

    /**
     * {@inheritDoc}
     * <p>
     * Creates a {@link Spliterator} over the elements in this list.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
     * {@link Spliterator#ORDERED}.  Implementations should document the
     * reporting of additional characteristic values.
     *
     * @implSpec The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em>
     * spliterator as follows:
     * <ul>
     * <li>If the list is an instance of {@link java.util.RandomAccess} then the default
     * implementation creates a spliterator that traverses elements by
     * invoking the method {@link java.util.List#get}.  If such invocation results or
     * would result in an {@code IndexOutOfBoundsException} then the
     * spliterator will <em>fail-fast</em> and throw a
     * {@code ConcurrentModificationException}.
     * If the list is also an instance of {@link java.util.AbstractList} then the
     * spliterator will use the list's modCount
     * field to provide additional <em>fail-fast</em> behavior.
     * <li>Otherwise, the default implementation creates a spliterator from the
     * list's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> of the list's iterator.
     * </ul>
     * @implNote The created {@code Spliterator} additionally reports
     * {@link java.util.Spliterator#SUBSIZED}.
     * @since 1.8
     */
    @Override
    default IntSpliterator spliterator() {
        if (this instanceof RandomAccess) {
            return new IntRandomAccessSpliterator(this);
        } else {
            return IntSpliterators.spliterator(this, Spliterator.ORDERED);
        }
    }

}
