package com.example.connectfour;

import java.util.ArrayList;
import java.util.List;

public class ConnectFourGame {

    // Constants for the game
    public static final int ROW = 7;
    public static final int COL = 6;
    public static final int EMPTY = 0;
    public static final int BLUE = 1;
    public static final int RED = 2;

    // Game board and player tracking
    private int[][] board;
    private int currentPlayer;

    // List to store the winning cells
    private List<int[]> winningCells;

    // Constructor to initialize the board array
    public ConnectFourGame() {
        board = new int[ROW][COL];
        winningCells = new ArrayList<>();
        newGame();
    }

    // Method to reset the board and set the initial player
    public void newGame() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = EMPTY;
            }
        }
        currentPlayer = BLUE; // Blue starts
        winningCells.clear(); // Clear any previous winning cells
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

    // Method to set the state of the board from a saved state
    public void setState(String gameState) {
        int index = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = Character.getNumericValue(gameState.charAt(index++));
            }
        }
    }

    // Method to get the disc at a specific row and column
    public int getDisc(int row, int col) {
        return board[row][col];
    }

    // Method to select a disc (place it in the appropriate column)
    public boolean selectDisc(int row, int col) {
        if (col < 0 || col >= COL || board[0][col] != EMPTY) {
            return false; // Invalid move
        }

        for (int i = ROW - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY) {
                board[i][col] = currentPlayer;
                currentPlayer = (currentPlayer == BLUE) ? RED : BLUE; // Switch players
                return true;
            }
        }
        return false;
    }

    // Method to check if the game is over
    public boolean isGameOver() {
        return isWin() || isBoardFull();
    }

    // Method to check if the board is full
    private boolean isBoardFull() {
        for (int j = 0; j < COL; j++) {
            if (board[0][j] == EMPTY) {
                return false; // At least one column has space
            }
        }
        return true;
    }

    // Method to check for a win condition
    private boolean isWin() {
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    // Method to check for horizontal win
    private boolean checkHorizontal() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j <= COL - 4; j++) {
                if (board[i][j] != EMPTY &&
                        board[i][j] == board[i][j + 1] &&
                        board[i][j] == board[i][j + 2] &&
                        board[i][j] == board[i][j + 3]) {
                    winningCells.clear();
                    winningCells.add(new int[]{i, j});
                    winningCells.add(new int[]{i, j + 1});
                    winningCells.add(new int[]{i, j + 2});
                    winningCells.add(new int[]{i, j + 3});
                    return true;
                }
            }
        }
        return false;
    }

    // Method to check for vertical win
    private boolean checkVertical() {
        for (int i = 0; i <= ROW - 4; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j] != EMPTY &&
                        board[i][j] == board[i + 1][j] &&
                        board[i][j] == board[i + 2][j] &&
                        board[i][j] == board[i + 3][j]) {
                    winningCells.clear();
                    winningCells.add(new int[]{i, j});
                    winningCells.add(new int[]{i + 1, j});
                    winningCells.add(new int[]{i + 2, j});
                    winningCells.add(new int[]{i + 3, j});
                    return true;
                }
            }
        }
        return false;
    }

    // Method to check for diagonal wins
    private boolean checkDiagonal() {
        // Top-left to bottom-right
        for (int i = 0; i <= ROW - 4; i++) {
            for (int j = 0; j <= COL - 4; j++) {
                if (board[i][j] != EMPTY &&
                        board[i][j] == board[i + 1][j + 1] &&
                        board[i][j] == board[i + 2][j + 2] &&
                        board[i][j] == board[i + 3][j + 3]) {
                    winningCells.clear();
                    winningCells.add(new int[]{i, j});
                    winningCells.add(new int[]{i + 1, j + 1});
                    winningCells.add(new int[]{i + 2, j + 2});
                    winningCells.add(new int[]{i + 3, j + 3});
                    return true;
                }
            }
        }

        // Top-right to bottom-left
        for (int i = 0; i <= ROW - 4; i++) {
            for (int j = 3; j < COL; j++) {
                if (board[i][j] != EMPTY &&
                        board[i][j] == board[i + 1][j - 1] &&
                        board[i][j] == board[i + 2][j - 2] &&
                        board[i][j] == board[i + 3][j - 3]) {
                    winningCells.clear();
                    winningCells.add(new int[]{i, j});
                    winningCells.add(new int[]{i + 1, j - 1});
                    winningCells.add(new int[]{i + 2, j - 2});
                    winningCells.add(new int[]{i + 3, j - 3});
                    return true;
                }
            }
        }
        return false;
    }

    // Method to get the current player
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    // Method to get the winning cells
    public List<int[]> getWinningCells() {
        return winningCells;
    }
}
