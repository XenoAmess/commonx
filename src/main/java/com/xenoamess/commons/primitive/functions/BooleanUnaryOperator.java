package com.xenoamess.commons.primitive.functions;

import java.util.function.UnaryOperator;

/**
 * @author XenoAmess
 */
public interface BooleanUnaryOperator extends UnaryOperator<Boolean> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Boolean apply(Boolean t) {
        return this.applyPrimitive(t);
    }

    /**
     * Primitive replacement of apply(Boolean t)
     *
     * @param t the function argument
     * @return the function result
     * @see #apply(Boolean t)
     */
    boolean applyPrimitive(boolean t);
}
