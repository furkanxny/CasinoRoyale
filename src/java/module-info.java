module com.example.casinoroyale {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.casinoroyale to javafx.fxml;
    exports casinoroyale;
    opens casinoroyale to javafx.fxml;
}