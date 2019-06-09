package com.xenoamess.commons.primitive.functions;

import java.util.function.UnaryOperator;

/**
 * @author XenoAmess
 */
public interface LongUnaryOperator extends UnaryOperator<Long> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Long apply(Long t) {
        return this.applyPrimitive(t);
    }

    /**
     * Primitive replacement of apply(Long t)
     *
     * @param t the function argument
     * @return the function result
     * @see #apply(Long t)
     */
    long applyPrimitive(long t);
}
