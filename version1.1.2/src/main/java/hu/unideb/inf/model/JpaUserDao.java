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
public class JpaUserDao implements UserDao{
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("DAO closed...");
    }
    
    public void saveUser(User a)
    {
        entityManager.getTransaction().begin();
        System.out.println("#1");
        entityManager.persist(a);
        System.out.println("#2");
        entityManager.getTransaction().commit();
        System.out.println("#3");
    }
    public void deleteUser(User a)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }
    public void updateUser(User a)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }
    public List<User> getUsers()
    {
        TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a", User.class);
        List<User> users = query.getResultList();
        return users;
    }

}