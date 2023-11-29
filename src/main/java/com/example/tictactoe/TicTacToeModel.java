package com.example.tictactoe;

public class TicTacToeModel {
    private char[][] buttons;
    private char currentPlayer;


    public TicTacToeModel(){
        buttons = new char[3][3];
        currentPlayer = 'X';
    }
    public char[][] getButtons() {return buttons;}

    public char getCurrentPlayer(){
        return currentPlayer;
    }

    public void setButtons(char[][] buttons) {this.buttons = buttons;}

    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchPLayer(){
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean makeMove(int row, int col) {
        if (buttons[row][col] == '\u0000') {
            buttons[row][col] = currentPlayer;
            return true;
        }
        return false;
    }
    public boolean checkForWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    public boolean checkForTie(){
        for (int rows = 0; rows < 3; rows++) {
            for (int col = 0; col < 3; col++)
                if (buttons[rows][col] == '\u0000') return false;
        }
        return true;
    }

    private boolean checkRows() {
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0] != '\u0000' &&
                    buttons[row][0] == buttons[row][1] &&
                    buttons[row][0] == buttons[row][2]) return true;
        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col] != '\u0000' &&
                    buttons[0][col] == buttons[1][col] &&
                    buttons[0][col] == buttons[2][col]) return true;
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (buttons[0][0] != '\u0000' && buttons[0][0] == buttons[1][1] && buttons[0][0] == buttons[2][2]) ||
                (buttons[0][2] != '\u0000' && buttons[0][2] == buttons[1][1] && buttons[0][2] == buttons[2][0]);
    }



}
