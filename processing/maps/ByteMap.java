//package com.xenoamess.commons.primitive.collections.maps;
//
//import com.xenoamess.commons.primitive.Primitive;
//import com.xenoamess.commons.primitive.comparators.ByteComparator;
//import com.xenoamess.commons.primitive.comparators.PrimitiveComparators;
//
//import java.io.Serializable;
//import java.util.Comparator;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @author XenoAmess
// */
//public interface ByteMap<V> extends Map<Byte, V>, Primitive {
//    /**
//     * A primitive map entry (key-value pair).  The {@code Map.entrySet} method returns
//     * a collection-view of the map, whose elements are of this class.  The
//     * <i>only</i> way to obtain a reference to a map entry is from the
//     * iterator of this collection-view.  These {@code Map.Entry} objects are
//     * valid <i>only</i> for the duration of the iteration; more formally,
//     * the behavior of a map entry is undefined if the backing map has been
//     * modified after the entry was returned by the iterator, except through
//     * the {@code setValue} operation on the map entry.
//     *
//     * @see Map#entrySet()
//     * @since 1.2
//     */
//    interface ByteEntry<V> extends Map.Entry<Byte, V> {
//        /**
//         * Returns the key corresponding to this entry.
//         *
//         * @return the key corresponding to this entry
//         * @throws IllegalStateException implementations may, but are not
//         *                               required to, throw this exception if the entry has been
//         *                               removed from the backing map.
//         */
//        @Override
//        default Byte getKey() {
//            return this.getKeyPrimitive();
//        }
//
//        /**
//         * Returns the key corresponding to this entry.
//         *
//         * @return the key corresponding to this entry
//         * @throws IllegalStateException implementations may, but are not
//         *                               required to, throw this exception if the entry has been
//         *                               removed from the backing map.
//         * @see ByteEntry#getKey();
//         */
//        byte getKeyPrimitive();
//
//        /**
//         * Returns the hash code value for this map entry.  The hash code
//         * of a map entry {@code e} is defined to be: <pre>
//         *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
//         *     (e.getValue()==null ? 0 : e.getValue().hashCode())
//         * </pre>
//         * This ensures that {@code e1.equals(e2)} implies that
//         * {@code e1.hashCode()==e2.hashCode()} for any two Entries
//         * {@code e1} and {@code e2}, as required by the general
//         * contract of {@code Object.hashCode}.
//         *
//         * @return the hash code value for this map entry
//         * @see Object#hashCode()
//         * @see Object#equals(Object)
//         * @see #equals(Object)
//         */
//        @Override
//        default int hashCode() {
//            return Byte.hashCode(getKeyPrimitive());
//        }
//
//        /**
//         * Returns a comparator that compares {@link Map.Entry} in natural order on key.
//         *
//         * The returned comparator is serializable and throws {@link
//         * NullPointerException} when comparing an entry with a null key.
//         *
//         * @param <V> the type of the map values
//         * @return a comparator that compares {@link Map.Entry} in natural order on key.
//         * @see Comparable
//         * @since 1.8
//         */
//        public static <V> Comparator<ByteEntry<V>> comparingByKey() {
//            return (Comparator<ByteEntry<V>> & Serializable)
//                    (c1, c2) -> PrimitiveComparators.
//                            BYTE_COMPARATOR_INCREASING.
//                            compare(c1.getKeyPrimitive(), c2.getKeyPrimitive());
//        }
//
//        /**
//         * Returns a comparator that compares {@link Map.Entry} by key using the given
//         * {@link Comparator}.
//         *
//         * The returned comparator is serializable if the specified comparator
//         * is also serializable.
//         *
//         * @param <V> the type of the map values
//         * @param cmp the key {@link Comparator}
//         * @return a comparator that compares {@link Map.Entry} by the key.
//         * @since 1.8
//         */
//        public static <V> Comparator<ByteEntry<V>> comparingByKey(ByteComparator cmp) {
//            Objects.requireNonNull(cmp);
//            return (Comparator<ByteEntry<V>> & Serializable)
//                    (c1, c2) -> cmp.compare(c1.getKeyPrimitive(), c2.getKeyPrimitive());
//        }
//
//        /**
//         * Returns a comparator that compares {@link Map.Entry} by value using the given
//         * {@link Comparator}.
//         *
//         * The returned comparator is serializable if the specified comparator
//         * is also serializable.
//         *
//         * @param <V> the type of the map values
//         * @param cmp the value {@link Comparator}
//         * @return a comparator that compares {@link Map.Entry} by the value.
//         * @since 1.8
//         */
//        public static <V> Comparator<ByteEntry<V>> comparingByValue(Comparator<? super V> cmp) {
//            Objects.requireNonNull(cmp);
//            return (Comparator<ByteEntry<V>> & Serializable)
//                    (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
//        }
//    }
//}
