package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.IllegalFormatCodePointException;
import java.util.Random;

public class TicTacToeController {
    private TicTacToeModel model;
    @FXML
    private GridPane gridPane;
    private int xWins;
    private int oWins;
    private final Random random;

    public TicTacToeController() {
        model = new TicTacToeModel();
        xWins = 0;
        oWins = 0;
        random = new Random();
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
                computerMove();
            }
        }
        checkResult();
    }

    private void computerMove() {
        int emptyButtonCount = countEmptyButtons();
        if (emptyButtonCount == 0) {return;}
        int computerRow, computerCol;
        do {
            computerRow = random.nextInt(3);
            computerCol = random.nextInt(3);
        } while (!model.makeMove(computerRow, computerCol));
        updateButton(computerRow, computerCol);
        model.switchPLayer();
    }

    private int countEmptyButtons() {
        int emptyButtonCount = 0;
        char[][] buttons = model.getButtons();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) if (buttons[row][col] == '\u0000') emptyButtonCount++;
        }
        return emptyButtonCount;
    }

    private void updateButton(int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                Button computer = (Button) node;
                computer.setText(String.valueOf(model.getCurrentPlayer()));
                break;
            }
        }
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
            Button button = (Button) node;
            button.setText("");
        }
    }

    private void announceWinner() {
        char winner = model.getCurrentPlayer();
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