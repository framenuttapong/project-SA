package ku.cs.model.product;

import java.time.LocalDateTime;

public class Lot {
    private int L_Date;
    private int P_ID;
    private LocalDateTime L_Exp;
    private int L_Quantity;

    public Lot(int l_Date, int p_ID, LocalDateTime l_Exp, int l_Quantity) {
        L_Date = l_Date;
        P_ID = p_ID;
        L_Exp = l_Exp;
        L_Quantity = l_Quantity;
    }

    public int getL_Date() {
        return L_Date;
    }

    public void setL_Date(int l_Date) {
        L_Date = l_Date;
    }

    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int p_ID) {
        P_ID = p_ID;
    }

    public LocalDateTime getL_Exp() {
        return L_Exp;
    }

    public void setL_Exp(LocalDateTime l_Exp) {
        L_Exp = l_Exp;
    }

    public int getL_Quantity() {
        return L_Quantity;
    }

    public void setL_Quantity(int l_Quantity) {
        L_Quantity = l_Quantity;
    }
}
