package com.example.empal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView usernameId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameId = (TextView) findViewById(R.id.usernameId);

        Bundle extras = getIntent().getExtras();
        String username;

        if(extras != null){
            username = extras.getString("username");
            usernameId.setText("Hello, " +username);
        }
    }
}
