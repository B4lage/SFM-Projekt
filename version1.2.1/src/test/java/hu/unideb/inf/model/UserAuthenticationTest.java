/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Balage
 */
public class UserAuthenticationTest {
    
    private UserAuthentication usat;
    
    public UserAuthenticationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        usat = new UserAuthentication(); 
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId method, of class UserAuthentication.
     */
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final UserAuthentication felh = new UserAuthentication();
        final Field field = felh.getClass().getDeclaredField("id");
        field.setAccessible(true);
        
        field.set(felh, 39);

        //when
        final int result = felh.getId();

        //then
        assertEquals(result, 39);
    }

    /**
     * Test of setId method, of class UserAuthentication.
     */
    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final UserAuthentication felh = new UserAuthentication();

        //when
        felh.setId(74);

        //then
        final Field field = felh.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(felh), 74);
    }

    /**
     * Test of getName method, of class UserAuthentication.
     */
    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final UserAuthentication felh = new UserAuthentication();
        final Field field = felh.getClass().getDeclaredField("name");
        field.setAccessible(true);
        
        field.set(felh, "Geza");

        //when
        final String result = felh.getName();

        //then
        assertEquals(result, "Geza");
    }

    /**
     * Test of setName method, of class UserAuthentication.
     */
    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final UserAuthentication felh = new UserAuthentication();

        //when
        felh.setName("Roli");

        //then
        final Field field = felh.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals(field.get(felh), "Roli");
    }

    /**
     * Test of getPw method, of class UserAuthentication.
     */
    @Test
    public void testGetPw() throws NoSuchFieldException, IllegalAccessException {
        //given
        final UserAuthentication felh = new UserAuthentication();
        final Field field = felh.getClass().getDeclaredField("pw");
        field.setAccessible(true);
        
        field.set(felh, "mogotted");

        //when
        final String result = felh.getPw();

        //then
        assertEquals(result, "mogotted");
    }

    /**
     * Test of setPw method, of class UserAuthentication.
     */
    @Test
    public void testSetPw() throws NoSuchFieldException, IllegalAccessException {
        //given
        final UserAuthentication felh = new UserAuthentication();

        //when
        felh.setPw("elotted");

        //then
        final Field field = felh.getClass().getDeclaredField("pw");
        field.setAccessible(true);
        assertEquals(field.get(felh), "elotted");
    }
    
}
