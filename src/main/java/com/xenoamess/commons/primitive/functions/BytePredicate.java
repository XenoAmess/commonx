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
 * @since 1.8
 */
@FunctionalInterface
public interface BytePredicate extends Predicate<Byte>, Primitive {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    default boolean test(Byte t) {
        return this.testPrimitive(t);
    }

    /**
     * Primitive replacement of test(Byte t)
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     * @see #test(Byte t)
     */
    default boolean test(byte t) {
        return this.testPrimitive(t);
    }

    /**
     * Primitive replacement of test(Byte t)
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     * @see #test(Byte t)
     */
    boolean testPrimitive(byte t);


    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    @Override
    default Predicate<Byte> and(final Predicate<? super Byte> other) {
        Objects.requireNonNull(other);
        if (other instanceof BytePredicate) {
            return (BytePredicate) t -> BytePredicate.this.testPrimitive(t) && ((BytePredicate) other).testPrimitive(t);
        } else {
            return (t) -> test(t) && other.test(t);
        }
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    @Override
    default BytePredicate negate() {
        return (byte t) -> !testPrimitive(t);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    @Override
    default Predicate<Byte> or(Predicate<? super Byte> other) {
        Objects.requireNonNull(other);
        if (other instanceof BytePredicate) {
            return (BytePredicate) t -> BytePredicate.this.testPrimitive(t) || ((BytePredicate) other).testPrimitive(t);
        } else {
            return (t) -> test(t) || other.test(t);
        }
    }

    /**
     * Returns a predicate that tests if two arguments are equal according
     * to {@link Objects#equals(Object, Object)}.
     *
     * @param targetPrimitive the object reference with which to compare for equality,
     *                        which may be {@code null}
     * @return a predicate that tests if two arguments are equal according
     * to {@link Objects#equals(Object, Object)}
     */
    static BytePredicate isEqual(byte targetPrimitive) {
        return t -> t == targetPrimitive;
    }

    /**
     * Returns a predicate that is the negation of the supplied predicate.
     * This is accomplished by returning result of the calling
     * {@code target.negate()}.
     *
     * @param target predicate to negate
     * @return a predicate that negates the results of the supplied
     * predicate
     * @throws NullPointerException if target is null
     * @since 11
     */
    @SuppressWarnings("unchecked")
    static BytePredicate not(Predicate<? super Byte> target) {
        Objects.requireNonNull(target);
        return (BytePredicate) target.negate();
    }
}
