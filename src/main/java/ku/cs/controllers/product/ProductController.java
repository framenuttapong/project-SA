package ku.cs.controllers.product;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ku.cs.model.product.Product;
import ku.cs.service.ConnectionClass;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductController {
    @FXML private Label productNameLabel;
    @FXML private Label priceLabel;
    @FXML private Rectangle productImage;

    private Product product;

    public void setData(Product product) {
        String id = String.valueOf(product.getP_ID());
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connectDB =connectionClass.getConnection();
        String sql = "SELECT P_Name FROM mydb.product WHERE P_ID = '" + id + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);

            while (queryResult.next()) {
                if (queryResult.getInt(1)==0) {

                } else {

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        File f = new File("data/product_image/"+product.getID()+".png");
//        productImage.setFill(new ImagePattern(new Image("file:///" + f.getAbsolutePath())));
//        productImage.setArcHeight(15);
//        productImage.setArcWidth(15);
//        this.product = product;
//        productNameLabel.setText(product.getProductName());
//        priceLabel.setText(product.getPrice()+"฿");
    }

//    @FXML
//    private void handleMouseEvent(MouseEvent event) {
//        System.out.println("goTo = " + product.getProductName() + ": " + product.getPrice());
//        productList.setThisProduct(product);
//        try {
//            FXRouter.goTo("product_detail", info);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

}
