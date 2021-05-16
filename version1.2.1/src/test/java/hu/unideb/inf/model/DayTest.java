/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.lang.reflect.Field;
import java.time.LocalDate;
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
public class DayTest {
    
    private Day day;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        day = new Day(); 
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of setUsr method, of class Day.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetUsr() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        var user = new UserAuthentication();
        
        //when
        nap.setUsr(user);

        //then
        final Field field = nap.getClass().getDeclaredField("usr");
        field.setAccessible(true);
        assertEquals(field.get(nap), user);
    }

    /**
     * Test of getUsr method, of class Day.
     */
    @Test
    public void testGetUsr() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("usr");
        field.setAccessible(true);
        UserAuthentication user = new UserAuthentication();
        field.set(nap, user);

        //when
        final UserAuthentication result = nap.getUsr();

        //then
        assertEquals(result, user);
    }

    /**
     * Test of setMeal method, of class Day.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetMeal() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        var meal = new Meal();
        
        //when
        nap.setMeal(meal);

        //then
        final Field field = nap.getClass().getDeclaredField("meal");
        field.setAccessible(true);
        assertEquals(field.get(nap), meal);
    }

    /**
     * Test of getMeal method, of class Day.
     */
    @Test
    public void testGetMeal() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("meal");
        field.setAccessible(true);
        var meal = new Meal();
        field.set(nap, meal);

        //when
        final var result = nap.getMeal();

        //then
        assertEquals(result, meal);
    }

    /**
     * Test of getId method, of class Day.
     */
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(nap, 25);

        //when
        final var result = nap.getId();

        //then
        assertEquals(result, 25);
    }
    
    /**
     * Test of setId method, of class Day.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        //when
        nap.setId(55);

        //then
        final Field field = nap.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(nap), 55);
    }

    /**
     * Test of getGramm method, of class Day.
     */
    @Test
    public void testGetGramm() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("gramm");
        field.setAccessible(true);
        field.set(nap, 2509);

        //when
        final var result = nap.getGramm();

        //then
        assertEquals(result, 2509);
    }

    /**
     * Test of setGramm method, of class Day.
     */
    @Test
    public void testSetGramm() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        //when
        nap.setGramm(5450);

        //then
        final Field field = nap.getClass().getDeclaredField("gramm");
        field.setAccessible(true);
        assertEquals(field.get(nap), 5450);
    }

    /**
     * Test of getDatum method, of class Day.
     */
    @Test
    public void testGetDatum() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("datum");
        field.setAccessible(true);
        var date = LocalDate.now();
        field.set(nap, date);

        //when
        final var result = nap.getDatum();

        //then
        assertEquals(result, date);
    }

    /**
     * Test of setDatum method, of class Day.
     */
    @Test
    public void testSetDatum() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        LocalDate date = LocalDate.now();
        
        //when
        nap.setDatum(date);

        //then
        final Field field = nap.getClass().getDeclaredField("datum");
        field.setAccessible(true);
        assertEquals(field.get(nap), date);
    }

    /**
     * Test of getKcal method, of class Day.
     */
    @Test
    public void testGetKcal() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("kcal");
        field.setAccessible(true);
        field.set(nap, 23.756);

        //when
        final double result = nap.getKcal();

        //then
        assertEquals(result, 23.756);
    }

    /**
     * Test of setKcal method, of class Day.
     */
    @Test
    public void testSetKcal() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        //when
        nap.setKcal(654.78);

        //then
        final Field field = nap.getClass().getDeclaredField("kcal");
        field.setAccessible(true);
        assertEquals(field.get(nap), 654.78);
    }

    /**
     * Test of getProtein method, of class Day.
     */
    @Test
    public void testGetProtein() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("protein");
        field.setAccessible(true);
        field.set(nap, 566.78);

        //when
        final double result = nap.getProtein();

        //then
        assertEquals(result, 566.78);
    }

    /**
     * Test of setProtein method, of class Day.
     */
    @Test
    public void testSetProtein() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        //when
        nap.setProtein(456.6564987);

        //then
        final Field field = nap.getClass().getDeclaredField("protein");
        field.setAccessible(true);
        assertEquals(field.get(nap), 456.6564987);
    }

    /**
     * Test of getCh method, of class Day.
     */
    @Test
    public void testGetCh() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("ch");
        field.setAccessible(true);
        field.set(nap, 45.5464);

        //when
        final double result = nap.getCh();

        //then
        assertEquals(result, 45.5464);
    }

    /**
     * Test of setCh method, of class Day.
     */
    @Test
    public void testSetCh() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        //when
        nap.setCh(456.6564987);

        //then
        final Field field = nap.getClass().getDeclaredField("ch");
        field.setAccessible(true);
        assertEquals(field.get(nap), 456.6564987);
    }

    /**
     * Test of getFat method, of class Day.
     */
    @Test
    public void testGetFat() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();
        final Field field = nap.getClass().getDeclaredField("fat");
        field.setAccessible(true);
        field.set(nap, 874.45421);

        //when
        final double result = nap.getFat();

        //then
        assertEquals(result, 874.45421);
    }

    /**
     * Test of setFat method, of class Day.
     */
    @Test
    public void testSetFat() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Day nap = new Day();

        //when
        nap.setFat(45.828);

        //then
        final Field field = nap.getClass().getDeclaredField("fat");
        field.setAccessible(true);
        assertEquals(field.get(nap), 45.828);
    }
}
