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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

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
                String requestBody = data.toString();
                String address = "http://10.49.40.75:8080/users/create";
                BooleanRequest request = new BooleanRequest(Request.Method.POST, address, requestBody, new Response.Listener<Boolean>() {
					@Override
					public void onResponse(Boolean response) {
					    if(response) {
					        GlobalVars.setCurUserID(username);
                            Intent i = new Intent(CreateLoginScreen.this, HomeScreen.class);
                            startActivity(i);
                        }
					    else{
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateLoginScreen.this);
                            alertDialogBuilder.setTitle("Error");
                            alertDialogBuilder.setMessage("Username already in use");
                            alertDialogBuilder.setPositiveButton("Ok", null);
                            alertDialogBuilder.setNegativeButton("", null);
                            alertDialogBuilder.create().show();
                        }
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						error.printStackTrace();
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateLoginScreen.this);
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


    }
    //Public class
    private class BooleanRequest extends Request<Boolean> {
        private final Response.Listener<Boolean> mListener;
        private final Response.ErrorListener mErrorListener;
        private final String mRequestBody;

        private final String PROTOCOL_CHARSET = "utf-8";
        private final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

        public BooleanRequest(int method, String url, String requestBody, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
            super(method, url, errorListener);
            this.mListener = listener;
            this.mErrorListener = errorListener;
            this.mRequestBody = requestBody;
        }

        @Override
        protected Response<Boolean> parseNetworkResponse(NetworkResponse response) {
            Boolean parsed;
            try {
                parsed = Boolean.valueOf(new String(response.data, HttpHeaderParser.parseCharset(response.headers)));
            } catch (UnsupportedEncodingException e) {
                parsed = Boolean.valueOf(new String(response.data));
            }
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }

        @Override
        protected VolleyError parseNetworkError(VolleyError volleyError) {
            return super.parseNetworkError(volleyError);
        }

        @Override
        protected void deliverResponse(Boolean response) {
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
