package com.xenoamess.commons.primitive.functions;

import com.xenoamess.commons.primitive.Primitive;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author XenoAmess
 */
public interface BooleanConsumer extends Consumer<Boolean>, Primitive {
    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    @Override
    default void accept(Boolean t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Boolean t)
     *
     * @param t the input argument
     */
    default void accept(boolean t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Boolean t)
     *
     * @param t the input argument
     */
    void acceptPrimitive(boolean t);

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    @Override
    default BooleanConsumer andThen(Consumer<? super Boolean> after) {
        Objects.requireNonNull(after);
        return new BooleanConsumer() {
            @Override
            public void acceptPrimitive(boolean t) {
                acceptPrimitive(t);
                if (after instanceof BooleanConsumer) {
                    ((BooleanConsumer) after).acceptPrimitive(t);
                } else {
                    after.accept(t);
                }
            }
        };
    }
}
