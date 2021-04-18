package com.mycompany.mokolasvol1;

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
        App.setRoot("AddMeal");
    }
}
