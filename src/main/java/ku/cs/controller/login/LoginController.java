package ku.cs.controller.login;

import animatefx.animation.Flash;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import fxrouter.FXRouter;
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

//        accountsList = new AccountList();
//        productList = new ProductList();
//        reviewList = new ReviewList();
//        orderList = new OrderList();
//        reportList = new ReportList();
//
//        imageDirectory = new ImageDirectory();
//
//        accountListDataSource = new AccountListFileDataSource();
//        productListDataSource = new ProductListFileDataSource();
//        reviewListDataSource = new ReviewListFileDataSource();
//        orderListDataSource = new OrderListFileDataSource();
//        reportListDataSource = new ReportListFileDataSource();
//
//        accountsList = accountListDataSource.getData();
//        productList = productListDataSource.getData();
//        reviewList = reviewListDataSource.getData();
//        orderList = orderListDataSource.getData();
//        reportList = reportListDataSource.getData();
//
//        if (!accountsList.isExistUsername("admin")) {
//            accountsList.addAccount(new Account("Admin","admin", "admin", "admin"));
//        }
//        accountListDataSource.writeData(accountsList);
//        ThemeMode.setThemeMode(pane);
//        System.out.println("initialize LoginController");
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
        if(!username.isEmpty() && !password.isEmpty()){
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
                    fxrouter.FXRouter.goTo("register");
                } else {
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText("Username or password is incorrect.");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า "+ "register" + " ไม่ได้");
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
