package ku.cs.controllers.manager;

import animatefx.animation.SlideInUp;
import fxrouter.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import ku.cs.controllers.product.ProductCheckStockController;
import ku.cs.model.product.Order;
import ku.cs.model.product.Product;
import ku.cs.service.ConnectionClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static ku.cs.controllers.login.LoginController.ACCOUNT;

public class CheckStockController {
    @FXML
    StackPane pane;
    @FXML private Label adminNameLabel;
    @FXML private AnchorPane anchorPane;
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
    public ArrayList<Order> orderList ;

    private ArrayList<Product> productList;
    private Product product;
    @FXML
    public void initialize() {
//        ThemeMode.setThemeMode(anchorPane);
        this.productList = queryCheckStockProduct();
        showProductsLessThan100();
        InterfaceManage();
        System.out.println("initialize CheckStockController");
    }

    private void showProductsLessThan100() {
        gridProduct.getChildren().clear();
        int column = 0;
        int row = 0;
        try {
            for (Product product : productList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/interfaces/product_check_stock.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProductCheckStockController productCheckStockController = fxmlLoader.getController();
                productCheckStockController.setData(product);
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

    private ArrayList<Product> queryCheckStockProduct(){
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connectDB =connectionClass.getConnection();
        String sql = "SELECT * FROM PRODUCT WHERE P_Quantity < 100 ;";
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

    private void InterfaceManage() {
        nameLabel.setText(ACCOUNT.getName());
        roleLabel.setText(ACCOUNT.getRole());
        btn_I.setText("Back");
        Image icon_addProduct = new Image(getClass().getResource("/ku/cs/icons/backButton.png").toExternalForm());
        iconButton_I.setImage(icon_addProduct);

        btn_II.setText("Import Stock");
        Image icon_import = new Image(getClass().getResource("/ku/cs/icons/newOrder.png").toExternalForm());
        iconButton_II.setImage(icon_import);

        btn_III.setVisible(false);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        // todo: Back to Marketplace
        if (event.getSource() == btn_I) {
            try {
                FXRouter.goTo("marketplace", 1000, 600);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า marketplace ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }

        if (event.getSource() == btn_II) {
            // todo: Import Stock
            if (btn_II.getText().equals("Import Stock")) {
                FXRouter.goTo("import_stock", product,750, 500);
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
