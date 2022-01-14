
/**
 * This is the User class for the vehicle rental system.
 * 
 * TCSS 305 - Rentz
 */

package model;

import java.util.Objects;


/**
 * Represents a single user for registration or sign-in. User is an immutable object.
 * 
 * Constructors and methods of this class throw NullPointerException if required parameters are
 * null.
 * 
 * @author atreece
 * @version Fall 2019
 */
public final class User {
    

    /**
     * Users name.
     */
    private final String myName;
    
    /**
     * Users password.
     */
    private final String myPassword;
    

    /**
     * Users VIP status.
     */
    private final boolean myVIPStatus;

    /**
     * parameterized constructor concerning theName and the Password.
     * 
     * @param theName
     * @param thePassword
     * 
     */
    public User(final String theName, final String thePassword) {
        myName = theName;
        myPassword = thePassword;
        this.myVIPStatus = false;
        
        Objects.requireNonNull(theName);
        Objects.requireNonNull(thePassword);
        
        if (theName.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
    }
    
    /**
     * Parameterized constructor concerning theName, thePassword, and theVIPStatus.
     * 
     * @param theName
     * @param thePassword
     * @param theVIPStatus
     */
    public User(final String theName, final String thePassword, final boolean theVIPStatus) {
        myName = theName;
        myPassword = thePassword;
        myVIPStatus = theVIPStatus;
        
        Objects.requireNonNull(theName);
        Objects.requireNonNull(thePassword);
        Objects.requireNonNull(theVIPStatus);
        
        if (theName.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Getter for myName.
     * @return myName
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Getter for myPassword.
     * @return myPassword
     */
    public String getMyPassword() {
        return myPassword;
    }

    /**
     * Getter for myVIPStatus.
     * @return myVIPStatus
     */
    public boolean getMyVIPStatus() {
        return myVIPStatus;
    }
    
    /**
     * Overrides the toString method.
     * returns a string representation of the User class.
     */
    @Override
    public String toString() {
        
        final String commaSpace = ",";
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()); // the class name without the package name
        sb.append(" (");
        sb.append(myName);
        sb.append(commaSpace);
        sb.append(myPassword);
        sb.append(commaSpace);
        sb.append(myVIPStatus);
        sb.append(")");

        return sb.toString();
        
    }
    
    /**
     * Override method of .equals.
     * returns a boolean value.
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
            final User other = (User) theOtherObject;
            result = myName.compareTo(other.myName) == 0
                     && myPassword.compareTo(other.myPassword) == 0
                     && Boolean.compare(this.myVIPStatus, other.myVIPStatus) == 0;
        }
        return result;
    }
    
    /**
     * Override of the hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myPassword, myVIPStatus);
    }
        
}
    

