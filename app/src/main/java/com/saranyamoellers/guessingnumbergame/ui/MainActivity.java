package com.saranyamoellers.guessingnumbergame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saranyamoellers.guessingnumbergame.R;

public class MainActivity extends AppCompatActivity {

    // declare member variables
    private EditText nameField;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText) findViewById(R.id.nameEditText);
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();
                //Toast.makeText(MainActivity.this, name , Toast.LENGTH_LONG).show();
                startGame(name);
            }
        });
    }

    //Once clicked back to main activity the EditText field is set to empty
    @Override
    protected void onResume() {
        super.onResume();
        nameField.setText("");
    }

    private void startGame(String name) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
