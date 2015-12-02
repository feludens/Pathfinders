package com.spadatech.pers.pathfinders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends AppCompatActivity {
    private static final String PLAYER = "Player ";

    TextView mTvPlayerName;
    ImageView mIvDice;
    int mCurrentPlayer;
    int mNumberOfPlayers;
    ArrayList<String> mPlayerNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        mNumberOfPlayers = getIntent().getIntExtra("numberOfPlayer", 0);
        mPlayerNamesList = getIntent().getStringArrayListExtra("playerNames");

        mTvPlayerName = (TextView) findViewById(R.id.tv_player_name);
        mIvDice = (ImageView) findViewById(R.id.iv_dice);

        setUpGame();
    }

    private void setUpGame() {
        mCurrentPlayer = 0;
        updateCurrentPlayerName();
        rollDice();
    }

    private void updateCurrentPlayerName(){
        if(mCurrentPlayer < mPlayerNamesList.size()){
            mTvPlayerName.setText(mPlayerNamesList.get(mCurrentPlayer));
        }else{
            mTvPlayerName.setText(PLAYER + (mCurrentPlayer + 1));
        }

        rollDice();
    }

    private void rollDice(){
        final Random rand = new Random();
        int diceRoll = rand.nextInt(6) + 1;
        updateDiceImage(diceRoll);
    }

    private void updateDiceImage(int diceRoll) {
        switch (diceRoll){
            case 1:
                mIvDice.setBackgroundResource(R.drawable.dice_one);
                break;
            case 2:
                mIvDice.setBackgroundResource(R.drawable.dice_two);
                break;
            case 3:
                mIvDice.setBackgroundResource(R.drawable.dice_three);
                break;
            case 4:
                mIvDice.setBackgroundResource(R.drawable.dice_four);
                break;
            case 5:
                mIvDice.setBackgroundResource(R.drawable.dice_five);
                break;
            case 6:
                mIvDice.setBackgroundResource(R.drawable.dice_six);
                break;
        }
    }

    public void getNewDiceNumber(View v){
        rollDice();
    }

    public void nextPlayer(View v){
        if(mCurrentPlayer < mNumberOfPlayers){
            mCurrentPlayer++;
        }else{
            mCurrentPlayer = 0;
        }

        updateCurrentPlayerName();
    }
}
