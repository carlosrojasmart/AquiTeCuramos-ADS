module com.ips.aquitecuramos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.ips.aquitecuramos to javafx.fxml;
    opens com.ips.aquitecuramos.models to javafx.base;
    exports com.ips.aquitecuramos;
    exports com.ips.aquitecuramos.Controllers;
    opens com.ips.aquitecuramos.Controllers to javafx.fxml;
    opens com.ips.aquitecuramos.Stores to javafx.base;
}
