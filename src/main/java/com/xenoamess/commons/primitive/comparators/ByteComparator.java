package com.xenoamess.commons.primitive.comparators;

import com.xenoamess.commons.primitive.Primitive;

import java.util.*;


/**
 * A comparison function, which imposes a <i>total ordering</i> on some
 * collection of objects.  Comparators can be passed to a sort method (such
 * as {@link java.util.Collections#sort(List, Comparator) Collections.sort} or {@link
 * Arrays#sort(Object[], Comparator) Arrays.sort}) to allow precise control
 * over the sort order.  Comparators can also be used to control the order of
 * certain data structures (such as {@link SortedSet sorted sets} or {@link
 * SortedMap sorted maps}), or to provide an ordering for collections of
 * objects that don't have a {@link Comparable natural ordering}.<p>
 * <p>
 * The ordering imposed by a comparator {@code c} on a set of elements
 * {@code S} is said to be <i>consistent with equals</i> if and only if
 * {@code c.compare(e1, e2)==0} has the same boolean value as
 * {@code e1.equals(e2)} for every {@code e1} and {@code e2} in
 * {@code S}.<p>
 * <p>
 * Caution should be exercised when using a comparator capable of imposing an
 * ordering inconsistent with equals to order a sorted set (or sorted map).
 * Suppose a sorted set (or sorted map) with an explicit comparator {@code c}
 * is used with elements (or keys) drawn from a set {@code S}.  If the
 * ordering imposed by {@code c} on {@code S} is inconsistent with equals,
 * the sorted set (or sorted map) will behave "strangely."  In particular the
 * sorted set (or sorted map) will violate the general contract for set (or
 * map), which is defined in terms of {@code equals}.<p>
 * <p>
 * For example, suppose one adds two elements {@code a} and {@code b} such that
 * {@code (a.equals(b) && c.compare(a, b) != 0)}
 * to an empty {@code TreeSet} with comparator {@code c}.
 * The second {@code add} operation will return
 * true (and the size of the tree set will increase) because {@code a} and
 * {@code b} are not equivalent from the tree set's perspective, even though
 * this is contrary to the specification of the
 * {@link java.util.Set#add Set.add} method.<p>
 * <p>
 * Note: It is generally a good idea for comparators to also implement
 * {@code java.io.Serializable}, as they may be used as ordering methods in
 * serializable data structures (like {@link java.util.TreeSet}, {@link java.util.TreeMap}).  In
 * order for the data structure to serialize successfully, the comparator (if
 * provided) must implement {@code Serializable}.<p>
 * <p>
 * For the mathematically inclined, the <i>relation</i> that defines the
 * <i>imposed ordering</i> that a given comparator {@code c} imposes on a
 * given set of objects {@code S} is:<pre>
 *       {(x, y) such that c.compare(x, y) &lt;= 0}.
 * </pre> The <i>quotient</i> for this total order is:<pre>
 *       {(x, y) such that c.compare(x, y) == 0}.
 * </pre>
 * <p>
 * It follows immediately from the contract for {@code compare} that the
 * quotient is an <i>equivalence relation</i> on {@code S}, and that the
 * imposed ordering is a <i>total order</i> on {@code S}.  When we say that
 * the ordering imposed by {@code c} on {@code S} is <i>consistent with
 * equals</i>, we mean that the quotient for the ordering is the equivalence
 * relation defined by the objects' {@link java.lang.Object#equals(Object)
 * equals(Object)} method(s):<pre>
 *     {(x, y) such that x.equals(y)}. </pre>
 *
 * <p>Unlike {@code Comparable}, a comparator may optionally permit
 * comparison of null arguments, while maintaining the requirements for
 * an equivalence relation.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @param <T> the type of objects that may be compared by this comparator
 * @author Josh Bloch
 * @author Neal Gafter
 * @author XenoAmess
 * @version 0.6.0
 * @see Comparable
 * @see java.io.Serializable
 * @see java.util.Comparator
 * @since 1.2
 */
@FunctionalInterface
public interface ByteComparator extends Comparator<Byte>, Primitive {
    /**
     * {@inheritDoc}
     * <p>
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * The implementor must ensure that {@code sgn(compare(x, y)) ==
     * -sgn(compare(y, x))} for all {@code x} and {@code y}.  (This
     * implies that {@code compare(x, y)} must throw an exception if and only
     * if {@code compare(y, x)} throws an exception.)<p>
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * {@code ((compare(x, y)>0) && (compare(y, z)>0))} implies
     * {@code compare(x, z)>0}.<p>
     * <p>
     * Finally, the implementor must ensure that {@code compare(x, y)==0}
     * implies that {@code sgn(compare(x, z))==sgn(compare(y, z))} for all
     * {@code z}.<p>
     * <p>
     * It is generally the case, but <i>not</i> strictly required that
     * {@code (compare(x, y)==0) == (x.equals(y))}.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."<p>
     * <p>
     * In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     */
    @Override
    default int compare(Byte o1, Byte o2) {
        return comparePrimitive(o1, o2);
    }

    /**
     * Primitive replacement of {@code ByteComparator.compare(Byte o1, Byte o2)}
     *
     * @param p1 the first byte to be compared.
     * @param p2 the second byte to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @see ByteComparator#compare(Byte o1, Byte o2)
     */
    int compare(byte p1, byte p2);

    /**
     * Primitive replacement of {@code ByteComparator.compare(Byte o1, Byte o2)}
     *
     * @param p1 the first byte to be compared.
     * @param p2 the second byte to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @see ByteComparator#compare(Byte o1, Byte o2)
     */
    default int comparePrimitive(byte p1, byte p2) {
        return compare(p1, p2);
    }
}
