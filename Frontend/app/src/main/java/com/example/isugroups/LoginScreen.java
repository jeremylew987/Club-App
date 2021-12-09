package com.example.isugroups;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import java.lang.reflect.Method;

public class LoginScreen extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		EditText username = findViewById(R.id.usernameInput);
		EditText password = findViewById(R.id.passwordInput);
		Button submit = findViewById(R.id.submit);
		Button CreateLogin = findViewById(R.id.ToCreateLogin);
		CreateLogin.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginScreen.this, CreateLoginScreen.class));
			}
		});
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String name = username.getText().toString();
				String pass = password.getText().toString();

				RequestQueue queue = Volley.newRequestQueue(LoginScreen.this);
				JSONObject data = new JSONObject();
				try {
					data.put("username",name);
					data.put("passphrase",pass);
					data.put("firstName","");//firstname
					data.put("lastName","");//last name
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String requestBody = data.toString();
				String address = "http://10.49.40.75:8080/users/login";
				BooleanRequest request = new BooleanRequest(Request.Method.POST, address, requestBody, new Response.Listener<Boolean>() {
					@Override
					public void onResponse(Boolean response) {
						if(response) {
							Intent i = new Intent(LoginScreen.this, HomeScreen.class);
							startActivity(i);
						}
						else{
							android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(LoginScreen.this);
							alertDialogBuilder.setTitle("Error");
							alertDialogBuilder.setMessage("Incorrect password, username, or don't have an account with that username");
							alertDialogBuilder.setPositiveButton("Ok", null);
							alertDialogBuilder.setNegativeButton("", null);
							alertDialogBuilder.create().show();
						}
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


