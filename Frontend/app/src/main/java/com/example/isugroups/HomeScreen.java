package com.example.isugroups;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        GlobalVars.setPrevPage("HomeScreen.this");

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

        //Make a RequestQueue Object named "queue" and assign to "Volley.newRequestQueue(HomeScreen.this);"

        //The "queue" will take in HTTP messages(JsonObjectResponse), send them over the network, wait, get a response and then call a method

        //To enqueue an HTTP Request call "queue"."add("requestObject-here");"


        //lol so you actually have to construct the "request"
        //Here is an example WARNING
        JsonObjectRequest request = null; //new JsonObjectRequest();

        //Problem is it needs some arguments, Like is the request a Post or Get request???
        //What URL are we sending this message to?
        //We're also going to have to make 2 callback methods
        //One call back method for succeussful responses (200)
        //One call back method for erroneous responses (404,400,500)

        //The call back methods are called "Listeners" in java
    }

}
