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
public class UserTest {
    
    private User user;
    
    public UserTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        user = new User(); 
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getFelhasznalo method, of class User.
     */
    @Test
    public void testGetFelhasznalo() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();
        final Field field = felh.getClass().getDeclaredField("felhasznalo");
        field.setAccessible(true);
        
        UserAuthentication usat = new UserAuthentication();
        
        field.set(felh, usat);

        //when
        final UserAuthentication result = felh.getFelhasznalo();

        //then
        assertEquals(result, usat);
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();
        final Field field = felh.getClass().getDeclaredField("id");
        field.setAccessible(true);
        
        field.set(felh, 31);

        //when
        final int result = felh.getId();

        //then
        assertEquals(result, 31);
    }

    /**
     * Test of setId method, of class User.
     */
    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setId(24);

        //then
        final Field field = felh.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(felh), 24);
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();
        final Field field = felh.getClass().getDeclaredField("name");
        field.setAccessible(true);
        
        field.set(felh, "Jozsi");

        //when
        final String result = felh.getName();

        //then
        assertEquals(result, "Jozsi");
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setName("Zoli");

        //then
        final Field field = felh.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals(field.get(felh), "Zoli");
    }

    /**
     * Test of getKor method, of class User.
     */
    @Test
    public void testGetKor() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();
        final Field field = felh.getClass().getDeclaredField("kor");
        field.setAccessible(true);
        
        field.set(felh, 34);

        //when
        final int result = felh.getKor();

        //then
        assertEquals(result, 34);
    }

    /**
     * Test of setKor method, of class User.
     */
    @Test
    public void testSetKor() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setKor(24);

        //then
        final Field field = felh.getClass().getDeclaredField("kor");
        field.setAccessible(true);
        assertEquals(field.get(felh), 24);
    }

    /**
     * Test of getNem method, of class User.
     */
    @Test
    public void testGetNem() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();
        final Field field = felh.getClass().getDeclaredField("nem");
        field.setAccessible(true);
        
        field.set(felh, 1);

        //when
        final int result = felh.getNem();

        //then
        assertEquals(result, 1);
    }

    /**
     * Test of setNem method, of class User.
     */
    @Test
    public void testSetNem() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setNem(24);

        //then
        final Field field = felh.getClass().getDeclaredField("nem");
        field.setAccessible(true);
        assertEquals(field.get(felh), 24);
    }

    /**
     * Test of getMagassag method, of class User.
     */
    @Test
    public void testGetMagassag() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();
        final Field field = felh.getClass().getDeclaredField("magassag");
        field.setAccessible(true);
        
        field.set(felh, 178);

        //when
        final double result = felh.getMagassag();

        //then
        assertEquals(result, 178);
    }

    /**
     * Test of setMagassag method, of class User.
     */
    @Test
    public void testSetMagassag() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setMagassag(155);

        //then
        final Field field = felh.getClass().getDeclaredField("magassag");
        field.setAccessible(true);
        assertEquals(field.get(felh), 155);
    }

    /**
     * Test of getSuly method, of class User.
     */
    @Test
    public void testGetSuly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setSuly(82.3);

        //then
        final Field field = felh.getClass().getDeclaredField("suly");
        field.setAccessible(true);
        assertEquals(field.get(felh), 82.3);
    }

    /**
     * Test of setSuly method, of class User.
     */
    @Test
    public void testSetSuly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setMagassag(155);

        //then
        final Field field = felh.getClass().getDeclaredField("magassag");
        field.setAccessible(true);
        assertEquals(field.get(felh), 155);
    }

    /**
     * Test of getAktivitas method, of class User.
     */
    @Test
    public void testGetAktivitas() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setAktivitas(25.2);

        //then
        final Field field = felh.getClass().getDeclaredField("aktivitas");
        field.setAccessible(true);
        assertEquals(field.get(felh), 25.2);
    }

    /**
     * Test of setAktivitas method, of class User.
     */
    @Test
    public void testSetAktivitas() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        //when
        felh.setAktivitas(23.9654);

        //then
        final Field field = felh.getClass().getDeclaredField("aktivitas");
        field.setAccessible(true);
        assertEquals(field.get(felh), 23.9654);
    }

    /**
     * Test of setFelhasznalo method, of class User.
     */
    @Test
    public void testSetFelhasznalo() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User felh = new User();

        UserAuthentication usat = new UserAuthentication();
        //when
        felh.setFelhasznalo(usat);

        //then
        final Field field = felh.getClass().getDeclaredField("felhasznalo");
        field.setAccessible(true);
        assertEquals(field.get(felh), usat);
    }
    
}
