module app.casinoroyale {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    exports app.casinoroyale;
    opens app.casinoroyale to javafx.fxml;
}