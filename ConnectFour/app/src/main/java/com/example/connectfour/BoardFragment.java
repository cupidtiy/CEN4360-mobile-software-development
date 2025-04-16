package com.example.connectfour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;  // Use android.widget.GridLayout
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BoardFragment extends Fragment {

    // Constant and variables for the fragment
    private final String GAME_STATE = "gameState";
    private ConnectFourGame mGame;
    private GridLayout mGrid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_board layout
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        // Initialize GridLayout and ConnectFourGame
        mGrid = view.findViewById(R.id.boardGrid);
        mGame = new ConnectFourGame();

        // Set click listeners for each button in the grid
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            View button = mGrid.getChildAt(i);
            button.setOnClickListener(this::onButtonClick);
        }

        return view;
    }

    // Stub method for button click handling
    private void onButtonClick(View view) {
        // Game logic
    }

    // Save game state when instance is saved
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }
}