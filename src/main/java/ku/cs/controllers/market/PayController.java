package ku.cs.controllers.market;

import ku.cs.service.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayController {
//    private void cutStock(int quantity,int op_quantity){
//        ConnectionClass connectNow = new ConnectionClass();
//        Connection connectDB = connectNow.getConnection();
//
//        String updateQuantity = "UPDATE PRODUCT SET P_Quantity = '" + quantity + "'-'" + op_quantity + "' WHERE P_ID = '"
//                + product.getP_ID() + "';";
//        try {
//            PreparedStatement statement = connectDB.prepareStatement(updateQuantity);
//            statement.executeUpdate();
//            System.out.println("cut stock complete!!!");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void confirmCutStock(){
//        int quantity = product.getP_Quantity();
//        int op_quantity = Integer.parseInt(quantityTextField.getText());
//        cutStock(quantity,op_quantity);
//    }
}
