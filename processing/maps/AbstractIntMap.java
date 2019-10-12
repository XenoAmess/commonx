package com.xenoamess.commons.primitive.collections.maps;

import com.xenoamess.commons.primitive.Primitive;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;

/**
 * This class provides a skeletal implementation of the {@code Map}
 * interface, to minimize the effort required to implement this interface.
 *
 * To implement an unmodifiable map, the programmer needs only to extend this
 * class and provide an implementation for the {@code entrySet} method, which
 * returns a set-view of the map's mappings.  Typically, the returned set
 * will, in turn, be implemented atop {@code AbstractSet}.  This set should
 * not support the {@code add} or {@code remove} methods, and its iterator
 * should not support the {@code remove} method.
 *
 * To implement a modifiable map, the programmer must additionally override
 * this class's {@code put} method (which otherwise throws an
 * {@code UnsupportedOperationException}), and the iterator returned by
 * {@code entrySet().iterator()} must additionally implement its
 * {@code remove} method.
 *
 * The programmer should generally provide a void (no argument) and map
 * constructor, as per the recommendation in the {@code Map} interface
 * specification.
 *
 * The documentation for each non-abstract method in this class describes its
 * implementation in detail.  Each of these methods may be overridden if the
 * map being implemented admits a more efficient implementation.
 *
 * This class is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @param <V> the type of mapped values
 * @author Josh Bloch
 * @author Neal Gafter
 * @version 0.8.0
 * @see Map
 * @see Collection
 * @see AbstractMap
 * @since 1.2
 */
public abstract class AbstractIntMap<V> extends AbstractMap<Integer, V> implements Primitive {

}
