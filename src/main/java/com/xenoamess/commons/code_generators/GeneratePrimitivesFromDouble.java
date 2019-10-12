package com.xenoamess.commons.code_generators;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * GeneratePrimitivesFromDouble class.</p>
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
     * generatePrimitivesFromFile.</p>
     *
     * @param sourcePath a {@link java.lang.String} object.
     */
    public static void generatePrimitivesFromFile(String sourcePath) {
        for (int i = 0, len = PRIMITIVE_TYPES_EXCLUDE_DOUBLE.length; i < len; i++) {
            generateSinglePrimitivesFromFile(sourcePath, i);
        }
    }

    /**
     * generateSinglePrimitivesFromFile.</p>
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
            String line;
            while ((line = bufIn.readLine()) != null) {
                line = line.replaceAll(sourceString, replaceString);
                line = line.replaceAll(sourceStringL, replaceStringL);
                line = line.replaceAll(sourceStringL.toUpperCase(), replaceStringL.toUpperCase());
                switch (PRIMITIVE_TYPES_EXCLUDE_DOUBLEL[replaceStringIndex]) {
                    case "char":
//                        line = line.replaceAll("CharacterArrayList", "CharArrayList");
//                        line = line.replaceAll("CharacterIterator", "CharIterator");
//                        line = line.replaceAll("CharacterListIterator", "CharListIterator");
                        line = line.replaceAll(".readCharacter\\(", ".readChar(");
                        line = line.replaceAll(".writeCharacter\\(", ".writeChar(");
//                        line = line.replaceAll("CharacterComparator", "CharComparator");
//                        line = line.replaceAll("CharacterTimSort", "CharTimSort");
//                        line = line.replaceAll("CharacterSpliterators", "CharSpliterators");
//                        line = line.replaceAll("CharacterConsumer", "CharConsumer");
//                        line = line.replaceAll("CharacterCollection", "CharCollection");
//                        line = line.replaceAll("CharacterIterable", "CharIterable");
//                        line = line.replaceAll("CharacterList", "CharList");
                        line = line.replaceAll("(Character)([a-zA-Z])", "Char$2");
                        line = line.replaceAll("Charistics", "Characteristics");
                        //test
                        line = line.replaceAll("random.nextCharacter\\(\\)", "((char)random.nextInt())");
                        break;
                    case "int":
//                        line = line.replaceAll("IntegerArrayList", "IntArrayList");
//                        line = line.replaceAll("IntegerIterator", "IntIterator");
//                        line = line.replaceAll("IntegerListIterator", "IntListIterator");
                        line = line.replaceAll(".readInteger\\(", ".readInt(");
                        line = line.replaceAll(".writeInteger\\(", ".writeInt(");
//                        line = line.replaceAll("IntegerComparator", "IntComparator");
//                        line = line.replaceAll("IntegerTimSort", "IntTimSort");
//                        line = line.replaceAll("IntegerSpliterators", "IntSpliterators");
//                        line = line.replaceAll("IntegerConsumer", "IntConsumer");
//                        line = line.replaceAll("IntegerCollection", "IntCollection");
//                        line = line.replaceAll("IntegerIterable", "IntIterable");
//                        line = line.replaceAll("IntegerList", "IntList");
                        line = line.replaceAll("(Integer)([a-zA-Z])", "Int$2");
                        line = line.replaceAll("random.nextInteger\\(\\)", "(random.nextInt())");
                        break;
                    case "byte":
                        line = line.replaceAll("random.nextByte\\(\\)", "((byte)random.nextInt())");
                        break;
                    case "short":
                        line = line.replaceAll("random.nextShort\\(\\)", "((short)random.nextInt())");
                        break;
                    case "boolean":
                        line = line.replaceAll("\\(boolean\\) 0[0-9]+", "false");
                        line = line.replaceAll("\\(boolean\\) [0-9]+", "true");
                        line = line.replaceAll("new boolean\\[]\\{1, 2}", "new boolean\\[]\\{false, true}");
                        line = line.replaceAll("\\(boolean\\) i", "false");
                        line = line.replaceAll("\\(boolean\\) w+ ", "false ");
                        break;
                    default:
                }

                tempStream.write(line);
                tempStream.append(System.getProperty("line.separator"));
            }
            tempStream.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        processFile("src/main/java/com/xenoamess/commons/primitive/");
        processFile("src/test/java/com/xenoamess/commons/primitive/");
        processFile("src/test/java/jdk/java/util/");

        ArrayList<String> banFiles = new ArrayList<>();
        banFiles.add("src/test/java/jdk/java/util/AbstractList" +
                "/BooleanCheckForComodificationTest.java");
        banFiles.add("src/test/java/jdk/java/util/ArrayList" +
                "/BooleanRangeCheckMicroBenchmarkTest.java");
        banFiles.add("src/test/java/jdk/java/util/AbstractCollection" +
                "/BooleanToArrayTest.java");
        banFiles.add("src/test/java/jdk/java/util/AbstractList" +
                "/BooleanFailFastIteratorTest.java");

        for (String fileName : banFiles) {
            File nowFile = new File(fileName);
            if (nowFile.exists()) {
                try {
                    Files.delete(nowFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void processFile(String path) {
        // get file list where the path has
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        // get the folder list
        File[] array = file.listFiles();

        for (File value : array) {
            if (value.isFile()) {
                if (value.toString().contains("Double")) {
                    System.out.println(value.getAbsolutePath());
                    generatePrimitivesFromFile(value.getAbsolutePath());
                }
            } else if (value.isDirectory()) {
                processFile(value.getPath());
            }
        }
    }
}
