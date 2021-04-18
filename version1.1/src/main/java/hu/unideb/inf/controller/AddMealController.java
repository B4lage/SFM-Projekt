package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import java.io.IOException;
import javafx.fxml.FXML;

public class AddMealController {

    
    @FXML
    void switchToCreateNewMeal() throws IOException{
        MainApp.setRoot("CreateNewMeal");
    }

    @FXML
    void switchToMeals() throws IOException{
        MainApp.setRoot("Meals");
    }
}