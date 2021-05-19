
package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.ActualUser;
import hu.unideb.inf.model.Day;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * @author roli1
 */
public class MealsController implements Initializable{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public static LocalDate ma;
    
    @FXML
    private ImageView monkee;
    
    @FXML
    private AnchorPane bckgrund;
    
    @FXML
    private Label LabKcal;

    @FXML
    private Label LabCh;

    @FXML
    private Label LabFat;

    @FXML
    private Label LabProt;
    
    @FXML
    private TableView<Day> todaysMeals;

    @FXML
    private TableColumn<Day, String> todaysMealName;

    @FXML
    private TableColumn<Day, String> todaysMealFat;

    @FXML
    private TableColumn<Day, String> todaysMealGramm;

    @FXML
    private TableColumn<Day, String> todaysMealKcal;

    @FXML
    private TableColumn<Day, String> todaysMealProt;

    
    ObservableList<Day> oblist = FXCollections.observableArrayList();

    @FXML
    void switchToAddMeal() throws IOException{
        comeout("AddMeal");
    }
    
    @FXML
    void handleVisszaButtonClicked() throws IOException{
        comeout("DefaultPage");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comein();
        System.out.println(ma.toString());
        TypedQuery<Day> query = entityManager.createQuery("SELECT d FROM Day d WHERE DATUM = '"+ma.toString()+"' AND USERID = "+ActualUser.actUser.getId(),Day.class);
        oblist.addAll(query.getResultList());
        double sumKcal = 0;
        double sumCh = 0;
        double sumFat = 0;
        double sumProt = 0;
        for(int i = 0; i < query.getResultList().size(); i++)
        {
            sumKcal += query.getResultList().get(i).getKcal();
            sumCh += query.getResultList().get(i).getCh();
            sumFat += query.getResultList().get(i).getFat();
            sumProt += query.getResultList().get(i).getProtein();
        }
        LabKcal.setText(""+sumKcal);
        LabCh.setText(""+sumCh);
        LabFat.setText(""+sumFat);
        LabProt.setText(""+sumProt);
        todaysMealName.setCellValueFactory(new PropertyValueFactory<>("Meal"));
        todaysMealFat.setCellValueFactory(new PropertyValueFactory<>("fat"));
        todaysMealGramm.setCellValueFactory(new PropertyValueFactory<>("gramm"));
        todaysMealKcal.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        todaysMealProt.setCellValueFactory(new PropertyValueFactory<>("protein"));
        todaysMeals.setItems(oblist);
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
        LeirasController.previous = "Meals";
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

    // Menubar vege
}
