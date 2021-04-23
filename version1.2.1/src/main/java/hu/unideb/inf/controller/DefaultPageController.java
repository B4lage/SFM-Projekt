/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.ActualUser;
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
        LocalDate ld = datumValaszto.getValue();
        datum.setText(ld.toString());
        
        TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a", User.class);
        //udvozloText.setText("Szia "+query.getResultList().get(0).getName()+"!");
        System.out.println(ActualUser.actUser.getId());
        
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
