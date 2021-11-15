package com.example.isugroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EventCalendar extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private JsonObjectRequest mJSONRequest;
    private String url;
    private static final String TAG = EventCalendar.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_calendar);

        Button homebutton = findViewById(R.id.calendar_home_button);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(v.getContext(), LoginScreen.class));
            }
        });



    }

    private void EventRequest() {
        String monthtext = "";
        String daytext = "";
        String clubnametext = "";


        mJSONRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("clubs");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject clubs = jsonArray.getJSONObject(i);

                                String clubname = clubs.getString("club name");
                                if (clubnametext == clubname) {
                                    JSONArray eventsarray = clubs.getJSONArray("events");
                                    for (int j = 0; j < eventsarray.length(); j++) {
                                        JSONObject events = eventsarray.getJSONObject(j);
                                        String month = events.getString("month");
                                        String day = events.getString("day");
                                        String eventname = events.getString("eventname");
                                        String description = events.getString("description");
                                        if (monthtext == month && daytext == day) {
                                            Changetext(description, eventname);
                                        }
                                    }
                                }

                            }
                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "Error :" + error.toString());
                    }
                });
        mRequestQueue.add(mJSONRequest);
    }


    private void Changetext(String eventdescription, String eventname) {
        TextView Event = (TextView) findViewById(R.id.calendar_event_title);
        TextView description = (TextView) findViewById(R.id.calendar_event_description);
        Event.setText(eventdescription);
        description.setText(eventname);

    }
}