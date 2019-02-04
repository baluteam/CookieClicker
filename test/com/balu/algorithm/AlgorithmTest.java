package com.balu.algorithm;

import com.balu.bom.InputBOM;
import com.balu.bom.SolutionWrapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Balu_ADMIN
 */
public class AlgorithmTest {
    
    private static List<InputBOM> dataRows;
    
    private static List<SolutionWrapper> expectedResults;
    
    public AlgorithmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Test input data rows
        dataRows = new ArrayList<>();
        InputBOM testInput_1 = new InputBOM(30.0d, 1.0d, 2.0d);
        InputBOM testInput_2 = new InputBOM(30.0d, 2.0d, 100.0d);
        InputBOM testInput_3 = new InputBOM(30.50000d, 3.14159d, 1999.19990d);
        InputBOM testInput_4 = new InputBOM(500.0d, 4.0d, 2000.0d);
        dataRows.add(testInput_1);
        dataRows.add(testInput_2);
        dataRows.add(testInput_3);
        dataRows.add(testInput_4);
        // Expected results
        expectedResults = new ArrayList<>();
        SolutionWrapper expectedResult_1 = new SolutionWrapper(testInput_1, 1.0d);
        SolutionWrapper expectedResult_2 = new SolutionWrapper(testInput_2, 39.16666d);
        SolutionWrapper expectedResult_3 = new SolutionWrapper(testInput_3, 63.968d);
        SolutionWrapper expectedResult_4 = new SolutionWrapper(testInput_4, 526.19047d);
        expectedResults.add(expectedResult_1);
        expectedResults.add(expectedResult_2);
        expectedResults.add(expectedResult_3);
        expectedResults.add(expectedResult_4);
    }
    
    @AfterClass
    public static void tearDownClass() {
        dataRows = null;
        expectedResults = null;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculate method of class Algorithm with the example input data rows.
     */
    @Test
    public void testCalculate_Example() {
        List<SolutionWrapper> result = Algorithm.calculate(dataRows);
        assertNotNull("The result may not be null.", result);
        assertNotNull("The input data row may not be null.", dataRows);
        assertTrue("The result may not be an empty list.", (!result.isEmpty()));
        assertTrue("The result should contain as many elements(" + result.size() + ") as the input data(" + dataRows.size() + ")", (dataRows.size() == result.size()));
        for(int i=0; i<expectedResults.size(); i++) {
            assertEquals("Expected: " + expectedResults.get(i).getMinimumNumberOFSecondsNeededToEndTheGame() + " but got " + result.get(i).getMinimumNumberOFSecondsNeededToEndTheGame(), 
                    expectedResults.get(i).getMinimumNumberOFSecondsNeededToEndTheGame(), 
                    result.get(i).getMinimumNumberOFSecondsNeededToEndTheGame(), 
                    0d
            ); 
        }
    }
    
    /**
     * Test of calculate method of class Algorithm with null input.
     */
    @Test
    public void testCalculate_NullInput() {
        List<SolutionWrapper> result = Algorithm.calculate(null);
        assertNotNull("The result may not be null.", result);
        assertTrue("The result should be an empty list.", result.isEmpty());
    }
    
    /**
     * Test of calculate method of class Algorithm with an empty input.
     */
    @Test
    public void testCalculate_EmptyInput() {
        List<InputBOM> emptyInput = new ArrayList<>();
        List<SolutionWrapper> result = Algorithm.calculate(emptyInput);
        assertNotNull("The result may not be null.", result);
        assertNotNull("The input data row may not be null.", dataRows);
        assertTrue("The result should be an empty list.", result.isEmpty());
        assertTrue("The result should contain as many elements(" + result.size() + ") as the input data(" + emptyInput.size() + ")", (emptyInput.size() == result.size()));
    }
}
