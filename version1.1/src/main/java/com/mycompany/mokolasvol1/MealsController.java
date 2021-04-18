
package com.mycompany.mokolasvol1;

import java.io.IOException;
import javafx.fxml.FXML;



/**
 * FXML Controller class
 *
 * @author roli1
 */
public class MealsController{
  
    @FXML
    void switchToAddMeal() throws IOException{
        App.setRoot("AddMeal");
    }
    
    @FXML
    void switchToMainPage() throws IOException{
        App.setRoot("MainPage");
    }
}
