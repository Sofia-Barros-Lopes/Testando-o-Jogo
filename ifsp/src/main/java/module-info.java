module jogomemoria {
    requires javafx.controls;
    requires javafx.fxml;

    opens jogomemoria to javafx.fxml;
    exports jogomemoria;
}
