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
public interface UserDao extends AutoCloseable{
    
    public void saveUser(User a);
    public void deleteUser(User a);    
    public void updateUser(User a);    
    public List<User> getUsers();

    @Override
    default public void close(){        
    }
}