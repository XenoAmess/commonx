package com.xenoamess.commons.collections.list.primitive_array_lists;

/**
 * place to hold some commonly used PrimitiveComparator s.
 *
 * @author XenoAmess
 * @version 0.6.0
 * @see PrimitiveComparator
 */
public class PrimitiveComparators {
    /**
     * <p>Instances of this class should NOT be constructed in standard programming.
     *
     * <p>This constructor is public to permit tools that require a JavaBean instance
     * to operate.
     */
    public PrimitiveComparators() {
        super();
    }

    /**
     * comparator for increasing sort.
     */
    public static final LongComparator LONG_COMPARATOR_INCREASING = (long d1, long d2) -> (int) (d1 - d2);
    /**
     * comparator for increasing sort.
     */
    public static final IntComparator INT_COMPARATOR_INCREASING = (int d1, int d2) -> (d1 - d2);
    /**
     * comparator for increasing sort.
     */
    public static final ShortComparator SHORT_COMPARATOR_INCREASING = (short d1, short d2) -> (d1 - d2);
    /**
     * comparator for increasing sort.
     */
    public static final CharComparator CHAR_COMPARATOR_INCREASING = (char d1, char d2) -> (d1 - d2);
    /**
     * comparator for increasing sort.
     */
    public static final ByteComparator BYTE_COMPARATOR_INCREASING = (byte d1, byte d2) -> (d1 - d2);
    /**
     * comparator for increasing sort.
     */
    public static final DoubleComparator DOUBLE_COMPARATOR_INCREASING = (double d1, double d2) -> (int) (d1 - d2);
    /**
     * comparator for increasing sort.
     */
    public static final FloatComparator FLOAT_COMPARATOR_INCREASING = (float d1, float d2) -> (int) (d1 - d2);
    /**
     * comparator for increasing sort.
     */
    public static final BooleanComparator BOOLEAN_COMPARATOR_INCREASING = (boolean d1, boolean d2) -> (d1 ? (d2 ? 0 :
            1) : (d2 ? -1 : 0));

    /**
     * comparator for decreasing sort.
     */
    public static final LongComparator LONG_COMPARATOR_DECREASING = (long d1, long d2) -> (int) (d2 - d1);
    /**
     * comparator for decreasing sort.
     */
    public static final IntComparator INT_COMPARATOR_DECREASING = (int d1, int d2) -> (d2 - d1);
    /**
     * comparator for decreasing sort.
     */
    public static final ShortComparator SHORT_COMPARATOR_DECREASING = (short d1, short d2) -> (d2 - d1);
    /**
     * comparator for decreasing sort.
     */
    public static final CharComparator CHAR_COMPARATOR_DECREASING = (char d1, char d2) -> (d2 - d1);
    /**
     * comparator for decreasing sort.
     */
    public static final ByteComparator BYTE_COMPARATOR_DECREASING = (byte d1, byte d2) -> (d2 - d1);
    /**
     * comparator for decreasing sort.
     */
    public static final DoubleComparator DOUBLE_COMPARATOR_DECREASING = (double d1, double d2) -> (int) (d2 - d1);
    /**
     * comparator for decreasing sort.
     */
    public static final FloatComparator FLOAT_COMPARATOR_DECREASING = (float d1, float d2) -> (int) (d2 - d1);
    /**
     * comparator for decreasing sort.
     */
    public static final BooleanComparator BOOLEAN_COMPARATOR_DECREASING = (boolean d1, boolean d2) -> (d2 ? (d1 ? 0 :
            1) : (d1 ? -1 : 0));
}
