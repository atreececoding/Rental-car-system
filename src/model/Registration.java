
/*
 * This file is the registration class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */

package model;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import utility.FileLoader;

/**
 * Represents User Sign-in Object.
 * 
 * Methods of this class throw NullPointerException if required parameters are null.
 * 
 * @author Athirai, atreece
 * @version Fall 2019
 */

public class Registration {

    /**
     * User Storage File.
     */
    public static final String USERFILE_NAME = "./resources/registeredusers.txt";
    
    /**
     * Scanner used throughout the whole document.
     */
    public static final Scanner SC = new Scanner(System.in);
    
    /**
     * Space token.
     */
    public static final String SPACE_TOKEN = " ";

    /**
     * The registered user list for signin.
     */
    private final Map<String, User> myUserList;
    

    /**
     * Constructs a sigin/registration system.
     * 
     * 
     */
    public Registration() {
        myUserList = FileLoader.readItemsFromFile(USERFILE_NAME);

    }

    /**
     * getter for myUserList.
     * 
     * @return myUserList
     */
    public Map<String, User> getMyUserList() {
        return myUserList;
    }

    /**
     * display sign-in or registration options.
     * @return true only if registration successful.
     */
    public boolean printSignin() {

        // ------------Fill in--------------------//
        boolean result = false;

        System.out.print("Enter 1 or 2 (1. New Registration 2. Login): ");
        // final String input = sc.nextLine();

        final String line = SC.next();
        final String breakLines = "**********************";
        final String enterDetails = "EnterDetails";
        final String userName = "User Name: ";
        final String passWord = "Password: ";
        final String vipStatus = "isVIP(True/False): ";

        if ("1".equals(line)) {
            System.out.println("You entered option 1");
            System.out.println(breakLines);
            System.out.println(enterDetails);
            System.out.println(breakLines);
            System.out.print(userName);

            String userLine = SC.next();
            while (myUserList.containsKey(userLine)) {
                System.out.print("User already exists, enter different user name: ");
                userLine = SC.next();
            }
            System.out.print(passWord);
            final String passLine = SC.next();
            System.out.print(vipStatus);
            final Boolean vipLine = SC.nextBoolean();
            final User newUser = new User(userLine, passLine, vipLine);
            register(newUser);
            System.out.println("Registration Successful");

        } else if ("2".equals(line)) {
            System.out.println("You entered option 2");
            System.out.println(breakLines);
            System.out.println(enterDetails);
            System.out.println(breakLines);
            System.out.print(userName);

            String userLine = SC.next();
            System.out.print(passWord);

            String passLine = SC.next();
            while (!login(userLine, passLine)) {
                System.out.println("Wrong credentials");
                System.out.println(SPACE_TOKEN);
                System.out.print(userName);
                userLine = SC.next();
                System.out.print(passWord);
                passLine = SC.next();

            }
            System.out.print("Login successfull");
            
            result = true;
        }
       
        return result;
    }

    /**
     * Verify Sign-in procedure.
     * 
     * @param theUsername username for sign-in
     * @param thePassword password for signin
     * @return sign-in success
     */
    public boolean login(final String theUsername, final String thePassword) {

        // ------------Fill in--------------------//

        Objects.requireNonNull(theUsername);
        Objects.requireNonNull(thePassword);

        if (theUsername.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();

        }

        return myUserList.containsKey(theUsername)
               && myUserList.get(theUsername).getMyPassword().equals(thePassword);
    }

    /**
     * Adds a user to the registered user list.
     * 
     * @param theUser an order to add to this shopping cart
     * @return true/false returns if registration is successful
     */
    public boolean register(final User theUser) {
        // want to check if user is in map. Want to ask again if the user is in the map

        // ------------Fill in--------------------//
        Objects.requireNonNull(theUser);

        if (!myUserList.containsKey(theUser.getMyName())) {
            myUserList.put(theUser.getMyName(), theUser);
            FileLoader.writeUserToFile(USERFILE_NAME, theUser);
        }

        return myUserList.containsKey(theUser.getMyName());
    }

    /**
     * Empties the user list.
     */
    public void clear() {

        // ------------Fill in--------------------//
        myUserList.clear();
    }

    /**
     * String representation of the object.
     * 
     * @return String representation
     */
    public String toString() {

        // ------------Fill in--------------------//
        final String commaSpace = ",";
        final StringBuilder sb = new StringBuilder();

        final Set<String> keys = myUserList.keySet();
        final Iterator<String> itr = keys.iterator();

        sb.append("Registered User");
        sb.append("{");
        while (itr.hasNext()) {
            final String key = itr.next();
            sb.append(key + "=" + myUserList.get(key));
            if (itr.hasNext()) {
                sb.append(commaSpace + SPACE_TOKEN);
            }
        }
        sb.append("}");
        return sb.toString();
    }

}
