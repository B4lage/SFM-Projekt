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
public class JpaUserAuthenticationDao implements UserAuthenticationDao{
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("DAO closed...");
    }
    
    public void saveUserAuthentication(UserAuthentication a)
    {
        entityManager.getTransaction().begin();
        System.out.println("#1");
        entityManager.persist(a);
        System.out.println("#2");
        entityManager.getTransaction().commit();
        System.out.println("#3");
    }
    public void deleteUserAuthentication(UserAuthentication a)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }
    public void updateUserAuthentication(UserAuthentication a)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }
    public List<UserAuthentication> getUserAuthentications()
    {
        TypedQuery<UserAuthentication> query = entityManager.createQuery("SELECT a FROM User a", UserAuthentication.class);
        List<UserAuthentication> userauthentications = query.getResultList();
        return userauthentications;
    }

}