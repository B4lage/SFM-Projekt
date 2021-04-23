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
public class JpaDayDao implements DayDao{
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("DAO closed...");
    }
    
    public void saveDay(Day d)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(d);
        entityManager.getTransaction().commit();
    }
    public void deleteDay(Day d)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(d);
        entityManager.getTransaction().commit();
    }
    public void updateDay(Day d)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(d);
        entityManager.getTransaction().commit();
    }
    public List<Day> getDays()
    {
        TypedQuery<Day> query = entityManager.createQuery("SELECT a FROM Day a", Day.class);
        List<Day> days = query.getResultList();
        return days;
    }
}
