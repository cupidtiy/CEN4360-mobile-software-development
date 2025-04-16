package com.example.connectfour;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class GameOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);

        // Get the RadioGroup for difficulty selection
        RadioGroup difficultyGroup = findViewById(R.id.radioGroup);

        // Set a listener to detect when a radio button is selected
        difficultyGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Find the selected RadioButton
            RadioButton selectedButton = findViewById(checkedId);
            String mode = selectedButton.getText().toString();

            // Prepare the result intent with the selected mode
            Intent resultIntent = new Intent();
            resultIntent.putExtra("gameMode", mode);

            // Set the result and return to MainActivity
            setResult(RESULT_OK, resultIntent);
            finish();  // Close this activity and return to the previous screen
        });
    }
}
