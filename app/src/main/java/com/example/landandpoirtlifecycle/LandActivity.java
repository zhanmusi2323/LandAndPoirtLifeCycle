package com.example.landandpoirtlifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        Log.e("wyw","=========  onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("wyw","=========  onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("wyw","=========  onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("wyw","=========  onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("wyw","=========  onDestroy");
    }
}
