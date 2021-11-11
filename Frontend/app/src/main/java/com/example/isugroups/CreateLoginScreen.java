package com.example.isugroups;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateLoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login_screen);

        ImageButton yourButton = (ImageButton) findViewById(R.id.BackToLogin);

        yourButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateLoginScreen.this, LoginScreen.class));
            }
        });


        Button Button = (android.widget.Button) findViewById(R.id.CreateUserSumbit);

        Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                EditText firstName1 = (EditText)findViewById(R.id.FirstName);
                String firstName = firstName1.getText().toString();

                EditText lastName1 = (EditText)findViewById(R.id.LastName);
                String lastName = lastName1.getText().toString();

                EditText username1 = (EditText)findViewById(R.id.Username);
                String username = username1.getText().toString();

                EditText password1 = (EditText)findViewById(R.id.Password);
                String password = password1.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(CreateLoginScreen.this);
                JSONObject data = new JSONObject();
                try {
                    data.put("username",username);
                    data.put("passphrase",password);
                    data.put("firstName",firstName);//firstname
                    data.put("lastName",lastName);//last name
                } catch (JSONException e) {
                    e.printStackTrace();
                }
				/*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constance.BASE_URL + "user/login", data, new Response.Listener<JSONObject>() {
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



            }
        });


    }

}
