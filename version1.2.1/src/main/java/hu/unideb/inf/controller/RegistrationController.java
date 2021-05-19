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
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

public class RegistrationController implements Initializable 
{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public static double GYENGE = 0.33;
    public static double KOZEPES = 0.66;
    public static double EROS = 1;
    
    @FXML
    private ImageView monkee;
     
     @FXML
    private AnchorPane bckgrund;
    
    @FXML
    private Label passwordStrengthLabel;
    
    @FXML
    private ProgressBar passwordMeter;
    
    @FXML
    private TextField felhNev;
    
    @FXML
    private PasswordField jelszoErtek;

    @FXML
    private PasswordField jelszoErosites;

    

    public static double getStrength(String pw)
    {
        if(pw.length() < 3)
        {
            return 0;
        }
        boolean containsNum = false;
        boolean containsSpec = false;
        boolean containsBig = false;
        for(int i=0;i<pw.length();i++)
        {
            if(Character.isDigit(pw.charAt(i)))
            {
                containsNum = true;
            }
            if(Character.isUpperCase(pw.charAt(i)))
            {
                containsBig = true;
            }
            if (String.valueOf(pw.charAt(i)).matches("[^a-zA-Z0-9]")) 
            {
                containsSpec = true;
            }
        }
        if(containsNum && containsSpec && containsBig)
        {
            return EROS;
        }
        else if(containsNum && containsBig || containsNum && containsSpec || containsSpec && containsBig)
        {
            return KOZEPES;
        }
        
        return GYENGE;
    }
        
    @FXML
    void handleRegisztracioButtonClicked() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/fxml/dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.initStyle(StageStyle.UTILITY);
        TypedQuery<UserAuthentication> query = entityManager.createQuery("SELECT a FROM UserAuthentication a WHERE NAME ='"+felhNev.getText()+"'", UserAuthentication.class);
        if(felhNev.getText()== "")
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás felhasználónév!");
            alert.setContentText("Adj meg érvényes felhasználónevet!");
            alert.showAndWait();
        }
        else if(query.getResultList().size() != 0)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Foglalt felhasználónév!");
            alert.setContentText("Ez a felhasználónév foglalt!");
            alert.showAndWait();
        }
        else if(jelszoErtek.getText() == "" || jelszoErosites.getText() == "")
        {            
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás jelszó!");
            alert.setContentText("Adj meg érvényes jelszót!");
            alert.showAndWait();
        }
        else if(!jelszoErtek.getText().equals(jelszoErosites.getText()))
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás jelszó!");
            alert.setContentText("A két jelszónak egyezőnek kell lennie!");
            alert.showAndWait();
        }
        else if(getStrength(jelszoErtek.getText()) != 1)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Gyenge jelszó!");
            alert.setContentText("A jelszónak minimum 4 karakter hosszúnak kell lennie, tartalmaznia kell számot és speciális karaktert!");
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
            
            comeout("DataIn");
            
        }
    }
    
    @FXML
    void setPasswordMeter() {
        if(getStrength(jelszoErtek.getText()) == 0 )
        {
            passwordStrengthLabel.setText("Túl rövid!");
        }
        else if(getStrength(jelszoErtek.getText()) == GYENGE )
        {
            passwordStrengthLabel.setText("Gyenge jelszó!");
            passwordMeter.setStyle("-fx-accent: red;");
        }
        else if(getStrength(jelszoErtek.getText()) == KOZEPES )
        {
            passwordStrengthLabel.setText("Közepes jelszó!");
            passwordMeter.setStyle("-fx-accent: orange;");
        }
        else if(getStrength(jelszoErtek.getText()) == EROS)
        {
            passwordStrengthLabel.setText("Erős jelszó!");
            passwordMeter.setStyle("-fx-accent: green;");
        }
        passwordMeter.setProgress(getStrength(jelszoErtek.getText()));
        
    }
    
    @FXML
    void handleVisszaButtonClicked() throws IOException {
        comeout("Login");
    }

    @FXML
    void menuHandleLeirasButtonClicked() throws IOException {
        LeirasController.previous = "Registration";
        comeout("Leiras");
    }
    
    @FXML
    void comein() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.1), bckgrund);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
    
    @FXML
    void comeout(String s) throws IOException {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.1), bckgrund);
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
    
    @FXML
    void mutat() throws InterruptedException{
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.2), monkee);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(01.0);
        fadeIn.play(); 
    }
    
    @FXML
    void takar() {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), monkee);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play(); 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        comein();
    }
}
          
    

