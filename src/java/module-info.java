module com.example.casinoroyale {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    exports casinoroyale;
    opens casinoroyale to javafx.fxml;
}