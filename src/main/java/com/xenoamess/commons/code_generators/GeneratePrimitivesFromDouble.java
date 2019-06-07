package com.xenoamess.commons.code_generators;

import java.io.*;

/**
 * <p>GeneratePrimitivesFromDouble class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 */
public class GeneratePrimitivesFromDouble {
    /**
     * Constant <code>PRIMITIVE_TYPES</code>
     */
    public static final String[] PRIMITIVE_TYPES = {"Long", "Int", "Short", "Char", "Byte", "Double", "Float"
            , "Boolean"};
    /**
     * Constant <code>PRIMITIVE_TYPES_EXCLUDE_DOUBLE</code>
     */
    public static final String[] PRIMITIVE_TYPES_EXCLUDE_DOUBLE = {"Long", "Integer", "Short", "Character", "Byte",
            "Float",
            "Boolean"};
    /**
     * Constant <code>PRIMITIVE_TYPES_EXCLUDE_DOUBLEL</code>
     */
    public static final String[] PRIMITIVE_TYPES_EXCLUDE_DOUBLEL = {"long", "int", "short", "char", "byte", "float",
            "boolean"};
    /**
     * Constant <code>fileNameReplaceStrings</code>
     */
    public static final String[] fileNameReplaceStrings = {"Long", "Int", "Short", "Char", "Byte",
            "Float",
            "Boolean"};

    /**
     * <p>generatePrimitivesFromFile.</p>
     *
     * @param sourcePath a {@link java.lang.String} object.
     */
    public static void generatePrimitivesFromFile(String sourcePath) {
        for (int i = 0, len = PRIMITIVE_TYPES_EXCLUDE_DOUBLE.length; i < len; i++) {
            generateSinglePrimitivesFromFile(sourcePath, i);
        }
    }

    /**
     * <p>generateSinglePrimitivesFromFile.</p>
     *
     * @param sourcePath         a {@link java.lang.String} object.
     * @param replaceStringIndex a int.
     */
    public static void generateSinglePrimitivesFromFile(String sourcePath, int replaceStringIndex) {
        String sourceString = "Double";
        String replaceString = PRIMITIVE_TYPES_EXCLUDE_DOUBLE[replaceStringIndex];
        String fileNameReplaceString = fileNameReplaceStrings[replaceStringIndex];
        File sourceFile = new File(sourcePath);
        String targetPath = sourcePath.replaceAll(sourceString, fileNameReplaceString);
        File targetFile = new File(targetPath);
        String sourceStringL = "double";
        String replaceStringL = PRIMITIVE_TYPES_EXCLUDE_DOUBLEL[replaceStringIndex];

        try (
                FileReader in = new FileReader(sourceFile);
                BufferedReader bufIn = new BufferedReader(in);
                CharArrayWriter tempStream = new CharArrayWriter();
                FileWriter out = new FileWriter(targetFile)
        ) {
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                line = line.replaceAll(sourceString, replaceString);
                line = line.replaceAll(sourceStringL, replaceStringL);
                tempStream.write(line);
                tempStream.append(System.getProperty("line.separator"));
            }
            tempStream.writeTo(out);
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
        generatePrimitivesFromFile("D:\\workspace\\commonx\\src\\main\\java\\com\\xenoamess\\commons\\collections" +
                "\\list\\primitive_array_lists\\DoubleArrayList.java");

//        generatePrimitivesFromFile("D:\\workspace\\commonx\\src\\main\\java\\com\\xenoamess\\commons\\collections" +
//                "\\list\\primitive_array_lists\\DoubleIterator.java");

//        generatePrimitivesFromFile("D:\\workspace\\commonx\\src\\main\\java\\com\\xenoamess\\commons\\collections" +
//                "\\list\\primitive_array_lists\\DoubleListIterator.java");

        generatePrimitivesFromFile("D:\\workspace\\commonx\\src\\main\\java\\com\\xenoamess\\commons\\collections" +
                "\\list\\primitive_array_lists\\DoubleTimSort.java");

//        generatePrimitivesFromFile("D:\\workspace\\commonx\\src\\main\\java\\com\\xenoamess\\commons\\collections" +
//                "\\list\\primitive_array_lists" +
//                "\\DoubleComparator.java");

    }
}
