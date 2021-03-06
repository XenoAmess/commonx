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

package com.xenoamess.commons.code_generators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.UnaryOperator;

/**
 * <p>IllegalArgumentExceptionUtilsxCodeGenerator class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 */
public class IllegalArgumentExceptionUtilsxCodeGenerator {

    /**
     * <p>generateFile.</p>
     *
     * @param name                a {@link java.lang.String} object.
     * @param functionalInterface a {@link java.util.function.Function} object.
     */
    public static void generateFile(String name, UnaryOperator<String> functionalInterface) {
        String content = functionalInterface.apply(name);
        File file = new File("src/main/resources/templates/" + name + ".template");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
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
        String javadocHeader = "    /**\n" +
                "     * <p>Checks if any of the parameters are null.</p>\n" +
                "     *\n" +
                "     * <pre>\n" +
                "     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams());\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(null));\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(1, null));\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(2, 2, 2, null));\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(null, 4, 3, 3, 5));\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(4, null, 3, 5));\n" +
                "     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams(4, true, 3, 5));\n" +
                "     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams(new Object[]{null, " +
                "null, null}));\n" +
                "     * </pre>\n" +
                "     * <p>\n" +
                "     * @return {@code true} if any of the parameters are null\n" +
                "     * @author XenoAmess\n" +
                "     */\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < 256; i++) {
            if (i != 0) {
                stringBuilder.append(javadocHeader);
            }
            generateIsAnyNullInParams(stringBuilder, i);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsAnyNullInParams.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum      a int.
     */
    public static void generateIsAnyNullInParams(StringBuilder stringBuilder, int paramNum) {
        /*
              public static boolean isAnyNullInParams(final Object param0, final Object param1) {
                  return param0 == null || param1 == null;
              }
         */
        stringBuilder.append("    public static boolean isAnyNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\n        return ");
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
        stringBuilder.append(";\n    }\n");
    }

    /**
     * <p>generateIsAnyNullInParamsThenThrowIllegalArgumentException.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsAnyNullInParamsThenThrowIllegalArgumentException(String name) {
        String javadocHeader = "    /**\n" +
                "     * <p>If any of the parameters are null, throw an IllegalArgumentException about it.</p>\n" +
                "     *\n" +
                "     * @author XenoAmess\n" +
                "     */\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < 256; i++) {
            if (i != 0) {
                stringBuilder.append(javadocHeader);
            }
            generateIsAnyNullInParamsThenThrowIllegalArgumentException(stringBuilder, i);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsAnyNullInParamsThenThrowIllegalArgumentException.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum      a int.
     */
    public static void generateIsAnyNullInParamsThenThrowIllegalArgumentException(
            StringBuilder stringBuilder, int paramNum) {
        /*
              public static void isAnyNullInParamsThenThrowIllegalArgumentException(final Object param0, final
              Object param1) {
                  if (isAnyNullInParams(param0, param1)) {
                      throw new IllegalArgumentException("caused by any of the following be null: " + param0 + ", "
                      + param1);
                  }
              }
         */
        stringBuilder.append("    public static void isAnyNullInParamsThenThrowIllegalArgumentException(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\n");
        stringBuilder.append("        if (isAnyNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
        }
        stringBuilder.append(")){\n            throw new IllegalArgumentException(\"caused by any of the following " +
                "be " +
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
        stringBuilder.append(");\n        }\n    }\n");
    }

    /**
     * <p>generateIsNoneNullInParams.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsNoneNullInParams(String name) {
        String javadocHeader = "    /**\n" +
                "     * <p>Checks if none of the parameters are null.</p>\n" +
                "     *\n" +
                "     * <pre>\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams());\n" +
                "     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(null));\n" +
                "     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, null));\n" +
                "     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(2, 2, 2, null));\n" +
                "     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(null, 4, 3, 3, 5));" +
                "\n" +
                "     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(4, null, 3, 5));\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(4, true, 3, 5));\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(new Object[]{null, " +
                "null, null}));\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, new " +
                "Object[]{null}));\n" +
                "     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, (Object) new " +
                "Object[]{null}));\n" +
                "     * </pre>\n" +
                "     * <p>\n" +
                "     * @return {@code true} if none of the parameters are null\n" +
                "     * @author XenoAmess\n" +
                "     */\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < 256; i++) {
            if (i != 0) {
                stringBuilder.append(javadocHeader);
            }
            generateIsNoneNullInParams(stringBuilder, i);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsNoneNullInParams.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum      a int.
     */
    public static void generateIsNoneNullInParams(StringBuilder stringBuilder, int paramNum) {
        /*
              public static boolean isNoneNullInParams(Object param0, Object param1) {
                  return !isAnyNullInParams(param0, param1);
              }
         */
        stringBuilder.append("    public static boolean isNoneNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\n        return !isAnyNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
        }
        stringBuilder.append(");\n    }\n");
    }


    /**
     * <p>generateIsAllNullInParams.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsAllNullInParams(String name) {
        String javadocHeader = "    /**\n" +
                "     * <p>Checks if all of the parameters are null.</p>\n" +
                "     *\n" +
                "     * @return {@code true} if all of the parameters are null\n" +
                "     * @author XenoAmess\n" +
                "     */\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < 256; i++) {
            if (i != 0) {
                stringBuilder.append(javadocHeader);
            }
            generateIsAllNullInParams(stringBuilder, i);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsAllNullInParams.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum      a int.
     */
    public static void generateIsAllNullInParams(StringBuilder stringBuilder, int paramNum) {
        /*
              public static boolean isAllNull(final Object param0, final Object param1) {
                  return param0 == null && param1 == null;
              }
         */
        stringBuilder.append("    public static boolean isAllNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\n        return ");
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
        stringBuilder.append(";\n    }\n");
    }

    /**
     * <p>generateIsAllNullInParamsThenThrowIllegalArgumentException.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String generateIsAllNullInParamsThenThrowIllegalArgumentException(String name) {
        String javadocHeader = "    /**\n" +
                "     * <p>If all of the parameters are null, throw an IllegalArgumentException about it.</p>\n" +
                "     *\n" +
                "     * @author XenoAmess\n" +
                "     */\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < 256; i++) {
            if (i != 0) {
                stringBuilder.append(javadocHeader);
            }
            generateIsAllNullInParamsThenThrowIllegalArgumentException(stringBuilder, i);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * <p>generateIsAllNullInParamsThenThrowIllegalArgumentException.</p>
     *
     * @param stringBuilder a {@link java.lang.StringBuilder} object.
     * @param paramNum      a int.
     */
    public static void generateIsAllNullInParamsThenThrowIllegalArgumentException(
            StringBuilder stringBuilder, int paramNum) {
        /*
              public static void isAllNullInParamsThenThrowIllegalArgumentException(final Object param0, final
              Object param1) {
                  if (isAllNullInParams(param0, param1)) {
                      throw new IllegalArgumentException("caused by all of the following be null: " + param0 + ", "
                      + param1);
                  }
              }
         */
        stringBuilder.append("    public static void isAllNullInParamsThenThrowIllegalArgumentException(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("final Object param");
            stringBuilder.append(i);
        }
        stringBuilder.append("){\n");
        stringBuilder.append("        if (isAllNullInParams(");
        for (int i = 0; i < paramNum; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("param");
            stringBuilder.append(i);
        }
        stringBuilder.append(")){\n            throw new IllegalArgumentException(\"caused by all of the following " +
                "be " +
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
        stringBuilder.append(");\n        }\n    }\n");
    }
}

