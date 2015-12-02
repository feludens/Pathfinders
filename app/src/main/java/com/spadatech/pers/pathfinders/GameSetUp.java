package com.spadatech.pers.pathfinders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class GameSetUp extends AppCompatActivity implements OnItemSelectedListener {
    private static final int INVALID_NUMBER_OF_PLAYERS = -1;

    private RadioGroup mRadioGroup;
    private RadioButton mRbNo, mRbYes;
    private Button button;
    private Spinner mPlayerSpinner;
    private LinearLayout mPlayerNamesContainer;
    private int mNumberOfPlayer = -1;

    List<String> mNumberOfPlayersList = new ArrayList<String>();
    List<EditText> mEditTextList = new ArrayList<EditText>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_set_up);

        mPlayerSpinner = (Spinner) findViewById(R.id.spinner);

        mNumberOfPlayersList.add("Select");
        mNumberOfPlayersList.add("2 Players");
        mNumberOfPlayersList.add("3 Players");
        mNumberOfPlayersList.add("4 Players");
        mNumberOfPlayersList.add("5 Players");
        mNumberOfPlayersList.add("6 Players");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mNumberOfPlayersList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mPlayerSpinner.setAdapter(dataAdapter);
        mPlayerSpinner.setSelection(0, false);
        mPlayerSpinner.setOnItemSelectedListener(this);

        mPlayerNamesContainer = (LinearLayout) findViewById(R.id.ll_player_names_container);

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_radio_buttons_container);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.rb_yes) {
                    if(mNumberOfPlayer == INVALID_NUMBER_OF_PLAYERS){
                        selectPlayer();
                        mRbNo.toggle();
                    }else{
                        createEditTexts();
                    }
                } else if (checkedId == R.id.rb_no) {
                    Toast.makeText(getApplicationContext(), "choice: No",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRbNo = (RadioButton) findViewById(R.id.rb_no);
        mRbYes = (RadioButton) findViewById(R.id.rb_yes);

        button = (Button)findViewById(R.id.start_game);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mNumberOfPlayer == INVALID_NUMBER_OF_PLAYERS) {
                    selectPlayer();
                }else{
                    ArrayList<String> playerNames = getPlayerNames();
                    Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                    intent.putExtra("numberOfPlayer", mNumberOfPlayer);
                    intent.putStringArrayListExtra("playerNames", getPlayerNames());
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Start Game", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0) {
            selectPlayer();
        }else if(mNumberOfPlayer > 0){
            mNumberOfPlayer = position;
            createEditTexts();
        }else{
            mNumberOfPlayer = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectPlayer();
    }

    private void selectPlayer() {
        Toast.makeText(this, "Please select the number of players", Toast.LENGTH_LONG).show();
    }

    private void createEditTexts() {
        mPlayerNamesContainer.removeAllViews();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int i = 1; i <= mNumberOfPlayer+1; i++){
            EditText edttext = new EditText(this);
            edttext.setId(R.id.et_player_id + i);
            edttext.setHint("Player " + i + " name");
            edttext.setLayoutParams(params);

            mPlayerNamesContainer.addView(edttext);
            mEditTextList.add(edttext);
        }
    }

    public ArrayList<String> getPlayerNames() {
        ArrayList<String> names = new ArrayList<>();

        for(int i = 0; i < mEditTextList.size(); i++){
            String editTextString = mEditTextList.get(i).getText().toString();
            if(!editTextString.isEmpty()) {
                names.add(editTextString);
            }
        }

        return names;
    }
}
