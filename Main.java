package com.fal.alex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Main class in which arguments are checked, data is read, sorted and written to output file using helper methods.
 *  @author Alexander Fal (falalexandr007@gmail.com)
 */
public class Main {

    private final static String defaultOutputFile = "out.txt";

    private static boolean sortIntegers = false;
    private static boolean sortDescending = false;
    private static String input, output;

    public static void main(String[] args) {

        List<? extends Comparable> list;

        try {
           parseArgs(args);

           list = sortIntegers ?
                    FileUtils.readIntegers(input) :
                    FileUtils.readStrings(input);

        } catch (IOException | InputMismatchException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        SortUtils.sort(list, sortDescending);

        try {
            FileUtils.writeTo(output, sortIntegers ?
                    list.stream().map(String::valueOf).collect(Collectors.toList()) :
                    (List<String>) list);
        } catch (IOException e) {
            System.out.println("Couldn't create output file.\nResulting array: " + list);
        }
    }

    private static void parseArgs(String[] args) throws IllegalArgumentException {
        if(args.length < 1) {
            throw new IllegalArgumentException("Usage: java -jar file_sort.jar in.txt [-o out.txt] [-i|-s] [-a|-d]\n"
                    + "in.txt       - input file\n"
                    + "-o out.txt   - output file (default out.txt)\n"
                    + "-i           - sort integers\n"
                    + "-s           - sort strings (default)\n"
                    + "-a           - sort in ascending order (default)\n"
                    + "-d           - sort in descending order");
        }

        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args));

        if(argsList.contains("-i") && argsList.contains("-s"))
            throw new IllegalArgumentException("Can't use both -i and -s options.");
        if(argsList.contains("-d") && argsList.contains("-a"))
            throw new IllegalArgumentException("Can't use both -a and -d options.");

        if(argsList.contains("-d"))
            sortDescending = true;
        if(argsList.contains("-i"))
            sortIntegers = true;

        input = argsList.get(0);

        if(argsList.contains("-o")) {
            try {
                output = argsList.get(argsList.indexOf("-o") + 1);
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Output file must be specified if -o option is used.");
            }
        } else
            output = defaultOutputFile;
    }
}
