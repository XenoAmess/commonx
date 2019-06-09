package com.xenoamess.commons.primitive.functions;

import java.util.function.UnaryOperator;

/**
 * @author XenoAmess
 */
public interface FloatUnaryOperator extends UnaryOperator<Float> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Float apply(Float t) {
        return this.applyPrimitive(t);
    }

    /**
     * Primitive replacement of apply(Float t)
     *
     * @param t the function argument
     * @return the function result
     * @see #apply(Float t)
     */
    float applyPrimitive(float t);
}
