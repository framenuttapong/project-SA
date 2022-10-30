package ku.cs.controllers.product;

import animatefx.animation.Tada;
import fxrouter.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.service.ConnectionClass;
import ku.cs.service.ThemeMode;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddProductController {
    @FXML private BorderPane borderAddProduct;
    @FXML private Circle productPicture;
    @FXML private TextField productNameTextField;
    @FXML private TextField productPriceTextField;
    @FXML private TextField quantityTextField;
    @FXML private Button btnAddProduct;
    @FXML private Button btnChangePicture;
    @FXML private ComboBox categoryComboBox;
    @FXML private AnchorPane pane;
    @FXML private Rectangle rectangleImage;

    private File imageFile;
    private String currentCategory = "-";
    private Alert alert;
    File selectedFile;
    private String newImageName;


    private String imageName;
    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
//        ThemeMode.setThemeMode(pane);
        categoryComboBox.getItems().addAll(
                "spices",
                "vegetables",
                "fruits"
        );
        System.out.println("initialize AddProductController");
    }

    @FXML
    public void handleAddProductBtn(ActionEvent actionEvent) throws SQLException {
//        DataSource<ProductList> productListDataSource = new ProductListFileDataSource();
        String name = productNameTextField.getText();
        int quantity = Integer.parseInt(quantityTextField.getText());
        int price = Integer.parseInt(productPriceTextField.getText());
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql ="INSERT INTO PRODUCT (P_Name,P_Quantity,P_Type,P_Price,P_Image)VALUES('"
                + name + "','" + quantity + "','" + currentCategory + "','" + price + "','" + imageName +"')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
//            if (imageFile==null) {
//                alert.setAlertType(Alert.AlertType.WARNING);
//                alert.setContentText("กรุณาเพิ่มรูปภาพสินค้า");
//                alert.show();
//            }
//            else if (currentCategory.equals("-")) {
//                alert.setAlertType(Alert.AlertType.WARNING);
//                alert.setContentText("กรุณาเลือกประเภทของสินค้า");
//                alert.show();
//            }
//            else if (name.trim().isBlank()) {
//                alert.setAlertType(Alert.AlertType.WARNING);
//                alert.setContentText("กรุณาใส่ชื่อสินค้า");
//                alert.show();
//            }
//            else if (detail.trim().isBlank()) {
//                alert.setAlertType(Alert.AlertType.WARNING);
//                alert.setContentText("กรุณาใส่รายละเอียดสินค้า");
//                alert.show();
//            }
//            else {
//                try {
//                    String productDetail = productDetailTextField.getText();
//                    double price = Double.parseDouble(productPriceTextField.getText());
//                    int quantity = Integer.parseInt(quantityTextField.getText());
//
//                    if (price>0&&quantity>0) {
//                        File dest = new File("data/product_image/" + (productList.findProductID()) + ".png");
//                        try {
//                            Files.copy(imageFile.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                        } catch (IOException e) {
//                            System.err.println("Image is not there");
//                        }
//                        Product product = new Product(name, productDetail, price, quantity, LocalDateTime.now(),
//                                productList.findProductID() + ".png", productList.findProductID(),
//                                0, 0, accountsList.getThisAccount().getUsername(), currentCategory);
//                        productList.addProduct(product);
//                        productListDataSource.writeData(productList);
//                        alert.setAlertType(Alert.AlertType.INFORMATION);
//                        alert.setContentText("ทำการเพิ่มสินค้าเสร็จสิ้น");
//                        alert.show();
//
//                        try {
//                            Info infoForPassing = new Info(accountsList, productList, info.getReviewList(),
//                                    info.getOrderList(), info.getReportList());
//                            FXRouter.goTo("store", infoForPassing);
//                        } catch (IOException e) {
//                            System.err.println("ไปที่หน้า store ไม่ได้");
//                            System.err.println("ให้ตรวจสอบการกำหนด route");
//                        }
//                    } else {
//                        alert.setAlertType(Alert.AlertType.WARNING);
//                        alert.setContentText("ไม่สามารถใส่ตัวเลขน้อยกว่า 0 ได้");
//                        alert.show();
//                    }
//                } catch (NumberFormatException e) {
//                    alert.setAlertType(Alert.AlertType.WARNING);
//                    alert.setContentText("กรุณาใส่ตัวเลขให้ถูกต้อง");
//                    alert.show();
//                }
//            }
    }

    @FXML
    public void handleBackToStoreBtn(MouseEvent actionEvent) {
        try {
            FXRouter.goTo("marketplace");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า marketplace ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML void handleUploadProductImageBtn(ActionEvent actionEvent) throws IOException {

//        if (actionEvent.getSource() == btnChangePicture) {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image", "*.jpg", "*.png"));
//            Stage stage = (Stage) borderAddProduct.getScene().getWindow();
//            selectedFile = fileChooser.showOpenDialog(stage);
//
//            if (selectedFile != null) {
//                LocalDateTime currentDateTime = LocalDateTime.now();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
//                String formattedDateTime = currentDateTime.format(formatter);
//                String fileName = selectedFile.toString();
//                int index = fileName.lastIndexOf('.');
//                String fileExtension = fileName.substring(index + 1);
//                newImageName = "-picture" + formattedDateTime + "." + fileExtension;
//                System.out.println(newImageName);
//                Image image = new Image("file:///" + selectedFile.getAbsolutePath());
//                productPicture.setFill(new ImagePattern(image));
//            }
//        }

        ///////////////////////
        FileChooser chooserImage = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Images","*.jpg", "*.png", "*.jpeg");
        chooserImage.getExtensionFilters().add(extFilter);
        imageFile = chooserImage.showOpenDialog(new Stage());
        if(imageFile != null) {
            String imageName = imageFile.toURI().toURL().toString();
            productPicture.setFill(new ImagePattern(new Image(imageName)));
            new Tada(productPicture).play();
            this.imageName = imageName;
        }
        /////////////////////////
//        FileChooser chooser = new FileChooser();
//        // SET FILECHOOSER INITIAL DIRECTORY
//        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
//        // DEFINE ACCEPTABLE FILE EXTENSION
//        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
//        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
//        Node source = (Node) actionEvent.getSource();
//        //เลือกรูป
//        File file = chooser.showOpenDialog(source.getScene().getWindow());
//        if (file != null) {
//            try {
//                // CREATE FOLDER IF NOT EXIST
//                File destDir = new File("images");
//                if (!destDir.exists()) destDir.mkdirs();
//                // RENAME FILE
//                String[] fileSplit = file.getName().split("\\.");
//                String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "."
//                        + fileSplit[fileSplit.length - 1];
//                Path target = FileSystems.getDefault().getPath(
//                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
//                );
//                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
//                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
//                // SET NEW FILE PATH TO IMAGE
//                String imageName = imageFile.toURI().toURL().toString();
//                rectangleImage.setFill(new ImagePattern(new Image(imageName)));
//                new Tada(rectangleImage).play();
//                imageName = destDir + "/" + filename;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        //////////////////////////////////
//        if (event.getSource() == btnChangePicture) {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image", "*.jpg", "*.png"));
//            Stage stage = (Stage) borderAddProduct.getScene().getWindow();
//            selectedFile = fileChooser.showOpenDialog(stage);
//
//            if (selectedFile != null) {
////                String fileName = selectedFile.toString();
////                int index = fileName.lastIndexOf('.');
////                String fileExtension = fileName.substring(index + 1);
////                newImageName = user.getNameShop() + "-picture" + "." + fileExtension;
//                System.out.println(newImageName);
//                Image image = new Image("file:///" + selectedFile.getAbsolutePath());
//                productPicture.setFill(new ImagePattern(image));
//            }
//        }

    @FXML
    public void chooseCategory(ActionEvent actionEvent) {
        if (categoryComboBox.getValue().equals("spices")) {
            currentCategory = "spices";
        }
        if (categoryComboBox.getValue().equals("vegetables")) {
            currentCategory = "vegetables";
        }
        if (categoryComboBox.getValue().equals("fruits")) {
            currentCategory = "fruits";
        }
    }

    public boolean isValidProductName(String productName) {
        return ((productName!= null)
                && (!productName.equals(""))
                && (productName.matches("^[a-zA-Z]{0,15}$")));
    }

    public boolean isValidPrice(String price) {
        return ((price!= null)
                && (!price.equals(""))
                && (price.matches("^[0-9]{7}$")));
    }

    public boolean isValidProductType(String productType) {
        return ((productType!= null)
                && (!productType.equals("-")));
    }
}
