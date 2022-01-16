package com.ksh;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class SudokuValidatorTest {

    @Test
    public void validateCsvEmptyFileTest() throws IOException {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("empty-file.csv")).getPath();
        boolean result = SudokuValidator.validateCsv(String.valueOf(Paths.get(filePath)));
        Assert.assertFalse(result);
    }
    @Test
    public void validateCsvHalfEmptyFileTest() throws IOException {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("half-empty-file.csv")).getPath();
        boolean result= SudokuValidator.validateCsv(String.valueOf(Paths.get(filePath)));
        Assert.assertFalse(result);
    }
    @Test
    public void readCsvFile1FailTest() throws IOException {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("wrong-values-file1.csv")).getPath();
        int[][] array = SudokuValidator.readCsv(String.valueOf(Paths.get(filePath)));
        Assert.assertNull(array);
    }
    @Test
    public void readCsvFile2FailTest() throws IOException {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("wrong-values-file2.csv")).getPath();
        int[][] array = SudokuValidator.readCsv(String.valueOf(Paths.get(filePath)));
        Assert.assertNull(array);
    }
    @Test
    public void validateSudokuFailTest() throws IOException {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("validate-sudoku-fail.csv")).getPath();
        int[][] array = SudokuValidator.readCsv(String.valueOf(Paths.get(filePath)));
        boolean result = SudokuValidator.validateArrayForZerosAndDuplicates(array);
        Assert.assertFalse(result);
    }
    @Test
    public void validateSudokuSuccessTest() throws IOException {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("validate-sudoku-success.csv")).getPath();
        int[][] array = SudokuValidator.readCsv(String.valueOf(Paths.get(filePath)));
        boolean result = SudokuValidator.validateArrayForZerosAndDuplicates(array);
        Assert.assertTrue(result);
    }

}
