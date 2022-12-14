package com.example.isugroups;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {
    private JSONArray joinedClubs;
    private ImageButton[] clubButtons;

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
        Button yourButton2 = (Button) findViewById(R.id.ToCreateClub);

        yourButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, CreateClub.class));
            }
        });

        //Make a RequestQueue Object named "queue" and assign to "Volley.newRequestQueue(HomeScreen.this);"
        RequestQueue queue = Volley.newRequestQueue(HomeScreen.this);
        //The "queue" will take in HTTP messages(JsonObjectResponse), send them over the network, wait, get a response and then call a method

        //To enqueue an HTTP Request call "queue"."add("requestObject-here");"
        String address = GlobalVars.VirtualUrl + "/club/joined?username=" +
                GlobalVars.getCurUserID();
        JsonArrayRequest userClubs = new JsonArrayRequest(Request.Method.GET, address, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                joinedClubs = response;
                Log.i("Response: " , response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        addClub(response.getJSONObject(i));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            //lol so you actually have to construct the "request"
            //Here is an example WARNING
            //JsonObjectRequest request = null; //new JsonObjectRequest();

            //Problem is it needs some arguments, Like is the request a Post or Get request???
            //What URL are we sending this message to?
            //We're also going to have to make 2 callback methods
            //One call back method for succeussful responses (200)
            //One call back method for erroneous responses (404,400,500)

            //The call back methods are called "Listeners" in java
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(userClubs);

        String address2 = GlobalVars.VirtualUrl + "/club/joined/notifications?page=0";
        JsonArrayRequest userNotificatons = new JsonArrayRequest(Request.Method.GET, address2, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                Log.i("Response: " , response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        addNotif(response.getJSONObject(i));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("Authorization", GlobalVars.getCurUserID() + ":" + GlobalVars.getUserPassphrase());
            return params;
        }};
        queue.add(userNotificatons);

    }

    //Adds a button to the "My Clubs" scroll region with the information about the club.
    //Not sure how to link what club was tapped so the club details screen displays the correct data
    private void addClub(JSONObject clubToAdd){
        Toolbar.LayoutParams lparams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        ViewGroup layout = (ViewGroup) findViewById(R.id.clubsListLayout);
        Button tv = new Button(this);
        tv.setLayoutParams(lparams);
        try {
            tv.setText(clubToAdd.getString("name") + "\n" + clubToAdd.getString("meetingTimes"));
        } catch (Exception e){
            e.printStackTrace();
        }
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    GlobalVars.setCurClubName(clubToAdd.getString("name") );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(HomeScreen.this, ClubDetailsScreen.class));
            }
        });
        layout.addView(tv);
    }
    private void addNotif(JSONObject clubToAdd){
        Toolbar.LayoutParams lparams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        ViewGroup layout = (ViewGroup) findViewById(R.id.ClubNotiflayout);
        Button tv = new Button(this);
        tv.setLayoutParams(lparams);
        try {
            tv.setText("Sent From: "+clubToAdd.getString("senderName") + "\n" + clubToAdd.getString("message")+ "\n"+
                    clubToAdd.getString("timestamp") + "\n"+clubToAdd.getString("clubName"));
        } catch (Exception e){
            e.printStackTrace();
        }
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    GlobalVars.setCurClubName(clubToAdd.getString("clubName") );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(HomeScreen.this, ClubDetailsScreen.class));
            }
        });
        layout.addView(tv);
    }

    //debug do not use
    private void addClub(String text){
        Toolbar.LayoutParams lparams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        ViewGroup layout = (ViewGroup) findViewById(R.id.clubsListLayout);
        Button tv = new Button(this);
        tv.setLayoutParams(lparams);
        tv.setText(text);
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(HomeScreen.this, ClubDetailsScreen.class));
            }
        });
        layout.addView(tv);
    }


}
