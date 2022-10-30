package ku.cs.model.product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product {
    private int P_ID;
    private String P_Name;
    private int P_Price;
    private int P_Quantity;
    private String P_Image;
    private String P_Type;

    public Product(int p_ID, String p_Name, int p_Price, int p_Quantity, String p_Image, String p_Type) {
        this.P_ID = p_ID;
        this.P_Name = p_Name;
        this.P_Price = p_Price;
        this.P_Quantity = p_Quantity;
        this.P_Image = p_Image;
        this.P_Type = p_Type;
    }

    public Product() {}

    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int p_ID) {
        P_ID = p_ID;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }

    public int getP_Price() {
        return P_Price;
    }

    public void setP_Price(int p_Price) {
        P_Price = p_Price;
    }

    public int getP_Quantity() {
        return P_Quantity;
    }

    public void setP_Quantity(int p_Quantity) {
        P_Quantity = p_Quantity;
    }

    public String getP_Image() {
        return P_Image;
    }

    public void setP_Image(String p_Image) {
        P_Image = p_Image;
    }

    public String getP_Type() {
        return P_Type;
    }

    public void setP_Type(String p_Type) {
        P_Type = p_Type;
    }

    public void addQuantity(int add) {
        if (add>0) {
            this.P_Quantity += add;
        }
    }

    public void decreaseQuantity(int quantity) {
            this.P_Quantity = this.P_Quantity - quantity;
        }

}
