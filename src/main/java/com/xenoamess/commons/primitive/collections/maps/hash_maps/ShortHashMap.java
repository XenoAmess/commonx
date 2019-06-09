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
//package com.xenoamess.commons.primitive.collections.maps.hash_maps;
//
//import com.xenoamess.commons.primitive.collections.maps.AbstractShortMap;
//import com.xenoamess.commons.primitive.collections.maps.ShortMap;
//import jdk.internal.access.SharedSecrets;
//
//import java.io.IOException;
//import java.io.InvalidObjectException;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.*;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.Consumer;
//import java.util.function.Function;
//
///**
// * Hash table based implementation of the {@code Map} interface.  This
// * implementation provides all of the optional map operations, and permits
// * {@code null} values and the {@code null} key.  (The {@code ShortHashMap}
// * class is roughly equivalent to {@code Hashtable}, except that it is
// * unsynchronized and permits nulls.)  This class makes no guarantees as to
// * the order of the map; in particular, it does not guarantee that the order
// * will remain constant over time.
// *
// * <p>This implementation provides constant-time performance for the basic
// * operations ({@code get} and {@code put}), assuming the hash function
// * disperses the elements properly among the buckets.  Iteration over
// * collection views requires time proportional to the "capacity" of the
// * {@code ShortHashMap} instance (the number of buckets) plus its size (the number
// * of key-value mappings).  Thus, it's very important not to set the initial
// * capacity too high (or the load factor too low) if iteration performance is
// * important.
// *
// * <p>An instance of {@code ShortHashMap} has two parameters that affect its
// * performance: <i>initial capacity</i> and <i>load factor</i>.  The
// * <i>capacity</i> is the number of buckets in the hash table, and the initial
// * capacity is simply the capacity at the time the hash table is created.  The
// * <i>load factor</i> is a measure of how full the hash table is allowed to
// * get before its capacity is automatically increased.  When the number of
// * entries in the hash table exceeds the product of the load factor and the
// * current capacity, the hash table is <i>rehashed</i> (that is, internal data
// * structures are rebuilt) so that the hash table has approximately twice the
// * number of buckets.
// *
// * <p>As a general rule, the default load factor (.75) offers a good
// * tradeoff between time and space costs.  Higher values decrease the
// * space overhead but increase the lookup cost (reflected in most of
// * the operations of the {@code ShortHashMap} class, including
// * {@code get} and {@code put}).  The expected number of entries in
// * the map and its load factor should be taken into account when
// * setting its initial capacity, so as to minimize the number of
// * rehash operations.  If the initial capacity is greater than the
// * maximum number of entries divided by the load factor, no rehash
// * operations will ever occur.
// *
// * <p>If many mappings are to be stored in a {@code ShortHashMap}
// * instance, creating it with a sufficiently large capacity will allow
// * the mappings to be stored more efficiently than letting it perform
// * automatic rehashing as needed to grow the table.  Note that using
// * many keys with the same {@code hashCode()} is a sure way to slow
// * down performance of any hash table. To ameliorate impact, when keys
// * are {@link Comparable}, this class may use comparison order among
// * keys to help break ties.
// *
// * <p><strong>Note that this implementation is not synchronized.</strong>
// * If multiple threads access a hash map concurrently, and at least one of
// * the threads modifies the map structurally, it <i>must</i> be
// * synchronized externally.  (A structural modification is any operation
// * that adds or deletes one or more mappings; merely changing the value
// * associated with a key that an instance already contains is not a
// * structural modification.)  This is typically accomplished by
// * synchronizing on some object that naturally encapsulates the map.
// * <p>
// * If no such object exists, the map should be "wrapped" using the
// * {@link Collections#synchronizedMap Collections.synchronizedMap}
// * method.  This is best done at creation time, to prevent accidental
// * unsynchronized access to the map:<pre>
// *   Map m = Collections.synchronizedMap(new ShortHashMap(...));</pre>
// *
// * <p>The iterators returned by all of this class's "collection view methods"
// * are <i>fail-fast</i>: if the map is structurally modified at any time after
// * the iterator is created, in any way except through the iterator's own
// * {@code remove} method, the iterator will throw a
// * {@link ConcurrentModificationException}.  Thus, in the face of concurrent
// * modification, the iterator fails quickly and cleanly, rather than risking
// * arbitrary, non-deterministic behavior at an undetermined time in the
// * future.
// *
// * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
// * as it is, generally speaking, impossible to make any hard guarantees in the
// * presence of unsynchronized concurrent modification.  Fail-fast iterators
// * throw {@code ConcurrentModificationException} on a best-effort basis.
// * Therefore, it would be wrong to write a program that depended on this
// * exception for its correctness: <i>the fail-fast behavior of iterators
// * should be used only to detect bugs.</i>
// *
// * <p>This class is a member of the
// * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
// * Java Collections Framework</a>.
// *
// * @param <V> the type of mapped values
// * @author Doug Lea
// * @author Josh Bloch
// * @author Arthur van Hoff
// * @author Neal Gafter
// * @see Object#hashCode()
// * @see Collection
// * @see Map
// * @see TreeMap
// * @see Hashtable
// * @since 1.2
// */
//public class ShortHashMap<V> extends AbstractShortMap<V>
//        implements ShortMap<V>, Cloneable, Serializable {
//    /*
//     * Implementation notes.
//     *
//     * This map usually acts as a binned (bucketed) hash table, but
//     * when bins get too large, they are transformed into bins of
//     * TreeNodes, each structured similarly to those in
//     * java.util.TreeMap. Most methods try to use normal bins, but
//     * relay to TreeNode methods when applicable (simply by checking
//     * instanceof a node).  Bins of TreeNodes may be traversed and
//     * used like any others, but additionally support faster lookup
//     * when overpopulated. However, since the vast majority of bins in
//     * normal use are not overpopulated, checking for existence of
//     * tree bins may be delayed in the course of table methods.
//     *
//     * Tree bins (i.e., bins whose elements are all TreeNodes) are
//     * ordered primarily by hashCode, but in the case of ties, if two
//     * elements are of the same "class C implements Comparable<C>",
//     * type then their compareTo method is used for ordering. (We
//     * conservatively check generic types via reflection to validate
//     * this -- see method comparableClassFor).  The added complexity
//     * of tree bins is worthwhile in providing worst-case O(log n)
//     * operations when keys either have distinct hashes or are
//     * orderable, Thus, performance degrades gracefully under
//     * accidental or malicious usages in which hashCode() methods
//     * return values that are poorly distributed, as well as those in
//     * which many keys share a hashCode, so long as they are also
//     * Comparable. (If neither of these apply, we may waste about a
//     * factor of two in time and space compared to taking no
//     * precautions. But the only known cases stem from poor user
//     * programming practices that are already so slow that this makes
//     * little difference.)
//     *
//     * Because TreeNodes are about twice the size of regular nodes, we
//     * use them only when bins contain enough nodes to warrant use
//     * (see TREEIFY_THRESHOLD). And when they become too small (due to
//     * removal or resizing) they are converted back to plain bins.  In
//     * usages with well-distributed user hashCodes, tree bins are
//     * rarely used.  Ideally, under random hashCodes, the frequency of
//     * nodes in bins follows a Poisson distribution
//     * (http://en.wikipedia.org/wiki/Poisson_distribution) with a
//     * parameter of about 0.5 on average for the default resizing
//     * threshold of 0.75, although with a large variance because of
//     * resizing granularity. Ignoring variance, the expected
//     * occurrences of list size k are (exp(-0.5) * pow(0.5, k) /
//     * factorial(k)). The first values are:
//     *
//     * 0:    0.60653066
//     * 1:    0.30326533
//     * 2:    0.07581633
//     * 3:    0.01263606
//     * 4:    0.00157952
//     * 5:    0.00015795
//     * 6:    0.00001316
//     * 7:    0.00000094
//     * 8:    0.00000006
//     * more: less than 1 in ten million
//     *
//     * The root of a tree bin is normally its first node.  However,
//     * sometimes (currently only upon Iterator.remove), the root might
//     * be elsewhere, but can be recovered following parent links
//     * (method TreeNode.root()).
//     *
//     * All applicable internal methods accept a hash code as an
//     * argument (as normally supplied from a public method), allowing
//     * them to call each other without recomputing user hashCodes.
//     * Most internal methods also accept a "tab" argument, that is
//     * normally the current table, but may be a new or old one when
//     * resizing or converting.
//     *
//     * When bin lists are treeified, split, or untreeified, we keep
//     * them in the same relative access/traversal order (i.e., field
//     * Node.next) to better preserve locality, and to slightly
//     * simplify handling of splits and traversals that invoke
//     * iterator.remove. When using comparators on insertion, to keep a
//     * total ordering (or as close as is required here) across
//     * rebalancings, we compare classes and identityHashCodes as
//     * tie-breakers.
//     *
//     * The use and transitions among plain vs tree modes is
//     * complicated by the existence of subclass LinkedShortHashMap. See
//     * below for hook methods defined to be invoked upon insertion,
//     * removal and access that allow LinkedShortHashMap internals to
//     * otherwise remain independent of these mechanics. (This also
//     * requires that a map instance be passed to some utility methods
//     * that may create new nodes.)
//     *
//     * The concurrent-programming-like SSA-based coding style helps
//     * avoid aliasing errors amid all of the twisty pointer operations.
//     */
//
//    /**
//     * The default initial capacity - MUST be a power of two.
//     */
//    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//
//    /**
//     * The maximum capacity, used if a higher value is implicitly specified
//     * by either of the constructors with arguments.
//     * MUST be a power of two <= 1<<30.
//     */
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    /**
//     * The load factor used when none specified in constructor.
//     */
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    /**
//     * The bin count threshold for using a tree rather than list for a
//     * bin.  Bins are converted to trees when adding an element to a
//     * bin with at least this many nodes. The value must be greater
//     * than 2 and should be at least 8 to mesh with assumptions in
//     * tree removal about conversion back to plain bins upon
//     * shrinkage.
//     */
//    static final int TREEIFY_THRESHOLD = 8;
//
//    /**
//     * The bin count threshold for untreeifying a (split) bin during a
//     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
//     * most 6 to mesh with shrinkage detection under removal.
//     */
//    static final int UNTREEIFY_THRESHOLD = 6;
//
//    /**
//     * The smallest table capacity for which bins may be treeified.
//     * (Otherwise the table is resized if too many nodes in a bin.)
//     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
//     * between resizing and treeification thresholds.
//     */
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//    /**
//     * Basic hash bin node, used for most entries.  (See below for
//     * TreeNode subclass, and in LinkedShortHashMap for its Entry subclass.)
//     */
//    static class ShortNode<V> implements ShortMap.ShortEntry<V> {
//        final int hash;
//        final short key;
//        V value;
//        ShortNode<V> next;
//
//        ShortNode(int hash, short key, V value, ShortNode<V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        @Override
//        public short getKeyPrimitive() {
//            return key;
//        }
//
//        @Override
//        public V getValue() {
//            return value;
//        }
//
//        @Override
//        public String toString() {
//            return key + "=" + value;
//        }
//
//        @Override
//        public int hashCode() {
//            return Short.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        @Override
//        public V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (o == this) {
//                return true;
//            }
//            if (o instanceof ShortMap.ShortEntry) {
//                ShortMap.ShortEntry<?> e = (ShortMap.ShortEntry<?>) o;
//                return (key == e.getKeyPrimitive()) &&
//                        Objects.equals(value, e.getValue());
//            }
//            if (o instanceof Map.Entry) {
//                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//                return Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue());
//            }
//            return false;
//        }
//    }
//
//    /* ---------------- Static utilities -------------- */
//
//    /**
//     * Computes key.hashCode() and spreads (XORs) higher bits of hash
//     * to lower.  Because the table uses power-of-two masking, sets of
//     * hashes that vary only in bits above the current mask will
//     * always collide. (Among known examples are sets of Float keys
//     * holding consecutive whole numbers in small tables.)  So we
//     * apply a transform that spreads the impact of higher bits
//     * downward. There is a tradeoff between speed, utility, and
//     * quality of bit-spreading. Because many common sets of hashes
//     * are already reasonably distributed (so don't benefit from
//     * spreading), and because we use trees to handle large sets of
//     * collisions in bins, we just XOR some shifted bits in the
//     * cheapest possible way to reduce systematic lossage, as well as
//     * to incorporate impact of the highest bits that would otherwise
//     * never be used in index calculations because of table bounds.
//     */
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//    /**
//     * Primitive replacement of hash(Object key)
//     *
//     * @see ShortHashMap#hash(Object key)
//     */
//    static final int hash(short key) {
//        return hashPrimitive(key);
//    }
//
//    /**
//     * Primitive replacement of hash(Object key)
//     *
//     * @see ShortHashMap#hash(Object key)
//     */
//    static final int hashPrimitive(short key) {
//        int h;
//        return (h = Short.hashCode(key)) ^ (h >>> 16);
//    }
//
//    /**
//     * Returns x's Class if it is of the form "class C implements
//     * Comparable<C>", else null.
//     */
//    static Class<?> comparableClassFor(Object x) {
//        if (x instanceof Comparable) {
//            Class<?> c;
//            Type[] ts, as;
//            ParameterizedType p;
//
//            if ((c = x.getClass()) == String.class) {
//                // bypass checks
//                return c;
//            }
//            if ((ts = c.getGenericInterfaces()) != null) {
//                for (Type t : ts) {
//                    if ((t instanceof ParameterizedType)
//                            &&
//                            ((p = (ParameterizedType) t)
//                                    .getRawType() == Comparable.class
//                            )
//                            && (as = p.getActualTypeArguments()) != null
//                            && as.length == 1 && as[0] == c
//                    ) // type arg is c
//                    {
//                        return c;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Returns k.compareTo(x) if x matches kc (k's screened comparable
//     * class), else 0.
//     */
//    @SuppressWarnings({"rawtypes", "unchecked"}) // for cast to Comparable
//    static int compareComparables(Class<?> kc, Object k, Object x) {
//        return (x == null || x.getClass() != kc ? 0 :
//                ((Comparable) k).compareTo(x));
//    }
//
//    /**
//     * Returns a power of two size for the given target capacity.
//     */
//    static final int tableSizeFor(int cap) {
//        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }
//
//    /* ---------------- Fields -------------- */
//
//    /**
//     * The table, initialized on first use, and resized as
//     * necessary. When allocated, length is always a power of two.
//     * (We also tolerate length zero in some operations to allow
//     * bootstrapping mechanics that are currently not needed.)
//     */
//    transient ShortHashMap.ShortNode<V>[] table;
//
//    /**
//     * Holds cached entrySet(). Note that AbstractMap fields are used
//     * for keySet() and values().
//     */
//    transient Set<ShortHashMap.ShortEntry<V>> entrySet;
//
//    /**
//     * The number of key-value mappings contained in this map.
//     */
//    transient int size;
//
//    /**
//     * The number of times this ShortHashMap has been structurally modified
//     * Structural modifications are those that change the number of mappings in
//     * the ShortHashMap or otherwise modify its internal structure (e.g.,
//     * rehash).  This field is used to make iterators on Collection-views of
//     * the ShortHashMap fail-fast.  (See ConcurrentModificationException).
//     */
//    transient int modCount;
//
//    /**
//     * The next size value at which to resize (capacity * load factor).
//     *
//     * @serial
//     */
//    // (The javadoc description is true upon serialization.
//    // Additionally, if the table array has not been allocated, this
//    // field holds the initial array capacity, or zero signifying
//    // DEFAULT_INITIAL_CAPACITY.)
//    int threshold;
//
//    /**
//     * The load factor for the hash table.
//     *
//     * @serial
//     */
//    final float loadFactor;
//
//    /* ---------------- Public operations -------------- */
//
//    /**
//     * Constructs an empty {@code ShortHashMap} with the specified initial
//     * capacity and load factor.
//     *
//     * @param initialCapacity the initial capacity
//     * @param loadFactor      the load factor
//     * @throws IllegalArgumentException if the initial capacity is negative
//     *                                  or the load factor is nonpositive
//     */
//    public ShortHashMap(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0) {
//            throw new IllegalArgumentException("Illegal initial capacity: " +
//                    initialCapacity);
//        }
//        if (initialCapacity > MAXIMUM_CAPACITY) {
//            initialCapacity = MAXIMUM_CAPACITY;
//        }
//        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
//            throw new IllegalArgumentException("Illegal load factor: " +
//                    loadFactor);
//        }
//        this.loadFactor = loadFactor;
//        this.threshold = tableSizeFor(initialCapacity);
//    }
//
//    /**
//     * Constructs an empty {@code ShortHashMap} with the specified initial
//     * capacity and the default load factor (0.75).
//     *
//     * @param initialCapacity the initial capacity.
//     * @throws IllegalArgumentException if the initial capacity is negative.
//     */
//    public ShortHashMap(int initialCapacity) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//    /**
//     * Constructs an empty {@code ShortHashMap} with the default initial capacity
//     * (16) and the default load factor (0.75).
//     */
//    public ShortHashMap() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
//    }
//
//    /**
//     * Constructs a new {@code ShortHashMap} with the same mappings as the
//     * specified {@code Map}.  The {@code ShortHashMap} is created with
//     * default load factor (0.75) and an initial capacity sufficient to
//     * hold the mappings in the specified {@code Map}.
//     *
//     * @param m the map whose mappings are to be placed in this map
//     * @throws NullPointerException if the specified map is null
//     */
//    public ShortHashMap(Map<? extends Short, ? extends V> m) {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);
//    }
//
//    /**
//     * Implements Map.putAll and Map constructor.
//     *
//     * @param m     the map
//     * @param evict false when initially constructing this map, else
//     *              true (relayed to method afterNodeInsertion).
//     */
//    final void putMapEntries(Map<? extends Short, ? extends V> m, boolean evict) {
//        int s = m.size();
//        if (s > 0) {
//            if (table == null) { // pre-size
//                float ft = ((float) s / loadFactor) + 1.0F;
//                int t = ((ft < (float) MAXIMUM_CAPACITY) ?
//                        (int) ft : MAXIMUM_CAPACITY);
//                if (t > threshold) {
//                    threshold = tableSizeFor(t);
//                }
//            } else if (s > threshold) {
//                resize();
//            }
//            for (Map.Entry<? extends Short, ? extends V> e : m.entrySet()) {
//                short key;
//                if (e instanceof ShortHashMap.ShortEntry) {
//                    key = ((ShortHashMap.ShortEntry) e).getKeyPrimitive();
//                } else {
//                    key = e.getKey();
//                }
//
//                V value = e.getValue();
//                putVal(hash(key), key, value, false, evict);
//            }
//        }
//    }
//
//    /**
//     * Returns the number of key-value mappings in this map.
//     *
//     * @return the number of key-value mappings in this map
//     */
//    @Override
//    public int size() {
//        return size;
//    }
//
//    /**
//     * Returns {@code true} if this map contains no key-value mappings.
//     *
//     * @return {@code true} if this map contains no key-value mappings
//     */
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
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
//     *
//     * @see #put(Short, Object)
//     */
//    @Override
//    public V get(Object key) {
//        ShortHashMap.ShortNode<V> e;
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }
//
//    /**
//     * Primitive replacement of get(Object key)
//     *
//     * @see #get(Object key)
//     */
//    public V get(short key) {
//        return this.getPrimitive(key);
//    }
//
//    /**
//     * Primitive replacement of get(Object key)
//     *
//     * @see #get(Object key)
//     */
//    public V getPrimitive(short key) {
//        ShortHashMap.ShortNode<V> e;
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }
//
//    /**
//     * Implements Map.get and related methods.
//     *
//     * @param hash hash for key
//     * @param key  the key
//     * @return the node, or null if none
//     */
//    final ShortHashMap.ShortNode<V> getNode(int hash, Object key) {
//        if (key == null) {
//            return null;
//        }
//        if (!(key instanceof Short)) {
//            return null;
//        }
//        short keyShort = (Short) key;
//
//        ShortHashMap.ShortNode<V>[] tab;
//        ShortHashMap.ShortNode<V> first, e;
//        int n;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (first = tab[(n - 1) & hash]) != null) {
//
//            if (first.hash == hash && (first.key == keyShort)) {
//                // always check first node
//                return first;
//            }
//            if ((e = first.next) != null) {
//                if (first instanceof ShortHashMap.ShortTreeNode) {
//                    return ((ShortHashMap.ShortTreeNode<V>) first).getTreeNode(hash, key);
//                }
//                do {
//                    if (e.hash == hash &&
//                            (e.key == keyShort)) {
//                        return e;
//                    }
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Returns {@code true} if this map contains a mapping for the
//     * specified key.
//     *
//     * @param key The key whose presence in this map is to be tested
//     * @return {@code true} if this map contains a mapping for the specified
//     * key.
//     */
//    @Override
//    public boolean containsKey(Object key) {
//        return getNode(hash(key), key) != null;
//    }
//
//    /**
//     * Primitive replacement of containsKey(Object key)
//     *
//     * @see #containsKey(Object key)
//     */
//    public boolean containsKey(short key) {
//        return this.containsKeyPrimitive(key);
//    }
//
//    /**
//     * Primitive replacement of containsKey(Object key)
//     *
//     * @see #containsKey(Object key)
//     */
//    public boolean containsKeyPrimitive(short key) {
//        return getNode(hash(key), key) != null;
//    }
//
//    /**
//     * Associates the specified value with the specified key in this map.
//     * If the map previously contained a mapping for the key, the old
//     * value is replaced.
//     *
//     * @param key   key with which the specified value is to be associated
//     * @param value value to be associated with the specified key
//     * @return the previous value associated with {@code key}, or
//     * {@code null} if there was no mapping for {@code key}.
//     * (A {@code null} return can also indicate that the map
//     * previously associated {@code null} with {@code key}.)
//     */
//    @Override
//    public V put(Short key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    /**
//     * Primitive replacement of put(Short key, V value)
//     *
//     * @see #put(Short key, V value);
//     */
//    public V put(short key, V value) {
//        return this.putPrimitive(key, value);
//    }
//
//    /**
//     * Primitive replacement of put(Short key, V value)
//     *
//     * @see #put(Short key, V value);
//     */
//    public V putPrimitive(short key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    /**
//     * Implements Map.put and related methods.
//     *
//     * @param hash         hash for key
//     * @param key          the key
//     * @param value        the value to put
//     * @param onlyIfAbsent if true, don't change existing value
//     * @param evict        if false, the table is in creation mode.
//     * @return previous value, or null if none
//     */
//    final V putVal(int hash, Short key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        if (key == null) {
//            return null;
//        }
//        if (!(key instanceof Short)) {
//            return null;
//        }
//        short keyShort = key;
//        ShortHashMap.ShortNode<V>[] tab;
//        ShortHashMap.ShortNode<V> p;
//        int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0) {
//            n = (tab = resize()).length;
//        }
//        if ((p = tab[i = (n - 1) & hash]) == null) {
//            tab[i] = newNode(hash, key, value, null);
//        } else {
//            ShortHashMap.ShortNode<V> e;
//            short k;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k)))) {
//                e = p;
//            } else if (p instanceof ShortHashMap.ShortTreeNode) {
//                e = ((ShortHashMap.ShortTreeNode<V>) p).putTreeVal(this, tab, hash, key, value);
//            } else {
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                        {
//                            treeifyBin(tab, hash);
//                        }
//                        break;
//                    }
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        break;
//                    }
//                    p = e;
//                }
//            }
//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null) {
//                    e.value = value;
//                }
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold) {
//            resize();
//        }
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//    /**
//     * Initializes or shorts table size.  If null, allocates in
//     * accord with initial capacity target held in field threshold.
//     * Otherwise, because we are using power-of-two expansion, the
//     * elements from each bin must either stay at same index, or move
//     * with a power of two offset in the new table.
//     *
//     * @return the table
//     */
//    final ShortHashMap.ShortNode<V>[] resize() {
//        ShortHashMap.ShortNode<V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        if (oldCap > 0) {
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
//                    oldCap >= DEFAULT_INITIAL_CAPACITY) {
//                newThr = oldThr << 1; // short threshold
//            }
//        } else if (oldThr > 0) // initial capacity was placed in threshold
//        {
//            newCap = oldThr;
//        } else {               // zero initial threshold signifies using defaults
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
//        }
//        if (newThr == 0) {
//            float ft = (float) newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
//                    (int) ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        @SuppressWarnings({"rawtypes", "unchecked"})
//        ShortHashMap.ShortNode<V>[] newTab = (ShortHashMap.ShortNode<V>[]) new ShortHashMap.ShortNode[newCap];
//        table = newTab;
//        if (oldTab != null) {
//            for (int j = 0; j < oldCap; ++j) {
//                ShortHashMap.ShortNode<V> e;
//                if ((e = oldTab[j]) != null) {
//                    oldTab[j] = null;
//                    if (e.next == null) {
//                        newTab[e.hash & (newCap - 1)] = e;
//                    } else if (e instanceof ShortHashMap.ShortTreeNode) {
//                        ((ShortHashMap.ShortTreeNode<V>) e).split(this, newTab, j, oldCap);
//                    } else { // preserve order
//                        ShortHashMap.ShortNode<V> loHead = null, loTail = null;
//                        ShortHashMap.ShortNode<V> hiHead = null, hiTail = null;
//                        ShortHashMap.ShortNode<V> next;
//                        do {
//                            next = e.next;
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null) {
//                                    loHead = e;
//                                } else {
//                                    loTail.next = e;
//                                }
//                                loTail = e;
//                            } else {
//                                if (hiTail == null) {
//                                    hiHead = e;
//                                } else {
//                                    hiTail.next = e;
//                                }
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//    /**
//     * Replaces all linked nodes in bin at index for given hash unless
//     * table is too small, in which case resizes instead.
//     */
//    final void treeifyBin(ShortHashMap.ShortNode<V>[] tab, int hash) {
//        int n, index;
//        ShortHashMap.ShortNode<V> e;
//        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) {
//            resize();
//        } else if ((e = tab[index = (n - 1) & hash]) != null) {
//            ShortHashMap.ShortTreeNode<V> hd = null, tl = null;
//            do {
//                ShortHashMap.ShortTreeNode<V> p = replacementTreeNode(e, null);
//                if (tl == null) {
//                    hd = p;
//                } else {
//                    p.prev = tl;
//                    tl.next = p;
//                }
//                tl = p;
//            } while ((e = e.next) != null);
//            if ((tab[index] = hd) != null) {
//                hd.treeify(tab);
//            }
//        }
//    }
//
//    /**
//     * Copies all of the mappings from the specified map to this map.
//     * These mappings will replace any mappings that this map had for
//     * any of the keys currently in the specified map.
//     *
//     * @param m mappings to be stored in this map
//     * @throws NullPointerException if the specified map is null
//     */
//    @Override
//    public void putAll(Map<? extends Short, ? extends V> m) {
//        putMapEntries(m, true);
//    }
//
//    /**
//     * Removes the mapping for the specified key from this map if present.
//     *
//     * @param key key whose mapping is to be removed from the map
//     * @return the previous value associated with {@code key}, or
//     * {@code null} if there was no mapping for {@code key}.
//     * (A {@code null} return can also indicate that the map
//     * previously associated {@code null} with {@code key}.)
//     */
//    @Override
//    public V remove(Object key) {
//        ShortHashMap.ShortNode<V> e;
//        return (e = removeNode(hash(key), key, null, false, true)) == null ?
//                null : e.value;
//    }
//
//    /**
//     * Primitive replacement of remove(Object key)
//     *
//     * @see #remove(Object key)
//     */
//    public V remove(short key) {
//        return this.removePrimitive(key);
//    }
//
//    /**
//     * Primitive replacement of remove(Object key)
//     *
//     * @see #remove(Object key)
//     */
//    public V removePrimitive(short key) {
//        ShortHashMap.ShortNode<V> e;
//        return (e = removeNode(hash(key), key, null, false, true)) == null ?
//                null : e.value;
//    }
//
//    /**
//     * Implements Map.remove and related methods.
//     *
//     * @param hash       hash for key
//     * @param key        the key
//     * @param value      the value to match if matchValue, else ignored
//     * @param matchValue if true only remove if value is equal
//     * @param movable    if false do not move other nodes while removing
//     * @return the node, or null if none
//     */
//    final ShortHashMap.ShortNode<V> removeNode(int hash, Object key, Object value,
//                                                 boolean matchValue, boolean movable) {
//        if (key == null) {
//            return null;
//        }
//        if (!(key instanceof Short)) {
//            return null;
//        }
//        return removeNode(hash, key, value, matchValue, movable);
//    }
//
//    /**
//     * Primitive replacement of removeNode(int hash, Object key, Object value,
//     * boolean matchValue, boolean movable)
//     *
//     * @see #removeNode(int hash, Object key, Object value,
//     * boolean matchValue, boolean movable)
//     */
//    final ShortHashMap.ShortNode<V> removeNode(int hash, short key, Object value,
//                                                 boolean matchValue, boolean movable) {
//        return this.removeNodePrimitive(hash, key, value, matchValue, movable);
//    }
//
//    /**
//     * Primitive replacement of removeNode(int hash, Object key, Object value,
//     * boolean matchValue, boolean movable)
//     *
//     * @see #removeNode(int hash, Object key, Object value,
//     * boolean matchValue, boolean movable)
//     */
//    final ShortHashMap.ShortNode<V> removeNodePrimitive(int hash, short key, Object value,
//                                                          boolean matchValue, boolean movable) {
//        ShortHashMap.ShortNode<V>[] tab;
//        ShortHashMap.ShortNode<V> p;
//        int n, index;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (p = tab[index = (n - 1) & hash]) != null) {
//            ShortHashMap.ShortNode<V> node = null, e;
//            Short k;
//            V v;
//            if (p.hash == hash && ((k = p.key) == key)) {
//                node = p;
//            } else if ((e = p.next) != null) {
//                if (p instanceof ShortHashMap.ShortTreeNode) {
//                    node = ((ShortHashMap.ShortTreeNode<V>) p).getTreeNode(hash, key);
//                } else {
//                    do {
//                        if (e.hash == hash && ((k = e.key) == key)) {
//                            node = e;
//                            break;
//                        }
//                        p = e;
//                    } while ((e = e.next) != null);
//                }
//            }
//            if (node != null && (!matchValue || (v = node.value) == value ||
//                    (value != null && value.equals(v)))) {
//                if (node instanceof ShortHashMap.ShortTreeNode) {
//                    ((ShortHashMap.ShortTreeNode<V>) node).removeTreeNode(this, tab, movable);
//                } else if (node == p) {
//                    tab[index] = node.next;
//                } else {
//                    p.next = node.next;
//                }
//                ++modCount;
//                --size;
//                afterNodeRemoval(node);
//                return node;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Removes all of the mappings from this map.
//     * The map will be empty after this call returns.
//     */
//    @Override
//    public void clear() {
//        ShortHashMap.ShortNode<V>[] tab;
//        modCount++;
//        if ((tab = table) != null && size > 0) {
//            size = 0;
//            for (int i = 0; i < tab.length; ++i) {
//                tab[i] = null;
//            }
//        }
//    }
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
//        ShortHashMap.ShortNode<V>[] tab;
//        V v;
//        if ((tab = table) != null && size > 0) {
//            for (ShortHashMap.ShortNode<V> e : tab) {
//                for (; e != null; e = e.next) {
//                    if ((v = e.value) == value ||
//                            (value != null && value.equals(v))) {
//                        return true;
//                    }
//                }
//            }
//        }
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
//     *
//     * @return a set view of the keys contained in this map
//     */
//    @Override
//    public Set<Short> keySet() {
//        Set<Short> ks = keySet;
//        if (ks == null) {
//            ks = new ShortHashMap.KeySet();
//            keySet = ks;
//        }
//        return ks;
//    }
//
//    final class ShortKeySet extends AbstractSet<Short> {
//        @Override
//        public final int size() {
//            return size;
//        }
//
//        @Override
//        public final void clear() {
//            ShortHashMap.this.clear();
//        }
//
//        @Override
//        public final Iterator<Short> iterator() {
//            return new ShortHashMap.KeyIterator();
//        }
//
//        @Override
//        public final boolean contains(Object o) {
//            return containsKey(o);
//        }
//
//        public final boolean contains(short o) {
//            return containsPrimitive(o);
//        }
//
//        public final boolean containsPrimitive(short o) {
//            return containsKey(o);
//        }
//
//        @Override
//        public final boolean remove(Object key) {
//            return removeNode(hash(key), key, null, false, true) != null;
//        }
//
//        public final boolean remove(short key) {
//            return this.removePrimitive(key);
//        }
//
//        public final boolean removePrimitive(short key) {
//            return removeNode(hash(key), key, null, false, true) != null;
//        }
//
//        @Override
//        public final Spliterator<Short> spliterator() {
//            return new ShortHashMap.KeySpliterator<>(ShortHashMap.this, 0, -1, 0, 0);
//        }
//
//        @Override
//        public final void forEach(Consumer<? super Short> action) {
//            ShortHashMap.ShortNode<V>[] tab;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            if (size > 0 && (tab = table) != null) {
//                int mc = modCount;
//                for (ShortHashMap.ShortNode<V> e : tab) {
//                    for (; e != null; e = e.next) {
//                        action.accept(e.key);
//                    }
//                }
//                if (modCount != mc) {
//                    throw new ConcurrentModificationException();
//                }
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
//     *
//     * @return a view of the values contained in this map
//     */
//    @Override
//    public Collection<V> values() {
//        Collection<V> vs = values;
//        if (vs == null) {
//            vs = new ShortHashMap.Values();
//            values = vs;
//        }
//        return vs;
//    }
//
//    final class Values extends AbstractCollection<V> {
//        @Override
//        public final int size() {
//            return size;
//        }
//
//        @Override
//        public final void clear() {
//            ShortHashMap.this.clear();
//        }
//
//        @Override
//        public final Iterator<V> iterator() {
//            return new ShortHashMap.ValueIterator();
//        }
//
//        @Override
//        public final boolean contains(Object o) {
//            return containsValue(o);
//        }
//
//        public final boolean contains(short o) {
//            return this.containsPrimitive(o);
//        }
//
//        public final boolean containsPrimitive(short o) {
//            return containsValue(o);
//        }
//
//        @Override
//        public final Spliterator<V> spliterator() {
//            return new ShortHashMap.ValueSpliterator<>(ShortHashMap.this, 0, -1, 0, 0);
//        }
//
//        @Override
//        public final void forEach(Consumer<? super V> action) {
//            ShortHashMap.ShortNode<V>[] tab;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            if (size > 0 && (tab = table) != null) {
//                int mc = modCount;
//                for (ShortHashMap.ShortNode<V> e : tab) {
//                    for (; e != null; e = e.next) {
//                        action.accept(e.value);
//                    }
//                }
//                if (modCount != mc) {
//                    throw new ConcurrentModificationException();
//                }
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
//     *
//     * @return a set view of the mappings contained in this map
//     */
//    public Set<ShortHashMap.ShortEntry<V>> entrySet() {
//        Set<ShortHashMap.ShortEntry<V>> es;
//        return (es = entrySet) == null ? (entrySet = new ShortHashMap.EntrySet()) : es;
//    }
//
//    final class EntrySet extends AbstractSet<ShortHashMap.ShortEntry<V>> {
//        public final int size() {
//            return size;
//        }
//
//        public final void clear() {
//            ShortHashMap.this.clear();
//        }
//
//        public final Iterator<ShortHashMap.ShortEntry<V>> iterator() {
//            return new ShortHashMap.EntryIterator();
//        }
//
//        public final boolean contains(Object o) {
//            if (!(o instanceof Map.Entry)) {
//                return false;
//            }
//            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//            Object key = e.getKey();
//            ShortHashMap.ShortNode<V> candidate = getNode(hash(key), key);
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
//        public final Spliterator<ShortHashMap.ShortEntry<V>> spliterator() {
//            return new ShortHashMap.EntrySpliterator<>(ShortHashMap.this, 0, -1, 0, 0);
//        }
//
//        public final void forEach(Consumer<? super ShortHashMap.ShortEntry<V>> action) {
//            ShortHashMap.ShortNode<V>[] tab;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            if (size > 0 && (tab = table) != null) {
//                int mc = modCount;
//                for (ShortHashMap.ShortNode<V> e : tab) {
//                    for (; e != null; e = e.next) {
//                        action.accept(e);
//                    }
//                }
//                if (modCount != mc) {
//                    throw new ConcurrentModificationException();
//                }
//            }
//        }
//    }
//
//    // Overrides of JDK8 Map extension methods
//
//    @Override
//    public V getOrDefault(Object key, V defaultValue) {
//        ShortHashMap.ShortNode<V> e;
//        return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;
//    }
//
//    @Override
//    public V putIfAbsent(Short key, V value) {
//        return putVal(hash(key), key, value, true, true);
//    }
//
//    @Override
//    public boolean remove(Object key, Object value) {
//        return removeNode(hash(key), key, value, true, true) != null;
//    }
//
//    @Override
//    public boolean replace(Short key, V oldValue, V newValue) {
//        ShortHashMap.ShortNode<V> e;
//        V v;
//        if ((e = getNode(hash(key), key)) != null &&
//                ((v = e.value) == oldValue || (v != null && v.equals(oldValue)))) {
//            e.value = newValue;
//            afterNodeAccess(e);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public V replace(Short key, V value) {
//        ShortHashMap.ShortNode<V> e;
//        if ((e = getNode(hash(key), key)) != null) {
//            V oldValue = e.value;
//            e.value = value;
//            afterNodeAccess(e);
//            return oldValue;
//        }
//        return null;
//    }
//
//    /**
//     * {@inheritDoc}
//     *
//     * <p>This method will, on a best-effort basis, throw a
//     * {@link ConcurrentModificationException} if it is detected that the
//     * mapping function modifies this map during computation.
//     *
//     * @throws ConcurrentModificationException if it is detected that the
//     *                                         mapping function modified this map
//     */
//    @Override
//    public V computeIfAbsent(Short key,
//                             Function<? super K, ? extends V> mappingFunction) {
//        if (mappingFunction == null) {
//            throw new NullPointerException();
//        }
//        int hash = hash(key);
//        ShortHashMap.ShortNode<V>[] tab;
//        ShortHashMap.ShortNode<V> first;
//        int n, i;
//        int binCount = 0;
//        ShortHashMap.ShortTreeNode<V> t = null;
//        ShortHashMap.ShortNode<V> old = null;
//        if (size > threshold || (tab = table) == null ||
//                (n = tab.length) == 0) {
//            n = (tab = resize()).length;
//        }
//        if ((first = tab[i = (n - 1) & hash]) != null) {
//            if (first instanceof ShortHashMap.ShortTreeNode) {
//                old = (t = (ShortHashMap.ShortTreeNode<V>) first).getTreeNode(hash, key);
//            } else {
//                ShortHashMap.ShortNode<V> e = first;
//                Short k;
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        old = e;
//                        break;
//                    }
//                    ++binCount;
//                } while ((e = e.next) != null);
//            }
//            V oldValue;
//            if (old != null && (oldValue = old.value) != null) {
//                afterNodeAccess(old);
//                return oldValue;
//            }
//        }
//        int mc = modCount;
//        V v = mappingFunction.apply(key);
//        if (mc != modCount) {
//            throw new ConcurrentModificationException();
//        }
//        if (v == null) {
//            return null;
//        } else if (old != null) {
//            old.value = v;
//            afterNodeAccess(old);
//            return v;
//        } else if (t != null) {
//            t.putTreeVal(this, tab, hash, key, v);
//        } else {
//            tab[i] = newNode(hash, key, v, first);
//            if (binCount >= TREEIFY_THRESHOLD - 1) {
//                treeifyBin(tab, hash);
//            }
//        }
//        modCount = mc + 1;
//        ++size;
//        afterNodeInsertion(true);
//        return v;
//    }
//
//    /**
//     * {@inheritDoc}
//     *
//     * <p>This method will, on a best-effort basis, throw a
//     * {@link ConcurrentModificationException} if it is detected that the
//     * remapping function modifies this map during computation.
//     *
//     * @throws ConcurrentModificationException if it is detected that the
//     *                                         remapping function modified this map
//     */
//    @Override
//    public V computeIfPresent(Short key,
//                              BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        if (remappingFunction == null) {
//            throw new NullPointerException();
//        }
//        ShortHashMap.ShortNode<V> e;
//        V oldValue;
//        int hash = hash(key);
//        if ((e = getNode(hash, key)) != null &&
//                (oldValue = e.value) != null) {
//            int mc = modCount;
//            V v = remappingFunction.apply(key, oldValue);
//            if (mc != modCount) {
//                throw new ConcurrentModificationException();
//            }
//            if (v != null) {
//                e.value = v;
//                afterNodeAccess(e);
//                return v;
//            } else {
//                removeNode(hash, key, null, false, true);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * {@inheritDoc}
//     *
//     * <p>This method will, on a best-effort basis, throw a
//     * {@link ConcurrentModificationException} if it is detected that the
//     * remapping function modifies this map during computation.
//     *
//     * @throws ConcurrentModificationException if it is detected that the
//     *                                         remapping function modified this map
//     */
//    @Override
//    public V compute(Short key,
//                     BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        if (remappingFunction == null) {
//            throw new NullPointerException();
//        }
//        int hash = hash(key);
//        ShortHashMap.ShortNode<V>[] tab;
//        ShortHashMap.ShortNode<V> first;
//        int n, i;
//        int binCount = 0;
//        ShortHashMap.ShortTreeNode<V> t = null;
//        ShortHashMap.ShortNode<V> old = null;
//        if (size > threshold || (tab = table) == null ||
//                (n = tab.length) == 0) {
//            n = (tab = resize()).length;
//        }
//        if ((first = tab[i = (n - 1) & hash]) != null) {
//            if (first instanceof ShortHashMap.ShortTreeNode) {
//                old = (t = (ShortHashMap.ShortTreeNode<V>) first).getTreeNode(hash, key);
//            } else {
//                ShortHashMap.ShortNode<V> e = first;
//                Short k;
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        old = e;
//                        break;
//                    }
//                    ++binCount;
//                } while ((e = e.next) != null);
//            }
//        }
//        V oldValue = (old == null) ? null : old.value;
//        int mc = modCount;
//        V v = remappingFunction.apply(key, oldValue);
//        if (mc != modCount) {
//            throw new ConcurrentModificationException();
//        }
//        if (old != null) {
//            if (v != null) {
//                old.value = v;
//                afterNodeAccess(old);
//            } else {
//                removeNode(hash, key, null, false, true);
//            }
//        } else if (v != null) {
//            if (t != null) {
//                t.putTreeVal(this, tab, hash, key, v);
//            } else {
//                tab[i] = newNode(hash, key, v, first);
//                if (binCount >= TREEIFY_THRESHOLD - 1) {
//                    treeifyBin(tab, hash);
//                }
//            }
//            modCount = mc + 1;
//            ++size;
//            afterNodeInsertion(true);
//        }
//        return v;
//    }
//
//    /**
//     * {@inheritDoc}
//     *
//     * <p>This method will, on a best-effort basis, throw a
//     * {@link ConcurrentModificationException} if it is detected that the
//     * remapping function modifies this map during computation.
//     *
//     * @throws ConcurrentModificationException if it is detected that the
//     *                                         remapping function modified this map
//     */
//    @Override
//    public V merge(Short key, V value,
//                   BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
//        if (value == null || remappingFunction == null) {
//            throw new NullPointerException();
//        }
//        int hash = hash(key);
//        ShortHashMap.ShortNode<V>[] tab;
//        ShortHashMap.ShortNode<V> first;
//        int n, i;
//        int binCount = 0;
//        ShortHashMap.ShortTreeNode<V> t = null;
//        ShortHashMap.ShortNode<V> old = null;
//        if (size > threshold || (tab = table) == null ||
//                (n = tab.length) == 0) {
//            n = (tab = resize()).length;
//        }
//        if ((first = tab[i = (n - 1) & hash]) != null) {
//            if (first instanceof ShortHashMap.ShortTreeNode) {
//                old = (t = (ShortHashMap.ShortTreeNode<V>) first).getTreeNode(hash, key);
//            } else {
//                ShortHashMap.ShortNode<V> e = first;
//                Short k;
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        old = e;
//                        break;
//                    }
//                    ++binCount;
//                } while ((e = e.next) != null);
//            }
//        }
//        if (old != null) {
//            V v;
//            if (old.value != null) {
//                int mc = modCount;
//                v = remappingFunction.apply(old.value, value);
//                if (mc != modCount) {
//                    throw new ConcurrentModificationException();
//                }
//            } else {
//                v = value;
//            }
//            if (v != null) {
//                old.value = v;
//                afterNodeAccess(old);
//            } else {
//                removeNode(hash, key, null, false, true);
//            }
//            return v;
//        } else {
//            if (t != null) {
//                t.putTreeVal(this, tab, hash, key, value);
//            } else {
//                tab[i] = newNode(hash, key, value, first);
//                if (binCount >= TREEIFY_THRESHOLD - 1) {
//                    treeifyBin(tab, hash);
//                }
//            }
//            ++modCount;
//            ++size;
//            afterNodeInsertion(true);
//            return value;
//        }
//    }
//
//    @Override
//    public void forEach(BiConsumer<? super K, ? super V> action) {
//        ShortHashMap.ShortNode<V>[] tab;
//        if (action == null) {
//            throw new NullPointerException();
//        }
//        if (size > 0 && (tab = table) != null) {
//            int mc = modCount;
//            for (ShortHashMap.ShortNode<V> e : tab) {
//                for (; e != null; e = e.next) {
//                    action.accept(e.key, e.value);
//                }
//            }
//            if (modCount != mc) {
//                throw new ConcurrentModificationException();
//            }
//        }
//    }
//
//    @Override
//    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
//        ShortHashMap.ShortNode<V>[] tab;
//        if (function == null) {
//            throw new NullPointerException();
//        }
//        if (size > 0 && (tab = table) != null) {
//            int mc = modCount;
//            for (ShortHashMap.ShortNode<V> e : tab) {
//                for (; e != null; e = e.next) {
//                    e.value = function.apply(e.key, e.value);
//                }
//            }
//            if (modCount != mc) {
//                throw new ConcurrentModificationException();
//            }
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // Cloning and serialization
//
//    /**
//     * Returns a shallow copy of this {@code ShortHashMap} instance: the keys and
//     * values themselves are not cloned.
//     *
//     * @return a shallow copy of this map
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public Object clone() {
//        ShortHashMap<V> result;
//        try {
//            result = (ShortHashMap<V>) super.clone();
//        } catch (CloneNotSupportedException e) {
//            // this shouldn't happen, since we are Cloneable
//            throw new InternalError(e);
//        }
//        result.reinitialize();
//        result.putMapEntries(this, false);
//        return result;
//    }
//
//    // These methods are also used when serializing HashSets
//    final float loadFactor() {
//        return loadFactor;
//    }
//
//    final int capacity() {
//        return (table != null) ? table.length :
//                (threshold > 0) ? threshold :
//                        DEFAULT_INITIAL_CAPACITY;
//    }
//
//    /**
//     * Saves this map to a stream (that is, serializes it).
//     *
//     * @param s the stream
//     * @throws IOException if an I/O error occurs
//     * @serialData The <i>capacity</i> of the ShortHashMap (the length of the
//     * bucket array) is emitted (int), followed by the
//     * <i>size</i> (an int, the number of key-value
//     * mappings), followed by the key (Object) and value (Object)
//     * for each key-value mapping.  The key-value mappings are
//     * emitted in no particular order.
//     */
//    private void writeObject(java.io.ObjectOutputStream s)
//            throws IOException {
//        int buckets = capacity();
//        // Write out the threshold, loadfactor, and any hidden stuff
//        s.defaultWriteObject();
//        s.writeInt(buckets);
//        s.writeInt(size);
//        internalWriteEntries(s);
//    }
//
//    /**
//     * Reconstitutes this map from a stream (that is, deserializes it).
//     *
//     * @param s the stream
//     * @throws ClassNotFoundException if the class of a serialized object
//     *                                could not be found
//     * @throws IOException            if an I/O error occurs
//     */
//    private void readObject(java.io.ObjectInputStream s)
//            throws IOException, ClassNotFoundException {
//        // Read in the threshold (ignored), loadfactor, and any hidden stuff
//        s.defaultReadObject();
//        reinitialize();
//        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
//            throw new InvalidObjectException("Illegal load factor: " +
//                    loadFactor);
//        }
//        s.readInt();                // Read and ignore number of buckets
//        int mappings = s.readInt(); // Read number of mappings (size)
//        if (mappings < 0) {
//            throw new InvalidObjectException("Illegal mappings count: " +
//                    mappings);
//        } else if (mappings > 0) { // (if zero, use defaults)
//            // Size the table using given load factor only if within
//            // range of 0.25...4.0
//            float lf = Math.min(Math.max(0.25f, loadFactor), 4.0f);
//            float fc = (float) mappings / lf + 1.0f;
//            int cap = ((fc < DEFAULT_INITIAL_CAPACITY) ?
//                    DEFAULT_INITIAL_CAPACITY :
//                    (fc >= MAXIMUM_CAPACITY) ?
//                            MAXIMUM_CAPACITY :
//                            tableSizeFor((int) fc));
//            float ft = (float) cap * lf;
//            threshold = ((cap < MAXIMUM_CAPACITY && ft < MAXIMUM_CAPACITY) ?
//                    (int) ft : Integer.MAX_VALUE);
//
//            // Check Map.Entry[].class since it's the nearest public type to
//            // what we're actually creating.
//            SharedSecrets.getJavaObjectInputStreamAccess().checkArray(s, Map.Entry[].class, cap);
//            @SuppressWarnings({"rawtypes", "unchecked"})
//            ShortHashMap.ShortNode<V>[] tab = (ShortHashMap.ShortNode<V>[]) new ShortHashMap.Node[cap];
//            table = tab;
//
//            // Read the keys and values, and put the mappings in the ShortHashMap
//            for (int i = 0; i < mappings; i++) {
//                @SuppressWarnings("unchecked")
//                Short key = (Short) s.readObject();
//                @SuppressWarnings("unchecked")
//                V value = (V) s.readObject();
//                putVal(hash(key), key, value, false, false);
//            }
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // iterators
//
//    abstract class HashIterator {
//        ShortHashMap.ShortNode<V> next;        // next entry to return
//        ShortHashMap.ShortNode<V> current;     // current entry
//        int expectedModCount;  // for fast-fail
//        int index;             // current slot
//
//        HashIterator() {
//            expectedModCount = modCount;
//            ShortHashMap.ShortNode<V>[] t = table;
//            current = next = null;
//            index = 0;
//            if (t != null && size > 0) { // advance to first entry
//                do {
//                } while (index < t.length && (next = t[index++]) == null);
//            }
//        }
//
//        public final boolean hasNext() {
//            return next != null;
//        }
//
//        final ShortHashMap.ShortNode<V> nextNode() {
//            ShortHashMap.ShortNode<V>[] t;
//            ShortHashMap.ShortNode<V> e = next;
//            if (modCount != expectedModCount) {
//                throw new ConcurrentModificationException();
//            }
//            if (e == null) {
//                throw new NoSuchElementException();
//            }
//            if ((next = (current = e).next) == null && (t = table) != null) {
//                do {
//                } while (index < t.length && (next = t[index++]) == null);
//            }
//            return e;
//        }
//
//        public final void remove() {
//            ShortHashMap.ShortNode<V> p = current;
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
//    final class KeyIterator extends ShortHashMap.HashIterator
//            implements Iterator<Short> {
//        public final Short next() {
//            return nextNode().key;
//        }
//    }
//
//    final class ValueIterator extends ShortHashMap.HashIterator
//            implements Iterator<V> {
//        public final V next() {
//            return nextNode().value;
//        }
//    }
//
//    final class EntryIterator extends ShortHashMap.HashIterator
//            implements Iterator<ShortHashMap.ShortEntry<V>> {
//        public final ShortHashMap.ShortEntry<V> next() {
//            return nextNode();
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // spliterators
//
//    static class ShortHashMapSpliterator<Short, V> {
//        final ShortHashMap<V> map;
//        ShortHashMap.ShortNode<V> current;          // current node
//        int index;                  // current index, modified on advance/split
//        int fence;                  // one past last index
//        int est;                    // size estimate
//        int expectedModCount;       // for comodification checks
//
//        ShortHashMapSpliterator(ShortHashMap<V> m, int origin,
//                                 int fence, int est,
//                                 int expectedModCount) {
//            this.map = m;
//            this.index = origin;
//            this.fence = fence;
//            this.est = est;
//            this.expectedModCount = expectedModCount;
//        }
//
//        final int getFence() { // initialize fence and size on first use
//            int hi;
//            if ((hi = fence) < 0) {
//                ShortHashMap<V> m = map;
//                est = m.size;
//                expectedModCount = m.modCount;
//                ShortHashMap.ShortNode<V>[] tab = m.table;
//                hi = fence = (tab == null) ? 0 : tab.length;
//            }
//            return hi;
//        }
//
//        public final long estimateSize() {
//            getFence(); // force init
//            return (long) est;
//        }
//    }
//
//    static final class KeySpliterator<Short, V>
//            extends ShortHashMap.ShortHashMapSpliterator<Short, V>
//            implements Spliterator<Short> {
//        KeySpliterator(ShortHashMap<V> m, int origin, int fence, int est,
//                       int expectedModCount) {
//            super(m, origin, fence, est, expectedModCount);
//        }
//
//        public ShortHashMap.KeySpliterator<Short, V> trySplit() {
//            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
//            return (lo >= mid || current != null) ? null :
//                    new ShortHashMap.KeySpliterator<>(map, lo, index = mid, est >>>= 1,
//                            expectedModCount);
//        }
//
//        public void forEachRemaining(Consumer<? super K> action) {
//            int i, hi, mc;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            ShortHashMap<V> m = map;
//            ShortHashMap.ShortNode<V>[] tab = m.table;
//            if ((hi = fence) < 0) {
//                mc = expectedModCount = m.modCount;
//                hi = fence = (tab == null) ? 0 : tab.length;
//            } else {
//                mc = expectedModCount;
//            }
//            if (tab != null && tab.length >= hi &&
//                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
//                ShortHashMap.ShortNode<V> p = current;
//                current = null;
//                do {
//                    if (p == null) {
//                        p = tab[i++];
//                    } else {
//                        action.accept(p.key);
//                        p = p.next;
//                    }
//                } while (p != null || i < hi);
//                if (m.modCount != mc) {
//                    throw new ConcurrentModificationException();
//                }
//            }
//        }
//
//        public boolean tryAdvance(Consumer<? super K> action) {
//            int hi;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            ShortHashMap.ShortNode<V>[] tab = map.table;
//            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
//                while (current != null || index < hi) {
//                    if (current == null) {
//                        current = tab[index++];
//                    } else {
//                        Short k = current.key;
//                        current = current.next;
//                        action.accept(k);
//                        if (map.modCount != expectedModCount) {
//                            throw new ConcurrentModificationException();
//                        }
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        public int characteristics() {
//            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
//                    Spliterator.DISTINCT;
//        }
//    }
//
//    static final class ValueSpliterator<Short, V>
//            extends ShortHashMap.ShortHashMapSpliterator<Short, V>
//            implements Spliterator<V> {
//        ValueSpliterator(ShortHashMap<V> m, int origin, int fence, int est,
//                         int expectedModCount) {
//            super(m, origin, fence, est, expectedModCount);
//        }
//
//        public ShortHashMap.ValueSpliterator<Short, V> trySplit() {
//            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
//            return (lo >= mid || current != null) ? null :
//                    new ShortHashMap.ValueSpliterator<>(map, lo, index = mid, est >>>= 1,
//                            expectedModCount);
//        }
//
//        public void forEachRemaining(Consumer<? super V> action) {
//            int i, hi, mc;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            ShortHashMap<V> m = map;
//            ShortHashMap.ShortNode<V>[] tab = m.table;
//            if ((hi = fence) < 0) {
//                mc = expectedModCount = m.modCount;
//                hi = fence = (tab == null) ? 0 : tab.length;
//            } else {
//                mc = expectedModCount;
//            }
//            if (tab != null && tab.length >= hi &&
//                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
//                ShortHashMap.ShortNode<V> p = current;
//                current = null;
//                do {
//                    if (p == null) {
//                        p = tab[i++];
//                    } else {
//                        action.accept(p.value);
//                        p = p.next;
//                    }
//                } while (p != null || i < hi);
//                if (m.modCount != mc) {
//                    throw new ConcurrentModificationException();
//                }
//            }
//        }
//
//        public boolean tryAdvance(Consumer<? super V> action) {
//            int hi;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            ShortHashMap.ShortNode<V>[] tab = map.table;
//            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
//                while (current != null || index < hi) {
//                    if (current == null) {
//                        current = tab[index++];
//                    } else {
//                        V v = current.value;
//                        current = current.next;
//                        action.accept(v);
//                        if (map.modCount != expectedModCount) {
//                            throw new ConcurrentModificationException();
//                        }
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        public int characteristics() {
//            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
//        }
//    }
//
//    static final class EntrySpliterator<Short, V>
//            extends ShortHashMap.ShortHashMapSpliterator<Short, V>
//            implements Spliterator<ShortHashMap.ShortEntry<V>> {
//        EntrySpliterator(ShortHashMap<V> m, int origin, int fence, int est,
//                         int expectedModCount) {
//            super(m, origin, fence, est, expectedModCount);
//        }
//
//        public ShortHashMap.EntrySpliterator<Short, V> trySplit() {
//            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
//            return (lo >= mid || current != null) ? null :
//                    new ShortHashMap.EntrySpliterator<>(map, lo, index = mid, est >>>= 1,
//                            expectedModCount);
//        }
//
//        public void forEachRemaining(Consumer<? super ShortHashMap.ShortEntry<V>> action) {
//            int i, hi, mc;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            ShortHashMap<V> m = map;
//            ShortHashMap.ShortNode<V>[] tab = m.table;
//            if ((hi = fence) < 0) {
//                mc = expectedModCount = m.modCount;
//                hi = fence = (tab == null) ? 0 : tab.length;
//            } else {
//                mc = expectedModCount;
//            }
//            if (tab != null && tab.length >= hi &&
//                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
//                ShortHashMap.ShortNode<V> p = current;
//                current = null;
//                do {
//                    if (p == null) {
//                        p = tab[i++];
//                    } else {
//                        action.accept(p);
//                        p = p.next;
//                    }
//                } while (p != null || i < hi);
//                if (m.modCount != mc) {
//                    throw new ConcurrentModificationException();
//                }
//            }
//        }
//
//        public boolean tryAdvance(Consumer<? super ShortHashMap.ShortEntry<V>> action) {
//            int hi;
//            if (action == null) {
//                throw new NullPointerException();
//            }
//            ShortHashMap.ShortNode<V>[] tab = map.table;
//            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
//                while (current != null || index < hi) {
//                    if (current == null) {
//                        current = tab[index++];
//                    } else {
//                        ShortHashMap.ShortNode<V> e = current;
//                        current = current.next;
//                        action.accept(e);
//                        if (map.modCount != expectedModCount) {
//                            throw new ConcurrentModificationException();
//                        }
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        public int characteristics() {
//            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
//                    Spliterator.DISTINCT;
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // LinkedShortHashMap support
//
//
//    /*
//     * The following package-protected methods are designed to be
//     * overridden by LinkedShortHashMap, but not by any other subclass.
//     * Nearly all other internal methods are also package-protected
//     * but are declared final, so can be used by LinkedShortHashMap, view
//     * classes, and HashSet.
//     */
//
//    // Create a regular (non-tree) node
//    ShortHashMap.ShortNode<V> newNode(int hash, Short key, V value, ShortHashMap.ShortNode<V> next) {
//        return new ShortHashMap.Node<>(hash, key, value, next);
//    }
//
//    // For conversion from TreeNodes to plain nodes
//    ShortHashMap.ShortNode<V> replacementNode(ShortHashMap.ShortNode<V> p, ShortHashMap.ShortNode<V> next) {
//        return new ShortHashMap.Node<>(p.hash, p.key, p.value, next);
//    }
//
//    // Create a tree bin node
//    ShortHashMap.ShortTreeNode<V> newTreeNode(int hash, Short key, V value, ShortHashMap.ShortNode<V> next) {
//        return new ShortHashMap.ShortTreeNode<>(hash, key, value, next);
//    }
//
//    // For treeifyBin
//    ShortHashMap.ShortTreeNode<V> replacementTreeNode(ShortHashMap.ShortNode<V> p,
//                                                        ShortHashMap.ShortNode<V> next) {
//        return new ShortHashMap.ShortTreeNode<>(p.hash, p.key, p.value, next);
//    }
//
//    /**
//     * Reset to initial default state.  Called by clone and readObject.
//     */
//    void reinitialize() {
//        table = null;
//        entrySet = null;
//        keySet = null;
//        values = null;
//        modCount = 0;
//        threshold = 0;
//        size = 0;
//    }
//
//    // Callbacks to allow LinkedShortHashMap post-actions
//    void afterNodeAccess(ShortHashMap.ShortNode<V> p) {
//    }
//
//    void afterNodeInsertion(boolean evict) {
//    }
//
//    void afterNodeRemoval(ShortHashMap.ShortNode<V> p) {
//    }
//
//    // Called only from writeObject, to ensure compatible ordering.
//    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException {
//        ShortHashMap.ShortNode<V>[] tab;
//        if (size > 0 && (tab = table) != null) {
//            for (ShortHashMap.ShortNode<V> e : tab) {
//                for (; e != null; e = e.next) {
//                    s.writeObject(e.key);
//                    s.writeObject(e.value);
//                }
//            }
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // Tree bins
//
//    /**
//     * Entry for Tree bins. Extends LinkedShortHashMap.Entry (which in turn
//     * extends Node) so can be used as extension of either regular or
//     * linked node.
//     */
//    static final class ShortTreeNode<V> extends ShortLinkedHashMap.ShortEntry<V> {
//        ShortHashMap.ShortTreeNode<V> parent;  // red-black tree links
//        ShortHashMap.ShortTreeNode<V> left;
//        ShortHashMap.ShortTreeNode<V> right;
//        ShortHashMap.ShortTreeNode<V> prev;    // needed to unlink next upon deletion
//        boolean red;
//
//        ShortTreeNode(int hash, Short key, V val, ShortHashMap.ShortNode<V> next) {
//            super(hash, key, val, next);
//        }
//
//        /**
//         * Returns root of tree containing this node.
//         */
//        final ShortHashMap.ShortTreeNode<V> root() {
//            for (ShortHashMap.ShortTreeNode<V> r = this, p; ; ) {
//                if ((p = r.parent) == null) {
//                    return r;
//                }
//                r = p;
//            }
//        }
//
//        /**
//         * Ensures that the given root is the first node of its bin.
//         */
//        static <Short, V> void moveRootToFront(ShortHashMap.ShortNode<V>[] tab,
//                                                ShortHashMap.ShortTreeNode<V> root) {
//            int n;
//            if (root != null && tab != null && (n = tab.length) > 0) {
//                int index = (n - 1) & root.hash;
//                ShortHashMap.ShortTreeNode<V> first = (ShortHashMap.ShortTreeNode<V>) tab[index];
//                if (root != first) {
//                    ShortHashMap.ShortNode<V> rn;
//                    tab[index] = root;
//                    ShortHashMap.ShortTreeNode<V> rp = root.prev;
//                    if ((rn = root.next) != null) {
//                        ((ShortHashMap.ShortTreeNode<V>) rn).prev = rp;
//                    }
//                    if (rp != null) {
//                        rp.next = rn;
//                    }
//                    if (first != null) {
//                        first.prev = root;
//                    }
//                    root.next = first;
//                    root.prev = null;
//                }
//                assert checkInvariants(root);
//            }
//        }
//
//        /**
//         * Finds the node starting at root p with the given hash and key.
//         * The kc argument caches comparableClassFor(key) upon first use
//         * comparing keys.
//         */
//        final ShortHashMap.ShortTreeNode<V> find(int h, Object k, Class<?> kc) {
//            ShortHashMap.ShortTreeNode<V> p = this;
//            do {
//                int ph, dir;
//                Short pk;
//                ShortHashMap.ShortTreeNode<V> pl = p.left, pr = p.right, q;
//                if ((ph = p.hash) > h) {
//                    p = pl;
//                } else if (ph < h) {
//                    p = pr;
//                } else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
//                    return p;
//                } else if (pl == null) {
//                    p = pr;
//                } else if (pr == null) {
//                    p = pl;
//                } else if ((kc != null ||
//                        (kc = comparableClassFor(k)) != null) &&
//                        (dir = compareComparables(kc, k, pk)) != 0) {
//                    p = (dir < 0) ? pl : pr;
//                } else if ((q = pr.find(h, k, kc)) != null) {
//                    return q;
//                } else {
//                    p = pl;
//                }
//            } while (p != null);
//            return null;
//        }
//
//        /**
//         * Calls find for root node.
//         */
//        final ShortHashMap.ShortTreeNode<V> getTreeNode(int h, Object k) {
//            return ((parent != null) ? root() : this).find(h, k, null);
//        }
//
//        /**
//         * Tie-breaking utility for ordering insertions when equal
//         * hashCodes and non-comparable. We don't require a total
//         * order, just a consistent insertion rule to maintain
//         * equivalence across rebalancings. Tie-breaking further than
//         * necessary simplifies testing a bit.
//         */
//        static int tieBreakOrder(Object a, Object b) {
//            int d;
//            if (a == null || b == null ||
//                    (d = a.getClass().getName().
//                            compareTo(b.getClass().getName())) == 0) {
//                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
//                        -1 : 1);
//            }
//            return d;
//        }
//
//        /**
//         * Forms tree of the nodes linked from this node.
//         */
//        final void treeify(ShortHashMap.ShortNode<V>[] tab) {
//            ShortHashMap.ShortTreeNode<V> root = null;
//            for (ShortHashMap.ShortTreeNode<V> x = this, next; x != null; x = next) {
//                next = (ShortHashMap.ShortTreeNode<V>) x.next;
//                x.left = x.right = null;
//                if (root == null) {
//                    x.parent = null;
//                    x.red = false;
//                    root = x;
//                } else {
//                    Short k = x.key;
//                    int h = x.hash;
//                    Class<?> kc = null;
//                    for (ShortHashMap.ShortTreeNode<V> p = root; ; ) {
//                        int dir, ph;
//                        Short pk = p.key;
//                        if ((ph = p.hash) > h) {
//                            dir = -1;
//                        } else if (ph < h) {
//                            dir = 1;
//                        } else if ((kc == null &&
//                                (kc = comparableClassFor(k)) == null) ||
//                                (dir = compareComparables(kc, k, pk)) == 0) {
//                            dir = tieBreakOrder(k, pk);
//                        }
//
//                        ShortHashMap.ShortTreeNode<V> xp = p;
//                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                            x.parent = xp;
//                            if (dir <= 0) {
//                                xp.left = x;
//                            } else {
//                                xp.right = x;
//                            }
//                            root = balanceInsertion(root, x);
//                            break;
//                        }
//                    }
//                }
//            }
//            moveRootToFront(tab, root);
//        }
//
//        /**
//         * Returns a list of non-TreeNodes replacing those linked from
//         * this node.
//         */
//        final ShortHashMap.ShortNode<V> untreeify(ShortHashMap<V> map) {
//            ShortHashMap.ShortNode<V> hd = null, tl = null;
//            for (ShortHashMap.ShortNode<V> q = this; q != null; q = q.next) {
//                ShortHashMap.ShortNode<V> p = map.replacementNode(q, null);
//                if (tl == null) {
//                    hd = p;
//                } else {
//                    tl.next = p;
//                }
//                tl = p;
//            }
//            return hd;
//        }
//
//        /**
//         * Tree version of putVal.
//         */
//        final ShortHashMap.ShortTreeNode<V> putTreeVal(ShortHashMap<V> map,
//                                                         ShortHashMap.ShortNode<V>[] tab,
//                                                         int h, Short k, V v) {
//            Class<?> kc = null;
//            boolean searched = false;
//            ShortHashMap.ShortTreeNode<V> root = (parent != null) ? root() : this;
//            for (ShortHashMap.ShortTreeNode<V> p = root; ; ) {
//                int dir, ph;
//                Short pk;
//                if ((ph = p.hash) > h) {
//                    dir = -1;
//                } else if (ph < h) {
//                    dir = 1;
//                } else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
//                    return p;
//                } else if ((kc == null &&
//                        (kc = comparableClassFor(k)) == null) ||
//                        (dir = compareComparables(kc, k, pk)) == 0) {
//                    if (!searched) {
//                        ShortHashMap.ShortTreeNode<V> q, ch;
//                        searched = true;
//                        if (((ch = p.left) != null &&
//                                (q = ch.find(h, k, kc)) != null) ||
//                                ((ch = p.right) != null &&
//                                        (q = ch.find(h, k, kc)) != null)) {
//                            return q;
//                        }
//                    }
//                    dir = tieBreakOrder(k, pk);
//                }
//
//                ShortHashMap.ShortTreeNode<V> xp = p;
//                if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                    ShortHashMap.ShortNode<V> xpn = xp.next;
//                    ShortHashMap.ShortTreeNode<V> x = map.newTreeNode(h, k, v, xpn);
//                    if (dir <= 0) {
//                        xp.left = x;
//                    } else {
//                        xp.right = x;
//                    }
//                    xp.next = x;
//                    x.parent = x.prev = xp;
//                    if (xpn != null) {
//                        ((ShortHashMap.ShortTreeNode<V>) xpn).prev = x;
//                    }
//                    moveRootToFront(tab, balanceInsertion(root, x));
//                    return null;
//                }
//            }
//        }
//
//        /**
//         * Removes the given node, that must be present before this call.
//         * This is messier than typical red-black deletion code because we
//         * cannot swap the contents of an interior node with a leaf
//         * successor that is pinned by "next" pointers that are accessible
//         * independently during traversal. So instead we swap the tree
//         * linkages. If the current tree appears to have too few nodes,
//         * the bin is converted back to a plain bin. (The test triggers
//         * somewhere between 2 and 6 nodes, depending on tree structure).
//         */
//        final void removeTreeNode(ShortHashMap<V> map, ShortHashMap.ShortNode<V>[] tab,
//                                  boolean movable) {
//            int n;
//            if (tab == null || (n = tab.length) == 0) {
//                return;
//            }
//            int index = (n - 1) & hash;
//            ShortHashMap.ShortTreeNode<V> first = (ShortHashMap.ShortTreeNode<V>) tab[index], root = first, rl;
//            ShortHashMap.ShortTreeNode<V> succ = (ShortHashMap.ShortTreeNode<V>) next, pred = prev;
//            if (pred == null) {
//                tab[index] = first = succ;
//            } else {
//                pred.next = succ;
//            }
//            if (succ != null) {
//                succ.prev = pred;
//            }
//            if (first == null) {
//                return;
//            }
//            if (root.parent != null) {
//                root = root.root();
//            }
//            if (root == null
//                    || (movable
//                    && (root.right == null
//                    || (rl = root.left) == null
//                    || rl.left == null))) {
//                tab[index] = first.untreeify(map);  // too small
//                return;
//            }
//            ShortHashMap.ShortTreeNode<V> p = this, pl = left, pr = right, replacement;
//            if (pl != null && pr != null) {
//                ShortHashMap.ShortTreeNode<V> s = pr, sl;
//                while ((sl = s.left) != null) // find successor
//                {
//                    s = sl;
//                }
//                boolean c = s.red;
//                s.red = p.red;
//                p.red = c; // swap colors
//                ShortHashMap.ShortTreeNode<V> sr = s.right;
//                ShortHashMap.ShortTreeNode<V> pp = p.parent;
//                if (s == pr) { // p was s's direct parent
//                    p.parent = s;
//                    s.right = p;
//                } else {
//                    ShortHashMap.ShortTreeNode<V> sp = s.parent;
//                    if ((p.parent = sp) != null) {
//                        if (s == sp.left) {
//                            sp.left = p;
//                        } else {
//                            sp.right = p;
//                        }
//                    }
//                    if ((s.right = pr) != null) {
//                        pr.parent = s;
//                    }
//                }
//                p.left = null;
//                if ((p.right = sr) != null) {
//                    sr.parent = p;
//                }
//                if ((s.left = pl) != null) {
//                    pl.parent = s;
//                }
//                if ((s.parent = pp) == null) {
//                    root = s;
//                } else if (p == pp.left) {
//                    pp.left = s;
//                } else {
//                    pp.right = s;
//                }
//                if (sr != null) {
//                    replacement = sr;
//                } else {
//                    replacement = p;
//                }
//            } else if (pl != null) {
//                replacement = pl;
//            } else if (pr != null) {
//                replacement = pr;
//            } else {
//                replacement = p;
//            }
//            if (replacement != p) {
//                ShortHashMap.ShortTreeNode<V> pp = replacement.parent = p.parent;
//                if (pp == null) {
//                    (root = replacement).red = false;
//                } else if (p == pp.left) {
//                    pp.left = replacement;
//                } else {
//                    pp.right = replacement;
//                }
//                p.left = p.right = p.parent = null;
//            }
//
//            ShortHashMap.ShortTreeNode<V> r = p.red ? root : balanceDeletion(root, replacement);
//
//            if (replacement == p) {  // detach
//                ShortHashMap.ShortTreeNode<V> pp = p.parent;
//                p.parent = null;
//                if (pp != null) {
//                    if (p == pp.left) {
//                        pp.left = null;
//                    } else if (p == pp.right) {
//                        pp.right = null;
//                    }
//                }
//            }
//            if (movable) {
//                moveRootToFront(tab, r);
//            }
//        }
//
//        /**
//         * Splits nodes in a tree bin into lower and upper tree bins,
//         * or untreeifies if now too small. Called only from resize;
//         * see above discussion about split bits and indices.
//         *
//         * @param map   the map
//         * @param tab   the table for recording bin heads
//         * @param index the index of the table being split
//         * @param bit   the bit of hash to split on
//         */
//        final void split(ShortHashMap<V> map, ShortHashMap.ShortNode<V>[] tab, int index, int bit) {
//            ShortHashMap.ShortTreeNode<V> b = this;
//            // Relink into lo and hi lists, preserving order
//            ShortHashMap.ShortTreeNode<V> loHead = null, loTail = null;
//            ShortHashMap.ShortTreeNode<V> hiHead = null, hiTail = null;
//            int lc = 0, hc = 0;
//            for (ShortHashMap.ShortTreeNode<V> e = b, next; e != null; e = next) {
//                next = (ShortHashMap.ShortTreeNode<V>) e.next;
//                e.next = null;
//                if ((e.hash & bit) == 0) {
//                    if ((e.prev = loTail) == null) {
//                        loHead = e;
//                    } else {
//                        loTail.next = e;
//                    }
//                    loTail = e;
//                    ++lc;
//                } else {
//                    if ((e.prev = hiTail) == null) {
//                        hiHead = e;
//                    } else {
//                        hiTail.next = e;
//                    }
//                    hiTail = e;
//                    ++hc;
//                }
//            }
//
//            if (loHead != null) {
//                if (lc <= UNTREEIFY_THRESHOLD) {
//                    tab[index] = loHead.untreeify(map);
//                } else {
//                    tab[index] = loHead;
//                    if (hiHead != null) // (else is already treeified)
//                    {
//                        loHead.treeify(tab);
//                    }
//                }
//            }
//            if (hiHead != null) {
//                if (hc <= UNTREEIFY_THRESHOLD) {
//                    tab[index + bit] = hiHead.untreeify(map);
//                } else {
//                    tab[index + bit] = hiHead;
//                    if (loHead != null) {
//                        hiHead.treeify(tab);
//                    }
//                }
//            }
//        }
//
//        /* ------------------------------------------------------------ */
//        // Red-black tree methods, all adapted from CLR
//
//        static <V> ShortHashMap.ShortTreeNode<V> rotateLeft(ShortHashMap.ShortTreeNode<V> root,
//                                                              ShortHashMap.ShortTreeNode<V> p) {
//            ShortHashMap.ShortTreeNode<V> r, pp, rl;
//            if (p != null && (r = p.right) != null) {
//                if ((rl = p.right = r.left) != null) {
//                    rl.parent = p;
//                }
//                if ((pp = r.parent = p.parent) == null) {
//                    (root = r).red = false;
//                } else if (pp.left == p) {
//                    pp.left = r;
//                } else {
//                    pp.right = r;
//                }
//                r.left = p;
//                p.parent = r;
//            }
//            return root;
//        }
//
//        static <V> ShortHashMap.ShortTreeNode<V> rotateRight(ShortHashMap.ShortTreeNode<V> root,
//                                                               ShortHashMap.ShortTreeNode<V> p) {
//            ShortHashMap.ShortTreeNode<V> l, pp, lr;
//            if (p != null && (l = p.left) != null) {
//                if ((lr = p.left = l.right) != null) {
//                    lr.parent = p;
//                }
//                if ((pp = l.parent = p.parent) == null) {
//                    (root = l).red = false;
//                } else if (pp.right == p) {
//                    pp.right = l;
//                } else {
//                    pp.left = l;
//                }
//                l.right = p;
//                p.parent = l;
//            }
//            return root;
//        }
//
//        static <V> ShortHashMap.ShortTreeNode<V> balanceInsertion(ShortHashMap.ShortTreeNode<V> root,
//                                                                    ShortHashMap.ShortTreeNode<V> x) {
//            x.red = true;
//            for (ShortHashMap.ShortTreeNode<V> xp, xpp, xppl, xppr; ; ) {
//                if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                } else if (!xp.red || (xpp = xp.parent) == null) {
//                    return root;
//                }
//                if (xp == (xppl = xpp.left)) {
//                    if ((xppr = xpp.right) != null && xppr.red) {
//                        xppr.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    } else {
//                        if (x == xp.right) {
//                            root = rotateLeft(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateRight(root, xpp);
//                            }
//                        }
//                    }
//                } else {
//                    if (xppl != null && xppl.red) {
//                        xppl.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    } else {
//                        if (x == xp.left) {
//                            root = rotateRight(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateLeft(root, xpp);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        static <Short, V> ShortHashMap.ShortTreeNode<V> balanceDeletion(ShortHashMap.ShortTreeNode<V> root,
//                                                                           ShortHashMap.ShortTreeNode<V> x) {
//            for (ShortHashMap.ShortTreeNode<V> xp, xpl, xpr; ; ) {
//                if (x == null || x == root) {
//                    return root;
//                } else if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                } else if (x.red) {
//                    x.red = false;
//                    return root;
//                } else if ((xpl = xp.left) == x) {
//                    if ((xpr = xp.right) != null && xpr.red) {
//                        xpr.red = false;
//                        xp.red = true;
//                        root = rotateLeft(root, xp);
//                        xpr = (xp = x.parent) == null ? null : xp.right;
//                    }
//                    if (xpr == null) {
//                        x = xp;
//                    } else {
//                        ShortHashMap.ShortTreeNode<V> sl = xpr.left, sr = xpr.right;
//                        if ((sr == null || !sr.red) &&
//                                (sl == null || !sl.red)) {
//                            xpr.red = true;
//                            x = xp;
//                        } else {
//                            if (sr == null || !sr.red) {
//                                if (sl != null) {
//                                    sl.red = false;
//                                }
//                                xpr.red = true;
//                                root = rotateRight(root, xpr);
//                                xpr = (xp = x.parent) == null ?
//                                        null : xp.right;
//                            }
//                            if (xpr != null) {
//                                xpr.red = (xp == null) ? false : xp.red;
//                                if ((sr = xpr.right) != null) {
//                                    sr.red = false;
//                                }
//                            }
//                            if (xp != null) {
//                                xp.red = false;
//                                root = rotateLeft(root, xp);
//                            }
//                            x = root;
//                        }
//                    }
//                } else { // symmetric
//                    if (xpl != null && xpl.red) {
//                        xpl.red = false;
//                        xp.red = true;
//                        root = rotateRight(root, xp);
//                        xpl = (xp = x.parent) == null ? null : xp.left;
//                    }
//                    if (xpl == null) {
//                        x = xp;
//                    } else {
//                        ShortHashMap.ShortTreeNode<V> sl = xpl.left, sr = xpl.right;
//                        if ((sl == null || !sl.red) &&
//                                (sr == null || !sr.red)) {
//                            xpl.red = true;
//                            x = xp;
//                        } else {
//                            if (sl == null || !sl.red) {
//                                if (sr != null) {
//                                    sr.red = false;
//                                }
//                                xpl.red = true;
//                                root = rotateLeft(root, xpl);
//                                xpl = (xp = x.parent) == null ?
//                                        null : xp.left;
//                            }
//                            if (xpl != null) {
//                                xpl.red = (xp == null) ? false : xp.red;
//                                if ((sl = xpl.left) != null) {
//                                    sl.red = false;
//                                }
//                            }
//                            if (xp != null) {
//                                xp.red = false;
//                                root = rotateRight(root, xp);
//                            }
//                            x = root;
//                        }
//                    }
//                }
//            }
//        }
//
//        /**
//         * Recursive invariant check
//         */
//        static <V> boolean checkInvariants(ShortHashMap.ShortTreeNode<V> t) {
//            ShortHashMap.ShortTreeNode<V> tp = t.parent, tl = t.left, tr = t.right,
//                    tb = t.prev, tn = (ShortHashMap.ShortTreeNode<V>) t.next;
//            if (tb != null && tb.next != t) {
//                return false;
//            }
//            if (tn != null && tn.prev != t) {
//                return false;
//            }
//            if (tp != null && t != tp.left && t != tp.right) {
//                return false;
//            }
//            if (tl != null && (tl.parent != t || tl.hash > t.hash)) {
//                return false;
//            }
//            if (tr != null && (tr.parent != t || tr.hash < t.hash)) {
//                return false;
//            }
//            if (t.red && tl != null && tl.red && tr != null && tr.red) {
//                return false;
//            }
//            if (tl != null && !checkInvariants(tl)) {
//                return false;
//            }
//            if (tr != null && !checkInvariants(tr)) {
//                return false;
//            }
//            return true;
//        }
//    }
//}
