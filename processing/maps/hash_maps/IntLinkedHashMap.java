///*
// * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
// * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
// *
// * This code is free software; you can redistribute it and/or modify it
// * under the terms of the GNU General Public License version 2 only, as
// * published by the Free Software Foundation.  Oracle designates this
// * particular file as subject to the "Classpath" exception as provided
// * by Oracle in the LICENSE file that accompanied this code.
// *
// * This code is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
// * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
// * version 2 for more details (a copy is included in the LICENSE file that
// * accompanied this code).
// *
// * You should have received a copy of the GNU General Public License version
// * 2 along with this work; if not, write to the Free Software Foundation,
// * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
// *
// * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
// * or visit www.oracle.com if you need additional information or have any
// * questions.
// */
//
//
//package com.xenoamess.commons.primitive.collections.maps.hash_maps;
//
//import com.xenoamess.commons.primitive.collections.maps.IntMap;
//
//import java.io.IOException;
//import java.util.*;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.Consumer;
//
///**
// * <p>Hash table and linked list implementation of the {@code Map} interface,
// * with predictable iteration order.  This implementation differs from
// * {@code HashMap} in that it maintains a doubly-linked list running through
// * all of its entries.  This linked list defines the iteration ordering,
// * which is normally the order in which keys were inserted into the map
// * (<i>insertion-order</i>).  Note that insertion order is not affected
// * if a key is <i>re-inserted</i> into the map.  (A key {@code k} is
// * reinserted into a map {@code m} if {@code m.put(k, v)} is invoked when
// * {@code m.containsKey(k)} would return {@code true} immediately prior to
// * the invocation.)
// *
// * <p>This implementation spares its clients from the unspecified, generally
// * chaotic ordering provided by {@link HashMap} (and {@link Hashtable}),
// * without incurring the increased cost associated with {@link TreeMap}.  It
// * can be used to produce a copy of a map that has the same order as the
// * original, regardless of the original map's implementation:
// * <pre>
// *     void foo(Map m) {
// *         Map copy = new IntLinkedHashMap(m);
// *         ...
// *     }
// * </pre>
// * This technique is particularly useful if a module takes a map on input,
// * copies it, and later returns results whose order is determined by that of
// * the copy.  (Clients generally appreciate having things returned in the same
// * order they were presented.)
// *
// * <p>A special {@link #IntLinkedHashMap(int, float, boolean) constructor} is
// * provided to create a linked hash map whose order of iteration is the order
// * in which its entries were last accessed, from least-recently accessed to
// * most-recently (<i>access-order</i>).  This kind of map is well-suited to
// * building LRU caches.  Invoking the {@code put}, {@code putIfAbsent},
// * {@code get}, {@code getOrDefault}, {@code compute}, {@code computeIfAbsent},
// * {@code computeIfPresent}, or {@code merge} methods results
// * in an access to the corresponding entry (assuming it exists after the
// * invocation completes). The {@code replace} methods only result in an access
// * of the entry if the value is replaced.  The {@code putAll} method generates one
// * entry access for each mapping in the specified map, in the order that
// * key-value mappings are provided by the specified map's entry set iterator.
// * <i>No other methods generate entry accesses.</i>  In particular, operations
// * on collection-views do <i>not</i> affect the order of iteration of the
// * backing map.
// *
// * <p>The {@link #removeEldestEntry(Map.Entry)} method may be overridden to
// * impose a policy for removing stale mappings automatically when new mappings
// * are added to the map.
// *
// * <p>This class provides all of the optional {@code Map} operations, and
// * permits null elements.  Like {@code HashMap}, it provides constant-time
// * performance for the basic operations ({@code add}, {@code contains} and
// * {@code remove}), assuming the hash function disperses elements
// * properly among the buckets.  Performance is likely to be just slightly
// * below that of {@code HashMap}, due to the added expense of maintaining the
// * linked list, with one exception: Iteration over the collection-views
// * of a {@code IntLinkedHashMap} requires time proportional to the <i>size</i>
// * of the map, regardless of its capacity.  Iteration over a {@code HashMap}
// * is likely to be more expensive, requiring time proportional to its
// * <i>capacity</i>.
// *
// * <p>A linked hash map has two parameters that affect its performance:
// * <i>initial capacity</i> and <i>load factor</i>.  They are defined precisely
// * as for {@code HashMap}.  Note, however, that the penalty for choosing an
// * excessively high value for initial capacity is less severe for this class
// * than for {@code HashMap}, as iteration times for this class are unaffected
// * by capacity.
// *
// * <p><strong>Note that this implementation is not synchronized.</strong>
// * If multiple threads access a linked hash map concurrently, and at least
// * one of the threads modifies the map structurally, it <em>must</em> be
// * synchronized externally.  This is typically accomplished by
// * synchronizing on some object that naturally encapsulates the map.
// * <p>
// * If no such object exists, the map should be "wrapped" using the
// * {@link Collections#synchronizedMap Collections.synchronizedMap}
// * method.  This is best done at creation time, to prevent accidental
// * unsynchronized access to the map:<pre>
// *   Map m = Collections.synchronizedMap(new IntLinkedHashMap(...));</pre>
// * <p>
// * A structural modification is any operation that adds or deletes one or more
// * mappings or, in the case of access-ordered linked hash maps, affects
// * iteration order.  In insertion-ordered linked hash maps, merely changing
// * the value associated with a key that is already contained in the map is not
// * a structural modification.  <strong>In access-ordered linked hash maps,
// * merely querying the map with {@code get} is a structural modification.
// * </strong>)
// *
// * <p>The iterators returned by the {@code iterator} method of the collections
// * returned by all of this class's collection view methods are
// * <em>fail-fast</em>: if the map is structurally modified at any time after
// * the iterator is created, in any way except through the iterator's own
// * {@code remove} method, the iterator will throw a {@link
// * ConcurrentModificationException}.  Thus, in the face of concurrent
// * modification, the iterator fails quickly and cleanly, rather than risking
// * arbitrary, non-deterministic behavior at an undetermined time in the future.
// *
// * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
// * as it is, generally speaking, impossible to make any hard guarantees in the
// * presence of unsynchronized concurrent modification.  Fail-fast iterators
// * throw {@code ConcurrentModificationException} on a best-effort basis.
// * Therefore, it would be wrong to write a program that depended on this
// * exception for its correctness:   <i>the fail-fast behavior of iterators
// * should be used only to detect bugs.</i>
// *
// * <p>The spliterators returned by the spliterator method of the collections
// * returned by all of this class's collection view methods are
// * <em><a href="Spliterator.html#binding">late-binding</a></em>,
// * <em>fail-fast</em>, and additionally report {@link Spliterator#ORDERED}.
// *
// * <p>This class is a member of the
// * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
// * Java Collections Framework</a>.
// *
// * @param <V> the type of mapped values
// * @author Josh Bloch
// * @implNote The spliterators returned by the spliterator method of the collections
// * returned by all of this class's collection view methods are created from
// * the iterators of the corresponding collections.
// * @see Object#hashCode()
// * @see Collection
// * @see Map
// * @see HashMap
// * @see TreeMap
// * @see Hashtable
// * @since 1.4
// */
//public class IntLinkedHashMap<V> extends IntHashMap<V> implements IntMap<V> {
//    /*
//     * Implementation note.  A previous version of this class was
//     * internally structured a little differently. Because superclass
//     * HashMap now uses trees for some of its nodes, class
//     * IntLinkedHashMap.Entry is now treated as intermediary node class
//     * that can also be converted to tree form. The name of this
//     * class, IntLinkedHashMap.Entry, is confusing in several ways in its
//     * current context, but cannot be changed.  Otherwise, even though
//     * it is not exported outside this package, some existing source
//     * code is known to have relied on a symbol resolution corner case
//     * rule in calls to removeEldestEntry that suppressed compilation
//     * errors due to ambiguous usages. So, we keep the name to
//     * preserve unmodified compilability.
//     *
//     * The changes in node classes also require using two fields
//     * (head, tail) rather than a pointer to a header node to maintain
//     * the doubly-linked before/after list. This class also
//     * previously used a different style of callback methods upon
//     * access, insertion, and removal.
//     */
//
//    /**
//     * HashMap.Node subclass for normal IntLinkedHashMap entries.
//     */
//    static class IntEntry<V> extends IntHashMap.IntNode<V> {
//        IntLinkedHashMap.IntEntry<V> before, after;
//
//        IntEntry(int hash, int key, V value, IntHashMap.IntNode<V> next) {
//            super(hash, key, value, next);
//        }
//    }
//
//    /**
//     * The head (eldest) of the doubly linked list.
//     */
//    transient IntLinkedHashMap.IntEntry<V> head;
//
//    /**
//     * The tail (youngest) of the doubly linked list.
//     */
//    transient IntLinkedHashMap.IntEntry<V> tail;
//
//    /**
//     * The iteration ordering method for this linked hash map: {@code true}
//     * for access-order, {@code false} for insertion-order.
//     *
//     * @serial
//     */
//    final boolean accessOrder;
//
//    // internal utilities
//
//    // link at the end of list
//    private void linkNodeLast(IntLinkedHashMap.IntEntry<V> p) {
//        IntLinkedHashMap.IntEntry<V> last = tail;
//        tail = p;
//        if (last == null) {
//            head = p;
//        } else {
//            p.before = last;
//            last.after = p;
//        }
//    }
//
//    // apply src's links to dst
//    private void transferLinks(IntLinkedHashMap.IntEntry<V> src,
//                               IntLinkedHashMap.IntEntry<V> dst) {
//        IntLinkedHashMap.IntEntry<V> b = dst.before = src.before;
//        IntLinkedHashMap.IntEntry<V> a = dst.after = src.after;
//        if (b == null) {
//            head = dst;
//        } else {
//            b.after = dst;
//        }
//        if (a == null) {
//            tail = dst;
//        } else {
//            a.before = dst;
//        }
//    }
//
//    // overrides of HashMap hook methods
//
//    @Override
//    void reinitialize() {
//        super.reinitialize();
//        head = tail = null;
//    }
//
//    IntHashMap.IntNode<V> newNode(int hash, Integer key, V value, IntHashMap.IntNode<V> e) {
//        IntLinkedHashMap.IntEntry<V> p =
//                new IntLinkedHashMap.Entry<>(hash, key, value, e);
//        linkNodeLast(p);
//        return p;
//    }
//
//    IntHashMap.IntNode<V> replacementNode(IntHashMap.IntNode<V> p, IntHashMap.IntNode<V> next) {
//        IntLinkedHashMap.IntEntry<V> q = (IntLinkedHashMap.IntEntry<V>) p;
//        IntLinkedHashMap.IntEntry<V> t =
//                new IntLinkedHashMap.Entry<>(q.hash, q.key, q.value, next);
//        transferLinks(q, t);
//        return t;
//    }
//
//    HashMap.TreeNode<K, V> newTreeNode(int hash, Integer key, V value, IntHashMap.IntNode<V> next) {
//        HashMap.TreeNode<K, V> p = new HashMap.TreeNode<>(hash, key, value, next);
//        linkNodeLast(p);
//        return p;
//    }
//
//    HashMap.TreeNode<K, V> replacementTreeNode(IntHashMap.IntNode<V> p, IntHashMap.IntNode<V> next) {
//        IntLinkedHashMap.IntEntry<V> q = (IntLinkedHashMap.IntEntry<V>) p;
//        HashMap.TreeNode<K, V> t = new HashMap.TreeNode<>(q.hash, q.key, q.value, next);
//        transferLinks(q, t);
//        return t;
//    }
//
//    void afterNodeRemoval(IntHashMap.IntNode<V> e) { // unlink
//        IntLinkedHashMap.IntEntry<V> p =
//                (IntLinkedHashMap.IntEntry<V>) e, b = p.before, a = p.after;
//        p.before = p.after = null;
//        if (b == null) {
//            head = a;
//        } else {
//            b.after = a;
//        }
//        if (a == null) {
//            tail = b;
//        } else {
//            a.before = b;
//        }
//    }
//
//    void afterNodeInsertion(boolean evict) { // possibly remove eldest
//        IntLinkedHashMap.IntEntry<V> first;
//        if (evict && (first = head) != null && removeEldestEntry(first)) {
//            Integer key = first.key;
//            removeNode(hash(key), key, null, false, true);
//        }
//    }
//
//    void afterNodeAccess(IntHashMap.IntNode<V> e) { // move node to last
//        IntLinkedHashMap.IntEntry<V> last;
//        if (accessOrder && (last = tail) != e) {
//            IntLinkedHashMap.IntEntry<V> p =
//                    (IntLinkedHashMap.IntEntry<V>) e, b = p.before, a = p.after;
//            p.after = null;
//            if (b == null) {
//                head = a;
//            } else {
//                b.after = a;
//            }
//            if (a != null) {
//                a.before = b;
//            } else {
//                last = b;
//            }
//            if (last == null) {
//                head = p;
//            } else {
//                p.before = last;
//                last.after = p;
//            }
//            tail = p;
//            ++modCount;
//        }
//    }
//
//    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException {
//        for (IntLinkedHashMap.IntEntry<V> e = head; e != null; e = e.after) {
//            s.writeObject(e.key);
//            s.writeObject(e.value);
//        }
//    }
//
//    /**
//     * Constructs an empty insertion-ordered {@code IntLinkedHashMap} instance
//     * with the specified initial capacity and load factor.
//     *
//     * @param initialCapacity the initial capacity
//     * @param loadFactor      the load factor
//     * @throws IllegalArgumentException if the initial capacity is negative
//     *                                  or the load factor is nonpositive
//     */
//    public IntLinkedHashMap(int initialCapacity, float loadFactor) {
//        super(initialCapacity, loadFactor);
//        accessOrder = false;
//    }
//
//    /**
//     * Constructs an empty insertion-ordered {@code IntLinkedHashMap} instance
//     * with the specified initial capacity and a default load factor (0.75).
//     *
//     * @param initialCapacity the initial capacity
//     * @throws IllegalArgumentException if the initial capacity is negative
//     */
//    public IntLinkedHashMap(int initialCapacity) {
//        super(initialCapacity);
//        accessOrder = false;
//    }
//
//    /**
//     * Constructs an empty insertion-ordered {@code IntLinkedHashMap} instance
//     * with the default initial capacity (16) and load factor (0.75).
//     */
//    public IntLinkedHashMap() {
//        super();
//        accessOrder = false;
//    }
//
//    /**
//     * Constructs an insertion-ordered {@code IntLinkedHashMap} instance with
//     * the same mappings as the specified map.  The {@code IntLinkedHashMap}
//     * instance is created with a default load factor (0.75) and an initial
//     * capacity sufficient to hold the mappings in the specified map.
//     *
//     * @param m the map whose mappings are to be placed in this map
//     * @throws NullPointerException if the specified map is null
//     */
//    public IntLinkedHashMap(Map<? extends K, ? extends V> m) {
//        super();
//        accessOrder = false;
//        putMapEntries(m, false);
//    }
//
//    /**
//     * Constructs an empty {@code IntLinkedHashMap} instance with the
//     * specified initial capacity, load factor and ordering mode.
//     *
//     * @param initialCapacity the initial capacity
//     * @param loadFactor      the load factor
//     * @param accessOrder     the ordering mode - {@code true} for
//     *                        access-order, {@code false} for insertion-order
//     * @throws IllegalArgumentException if the initial capacity is negative
//     *                                  or the load factor is nonpositive
//     */
//    public IntLinkedHashMap(int initialCapacity,
//                               float loadFactor,
//                               boolean accessOrder) {
//        super(initialCapacity, loadFactor);
//        this.accessOrder = accessOrder;
//    }
//
//
//    /**
//     * Returns {@code true} if this map maps one or more keys to the
//     * specified value.
//     *
//     * @param value value whose presence in this map is to be tested
//     * @return {@code true} if this map maps one or more keys to the
//     * specified value
//     */
//    @Override
//    public boolean containsValue(Object value) {
//        for (IntLinkedHashMap.IntEntry<V> e = head; e != null; e = e.after) {
//            V v = e.value;
//            if (v == value || (value != null && value.equals(v))) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Returns the value to which the specified key is mapped,
//     * or {@code null} if this map contains no mapping for the key.
//     *
//     * <p>More formally, if this map contains a mapping from a key
//     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
//     * key.equals(k))}, then this method returns {@code v}; otherwise
//     * it returns {@code null}.  (There can be at most one such mapping.)
//     *
//     * <p>A return value of {@code null} does not <i>necessarily</i>
//     * indicate that the map contains no mapping for the key; it's also
//     * possible that the map explicitly maps the key to {@code null}.
//     * The {@link #containsKey containsKey} operation may be used to
//     * distinguish these two cases.
//     */
//    @Override
//    public V get(Object key) {
//        IntHashMap.IntNode<V> e;
//        if ((e = getNode(hash(key), key)) == null) {
//            return null;
//        }
//        if (accessOrder) {
//            afterNodeAccess(e);
//        }
//        return e.value;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    public V getOrDefault(Object key, V defaultValue) {
//        IntHashMap.IntNode<V> e;
//        if ((e = getNode(hash(key), key)) == null) {
//            return defaultValue;
//        }
//        if (accessOrder) {
//            afterNodeAccess(e);
//        }
//        return e.value;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void clear() {
//        super.clear();
//        head = tail = null;
//    }
//
//    /**
//     * Returns {@code true} if this map should remove its eldest entry.
//     * This method is invoked by {@code put} and {@code putAll} after
//     * inserting a new entry into the map.  It provides the implementor
//     * with the opportunity to remove the eldest entry each time a new one
//     * is added.  This is useful if the map represents a cache: it allows
//     * the map to reduce memory consumption by deleting stale entries.
//     *
//     * <p>Sample use: this override will allow the map to grow up to 100
//     * entries and then delete the eldest entry each time a new entry is
//     * added, maintaining a steady state of 100 entries.
//     * <pre>
//     *     private static final int MAX_ENTRIES = 100;
//     *
//     *     protected boolean removeEldestEntry(Map.Entry eldest) {
//     *        return size() &gt; MAX_ENTRIES;
//     *     }
//     * </pre>
//     *
//     * <p>This method typically does not modify the map in any way,
//     * instead allowing the map to modify itself as directed by its
//     * return value.  It <i>is</i> permitted for this method to modify
//     * the map directly, but if it does so, it <i>must</i> return
//     * {@code false} (indicating that the map should not attempt any
//     * further modification).  The effects of returning {@code true}
//     * after modifying the map from within this method are unspecified.
//     *
//     * <p>This implementation merely returns {@code false} (so that this
//     * map acts like a normal map - the eldest element is never removed).
//     *
//     * @param eldest The least recently inserted entry in the map, or if
//     *               this is an access-ordered map, the least recently accessed
//     *               entry.  This is the entry that will be removed it this
//     *               method returns {@code true}.  If the map was empty prior
//     *               to the {@code put} or {@code putAll} invocation resulting
//     *               in this invocation, this will be the entry that was just
//     *               inserted; in other words, if the map contains a single
//     *               entry, the eldest entry is also the newest.
//     * @return {@code true} if the eldest entry should be removed
//     * from the map; {@code false} if it should be retained.
//     */
//    protected boolean removeEldestEntry(Map.IntEntry<V> eldest) {
//        return false;
//    }
//
//    /**
//     * Returns a {@link Set} view of the keys contained in this map.
//     * The set is backed by the map, so changes to the map are
//     * reflected in the set, and vice-versa.  If the map is modified
//     * while an iteration over the set is in progress (except through
//     * the iterator's own {@code remove} operation), the results of
//     * the iteration are undefined.  The set supports element removal,
//     * which removes the corresponding mapping from the map, via the
//     * {@code Iterator.remove}, {@code Set.remove},
//     * {@code removeAll}, {@code retainAll}, and {@code clear}
//     * operations.  It does not support the {@code add} or {@code addAll}
//     * operations.
//     * Its {@link Spliterator} typically provides faster sequential
//     * performance but much poorer parallel performance than that of
//     * {@code HashMap}.
//     *
//     * @return a set view of the keys contained in this map
//     */
//    @Override
//    public Set<K> keySet() {
//        Set<K> ks = keySet;
//        if (ks == null) {
//            ks = new IntLinkedHashMap.LinkedKeySet();
//            keySet = ks;
//        }
//        return ks;
//    }
//
//    final class LinkedKeySet extends AbstractSet<K> {
//        public final int size() {
//            return size;
//        }
//
//        public final void clear() {
//            IntLinkedHashMap.this.clear();
//        }
//
//        public final Iterator<K> iterator() {
//            return new IntLinkedHashMap.LinkedKeyIterator();
//        }
//
//        public final boolean contains(Object o) {
//            return containsKey(o);
//        }
//
//        public final boolean remove(Object key) {
//            return removeNode(hash(key), key, null, false, true) != null;
//        }
//
//        public final Spliterator<K> spliterator() {
//            return Spliterators.spliterator(this, Spliterator.SIZED |
//                    Spliterator.ORDERED |
//                    Spliterator.DISTINCT);
//        }
//
//        public final void forEach(Consumer<? super K> action) {
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            int mc = modCount;
//            for (IntLinkedHashMap.IntEntry<V> e = head; e != null; e = e.after) {
//                action.accept(e.key);
//            }
//            if (modCount != mc) {
//                throw new ConcurrentModificationException();
//            }
//        }
//    }
//
//    /**
//     * Returns a {@link Collection} view of the values contained in this map.
//     * The collection is backed by the map, so changes to the map are
//     * reflected in the collection, and vice-versa.  If the map is
//     * modified while an iteration over the collection is in progress
//     * (except through the iterator's own {@code remove} operation),
//     * the results of the iteration are undefined.  The collection
//     * supports element removal, which removes the corresponding
//     * mapping from the map, via the {@code Iterator.remove},
//     * {@code Collection.remove}, {@code removeAll},
//     * {@code retainAll} and {@code clear} operations.  It does not
//     * support the {@code add} or {@code addAll} operations.
//     * Its {@link Spliterator} typically provides faster sequential
//     * performance but much poorer parallel performance than that of
//     * {@code HashMap}.
//     *
//     * @return a view of the values contained in this map
//     */
//    @Override
//    public Collection<V> values() {
//        Collection<V> vs = values;
//        if (vs == null) {
//            vs = new IntLinkedHashMap.LinkedValues();
//            values = vs;
//        }
//        return vs;
//    }
//
//    final class LinkedValues extends AbstractCollection<V> {
//        public final int size() {
//            return size;
//        }
//
//        public final void clear() {
//            IntLinkedHashMap.this.clear();
//        }
//
//        public final Iterator<V> iterator() {
//            return new IntLinkedHashMap.LinkedValueIterator();
//        }
//
//        public final boolean contains(Object o) {
//            return containsValue(o);
//        }
//
//        public final Spliterator<V> spliterator() {
//            return Spliterators.spliterator(this, Spliterator.SIZED |
//                    Spliterator.ORDERED);
//        }
//
//        public final void forEach(Consumer<? super V> action) {
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            int mc = modCount;
//            for (IntLinkedHashMap.IntEntry<V> e = head; e != null; e = e.after) {
//                action.accept(e.value);
//            }
//            if (modCount != mc) {
//                throw new ConcurrentModificationException();
//            }
//        }
//    }
//
//    /**
//     * Returns a {@link Set} view of the mappings contained in this map.
//     * The set is backed by the map, so changes to the map are
//     * reflected in the set, and vice-versa.  If the map is modified
//     * while an iteration over the set is in progress (except through
//     * the iterator's own {@code remove} operation, or through the
//     * {@code setValue} operation on a map entry returned by the
//     * iterator) the results of the iteration are undefined.  The set
//     * supports element removal, which removes the corresponding
//     * mapping from the map, via the {@code Iterator.remove},
//     * {@code Set.remove}, {@code removeAll}, {@code retainAll} and
//     * {@code clear} operations.  It does not support the
//     * {@code add} or {@code addAll} operations.
//     * Its {@link Spliterator} typically provides faster sequential
//     * performance but much poorer parallel performance than that of
//     * {@code HashMap}.
//     *
//     * @return a set view of the mappings contained in this map
//     */
//    public Set<Map.IntEntry<V>> entrySet() {
//        Set<Map.IntEntry<V>> es;
//        return (es = entrySet) == null ? (entrySet = new IntLinkedHashMap.LinkedEntrySet()) : es;
//    }
//
//    final class LinkedEntrySet extends AbstractSet<Map.IntEntry<V>> {
//        public final int size() {
//            return size;
//        }
//
//        public final void clear() {
//            IntLinkedHashMap.this.clear();
//        }
//
//        public final Iterator<Map.IntEntry<V>> iterator() {
//            return new IntLinkedHashMap.LinkedEntryIterator();
//        }
//
//        public final boolean contains(Object o) {
//            if (!(o instanceof Map.Entry)) {
//                return false;
//            }
//            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//            Object key = e.getKey();
//            IntHashMap.IntNode<V> candidate = getNode(hash(key), key);
//            return candidate != null && candidate.equals(e);
//        }
//
//        public final boolean remove(Object o) {
//            if (o instanceof Map.Entry) {
//                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//                Object key = e.getKey();
//                Object value = e.getValue();
//                return removeNode(hash(key), key, value, true, true) != null;
//            }
//            return false;
//        }
//
//        public final Spliterator<Map.IntEntry<V>> spliterator() {
//            return Spliterators.spliterator(this, Spliterator.SIZED |
//                    Spliterator.ORDERED |
//                    Spliterator.DISTINCT);
//        }
//
//        public final void forEach(Consumer<? super Map.IntEntry<V>> action) {
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            int mc = modCount;
//            for (IntLinkedHashMap.IntEntry<V> e = head; e != null; e = e.after) {
//                action.accept(e);
//            }
//            if (modCount != mc) {
//                throw new ConcurrentModificationException();
//            }
//        }
//    }
//
//    // Map overrides
//
//    public void forEach(BiConsumer<? super K, ? super V> action) {
//        if (action == null) {
//            throw new NullPointerException();
//        }
//        int mc = modCount;
//        for (IntLinkedHashMap.IntEntry<V> e = head; e != null; e = e.after) {
//            action.accept(e.key, e.value);
//        }
//        if (modCount != mc) {
//            throw new ConcurrentModificationException();
//        }
//    }
//
//    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
//        if (function == null) {
//            throw new NullPointerException();
//        }
//        int mc = modCount;
//        for (IntLinkedHashMap.IntEntry<V> e = head; e != null; e = e.after) {
//            e.value = function.apply(e.key, e.value);
//        }
//        if (modCount != mc) {
//            throw new ConcurrentModificationException();
//        }
//    }
//
//    // Iterators
//
//    abstract class LinkedHashIterator {
//        IntLinkedHashMap.IntEntry<V> next;
//        IntLinkedHashMap.IntEntry<V> current;
//        int expectedModCount;
//
//        LinkedHashIterator() {
//            next = head;
//            expectedModCount = modCount;
//            current = null;
//        }
//
//        public final boolean hasNext() {
//            return next != null;
//        }
//
//        final IntLinkedHashMap.IntEntry<V> nextNode() {
//            IntLinkedHashMap.IntEntry<V> e = next;
//            if (modCount != expectedModCount) {
//                throw new ConcurrentModificationException();
//            }
//            if (e == null) {
//                throw new NoSuchElementException();
//            }
//            current = e;
//            next = e.after;
//            return e;
//        }
//
//        public final void remove() {
//            IntHashMap.IntNode<V> p = current;
//            if (p == null) {
//                throw new IllegalStateException();
//            }
//            if (modCount != expectedModCount) {
//                throw new ConcurrentModificationException();
//            }
//            current = null;
//            removeNode(p.hash, p.key, null, false, false);
//            expectedModCount = modCount;
//        }
//    }
//
//    final class LinkedKeyIterator extends IntLinkedHashMap.LinkedHashIterator
//            implements Iterator<K> {
//        public final K next() {
//            return nextNode().getKey();
//        }
//    }
//
//    final class LinkedValueIterator extends IntLinkedHashMap.LinkedHashIterator
//            implements Iterator<V> {
//        public final V next() {
//            return nextNode().value;
//        }
//    }
//
//    final class LinkedEntryIterator extends IntLinkedHashMap.LinkedHashIterator
//            implements Iterator<Map.IntEntry<V>> {
//        public final Map.IntEntry<V> next() {
//            return nextNode();
//        }
//    }
//
//
//}
