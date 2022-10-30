package ku.cs.controllers.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ku.cs.service.ThemeMode;

public class ManagerController {
    @FXML
    StackPane pane;
    @FXML private Label adminNameLabel;
    @FXML private AnchorPane anchorPane;
    @FXML
    public void initialize() {
        ThemeMode.setThemeMode(anchorPane);

        System.out.println("initialize ManagerController");
    }

    void showProductData(){

    }
}
