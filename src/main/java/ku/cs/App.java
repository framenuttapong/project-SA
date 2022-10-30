package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fxrouter.FXRouter;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        FXRouter.bind(this, stage, "SA Shop");
        Image icon = new Image(getClass().getResource("/ku/cs/icons/moon.png").toExternalForm());
        stage.getIcons().add(icon);
        FXRouter.bind(this, stage, "SA Shop", 750, 500);
        configRoute();
        FXRouter.goTo("marketplace");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/interfaces/";

        FXRouter.when("addProduct", packageStr+ "add_product.fxml");
//        FXRouter.when("createEmployee", packageStr2+ "create_employee.fxml");
        FXRouter.when("login", packageStr+ "login.fxml");
        FXRouter.when("register", packageStr+ "register.fxml");
        FXRouter.when("marketplace", packageStr+ "market.fxml");
        FXRouter.when("product", packageStr+ "product.fxml");
        FXRouter.when("creator",packageStr+ "creator.fxml");
        FXRouter.when("create_promotion_code",packageStr+ "create_promotion_code.fxml");

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}