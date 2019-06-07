package com.xenoamess.commons.collections.list.primitive_array_lists;

/**
 * <p>PrimitiveComparators class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
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
     * Constant <code>LONG_COMPARATOR_INCREASING</code>
     */
    public static final LongComparator LONG_COMPARATOR_INCREASING = (long d1, long d2) -> (int) (d1 - d2);
    /**
     * Constant <code>INT_COMPARATOR_INCREASING</code>
     */
    public static final IntComparator INT_COMPARATOR_INCREASING = (int d1, int d2) -> (d1 - d2);
    /**
     * Constant <code>SHORT_COMPARATOR_INCREASING</code>
     */
    public static final ShortComparator SHORT_COMPARATOR_INCREASING = (short d1, short d2) -> (d1 - d2);
    /**
     * Constant <code>CHAR_COMPARATOR_INCREASING</code>
     */
    public static final CharComparator CHAR_COMPARATOR_INCREASING = (char d1, char d2) -> (d1 - d2);
    /**
     * Constant <code>BYTE_COMPARATOR_INCREASING</code>
     */
    public static final ByteComparator BYTE_COMPARATOR_INCREASING = (byte d1, byte d2) -> (d1 - d2);
    /**
     * Constant <code>DOUBLE_COMPARATOR_INCREASING</code>
     */
    public static final DoubleComparator DOUBLE_COMPARATOR_INCREASING = (double d1, double d2) -> (int) (d1 - d2);
    /**
     * Constant <code>FLOAT_COMPARATOR_INCREASING</code>
     */
    public static final FloatComparator FLOAT_COMPARATOR_INCREASING = (float d1, float d2) -> (int) (d1 - d2);
    /**
     * Constant <code>BOOLEAN_COMPARATOR_INCREASING</code>
     */
    public static final BooleanComparator BOOLEAN_COMPARATOR_INCREASING = (boolean d1, boolean d2) -> (d1 ? (d2 ? 0 :
            1) : (d2 ? -1 : 0));

    /**
     * Constant <code>LONG_COMPARATOR_DECREASING</code>
     */
    public static final LongComparator LONG_COMPARATOR_DECREASING = (long d1, long d2) -> (int) (d2 - d1);
    /**
     * Constant <code>INT_COMPARATOR_DECREASING</code>
     */
    public static final IntComparator INT_COMPARATOR_DECREASING = (int d1, int d2) -> (d2 - d1);
    /**
     * Constant <code>SHORT_COMPARATOR_DECREASING</code>
     */
    public static final ShortComparator SHORT_COMPARATOR_DECREASING = (short d1, short d2) -> (d2 - d1);
    /**
     * Constant <code>CHAR_COMPARATOR_DECREASING</code>
     */
    public static final CharComparator CHAR_COMPARATOR_DECREASING = (char d1, char d2) -> (d2 - d1);
    /**
     * Constant <code>BYTE_COMPARATOR_DECREASING</code>
     */
    public static final ByteComparator BYTE_COMPARATOR_DECREASING = (byte d1, byte d2) -> (d2 - d1);
    /**
     * Constant <code>DOUBLE_COMPARATOR_DECREASING</code>
     */
    public static final DoubleComparator DOUBLE_COMPARATOR_DECREASING = (double d1, double d2) -> (int) (d2 - d1);
    /**
     * Constant <code>FLOAT_COMPARATOR_DECREASING</code>
     */
    public static final FloatComparator FLOAT_COMPARATOR_DECREASING = (float d1, float d2) -> (int) (d2 - d1);
    /**
     * Constant <code>BOOLEAN_COMPARATOR_DECREASING</code>
     */
    public static final BooleanComparator BOOLEAN_COMPARATOR_DECREASING = (boolean d1, boolean d2) -> (d2 ? (d1 ? 0 :
            1) : (d1 ? -1 : 0));
}
