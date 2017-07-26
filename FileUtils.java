package com.fal.alex;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for file I\O helper methods. Used for file reading and writing.
 * @author Alexander Fal (falalexandr007@gmail.com)
 */
class FileUtils {

    /**
     * Reads strings from specified file.
     * @param in path to the input file
     * @return the {@link java.util.ArrayList} of Strings
     * @throws IOException if the file couldn't be read or was empty
     */
    static ArrayList<String> readStrings(String in) throws IOException {
        ArrayList<String> list;

        try (Scanner scanner = new Scanner(Paths.get(in))){
            list = new ArrayList<>();
            while(scanner.hasNext())
                list.add(scanner.next());

        } catch (IOException e) {
            throw new IOException("Couldn't read from specified file. Check if file exists.");
        }

        if(list.isEmpty())
            throw new IOException("File is empty.");

        return list;
    }

    /**
     * Reads integers from specified file.
     * @param in path to the input file
     * @return the {@link java.util.ArrayList} of Integers
     * @throws IOException if the file couldn't be read or was empty
     * @throws InputMismatchException if the file contains non-integers or range check for integer failed
     */
    static ArrayList<Integer> readIntegers(String in) throws IOException, InputMismatchException {
        ArrayList<Integer> list;

        try (Scanner scanner = new Scanner(Paths.get(in))){
            list = new ArrayList<>();
            while(scanner.hasNext())
                list.add(scanner.nextInt());

        } catch (IOException e) {
            throw new IOException("Couldn't read from specified file. Check if file exists.");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Couldn't parse integer.");
        }

        if(list.isEmpty())
            throw new IOException("File is empty.");

        return list;
    }

    /**
     * Writes data from {@link java.util.List} to specified file.
     * Creates the file if it doesn't exist.
     * Truncates the file if it exists
     * @param out path to the output file
     * @param list data to be written
     * @throws IOException if the file couldn't be written
     */
    static void writeTo(String out, List<String> list) throws IOException {
        Files.write(
                Paths.get(out),
                list,
                Charset.defaultCharset(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }
}