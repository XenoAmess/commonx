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

package com.xenoamess.commonx.java.lang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

/**
 * <p>IllegalArgumentExceptionUtilsxCodeGenerator class.</p>
 *
 * @author XenoAmess
 * @version $Id: $Id
 */
public class IllegalArgumentExceptionUtilsxCodeGenerator {

    /**
     * <p>generateFile.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param functionalInterface a {@link java.util.function.Function} object.
     */
    public static void generateFile(String name, Function<String, String> functionalInterface) {
        String content = functionalInterface.apply(name);
        File file = new File("src/main/resources/templates/" + name + ".template");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file));) {
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        generateFile(
                "generateIsAnyNullInParams",
                IllegalArgumentExceptionUtilsxCodeGenerator::generateIsAnyNullInParams
        );
        generateFile(
                "generateIsAnyNullInParamsThenThrowIllegalArgumentException",
                IllegalArgumentExceptionUtilsxCodeGenerator::generateIsAnyNullInParamsThenThrowIllegalArgumentException
        );
        generateFile(
                "generateIsNoneNullInParams",
                IllegalArgumentExceptionUtilsxCodeGenerator::generateIsNoneNullInParams
        );
        generateFile(
                "generateIsAllNullInParams",
                IllegalArgumentExceptionUtilsxCodeGenerator::generateIsAllNullInParams
        );
        generateFile(
                "generateIsAllNullInParamsThenThrowIllegalArgumentException",
                IllegalArgumentExceptionUtilsxCodeGenerator::generateIsAllNullInParamsThenThrowIllegalArgumentException
        );
    }

    /**
     * <p>generateIsAnyNullInParams.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsAnyNullInParams(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsAnyNullInParams(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsAnyNullInParams.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum a int.
     */
    public static void generateIsAnyNullInParams(StringBuilder stringBuilder, int paramNum) {
        /**
         *     public static boolean isAnyNullInParams(final Object param0, final Object param1) {
         *         return param0 == null || param1 == null;
         *     }
         */
        stringBuilder.append("    public static boolean isAnyNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\\\n        return ");
        if (paramNum == 0) {
            stringBuilder.append("false");
        }
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(" || ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
            stringBuilder.append(" == null");
        }
        stringBuilder.append(";\\\n    }\\\n");
    }

    /**
     * <p>generateIsAnyNullInParamsThenThrowIllegalArgumentException.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsAnyNullInParamsThenThrowIllegalArgumentException(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsAnyNullInParamsThenThrowIllegalArgumentException(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsAnyNullInParamsThenThrowIllegalArgumentException.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum a int.
     */
    public static void generateIsAnyNullInParamsThenThrowIllegalArgumentException(
            StringBuilder stringBuilder, int paramNum) {
        /**
         *     public static void isAnyNullInParamsThenThrowIllegalArgumentException(final Object param0, final
         *     Object param1) {
         *         if (isAnyNullInParams(param0, param1)) {
         *             throw new IllegalArgumentException("caused by any of the following be null: " + param0 + ", "
         *             + param1);
         *         }
         *     }
         */
        stringBuilder.append("    public static void isAnyNullInParamsThenThrowIllegalArgumentException(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\\\n");
        stringBuilder.append("    if (isAnyNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
        }
        stringBuilder.append(")){\\\n        throw new IllegalArgumentException(\"caused by any of the following be " +
                "null: \"");

        if (paramNum != 0) {
            stringBuilder.append(" + ");
        }

        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(" + \", \" + ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
        }
        stringBuilder.append(");\\\n    }\\\n}\\\n");
    }

    /**
     * <p>generateIsNoneNullInParams.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsNoneNullInParams(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsNoneNullInParams(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsNoneNullInParams.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum a int.
     */
    public static void generateIsNoneNullInParams(StringBuilder stringBuilder, int paramNum) {
        /**
         *     public static boolean isNoneNullInParams(Object param0, Object param1) {
         *         return !isAnyNullInParams(param0, param1);
         *     }
         */
        stringBuilder.append("    public static boolean isNoneNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\\\n        return !isAnyNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
        }
        stringBuilder.append(");\\\n    }\\\n");
    }


    /**
     * <p>generateIsAllNullInParams.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsAllNullInParams(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsAllNullInParams(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsAllNullInParams.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum a int.
     */
    public static void generateIsAllNullInParams(StringBuilder stringBuilder, int paramNum) {
        /**
         *     public static boolean isAllNull(final Object param0, final Object param1) {
         *         return param0 == null && param1 == null;
         *     }
         */
        stringBuilder.append("    public static boolean isAllNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\\\n        return ");
        if (paramNum == 0) {
            stringBuilder.append("false");
        }
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(" && ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
            stringBuilder.append(" == null");
        }
        stringBuilder.append(";\\\n    }\\\n");
    }

    /**
     * <p>generateIsAllNullInParamsThenThrowIllegalArgumentException.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsAllNullInParamsThenThrowIllegalArgumentException(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsAllNullInParamsThenThrowIllegalArgumentException(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsAllNullInParamsThenThrowIllegalArgumentException.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum a int.
     */
    public static void generateIsAllNullInParamsThenThrowIllegalArgumentException(
            StringBuilder stringBuilder, int paramNum) {
        /**
         *     public static void isAllNullInParamsThenThrowIllegalArgumentException(final Object param0, final
         *     Object param1) {
         *         if (isAllNullInParams(param0, param1)) {
         *             throw new IllegalArgumentException("caused by all of the following be null: " + param0 + ", "
         *             + param1);
         *         }
         *     }
         */
        stringBuilder.append("    public static void isAllNullInParamsThenThrowIllegalArgumentException(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\\\n");
        stringBuilder.append("    if (isAllNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
        }
        stringBuilder.append(")){\\\n        throw new IllegalArgumentException(\"caused by all of the following be " +
                "null: \"");

        if (paramNum != 0) {
            stringBuilder.append(" + ");
        }

        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(" + \", \" + ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
        }
        stringBuilder.append(");\\\n    }\\\n}\\\n");
    }
}

