package com.mycompany.mokolasvol1;

import java.io.IOException;
import javafx.fxml.FXML;

public class AddMealController {

    
    @FXML
    void switchToCreateNewMeal() throws IOException{
        App.setRoot("CreateNewMeal");
    }

    @FXML
    void switchToMeals() throws IOException{
        App.setRoot("Meals");
    }
}