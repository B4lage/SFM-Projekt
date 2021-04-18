package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DataInController {

    @FXML
    private void switchToMainPage() throws IOException {
        MainApp.setRoot("MainPage");
    }
    
    @FXML
    private Button tovabbMainPageButton;

}
