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
public class MealTest {
    
    private Meal meal;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        meal = new Meal(); 
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal etel = new Meal();

        //when
        etel.setId(38);

        //then
        final Field field = etel.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(etel), 38);
    }

    /**
     * Test of setId method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal etel = new Meal();
        final Field field = etel.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(etel, 52);

        //when
        final int result = etel.getId();

        //then
        assertEquals(result, 52);
    }
    
    /**
     * Test of getName method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal etel = new Meal();

        //when
        etel.setName("Krumpli");

        //then
        final Field field = etel.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals(field.get(etel), "Krumpli");
    }

    /**
     * Test of setName method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal repa = new Meal();
        final Field field = repa.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(repa, "Repa");

        //when
        final String result = repa.getName();

        //then
        assertEquals(result, "Repa");
    }

    /**
     * Test of getKcal method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetKcal() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal etel = new Meal();

        //when
        etel.setKcal(532.2);

        //then
        final Field field = etel.getClass().getDeclaredField("kcal");
        field.setAccessible(true);
        assertEquals(field.get(etel), 532.2);
    }

    /**
     * Test of setKcal method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetKcal() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal retek = new Meal();
        final Field field = retek.getClass().getDeclaredField("kcal");
        field.setAccessible(true);
        field.set(retek, 65.2);

        //when
        final double result = retek.getKcal();

        //then
        assertEquals(result, 65.2);
    }

    /**
     * Test of getPortion method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetPortion() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal etel = new Meal();

        //when
        etel.setPortion(3);

        //then
        final Field field = etel.getClass().getDeclaredField("portion");
        field.setAccessible(true);
        assertEquals(field.get(etel), 3);
    }

    /**
     * Test of setPortion method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetPortion() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal retek = new Meal();
        final Field field = retek.getClass().getDeclaredField("portion");
        field.setAccessible(true);
        field.set(retek, 8);

        //when
        final int result = retek.getPortion();

        //then
        assertEquals(result, 8);
    }

    /**
     * Test of getCh method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetCh() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal etel = new Meal();

        //when
        etel.setCh(3.76);

        //then
        final Field field = etel.getClass().getDeclaredField("ch");
        field.setAccessible(true);
        assertEquals(field.get(etel), 3.76);
    }

    /**
     * Test of setCh method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetCh() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal retek = new Meal();
        final Field field = retek.getClass().getDeclaredField("ch");
        field.setAccessible(true);
        field.set(retek, 4.85625);

        //when
        final double result = retek.getCh();

        //then
        assertEquals(result, 4.85625);
    }

    /**
     * Test of getProtein method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetProtein() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal etel = new Meal();

        //when
        etel.setProtein(15.897464);

        //then
        final Field field = etel.getClass().getDeclaredField("protein");
        field.setAccessible(true);
        assertEquals(field.get(etel), 15.897464);
    }

    /**
     * Test of setProtein method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetProtein() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal retek = new Meal();
        final Field field = retek.getClass().getDeclaredField("protein");
        field.setAccessible(true);
        field.set(retek, 151.8784564);

        //when
        final double result = retek.getProtein();

        //then
        assertEquals(result, 151.8784564);
    }

    /**
     * Test of getFat method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetFat() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal etel = new Meal();

        //when
        etel.setFat(87.541654);

        //then
        final Field field = etel.getClass().getDeclaredField("fat");
        field.setAccessible(true);
        assertEquals(field.get(etel), 87.541654);
    }

    /**
     * Test of setFat method, of class Meal.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetFat() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Meal retek = new Meal();
        final Field field = retek.getClass().getDeclaredField("fat");
        field.setAccessible(true);
        field.set(retek, 78.654654);

        //when
        final double result = retek.getFat();

        //then
        assertEquals(result, 78.654654);
    }

    /**
     * Test of toString method, of class Meal.
     */
    @Test
    public void testToString() {
        Meal etel = new Meal();
        etel.setName("retek");
        
        String expected = "retek";
        
        assertEquals(etel.getName(), expected);
    } 
}
