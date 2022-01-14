/**
 * This is the BiCycle class which extends AbstractVehicle
 * 
 * TCSS305 - Rentz
 */

package model.vehicles;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * This class extends AbstractVehicle and handles the prices and availability of the bicycles.
 * 
 * @author atreece
 * @version 1.0
 */

public class BiCycle extends AbstractVehicle {
    
    /**
     * The standard fare for a bike.
     */
    public static final BigDecimal CYCLE_FARE = new BigDecimal("10.00");
    
    /**
     * The type of bicycle being used.
     */
    private final String myBiCycleType;
    
    /**
     * Parameterized constructor for the initialized fields. 
     * Calls the AbstractVehicle class.
     * @param theVehicleID
     * @param theVIN
     * @param theName
     * @param theCanRent
     * @param theBiCycleType
     */
    public BiCycle(final int theVehicleID, final String theVIN, final String theName, final boolean theCanRent,
                   final String theBiCycleType) {
        super(theVehicleID, theVIN, theName, theCanRent);
        myBiCycleType = theBiCycleType;
    }
    
    /**
     * Getter for myBiCycleType.
     * @return myBiCycleType
     */
    public String getMyBiCycleType() {
        return myBiCycleType;
    }

 
    /**
     * Calculator for the amount it will cost to rent a bicycle.
     */
    @Override
    public BigDecimal calculateRentalAmount() {
        BigDecimal rentalAmount = CYCLE_FARE;
        final BigDecimal mountainAdd = new BigDecimal("0.10");
        final BigDecimal cruiserAdd = new BigDecimal("0.20");
        final BigDecimal hybridAdd = new BigDecimal("0.40");
        
        if ("Road".equals(myBiCycleType)) {
            rentalAmount = CYCLE_FARE;
        }
        if ("Mountain".equals(myBiCycleType)) {
            rentalAmount = rentalAmount.add(mountainAdd);
        }
        if ("Cruiser".equals(myBiCycleType)) {
            rentalAmount = rentalAmount.add(cruiserAdd);
        }
        if ("Hybrid".equals(myBiCycleType)) {
            rentalAmount = rentalAmount.add(hybridAdd);
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
        
        sb.append("BiCycle ");
        sb.append("(");
        sb.append("ID:" + this.getMyVehicleID());
        sb.append(commaSpace);
        sb.append("Name:" + this.getMyName());
        sb.append(commaSpace);
        sb.append("VIN:" + this.getMyVIN());
        sb.append(commaSpace);
        sb.append("CanRent?:" + this.isMyCanRent());
        sb.append(commaSpace);
        sb.append("CycleType:" + this.getMyBiCycleType());
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
            final BiCycle other = (BiCycle) theOtherObject;
            result = Integer.compare(this.getMyVehicleID(), other.getMyVehicleID()) == 0
                            && this.getMyName().compareTo(other.getMyName()) == 0
                            && this.getMyVIN().compareTo(other.getMyVIN()) == 0
                            && Boolean.compare(this.isMyCanRent(), other.isMyCanRent()) == 0
                            && this.getMyBiCycleType().compareTo(other.getMyBiCycleType()) == 0;
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
                            this.isMyCanRent(), this.getMyBiCycleType());
    }
}
