package ku.cs.controllers.market;

import animatefx.animation.SlideInUp;
import animatefx.animation.Tada;
import fxrouter.FXRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import ku.cs.controllers.product.ProductController;
import ku.cs.model.account.Account;
import ku.cs.model.product.Product;
import ku.cs.service.ConnectionClass;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class MarketController {
    @FXML private GridPane grid;
    @FXML private ScrollPane scroll;
    @FXML
    public void initialize() {
        Alert alert = new Alert(Alert.AlertType.NONE);
//        Account account = (Account) FXRouter.getData();
////        accountsList = info.getAccountList();
////        productList = info.getProductList();
////        currentProductList = productList.sortProductByLatest();
////        ThemeMode.setThemeMode(pane);
//        showUserData();
//        showProducts();
//        categoryComboBox.getItems().addAll(
//                "ประเภททั้งหมด",
//                "spices",
//                "vegetables",
//                "fruits"
//
//        );
        System.out.println("initialize MarketController");
    }
//
//    private void showUserData() {
//        ConnectionClass connectionClass= new ConnectionClass();
//        Connection connection =connectionClass.getConnection();
//        String customerName = "SELECT U_Name From USER WHERE Username = '" +
//        nameLabel.setText(accountsList.getThisAccount().getName());
//
//    }
private void showProducts(List<Product> products){
    grid.getChildren().clear();
    int column = 0;
    int row = 1;
    scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scroll.setPannable(true);
    try {
        for (Product product : products) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/product.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            ProductController productController = fxmlLoader.getController();
            productController.setData(product);
            if (column == 4) {
                column = 0;
                ++row;
            }
            grid.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(9.5));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    new SlideInUp(grid).play();
}
}
