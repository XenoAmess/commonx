/*
 * MIT License
 *
 * Copyright (c) 2019 XenoAmess
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.xenoamess.commons.primitive.functions;

import com.xenoamess.commons.primitive.Primitive;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * <p>IntConsumer interface.</p>
 *
 * @author XenoAmess
 * @version 0.8.0
 * @see Consumer
 */
public interface IntConsumer extends Consumer<Integer>, Primitive {
    /**
     * {@inheritDoc}
     * <p>
     * Performs this operation on the given argument.
     */
    @Override
    default void accept(Integer t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Integer t)
     *
     * @param t the input argument
     */
    default void accept(int t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Integer t)
     *
     * @param t the input argument
     */
    void acceptPrimitive(int t);

    /**
     * {@inheritDoc}
     * <p>
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     */
    @Override
    default IntConsumer andThen(Consumer<? super Integer> after) {
        Objects.requireNonNull(after);
        return new IntConsumer() {
            @Override
            public void acceptPrimitive(int t) {
                acceptPrimitive(t);
                if (after instanceof IntConsumer) {
                    ((IntConsumer) after).acceptPrimitive(t);
                } else {
                    after.accept(t);
                }
            }
        };
    }
}
