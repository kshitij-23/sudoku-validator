package com.ksh;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SudokuValidatorTest {

    public String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        Path filePath = new File(url.getPath()).toPath();
        return filePath.toString();
    }

    @Test
    public void validateCsvEmptyFileTest() throws IOException {
        String filePath = getFilePath("empty-file.csv");
        boolean result = SudokuValidator.validateCsv(String.valueOf(Paths.get(filePath)));
        Assert.assertFalse(result);
    }
    @Test
    public void validateCsvHalfEmptyFileTest() throws IOException {
        String filePath = getFilePath("half-empty-file.csv");
        boolean result= SudokuValidator.validateCsv(String.valueOf(Paths.get(filePath)));
        Assert.assertFalse(result);
    }
    @Test
    public void readCsvFile1FailTest() throws IOException {
        String filePath = getFilePath("wrong-values-file1.csv");
        int[][] array = SudokuValidator.readCsv(String.valueOf(Paths.get(filePath)));
        Assert.assertNull(array);
    }
    @Test
    public void readCsvFile2FailTest() throws IOException {
        String filePath = getFilePath("wrong-values-file2.csv");
        int[][] array = SudokuValidator.readCsv(String.valueOf(Paths.get(filePath)));
        Assert.assertNull(array);
    }
    @Test
    public void validateSudokuFailTest() throws IOException {
        String filePath = getFilePath("validate-sudoku-fail.csv");
        int[][] array = SudokuValidator.readCsv(String.valueOf(Paths.get(filePath)));
        boolean result = SudokuValidator.validateArrayForZerosAndDuplicates(array);
        Assert.assertFalse(result);
    }
    @Test
    public void validateSudokuSuccessTest() throws IOException {
        String filePath = getFilePath("validate-sudoku-success.csv");
        int[][] array = SudokuValidator.readCsv(String.valueOf(Paths.get(filePath)));
        boolean result = SudokuValidator.validateArrayForZerosAndDuplicates(array);
        Assert.assertTrue(result);
    }

}
