package com.jithin.games.chessquad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void login(View view){
        Log.d("Home", "Logging in");
    }


    public void goToGame(View view){
        Log.d("Home", "Moving to Game activity");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}
