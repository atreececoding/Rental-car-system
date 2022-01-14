/**
 * Testing class for the User class.
 * 
 * Tcss 305 - Rentz
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Objects;
import model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This file tests each and every method for the User class in the Rentz project.
 * 
 * @author atreece
 * @version 1.0
 */

public class UserTest {

    
    /**
     * First User (String, String).
     */
    private User myUser1;
    
    /**
     * Second User (String, String, Boolean).
     */
    private User myUser2;
    
    /**
     * Test method for {@link tests.User#User()}.
     * Set up initializing two user objects.
     */
    @Before
    public void setUp() {
        myUser1 = new User("tcss305", "305");
        myUser2 = new User("tcss305", "305", true);
    }
    
    /**
     * Test method for {@link tests.User#User(String, String)}.
     * Test for first parameterized constructor.
     */
    @Test
    public void testUserStringString() {
        final User user3 = new User("tcss305", "305");
        assertNotNull("The object is null", myUser1);
        assertEquals("The default value is not correct", user3, myUser1);
    }
    
    /**
     * Test method for {@link tests.User#User}.
     * Test for null values in username.
     */
    @Test(expected = NullPointerException.class)
    public void testUserNullmyUserName() {
        myUser1 = new User(null, "305");
        
    }
    
    /**
     * Test method for {@link tests.User#User}.
     * Test for null values in password.
     */
    @Test(expected = NullPointerException.class)
    public void testUserNullmyPassword() {
        myUser1 = new User("tcss305", null);
        
    }
    
    /**
     * Test method for {@link tests.User#User}.
     * Tests for illegalArgumentException of username in parameterized constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalMyName() {
        myUser1 = new User("", "305");
    }
    
    /**
     * Test method for {@link tests.User#User}.
     * Tests for IllegalArgumentException of password in parameterized constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalMyPassword() {
        myUser1 = new User("tcss305", "");
    }
    
    /**
     * Test method for {@link tests.User#User(String, String, boolean)}.
     * Tests for my second parameterized constructor.
     */
    @Test
    public void testUserStringStringBoolean() {
        final User user3 = new User("tcss305", "305", true);
        assertNotNull("The object is null", myUser2);
        assertEquals("The default value is not correct", user3, myUser2);
    }
    
    /**
     * Test method for {@link tests.User#User}.
     * Tests for NullPointerException of second parameterized constructor.
     */
    @Test(expected = NullPointerException.class)
    public void testUser2NullmyUsername() {
        myUser2 = new User(null, "305", true);
        
    }
    
    /**
     * Test method for {@link tests.User#User}.
     * Tests for NullPointerException of second parameterized constructor.
     */
    @Test(expected = NullPointerException.class)
    public void testUser2NullmyPassword() {
        myUser2 = new User("tcss305", null, true);
        
    }
    
    /**
     * Test method for {@link tests.User#User}.
     * Tests for IllegalArgumentException of username in second parameterized constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUser2IllegalMyName() {
        myUser1 = new User("", "305", true);
    }
    
    /**
     * Test method for {@link tests.User#User}.
     * Tests for IllegalArgumentException of password in second parameterized constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUser2IllegalMyPassword() {
        myUser1 = new User("tcss305", "", true);
    }
    
    /**
     * Test method for {@link tests.User#getMyName()}.
     * Tests for getMyName.
     */
    @Test
    public void testGetMyName() {
        assertNotNull("The object is null", myUser1);
        assertNotNull("The object is null", myUser2);
        assertEquals("GetMyName() failed!", "tcss305", myUser1.getMyName());
        assertEquals("GetMyName() failed!", "tcss305", myUser2.getMyName());
    }
    
    /**
     * Test method for {@link tests.User#getMyPassword()}.
     * Tests for getMyPassword.
     */
    @Test
    public void testGetMyPassword() {
        assertNotNull("The object is null", myUser1);
        assertNotNull("The object is null", myUser2);
        assertEquals("GetMyPassword() failed!", "305", myUser1.getMyPassword());
        assertEquals("GetMyPassword() failed!", "305", myUser2.getMyPassword());
    }
    
    /**
     * Test method for {@link tests.User#getMyVIPStatus()}.
     * Tests for getMyVIPStatus.
     */
    @Test
    public void testGetMyVIPStatus() {
        assertNotNull("The object is null", myUser1);
        assertNotNull("The object is null", myUser2);
        assertEquals("GetMyVIPStatus() failed!", true, myUser2.getMyVIPStatus());
    }
    
    /**
     * Test method for {@link tests.User#toString()}.
     * Tests for toString method.
     */
    @Test
    public void testToString() {
        assertEquals("toString() failed!", "User (tcss305,305,true)", myUser2.toString());
        assertEquals("toString() failed!", "User (tcss305,305,false)", myUser1.toString());
    }
    
    /**
     * Test method for {@link tests.User#equals(java.lang.Object)}.
     * Test for equals method.
     */
    @Test
    public void testEqualsObject() {
        final User user3 = new User("tcss305", "305", true);
        assertEquals("equals() failed!", user3, myUser2);
        assertEquals("equals() failed!", myUser2, myUser2);
    }
    
    /**
     * Test method for {@link tests.User#equals(java.lang.Object)}.
     * Test for equals method.
     */
    @Test
    public void testEqualsNegation() {
        assertNotEquals("equals() failed!", myUser2, myUser1);
        assertNotEquals("equals() failed!", myUser2, null);
        assertNotEquals("equals() failed!", myUser2, new ArrayList<User>());
    }
    
    /**
     * Test method for {@link tests.User#hashCode()}.
     * Test for hashCode.
     */
    @Test
    public void testHashCode() {
        assertEquals("HashCode() failed!", Objects.hash(myUser2.getMyName(), 
                     myUser2.getMyPassword(), myUser2.getMyVIPStatus()), myUser2.hashCode());
        
        final User user3 = new User("tcss305", "305", true);
        assertEquals("HashCode() failed!", myUser2.hashCode(), user3.hashCode());
    }

}
