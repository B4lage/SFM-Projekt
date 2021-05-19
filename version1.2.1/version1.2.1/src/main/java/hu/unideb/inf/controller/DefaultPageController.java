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
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Tomi
 */
public class DefaultPageController implements Initializable{

    public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    public static final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public LocalDate ld;
    
    private int bevittKcal ;
    
    private double actCh;
    private double actP;
    private double actF;
    
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
    private Label chLabel;

    @FXML
    private Label pLabel;

    @FXML
    private Label fatLabel;
    
    @FXML
    private ProgressBar chMeter;

    @FXML
    private ProgressBar pMeter;

    @FXML
    private ProgressBar fatMeter;
    
    @FXML
    private ImageView monke;
    
    void showData(){
        bevittKcal = 0;
        actCh = 0;
        actP = 0;
        actF = 0;
        datum.setText(ld.toString());
        TypedQuery<Day> nap = entityManager.createQuery("SELECT d FROM Day d WHERE DATUM = '"+ld.toString()+"' AND USERID = "+ActualUser.actUser.getId(),Day.class);
        
        for(int i = 0; i < nap.getResultList().size(); i++)
        {
            bevittKcal += nap.getResultList().get(i).getKcal();
            actCh += nap.getResultList().get(i).getCh();
            actP += nap.getResultList().get(i).getProtein();
            actF += nap.getResultList().get(i).getFat();
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
        
        double celCh = celKcalDouble*0.48 / 4;
        double celP = celKcalDouble*0.19 / 4;
        double celF = celKcalDouble*0.29 / 9;
        
        chLabel.setText(String.format("%.1f / %.0f g",actCh,celCh));
        pLabel.setText(String.format("%.1f / %.0f g",actP,celP));
        fatLabel.setText(String.format("%.1f / %.0f g",actF,celF));
        
        chMeter.setProgress(actCh/celCh);
        pMeter.setProgress(actP/celP);
        fatMeter.setProgress(actF/celF);
    }
    
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
            ld = datumValaszto.getValue();
            showData();
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
        LeirasController.previous = "DefaultPage";
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ld = LocalDate.now();
        datumValaszto.setValue(ld);
        showData();    
    }
    
    @FXML
    void mutat() throws InterruptedException{
        monke.setStyle("-fx-opacity: 1.0;");      
    }
    
    @FXML
    void takar() {
        monke.setStyle("-fx-opacity: 0.0;");
    }
    
    
    
}
