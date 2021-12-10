package com.example.isugroups;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CreateClub extends AppCompatActivity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_club);


		ImageButton back = (ImageButton) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(CreateClub.this, HomeScreen.class);
				startActivity(i);
			}
		});

		RequestQueue queue = Volley.newRequestQueue(this);


		Button createGroupButton = (Button) findViewById(R.id.save);
		createGroupButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


				String name = ((EditText) findViewById(R.id.clubName)).getText().toString();
				JSONObject newGroup = new JSONObject();
				try {
					newGroup.put("name", ((EditText) findViewById(R.id.clubName)).getText().toString());
					newGroup.put("description", ((EditText) findViewById(R.id.clubDescription)).getText().toString());
					newGroup.put("meetingTimes", ((EditText) findViewById(R.id.meetTimes)).getText().toString());
					newGroup.put("eventInformation", ((EditText) findViewById(R.id.EventC)).getText().toString());
					newGroup.put("fees", ((EditText) findViewById(R.id.FeeC)).getText().toString());
					newGroup.put("membershipRestrictions", ((EditText) findViewById(R.id.MemberRestrict)).getText().toString());
					newGroup.put("officerPositions", new JSONArray(((EditText) findViewById(R.id.etPositions)).getText().toString().split(",")));
					newGroup.put("electionInformation", ((EditText) findViewById(R.id.etElections)).getText().toString());
					newGroup.put("constitution", ((EditText) findViewById(R.id.ConstitutionC)).getText().toString());

					JSONObject Pres = new JSONObject();
					JSONObject VPres = new JSONObject();
					Pres.put("name", ((EditText) findViewById(R.id.PresName)).getText().toString());
					Pres.put("phoneNumber", ((EditText) findViewById(R.id.PresidentPhoneNumber)).getText().toString());
					Pres.put("email", ((EditText) findViewById(R.id.PresidentEmail)).getText().toString());
					VPres.put("name", ((EditText) findViewById(R.id.VPresName)).getText().toString());
					VPres.put("phoneNumber", ((EditText) findViewById(R.id.VicePresidentPhoneNumber)).getText().toString());
					VPres.put("email", ((EditText) findViewById(R.id.VicePresidentEmail)).getText().toString());
					JSONArray Contact = new JSONArray();
					Contact.put(Pres);
					Contact.put(VPres);

					newGroup.put("contacts", Contact);


				} catch (JSONException e) {
					e.printStackTrace();
				}
				String address = GlobalVars.VirtualUrl + "/club/create";
				String requestBody = newGroup.toString();




				BooleanRequest jsonObjectRequest = new BooleanRequest(Request.Method.POST, address, requestBody, new Response.Listener<Boolean>() {
					@Override
					public void onResponse(Boolean response) {
						if(response) {
							GlobalVars.setCurClubName(name);
							Intent i = new Intent(CreateClub.this, ClubDetailsScreen.class);
							startActivity(i);
						}
						else{
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateClub.this);
							alertDialogBuilder.setTitle("Error");
							alertDialogBuilder.setMessage("Club Already Taken");
							alertDialogBuilder.setPositiveButton("Ok", null);
							alertDialogBuilder.setNegativeButton("", null);
							alertDialogBuilder.create().show();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						error.printStackTrace();
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateClub.this);
						alertDialogBuilder.setTitle("Error");
						alertDialogBuilder.setMessage(error.getMessage());
						alertDialogBuilder.setPositiveButton("Ok", null);
						alertDialogBuilder.setNegativeButton("", null);
						alertDialogBuilder.create().show();
					}
				});

				// Add the request to the queue
				queue.add(jsonObjectRequest);


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
