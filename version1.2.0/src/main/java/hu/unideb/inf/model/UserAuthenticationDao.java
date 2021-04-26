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
public interface UserAuthenticationDao extends AutoCloseable{
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public void saveUserAuthentication(UserAuthentication a);
    public void deleteUserAuthentication(UserAuthentication a);    
    public void updateUserAuthentication(UserAuthentication a);    
    public List<UserAuthentication> getUserAuthentications();

    @Override
    default public void close(){        
    }
}