///*
// * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
// * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
// *
// * This code is free software; you can redistribute it and/or modify it
// * under the terms of the GNU General Public License version 2 only, as
// * published by the Free Software Foundation.  Oracle designates this
// * particular file as subject to the "Classpath" exception as provided
// * by Oracle in the LICENSE file that accompanied this code.
// *
// * This code is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
// * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
// * version 2 for more details (a copy is included in the LICENSE file that
// * accompanied this code).
// *
// * You should have received a copy of the GNU General Public License version
// * 2 along with this work; if not, write to the Free Software Foundation,
// * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
// *
// * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
// * or visit www.oracle.com if you need additional information or have any
// * questions.
// */
//package com.xenoamess.commons.primitive.collections.lists.array_lists;
//
//import com.xenoamess.commons.primitive.collections.lists.AbstractPrimitiveList;
//import com.xenoamess.commons.primitive.collections.lists.PrimitiveList;
//
//import java.util.AbstractList;
//import java.util.RandomAccess;
//
///**
// * Performance optimised {@code List} implements to replace {@code ArrayList}.
// * <p>
// * These classes aim to reduce performance issue of autoboxing and unboxing.
// * <p>
// * These classes are designed to be a replacement to {@code ArrayList<E>}
// * <p>
// * These classes shall be far faster in using.
// * <p>
// * These classes have functions dealing with Wrapper Classes for being a {@code List},
// * but nearly all of its functions have a replacement named XXXPrimitive.
// * <p>
// * The basic idea is use XXXPrimitive functions whenever possible, and only use
// * other functions when you have to.
// *
// * @author XenoAmess
// * @version 0.6.0
// */
//public abstract class PrimitiveArrayList<E> extends AbstractPrimitiveList<E>
//        implements PrimitiveList<E>, RandomAccess, Cloneable, java.io.Serializable {
//
//
//}
