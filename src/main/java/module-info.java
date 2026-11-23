module org.example.laberintos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.laberintos to javafx.fxml;
    exports org.example.laberintos;
}