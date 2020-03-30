package com.example.soma;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button profile = (Button)  findViewById(R.id.btn_profile);

        Toolbar mytool = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytool);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClassName("com.example.soma","com.example.soma.tutor_profile");
                startActivity(i);

            }
        });
    }

    }




