package edu.icet.tictactoegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Arrays;

public class HelloController {

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @FXML
    private Label lblStatus;

    private int playerTurn = 0;
    private ArrayList<Button> buttons;

    @FXML
    public void initialize() {

        buttons = new ArrayList<>(Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9));
    }

    @FXML
    void btnOnClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (!clickedButton.getText().isEmpty()) {
            return;
        }

        if (playerTurn == 0) {
            clickedButton.setText("X");
            clickedButton.setStyle("-fx-text-fill: blue; -fx-font-size: 24px;"); // X නිල් පාටින්
            lblStatus.setText("Player O Turn");
            playerTurn = 1;
        } else {
            clickedButton.setText("O");
            clickedButton.setStyle("-fx-text-fill: red; -fx-font-size: 24px;"); // O රතු පාටින්
            lblStatus.setText("Player X Turn");
            playerTurn = 0;
        }

        checkWinner();
    }

    @FXML
    void btnOnClickReset(ActionEvent event) {
        buttons.forEach(this::resetButton);
        lblStatus.setText("Player X Turn");
        playerTurn = 0;
    }

    private void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
        button.setStyle("-fx-font-size: 24px;"); // පාට අයින් කරනවා
    }

    private void checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> btn1.getText() + btn2.getText() + btn3.getText();
                case 1 -> btn4.getText() + btn5.getText() + btn6.getText();
                case 2 -> btn7.getText() + btn8.getText() + btn9.getText();
                case 3 -> btn1.getText() + btn4.getText() + btn7.getText();
                case 4 -> btn2.getText() + btn5.getText() + btn8.getText();
                case 5 -> btn3.getText() + btn6.getText() + btn9.getText();
                case 6 -> btn1.getText() + btn5.getText() + btn9.getText();
                case 7 -> btn3.getText() + btn5.getText() + btn7.getText();
                default -> null;
            };

            // X දිනුවාද?
            if ("XXX".equals(line)) {
                lblStatus.setText("X Won the Game!");
                endGame();
                return;
            }

            // O දිනුවාද?
            if ("OOO".equals(line)) {
                lblStatus.setText("O Won the Game!");
                endGame();
                return;
            }
        }
    }

    private void endGame() {
        buttons.forEach(button -> button.setDisable(true));
    }
}