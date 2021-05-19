package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.Meal;
import hu.unideb.inf.model.MealDao;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author roli1
 */
public class CreateNewMealController{
    @FXML
    private TextField etelNeve;

    @FXML
    private TextField kcalErtek;

    @FXML
    private TextField proteinErtek;

    @FXML
    private TextField chErtek;

    @FXML
    private TextField fatErtek;

    @FXML
    private TextField portionErtek;

    @FXML
    void switchToAddMeal() throws IOException{
        boolean isValidKcal = true;
        boolean isValidProt = true;
        boolean isValidCh = true;
        boolean isValidZsir = true;
        boolean isValidMertek = true;

        
        try
        {
            Integer.parseInt(kcalErtek.getText());
        }
        catch(NumberFormatException e)
        {
            isValidKcal = false;
        }

        try
        {
            Double.parseDouble(proteinErtek.getText());
        }
        catch(NumberFormatException e)
        {
            isValidProt = false;
        }
        if(!isValidProt)
        {
            try
            {
                Integer.parseInt(proteinErtek.getText());
            }
            catch(NumberFormatException e)
            {
                isValidProt = false;
            }
        }
        

        try
        {
            Double.parseDouble(chErtek.getText());
        }
        catch(NumberFormatException e)
        {
            isValidCh = false;
        }
        if(!isValidCh)
        {
            try
            {
                Integer.parseInt(chErtek.getText());
            }
            catch(NumberFormatException e)
            {
                isValidCh = false;
            }
        }
        

        try
        {
            Double.parseDouble(fatErtek.getText());
        }
        catch(NumberFormatException e)
        {
            isValidZsir = false;
        }
        if(!isValidZsir)
        {
            try
            {
                Integer.parseInt(fatErtek.getText());
            }
            catch(NumberFormatException e)
            {
                isValidZsir = false;
            }
        }
        

        
        try
        {
            Integer.parseInt(portionErtek.getText());
        }
        catch(NumberFormatException e)
        {
            isValidMertek = false;
        }
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/fxml/dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.initStyle(StageStyle.UTILITY);
        if(etelNeve.getText() == "")
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nincs megadva név!");
            alert.setContentText("Add meg az étel nevét!");
            alert.showAndWait();
        }
        else if(chErtek.getText() == "" || fatErtek.getText() == "" || proteinErtek.getText() == "" || kcalErtek.getText() == "")
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás adatok!");
            alert.setContentText("Add meg az étel tápanyagtartalmára vonatkozó összes adatot!");
            alert.showAndWait();
        }
        else if(portionErtek.getText() == "")
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás mennyiség!");
            alert.setContentText("Add meg az étel egy adagjának mennyiségét g/ml-ben!");
            alert.showAndWait();
        }
        else if(!isValidKcal)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás mennyiség!");
            alert.setContentText("A kalória értékét egész számmal add meg!");
            alert.showAndWait();
        }
        else if(!isValidProt)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás mennyiség!");
            alert.setContentText("A fehérje értékét egész számmal vagy tizedes törttel add meg!");
            alert.showAndWait();
        }
        else if(!isValidCh)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás mennyiség!");
            alert.setContentText("A szénhidrát értékét egész számmal vagy tizedes törttel add meg!");
            alert.showAndWait();
        }
        else if(!isValidZsir)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás mennyiség!");
            alert.setContentText("A zsír értékét egész számmal vagy tizedes törttel add meg!");
            alert.showAndWait();
        }
        else if(!isValidMertek)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hibás mennyiség!");
            alert.setContentText("Az adag értékét egész számmal vagy tizedes törttel add meg!");
            alert.showAndWait();
        }
        else
        {
            Meal kaja1 = new Meal();
            kaja1.setName(etelNeve.getText());
            kaja1.setCh(Double.parseDouble(chErtek.getText()));
            kaja1.setFat(Double.parseDouble(fatErtek.getText()));
            kaja1.setKcal(Integer.parseInt(kcalErtek.getText()));
            kaja1.setProtein(Double.parseDouble(proteinErtek.getText()));
            kaja1.setPortion(Integer.parseInt(portionErtek.getText()));
            
            try (MealDao mDao = new JpaMealDao();) {
            mDao.saveMeal(kaja1);
            }
            
            MainApp.setRoot("AddMeal");
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
        LeirasController.previous = "CreateNewMeal";
        MainApp.setRoot("Leiras");
    }
    
    // Menubar vege
    
}
