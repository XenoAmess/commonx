package com.xenoamess.commonx.java.lang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

/**
 * @author XenoAmess
 */
public class IllegalArgumentExceptionUtilsxCodeGenerator {

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

    public static String generateIsAnyNullInParams(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsAnyNullInParams(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

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

    public static String generateIsAnyNullInParamsThenThrowIllegalArgumentException(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsAnyNullInParamsThenThrowIllegalArgumentException(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

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

    public static String generateIsNoneNullInParams(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsNoneNullInParams(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

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


    public static String generateIsAllNullInParams(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsAllNullInParams(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

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

    public static String generateIsAllNullInParamsThenThrowIllegalArgumentException(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " = \\n\\");
        for (int i = 0; i < 256; i++) {
            generateIsAllNullInParamsThenThrowIllegalArgumentException(stringBuilder, i);
            stringBuilder.append("\\\n");
        }
        return stringBuilder.toString();
    }

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

