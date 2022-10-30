package ku.cs.controllers.login;

import animatefx.animation.Flash;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import fxrouter.FXRouter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import ku.cs.service.ConnectionClass;
import ku.cs.service.ThemeMode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginController {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private AnchorPane pane;
    @FXML private Button mode;

    private Alert alert;
    public static boolean isLightMode = true;

    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);

    }

    @FXML
    public void handleDarkModeBtn(ActionEvent event) {
        isLightMode = !isLightMode;
        if (isLightMode) {
            ThemeMode.setLightMode(pane, mode);
        } else {
            ThemeMode.setDarkMode(pane, mode);
        }
        new Flash(pane).play();
    }

    @FXML
    public void handleLoginBtn(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String scene;
        if (!username.isEmpty() && !password.isEmpty()) {
            validation();
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Username or password is incorrect.");
            alert.show();
        }
//        if(accountsList.checkUsernamePassword(usernameText,password)) {
//            System.out.println("Login Success");
//            if ("Admin".equals(accountsList.getThisAccount().getRole())) {
//                scene = "admin_manager";
//            } else {
//                if(((UserAccount)(accountsList.getThisAccount())).getUserStatus().equals("Banned")) {
//                    ((UserAccount)(accountsList.getThisAccount())).setLoginAttempts();
//                    accountListDataSource = new AccountListFileDataSource();
//                    accountListDataSource.writeData(accountsList);
//                    scene = "login";
//                    alert.setAlertType(Alert.AlertType.WARNING);
//                    alert.setContentText("คุณถูกระงับสิทธิ์การเข้าใช้งาน");
//                    alert.show();
//                } else {
//                    LocalDateTime dateTime = LocalDateTime.now();
//                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//                    String formatDateTime = dateTime.format(format);
//                    ((UserAccount)(accountsList.getThisAccount())).setLastLogin(formatDateTime);
//                    accountListDataSource = new AccountListFileDataSource();
//                    accountListDataSource.writeData(accountsList);
//                    scene = "marketplace";
//                }
//            }
//            try {
//                info = new Info(accountsList, productList, reviewList, orderList, reportList);
//                fxrouter.FXRouter.goTo(scene, info);
//            } catch (IOException e) {
//                System.err.println("ไปที่หน้า "+ scene + " ไม่ได้");
//                System.err.println("ให้ตรวจสอบการกำหนด route");
//            }
//        }
//        else{
//            alert.setAlertType(Alert.AlertType.WARNING);
//            alert.setContentText("Username or password is incorrect.");
//            alert.show();
//        }
    }

    public void validation(){
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        ConnectionClass connectNow = new ConnectionClass();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM mydb.user WHERE Username = '" + username +
                "' AND password = '" + password + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1)==1) {
                    fxrouter.FXRouter.goTo("marketplace");
                } else {
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText("Username or password is incorrect.");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า marketplace ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleGoToCreatorBtn(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("creator");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า creator ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleGoToRegisterBtn(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
        public void handleGoToSuggestionBtn(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("suggestion");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า suggestion ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
