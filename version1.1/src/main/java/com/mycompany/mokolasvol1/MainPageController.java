
package com.mycompany.mokolasvol1;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author roli1
 */
public class MainPageController{

    @FXML
    void switchToMeals() throws IOException{
        App.setRoot("Meals");
    }    
    
    @FXML
    void switchToDataIn() throws IOException{
        App.setRoot("DataIn");
    } 
    
}
