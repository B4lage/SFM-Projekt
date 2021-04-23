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
import javafx.fxml.Initializable;
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
    
    private int bevittKcal ;
    private double celKcalInt ;
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
        bevittKcal = 0;
        LocalDate ld = datumValaszto.getValue();
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
        celKcalInt = 10*actW + 6.25*actH - 5*actA + 5;
        udvozloText.setText("Szia "+query.getResultList().get(0).getName()+"!");
        celKcal.setText(""+celKcalInt);
        elfogyasztottKcal.setText(""+bevittKcal);
        hatravanKcal.setText("" + (celKcalInt-bevittKcal));
        
        
    }
    @FXML
    void handleAdataimMegtekinteseButtonClicked() throws IOException {
        MainApp.setRoot("DataShow");
    }
    
    @FXML
    void handleEtkezesHozzaadasaButtonClicked() throws IOException {
        MainApp.setRoot("Meals");
    }
    
    
    
}
