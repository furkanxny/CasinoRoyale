module app.casinoroyale {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    exports app.casinoroyale;
    exports app.casinoroyale.Controller to javafx.fxml;
    opens app.casinoroyale to javafx.fxml;
}
