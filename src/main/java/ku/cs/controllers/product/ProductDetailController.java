package ku.cs.controllers.product;

import fxrouter.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.model.product.Product;
import ku.cs.service.ConnectionClass;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ku.cs.controllers.login.LoginController.ACCOUNT;

public class ProductDetailController {

    @FXML private Button btnBuy;
    @FXML private Button btnPreorder;
    @FXML private Circle btnClose;
    @FXML private Circle productPicture;
    @FXML private ImageView btnBack;
    @FXML private Label NameLabel;
    @FXML private TextField nameLabel;
    @FXML private TextField priceLabel;
    @FXML private TextField quantityLabel;
    @FXML private TextField quantityTextField;
    @FXML private TextField productTypeLabel;

    private Product product = (Product) FXRouter.getData();


    private Alert alert;
    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        this.product = queryProduct();
        showProductDetail();
        System.out.println("initialize ProductDetailController");
    }

    public void showProductDetail() {
        NameLabel.setText(product.getP_Name());
        nameLabel.setText(product.getP_Name());
        nameLabel.setEditable(false);
//        priceLabel.setText(String.valueOf(product.getP_Price()));
        priceLabel.setText(product.getP_Price() + " ฿/kg.");
        priceLabel.setEditable(false);
//        quantityLabel.setText(String.valueOf(product.getP_Quantity()));
        quantityLabel.setText(product.getP_Quantity() + " kg.");
        quantityLabel.setEditable(false);
        productTypeLabel.setText(product.getP_Type());
        productTypeLabel.setEditable(false);
        File f = new File("images/"+product.getP_Image());
        productPicture.setFill(new ImagePattern(new Image("file:///" + f.getAbsolutePath())));
    }

    private Product queryProduct(){
        ConnectionClass connectNow = new ConnectionClass();
        Connection connectDB = connectNow.getConnection();

        String products = "SELECT * FROM mydb.product WHERE P_ID = '" + product.getP_ID() + "';";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(products);

            while (queryResult.next()) {
                int p_id = Integer.parseInt(queryResult.getString(1));
                String p_name = queryResult.getString(2);
                int p_quantity = Integer.parseInt(queryResult.getString(3));
                String p_type = queryResult.getString(4);
                int p_price = Integer.parseInt(queryResult.getString(5));
                String p_image = queryResult.getString(6);

                Product product1 = new Product(p_id, p_name, p_quantity, p_type, p_price, p_image);
                return product1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean checkQuantity(int amount){
        if (amount > this.product.getP_Quantity()){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("สินค้าไม่เพียงพอ");
            alert.show();
            btnBuy.setVisible(false);
            btnPreorder.setVisible(true);
            btnPreorder.toFront();
            return false;
        }
        return true;
    }

    public int totalPrice(int amount){
        return this.product.getP_Price()*amount;
    }

    private void queryOrder() throws SQLException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String op_quantity = quantityTextField.getText();
        ConnectionClass connectNow = new ConnectionClass();
        Connection connectDB = connectNow.getConnection();

        String orderSQL = "INSERT INTO ORDER_PRODUCT (OP_Quantity, OP_Price, P_ID, OP_Date, OP_Type, OP_Status, Username)VALUES('"
                + op_quantity + "','" + totalPrice(Integer.parseInt(op_quantity)) + "','" + product.getP_ID() + "','"
                + formattedDateTime + "','" + "0" + "','" + "0" + "','" + ACCOUNT.getUsername() + "')";
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(orderSQL);
    }


    public boolean isValidQuantity(String quantity) {
        return ((quantity!= null)
                && (!quantity.equals(""))
                && (quantity.matches("^[0-9]{1,5}$")));
    }

    private void cutStock(int quantity,int op_quantity){
        ConnectionClass connectNow = new ConnectionClass();
        Connection connectDB = connectNow.getConnection();

        String updateQuantity = "UPDATE PRODUCT SET P_Quantity = '" + quantity + "'-'" + op_quantity + "' WHERE P_ID = '"
                + product.getP_ID() + "';";
        try {
            PreparedStatement statement = connectDB.prepareStatement(updateQuantity);
            statement.executeUpdate();
            System.out.println("cut stock complete!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void confirmCutStock(){
        int quantity = product.getP_Quantity();
        int op_quantity = Integer.parseInt(quantityTextField.getText());
        System.out.println("product : " + quantity
                + " order : " + op_quantity);
        cutStock(quantity,op_quantity);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // todo: Button Buy
        if (event.getSource() == btnBuy) {
            String op_quantity = quantityTextField.getText();
            try {
                if (!isValidQuantity(op_quantity) || Integer.parseInt(op_quantity) <= 0) {
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Quantity");
                    alert.show();
                } else if (checkQuantity(Integer.parseInt(op_quantity))) {
                    queryOrder();
                    confirmCutStock();
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText("สั่งซื้อสำเร็จ");
                    alert.show();
                    FXRouter.goTo("marketplace", 1000, 600);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // todo: Buy Pre-Order
        if (event.getSource() == btnPreorder) {
            try {
                FXRouter.goTo("preorder",product, 750, 500);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า preorder ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }
    @FXML
    private void handleMouseEvent(MouseEvent event) {

        // todo: Close Program
        if (event.getSource() == btnClose) {
            System.exit(0);
        }

        // todo: Back To Marketplace
        if (event.getSource() == btnBack) {
            try {
                FXRouter.goTo("marketplace",1000, 600);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า marketplace ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }
}
