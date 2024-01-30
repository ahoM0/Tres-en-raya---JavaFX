module com.mycompany.tres.en.raya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.tres.en.raya to javafx.fxml;
    exports com.mycompany.tres.en.raya;
}
