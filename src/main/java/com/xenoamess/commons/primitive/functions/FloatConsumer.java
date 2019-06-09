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
 * <p>FloatConsumer interface.</p>
 *
 * @author XenoAmess
 * @version 0.8.0
 * @see Consumer
 */
public interface FloatConsumer extends Consumer<Float>, Primitive {
    /**
     * {@inheritDoc}
     * <p>
     * Performs this operation on the given argument.
     */
    @Override
    default void accept(Float t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Float t)
     *
     * @param t the input argument
     */
    default void accept(float t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Float t)
     *
     * @param t the input argument
     */
    void acceptPrimitive(float t);

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
    default FloatConsumer andThen(Consumer<? super Float> after) {
        Objects.requireNonNull(after);
        return new FloatConsumer() {
            @Override
            public void acceptPrimitive(float t) {
                acceptPrimitive(t);
                if (after instanceof FloatConsumer) {
                    ((FloatConsumer) after).acceptPrimitive(t);
                } else {
                    after.accept(t);
                }
            }
        };
    }
}
