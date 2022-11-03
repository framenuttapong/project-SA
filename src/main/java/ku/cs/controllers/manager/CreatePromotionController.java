package ku.cs.controllers.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class CreatePromotionController {
    private Alert alert;
    @FXML
    public void initialize() {

        alert = new Alert(Alert.AlertType.NONE);

        System.out.println("initialize CreatePromotionController");
    }

    

}
