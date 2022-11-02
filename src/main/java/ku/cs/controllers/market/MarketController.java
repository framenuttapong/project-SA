package ku.cs.controllers.market;

import animatefx.animation.SlideInUp;
import fxrouter.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import ku.cs.controllers.product.ProductController;
import ku.cs.model.product.Product;
import ku.cs.service.ConnectionClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static ku.cs.controllers.login.LoginController.ACCOUNT;

public class MarketController {

    @FXML private Button btn_I;
    @FXML private Button btn_II;
    @FXML private Button btn_III;
    @FXML private Button btnSignOut;
    @FXML private Circle btnClose;
    @FXML private GridPane gridProduct;
    @FXML private ImageView iconButton_I;
    @FXML private ImageView iconButton_II;
    @FXML private ImageView iconButton_III;
    @FXML private Label nameLabel;
    @FXML private Label roleLabel;
    public ArrayList<Product> productList ;


    @FXML public void initialize() {

        Alert alert = new Alert(Alert.AlertType.NONE);
        this.productList = queryAllProduct();
//        ThemeMode.setThemeMode(pane);

        showProducts();
        InterfaceManage(ACCOUNT.getRole());

        System.out.println("initialize MarketController");
    }

    private ArrayList<Product> queryAllProduct(){
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connectDB =connectionClass.getConnection();
        String sql = "SELECT P_ID,P_Name,P_Quantity,P_Type,P_Price,P_Image FROM mydb.product;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);

            while (queryResult.next()) {
                int p_id = Integer.parseInt(queryResult.getString(1));
                String p_name = queryResult.getString(2);
                int p_quantity = Integer.parseInt(queryResult.getString(3));
                String p_type = queryResult.getString(4);
                int p_price = Integer.parseInt(queryResult.getString(5));
                String p_image = queryResult.getString(6);
                Product product = new Product(p_id, p_name, p_quantity, p_type, p_price, p_image);
                productArrayList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productArrayList;
    }

    private void showProducts() {
        gridProduct.getChildren().clear();
        int column = 0;
        int row = 0;
        try {
            for (Product product : productList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/interfaces/product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProductController productController = fxmlLoader.getController();
                productController.setData(product);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                gridProduct.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(7.5));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        new SlideInUp(gridProduct).play();
    }

    private void InterfaceManage(String role) {

        nameLabel.setText(ACCOUNT.getName());
        roleLabel.setText(ACCOUNT.getRole());

        // todo: Administrator
        if (role.equals("Admin")) {
            roleLabel.setVisible(false);

            btn_I.setText("Create User");
            Image icon_createUser = new Image(getClass().getResource("/ku/cs/icons/user.png").toExternalForm());
            iconButton_I.setImage(icon_createUser);

            btn_II.setText("Add Product");
            Image icon_addProduct = new Image(getClass().getResource("/ku/cs/icons/add_product.png").toExternalForm());
            iconButton_II.setImage(icon_addProduct);

            btn_III.setVisible(false);
        }

        // todo: Manager
        if (role.equals("manager") || role.equals("Manager")) {

            btn_I.setText("Create Employee");
            Image icon_createEmployee = new Image(getClass().getResource("/ku/cs/icons/employee.png").toExternalForm());
            iconButton_I.setImage(icon_createEmployee);

            btn_II.setText("Add Product");
            Image icon_addProduct = new Image(getClass().getResource("/ku/cs/icons/add_product.png").toExternalForm());
            iconButton_II.setImage(icon_addProduct);

            btn_III.setVisible(false);
        }

        // todo: Employee
        if (role.equals("employee") || role.equals("Employee")) {

            btn_I.setText("Add Product");
            Image icon_addProduct = new Image(getClass().getResource("/ku/cs/icons/add_product.png").toExternalForm());
            iconButton_I.setImage(icon_addProduct);

            btn_II.setVisible(false);

            btn_III.setVisible(false);
        }

        // todo: Customer
        if (role.equals("customer") || role.equals("Customer")) {
            btn_I.setText("Purchase Orders");
            Image icon_addProduct = new Image(getClass().getResource("/ku/cs/icons/receipt.png").toExternalForm());
            iconButton_I.setImage(icon_addProduct);

            btn_II.setVisible(false);
            btn_III.setVisible(false);
        }
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == btn_I) {
            // todo: Create User
            if (btn_I.getText().equals("Create User")) {
                FXRouter.goTo("create_user", 750, 500);
            }

            // todo: Create Employee
            if (btn_I.getText().equals("Create Employee")) {
                FXRouter.goTo("create_employee", 750, 500);
            }

            // todo: Purchase Orders
            if (btn_I.getText().equals("Purchase Orders")) {
                FXRouter.goTo("purchase_orders", 1000, 600);
            }

        }

        if (event.getSource() == btn_II) {
            // todo: Add Product
            if (btn_II.getText().equals("Add Product")) {
                FXRouter.goTo("add_product", 750, 500);
            }
        }

        // todo: Sign Out
        if (event.getSource().equals(btnSignOut)) {
            FXRouter.goTo("login", 750, 500);
        }
}
    @FXML
    private void handleMouseEvent(MouseEvent event) {

        // todo: Close Program
        if (event.getSource() == btnClose) {
            System.exit(0);
        }
    }
}
