/**
 * This is the Bill class for the vehicle rental system.
 * 
 * TCSS305 - Rentz
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import model.vehicles.AbstractVehicle;

/**
 * This is the class that manages the bills in the rental vehicle system: Rentz.
 * @author atreece 
 * @version 1.0
 */

public class Bill {
  
    /**
     * The starting amount for any given bill.
     */
    public static final BigDecimal STARTING_AMOUNT = new BigDecimal("0.00");
    
    /**
     * The number currency formatter.
     */
    public static final NumberFormat FORMAT_TO_CURRENCY = NumberFormat.getCurrencyInstance(Locale.US);
    
    /**
     * the Id of the bill being given.
     */
    private final int myBillID;
    
    /**
     * the User that is being presented the bill.
     */
    private final User myPrimaryUser;
    
    /**
     * the type of vehicle from the AbstractVehicle class.
     */
    private final AbstractVehicle myVehicle;
    
    /**
     * The number of days in which the vehicle is rented.
     */
    private final int myNumDays;
    
    /**
     * The amount the bill will come to be in BigDecimal.
     */
    private BigDecimal myBillAmount;
    
    /**
     * Parameterized constructor that initializes private fields.
     * Increments the Bill ID given a new bill object.
     * @param thePrimaryUser
     * @param theVehicle
     * @param theNumDays
     */
    public Bill(final User thePrimaryUser, final AbstractVehicle theVehicle, 
                final int theNumDays) {
        int billID = 1;
        myBillID = billID;
        billID++;
        myPrimaryUser = thePrimaryUser;
        myVehicle = theVehicle;
        myNumDays = theNumDays;
    }
    
    /**
     * Getter for myBillID.
     * @return myBillID
     */
    public int getMyBillID() {
        return myBillID;
    }

    /**
     * Getter for myPrimaryUser.
     * @return myPrimaryUser
     */
    public User getMyPrimaryUser() {
        return myPrimaryUser;
    }

    /**
     * Getter for myVehicle.
     * @return myVehicle
     */
    public AbstractVehicle getMyVehicle() {
        return myVehicle;
    }

    /**
     * Getter for myNumDays.
     * @return myNumDays
     */
    public int getMyNumDays() {
        return myNumDays;
    }

    /**
     * Getter for myBillAmount.
     * @return myBillAmount;
     */
    public BigDecimal getMyBillAmount() {
        return myBillAmount;
    }
    
    /**
     * Computes and prints out the billing information and pricing.
     */
    public void computeAndPrintAmount() {
        
        final BigDecimal insuranceAndDiscountAmount = new BigDecimal("0.01");
         
        BigDecimal rentalAmount = STARTING_AMOUNT;
        final BigDecimal dailyCost = myVehicle.calculateRentalAmount();
        final BigDecimal rentTotal = dailyCost.multiply(new BigDecimal(myNumDays));
        rentalAmount = rentalAmount.add(rentTotal);
        
        System.out.println("RentalPerDay: ");
        
        System.out.println("Cost per Day: " + FORMAT_TO_CURRENCY.format(dailyCost));
        
        System.out.println("No.of Rental Days: " + myNumDays);
        
        System.out.println("Total Amount: " + rentalAmount);
        
        final BigDecimal insuranceToll = rentalAmount.multiply(insuranceAndDiscountAmount);
        
        System.out.println("Insurance: " + FORMAT_TO_CURRENCY.format(insuranceToll));
        
        final BigDecimal vipDiscount = rentalAmount.multiply(insuranceAndDiscountAmount);
        final String vipDiscountPrompt = "VIPDiscount: ";
        if (myPrimaryUser.getMyVIPStatus()) {
            System.out.println(vipDiscountPrompt + "-" + FORMAT_TO_CURRENCY.format(vipDiscount));
            rentalAmount = rentalAmount.subtract(vipDiscount);
        } else {
            System.out.println(vipDiscountPrompt + "-$0.00");
        }
        
        rentalAmount = rentalAmount.add(insuranceToll);
        
        final BigDecimal tax = rentalAmount.multiply(new BigDecimal("0.10"));
        System.out.println("Tax: " + FORMAT_TO_CURRENCY.format(tax));
       
        rentalAmount = rentalAmount.add(tax);
        
        myBillAmount = rentalAmount;
        
        System.out.println("Total Rent: " + FORMAT_TO_CURRENCY.format(myBillAmount));
    }

}
