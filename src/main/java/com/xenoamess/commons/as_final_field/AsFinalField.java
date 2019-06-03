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

package com.xenoamess.commons.as_final_field;

import java.lang.annotation.*;

/**
 * If the annotation {@code @AsFinalField} is present on the declaration
 * of a field <i>A</i>, then field {@code @A} is thought to be AsFinalField.
 * <p>
 * If a field is AsFinalField, then it must follow the following rules:
 * <p>
 * 1. It must have an empty value.
 * <p>
 * Empty value is a certain value that indicate it be empty.
 * By default, empty value shall be the original value of this field type in a static field.
 * For example, 0 for int, null for Object, etc.
 * <p>
 * 2. The field shall only be set when its value equals empty value.
 * <p>
 * Technically this is now implemented by using AsFinalFieldUtils#asFinalFieldSet to deal with any set of any
 * AsFinalField.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AsFinalField {

}
