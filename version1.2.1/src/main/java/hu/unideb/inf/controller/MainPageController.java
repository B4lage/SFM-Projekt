package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import java.io.IOException;
import javafx.fxml.FXML;

public class MainPageController{

    @FXML
    void switchToMeals() throws IOException{
        MainApp.setRoot("Meals");
    }    
    
    @FXML
    void switchToDataIn() throws IOException{
        MainApp.setRoot("DataIn");
    } 
    
}
