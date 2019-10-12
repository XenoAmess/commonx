/*
 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
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
package com.xenoamess.commons.primitive.collections;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.functions.ShortConsumer;
import com.xenoamess.commons.primitive.iterators.ShortIterator;
import com.xenoamess.commons.primitive.iterators.ShortSpliterator;
import com.xenoamess.commons.primitive.iterators.ShortSpliterators;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Implementing this interface allows an object to be the target of the enhanced
 * {@code for} statement (sometimes called the "for-each loop" statement).
 *
 * @author XenoAmess
 * @version 0.8.0
 * @jls 14.14.2 The enhanced {@code for} statement
 * @see Iterable
 * @since 1.5
 */
public interface ShortIterable extends Iterable<Short>, Primitive {
    /**
     * {@inheritDoc}
     * <p>
     * Returns an iterator over elements of type {@code T}.
     */
    @Override
    ShortIterator iterator();

    /**
     * {@inheritDoc}
     * <p>
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs
     * side-effects that modify the underlying source of elements, unless an
     * overriding class has specified a concurrent modification policy.
     *
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    default void forEach(Consumer<? super Short> action) {
        Objects.requireNonNull(action);

        if (action instanceof ShortConsumer) {
            ShortConsumer actionShortConsumer = (ShortConsumer) action;
            ShortIterator shortIterator = this.iterator();
            while (shortIterator.hasNext()) {
                actionShortConsumer.acceptPrimitive(shortIterator.nextPrimitive());
            }
        } else {
            for (Short t : this) {
                action.accept(t);
            }
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates a {@link Spliterator} over the elements described by this
     * {@code Iterable}.
     *
     * @implSpec The default implementation creates an
     * <em><a href="../util/Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator
     * inherits the <em>fail-fast</em> properties of the iterable's iterator.
     * @implNote The default implementation should usually be overridden.  The
     * spliterator returned by the default implementation has poor splitting
     * capabilities, is unsized, and does not report any spliterator
     * characteristics. Implementing classes can nearly always provide a
     * better implementation.
     * @since 1.8
     */
    @Override
    default ShortSpliterator spliterator() {
        return ShortSpliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
