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
     * Constructor for setting the input fields.
     * 
     * @param priceOfCookieFarm
     * @param cookieFarmProductionRate
     * @param numberOfCookiesNeededToEndTheGame 
     */
    public InputBOM(double priceOfCookieFarm, double cookieFarmProductionRate, double numberOfCookiesNeededToEndTheGame) {
        this.priceOfCookieFarm = priceOfCookieFarm;
        this.cookieFarmProductionRate = cookieFarmProductionRate;
        this.numberOfCookiesNeededToEndTheGame = numberOfCookiesNeededToEndTheGame;
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

    @Override
    public String toString() {
        return "InputBOM{" + "priceOfCookieFarm=" + priceOfCookieFarm + ", cookieFarmProductionRate=" + cookieFarmProductionRate + ", numberOfCookiesNeededToEndTheGame=" + numberOfCookiesNeededToEndTheGame + '}';
    }
}
