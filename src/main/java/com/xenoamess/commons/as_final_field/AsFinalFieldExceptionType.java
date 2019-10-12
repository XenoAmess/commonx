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
 * AsFinalFieldExceptionType class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 */
public enum AsFinalFieldExceptionType {
    FIELD_NOT_EXIST {
        @Override
        public String produceMessage(Object object, Field field, String fieldName) {
            if (object instanceof Class) {
                Class classObject = (Class) object;
                return "no such field in class: " + "class: " + classObject.getCanonicalName() + ",field: " + fieldName;
            } else {
                return "no such field in object: " + "class: " + object.getClass().getCanonicalName() + "object: " + object.toString() + ",field: " + fieldName;
            }
        }
    },
    ILLEGAL_ACCESS {
        @Override
        public String produceMessage(Object object, Field field, String fieldName) {
            if (object instanceof Class) {
                Class classObject = (Class) object;
                return "illegal to modify field in class: " + "class: " + classObject.getCanonicalName() + ",field: " + fieldName;
            } else {
                return "illegal to modify field in object: " + "class: " + object.getClass().getCanonicalName() +
                        "object: " + object.toString() + ",field: " + fieldName;
            }
        }
    },
    ALREADY_HAVE_VALUE {
        @Override
        public String produceMessage(Object object, Field field, String fieldName) {
            if (field == null) {
                throw new IllegalArgumentException("The field must not be null");
            }
            if (object instanceof Class) {
                Class classObject = (Class) object;
                return "field in class already have a non-empty value: " + "class: " + classObject.getCanonicalName() + ",field" +
                        ": " + fieldName;
            } else {
                return "field in object already have a non-empty value: " + "class: " + object.getClass().getCanonicalName() +
                        "object: " + object.toString() + ",field: " + fieldName;
            }
        }
    },
    ;

    abstract String produceMessage(Object object, Field field, String fieldName);
}
