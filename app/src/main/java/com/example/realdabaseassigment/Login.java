package com.example.realdabaseassigment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class Login extends AppCompatActivity {


    EditText emails,passwords;
    Button btnlognn;
    Realm realm;
 private  SharedPreferenceConfig preferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emails=findViewById(R.id.emaill);
        passwords=findViewById(R.id.passwordd);
        btnlognn=findViewById(R.id.lognbtn);
        preferenceConfig=new SharedPreferenceConfig(getApplicationContext());


        if (preferenceConfig.readloginstatus())
        {

            startActivity(new Intent(Login.this,audioapp.class));
            finish();
        }

        realm.init(this);
        realm=Realm.getDefaultInstance();
        btnlognn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (emails.getText().toString().isEmpty() && passwords.getText().toString().isEmpty())
                {
                    Toast.makeText(Login.this, "field required", Toast.LENGTH_SHORT).show();
                }
                else {


                   RealmResults<item> realmResults=realm.where(item.class).findAll();
                   String email="";
                   String pass="";

                   for (item item:realmResults)
                    {
                    email+=item.getEmail();
                    pass+=item.getPassword();



                    }


                    if (email.contains(emails.getText().toString()) && pass.contains(passwords.getText().toString()))
                    {
                     startActivity(new Intent(Login.this,audioapp.class));

                     preferenceConfig.writeloginstatus(true);
                     finish();
                    }
                    else {
                        Toast.makeText(Login.this, "invalid username and password", Toast.LENGTH_LONG).show();
                    }



                }

            }
        });
    }
}
