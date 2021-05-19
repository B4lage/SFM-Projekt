package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.ActualUser;
import hu.unideb.inf.model.Day;
import hu.unideb.inf.model.DayDao;
import hu.unideb.inf.model.JpaDayDao;
import hu.unideb.inf.model.Meal;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class AddMealController implements Initializable{
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @FXML
    private TextField mitEvett;
    
    @FXML
    private TextField mennyitEvett;
    
    @FXML
    private RadioButton gml;
    
    @FXML
    private RadioButton adag;

    @FXML
    private ToggleGroup egyseg;
    
    @FXML
    private TableView<Meal> mealsTable;
    
    @FXML
    private TableColumn<Meal,String> Tname;

    @FXML
    private TableColumn<Meal,String> Tfat;

    @FXML
    private TableColumn<Meal,String> Tkcal;

    @FXML
    private TableColumn<Meal,String> Tprot;

    @FXML
    private TableColumn<Meal,String> Tch;

    @FXML
    private TableColumn<Meal,String> Tportion;

    ObservableList<Meal> oblist = FXCollections.observableArrayList();    
    
    @FXML
    void switchToCreateNewMeal() throws IOException{
        oblist.removeAll();
        MainApp.setRoot("CreateNewMeal");
    }

    @FXML
    void handleOKButtonClicked() throws IOException{
        
        MainApp.setRoot("Meals");
    }
    
    @FXML
    void searchInTable() throws IOException{
        TypedQuery<Meal> query = entityManager.createQuery("SELECT a FROM Meal a WHERE NAME LIKE '%"+mitEvett.getText()+"%'", Meal.class);
        mealsTable.getItems().clear();
        oblist.removeAll();
        oblist.addAll(query.getResultList());
        mealsTable.setItems(oblist);
        mealsTable.setPlaceholder(new Label("Nincs találat"));
    }
   
    
    @FXML
    void onMouseClicked() throws IOException{      
        if (mealsTable.getSelectionModel().getSelectedItem() != null) {
            Meal selectedMeal = mealsTable.getSelectionModel().getSelectedItem();
            mitEvett.setText(selectedMeal.getName());
        }
    }
    
    @FXML
    void handleHozzaadomANapomhozButtonClicked() throws IOException{
        if(mitEvett.getText() == "")
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hibás étel!");
            alert.setHeaderText("Add meg az étel nevét!");
            alert.showAndWait();
        }
        else if(mennyitEvett.getText() == "")
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hibás mennyiség!");
            alert.setHeaderText("Add meg, hogy mennyit ettél az adott ételből!");
            alert.showAndWait();
        }
        else
        {
            Day napom = new Day();
            TypedQuery<Meal> query = entityManager.createQuery("SELECT a FROM Meal a WHERE NAME='"+mitEvett.getText()+"'", Meal.class);

            int portio = Integer.parseInt(mennyitEvett.getText());
            boolean adagOrG = gml.isSelected();
            if(gml.isSelected())
            {
                double x = (double)portio / query.getResultList().get(0).getPortion();
                napom.setKcal(Math.round(x * query.getResultList().get(0).getKcal() * 100.0)/100.0);

                napom.setFat(Math.round(query.getResultList().get(0).getFat() * x * 100.0) / 100.0);
                napom.setCh(Math.round(query.getResultList().get(0).getCh() * x * 100.0) / 100.0);
                napom.setProtein(Math.round(query.getResultList().get(0).getProtein() * x * 100.0) / 100.0);

                napom.setGramm(portio);
            }
            else
            {
                napom.setKcal(Math.round(portio * query.getResultList().get(0).getKcal() * 100.0) / 100.0);

                napom.setFat(Math.round(query.getResultList().get(0).getFat() * portio * 100.0) / 100.0);
                napom.setCh(Math.round(query.getResultList().get(0).getCh() * portio * 100.0) / 100.0);
                napom.setProtein(Math.round(query.getResultList().get(0).getProtein() * portio * 100.0) / 100.0);

                napom.setGramm(portio * query.getResultList().get(0).getPortion());
            }

            napom.setDatum(MealsController.ma);

            napom.setMeal(query.getResultList().get(0));
            napom.setUsr(ActualUser.actUser);


            try (DayDao mDao = new JpaDayDao();) {
                mDao.saveDay(napom);
            }
        }
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
        LeirasController.previous = "AddMeal";
        MainApp.setRoot("Leiras");
    }
    
    // Menubar vege

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypedQuery<Meal> query = entityManager.createQuery("SELECT a FROM Meal a", Meal.class);
        oblist.addAll(query.getResultList());
        Tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        Tfat.setCellValueFactory(new PropertyValueFactory<>("fat"));
        Tkcal.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        Tprot.setCellValueFactory(new PropertyValueFactory<>("protein"));
        Tch.setCellValueFactory(new PropertyValueFactory<>("ch"));
        Tportion.setCellValueFactory(new PropertyValueFactory<>("portion"));
        mealsTable.setPlaceholder(new Label("Nincs étel az adatbázisban"));
        
        mealsTable.setItems(oblist);
        
        /*TableViewSelectionModel<Meal> selectionModel = mealsTable.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Meal> selectedItems = selectionModel.getSelectedItems();
        
        mitEvett.setText(selectedItems.get(0).getName());*/
        
    }
}