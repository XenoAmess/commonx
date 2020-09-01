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

import java.util.ListIterator;

/**
 * An iterator for lists that allows the programmer
 * to traverse the list in either direction, modify
 * the list during iteration, and obtain the iterator's
 * current position in the list. A {@code ListIterator}
 * has no current element; its <I>cursor position</I> always
 * lies between the element that would be returned by a call
 * to {@code previous()} and the element that would be
 * returned by a call to {@code next()}.
 * An iterator for a list of length {@code n} has {@code n+1} possible
 * cursor positions, as illustrated by the carets ({@code ^}) below:
 * <PRE>
 * Element(0)   Element(1)   Element(2)   ... Element(n-1)
 * cursor positions:  ^            ^            ^            ^                  ^
 * </PRE>
 * Note that the {@link #remove} and {@link #set(Short)} methods are
 * <i>not</i> defined in terms of the cursor position;  they are defined to
 * operate on the last element returned by a call to {@link #next} or
 * {@link #previous()}.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @author XenoAmess
 * @version 0.6.0
 * @see java.util.Collection
 * @see java.util.List
 * @see java.util.Iterator
 * @see java.util.Enumeration
 * @see java.util.List#listIterator()
 * @see ShortIterator
 * @since 1.2
 */
public interface ShortListIterator extends ShortIterator, ListIterator<Short>, Primitive {
    // Query Operations

    /**
     * {@inheritDoc}
     * <p>
     * Returns the next element in the list and advances the cursor position.
     * This method may be called repeatedly to iterate through the list,
     * or intermixed with calls to {@link #previous} to go back and forth.
     * (Note that alternating calls to {@code next} and {@code previous}
     * will return the same element repeatedly.)
     */
    @Override
    default Short next() {
        return ShortIterator.super.next();
    }

    /**
     * Primitive replacement of {@code ShortListIterator.previous()}
     *
     * @return {@code true} if the list iterator has more elements when
     * traversing the list in the reverse direction
     * @see ShortListIterator#previous()
     */
    short previousPrimitive();

    // Modification Operations

    /**
     * {@inheritDoc}
     */
    @Override
    default Short previous() {
        return this.previousPrimitive();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    default void set(Short e) {
        this.setPrimitive(e);
    }

    /**
     * Primitive replacement of {@code ShortListIterator.set(Short e)}
     *
     * @param e the element with which to replace the last element returned by
     *          {@code next} or {@code previous}
     * @throws java.lang.UnsupportedOperationException if the {@code set} operation
     *                                                 is not supported by this list iterator
     * @throws java.lang.ClassCastException            if the class of the specified element
     *                                                 prevents it from being added to this list
     * @throws java.lang.IllegalArgumentException      if some aspect of the specified
     *                                                 element prevents it from being added to this list
     * @throws java.lang.IllegalStateException         if neither {@code next} nor
     *                                                 {@code previous} have been called, or {@code remove} or
     *                                                 {@code add} have been called after the last call to
     *                                                 {@code next} or {@code previous}
     * @see ShortListIterator#set(Short e)
     */
    void setPrimitive(short e);

    /**
     * {@inheritDoc}
     */
    @Override
    default void add(Short e) {
        addPrimitive(e);
    }

    /**
     * Primitive replacement of {@code ShortListIterator.add(Short e)}
     *
     * @param e the element to insert
     * @throws java.lang.UnsupportedOperationException if the {@code add} method is
     *                                                 not supported by this list iterator
     * @throws java.lang.ClassCastException            if the class of the specified element
     *                                                 prevents it from being added to this list
     * @throws java.lang.IllegalArgumentException      if some aspect of this element
     *                                                 prevents it from being added to this list
     * @see ShortListIterator#add(Short e)
     */
    void addPrimitive(short e);
}
