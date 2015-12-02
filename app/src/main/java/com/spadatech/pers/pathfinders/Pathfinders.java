package com.spadatech.pers.pathfinders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Pathfinders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeNoActionBar);
        setContentView(R.layout.activity_pathfinders);
    }

    public void startNewGame(View v){
        Intent intent = new Intent(this, GameSetUp.class);
        startActivity(intent);
    }

    public void seeInstructions(View v){
        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);
    }
}
