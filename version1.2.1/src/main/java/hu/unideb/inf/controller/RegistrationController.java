/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.ActualUser;
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

public class RegistrationController implements Initializable 
{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @FXML
    private TextField felhNev;

    @FXML
    private PasswordField jelszoErosites;

    @FXML
    private PasswordField jelszoErtek;

    @FXML
    void handleRegisztracioButtonClicked() throws IOException {
        
        TypedQuery<UserAuthentication> query = entityManager.createQuery("SELECT a FROM UserAuthentication a WHERE NAME ='"+felhNev.getText()+"'", UserAuthentication.class);
        if(felhNev.getText()== "")
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hibás felhasználónév!");
            alert.setHeaderText("Adj meg érvényes felhasználónevet!");
            alert.showAndWait();
        }
        else if(query.getResultList().size() != 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Foglalt felhasználónév!");
            alert.setHeaderText("Ez a felhasználónév foglalt!");
            alert.showAndWait();
        }
        else if(jelszoErtek.getText() == "" || jelszoErosites.getText() == "")
        {            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hibás jelszó!");
            alert.setHeaderText("Adj meg érvényes jelszót!");
            alert.showAndWait();
        }
        else if(!jelszoErtek.getText().equals(jelszoErosites.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hibás jelszó!");
            alert.setHeaderText("A két jelszónak egyezőnek kell lennie!");
            alert.showAndWait();
        }
        else
        {
            UserAuthentication usera = new UserAuthentication();
            usera.setName(felhNev.getText());
            usera.setPw(jelszoErtek.getText());
            try (UserAuthenticationDao uaDao = new JpaUserAuthenticationDao();) {
                uaDao.saveUserAuthentication(usera);
            }
            
            TypedQuery<UserAuthentication> UAquery = entityManager.createQuery("SELECT a FROM UserAuthentication a WHERE NAME ='"+felhNev.getText()+"'", UserAuthentication.class);
            ActualUser.actUser = UAquery.getResultList().get(0);
            
            MainApp.setRoot("DataIn");
            
        }
    }
    
    @FXML
    void handleVisszaButtonClicked() throws IOException {
        MainApp.setRoot("Login");
    }

    @FXML
    void menuHandleLeirasButtonClicked() throws IOException {
        MainApp.setRoot("Login");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //To change body of generated methods, choose Tools | Templates.
    }
}
          
    
