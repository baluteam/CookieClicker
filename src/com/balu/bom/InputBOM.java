package com.balu.bom;

/**
 * The class representation (structure) of a line of the input file.
 * 
 * @author Balu_ADMIN
 */
public class InputBOM {
    /**
     * The number of cookies produced pro second without any cookie farm.
     */
    public static final double DEFAULT_COOKIE_PRODUCTION_RATE_PER_SECOND = 2.0d;
    /**
     * The number of cookies, which we start the game with
     */
    public static final double STARTING_AMOUNT_OF_COOKIES = 0d;
    /**
     * The expected data separator of the input format
     */
    private static final String DATA_SEPARATOR = ExpectedOutputFormat.DATA_SEPARATOR;
    /**
     * How many cookie a farm is going to cost to be built
     */
    private final double priceOfCookieFarm;
    /**
     * How many cookies a new farm produces as extra
     */
    private final double cookieFarmProductionRate;
    /**
     * How many cookies are needed to end the game successfully 
     */
    private final double numberOfCookiesNeededToEndTheGame;
    /**
     * To keep the original number formats, because while parsing the input lines, some inputs have different fractional length
     */
    private final String originalDataLineStrFromInput;

    /**
     * Constructor for setting the input fields.
     * 
     * @param priceOfCookieFarm
     * @param cookieFarmProductionRate
     * @param numberOfCookiesNeededToEndTheGame 
     * @deprecated creating the original data line input string from these doubles have a good chance of loosing the original fracitonal lengths
     */
    @Deprecated
    public InputBOM(double priceOfCookieFarm, double cookieFarmProductionRate, double numberOfCookiesNeededToEndTheGame) {
        this.priceOfCookieFarm = priceOfCookieFarm;
        this.cookieFarmProductionRate = cookieFarmProductionRate;
        this.numberOfCookiesNeededToEndTheGame = numberOfCookiesNeededToEndTheGame;
        this.originalDataLineStrFromInput = Double.toString(priceOfCookieFarm) + DATA_SEPARATOR + Double.toString(cookieFarmProductionRate) + DATA_SEPARATOR + Double.toString(numberOfCookiesNeededToEndTheGame);
    }
    
    /**
     * Constructor for setting the input fields.
     * 
     * @param priceOfCookieFarm
     * @param cookieFarmProductionRate
     * @param numberOfCookiesNeededToEndTheGame 
     * @param originalDataLineStrFromInput the original data line string from the input keeping the original fractional lengths
     */
    public InputBOM(double priceOfCookieFarm, double cookieFarmProductionRate, double numberOfCookiesNeededToEndTheGame, String originalDataLineStrFromInput) {
        this.priceOfCookieFarm = priceOfCookieFarm;
        this.cookieFarmProductionRate = cookieFarmProductionRate;
        this.numberOfCookiesNeededToEndTheGame = numberOfCookiesNeededToEndTheGame;
        this.originalDataLineStrFromInput = originalDataLineStrFromInput;
    }
    
    public double getPriceOfCookieFarm() {
        return priceOfCookieFarm;
    }
    
    /**
     * Gets the price of a cookie farm to be built.
     * 
     * @return 
     */
    public double getC() {
        return getPriceOfCookieFarm();
    }

    public double getCookieFarmProductionRate() {
        return cookieFarmProductionRate;
    }
    
    /**
     * Gets how many cookies a new farm can produce as extra.
     * 
     * @return 
     */
    public double getF() {
        return getCookieFarmProductionRate();
    }

    public double getNumberOfCookiesNeededToEndTheGame() {
        return numberOfCookiesNeededToEndTheGame;
    }
    
    /**
     * Gets how many cookies are needed to end the game successfully.
     * 
     * @return 
     */
    public double getX() {
        return getNumberOfCookiesNeededToEndTheGame();
    }

    public String getOriginalDataLineStrFromInput() {
        return originalDataLineStrFromInput;
    }

    @Override
    public String toString() {
        return "InputBOM{" + "priceOfCookieFarm=" + priceOfCookieFarm + ", cookieFarmProductionRate=" + cookieFarmProductionRate + ", numberOfCookiesNeededToEndTheGame=" + numberOfCookiesNeededToEndTheGame + '}';
    }
}
