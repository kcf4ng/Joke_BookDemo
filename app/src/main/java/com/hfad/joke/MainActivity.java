package com.hfad.joke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Log.v("DelayMessageService", "button Click");
        Intent intent = new Intent(this, DelayMessageService.class);
        intent.putExtra(DelayMessageService.EXTRA_MESSAGE , getResources().getString(R.string.response))         ;
        startService(intent);
    }
}
