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

import java.lang.reflect.Field;

/**
 * <p>AsFinalFieldReSetException class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 */
public class AsFinalFieldReSetException extends RuntimeException {
    /**
     * <p>Constructor for AsFinalFieldReSetException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public AsFinalFieldReSetException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for AsFinalFieldReSetException.</p>
     *
     * @param object a {@link java.lang.Object} object.
     * @param field a {@link java.lang.reflect.Field} object.
     * @param asFinalFieldExceptionType a {@link com.xenoamess.commons.as_final_field.AsFinalFieldExceptionType} object.
     */
    public AsFinalFieldReSetException(
            Object object,
            Field field,
            AsFinalFieldExceptionType asFinalFieldExceptionType
    ) {
        super(produceMessage(object, field, field.getName(), asFinalFieldExceptionType));
    }

    /**
     * <p>Constructor for AsFinalFieldReSetException.</p>
     *
     * @param object a {@link java.lang.Object} object.
     * @param fieldName a {@link java.lang.String} object.
     * @param asFinalFieldExceptionType a {@link com.xenoamess.commons.as_final_field.AsFinalFieldExceptionType} object.
     */
    public AsFinalFieldReSetException(
            Object object,
            String fieldName,
            AsFinalFieldExceptionType asFinalFieldExceptionType
    ) {
        super(produceMessage(object, null, fieldName, asFinalFieldExceptionType));
    }

    /**
     * <p>produceMessage.</p>
     *
     * @param object a {@link java.lang.Object} object.
     * @param field a {@link java.lang.reflect.Field} object.
     * @param fieldName a {@link java.lang.String} object.
     * @param asFinalFieldExceptionType a {@link com.xenoamess.commons.as_final_field.AsFinalFieldExceptionType} object.
     * @return a {@link java.lang.String} object.
     */
    public static String produceMessage(
            Object object,
            Field field,
            String fieldName,
            AsFinalFieldExceptionType asFinalFieldExceptionType
    ) {
        return asFinalFieldExceptionType.produceMessage(object, field, fieldName);
    }
}
