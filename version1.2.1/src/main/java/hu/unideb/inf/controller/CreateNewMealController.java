package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.Meal;
import hu.unideb.inf.model.MealDao;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author roli1
 */
public class CreateNewMealController implements Initializable{
    
    @FXML
    private ImageView monkee;
        
     @FXML
    private AnchorPane bckgrund;
     
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
            alert.setHeaderText("Nincs megadva n??v!");
            alert.setContentText("Add meg az ??tel nev??t!");
            alert.showAndWait();
        }
        else if(chErtek.getText() == "" || fatErtek.getText() == "" || proteinErtek.getText() == "" || kcalErtek.getText() == "")
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hib??s adatok!");
            alert.setContentText("Add meg az ??tel t??panyagtartalm??ra vonatkoz?? ??sszes adatot!");
            alert.showAndWait();
        }
        else if(portionErtek.getText() == "")
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hib??s mennyis??g!");
            alert.setContentText("Add meg az ??tel egy adagj??nak mennyis??g??t g/ml-ben!");
            alert.showAndWait();
        }
        else if(!isValidKcal)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hib??s mennyis??g!");
            alert.setContentText("A kal??ria ??rt??k??t eg??sz sz??mmal add meg!");
            alert.showAndWait();
        }
        else if(!isValidProt)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hib??s mennyis??g!");
            alert.setContentText("A feh??rje ??rt??k??t eg??sz sz??mmal vagy tizedes t??rttel add meg!");
            alert.showAndWait();
        }
        else if(!isValidCh)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hib??s mennyis??g!");
            alert.setContentText("A sz??nhidr??t ??rt??k??t eg??sz sz??mmal vagy tizedes t??rttel add meg!");
            alert.showAndWait();
        }
        else if(!isValidZsir)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hib??s mennyis??g!");
            alert.setContentText("A zs??r ??rt??k??t eg??sz sz??mmal vagy tizedes t??rttel add meg!");
            alert.showAndWait();
        }
        else if(!isValidMertek)
        {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hib??s mennyis??g!");
            alert.setContentText("Az adag ??rt??k??t eg??sz sz??mmal vagy tizedes t??rttel add meg!");
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
            
            comeout("AddMeal");
        }
        

    }
    
    @FXML
    void handleVisszaButtonClicked() throws IOException {
        comeout("AddMeal");
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
        LeirasController.previous = "CreateNewMeal";
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comein();
    }    
}
