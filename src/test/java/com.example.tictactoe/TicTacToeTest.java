package com.example.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {
    private TicTacToeModel model;

    @BeforeEach
    public void setup() {
        model = new TicTacToeModel();
    }

    @Test
    @Order(1)
    @DisplayName("First player is X")
    public void InitialPlayerIsX() {
        assertEquals('X', model.getCurrentPlayer());
    }

    @Test
    @Order(2)
    @DisplayName("Switching player")
    public void PlayerSwitching() {
        model.switchPLayer();
        assertEquals('O', model.getCurrentPlayer());
        model.switchPLayer();
        assertEquals('X', model.getCurrentPlayer());
    }

    @Test
    @Order(3)
    @DisplayName("Check for winner 3 in rows")
    public void CheckForWinnerRows() {
        char[][] testState = {
                {'X', 'X', 'X'},
                {'O', 'O', '\u0000'},
                {'\u0000', '\u0000', '\u0000'}
        };
        model.setButtons(testState);
        assertTrue(model.checkForWinner());
    }

    @Test
    @Order(4)
    @DisplayName("Check for winner 3 in columns")
    public void CheckForWinnerColumns() {
        char[][] testState = {
                {'X', 'O', '\u0000'},
                {'X', 'O', '\u0000'},
                {'X', 'X', 'O'}
        };
        model.setButtons(testState);
        assertTrue(model.checkForWinner());
    }

    @Test
    @Order(5)
    @DisplayName("Check for winner 3 in diagonals")
    public void CheckForWinnerDiagonals() {
        char[][] testState = {
                {'X', 'O', '\u0000'},
                {'O', 'X', '\u0000'},
                {'\u0000', 'O', 'X'}
        };
        model.setButtons(testState);
        assertTrue(model.checkForWinner());
    }

    @Test
    @Order(6)
    @DisplayName("Check for tie")
    public void CheckForTie() {
        char[][] testState = {
                {'X', 'O', 'X'},
                {'X', 'O', 'O'},
                {'O', 'X', 'X'}
        };
        model.setButtons(testState);
        assertTrue(model.checkForTie());
    }
}
