package com.example.isugroups;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class ClubDetailsScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details_screen);

        ImageButton back = (ImageButton) findViewById(R.id.Back);//back button

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        RequestQueue queue = Volley.newRequestQueue(ClubDetailsScreen.this);
        /*String address = "http://coms-319-g22.cs.iastate.edu/club/search?phrase=<" +
                ((GlobalVars) ClubDetailsScreen.this.getApplication()).getCurClubName() +
                ">&page=<0>";*/
        String club = GlobalVars.getCurClubName();
        String address = GlobalVars.VirtualUrl + "/club/search/narrowed?club="+club;

				JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, address, null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
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
                                String join = GlobalVars.VirtualUrl+"/club/join?club="+GlobalVars.getCurClubName();
                                //TODO add user on backend and add club
                                SStringRequest request = new SStringRequest(Request.Method.POST, join, null, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("SUCCESS")){
                                            //Join Club, add user ID to the list and refresh page to change view
                                            ((GlobalVars) ClubDetailsScreen.this.getApplication()).getClubs().add(((GlobalVars) ClubDetailsScreen.this.getApplication()).getCurClubName());
                                            startActivity(new Intent(ClubDetailsScreen.this, ClubDetailsScreen.class));
                                        }
                                        else{

                                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ClubDetailsScreen.this);
                                            alertDialogBuilder.setTitle("Error");
                                            alertDialogBuilder.setMessage(GlobalVars.getCurUserID());
                                            alertDialogBuilder.setPositiveButton("Ok", null);
                                            alertDialogBuilder.setNegativeButton("", null);
                                            alertDialogBuilder.create().show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        error.printStackTrace();
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ClubDetailsScreen.this);
                                        alertDialogBuilder.setTitle("Error");
                                        alertDialogBuilder.setMessage(GlobalVars.getCurUserID());
                                        alertDialogBuilder.setPositiveButton("Ok", null);
                                        alertDialogBuilder.setNegativeButton("", null);
                                        alertDialogBuilder.create().show();
                                    }
                                });
                                queue.add(request);


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
                        Button yourButton2 = (Button) findViewById(R.id.ToCreateEvent);

                        yourButton2.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View view) {
                                startActivity(new Intent(ClubDetailsScreen.this, CreateEventScreen.class));
                            }
                        });
                        Button yourButton3 = (Button) findViewById(R.id.ToCreateNotification);

                        yourButton3.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View view) {
                                startActivity(new Intent(ClubDetailsScreen.this, CreateClubNotificationScreen.class));
                            }
                        });
                        if (!isMember) {
                            yourButton3.setVisibility(View.GONE);
                            yourButton2.setVisibility(View.GONE);

                        }

                        TextView Event = (TextView) findViewById(R.id.Events);
                        Event.setText(EventData);

                        int numMembers = 0;
                        String Rescrictions ="Membership Restrictions:"+"\n";
                        try {
                             Rescrictions += response.getString("membershipRestrictions");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String Elections = "Elections:"+"\n";
                        try {
                            Elections += response.getString("electionInformation");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray exampleArray = null;
                        try {
                             exampleArray = response.getJSONArray("officerPositions");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String Positions = "Officer Positions:" + "\n";
                        if(exampleArray != null) {

                            for (int i = 0; i < exampleArray.length(); i++) {
                                try {
                                    Positions += exampleArray.getString(i) + "\n";
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }



                        String MemberData = Rescrictions + "\n\n" + Positions + "\n" + Elections;

                        TextView Member = (TextView) findViewById(R.id.Members);
                        Member.setText(MemberData);

                        Button yourButton4 = (Button) findViewById(R.id.ConstitutionButton);

                        yourButton4.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View view) {

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ClubDetailsScreen.this);
                                alertDialogBuilder.setTitle("Constitution");
                                try {
                                    alertDialogBuilder.setMessage(response.getString("constitution"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                alertDialogBuilder.setPositiveButton("Ok", null);
                                alertDialogBuilder.setNegativeButton("", null);
                                alertDialogBuilder.create().show();
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





                        JSONArray exampleArray2 = null;
                        try {
                            exampleArray2 = response.getJSONArray("contacts");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String ContactList = "President: \n";
                        if(exampleArray2 != null) {

                            for (int i = 0; i < exampleArray2.length(); i++) {
                                try {
                                    if(i==1)
                                        ContactList += "Vice President: \n";
                                    JSONObject temp  = exampleArray2.getJSONObject(i);
                                    ContactList += temp.getString("name") + "\n";
                                    ContactList += temp.getString("phoneNumber") + "\n";
                                    ContactList += temp.getString("email") + "\n";
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        String ContactData = "John Doe\n" +
                                "Phone Number: 123-456-7890\n" +
                                "email: example@gmail.com\n" +
                                "Jane Doe\n" +
                                "Phone Number: 123-456-7891\n" +
                                "email: example2@gmail.com";

                        TextView Contact = (TextView) findViewById(R.id.Contact);
                        Contact.setText(ContactList);




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

    private class SStringRequest extends Request<String> {
        private final Response.Listener<String> mListener;
        private final Response.ErrorListener mErrorListener;
        private final String mRequestBody;

        private final String PROTOCOL_CHARSET = "utf-8";
        private final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

        public SStringRequest(int method, String url, String requestBody, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, errorListener);
            this.mListener = listener;
            this.mErrorListener = errorListener;
            this.mRequestBody = requestBody;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("Authorization", GlobalVars.getCurUserID() + ":" + GlobalVars.getUserPassphrase());
            return params;
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }

        @Override
        protected VolleyError parseNetworkError(VolleyError volleyError) {
            return super.parseNetworkError(volleyError);
        }

        @Override
        protected void deliverResponse(String response) {
            mListener.onResponse(response);
        }

        @Override
        public void deliverError(VolleyError error) {
            mErrorListener.onErrorResponse(error);
        }

        @Override
        public String getBodyContentType() {
            return PROTOCOL_CONTENT_TYPE;
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            try {
                return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
            } catch (UnsupportedEncodingException uee) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                        mRequestBody, PROTOCOL_CHARSET);
                return null;
            }
        }
    }
}
