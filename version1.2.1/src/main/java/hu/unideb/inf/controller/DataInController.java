package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.ActualUser;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.JpaUserDao;
import hu.unideb.inf.model.Meal;
import hu.unideb.inf.model.MealDao;
import hu.unideb.inf.model.User;
import hu.unideb.inf.model.UserDao;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DataInController implements Initializable {
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public static final String[] aktivitasLehetosegek = new String[]{"Ülő munkát végzek, és nem sportolok",
                    "Ülő munkát végzek, és hetente 2-3x sportolok",
                    "Ülő munkát végzek, és hetente 4-6x sportolok",
                    "Fizikai munkát végzek napi 4-6 órában",
                    "Nehéz fizikai munkát végzek napi 8 órában"};
    
    public static final double[] aktivitasErtekek = new double[]{1.3,1.55,1.65,1.8,2.0};
    
    @FXML
    private ComboBox comboBox;
    
     @FXML
    private AnchorPane bckgrund;
    
    @FXML
    private TextField magassagErtek;

    @FXML
    private TextField sulyErtek;

    @FXML
    private RadioButton radioNem;

    @FXML
    private ToggleGroup nem;

    @FXML
    private DatePicker szulEvErtek;

    @FXML
    private TextField nevErtek;
    
    @FXML
    private Button megseButton;

    @FXML
    private void handleMentesButtonClicked() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/fxml/dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.initStyle(StageStyle.UTILITY);
        
        boolean isValidSuly = true;
        boolean isValidMagassag = true;
        
            try
            {
              Double.parseDouble(sulyErtek.getText());
            }
            catch(NumberFormatException e)
            {
              isValidSuly = false;
            }
            
            try
            {
              Integer.parseInt(magassagErtek.getText());
            }
            catch(NumberFormatException e)
            {
              isValidMagassag = false;
            }
        
        if(szulEvErtek.getValue() == null)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás dátum!");
            alert.setContentText("Nincs kiválasztva dátum!");
            alert.showAndWait();
        }
        else if(nem.getSelectedToggle() == null)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nincs nem kiválasztva!");
            alert.setContentText("Válassz nemet!");
            alert.showAndWait();
        }
        else if(comboBox.getSelectionModel().getSelectedItem() == null)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nincs aktivitás kiválasztva!");
            alert.setContentText("Válassz aktivitást!");
            alert.showAndWait();
        }
        else if(!isValidSuly)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nem megfelelő súly!");
            alert.setContentText("Adj meg egy érvényes súly értéket kg-ban!");
            alert.showAndWait();
        }
        else if(!isValidMagassag)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nem megfelelő magasság!");
            alert.setContentText("Adj meg egy egész számot, ami megadja a magasságod cm-ben!");
            alert.showAndWait();
        }
        else if(nevErtek.getText() == "")
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Érvénytelen név!");
            alert.setContentText("Add meg a neved!");
            alert.showAndWait();
        }
        else
        {
            TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a WHERE USERID ="+ActualUser.actUser.getId(), User.class);
            User user1 = new User();
            User regi = new User();
            if(query.getResultList().size() > 0)
            {
                regi = query.getResultList().get(0);
            }
            // Felhasznalo nemenek beallitasa
            RadioButton selectedRadioButton = (RadioButton) nem.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            if(toogleGroupValue.equals("Férfi"))
            {
                user1.setNem(1);
            }
            else if(toogleGroupValue.equals("Nő")){
                user1.setNem(2);
            }
            user1.setName(nevErtek.getText());
            user1.setMagassag(Integer.parseInt(magassagErtek.getText()));
            user1.setSuly(Double.parseDouble(sulyErtek.getText()));
            
            // Felhasznalo aktivitasanak beallitasa
            if(comboBox.getSelectionModel().getSelectedItem().toString().equals(aktivitasLehetosegek[0]))
            {
                user1.setAktivitas(aktivitasErtekek[0]);
            }
            else if(comboBox.getSelectionModel().getSelectedItem().toString().equals(aktivitasLehetosegek[1]))
            {
                user1.setAktivitas(aktivitasErtekek[1]);
            }
            else if(comboBox.getSelectionModel().getSelectedItem().toString().equals(aktivitasLehetosegek[2]))
            {
                user1.setAktivitas(aktivitasErtekek[2]);
            }
            else if(comboBox.getSelectionModel().getSelectedItem().toString().equals(aktivitasLehetosegek[3]))
            {
                user1.setAktivitas(aktivitasErtekek[3]);
            }
            else if(comboBox.getSelectionModel().getSelectedItem().toString().equals(aktivitasLehetosegek[4]))
            {
                user1.setAktivitas(aktivitasErtekek[4]);
            }
            
            // Felhasznalo koranak beallitasa
            LocalDate today = LocalDate.now();                          //Today's date
            Period p = Period.between(szulEvErtek.getValue(), today);
            user1.setKor(p.getYears());
            
            // Felhasznalo adatainak es a fiok osszekapcsolasa
            user1.setFelhasznalo(ActualUser.actUser);
            
            try (UserDao uDao = new JpaUserDao();) {
                uDao.saveUser(user1);
                uDao.deleteUser(regi);
            }
            
            comeout("DefaultPage");
        }   
    }
    
    @FXML
    void handeMegseButtonClicked() throws IOException {
        comeout("DataShow");
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
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.7), bckgrund);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
    
    @FXML
    void comeout(String s) throws IOException {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.7), bckgrund);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comein();
        TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a WHERE USERID ="+ActualUser.actUser.getId(), User.class);
        if(query.getResultList().size() > 0)
        {
            nevErtek.setText(query.getResultList().get(0).getName());
            magassagErtek.setText(""+query.getResultList().get(0).getMagassag());
            sulyErtek.setText(""+query.getResultList().get(0).getSuly());     
        }
        else
        {
            megseButton.setOpacity(0);
            megseButton.setDisable(true);
        }
        ObservableList<String> list = FXCollections.observableArrayList(aktivitasLehetosegek);
        comboBox.setItems(list);
    }
}
