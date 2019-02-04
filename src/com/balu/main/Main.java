package com.balu.main;

import com.balu.algorithm.Algorithm;
import com.balu.bom.InputBOM;
import com.balu.bom.SolutionWrapper;
import com.balu.gui.IterationDisplayGUI;
import com.balu.io.FileIO;
import com.balu.parser.InputParser;
import java.util.List;

/**
 * The main entry point for the program.
 * 
 * @author Balu_ADMIN
 */
public class Main {
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
        String fileContent = fileIO.loadUsingGUI();
        List<InputBOM> inputBOMs = null;
        try {
            inputBOMs = InputParser.parse(fileContent);
        }
        catch(AssertionError e) {
            fileIO.displayError(e.getMessage());
        }
        List<SolutionWrapper> result = Algorithm.calculate(inputBOMs);
        fileIO.saveSolution(result);
        IterationDisplayGUI iterationDisplayGUI = new IterationDisplayGUI();
        iterationDisplayGUI.setText(Algorithm.getIterationLog());
    }
}
