package com.example.isugroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SearchPageScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page_screen);

        ImageButton yourButton = (ImageButton) findViewById(R.id.BackToHome);

        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchPageScreen.this, HomeScreen.class));
            }
        });

        Button Search = (Button) findViewById(R.id.SearchButton);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Searchbar = (EditText)findViewById(R.id.SearchBar);
                String SearchVal = Searchbar.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(SearchPageScreen.this);
                String address = "http://coms-319-g22.cs.iastate.edu/club/search?phrase=<" +
                        SearchVal +
                        ">&page=<0>";

            }
            //TODO make the JSON request and fill text values
        });






    }
}