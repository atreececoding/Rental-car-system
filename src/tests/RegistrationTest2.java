/**
 * Test registration file.
 * 
 * Tcss305 - Rentz.
 */
package tests;

import static org.junit.Assert.*;

import java.util.Map;
import model.Registration;
import model.User;
import org.junit.Before;
import org.junit.Test;
import utility.FileLoader;

/**
 * 
 * This file tests each and every method under the Registration file for the Rentz project
 * except for printSignIn().
 * 
 * @author atreece
 * @version 1.0
 *
 */

public class RegistrationTest2 {
    
    /**
     * The same file as in the registration class.
     */
    public static final String USERFILE_NAME = "./resources/registeredusers.txt";
    
    /**
     * Frequently used username in testing.
     */
    public static final String USERNAME = "tcss305";
    
    /**
     * Frequently used password in testing.
     */
    public static final String PASSWORD = "305";
    
    /**
     * Frequently used prompt in testing.
     */
    public static final String NULL_PROMPT = "The object is null ";
    
    /**
     * User object.
     */
    private User myUser;
    
    /**
     * registration object.
     */
    private Registration myRegistration1;
    
    /**
     * registration object2.
     */
    private Registration myRegistration2;
    
    /**
     * Object map.
     */
    private Map<String, User> myUserList;
    
    /**
     * Test method for {@link tests.Registration#Registration}.
     * Sets up file, registration objects, and a user object.
     */
    @Before
    public void setUp() {
        myUserList = FileLoader.readItemsFromFile(USERFILE_NAME);
        myRegistration1 = new Registration();
        myRegistration2 = new Registration();
        myUser = new User("newUser", "newPass", true);
    }
    
    /**
     * Test method for {@link tests.Registration#Registration}.
     * Tests for null objects.
     */
    @Test
    public void testRegistration() {
        assertNotNull(NULL_PROMPT, myRegistration1);
        assertNotNull(NULL_PROMPT, myRegistration2);
    }

    /**
     * Test method for {@link tests.Registration#getMyUserList()}. 
     * Tests for getMyUserList().
     */
    @Test
    public void testGetMyUserList() {
        assertNotNull(NULL_PROMPT, myRegistration1);
        assertNotNull(NULL_PROMPT, myRegistration2);
        assertEquals("getMyUserList() failed!", myUserList, myRegistration1.getMyUserList());
    }

    /**
     * Test method for {@link tests.RegistrationTest#login(String, String)}.
     * Tests my login method.
     */
    @Test
    public void testLogin() {
        final String failPrompt = "Login() failed!";
        assertEquals(failPrompt, myUserList.containsKey(USERNAME), 
                     myRegistration1.getMyUserList().containsKey(USERNAME));
        assertEquals(failPrompt, myUserList.get(USERNAME).getMyPassword(), 
                     myRegistration1.getMyUserList().get(USERNAME).getMyPassword());
        assertTrue(failPrompt, myRegistration1.login(USERNAME, PASSWORD));
        assertFalse(failPrompt, myRegistration1.login("BadUser", "BadPass"));
    }
    
    /**
     * Test method for {@link tests.Registration#Registration}.
     * Tests for NullPointerException with a username in login.
     */
    @Test(expected = NullPointerException.class)
    public void testLoginUserNameNull() {
        myRegistration1.login(null, PASSWORD);
    }
    
    /**
     * Test method for {@link tests.Registration#Registration}.
     * Tests for NullPointerException with a password in login.
     */
    @Test(expected = NullPointerException.class)
    public void testLoginPasswordNull() {
        myRegistration1.login(USERNAME, null);
    }
    
    /**
     * Test method for {@link tests.Registration#Registration}.
     * Tests for IllegalArgumentException for username in login.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginUserNameIllegalArgument() {
        myRegistration1.login("", PASSWORD);
    }
    
    /**
     * Test method for {@link tests.Registration#Registration}.
     * Tests for IllegalArgumentException for password in login.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginPasswordIllegalArgument() {
        myRegistration1.login(USERNAME, "");
    }

    /**
     * Test method for {@link tests.Registration#register(User)}.
     * Tests my register method.
     */
    @Test
    public void testRegister() {
        final String failPrompt = "Registration() failed!";
        myRegistration1.clear();
        assertTrue(failPrompt, myRegistration1.register(myUser));
        assertTrue(failPrompt, myRegistration1.getMyUserList().containsValue(myUser));
        
    }
    
    /**
     * Test method for {@link tests.Registration#Registration}.
     * Tests NullPointerException if the user object is null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterUserNull() {
        myRegistration1.register(null);
    }

    /**
     * Test method for {@link tests.Registration#clear()}.
     * Tests for clear method.
     */
    @Test
    public void testClear() {
        final String failPrompt = "clear() failed!";
        myUserList.clear();
        myRegistration1.clear();
        assertEquals(failPrompt, myUserList, myRegistration1.getMyUserList());
    }

    /**
     * Test method for {@link tests.Registration#toString()}.
     * Tests for toString method.
     */
    @Test
    public void testToString() {
        assertEquals("toString() failed! ", "Registered User" + myUserList.toString(),
                     myRegistration1.toString());
    }

}

