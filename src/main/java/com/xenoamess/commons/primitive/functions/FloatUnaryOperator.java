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

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single operand that produces a result of the
 * same type as its operand.  This is a specialization of {@code Function} for
 * the case where the operand and result are of the same type.
 *
 * This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Float)}.
 *
 * @author XenoAmess
 * @version 0.8.0
 * @see Function
 * @see UnaryOperator
 * @since 1.8
 */
public interface FloatUnaryOperator extends UnaryOperator<Float>, Primitive {
    /**
     * {@inheritDoc}
     * <p>
     * Applies this function to the given argument.
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
