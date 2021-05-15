package hu.unideb.inf;

import hu.unideb.inf.model.Meal;
import hu.unideb.inf.model.JpaMealDao;
import hu.unideb.inf.model.JpaUserDao;
import hu.unideb.inf.model.MealDao;
import hu.unideb.inf.model.User;
import hu.unideb.inf.model.UserDao;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.h2.tools.Server;


public class MainApp extends Application {
    Parent root;
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        try {
            MenuBar menuBar = new MenuBar();
            VBox vbox = new VBox(menuBar);
            scene = new Scene(loadFXML("Login"), 1080, 720);
            //root = FXMLLoader.load(getClass().getClassLoader().getResource("../../../../resources/fxml/Login.fxml"));
            //scene = new Scene(root);
            //stage.initStyle(StageStyle.TRANSPARENT);
            //scene.setFill(Color.TRANSPARENT);
            stage.getIcons().add(new Image("/img/icon.jpg"));
            stage.setTitle("Monkee");
            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                exit(0);
            } );  

            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getClassLoader().getResource("fxml/" + fxml + ".fxml"));
        
        return fxmlLoader.load();
    }

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
