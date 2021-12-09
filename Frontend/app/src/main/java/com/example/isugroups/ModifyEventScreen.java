package com.example.isugroups;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ModifyEventScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_event_screen);

        //set up back button
        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ModifyEventScreen.this, LoginScreen.class));
            }
        });

        //set up submit button
        Button submitButton = (Button) findViewById(R.id.Submit);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String title = ((TextView)findViewById(R.id.TitleText)).getText().toString();
                String description = ((EditText)findViewById(R.id.DescriptionText)).getText().toString();
                String date = ((EditText)findViewById(R.id.DateText)).getText().toString();
                String time = ((EditText)findViewById(R.id.TimeText)).getText().toString();


                RequestQueue queue = Volley.newRequestQueue(ModifyEventScreen.this);
                JSONObject data = new JSONObject();
                try {
                    data.put("clubName",GlobalVars.getCurClubName());
                    data.put("title",title);
                    data.put("description",description);
                    data.put("date",date);
                    data.put("time",time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String requestBody = data.toString();
                String address = "http://localhost:8080/event/modify";
                ModifyEventScreen.BooleanRequest request = new ModifyEventScreen.BooleanRequest(Request.Method.POST, address, requestBody, new Response.Listener<Boolean>() {
                    @Override
                    public void onResponse(Boolean response) {
                        if(response)
                            ((TextView)findViewById(R.id.StatusText)).setText("Event Successfully Modified.");
                        else
                            ((TextView)findViewById(R.id.StatusText)).setText("The Event Could Not Be Modified.");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ModifyEventScreen.this);
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