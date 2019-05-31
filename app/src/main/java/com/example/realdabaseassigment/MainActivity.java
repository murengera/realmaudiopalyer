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

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button btnlogn,btnregister;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btnlogn=findViewById(R.id.lognbtn);
        btnregister=findViewById(R.id.registerbtn);
        realm.init(this);
        realm=Realm.getDefaultInstance();
        if (email.getText().toString().isEmpty() &&password.getText().toString().isEmpty())

        {
            Toast.makeText(this, "empty field", Toast.LENGTH_LONG).show();
        }
        else

        {
            btnregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    realm.beginTransaction();
                    item itemobject=realm.createObject(item.class);
                    itemobject.setEmail(email.getText().toString());
                    itemobject.setPassword(password.getText().toString());

                    realm.commitTransaction();
                    startActivity(new Intent(MainActivity.this,Login.class));


                }
            });
        }

        btnlogn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    startActivity(new Intent(MainActivity.this,Login.class));

            }
        });
    }
}
