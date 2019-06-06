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

package com.xenoamess.commons.collections.list.primitive_array_lists;

import java.util.Iterator;

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
 * @version 0.6.0
 * @apiNote An {@link java.util.Enumeration} can be converted into an {@code Iterator} by
 * using the {@link java.util.Enumeration#asIterator} method.
 * @see java.util.Collection
 * @see CharListIterator
 * @see Iterable
 * @since 1.2
 */
public interface CharIterator extends Iterator<Character> {

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws java.util.NoSuchElementException if the iteration has no more elements
     */
    char nextPrimitive();
}
