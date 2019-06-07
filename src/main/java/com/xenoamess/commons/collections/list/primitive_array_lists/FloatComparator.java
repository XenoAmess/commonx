package com.xenoamess.commons.collections.list.primitive_array_lists;

/**
 * <p>FloatComparator interface.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 * @see java.util.Comparator
 */
public interface FloatComparator extends PrimitiveComparator<Float> {
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
    default int compare(Float o1, Float o2) {
        return comparePrimitive(o1, o2);
    }

    /**
     * Primitive replacement of {@code FloatComparator.compare(Float o1, Float o2)}
     *
     * @param p1 the first float to be compared.
     * @param p2 the second float to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @see FloatComparator#compare(Float o1, Float o2)
     */
    int compare(float p1, float p2);

    /**
     * Primitive replacement of {@code FloatComparator.compare(Float o1, Float o2)}
     *
     * @param p1 the first float to be compared.
     * @param p2 the second float to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @see FloatComparator#compare(Float o1, Float o2)
     */
    default int comparePrimitive(float p1, float p2) {
        return compare(p1, p2);
    }
}
