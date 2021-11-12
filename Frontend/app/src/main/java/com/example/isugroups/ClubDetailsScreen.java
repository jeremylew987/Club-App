package com.example.isugroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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
                //startActivity(new Intent(ClubDetailsScreen.this, CreateLoginScreen.class));
            }
        });
        RequestQueue queue = Volley.newRequestQueue(ClubDetailsScreen.this);
        JSONObject data = new JSONObject();

				/*JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constance.BASE_URL + "user/login", data, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Intent i = new Intent(LoginScreen.this,MainMenuScreen.class);
						startActivity(i);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						error.printStackTrace();
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginScreen.this);
						alertDialogBuilder.setTitle("Error");
						alertDialogBuilder.setMessage(error.getMessage());
						alertDialogBuilder.setPositiveButton("Ok", null);
						alertDialogBuilder.setNegativeButton("", null);
						alertDialogBuilder.create().show();
					}
				});
				queue.add(request);*/

        Button join = (Button) findViewById(R.id.JoinButton);

        Boolean isMember = false;
        //check if user is member in the DB
        //TODO
        if (isMember) {
            join.setVisibility(View.GONE);
        }


        join.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //Join Club, add user ID to the list and refresh page to change view
                startActivity(new Intent(ClubDetailsScreen.this, ClubDetailsScreen.class));
                //TODO
            }
        });


        String ClubNameData = "Random Club";//Will be called from DB

        TextView ClubName = (TextView) findViewById(R.id.ClubName);
        ClubName.setText(ClubNameData);

        String DescriptionData = "Club promoting and celebrating the game of chess.";//Will be called by DB

        TextView Description = (TextView) findViewById(R.id.Description);
        Description.setText(DescriptionData);


        String MeetTimeData = "Meetings are on MWF 3:00 - 5:00 p.m. and R 6:00 - 7:00 p.m.";//Will be called by DB

        TextView MeetTime = (TextView) findViewById(R.id.MeetingTimes);
        MeetTime.setText(MeetTimeData);

        String EventData = "Event information is delivered at club meetings, over email, and through this app.";
        Button yourButton = (Button) findViewById(R.id.EventButton);

        yourButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(ClubDetailsScreen.this, EventCalendar.class));
            }
        });

        TextView Event = (TextView) findViewById(R.id.Events);
        Event.setText(EventData);

        int numMembers = 0;
        String Rescrictions = "";
        String Elections = "";
        String Positions = "";

        String MemberData = "Students                             73\n" +
                "ISU members                     84\n" +
                "Non-ISU members            14\n" +
                "\n" +
                "Membership Restrictions\n" +
                "None\n" +
                "\n" +
                "Officer Positions\n" +
                "President\n" +
                "Treasurer\n" +
                "\n" +
                "Elections\n" +
                "Held in November\n" +
                "\n";

        TextView Member = (TextView) findViewById(R.id.Members);
        Member.setText(MemberData);

        Button yourButton2 = (Button) findViewById(R.id.ConstitutionButton);

        yourButton2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //Download Constitution from DB
                //TODO
            }
        });

        int FeeNum = 0;

        String FeeData = "$90 Per Semester\n" +
                "\n" +
                "$180 Per Year";

        TextView fee = (TextView) findViewById(R.id.Fees);
        fee.setText(FeeData);

        String OfficerList = "";

        String OfficersData = "President\n" +
                "John Doe\n" +
                "Treasurer\n" +
                "Jane Doe";

        TextView Officers = (TextView) findViewById(R.id.Officers);
        Officers.setText(OfficersData);

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

}
