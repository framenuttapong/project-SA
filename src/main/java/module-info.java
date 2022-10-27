module ku.cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires java.sql;

    opens ku.cs to javafx.fxml;
    exports ku.cs;

    exports ku.cs.controller.register;
    opens ku.cs.controller.register to javafx.fxml;

    exports ku.cs.controller.login;
    opens ku.cs.controller.login to javafx.fxml;

}
