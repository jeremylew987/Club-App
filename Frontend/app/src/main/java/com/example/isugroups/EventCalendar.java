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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EventCalendar extends AppCompatActivity {

    private JsonObjectRequest mJSONRequest;
    private String address;
    private static final String TAG = EventCalendar.class.getName();
    private TextView Clubtitle;
    private CalendarView calendar;
    private TextView eventdate;
    private TextView eventdescription;
    private TextView eventtitle;
    private TextView eventtime;
    private String currentmonth;
    private String currentyear;
    private String currentclub;
    private String currentdate;

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
        currentclub = GlobalVars.getCurClubName();
        Clubtitle.setText(currentclub);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(calendar.getDate()));
        currentmonth = selectedDate.substring(6, 10);
        currentyear = selectedDate.substring(3, 5);
        currentdate = selectedDate.substring(0, 2);
        eventdate.setText(currentdate + ":");
        address = "http://:8080/event/scheduled?club=" + currentclub + "&month=" + currentmonth + "&year=" + currentyear;

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
                eventdescription.setText("");
                eventtitle.setText("");
                eventtime.setText("");
                currentmonth = String.valueOf(i1);
                currentyear = String.valueOf(i);

                int month = i1 + 1;
                currentdate = i2 + "/" + month + "/" + i;
                eventdate.setText(currentdate + ":");
                address = "http://:8080/event/scheduled?club=" + currentclub + "&month=" + currentmonth + "&year=" + currentyear;
                EventRequest();

            }
        });



    }

    private void EventRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        mJSONRequest = new JsonObjectRequest
                (Request.Method.GET, address, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("EventDto");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject event = jsonArray.getJSONObject(i);

                                String date  = event.getString("date");
                                String time = event.getString("time");
                                String title = event.getString("title");
                                String description = event.getString("description");
                                if (currentdate.equals(date)) {
                                    eventtitle.setText(title);
                                    eventdescription.setText(description);
                                    eventtime.setText(time);
                                }
                            }
                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "Error :" + error.toString());
                        Toast.makeText(getApplicationContext(), "Error :", Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(mJSONRequest);
    }



}
