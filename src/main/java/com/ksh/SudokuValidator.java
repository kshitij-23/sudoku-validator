package com.ksh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SudokuValidator {

    public static void main(String[] args) throws IOException {
        System.out.println("Sudoku validator started with file name : "+args[0]);
        boolean validateCsvResult = SudokuValidator.validateCsv(args[0]);
        if(validateCsvResult) {
            int[][] array = SudokuValidator.readCsv(args[0]);
            boolean b = SudokuValidator.validateArrayForZerosAndDuplicates(array);
            if(b)
                System.out.println("0");
            else
                System.out.println("1");
        }
        else
            System.out.println("1");
    }

    public static final boolean validateCsv (String filePath) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(filePath));
        long lineCount = lines.count();
        if(lineCount == 0 || lineCount < 9)
            return false;
        lines = Files.lines(Paths.get(filePath));
        lineCount = lines.filter(l -> !l.trim().isEmpty()).count();
        if(lineCount < 9)
            return false;
        return true;
    }

    public static final int[][] readCsv(String filePath) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(filePath));
        int[][] array = new int[9][9];
        Map<String, int[][]> map = new HashMap<>();
        map.put("arrayObj", array);
        AtomicInteger count = new AtomicInteger(0);
        try {
            lines.forEach(l -> {
                if(!l.isEmpty()) {
                    String[] line = l.split(",");
                    int rowCount = count.getAndIncrement();
                    if (line.length != 9) {
                        System.out.println("CSV file is not valid");
                        map.put("arrayObj", null);
                    } else {
                        for (int j = 0; j < 9; j++) {
                            array[rowCount][j] = Integer.parseInt(line[j]);
                        }
                    }
                } else {
                    System.out.println("CSV file is not valid");
                }
            });
        } catch (Exception e) {
            map.put("arrayObj", null);
        }
        return map.get("arrayObj");
    }

    public static final boolean validateArrayForZerosAndDuplicates(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            Map<Integer, Integer> colMap = new TreeMap<>();
            Map<Integer, Integer> rowMap = new HashMap<>();
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == 0) {
                    System.out.println("Sudoku contains ZERO which is not valid.");
                    return false;
                } else {
                    if (rowMap.containsKey(array[i][j]))
                        rowMap.put(array[i][j], rowMap.get(array[i][j]) + 1);
                    else
                        rowMap.put(array[i][j], 1);

                    if (colMap.containsKey(array[j][i]))
                        colMap.put(array[j][i], colMap.get(array[j][i]) + 1);
                    else
                        colMap.put(array[j][i], 1);
                }
            }
            boolean rowMapResult = SudokuValidator.validateMap(rowMap);
            if(!rowMapResult) {
                System.out.println("duplicate values in row "+(i+1));
                return false;
            }
            boolean colMapResult = SudokuValidator.validateMap(colMap);
            if(!colMapResult) {
                System.out.println("duplicate values in column.");
                return false;
            }
        }
        return true;
    }

    public static final boolean validateMap(Map<Integer, Integer> map) {
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > 1) {
                return false;
            }
        }
        return true;
    }

}
