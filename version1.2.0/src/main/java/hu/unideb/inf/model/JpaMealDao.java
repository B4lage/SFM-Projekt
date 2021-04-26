/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tomi
 */
public class JpaMealDao implements MealDao{
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("DAO closed...");
    }
    
    public void saveMeal(Meal a)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }
    public void deleteMeal(Meal a)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }
    public void updateMeal(Meal a)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }
    public List<Meal> getMeals()
    {
        TypedQuery<Meal> query = entityManager.createQuery("SELECT a FROM Meal a", Meal.class);
        List<Meal> meals = query.getResultList();
        return meals;
    }
}