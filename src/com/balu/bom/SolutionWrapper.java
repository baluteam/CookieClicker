package com.balu.bom;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BOM object that wraps and represents the expected output data structure and format
 * 
 * @author Balu_ADMIN
 */
public class SolutionWrapper extends InputBOM implements ExpectedOutputFormat {
    /**
     * The minimum number of seconds needed to end the game successfully.
     */
    private double minimumNumberOFSecondsNeededToEndTheGame;

    /**
     * Constructor for setting the output fields.
     * 
     * @param priceOfCookieFarm
     * @param cookieFarmProductionRate
     * @param numberOfCookiesNeededToEndTheGame
     * @param minimumNumberOFSecondsNeededToEndTheGame it will be maximal of ExpectedOutputFormat.MAX_FRACTIONAL_PART digits after the decimal point, the rest will be trimmed if there is any
     * @param originalDataLineStrFromInput
     */
    public SolutionWrapper(double priceOfCookieFarm, double cookieFarmProductionRate, double numberOfCookiesNeededToEndTheGame, double minimumNumberOFSecondsNeededToEndTheGame, String originalDataLineStrFromInput) {
        super(priceOfCookieFarm, cookieFarmProductionRate, numberOfCookiesNeededToEndTheGame, originalDataLineStrFromInput);
        setMinimumNumberOFSecondsNeededToEndTheGame(minimumNumberOFSecondsNeededToEndTheGame);
    }
    
    /**
     * Constructor for setting the output fields.
     * 
     * @param inputBOM may not be null or nullpointer will be thrown
     * @param minimumNumberOFSecondsNeededToEndTheGame 
     */
    public SolutionWrapper(InputBOM inputBOM, double minimumNumberOFSecondsNeededToEndTheGame) {
        this(inputBOM.getPriceOfCookieFarm(), inputBOM.getCookieFarmProductionRate(), inputBOM.getNumberOfCookiesNeededToEndTheGame(), minimumNumberOFSecondsNeededToEndTheGame, inputBOM.getOriginalDataLineStrFromInput());
    }

    public double getMinimumNumberOFSecondsNeededToEndTheGame() {
        return minimumNumberOFSecondsNeededToEndTheGame;
    }
    
    /**
     * Sets the minimumNumberOFSecondsNeededToEndTheGame and crops it to have no longer than ExpectedOutputFormat.MAX_FRACTIONAL_PART after the decimal point in the fractional part
     * 
     * @param minimumNumberOFSecondsNeededToEndTheGame 
     */
    private void setMinimumNumberOFSecondsNeededToEndTheGame(double minimumNumberOFSecondsNeededToEndTheGame) {
        //BigDecimal does not have a proper rounding mode to trim of the rest of the fractional part
        BigDecimal bd = BigDecimal.valueOf(minimumNumberOFSecondsNeededToEndTheGame);
        bd = bd.movePointRight(MAX_FRACTIONAL_PART); //3.1456789 --> 314567.89 or 3.14 --> 314000
        BigInteger bi = bd.toBigInteger(); //314567.89 --> 314567 : any fractional part will be discarded, no rounding
        bd = new BigDecimal(bi);
        bd = bd.movePointLeft(MAX_FRACTIONAL_PART); //314567 --> 3.14567
        this.minimumNumberOFSecondsNeededToEndTheGame = bd.doubleValue();
        //System.out.println(minimumNumberOFSecondsNeededToEndTheGame + " --> " + this.minimumNumberOFSecondsNeededToEndTheGame);
    }
    
    @Override
    public String toExpectedOutputFormat() {
        return getOriginalDataLineStrFromInput() + DATA_SEPARATOR + getMinimumNumberOFSecondsNeededToEndTheGame();
    }
}
