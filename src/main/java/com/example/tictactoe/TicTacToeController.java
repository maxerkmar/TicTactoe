package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class TicTacToeController {
    private TicTacToeModel model;
    @FXML
    private GridPane gridPane;
    private int xWins;
    private int oWins;
    /*@FXML
    private Button Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9;
     */
    public TicTacToeController() {
        model = new TicTacToeModel();
        xWins = 0;
        oWins = 0;
    }
    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().isEmpty()) {
            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);
            if (model.makeMove(row, col)) {
                clickedButton.setText(String.valueOf(model.getCurrentPlayer()));
                model.switchPLayer();
            }
        }
        checkResult();
    }

    private void checkResult(){
        if (model.checkForWinner()) announceWinner();
        else if (model.checkForTie()) announceTie();

        if(model.checkForWinner() || model.checkForTie()) {
            clearBoard();
            model = new TicTacToeModel();
        }
    }
    private void clearBoard() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button button) {
                button.setText("");
            }
        }
    }
    private void announceWinner() {
        char winner = model.getCurrentPlayer() == 'X' ? 'O' : 'X';
        updateWinCount(winner);
        displayWinnerAlert(winner);
    }
    private void updateWinCount(char winner) {
        if (winner == 'X') xWins++;
        else if (winner == 'O') oWins++;
    }
    private void displayWinnerAlert(char winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TicTacToe");
        alert.setHeaderText(null);
        alert.setContentText("Player " + winner + " wins!\nX score: " + xWins + "\nO score: " + oWins);
        alert.showAndWait();
    }
    private void announceTie(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TicTacToe");
        alert.setHeaderText(null);
        alert.setContentText("Its a tie!");
        alert.showAndWait();
    }

}