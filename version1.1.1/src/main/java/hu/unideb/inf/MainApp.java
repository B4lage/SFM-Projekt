package hu.unideb.inf;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        scene = new Scene(loadFXML("DataIn"), 1080, 720);
        
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
        System.out.println("Yeet0");
        /*try {
            System.out.println("Yeet_database_start");
            startDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }*/
        System.out.println("Yeet_launch");
        launch();
        System.out.println("Yeet2");
        //stopDatabase();
        System.out.println("Yeet3");
    }

    private static Server s = new Server();
    
    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
    
}
