/**
 * This is the vehicle class for the vehicle rental system.
 * 
 * TCSS305 - Rentz
 */
package model.vehicles;

import java.math.BigDecimal;

/**
 * This class is the parent class for the types of vehicles available for rent.
 * 
 * @author atreece
 * @version 1.0
 */

public abstract class AbstractVehicle {
    
    /**
     * Big decimal value of the base fare for vehicle rental.
     */
    public static final BigDecimal BASE_FARE = new BigDecimal("10.00");

    /**
     * Unique ID of each vehicle.
     */
    private final int myVehicleID;
    
    /**
     * the Unique Identification Number assigned to each vehicle.
     */
    private final String myVIN;
    
    /**
     * the name of each vehicle.
     */
    private final String myName;
    
    /**
     * Boolean to determine if each vehicle is available for rent.
     */
    private boolean myCanRent;
    
    /**
     * Parameterized constructor for AbstractVehicle.
     * @param theVehicleID
     * @param theVIN
     * @param theName
     * @param theCanRent
     */
    public AbstractVehicle(final int theVehicleID, final String theName, final String theVIN, final boolean theCanRent) {
        this.myVehicleID = theVehicleID;
        this.myVIN = theVIN;
        this.myName = theName;
        this.myCanRent = theCanRent;
    }
    
    /**
     * getter for myVehicleID.
     * @return myVehicleID
     */
    public int getMyVehicleID() {
        
        return myVehicleID;
    }

    /**
     * getter for myVIN.
     * @return myVIN
     */
    public String getMyVIN() {
        
        return myVIN;
    }

    /**
     * getter for myName.
     * @return myName
     */
    public String getMyName() {
        
        return myName;
    }
    
    /**
     * getter for myCanRent.
     * @return myCanRent
     */
    public boolean isMyCanRent() {
        return myCanRent;
    }
    
    /**
     * Sets myCanRent for use in RentalManager.
     * @param theCanRent
     */
    public void setMyCanRent(final boolean theCanRent) {
        myCanRent = theCanRent;
    }
    
    
    /**
     * Abstract BigDecimal that returns a rental amount. This is shared by all child classes.
     * @return BigDecimal
     */
    public abstract BigDecimal calculateRentalAmount();
    
}
