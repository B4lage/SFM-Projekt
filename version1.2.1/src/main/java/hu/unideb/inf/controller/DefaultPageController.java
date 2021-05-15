/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.ActualUser;
import hu.unideb.inf.model.Day;
import hu.unideb.inf.model.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Tomi
 */
public class DefaultPageController{

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public LocalDate ld;
    
    private int bevittKcal ;
    private double celKcalDouble ;
    @FXML
    private Label elfogyasztottKcal;

    @FXML
    private Label udvozloText;

    @FXML
    private Label celKcal;

    @FXML
    private Label hatravanKcal;

    @FXML
    private Label datum;
    
    @FXML
    private DatePicker datumValaszto;
    
    @FXML
    void handleMutatButtonClicked() throws IOException {
        if(datumValaszto.getValue()== null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hibás dátum!");
            alert.setHeaderText("Nincs kiválasztva dátum!");
            alert.showAndWait();
        }
        else
        {
            bevittKcal = 0;
            ld = datumValaszto.getValue();
            if(ld != null)
            {
                datum.setText(ld.toString());
            } 
            else
            {
                System.out.println("Nincs kiválasztva dátum!");
            }
            TypedQuery<Day> nap = entityManager.createQuery("SELECT d FROM Day d WHERE DATUM = '"+ld.toString()+"' AND USERID = "+ActualUser.actUser.getId(),Day.class);
            for(int i = 0; i < nap.getResultList().size(); i++)
            {
                bevittKcal += nap.getResultList().get(i).getKcal();
            }
            TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a WHERE USERID="+ActualUser.actUser.getId(), User.class);
            int actW = (int) query.getResultList().get(0).getSuly();
            int actH = (int) query.getResultList().get(0).getMagassag();
            int actA = (int) query.getResultList().get(0).getKor();
            double actActivity = query.getResultList().get(0).getAktivitas();
            double actG = 0;
            if(query.getResultList().get(0).getNem() == 1)
            {
                actG = 1;
            }
            else
            {
                actG = 0.9;
            }
            celKcalDouble = (10 * actW + 6.25 * actH - 5 * actA) * actActivity * actG;
            udvozloText.setText("Szia "+query.getResultList().get(0).getName()+"!");
            celKcal.setText(""+(int)celKcalDouble);
            elfogyasztottKcal.setText(""+(int)bevittKcal);
            hatravanKcal.setText("" +(int)(celKcalDouble-bevittKcal));
        }
        
    }
    @FXML
    void handleAdataimMegtekinteseButtonClicked() throws IOException {
        MainApp.setRoot("DataShow");
    }
    
    // Menubar
    
    @FXML
    void menuHandleAdataimPushed() throws IOException {
        MainApp.setRoot("DataShow");
    }
    
    @FXML
    void menuHandleKijelentkezesButtonClicked() throws IOException {
        MainApp.setRoot("Login");
    }
    
    @FXML
    void menuHandleFooldalButtonClicked() throws IOException {
        MainApp.setRoot("DefaultPage");
    }
    
    @FXML        
    void menuHandleLeirasButtonClicked() throws IOException {
        MainApp.setRoot("Leiras");
    }
    
    // Menubar vege
    
    
    @FXML
    void handleEtkezesHozzaadasaButtonClicked() throws IOException {
        if(datumValaszto.getValue()== null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hibás dátum!");
            alert.setHeaderText("Nincs kiválasztva dátum!");
            alert.showAndWait();
        }
        else{
            MealsController.ma = datumValaszto.getValue();
            MainApp.setRoot("Meals");
        }
    }
    
    
    
}
