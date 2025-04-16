package com.example.connectfour;

public class ConnectFourGame {

    // 2D array representing the board
    private int[][] board;

    // Constructor to initialize the board array
    public ConnectFourGame() {
        board = new int[7][6];
        newGame();
    }

    // Method to reset the board, initializing all elements to 0
    public void newGame() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = 0;
            }
        }
    }

    // Method to get the current state of the board as a String
    public String getState() {
        StringBuilder state = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                state.append(cell);
            }
        }
        return state.toString();
    }
}