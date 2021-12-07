package com.example.isugroups;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar appBar = (Toolbar)findViewById(R.id.appbar);
        appBar.setTitle("Home");
        setSupportActionBar(appBar);

        Button yourButton = (Button) findViewById(R.id.ToSearch);

        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, SearchPageScreen.class));
            }
        });

    }

}
