/**
 * This is the RentalManager class for Rentz.
 * 
 * TCSS - 305
 */
package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import model.vehicles.AbstractVehicle;
import model.vehicles.BiCycle;
import model.vehicles.Car;
import model.vehicles.MotorBike;

/**
 * This class manages the rental options for the rental program.
 * 
 * @author atreece
 * @version 1.0
 */
public class RentalManager {

    /**
     * Scanner from Registration.
     */
    public static final Scanner SCANNER = Registration.SC;
    
    /**
     * Break lines used throughout the class.
     */
    public static final String BREAK_LINES = "*******************";
    
    /**
     * Separates lines of text in print statements.
     */
    public static final String LINE_SEPARATOR = " ";
    
    /**
     * Common line asking the user to re-enter the vehicle ID on drop off.
     */
    public static final String RE_ENTER_PROMPT = "Enter Drop-Off Vehicle ID:";
    
    /**
     * Map that stores Billing object.
     */
    private final Map<Integer, Bill> myBills;
    
    /**
     * Id number for vehicle.
     */
    private int myID;
    
    /**
     * The ID for the bill.
     */
    private final int myBillID;
    
    /**
     * Map that stores the vehicles.
     */
    private final Map<Integer, AbstractVehicle> myVehicleList;
    
    /**
     * A registration object.
     */
    private final Registration myRegistration;
 
    
    /**
     * Parameterized constructor that generates the inventory and initializes the registration object.
     * @param theRegistration
     */
    public RentalManager(final Registration theRegistration) {
        Objects.requireNonNull(theRegistration);
        
        myID = 1;
        myVehicleList = generateInventory();
        myRegistration = theRegistration;
        myBills = new HashMap<Integer, Bill>();
        myBillID = 1;
        
    }
    
    /**
     * Generates the inventory of all of the vehicles.
     * @return newMap
     */
    public final Map<Integer, AbstractVehicle> generateInventory() {
        final String cruiser = "Cruiser";
        final String mountain = "Mountain";
        
        final Map<Integer, AbstractVehicle> newVehicleList = new HashMap<Integer, AbstractVehicle>();
        generateInventoryHelper(new Car(myID, "Fiat", "V100", true, false, false, false), newVehicleList);
        generateInventoryHelper(new Car(myID, "Outback", "V101", true, true, true, false), newVehicleList);
        generateInventoryHelper(new Car(myID, "BMW", "V102", true,  true, true, true) , newVehicleList);
        generateInventoryHelper(new MotorBike(myID, "Bike1", "B100", true, false), newVehicleList);
        generateInventoryHelper(new MotorBike(myID, "Bike2", "B101", true, true), newVehicleList);
        generateInventoryHelper(new BiCycle(myID, "Roadies", "C100", true, "Road"), newVehicleList);
        generateInventoryHelper(new BiCycle(myID, cruiser, "C101", true, cruiser), newVehicleList);
        generateInventoryHelper(new BiCycle(myID, mountain, "C102", true, mountain), newVehicleList);
       
        return newVehicleList;
    }
    
    /**
     * Helper method to auto generate the inventory.
     * @param car1
     * @param theVehicleList
     */
    private void generateInventoryHelper(final AbstractVehicle car1, final Map<Integer, AbstractVehicle> theVehicleList) {
        theVehicleList.put(myID, car1);
        myID++;
    }
    
    
    /**
     * Getter for myVehicleList.
     * @return myVehicleList
     */
    public Map<Integer, AbstractVehicle> getMyVehicleList() {
        return myVehicleList;
    }

    /**
     * Prints out the options of the vehicles from the inventory.
     */
    public void printOptions() {
        boolean quitOption = true;
        
        System.out.println(LINE_SEPARATOR);
        while (quitOption) {
            final Set<Integer> keys = myVehicleList.keySet();
            final Iterator<Integer> itr = keys.iterator();
            System.out.println(LINE_SEPARATOR);
            System.out.println("Enter 1 or 2 or 3 (1. Rent 2. Drop-Off 3. Exit)");
            final String promptAnswer = SCANNER.next();
            if ("1".equals(promptAnswer)) {
                System.out.println("You entered option 1");
                System.out.println(LINE_SEPARATOR);
                System.out.println("*************List of available vehichles*************");

                while (itr.hasNext()) {
                    final int storedItr = itr.next();
                    if (myVehicleList.get(storedItr).isMyCanRent()) {
                        System.out.println(myVehicleList.get(storedItr));
                    }
                }
                
                final String userPrompt = "Enter User Name:";
                final String numDaysPrompt = "Enter NumDays to Rent:";
                printOptionsHelper();
                int idInput = SCANNER.nextInt();
                System.out.print(userPrompt);
                String userName = SCANNER.next();
                while (!myRegistration.getMyUserList().containsKey(userName)) {
                    System.out.print("User does not exist, enter different user name:");
                    userName = SCANNER.next();
                }
                System.out.print(numDaysPrompt);
                int numDays = SCANNER.nextInt();

                while (!rent(idInput, userName, numDays, myBillID)) {
                    System.out.println("Vehicle not Rentable");
                    printOptionsHelper();
                    idInput = SCANNER.nextInt();
                    System.out.print(userPrompt);
                    userName = SCANNER.next();
                    System.out.print(numDaysPrompt);
                    numDays = SCANNER.nextInt();
                }
            }
            if ("2".equals(promptAnswer)) {
                printOptions2Helper();
                int vehicleID = SCANNER.nextInt();
                while (!drop(vehicleID)) {
                    System.out.print(RE_ENTER_PROMPT);
                    vehicleID = SCANNER.nextInt();
                }
                System.out.println("Drop-Off successful");
            }
            if ("3".equals(promptAnswer)) {
                System.out.println("You entered option 3");
                System.out.println(LINE_SEPARATOR);
                System.out.println(BREAK_LINES);
                break;
            }
            
            System.out.print("Do you want to continue?");
            quitOption = SCANNER.nextBoolean();
        }
    }
    
    /**
     * Helper method that breaks up some of the print lines in printOptions.
     */
    private void printOptionsHelper() {
        final String rentalDetailsPrompt = "Enter Rental Details";
        final String vehicleIDPrompt = "Enter Vehicle ID:";
        System.out.println(BREAK_LINES);
        System.out.println(rentalDetailsPrompt);
        System.out.println(BREAK_LINES);
        System.out.print(vehicleIDPrompt);
    }
    
    /**
     * Another helper method that breaks up some of the print lines in printOptions.
     */
    private void printOptions2Helper() {
        System.out.println("You entered option 2");
        System.out.println("*****************************");
        System.out.println(BREAK_LINES);
        System.out.println("Enter Drop-Off Details");
        System.out.println(BREAK_LINES);
        System.out.print(RE_ENTER_PROMPT);
    }
    
    /**
     * Getter for the registration object.
     * @return myRegistration
     */
    public Registration getMyRegistration() {
        return myRegistration;
    }

    /**
     * getter for myBills map.
     * @return myBills
     */
    public Map<Integer, Bill> getMyBills() {
        return myBills;
    }   
    
    /**
     * getter for myBillID.
     * @return myBillID
     */
    public int getMyBillID() {
        return myBillID;
    }
    
    /**
     * Asks if the vehicle is rentable (boolean type).
     * @param theVehicleID
     * @return true
     */
    public boolean isRentable(final int theVehicleID) {
        
        return myVehicleList.containsKey(theVehicleID) && myVehicleList.get(theVehicleID).isMyCanRent();
    }
    
    /**
     * Manages the rental operations.
     * Throws implicit null pointer exceptions on parameters.
     * Throws IllegalArgumentExceptions if theVehicleID, theNumDays, or theBillID are less than 0.
     * @param theVehicleID
     * @param theUserName
     * @param theNumDays
     * @param theBillID
     * @return false
     */
    public boolean rent(final int theVehicleID, final String theUserName,
                        final int theNumDays, final int theBillID) {
        Objects.requireNonNull(theVehicleID);
        Objects.requireNonNull(theUserName);
        Objects.requireNonNull(theNumDays);
        Objects.requireNonNull(theBillID);
        
        
        if (theVehicleID <= 0 || theNumDays <= 0 || theBillID <= 0) {
            throw new IllegalArgumentException();
        }
        boolean result = false;
     
        if (isRentable(theVehicleID) && myRegistration.getMyUserList().containsKey(theUserName)) {
            final Bill bill = new Bill(myRegistration.getMyUserList().get(theUserName), myVehicleList.get(theVehicleID), theNumDays);
           
            final AbstractVehicle idMap = myVehicleList.get(theVehicleID);
            System.out.println(LINE_SEPARATOR);
            System.out.println(BREAK_LINES);
            System.out.println("Rental Bill Summary");
            System.out.println(BREAK_LINES);
            System.out.println("User Name: " + theUserName);
            System.out.println("----Vehicle Information----");
            System.out.println("VehicleName " + idMap.getMyName());
            System.out.println("VehicleID " + idMap.getMyVehicleID());
            System.out.println("VehicleType " + idMap.getMyVIN());
            System.out.println("VIN " + idMap.getMyVIN());
            System.out.println("----Cost Information----");
            
            bill.computeAndPrintAmount();
            myBills.put(theBillID, bill);
            myVehicleList.get(theVehicleID).setMyCanRent(false);
            result = true;
        }
        
        return result;
    }
    
    /**
     * Manages the drop off functions.
     * @param theVehicleID
     * @return false
     */
    public boolean drop(final int theVehicleID) {
      
        boolean result = false;
        if (!isRentable(theVehicleID)) {
            if (myVehicleList.containsKey(theVehicleID)) {
                myVehicleList.get(theVehicleID).setMyCanRent(true);
                result = true;
            } else {
                System.out.println("Vehicle does not exist");
            }
            
        } else {
            System.out.println("Vehicle is not rented already");
        }
        
        return result;
    }
    
    /**
     * Clears the lists/maps of any entries.
     */
    public void clearLists() {
        myVehicleList.clear();
        myBills.clear();
    }
    
}
