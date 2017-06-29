package com.mycompany.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class FullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Utils.getTag(), "실행");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
    }
}
