package com.example.connectfour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class BoardFragment extends Fragment {

    // Constant for saving game state
    private final String GAME_STATE = "gameState";

    // Member variables
    private ConnectFourGame mGame;
    private GridLayout mGrid;
    private WinningLineView mWinningLineView;
    private boolean isGameOver = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_board layout
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        // Initialize the GridLayout and WinningLineView
        mGrid = view.findViewById(R.id.boardGrid);
        mWinningLineView = view.findViewById(R.id.winningLineView);
        mGame = new ConnectFourGame();

        // Set click listeners for each button in the grid
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            View button = mGrid.getChildAt(i);
            button.setOnClickListener(this::onButtonClick);
        }

        // Handle saved instance state
        if (savedInstanceState == null) {
            startGame();
        } else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setDisc();
        }

        return view;
    }

    private void onButtonClick(View view) {
        if (isGameOver) {
            Toast.makeText(getActivity(), "Game over! Press 'Play Connect Four' to start a new game.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Find the button’s row and column
        int buttonIndex = mGrid.indexOfChild(view);
        int row = buttonIndex / ConnectFourGame.COL;
        int col = buttonIndex % ConnectFourGame.COL;

        // Make the move in ConnectFourGame
        if (!mGame.selectDisc(row, col)) {
            Toast.makeText(getActivity(), "Invalid move! Try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the visuals
        setDisc();

        // Check if the game is over
        if (mGame.isGameOver()) {
            isGameOver = true;
            if (mGame.getWinningCells().size() > 0) {
                Toast.makeText(getActivity(), "Player " + ((mGame.getCurrentPlayer() == ConnectFourGame.RED) ? "Blue" : "Red") + " wins!", Toast.LENGTH_SHORT).show();
                drawWinningLine();
            } else {
                Toast.makeText(getActivity(), "It's a draw!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startGame() {
        mGame.newGame();
        isGameOver = false; // Reset game state
        setDisc();
        mWinningLineView.clearLine(); // Clear any previous winning line
    }

    private void setDisc() {
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            View gridButton = mGrid.getChildAt(i);

            // Find the button’s row and column
            int row = i / ConnectFourGame.COL;
            int col = i % ConnectFourGame.COL;

            // Get the disc color at this position
            int disc = mGame.getDisc(row, col);

            // Update the button background based on the disc color
            if (disc == ConnectFourGame.BLUE) {
                gridButton.setBackgroundResource(R.drawable.circle_blue);
            } else if (disc == ConnectFourGame.RED) {
                gridButton.setBackgroundResource(R.drawable.circle_red);
            } else {
                gridButton.setBackgroundResource(R.drawable.circle_white);
            }
        }
    }

    private void drawWinningLine() {
        List<int[]> winningCells = mGame.getWinningCells();

        if (winningCells.size() < 4) {
            return; // No valid winning line
        }

        // Calculate start and end positions
        int[] startCell = winningCells.get(0);
        int[] endCell = winningCells.get(winningCells.size() - 1);

        View startButton = mGrid.getChildAt(startCell[0] * ConnectFourGame.COL + startCell[1]);
        View endButton = mGrid.getChildAt(endCell[0] * ConnectFourGame.COL + endCell[1]);

        // Get local coordinates relative to the parent (GridLayout)
        float startX = startButton.getX() + (startButton.getWidth() / 2.0f);
        float startY = startButton.getY() + (startButton.getHeight() / 2.0f);
        float endX = endButton.getX() + (endButton.getWidth() / 2.0f);
        float endY = endButton.getY() + (endButton.getHeight() / 2.0f);

        // Apply downward offset to Y values
        float verticalOffset = startButton.getHeight() * 6.3f; // Adjust to bring the line down
        startY += verticalOffset;
        endY += verticalOffset;

        // Extend the line slightly outward
        float extension = startButton.getWidth() * 0.2f; // Extend by 20% of button width
        if (startX < endX) { // Horizontal or diagonal line extending right
            startX -= extension;
            endX += extension;
        } else if (startX > endX) { // Horizontal or diagonal line extending left
            startX += extension;
            endX -= extension;
        }
        if (startY < endY) { // Vertical or diagonal line extending downward
            startY -= extension;
            endY += extension;
        } else if (startY > endY) { // Vertical or diagonal line extending upward
            startY += extension;
            endY -= extension;
        }

        // Draw the line on WinningLineView
        mWinningLineView.setLine(startX, startY, endX, endY);
    }






    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }
}
