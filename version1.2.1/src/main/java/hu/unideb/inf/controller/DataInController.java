package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.ActualUser;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.JpaUserDao;
import hu.unideb.inf.model.Meal;
import hu.unideb.inf.model.MealDao;
import hu.unideb.inf.model.User;
import hu.unideb.inf.model.UserDao;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DataInController implements Initializable {
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
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
    private void handleMentesButtonClicked() throws IOException {
        
        /*TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a WHERE USERID ="+ActualUser.actUser.getId(), User.class);
        int id = query.getResultList().get(0).getId();
        if(query.getResultList().size() > 0)
        {
            user1 = query.getResultList().get(0);
        }*/
        User user1 = new User();
        user1.setName(nevErtek.getText());
        user1.setMagassag(Double.parseDouble(magassagErtek.getText()));
        user1.setSuly(Double.parseDouble(sulyErtek.getText()));
        user1.setCelSuly(Double.parseDouble(celSulyErtek.getText()));
        
        LocalDate today = LocalDate.now();                          //Today's date
        Period p = Period.between(szulEvErtek.getValue(), today);
        
        user1.setKor(p.getYears());
        user1.setNem(1);
        user1.setFelhasznalo(ActualUser.actUser);
        try (UserDao uDao = new JpaUserDao();) {
            uDao.saveUser(user1);
        }
        /*(new Scanner(System.in)).nextLine();
        try (UserDao uDao = new JpaUserDao();) {
            uDao.deleteUser(user1);
        }
        */
        MainApp.setRoot("DefaultPage");
    }
    
    @FXML
    private Button tovabbMainPageButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a WHERE USERID ="+ActualUser.actUser.getId(), User.class);
        if(query.getResultList().size() > 0)
        {
            nevErtek.setText(query.getResultList().get(0).getName());
            magassagErtek.setText(""+query.getResultList().get(0).getMagassag());
            sulyErtek.setText(""+query.getResultList().get(0).getSuly());
            celSulyErtek.setText(""+query.getResultList().get(0).getCelSuly());
        }
    }
}
