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
import com.xenoamess.commons.primitive.functions.LongPredicate;
import com.xenoamess.commons.primitive.iterators.LongIterator;
import com.xenoamess.commons.primitive.iterators.LongSpliterator;
import com.xenoamess.commons.primitive.iterators.LongSpliterators;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * The root interface in the <i>collection hierarchy</i>.  A collection
 * represents a group of objects, known as its <i>elements</i>.  Some
 * collections allow duplicate elements and others do not.  Some are ordered
 * and others unordered.  The JDK does not provide any <i>direct</i>
 * implementations of this interface: it provides implementations of more
 * specific subinterfaces like {@code Set} and {@code List}.  This interface
 * is typically used to pass collections around and manipulate them where
 * maximum generality is desired.
 *
 * <p><i>Bags</i> or <i>multisets</i> (unordered collections that may contain
 * duplicate elements) should implement this interface directly.
 *
 * <p>All general-purpose {@code Collection} implementation classes (which
 * typically implement {@code Collection} indirectly through one of its
 * subinterfaces) should provide two "standard" constructors: a void (no
 * arguments) constructor, which creates an empty collection, and a
 * constructor with a single argument of type {@code Collection}, which
 * creates a new collection with the same elements as its argument.  In
 * effect, the latter constructor allows the user to copy any collection,
 * producing an equivalent collection of the desired implementation type.
 * There is no way to enforce this convention (as interfaces cannot contain
 * constructors) but all of the general-purpose {@code Collection}
 * implementations in the Java platform libraries comply.
 *
 * <p>Certain methods are specified to be
 * <i>optional</i>. If a collection implementation doesn't implement a
 * particular operation, it should define the corresponding method to throw
 * {@code UnsupportedOperationException}. Such methods are marked "optional
 * operation" in method specifications of the collections interfaces.
 *
 * <p><a id="optional-restrictions"></a>Some collection implementations
 * have restrictions on the elements that they may contain.
 * For example, some implementations prohibit null elements,
 * and some have restrictions on the types of their elements.  Attempting to
 * add an ineligible element throws an unchecked exception, typically
 * {@code NullPointerException} or {@code ClassCastException}.  Attempting
 * to query the presence of an ineligible element may throw an exception,
 * or it may simply return false; some implementations will exhibit the former
 * behavior and some will exhibit the latter.  More generally, attempting an
 * operation on an ineligible element whose completion would not result in
 * the insertion of an ineligible element into the collection may throw an
 * exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * <p>It is up to each collection to determine its own synchronization
 * policy.  In the absence of a stronger guarantee by the
 * implementation, undefined behavior may result from the invocation
 * of any method on a collection that is being mutated by another
 * thread; this includes direct invocations, passing the collection to
 * a method that might perform invocations, and using an existing
 * iterator to examine the collection.
 *
 * <p>Many methods in Collections Framework interfaces are defined in
 * terms of the {@link java.lang.Object#equals(Object) equals} method.  For example,
 * the specification for the {@link #contains(Object) contains(Object o)}
 * method says: "returns {@code true} if and only if this collection
 * contains at least one element {@code e} such that
 * {@code (o==null ? e==null : o.equals(e))}."  This specification should
 * <i>not</i> be construed to imply that invoking {@code Collection.contains}
 * with a non-null argument {@code o} will cause {@code o.equals(e)} to be
 * invoked for any element {@code e}.  Implementations are free to implement
 * optimizations whereby the {@code equals} invocation is avoided, for
 * example, by first comparing the hash codes of the two elements.  (The
 * {@link java.lang.Object#hashCode()} specification guarantees that two objects with
 * unequal hash codes cannot be equal.)  More generally, implementations of
 * the various Collections Framework interfaces are free to take advantage of
 * the specified behavior of underlying {@link java.lang.Object} methods wherever the
 * implementor deems it appropriate.
 *
 * <p>Some collection operations which perform recursive traversal of the
 * collection may fail with an exception for self-referential instances where
 * the collection directly or indirectly contains itself. This includes the
 * {@code clone()}, {@code equals()}, {@code hashCode()} and {@code toString()}
 * methods. Implementations may optionally handle the self-referential scenario,
 * however most current implementations do not do so.
 *
 * <h2><a id="view">View Collections</a></h2>
 *
 * <p>Most collections manage storage for elements they contain. By contrast, <i>view
 * collections</i> themselves do not store elements, but instead they rely on a
 * backing collection to store the actual elements. Operations that are not handled
 * by the view collection itself are delegated to the backing collection. Examples of
 * view collections include the wrapper collections returned by methods such as
 * {@link java.util.Collections#checkedCollection Collections.checkedCollection},
 * and {@link java.util.Collections#unmodifiableCollection Collections.unmodifiableCollection}.
 * Other examples of view collections include collections that provide a
 * different representation of the same elements, for example, as
 * provided by {@link java.util.List#subList List.subList},
 * {@link java.util.NavigableSet#subSet NavigableSet.subSet}, or
 * {@link java.util.Map#entrySet Map.entrySet}.
 * Any changes made to the backing collection are visible in the view collection.
 * Correspondingly, any changes made to the view collection &mdash; if changes
 * are permitted &mdash; are written through to the backing collection.
 * Although they technically aren't collections, instances of
 * {@link java.util.Iterator} and {@link java.util.ListIterator} can also allow modifications
 * to be written through to the backing collection, and in some cases,
 * modifications to the backing collection will be visible to the Iterator
 * during iteration.
 *
 * <h2><a id="unmodifiable">Unmodifiable Collections</a></h2>
 *
 * <p>Certain methods of this interface are considered "destructive" and are called
 * "mutator" methods in that they modify the group of objects contained within
 * the collection on which they operate. They can be specified to throw
 * {@code UnsupportedOperationException} if this collection implementation
 * does not support the operation. Such methods should (but are not required
 * to) throw an {@code UnsupportedOperationException} if the invocation would
 * have no effect on the collection. For example, consider a collection that
 * does not support the {@link #add add} operation. What will happen if the
 * {@link #addAll addAll} method is invoked on this collection, with an empty
 * collection as the argument? The addition of zero elements has no effect,
 * so it is permissible for this collection simply to do nothing and not to throw
 * an exception. However, it is recommended that such cases throw an exception
 * unconditionally, as throwing only in certain cases can lead to
 * programming errors.
 *
 * <p>An <i>unmodifiable collection</i> is a collection, all of whose
 * mutator methods (as defined above) are specified to throw
 * {@code UnsupportedOperationException}. Such a collection thus cannot be
 * modified by calling any methods on it. For a collection to be properly
 * unmodifiable, any view collections derived from it must also be unmodifiable.
 * For example, if a List is unmodifiable, the List returned by
 * {@link java.util.List#subList List.subList} is also unmodifiable.
 *
 * <p>An unmodifiable collection is not necessarily immutable. If the
 * contained elements are mutable, the entire collection is clearly
 * mutable, even though it might be unmodifiable. For example, consider
 * two unmodifiable lists containing mutable elements. The result of calling
 * {@code list1.equals(list2)} might differ from one call to the next if
 * the elements had been mutated, even though both lists are unmodifiable.
 * However, if an unmodifiable collection contains all immutable elements,
 * it can be considered effectively immutable.
 *
 * <h2><a id="unmodview">Unmodifiable View Collections</a></h2>
 *
 * <p>An <i>unmodifiable view collection</i> is a collection that is unmodifiable
 * and that is also a view onto a backing collection. Its mutator methods throw
 * {@code UnsupportedOperationException}, as described above, while
 * reading and querying methods are delegated to the backing collection.
 * The effect is to provide read-only access to the backing collection.
 * This is useful for a component to provide users with read access to
 * an internal collection, while preventing them from modifying such
 * collections unexpectedly. Examples of unmodifiable view collections
 * are those returned by the
 * {@link java.util.Collections#unmodifiableCollection Collections.unmodifiableCollection},
 * {@link java.util.Collections#unmodifiableList Collections.unmodifiableList}, and
 * related methods.
 *
 * <p>Note that changes to the backing collection might still be possible,
 * and if they occur, they are visible through the unmodifiable view. Thus,
 * an unmodifiable view collection is not necessarily immutable. However,
 * if the backing collection of an unmodifiable view is effectively immutable,
 * or if the only reference to the backing collection is through an
 * unmodifiable view, the view can be considered effectively immutable.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @author XenoAmess
 * @version 0.8.0
 * @implSpec The default method implementations (inherited or otherwise) do not apply any
 * synchronization protocol.  If a {@code Collection} implementation has a
 * specific synchronization protocol, then it must override default
 * implementations to apply that protocol.
 * @see Set
 * @see List
 * @see Map
 * @see SortedSet
 * @see SortedMap
 * @see HashSet
 * @see TreeSet
 * @see ArrayList
 * @see LinkedList
 * @see Vector
 * @see Collections
 * @see Arrays
 * @see AbstractCollection
 * @see Collection
 * @since 1.2
 */
public interface LongCollection extends Collection<Long>, LongIterable, Primitive {
    // Query Operations

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
        if (o == null) {
            return false;
        }
        if (!(o instanceof Long)) {
            return false;
        }
        return this.containsPrimitive((Long) o);
    }

    /**
     * Primitive replacement of contains(Object o)
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws java.lang.ClassCastException   if the type of the specified element
     *                                        is incompatible with this list
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified element is null and this
     *                                        list does not permit null elements
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @see #contains(Object o)
     */
    default boolean contains(long o) {
        return this.containsPrimitive(o);
    }

    /**
     * Primitive replacement of contains(Object o)
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws java.lang.ClassCastException   if the type of the specified element
     *                                        is incompatible with this list
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified element is null and this
     *                                        list does not permit null elements
     *                                        (<a href="Collection.html#optional-restrictions">optional</a>)
     * @see #contains(Object o)
     */
    boolean containsPrimitive(long o);


    /**
     * {@inheritDoc}
     * <p>
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     */
    @Override
    LongIterator iterator();

    /**
     * {@inheritDoc}
     * <p>
     * Returns an array containing all of the elements in this collection,
     * using the provided {@code generator} function to allocate the returned array.
     *
     * <p>If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * @apiNote This method acts as a bridge between array-based and collection-based APIs.
     * It allows creation of an array of a particular runtime type. Use
     * {@link #toArray()} to create an array whose runtime type is {@code Object[]},
     * or use {@link #toArray(Object[]) toArray(T[])} to reuse an existing array.
     *
     * <p>Suppose {@code x} is a collection known to contain only strings.
     * The following code can be used to dump the collection into a newly
     * allocated array of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArray(String[]::new);</pre>
     * @implSpec The default implementation calls the generator function with zero
     * and then passes the resulting array to {@link #toArray(Object[]) toArray(T[])}.
     * @since 11
     */
    default <T> T[] toArray(IntFunction<T[]> generator) {
        return toArray(generator.apply(0));
    }

    // Modification Operations

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
    default boolean add(Long e) {
        return addPrimitive(e);
    }

    /**
     * Primitive replacement of add(Long e)
     *
     * @param e element whose presence in this collection is to be ensured
     * @return {@code true} if this collection changed as a result of the
     * call
     * @throws java.lang.UnsupportedOperationException if the {@code add} operation
     *                                                 is not supported by this collection
     * @throws java.lang.ClassCastException            if the class of the specified element
     *                                                 prevents it from being added to this collection
     * @throws java.lang.NullPointerException          if the specified element is null and this
     *                                                 collection does not permit null elements
     * @throws java.lang.IllegalArgumentException      if some property of the element
     *                                                 prevents it from being added to this collection
     * @throws java.lang.IllegalStateException         if the element cannot be added at this
     *                                                 time due to insertion restrictions
     * @see #add(Long e)
     */
    default boolean add(long e) {
        return addPrimitive(e);
    }

    /**
     * Primitive replacement of add(Long e)
     *
     * @param e element whose presence in this collection is to be ensured
     * @return {@code true} if this collection changed as a result of the
     * call
     * @throws java.lang.UnsupportedOperationException if the {@code add} operation
     *                                                 is not supported by this collection
     * @throws java.lang.ClassCastException            if the class of the specified element
     *                                                 prevents it from being added to this collection
     * @throws java.lang.NullPointerException          if the specified element is null and this
     *                                                 collection does not permit null elements
     * @throws java.lang.IllegalArgumentException      if some property of the element
     *                                                 prevents it from being added to this collection
     * @throws java.lang.IllegalStateException         if the element cannot be added at this
     *                                                 time due to insertion restrictions
     * @see #add(Long e)
     */
    boolean addPrimitive(long e);

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
        if (o == null) {
            return false;
        }
        if (!(o instanceof Long)) {
            return false;
        }
        return removeByContentPrimitive((Long) o);
    }

    /**
     * Primitive replacement of remove(Object o)
     *
     * @param o element to be removed from this collection, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws java.lang.ClassCastException            if the type of the specified element
     *                                                 is incompatible with this collection
     * @throws java.lang.NullPointerException          if the specified element is null and this
     *                                                 collection does not permit null elements
     * @throws java.lang.UnsupportedOperationException if the {@code remove} operation
     *                                                 is not supported by this collection
     * @see #remove(Object o)
     */
    default boolean removeByContent(long o) {
        return this.removeByContentPrimitive(o);
    }

    /**
     * Primitive replacement of remove(Object o)
     *
     * @param o element to be removed from this collection, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws java.lang.ClassCastException            if the type of the specified element
     *                                                 is incompatible with this collection
     * @throws java.lang.NullPointerException          if the specified element is null and this
     *                                                 collection does not permit null elements
     * @throws java.lang.UnsupportedOperationException if the {@code remove} operation
     *                                                 is not supported by this collection
     * @see #remove(Object o)
     */
    boolean removeByContentPrimitive(long o);

    // Bulk Operations

    /**
     * {@inheritDoc}
     * <p>
     * Removes all of the elements of this collection that satisfy the given
     * predicate.  Errors or runtime exceptions thrown during iteration or by
     * the predicate are relayed to the caller.
     *
     * @implSpec The default implementation traverses all elements of the collection using
     * its {@link #iterator}.  Each matching element is removed using
     * {@link java.util.Iterator#remove()}.  If the collection's iterator does not
     * support removal then an {@code UnsupportedOperationException} will be
     * thrown on the first matching element.
     * @since 1.8
     */
    @Override
    default boolean removeIf(Predicate<? super Long> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final LongIterator each = iterator();

        if (filter instanceof LongPredicate) {
            LongPredicate actionLongPredicate = (LongPredicate) filter;
            while (each.hasNext()) {
                if (actionLongPredicate.testPrimitive(each.nextPrimitive())) {
                    each.remove();
                    removed = true;
                }
            }
        } else {
            while (each.hasNext()) {
                if (filter.test(each.next())) {
                    each.remove();
                    removed = true;
                }
            }
        }
        return removed;
    }

    // Comparison and hashing

    /**
     * {@inheritDoc}
     * <p>
     * Creates a {@link Spliterator} over the elements in this collection.
     * <p>
     * Implementations should document characteristic values reported by the
     * spliterator.  Such characteristic values are not required to be reported
     * if the spliterator reports {@link Spliterator#SIZED} and this collection
     * contains no elements.
     *
     * <p>The default implementation should be overridden by subclasses that
     * can return a more efficient spliterator.  In order to
     * preserve expected laziness behavior for the {@link #stream()} and
     * {@link #parallelStream()} methods, spliterators should either have the
     * characteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or be
     * <em><a href="Spliterator.html#binding">late-binding</a></em>.
     * If none of these is practical, the overriding class should describe the
     * spliterator's documented policy of binding and structural interference,
     * and should override the {@link #stream()} and {@link #parallelStream()}
     * methods to create streams using a {@code Supplier} of the spliterator,
     * as in:
     * <pre>{@code
     *     Stream<E> s = StreamSupport.stream(() -> spliterator(), spliteratorCharacteristics)
     * }</pre>
     * <p>These requirements ensure that streams produced by the
     * {@link #stream()} and {@link #parallelStream()} methods will reflect the
     * contents of the collection as of initiation of the terminal stream
     * operation.
     *
     * @implSpec The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
     * from the collection's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> properties of the collection's iterator.
     * <p>
     * The created {@code Spliterator} reports {@link java.util.Spliterator#SIZED}.
     * @implNote The created {@code Spliterator} additionally reports
     * {@link java.util.Spliterator#SUBSIZED}.
     *
     * <p>If a spliterator covers no elements then the reporting of additional
     * characteristic values, beyond that of {@code SIZED} and {@code SUBSIZED},
     * does not aid clients to control, specialize or simplify computation.
     * However, this does enable shared use of an immutable and empty
     * spliterator instance (see {@link java.util.Spliterators#emptySpliterator()}) for
     * empty collections, and enables clients to determine if such a spliterator
     * covers no elements.
     * @since 1.8
     */
    @Override
    default LongSpliterator spliterator() {
        return LongSpliterators.spliterator(this, 0);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a sequential {@code Stream} with this collection as its source.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @implSpec The default implementation creates a sequential {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    @Override
    default Stream<Long> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a possibly parallel {@code Stream} with this collection as its
     * source.  It is allowable for this method to return a sequential stream.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @implSpec The default implementation creates a parallel {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    @Override
    default Stream<Long> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
