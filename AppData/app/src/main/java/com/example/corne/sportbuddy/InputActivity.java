package com.example.corne.sportbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button button = findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView = findViewById(R.id.editText);
                String input = textView.getText().toString();
                Log.e("Developer", input);

                Intent intent = new Intent(InputActivity.this, ViewActivity.class);
                intent.putExtra("Input", input);
                startActivity(intent);
            }
        });

    }


}
