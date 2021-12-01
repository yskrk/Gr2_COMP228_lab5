module com.example.group2_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.sql.rowset;

    opens com.example.group2_comp228lab5 to javafx.fxml;
    exports com.example.group2_comp228lab5;
}