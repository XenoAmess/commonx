package com.xenoamess.commons.code_generators;

import java.io.*;

/**
 * @author XenoAmess
 */
public class GeneratePrimitivesFromDouble {
    public static final String[] primitiveTypes = {"Long", "Int", "Short", "Char", "Byte", "Double", "Float"
            , "Boolean"};
    public static final String[] primitiveTypesExcludeDouble = {"Long", "Integer", "Short", "Character", "Byte",
            "Float",
            "Boolean"};
    public static final String[] primitiveTypesExcludeDoubleL = {"long", "int", "short", "char", "byte", "float",
            "boolean"};
    public static final String[] fileNameReplaceStrings = {"Long", "Int", "Short", "Char", "Byte",
            "Float",
            "Boolean"};

    public static void generatePrimitivesFromFile(String sourcePath) {
        for (int i = 0, len = primitiveTypesExcludeDouble.length; i < len; i++) {
            generateSinglePrimitivesFromFile(sourcePath, i);
        }
    }

    public static void generateSinglePrimitivesFromFile(String sourcePath, int replaceStringIndex) {
        String sourceString = "Double";
        String replaceString = primitiveTypesExcludeDouble[replaceStringIndex];
        String fileNameReplaceString = fileNameReplaceStrings[replaceStringIndex];
        File sourceFile = new File(sourcePath);
        String targetPath = sourcePath.replaceAll(sourceString, fileNameReplaceString);
        File targetFile = new File(targetPath);
        String sourceStringL = "double";
        String replaceStringL = primitiveTypesExcludeDoubleL[replaceStringIndex];

        try (
                FileReader in = new FileReader(sourceFile);
                BufferedReader bufIn = new BufferedReader(in);
                CharArrayWriter tempStream = new CharArrayWriter();
                FileWriter out = new FileWriter(targetFile);
        ) {
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                line = line.replaceAll(sourceString, replaceString);
                line = line.replaceAll(sourceStringL, replaceStringL);
                tempStream.write(line);
                tempStream.append(System.getProperty("line.separator"));
            }
            tempStream.writeTo(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
