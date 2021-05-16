/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.ActualUser;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.JpaUserAuthenticationDao;
import hu.unideb.inf.model.User;
import hu.unideb.inf.model.UserAuthentication;
import hu.unideb.inf.model.UserAuthenticationDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Tomi
 */
public class LoginController implements Initializable {

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @FXML
    private TextField felhNev;

    @FXML
    private PasswordField jelszoErtek;

    void tovabb(TypedQuery<UserAuthentication> query) throws IOException
    {
        String rootStr = "DefaultPage"; 
        ActualUser.actUser = query.getResultList().get(0);
        TypedQuery<User> userQuery = entityManager.createQuery("SELECT a FROM User a WHERE USERID ="+ActualUser.actUser.getId(),User.class);
        if(userQuery.getResultList().size() == 0)
        {
            rootStr = "DataIn";
        }
        MainApp.setRoot(rootStr);
    }
    @FXML
    void handleBejelentkezesButtonClicked() throws IOException {
        
        String nev = felhNev.getText();
        String jelszo = jelszoErtek.getText();
        TypedQuery<UserAuthentication>query = entityManager.createQuery("SELECT a FROM UserAuthentication a WHERE NAME ='"+nev+"'", UserAuthentication.class);
        if(query.getResultList().size() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Hibás felhasználó!");
            alert.setHeaderText("Nincs ilyen felhasználó!");
            alert.setContentText("Próbáld újra, vagy regisztrálj!");
            alert.showAndWait();
        }
        else if(jelszo.equals(query.getResultList().get(0).getPw())){
            tovabb(query);
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Hibás jelszó!");
            alert.setHeaderText("Hibás jelszó! Próbáld újra!");
            alert.showAndWait();
        }
    }

    @FXML
    void handleRegisztracioButtonClicked() throws IOException {
        MainApp.setRoot("Registration");
    }
    
    @FXML
    void menuHandleLeirasButtonClicked() throws IOException {
        LeirasController.previous = "Login";
        MainApp.setRoot("Leiras");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
