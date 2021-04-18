package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import java.io.IOException;
import javafx.fxml.FXML;



/**
 * FXML Controller class
 *
 * @author roli1
 */
public class CreateNewMealController{
    
    @FXML
    void switchToAddMeal() throws IOException{
        MainApp.setRoot("AddMeal");
    }
}
