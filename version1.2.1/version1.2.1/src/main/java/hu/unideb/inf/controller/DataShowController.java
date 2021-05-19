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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class DataShowController implements Initializable {

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @FXML
    private Label nevErtek ;

    @FXML
    private Label korErtek;

    @FXML
    private Label magassagErtek;

    @FXML
    private Label sulyErtek;
    
    @FXML
    private Label aktivitasErtek;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a WHERE USERID ="+ActualUser.actUser.getId(), User.class);
        nevErtek.setText(query.getResultList().get(0).getName());
        korErtek.setText(""+query.getResultList().get(0).getKor());
        magassagErtek.setText(""+query.getResultList().get(0).getMagassag()+" cm");
        System.out.println(String.format("%d cm\n\n\n",query.getResultList().get(0).getMagassag()));
        sulyErtek.setText(""+query.getResultList().get(0).getSuly()+" kg");
        int aktivitasIndex = 0;
        for(int i=0;i<DataInController.aktivitasErtekek.length;i++)
        {
            if(query.getResultList().get(0).getAktivitas() == DataInController.aktivitasErtekek[i])
            {
                aktivitasIndex = i;
            }
        }
        aktivitasErtek.setText(DataInController.aktivitasLehetosegek[aktivitasIndex]);
    }    
    
    @FXML
    void handleAdataimMegvaltoztatasaButtonClicked() throws IOException{
        MainApp.setRoot("DataIn");
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
        LeirasController.previous = "DataShow";
        MainApp.setRoot("Leiras");
    }
    
    // Menubar vege
    
    @FXML
    void handleVisszaButtonClicked() throws IOException {
        MainApp.setRoot("DefaultPage");
    }
    
}
