package com.example.nnn228;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libnavannotation.ActivityDestination;

@ActivityDestination(pageUrl = "main/tabs/publish", asStarter = false)
public class activity_publish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
    }
}