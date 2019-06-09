package com.xenoamess.commons.primitive.functions;

import java.util.function.UnaryOperator;

/**
 * @author XenoAmess
 */
public interface ByteUnaryOperator extends UnaryOperator<Byte> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Byte apply(Byte t) {
        return this.applyPrimitive(t);
    }

    /**
     * Primitive replacement of apply(Byte t)
     *
     * @param t the function argument
     * @return the function result
     * @see #apply(Byte t)
     */
    byte applyPrimitive(byte t);
}
