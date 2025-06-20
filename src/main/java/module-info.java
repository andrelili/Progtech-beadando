module com.example.sorozatok {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.sorozatok to javafx.fxml;
    exports com.example.sorozatok;
    exports com.example.sorozatok.model;
    exports com.example.sorozatok.strategy;
    opens com.example.sorozatok.strategy to javafx.fxml;
}
