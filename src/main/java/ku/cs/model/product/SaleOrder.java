package ku.cs.model.product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SaleOrder {
    private int SO_ID;
    private LocalDateTime SO_Date;
    private int SO_Type;
    private int SO_Status;
    private int SO_Price;

    public SaleOrder(int SO_ID, LocalDateTime SO_Date, int SO_Type, int SO_Status, int SO_Price) {
        this.SO_ID = SO_ID;
        this.SO_Date = SO_Date;
        this.SO_Type = SO_Type;
        this.SO_Status = SO_Status;
        this.SO_Price = SO_Price;
    }

    public int getSO_ID() {
        return SO_ID;
    }

    public void setSO_ID(int SO_ID) {
        this.SO_ID = SO_ID;
    }

    public LocalDateTime getSO_Date() {
        return SO_Date;
    }

    public void setSO_Date(LocalDateTime SO_Date) {
        this.SO_Date = SO_Date;
    }

    public int getSO_Type() {
        return SO_Type;
    }

    public void setSO_Type(int SO_Type) {
        this.SO_Type = SO_Type;
    }

    public int getSO_Status() {
        return SO_Status;
    }

    public void setSO_Status(int SO_Status) {
        this.SO_Status = SO_Status;
    }

    public int getSO_Price() {
        return SO_Price;
    }

    public void setSO_Price(int SO_Price) {
        this.SO_Price = SO_Price;
    }

    public String formatHistory(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = SO_Date.format(format);
        return formatDateTime;
    }
}
