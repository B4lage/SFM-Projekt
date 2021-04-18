package com.mycompany.mokolasvol1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DataInController {

    @FXML
    private void switchToMainPage() throws IOException {
        App.setRoot("MainPage");
    }
    
    @FXML
    private Button tovabbMainPageButton;

}
