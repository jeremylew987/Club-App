package com.example.isugroups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
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

import java.util.ArrayList;
import java.util.List;


public class EventCalendar extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private JsonObjectRequest mJSONRequest;
    private String url;
    private static final String TAG = EventCalendar.class.getName();
    List<eventmodel> eventlist = new ArrayList<eventmodel>();
    private TextView Clubtitle;
    private CalendarView calendar;
    private TextView eventdate;
    private TextView eventdescription;
    private TextView eventtitle;
    private TextView eventtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_calendar);
        Clubtitle = findViewById(R.id.club_title_calendar);
        Button homebutton = findViewById(R.id.calendar_home_button);
        calendar = findViewById(R.id.calendarView);
        eventdate = findViewById(R.id.calendar_date);
        eventdescription = findViewById(R.id.calendar_event_description);
        eventtitle = findViewById(R.id.calendar_event_title);
        eventtime = findViewById(R.id.calendar_event_time);

        EventRequest();

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(v.getContext(), LoginScreen.class));
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                int month = i1 + 1;
                String date = i2 + "/" + month + "/" + i;
                eventdate.setText(date + ":");
                for (int j = 0; j < eventlist.size(); j++) {
                    if (eventlist.get(j).getdate().equals(date)) {
                        eventtitle.setText(eventlist.get(j).gettitle());
                        eventdescription.setText(eventlist.get(j).getdescription());
                        eventtime.setText(eventlist.get(j).gettime());
                    }
                }
            }
        });



    }

    private void EventRequest() {
        String clubtitlestring = Clubtitle.toString();

        mJSONRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("Events");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject event = jsonArray.getJSONObject(i);

                                String date  = event.getString("date");
                                String time = event.getString("time");
                                String title = event.getString("title");
                                String description = event.getString("description");

                                String clubname = event.getString("clubName");
                                if (clubtitlestring.equals(clubname)) {
                                    eventmodel ev = new eventmodel(clubname, description, title, date, time);
                                    eventlist.add(ev);
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



}
