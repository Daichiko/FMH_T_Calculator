package BackEnd;

import java.text.DecimalFormat;
import java.util.HashMap;


public class Calculator {
	
	HashMap<Boolean, Double> hash = new HashMap<>();
	
	private double totalMiles;
	
	// Constructor
	public Calculator() {
        hash.put(true, 550.00);
        hash.put(false, 840.00);
    }
	
	
	// Functions
	/**
	 * Calculates the total number of miles traveled based on the distances of three routes.
	 * 
 	 * @param routeAB: From the Yard to the charge zone
	 * @param routeBC: From the charge zone to the discharge zone
	 * @param routeCA: From the discharge zone to the Yard
	 * @return The total number of miles traveled.
	 */
	public double calculateTotalMiles(double routeAB, double routeBC, double routeCA) {
        double totalmiles = routeAB + routeBC + routeCA;
        totalmiles = roundDecimals(totalmiles, 2);
        this.totalMiles = totalmiles;
        return totalmiles;
    }

	/**
	 * Rounds a given value to a specified number of decimal places.
	 *
	 * @param value    The value to be rounded.
	 * @param decimals The number of decimal places to round to.
	 * @return The rounded value.
	 */
    public static double roundDecimals(double value, int decimals) {
        String pattern = "#.";
        for (int i = 0; i < decimals; i++) {
            pattern += "#";
        }

        DecimalFormat df = new DecimalFormat(pattern);
        String formattedValue = df.format(value).replace(",", ".");
        return Double.parseDouble(formattedValue);
    }
    
    /**
     * Calculates the minimum price of the cargo based on the total miles and price per mile.
     *
     * @return The minimum price of the cargo.
     */
    public double calculateMinPrice(double pricePerMile) {
        double valueOfCargo = totalMiles * pricePerMile;
        return roundDecimals(valueOfCargo, 2);
    }
    
    /**
     * Calculates the value of the cargo based on the price per mile and the distance of route BC.
     *
     * @param pricePerMileBroucker The price per mile set by the Broucker.
     * @param routeBC The distance of route BC.
     * @return The value of the cargo.
     */
    public double calculateCargoValue(double pricePerMileBroucker, double routeBC) {
        return roundDecimals(pricePerMileBroucker * routeBC,2);
    }

    /**
     * Calculates the real payment per mile based on the value of the cargo and the total miles traveled.
     *
     * @param cargoValue  The value of the cargo.
     * @param totalMiles  The total miles traveled.
     * @return The real payment per mile.
     */
    public double calculateRealPayment(double cargoValue, double totalMiles) {
        return roundDecimals(cargoValue / totalMiles,2);
    }
    
    
    public boolean isLoadAceptable (boolean isBoxtruck, double totalmiles) {
    	
    	double maxload = hash.get(isBoxtruck);
    	
    	if (maxload < totalmiles)
    		return false;
    	return true;
    }
}
