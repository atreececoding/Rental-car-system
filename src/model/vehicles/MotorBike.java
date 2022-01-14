/**
 * This is the MotorBike class which extends AbstractVehicle
 * 
 * TCSS305 - Rentz
 */

package model.vehicles;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;


/**
 * This class extends AbstractVehicle and handles the prices and availability of the motor bikes.
 * 
 * @author atreece
 * @version 1.0
 */


public class MotorBike extends AbstractVehicle {
    
    /**
     * The standard fare for a motor bike.
     */
    public static final BigDecimal BIKE_FARE = new BigDecimal("20.00");
    
    /**
     * Boolean for whether or not the motor bike is touring or not.
     */
    private final boolean myTouring;


    /**
     * Parameterized constructor.
     * Calls super from AbstractVehicle.
     * @param theVehicleID
     * @param theVIN
     * @param theName
     * @param theCanRent
     * @param theTouring
     */
    public MotorBike(final int theVehicleID, final String theVIN, final String theName, final boolean theCanRent, final boolean theTouring) {
        super(theVehicleID, theVIN, theName, theCanRent);
        myTouring = theTouring;
    }

    /**
     * Getter for myTouring.
     * @return myTouring
     */
    public boolean isMyTouring() {
        return myTouring;
    }
    
    /**
     * Calculator for the amount it will cost to rent a car.
     * @return rentalAmount
     */
    @Override
    public BigDecimal calculateRentalAmount() {
        BigDecimal rentalAmount = BIKE_FARE;
        final BigDecimal touringAdd = new BigDecimal("5.00");
        
        if (myTouring) {
            rentalAmount = rentalAmount.add(touringAdd);
        }
        
        final NumberFormat formatToCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        formatToCurrency.format(rentalAmount);
        return rentalAmount;
    }
    
    /**
     * toString method. Formats the print out options.
     * @return StringBuilder
     */
    @Override
    public String toString() {
        final String commaSpace = ", ";
        final StringBuilder sb = new StringBuilder();
        
        sb.append("MotorBike ");
        sb.append("(");
        sb.append("ID:" + this.getMyVehicleID());
        sb.append(commaSpace);
        sb.append("Name:" + this.getMyName());
        sb.append(commaSpace);
        sb.append("VIN:" + this.getMyVIN());
        sb.append(commaSpace);
        sb.append("CanRent?:" + this.isMyCanRent());
        sb.append(commaSpace);
        sb.append("IsTouring:" + this.isMyTouring());
        sb.append(")");
        
        return sb.toString();
    }
    
    /**
     * Overrides .equals method. 
     * @return boolean
     */
    @Override
    public boolean equals(final Object theOtherObject) {
        boolean result = true;

        if (this == theOtherObject) {
            result = true;
        } else if (theOtherObject == null) {
            result = false;
        } else if (this.getClass() != theOtherObject.getClass()) {
            result = false;
        } else {
            final MotorBike other = (MotorBike) theOtherObject;
            result = Integer.compare(this.getMyVehicleID(), other.getMyVehicleID()) == 0
                            && this.getMyName().compareTo(other.getMyName()) == 0
                            && this.getMyVIN().compareTo(other.getMyVIN()) == 0
                            && Boolean.compare(this.isMyCanRent(), other.isMyCanRent()) == 0
                            && Boolean.compare(this.isMyTouring(), other.isMyTouring()) == 0;
        }
        return result;
    }
    
    /**
     * hashCode associated with toString.
     * @return hashCode (integer)
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getMyVehicleID(), this.getMyVIN(), this.getMyName(), 
                            this.isMyCanRent(), this.isMyTouring());
    }
        
}

    

