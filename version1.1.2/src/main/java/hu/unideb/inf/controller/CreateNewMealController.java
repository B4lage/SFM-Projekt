package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.Meal;
import hu.unideb.inf.model.MealDao;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author roli1
 */
public class CreateNewMealController{
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
        MainApp.setRoot("AddMeal");
    }
}
