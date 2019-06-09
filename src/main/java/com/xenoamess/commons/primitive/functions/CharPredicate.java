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
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one argument.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Object)}.
 *
 * @author XenoAmess
 * @version 0.8.0
 * @see Predicate
 * @since 1.8
 */
@FunctionalInterface
public interface CharPredicate extends Predicate<Character>, Primitive {

    /**
     * {@inheritDoc}
     * <p>
     * Evaluates this predicate on the given argument.
     */
    @Override
    default boolean test(Character t) {
        return this.testPrimitive(t);
    }

    /**
     * Primitive replacement of test(Character t)
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     * @see #test(Character t)
     */
    default boolean test(char t) {
        return this.testPrimitive(t);
    }

    /**
     * Primitive replacement of test(Character t)
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     * @see #test(Character t)
     */
    boolean testPrimitive(char t);


    /**
     * {@inheritDoc}
     * <p>
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     */
    @Override
    default Predicate<Character> and(final Predicate<? super Character> other) {
        Objects.requireNonNull(other);
        if (other instanceof CharPredicate) {
            return (CharPredicate) t -> CharPredicate.this.testPrimitive(t) && ((CharPredicate) other).testPrimitive(t);
        } else {
            return (t) -> test(t) && other.test(t);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a predicate that represents the logical negation of this
     * predicate.
     */
    @Override
    default CharPredicate negate() {
        return (char t) -> !testPrimitive(t);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     */
    @Override
    default Predicate<Character> or(Predicate<? super Character> other) {
        Objects.requireNonNull(other);
        if (other instanceof CharPredicate) {
            return (CharPredicate) t -> CharPredicate.this.testPrimitive(t) || ((CharPredicate) other).testPrimitive(t);
        } else {
            return (t) -> test(t) || other.test(t);
        }
    }

    /**
     * Returns a predicate that tests if two arguments are equal according
     * to {@link java.util.Objects#equals(Object, Object)}.
     *
     * @param targetPrimitive the object reference with which to compare for equality,
     *                        which may be {@code null}
     * @return a predicate that tests if two arguments are equal according
     * to {@link java.util.Objects#equals(Object, Object)}
     */
    static CharPredicate isEqual(char targetPrimitive) {
        return t -> t == targetPrimitive;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a predicate that is the negation of the supplied predicate.
     * This is accomplished by returning result of the calling
     * {@code target.negate()}.
     *
     * @since 11
     */
    @SuppressWarnings("unchecked")
    static CharPredicate not(Predicate<? super Character> target) {
        Objects.requireNonNull(target);
        return (CharPredicate) target.negate();
    }
}
