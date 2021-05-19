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
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
public class DataShowController implements Initializable {

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    @FXML
    private ImageView monkee;
    
     @FXML
    private AnchorPane bckgrund;
    
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
        comein();
        TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a WHERE USERID ="+ActualUser.actUser.getId(), User.class);
        nevErtek.setText(query.getResultList().get(0).getName());
        korErtek.setText(""+query.getResultList().get(0).getKor());
        magassagErtek.setText(""+query.getResultList().get(0).getMagassag()+" cm");
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
        comeout("DataIn");
    }
    
    // Menubar
    
    @FXML
    void menuHandleAdataimPushed() throws IOException {
        comeout("DataShow");
        //MainApp.setRoot("DataShow");
    }
    
    @FXML
    void menuHandleKijelentkezesButtonClicked() throws IOException {
        comeout("Login");
        //MainApp.setRoot("Login");
    }
    
    @FXML
    void menuHandleFooldalButtonClicked() throws IOException {
        comeout("DefaultPage");
        //MainApp.setRoot("DefaultPage");
    }
    
    @FXML        
    void menuHandleLeirasButtonClicked() throws IOException {
        LeirasController.previous = "AddMeal";
        comeout("Leiras");
        //MainApp.setRoot("Leiras");
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
    
    // Menubar vege
    
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

    @FXML
    void handleVisszaButtonClicked() throws IOException {
        comeout("DefaultPage");
    }
    
}
