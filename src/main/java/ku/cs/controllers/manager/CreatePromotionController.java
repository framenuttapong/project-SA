package ku.cs.controllers.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class CreatePromotionController {

    @FXML private Button btnApply;
    @FXML private Circle btnClose;
    @FXML private Circle pictureProduct;
    @FXML private ComboBox selectComboBox;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private Label nameProduct;
    @FXML private ImageView btnBack;
    @FXML private TextField promoNameTextField;
    @FXML private TextField descriptionTextField;

    private Alert alert;

    @FXML
    public void initialize() {

        alert = new Alert(Alert.AlertType.NONE);

        System.out.println("initialize CreatePromotionController");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

    }

    @FXML
    private void handleMouseEvent(MouseEvent event) throws IOException {

    }
}
