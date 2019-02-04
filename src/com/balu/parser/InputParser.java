package com.balu.parser;

import com.balu.bom.ExpectedOutputFormat;
import com.balu.bom.InputBOM;
import com.balu.io.FileIO;
import java.util.ArrayList;
import java.util.List;

/**
 * This utility class handles the parsing of the input string content.
 * 
 * @author Balu_ADMIN
 */
public class InputParser {
    /**
     * The expected data separator of the input format
     */
    private static final String DATA_SEPARATOR = ExpectedOutputFormat.DATA_SEPARATOR;
    /**
     * The expected separator of the input data rows
     */
    private static final String LINE_SEPARATOR = FileIO.LINE_SEPARATOR;
    /**
     * Number of te data cells in the input row
     */
    private static final int NUMBER_OF_DATA_IN_A_ROW = 3;
    /**
     * Private constructor for disabling creating instances of this utility class.
     */
    private InputParser() {}
    
    /**
     * This method knows what to expect as the input format and how to parse it and return the business object models
     * 
     * @param inputFileContent
     * @return
     * @throws AssertionError (is enabled in the project as VM option -enableassertions)
     * @throws NullPointerException
     * @throws NumberFormatException 
     */
    public static List<InputBOM> parse(String inputFileContent) throws AssertionError, NullPointerException, NumberFormatException {
        List<InputBOM> inputBOMs = new ArrayList<>();
        assert (inputFileContent != null && !inputFileContent.equals("")) : "The input file is null or empty";
        String[] lines = inputFileContent.trim().split(LINE_SEPARATOR); //remove white spaces from the beginning and from the end and split the data rows
        assert (0 < lines.length) : "The input file had no data in it";
        InputBOM inputBOM;
        for(String line : lines) {
            String[] cells = line.trim().split(DATA_SEPARATOR); //remove white space from the beginning and from the end and splic the data cells in a row
            assert (cells.length == NUMBER_OF_DATA_IN_A_ROW) : "There is/are " + cells.length + " data cells instead of the expected " + NUMBER_OF_DATA_IN_A_ROW;
            double priceOfCookieFarm = Double.parseDouble(cells[0].trim());
            double cookieFarmProductionRate = Double.parseDouble(cells[1].trim());
            double numberOfCookiesNeededToEndTheGame = Double.parseDouble(cells[2].trim());
            //TODO: checking the limits and ExpectedOutputFormat.MAX_FRACTIONAL_PART for the fractional part length if needed
            //      Small dataset 1 ? C ? 500; 1 ? F ? 4; 1 ? X ? 2000 
            //      Large dataset 1 ? C ? 10000; 1 ? F ? 100; 1 ? X ? 100000 
            inputBOM = new InputBOM(priceOfCookieFarm, cookieFarmProductionRate, numberOfCookiesNeededToEndTheGame);
            inputBOMs.add(inputBOM);
        }
        return inputBOMs;
    }
}
