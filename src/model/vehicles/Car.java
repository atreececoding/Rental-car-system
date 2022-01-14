/**
 * This is the Car class which extends AbstractVehicle
 * 
 * TCSS305 - Rentz
 */

package model.vehicles;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * This class extends AbstractVehicle and handles the prices and availability of the cars.
 * 
 * @author atreece
 * @version 1.0
 */


public class Car extends AbstractVehicle {
    
    /**
     * The standard fare of a car.
     */
    public static final BigDecimal CAR_FARE = new BigDecimal("30.00");

    /**
     * Boolean for if the car is luxury or not.
     */
    private final boolean myLuxury;
    
    /**
     * Boolean for if the car has navigation or not.
     */
    private final boolean myNavigation;
    
    /**
     * Boolean for if the car has driving assistance or not.
     */
    private final boolean myDrivingAssistance;
        
    /**
     * Parameterized constructor. 
     * Calls super from AbstractVehicle.
     * @param theVehicleID
     * @param theVIN
     * @param theName
     * @param theCanRent
     * @param theLuxury
     * @param theNavigation
     * @param theDrivingAssistance
     */
    public Car(final int theVehicleID, final String theVIN, final String theName, final boolean theCanRent,
               final boolean theLuxury, final boolean theNavigation, final boolean theDrivingAssistance) {
        super(theVehicleID, theVIN, theName, theCanRent);
        
        myLuxury = theLuxury;
        myNavigation = theNavigation;
        myDrivingAssistance = theDrivingAssistance;
    }
    
    /**
     * Getter for myLuxury.
     * @return myLuxury
     */
    public boolean isMyLuxury() {
        return myLuxury;
    }

    /**
     * Getter for myNavigation.
     * @return myNavigation
     */
    public boolean isMyNavigation() {
        return myNavigation;
    }

    /**
     * Getter for myDrivingAssistance.
     * @return myDrivingAssistance
     */
    public boolean isMyDrivingAssistance() {
        return myDrivingAssistance;
    }

    /**
     * Calculator for the amount it will cost to rent a car.
     */
    @Override
    public BigDecimal calculateRentalAmount() {
        BigDecimal rentalAmount = CAR_FARE;
        final BigDecimal luxuryAdd = new BigDecimal("10.00");
        final BigDecimal navigationAdd = new BigDecimal("1.00");
        final BigDecimal assistanceAdd = new BigDecimal("2.00");
        
        if (myLuxury) {
            rentalAmount = rentalAmount.add(luxuryAdd);
        }
        
        if (myNavigation) {
            rentalAmount = rentalAmount.add(navigationAdd);
        }
        
        if (myDrivingAssistance) {
            rentalAmount = rentalAmount.add(assistanceAdd);
        }
        
        final NumberFormat formatToCurrecy = NumberFormat.getCurrencyInstance(Locale.US);
        formatToCurrecy.format(rentalAmount);
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
        
        sb.append("Car ");
        sb.append("(");
        sb.append("ID:" + this.getMyVehicleID());
        sb.append(commaSpace);
        sb.append("Name:" + this.getMyName());
        sb.append(commaSpace);
        sb.append("VIN:" + this.getMyVIN());
        sb.append(commaSpace);
        sb.append("CanRent?:" + this.isMyCanRent());
        sb.append(commaSpace);
        sb.append("IsLuxury?:" + this.isMyLuxury());
        sb.append(commaSpace);
        sb.append("HasNavigation?:" + this.isMyNavigation());
        sb.append(commaSpace);
        sb.append("HasAssistance?:" + this.isMyDrivingAssistance());
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
            final Car other = (Car) theOtherObject;
            result = Integer.compare(this.getMyVehicleID(), other.getMyVehicleID()) == 0
                            && this.getMyName().compareTo(other.getMyName()) == 0
                            && this.getMyVIN().compareTo(other.getMyVIN()) == 0
                            && Boolean.compare(this.isMyCanRent(), other.isMyCanRent()) == 0
                            && Boolean.compare(this.isMyLuxury(), other.isMyLuxury()) == 0
                            && Boolean.compare(this.isMyNavigation(), other.isMyNavigation()) == 0
                            && Boolean.compare(this.isMyDrivingAssistance(), other.isMyDrivingAssistance()) == 0;
        }
        return result;
    }
    
    /**
     * hashCode associated with toString.
     * @return hashCode (integer)
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getMyVehicleID(), this.getMyVIN(), this.getMyName(), this.isMyCanRent(),
                            this.isMyLuxury(), this.isMyNavigation(), this.isMyDrivingAssistance());
    }
}
