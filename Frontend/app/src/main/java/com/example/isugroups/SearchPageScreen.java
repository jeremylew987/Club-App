package com.example.isugroups;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchPageScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page_screen);

        ImageButton back = (ImageButton) findViewById(R.id.BackToHome);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        GlobalVars.setCurPage(0);


        Button Search = (Button) findViewById(R.id.SearchButton);
        RequestQueue queue = Volley.newRequestQueue(SearchPageScreen.this);
        Search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText Searchbar = (EditText) findViewById(R.id.SearchBar);
                String SearchVal = Searchbar.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(SearchPageScreen.this);
                String address = GlobalVars.VirtualUrl + "/club/search?phrase=" +
                        SearchVal +
                        "&page=" + GlobalVars.getCurPage();
                TextView Description = (TextView) findViewById(R.id.S1);
                Description.setText("");
                Description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                Description = (TextView) findViewById(R.id.S2);
                Description.setText("");
                Description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                Description = (TextView) findViewById(R.id.S3);
                Description.setText("");
                Description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                Description = (TextView) findViewById(R.id.S4);
                Description.setText("");
                Description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                Description = (TextView) findViewById(R.id.S5);
                Description.setText("");
                Description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });




                //TODO make the JSON request and fill text values
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, address, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0; i<response.length();i++){
                            String result = "";
                            String name = "";
                            try {
                                JSONObject temp  = response.getJSONObject(i);
                                 name = temp.getString("name");
                                result += "Name: " + temp.getString("name") + "\n";
                                result += "Meeting Times: " + temp.getString("meetingTimes") + "\n";

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(i==0){
                                TextView Description = (TextView) findViewById(R.id.S1);
                                Description.setText(result);
                                String s1 = name;
                                Description.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        GlobalVars.setCurClubName(s1);
                                        startActivity(new Intent(SearchPageScreen.this, ClubDetailsScreen.class));
                                    }
                                });
                            }
                            else if(i==1){
                                TextView Description = (TextView) findViewById(R.id.S2);
                                Description.setText(result);
                                String s2 = name;
                                Description.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        GlobalVars.setCurClubName(s2);
                                        startActivity(new Intent(SearchPageScreen.this, ClubDetailsScreen.class));
                                    }
                                });
                            }
                            else if(i==2){
                                TextView Description = (TextView) findViewById(R.id.S3);
                                Description.setText(result);
                                String s3 = name;
                                Description.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        GlobalVars.setCurClubName(s3);
                                        startActivity(new Intent(SearchPageScreen.this, ClubDetailsScreen.class));
                                    }
                                });
                            }
                            else if(i==3){
                                TextView Description = (TextView) findViewById(R.id.S4);
                                Description.setText(result);
                                String s4 = name;
                                Description.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        GlobalVars.setCurClubName(s4);
                                        startActivity(new Intent(SearchPageScreen.this, ClubDetailsScreen.class));
                                    }
                                });
                            }
                            else if(i==4){
                                TextView Description = (TextView) findViewById(R.id.S5);
                                Description.setText(result);
                                String s5 = name;
                                Description.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        GlobalVars.setCurClubName(s5);
                                        startActivity(new Intent(SearchPageScreen.this, ClubDetailsScreen.class));
                                    }
                                });
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchPageScreen.this);
                        alertDialogBuilder.setTitle("Error");
                        alertDialogBuilder.setMessage(error.getMessage());
                        alertDialogBuilder.setPositiveButton("Ok", null);
                        alertDialogBuilder.setNegativeButton("", null);
                        alertDialogBuilder.create().show();
                    }
                });
                queue.add(request);
            }

        });

        Button pageup = (Button) findViewById(R.id.PageUp);
        pageup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVars.setCurPage(GlobalVars.getCurPage() + 1);
                TextView page = (TextView) findViewById(R.id.PageNum);
                page.setText("Page: "+GlobalVars.getCurPage());
                Search.performClick();
            }
        });
        Button pagedown = (Button) findViewById(R.id.PageDown);
        pagedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GlobalVars.getCurPage() != 0) {
                    GlobalVars.setCurPage(GlobalVars.getCurPage() - 1);
                    TextView page = (TextView) findViewById(R.id.PageNum);
                    page.setText("Page: " + GlobalVars.getCurPage());
                    Search.performClick();
                }
            }
        });



    }
}