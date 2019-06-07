package com.xenoamess.commons.collections.list.primitive_array_lists;

/**
 * <p>ShortComparator interface.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 */
public interface ShortComparator extends PrimitiveComparator<Short> {
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
    default int compare(Short o1, Short o2) {
        return comparePrimitive(o1, o2);
    }

    /**
     * <p>compare.</p>
     *
     * @param d1 a short.
     * @param d2 a short.
     * @return a int.
     */
    int compare(short d1, short d2);

    /**
     * <p>comparePrimitive.</p>
     *
     * @param d1 a short.
     * @param d2 a short.
     * @return a int.
     */
    default int comparePrimitive(short d1, short d2) {
        return compare(d1, d2);
    }
}
