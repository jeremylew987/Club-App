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

                AlertDialog.Builder builder1 = new AlertDialog.Builder(CreateLoginScreen.this);
                builder1.setMessage("Entered values are: \n" + firstName + "\n" + lastName
                        + "\n" + username + "\n" + password );
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });


    }

}
