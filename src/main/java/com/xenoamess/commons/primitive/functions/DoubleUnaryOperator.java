package com.xenoamess.commons.primitive.functions;

import java.util.function.UnaryOperator;

/**
 * @author XenoAmess
 */
public interface DoubleUnaryOperator extends UnaryOperator<Double> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Double apply(Double t) {
        return this.applyPrimitive(t);
    }

    /**
     * Primitive replacement of apply(Double t)
     *
     * @param t the function argument
     * @return the function result
     * @see #apply(Double t)
     */
    double applyPrimitive(double t);
}
