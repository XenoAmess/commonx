package com.xenoamess.commons.primitive.functions;

import com.xenoamess.commons.primitive.Primitive;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author XenoAmess
 */
public interface CharConsumer extends Consumer<Character>, Primitive {
    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    @Override
    default void accept(Character t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Character t)
     *
     * @param t the input argument
     */
    default void accept(char t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Character t)
     *
     * @param t the input argument
     */
    void acceptPrimitive(char t);

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
    default CharConsumer andThen(Consumer<? super Character> after) {
        Objects.requireNonNull(after);
        return new CharConsumer() {
            @Override
            public void acceptPrimitive(char t) {
                acceptPrimitive(t);
                if (after instanceof CharConsumer) {
                    ((CharConsumer) after).acceptPrimitive(t);
                } else {
                    after.accept(t);
                }
            }
        };
    }
}
