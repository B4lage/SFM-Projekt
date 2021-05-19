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
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
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
    private AnchorPane bckgrund;
    
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
        comeout(rootStr);
    }
    @FXML
    void handleBejelentkezesButtonClicked() throws IOException {
        
        String nev = felhNev.getText();
        String jelszo = jelszoErtek.getText();
        TypedQuery<UserAuthentication>query = entityManager.createQuery("SELECT a FROM UserAuthentication a WHERE NAME ='"+nev+"'", UserAuthentication.class);
        Alert alert = new Alert(AlertType.ERROR);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/fxml/dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.initStyle(StageStyle.UTILITY);
        if(query.getResultList().size() == 0)
        {
            
            alert.setTitle("Error");
            alert.setHeaderText("Nincs ilyen felhasználó!");
            alert.setContentText("Próbáld újra, vagy regisztrálj!");
            alert.showAndWait();
        }
        else if(jelszo.equals(query.getResultList().get(0).getPw())){
            tovabb(query);
        }
        else{
            
            alert.setTitle("Error");
            alert.setHeaderText("Hibás jelszó!");
            alert.setContentText("Próbáld újra!");
            alert.showAndWait();
        }
    }

    @FXML
    void handleRegisztracioButtonClicked() throws IOException {
        comeout("Registration");
    }
    
    @FXML
    void menuHandleLeirasButtonClicked() throws IOException {
        LeirasController.previous = "Login";
        comeout("Leiras");
    }
    
    @FXML
    void comein() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.7), bckgrund);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
    
    @FXML
    void comeout(String s) throws IOException {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.7), bckgrund);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            try {
                MainApp.setRoot(s);
            } catch (IOException ex) {
                
            }
        });
        fadeOut.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comein();
    }    
    
}
