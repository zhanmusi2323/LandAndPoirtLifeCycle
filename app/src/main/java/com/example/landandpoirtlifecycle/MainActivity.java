package com.example.landandpoirtlifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView vJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        vJump = findViewById(R.id.tv_jump);
//        vJump.setOnClickListener(this);
        Log.e("wyw", "--------  onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("wyw", "--------  onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("wyw", "--------  onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("wyw", "--------  onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("wyw", "--------  onDestroy");
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, LandActivity.class));
    }
}
