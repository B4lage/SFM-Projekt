
package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Day;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        MainApp.setRoot("AddMeal");
    }
    
    @FXML
    void handleVisszaButtonClicked() throws IOException{
        MainApp.setRoot("DefaultPage");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypedQuery<Day> query = entityManager.createQuery("SELECT a FROM Day a", Day.class);
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
}
