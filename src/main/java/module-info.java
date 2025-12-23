module edu.icet.tictactoegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.icet.tictactoegame to javafx.fxml;
    exports edu.icet.tictactoegame;
}