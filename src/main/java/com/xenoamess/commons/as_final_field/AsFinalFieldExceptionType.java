package com.xenoamess.commons.as_final_field;

import java.lang.reflect.Field;

/**
 * @author XenoAmess
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
