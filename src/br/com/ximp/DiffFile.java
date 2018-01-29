package br.com.ximp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class DiffFile {

    public static void main(String args[]) throws Exception {

        Path f1 = Paths.get(args[0]);
        Path f2 = Paths.get(args[1]);

        System.out.println("Reading "+f1.toString());
        List<String> input1 = readFile(f1);
        System.out.println("Reading "+f2.toString());
        List<String> input2 = readFile(f2);

        Collections.sort(input1);
        Collections.sort(input2);

        diffFiles(f2, input1, input2);
        diffFiles(f1, input2, input1);

        System.out.println("End");

    }

    private static void diffFiles(Path f2, List<String> input1, List<String> input2) {
        System.out.println(String.format("Missing lines in %s", f2.toString()));
        for (String line : input1) {
            if (Collections.binarySearch(input2, line) < 0) {
                System.out.println(line);
            }
        }
    }

    private static List<String> readFile(Path file) throws IOException {
        return Files.readAllLines(file);
    }
}