/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
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

package com.xenoamess.commons.primitive.iterators;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.functions.FloatConsumer;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * An iterator over a collection.  {@code Iterator} takes the place of
 * {@link java.util.Enumeration} in the Java Collections Framework.  Iterators
 * differ from enumerations in two ways:
 *
 * <ul>
 * <li> Iterators allow the caller to remove elements from the
 * underlying collection during the iteration with well-defined
 * semantics.
 * <li> Method names have been improved.
 * </ul>
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @author XenoAmess
 * @version 0.8.0
 * @see Collection
 * @see ListIterator
 * @see Iterable
 * @see Iterator
 * @since 1.2
 */
public interface FloatIterator extends Iterator<Float>, Primitive {

    /**
     * {@inheritDoc}
     */
    @Override
    default Float next() {
        return this.nextPrimitive();
    }

    /**
     * Primitive replacement of next()
     *
     * @return the next element in the iteration
     * @see #next()
     */
    float nextPrimitive();

    /**
     * {@inheritDoc}
     * <p>
     * Performs the given action for each remaining element until all elements
     * have been processed or the action throws an exception.  Actions are
     * performed in the order of iteration, if that order is specified.
     * Exceptions thrown by the action are relayed to the caller.
     * <p>
     * The behavior of an iterator is unspecified if the action modifies the
     * collection in any way (even by calling the {@link #remove remove} method
     * or other mutator methods of {@code Iterator} subtypes),
     * unless an overriding class has specified a concurrent modification policy.
     * <p>
     * Subsequent behavior of an iterator is unspecified if the action throws an
     * exception.
     *
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     while (hasNext())
     *         action.accept(next());
     * }</pre>
     * @since 1.8
     */
    @Override
    default void forEachRemaining(Consumer<? super Float> action) {
        Objects.requireNonNull(action);
        if (action instanceof FloatConsumer) {
            FloatConsumer actionFloatConsumer = (FloatConsumer) action;
            while (hasNext()) {
                actionFloatConsumer.acceptPrimitive(nextPrimitive());
            }
        } else {
            while (hasNext()) {
                action.accept(nextPrimitive());
            }
        }
    }
}
