package com.tesinitsyn;

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь входного файла:");
        String inputFileName = scanner.nextLine();

        System.out.println("Введите путь выходного файла:");
        String outputFileName = scanner.nextLine();

        System.out.println("Выберите критерий сортировки (alphabet/length/word):");
        String criterion = scanner.nextLine();

        int wordIndex = -1;
        if (criterion.equals("word")) {
            System.out.println("Введите индекс слова для сортировки:");
            wordIndex = Integer.parseInt(scanner.nextLine());
        }

        sortLines(inputFileName, outputFileName, criterion, wordIndex);

        scanner.close();
    }

    public static void sortLines(String inputFileName, String outputFileName, String criterion, int wordIndex) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

            Map<String, Integer> linesCount = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (linesCount.containsKey(line)) {
                    linesCount.put(line, linesCount.get(line) + 1);
                } else {
                    linesCount.put(line, 1);
                }
            }
            reader.close();

            List<String> sortedLines = new ArrayList<>(linesCount.keySet());
            switch (criterion) {
                case "alphabet" -> Collections.sort(sortedLines);
                case "length" -> sortedLines.sort(Comparator.comparingInt(String::length));
                case "word" -> sortedLines.sort(Comparator.comparing(s -> s.split("\\s+")[wordIndex]));
            }

            for (String sortedLine : sortedLines) {
                if(linesCount.get(sortedLine) > 1){
                    for (int i = 0; i < linesCount.get(sortedLine); i++) {
                        writer.write(sortedLine + " " + linesCount.get(sortedLine) + "\n");
                    }
                }else {
                    writer.write(sortedLine + " " + linesCount.get(sortedLine) + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
