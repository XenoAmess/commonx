package com.xenoamess.commons.primitive.functions;

import java.util.function.UnaryOperator;

/**
 * @author XenoAmess
 */
public interface CharUnaryOperator extends UnaryOperator<Character> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Character apply(Character t) {
        return this.applyPrimitive(t);
    }

    /**
     * Primitive replacement of apply(Character t)
     *
     * @param t the function argument
     * @return the function result
     * @see #apply(Character t)
     */
    char applyPrimitive(char t);
}
