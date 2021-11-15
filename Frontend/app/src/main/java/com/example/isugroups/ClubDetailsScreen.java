package com.example.isugroups;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClubDetailsScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details_screen);

        ImageButton back = (ImageButton) findViewById(R.id.Back);//back button

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Back to the main menu
                startActivity(new Intent(ClubDetailsScreen.this, HomeScreen.class));
            }
        });
        RequestQueue queue = Volley.newRequestQueue(ClubDetailsScreen.this);
        String address = "http://coms-319-g22.cs.iastate.edu/club/search?phrase=<" +
                ((GlobalVars) ClubDetailsScreen.this.getApplication()).getCurClubName() +
                ">&page=<0>";

				JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, address, null, new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response2) {

                        JSONObject response = null;
                        try {
                            response = response2.getJSONObject(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Button join = (Button) findViewById(R.id.JoinButton);

                        Boolean isMember = false;
                        //check if user is member in the DB
                        //TODO
                        isMember = ((GlobalVars) ClubDetailsScreen.this.getApplication()).getClubs().contains(((GlobalVars) ClubDetailsScreen.this.getApplication()).getCurClubName());
                        if (isMember) {
                            join.setVisibility(View.GONE);
                        }


                        join.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View view) {
                                //Join Club, add user ID to the list and refresh page to change view
                                ((GlobalVars) ClubDetailsScreen.this.getApplication()).getClubs().add(((GlobalVars) ClubDetailsScreen.this.getApplication()).getCurClubName());
                                startActivity(new Intent(ClubDetailsScreen.this, ClubDetailsScreen.class));
                                //TODO add user on backend and add club
                            }
                        });


                        String ClubNameData = null;//Will be called from DB
                        try {
                            ClubNameData = response.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        TextView ClubName = (TextView) findViewById(R.id.ClubName);
                        ClubName.setText(ClubNameData);

                        String DescriptionData = null;
                        try {
                            DescriptionData = response.getString("description");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        TextView Description = (TextView) findViewById(R.id.Description);
                        Description.setText(DescriptionData);


                        String MeetTimeData = null;//Will be called by DB
                        try {
                            MeetTimeData = response.getString("meetingTimes");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        TextView MeetTime = (TextView) findViewById(R.id.MeetingTimes);
                        MeetTime.setText(MeetTimeData);

                        String EventData = null;
                        try {
                            EventData = response.getString("eventInformation");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Button yourButton = (Button) findViewById(R.id.EventButton);

                        yourButton.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View view) {
                                startActivity(new Intent(ClubDetailsScreen.this, EventCalendar.class));
                            }
                        });

                        TextView Event = (TextView) findViewById(R.id.Events);
                        Event.setText(EventData);

                        int numMembers = 0;
                        String Rescrictions ="Membership Restrictions:"+"\n";
                        try {
                             Rescrictions = response.getString("membershipRestrictions");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String Elections = "Elections:"+"\n";
                        try {
                            Elections = response.getString("electionInformation");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray exampleArray = null;
                        try {
                             exampleArray = response.getJSONArray("officerPositions");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String Positions = "Officer Positions:"+"\n";
                        for(int i =0;i< exampleArray.length();i++){
                            try {
                                Positions += exampleArray.getString(i) + "\n";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        String MemberData = Rescrictions + "\n" + Positions + "\n" + Elections;

                        TextView Member = (TextView) findViewById(R.id.Members);
                        Member.setText(MemberData);

                        Button yourButton2 = (Button) findViewById(R.id.ConstitutionButton);

                        yourButton2.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View view) {
                                //Download Constitution from DB
                                //TODO
                            }
                        });

                        String FeeData = null;
                        try {
                            FeeData = response.getString("fees");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        TextView fee = (TextView) findViewById(R.id.Fees);
                        fee.setText(FeeData);

                        String OfficerList = "";

                        String OfficersData = "President\n" +
                                "John Doe\n" +
                                "Treasurer\n" +
                                "Jane Doe";

                        TextView Officers = (TextView) findViewById(R.id.Officers);
                        Officers.setText(OfficersData);

                        //TODO add contact from JSON object

                        String ContactList = "";

                        String ContactData = "John Doe\n" +
                                "Phone Number: 123-456-7890\n" +
                                "email: example@gmail.com\n" +
                                "Jane Doe\n" +
                                "Phone Number: 123-456-7891\n" +
                                "email: example2@gmail.com";

                        TextView Contact = (TextView) findViewById(R.id.Contact);
                        Contact.setText(ContactData);




					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						error.printStackTrace();
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ClubDetailsScreen.this);
						alertDialogBuilder.setTitle("Error");
						alertDialogBuilder.setMessage(error.getMessage());
						alertDialogBuilder.setPositiveButton("Ok", null);
						alertDialogBuilder.setNegativeButton("", null);
						alertDialogBuilder.create().show();
					}
				});
				queue.add(request);




    }

}
