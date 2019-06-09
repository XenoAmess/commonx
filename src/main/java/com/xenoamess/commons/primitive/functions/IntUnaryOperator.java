package com.xenoamess.commons.primitive.functions;

import java.util.function.UnaryOperator;

/**
 * @author XenoAmess
 */
public interface IntUnaryOperator extends UnaryOperator<Integer> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Integer apply(Integer t) {
        return this.applyPrimitive(t);
    }

    /**
     * Primitive replacement of apply(Integer t)
     *
     * @param t the function argument
     * @return the function result
     * @see #apply(Integer t)
     */
    int applyPrimitive(int t);
}
