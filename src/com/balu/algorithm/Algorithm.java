package com.balu.algorithm;

import com.balu.bom.ExpectedOutputFormat;
import com.balu.bom.InputBOM;
import com.balu.bom.SolutionWrapper;
import com.balu.io.FileIO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This utility class is responsible for calculating the solution.
 * 
 * @author Balu_ADMIN
 */
public final class Algorithm {
    private static StringBuilder iterationLog;
    private static final Logger LOGGER = Logger.getLogger(Algorithm.class.getName());
    
    /**
     * No need for having instances of this class. It is bound to solve an exact problem in a single threaded app. No generic implementation is needed here.
     */
    private Algorithm() {}
    
    /**
     * Calculate the solution of the given problem.
     * 
     * @param dataRows
     * @return even if the dataRows input is null, an empty list will be returned at least
     */
    public static List<SolutionWrapper> calculate(List<InputBOM> dataRows) {
        iterationLog = new StringBuilder();
        List<SolutionWrapper> result = new ArrayList<>();
        if(dataRows == null) {
            return result;
        }
        for(InputBOM dataRow : dataRows) {
            double minimumNumberOFSecondsNeededToEndTheGame = calculateMinNumberOfSecondsNeeded(dataRow);
            SolutionWrapper solution = new SolutionWrapper(dataRow, minimumNumberOFSecondsNeededToEndTheGame);
            String msg = solution.toExpectedOutputFormat() + FileIO.LINE_SEPARATOR;
            LOGGER.log(Level.INFO, msg);
            iterationLog.append(msg);
            result.add(solution);
        }
        return result;
    }
    
    /**
     * Calculates the minimum number of seconds needed to end this game for the a given input row.
     * 
     * @param dataRow
     * @return 
     */
    private static double calculateMinNumberOfSecondsNeeded(final InputBOM dataRow) {
        String msg = String.format("%s %s", dataRow.toString(), FileIO.LINE_SEPARATOR);
        LOGGER.log(Level.INFO, msg);
        iterationLog.append(msg);
        return calculateMinNumberOfSecondsNeededRecursively(dataRow, InputBOM.DEFAULT_COOKIE_PRODUCTION_RATE_PER_SECOND, 0d);
    }
    
    /**
     * Separated the method to allow faster recursion iterations to calculate the seconds needed to end the game
     * 
     * @param dataRow
     * @param currentCookieRate [cookie(s)] the current cookie production rate of the iteration/recursion
     * @param secondsSpentAlready [second] the amount of seconds spent already till this iteration
     * @return 
     */
    private static double calculateMinNumberOfSecondsNeededRecursively(final InputBOM dataRow, final double currentCookieRate, final double secondsSpentAlready) {
        double secondsNeededToReachGoalUsingCookieProduction;
        double secondsNeededToBuildCookieFarm;
        double secondsNeededToReachGoalAfterFarm;
        if(Double.compare(currentCookieRate, InputBOM.DEFAULT_COOKIE_PRODUCTION_RATE_PER_SECOND) == 0) { //it is the first iteration
            secondsNeededToReachGoalUsingCookieProduction = (dataRow.getNumberOfCookiesNeededToEndTheGame() - InputBOM.STARTING_AMOUNT_OF_COOKIES) / currentCookieRate; //[seconds]
            secondsNeededToBuildCookieFarm = (dataRow.getPriceOfCookieFarm() - InputBOM.STARTING_AMOUNT_OF_COOKIES) / currentCookieRate; //[seconds]
        }
        else {
            secondsNeededToReachGoalUsingCookieProduction = dataRow.getNumberOfCookiesNeededToEndTheGame() / currentCookieRate; //[seconds]
            secondsNeededToBuildCookieFarm = dataRow.getPriceOfCookieFarm() / currentCookieRate; //[seconds]
        }
        secondsNeededToReachGoalAfterFarm  = secondsNeededToBuildCookieFarm + (dataRow.getNumberOfCookiesNeededToEndTheGame() / (currentCookieRate + dataRow.getCookieFarmProductionRate())); //[seconds]
        //we need to add some limitation, because even with -Xss10M VM stack size argument the recursion might reach 71996 depth and causes java.lang.StackOverflowError
        BigDecimal secNeededToReachGoalAfterFarm = BigDecimal.valueOf(secondsNeededToReachGoalAfterFarm);
        secNeededToReachGoalAfterFarm = secNeededToReachGoalAfterFarm.setScale(ExpectedOutputFormat.MAX_FRACTIONAL_PART+1, RoundingMode.HALF_EVEN);
        BigDecimal secNeededToReachGoalUsingCookieProduction = BigDecimal.valueOf(secondsNeededToReachGoalUsingCookieProduction);
        secNeededToReachGoalUsingCookieProduction = secNeededToReachGoalUsingCookieProduction.setScale(ExpectedOutputFormat.MAX_FRACTIONAL_PART+1, RoundingMode.HALF_EVEN);
        boolean isItWorthToBuildFarm = (secNeededToReachGoalAfterFarm.compareTo(secNeededToReachGoalUsingCookieProduction) < 0); // wrong, in some cases it will never end: (Double.compare(secondsNeededToReachGoalAfterFarm, secondsNeededToReachGoalUsingCookieProduction) < 0);
        /*
        String msg = String.format("Iteration %d.:\n\tcurrentCookieRate: %f \n\tsecondsSpentAlready: %f \n\tsecondsNeededToReachGoalUsingCookieProduction: %f \n\tsecondsNeededToBuildCookieFarm: %f \n\tsecondsNeededToReachGoalAfterFarm: %f \n\tisItWorthToBuildFarm: %b \n", 
                (((int)((currentCookieRate-InputBOM.DEFAULT_COOKIE_PRODUCTION_RATE_PER_SECOND) / dataRow.getCookieFarmProductionRate()))+1), 
                currentCookieRate,                              // 6317074.809450128                --> 6317074,809450
                secondsSpentAlready,                            //       0.6762120585449962         -->       0,676212
                secondsNeededToReachGoalUsingCookieProduction,  //       0.015830022660235758       -->       0,015830
                secondsNeededToBuildCookieFarm,                 //       1.7345844921146024E-7      -->       0,000000
                secondsNeededToReachGoalAfterFarm,              //       0.01582994778130971        -->       0,015830
                isItWorthToBuildFarm
        );
        */
        StringBuilder sb = new StringBuilder();
        sb.append("Iteration ").append(((int)((currentCookieRate-InputBOM.DEFAULT_COOKIE_PRODUCTION_RATE_PER_SECOND) / dataRow.getCookieFarmProductionRate()))+1).append(".:");
        sb.append("\n\tcurrentCookieRate: ").append(currentCookieRate);
        sb.append("\n\tsecondsSpentAlready: ").append(secondsSpentAlready);
        sb.append("\n\tsecondsNeededToReachGoalUsingCookieProduction: ").append(secondsNeededToReachGoalUsingCookieProduction);
        sb.append("\n\tsecondsNeededToBuildCookieFarm: ").append(secondsNeededToBuildCookieFarm);
        sb.append("\n\tsecondsNeededToReachGoalAfterFarm: ").append(secondsNeededToReachGoalAfterFarm);
        sb.append("\n\tisItWorthToBuildFarm: ").append(isItWorthToBuildFarm);
        sb.append("\n");
        String msg = sb.toString();
        LOGGER.log(Level.INFO, msg);
        iterationLog.append(msg);
        if(isItWorthToBuildFarm) {
            return calculateMinNumberOfSecondsNeededRecursively(
                    dataRow, 
                    (currentCookieRate + dataRow.getCookieFarmProductionRate()), 
                    (secondsSpentAlready + secondsNeededToBuildCookieFarm)
            );
        }
        msg = "\t==> timeNeeded: " + (secondsSpentAlready + secondsNeededToReachGoalUsingCookieProduction) + " sec\n";
        LOGGER.log(Level.INFO, msg);
        iterationLog.append(msg);
        return (secondsSpentAlready + secondsNeededToReachGoalUsingCookieProduction);
    }
    
    /**
     * Returns the logged calculations.
     * 
     * @return 
     */
    public static String getIterationLog() {
        return iterationLog.toString();
    }
}
