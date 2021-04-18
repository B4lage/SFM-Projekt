package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.JpaUserDao;
import hu.unideb.inf.model.Meal;
import hu.unideb.inf.model.MealDao;
import hu.unideb.inf.model.User;
import hu.unideb.inf.model.UserDao;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataInController {

    @FXML
    private TextField magassagErtek;

    @FXML
    private TextField sulyErtek;

    @FXML
    private TextField celSulyErtek;

    @FXML
    private RadioButton radioNem;

    @FXML
    private ToggleGroup nem;

    @FXML
    private DatePicker szulEvErtek;

    @FXML
    private TextField nevErtek;

    @FXML
    private void switchToMainPage() throws IOException {
        User user1 = new User();
        user1.setName("tamas");
        user1.setMagassag(180);
        user1.setSuly(100);
        user1.setCelSuly(80);
        user1.setKor(10);
        user1.setNem(1);
        
        /*System.out.println("#0");
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        System.out.println("#1");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("#2");
        entityManager.getTransaction().begin();
        System.out.println("#3");
        entityManager.persist(user1);
        System.out.println("#4");
        entityManager.getTransaction().commit();
        System.out.println("#5");
        */
        
        
        try (UserDao uDao = new JpaUserDao();) {
            uDao.saveUser(user1);
        }
        
        MainApp.setRoot("DefaultPage");
    }
    
    @FXML
    private Button tovabbMainPageButton;

}
