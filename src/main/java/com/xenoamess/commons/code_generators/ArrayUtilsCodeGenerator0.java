package com.xenoamess.commons.code_generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ArrayUtilsCodeGenerator0 {
    private static void writeNum(Writer writer, int numNum) throws IOException {
        switch (numNum) {
            case -1:
                writer.write("null");
                break;
            case 0:
                writer.write("empty");
                break;
            case 1:
                writer.write("one");
                break;
            case 2:
                writer.write("two");
                break;
        }
    }

    private static void writeClass(Writer writer, int classNum) throws IOException {
        switch (classNum) {
            case 0:
                writer.write("Object");
                break;
            case 1:
                writer.write("Boolean");
                break;
            case 2:
                writer.write("Long");
                break;
            case 3:
                writer.write("Int");
                break;
            case 4:
                writer.write("Short");
                break;
            case 5:
                writer.write("Char");
                break;
            case 6:
                writer.write("Byte");
                break;
            case 7:
                writer.write("Double");
                break;
            case 8:
                writer.write("Float");
                break;
        }
    }

    public static void main(String args[]) {
        String head = "final Object[] nullArrayObject = null;\n" +
                "final Object[] emptyArrayObject = new Object[0];\n" +
                "final Object[] oneArrayObject = new Object[]{\"pick\"};\n" +
                "final Object[] twoArrayObject = new Object[]{\"pick\", \"stick\"};\n" +
                "final boolean[] nullArrayBoolean = null;\n" +
                "final boolean[] emptyArrayBoolean = new boolean[0];\n" +
                "final boolean[] oneArrayBoolean = new boolean[]{true};\n" +
                "final boolean[] twoArrayBoolean = new boolean[]{true, false};\n" +
                "final long[] nullArrayLong = null;\n" +
                "final long[] emptyArrayLong = new long[0];\n" +
                "final long[] oneArrayLong = new long[]{0L};\n" +
                "final long[] twoArrayLong = new long[]{0L, 76L};\n" +
                "final int[] nullArrayInt = null;\n" +
                "final int[] emptyArrayInt = new int[0];\n" +
                "final int[] oneArrayInt = new int[]{4};\n" +
                "final int[] twoArrayInt = new int[]{5, 7};\n" +
                "final short[] nullArrayShort = null;\n" +
                "final short[] emptyArrayShort = new short[0];\n" +
                "final short[] oneArrayShort = new short[]{4};\n" +
                "final short[] twoArrayShort = new short[]{6, 8};\n" +
                "final char[] nullArrayChar = null;\n" +
                "final char[] emptyArrayChar = new char[0];\n" +
                "final char[] oneArrayChar = new char[]{'f'};\n" +
                "final char[] twoArrayChar = new char[]{'d', 't'};\n" +
                "final byte[] nullArrayByte = null;\n" +
                "final byte[] emptyArrayByte = new byte[0];\n" +
                "final byte[] oneArrayByte = new byte[]{3};\n" +
                "final byte[] twoArrayByte = new byte[]{4, 6};\n" +
                "final double[] nullArrayDouble = null;\n" +
                "final double[] emptyArrayDouble = new double[0];\n" +
                "final double[] oneArrayDouble = new double[]{1.3d};\n" +
                "final double[] twoArrayDouble = new double[]{4.5d, 6.3d};\n" +
                "final float[] nullArrayFloat = null;\n" +
                "final float[] emptyArrayFloat = new float[0];\n" +
                "final float[] oneArrayFloat = new float[]{2.5f};\n" +
                "final float[] twoArrayFloat = new float[]{6.4f, 5.8f};\n";
        File folder = new File("out");
        folder.mkdirs();
        File file = new File("out/ArrayUtilsCodeGenerator0_Output");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(head);
            for (int i = -1; i < 3; i++) {
                for (int j = -1; j < 3; j++) {
                    for (int k = 0; k < 9; k++) {
                        for (int l = 0; l < 9; l++) {
                            if (i == j || (i == -1 && j == 0) || (i == 0 && j == -1)) {
                                writer.write("assertTrue");
                            } else {
                                writer.write("assertFalse");
                            }
                            writer.write("(ArrayUtils.isSameLength(");
                            writeNum(writer, i);
                            writer.write("Array");
                            writeClass(writer, k);
                            writer.write(", ");
                            writeNum(writer, j);
                            writer.write("Array");
                            writeClass(writer, l);
                            writer.write("));\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        final Object[] nullArrayObject = null;
//        final Object[] emptyArrayObject = new Object[0];
//        final Object[] oneArrayObject = new Object[]{"pick"};
//        final Object[] twoArrayObject = new Object[]{"pick", "stick"};
//        final boolean[] nullArrayBoolean = null;
//        final boolean[] emptyArrayBoolean = new boolean[0];
//        final boolean[] oneArrayBoolean = new boolean[]{true};
//        final boolean[] twoArrayBoolean = new boolean[]{true, false};
//        final long[] nullArrayLong = null;
//        final long[] emptyArrayLong = new long[0];
//        final long[] oneArrayLong = new long[]{0L};
//        final long[] twoArrayLong = new long[]{0L, 76L};
//        final int[] nullArrayInt = null;
//        final int[] emptyArrayInt = new int[0];
//        final int[] oneArrayInt = new int[]{4};
//        final int[] twoArrayInt = new int[]{5, 7};
//        final short[] nullArrayShort = null;
//        final short[] emptyArrayShort = new short[0];
//        final short[] oneArrayShort = new short[]{4};
//        final short[] twoArrayShort = new short[]{6, 8};
//        final char[] nullArrayChar = null;
//        final char[] emptyArrayChar = new char[0];
//        final char[] oneArrayChar = new char[]{'f'};
//        final char[] twoArrayChar = new char[]{'d', 't'};
//        final byte[] nullArrayByte = null;
//        final byte[] emptyArrayByte = new byte[0];
//        final byte[] oneArrayByte = new byte[]{3};
//        final byte[] twoArrayByte = new byte[]{4, 6};
//        final double[] nullArrayDouble = null;
//        final double[] emptyArrayDouble = new double[0];
//        final double[] oneArrayDouble = new double[]{1.3d};
//        final double[] twoArrayDouble = new double[]{4.5d, 6.3d};
//        final float[] nullArrayFloat = null;
//        final float[] emptyArrayFloat = new float[0];
//        final float[] oneArrayFloat = new float[]{2.5f};
//        final float[] twoArrayFloat = new float[]{6.4f, 5.8f};
    }

}
