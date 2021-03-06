/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.xenoamess.commons.primitive.functions;

import com.xenoamess.commons.primitive.Primitive;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single input argument and returns no
 * result. Unlike most other functional interfaces, {@code Consumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Byte)}.
 *
 * @author XenoAmess
 * @version 0.8.0
 * @see Consumer
 * @since 1.8
 */
public interface ByteConsumer extends Consumer<Byte>, Primitive {
    /**
     * {@inheritDoc}
     * <p>
     * Performs this operation on the given argument.
     */
    @Override
    default void accept(Byte t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Byte t)
     *
     * @param t the input argument
     */
    default void accept(byte t) {
        this.acceptPrimitive(t);
    }

    /**
     * Primitive replacement of #accept(Byte t)
     *
     * @param t the input argument
     */
    void acceptPrimitive(byte t);

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
    default ByteConsumer andThen(Consumer<? super Byte> after) {
        Objects.requireNonNull(after);
        return t -> {
            ByteConsumer.this.acceptPrimitive(t);
            if (after instanceof ByteConsumer) {
                ((ByteConsumer) after).acceptPrimitive(t);
            } else {
                after.accept(t);
            }
        };
    }
}
