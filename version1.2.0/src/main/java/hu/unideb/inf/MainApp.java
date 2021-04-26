package hu.unideb.inf;

import hu.unideb.inf.model.Meal;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.JpaUserDao;
import hu.unideb.inf.model.MealDao;
import hu.unideb.inf.model.User;
import hu.unideb.inf.model.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.h2.tools.Server;


public class MainApp extends Application {

    private static Scene scene;

    /*@Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLStudentsScene.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Students Register");
        stage.setScene(scene);
        stage.show();
    }*/
    
    @Override
    public void start(Stage stage) throws IOException {
        /*try (UserDao uDAO = new JpaUserDao();) {
            List<User> aList = uDAO.getUsers();
            if(aList.size() > 0)
            {
            controllerName = "DefaultPage";
            }
        }*/
        scene = new Scene(loadFXML("Login"), 1080, 720);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getClassLoader().getResource("fxml/" + fxml + ".fxml"));
        
        return fxmlLoader.load();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            startDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        launch();
        
        //stopDatabase();
    }

    private static Server s = new Server();
    
    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
    
}
