package com.xenoamess.commons.primitive.functions;

import java.util.function.UnaryOperator;

/**
 * @author XenoAmess
 */
public interface ShortUnaryOperator extends UnaryOperator<Short> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Short apply(Short t) {
        return this.applyPrimitive(t);
    }

    /**
     * Primitive replacement of apply(Short t)
     *
     * @param t the function argument
     * @return the function result
     * @see #apply(Short t)
     */
    short applyPrimitive(short t);
}
