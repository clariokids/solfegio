package com.training.music.solfegio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private final static String TYPE = "type";
    private SharedPreferences prefAll;
    private LinearLayout infoText, menuContent, menuButton;
    ListView listView;
    private EditText usernameInput;
    private TextView labelContent, helpText, helpText2;
    private Button rhythm, melody, interval;
    HashMap<String, String> listRhythm = new HashMap<String, String>();
    HashMap<String, String> listMelody = new HashMap<String, String>();
    HashMap<String, String> listInterval = new HashMap<String, String>();

    List<Option> optionList = new ArrayList<>();
    private int type = 1;
    private String username, buildScore;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_material:
//                    labelContent.setText(R.string.label_content_material);
                    infoText.setVisibility(View.GONE);
                    menuContent.setVisibility(View.VISIBLE);
                    menuButton.setVisibility(View.VISIBLE);
                    usernameInput.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);

                    type = 1;
                    return true;
                case R.id.navigation_question:
                    infoText.setVisibility(View.VISIBLE);
                    menuContent.setVisibility(View.GONE);
                    helpText.setVisibility(View.GONE);
                    helpText2.setVisibility(View.GONE);
                    menuButton.setVisibility(View.VISIBLE);
                    usernameInput.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);


                    labelContent.setText(R.string.label_content_question);
                    type = 2;
                    return true;
                case R.id.navigation_help:
                    infoText.setVisibility(View.VISIBLE);
                    menuContent.setVisibility(View.GONE);
                    helpText.setVisibility(View.VISIBLE);
                    helpText2.setVisibility(View.VISIBLE);
                    menuButton.setVisibility(View.GONE);
                    usernameInput.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);



                    labelContent.setText(R.string.label_content_help);
                    helpText.setText(R.string.contact_help);
                    helpText2.setText(R.string.contact_help2);

                    type = 3;
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefAll = PreferenceManager.getDefaultSharedPreferences(this);

        listView = (ListView)findViewById(R.id.list_score);
        infoText = (LinearLayout) findViewById(R.id.info_text);
        menuContent = (LinearLayout) findViewById(R.id.menu_content);
        menuButton = (LinearLayout) findViewById(R.id.menu_button);

        labelContent = (TextView) findViewById(R.id.label_content_menu);
        helpText = (TextView) findViewById(R.id.help_text);
        helpText2 = (TextView) findViewById(R.id.help_text_2);

        usernameInput = (EditText) findViewById(R.id.username);

        rhythm = (Button) findViewById(R.id.btn_rhythm);
        melody = (Button) findViewById(R.id.btn_melody);
        interval = (Button) findViewById(R.id.btn_interval);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Map<String, ?> allEntries = prefAll.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            String keyIndex = entry.getKey();
            Log.d("keyIndex", keyIndex);
            if(keyIndex.length()>3){
                String[] splitKey = keyIndex.split(Pattern.quote("."));
                String username = splitKey[0];
                String typeQuiz = splitKey[1];
                Log.d("ganteng", username);
                Log.d("typeQuiz", typeQuiz);
                String valueSaved = entry.getValue().toString();
                Log.d("valueSaved", valueSaved);

                if(typeQuiz.equals("rhythm")){
                    listRhythm.put(username, valueSaved);
                }else if(typeQuiz.equals("melody")){
                    listMelody.put(username, valueSaved);
                }else if(typeQuiz.equals("interval")){
                    listInterval.put(username, valueSaved);
                }
            }

//                        Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }
        for(String key:listRhythm.keySet()){
            Option option = new Option();
            option.setNumber(key+" Skor Ritme : "+listRhythm.get(key));
            optionList.add(option);
        }
        for(String key:listMelody.keySet()){
            Option option = new Option();
            option.setNumber(key+" Skor Melodi : "+listMelody.get(key));
            optionList.add(option);
        }
        for(String key:listInterval.keySet()){
            Option option = new Option();
            option.setNumber(key+" Skor Interval : "+listInterval.get(key));
            optionList.add(option);
        }
        /*Option option = new Option();
        option.setNumber( );
        optionList.add(option);*/

        ListScoreAdapter adapter = new ListScoreAdapter(getApplicationContext(), R.layout.option_list, optionList);
        listView.setAdapter(adapter);

        rhythm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                username = usernameInput.getText().toString();
                Log.d("username", username);
                if(type==1){
                    Intent intent = new Intent(getApplicationContext(), RhythmActivity.class);
                    intent.putExtra(TYPE, type);
                    startActivity(intent);

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                if(username.length()<3 && type==2){
                    Toast.makeText(MainActivity.this, "Minimal 3 Karakter", Toast.LENGTH_SHORT).show();
                }else if(username.length()>=3 && type==2){
                    saveUser(username);
                    Intent intent = new Intent(getApplicationContext(), RhythmActivity.class);
                    intent.putExtra(TYPE, type);
                    startActivity(intent);

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }
        });

        melody.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                username = usernameInput.getText().toString();

                if(type==1){
                    Intent intent = new Intent(getApplicationContext(), MelodyActivity.class);
                    intent.putExtra(TYPE, type);
                    startActivity(intent);

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                if(username.length()<3 && type==2){
                    Toast.makeText(MainActivity.this, "Minimal 3 Karakter", Toast.LENGTH_SHORT).show();
                }else if(username.length()>=3 && type==2){
                    saveUser(username);

                    Intent intent = new Intent(getApplicationContext(), MelodyActivity.class);
                    intent.putExtra(TYPE, type);
                    startActivity(intent);

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }


            }
        });

        interval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                username = usernameInput.getText().toString();

                if(type==1){
                    Intent intent = new Intent(getApplicationContext(), IntervalActivity.class);
                    intent.putExtra(TYPE, type);
                    startActivity(intent);

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                if(username.length()<3 && type==2){
                    Toast.makeText(MainActivity.this, "Minimal 3 Karakter", Toast.LENGTH_SHORT).show();
                }else if(username.length()>=3 && type==2){
                    saveUser(username);

                    Intent intent = new Intent(getApplicationContext(), IntervalActivity.class);
                    intent.putExtra(TYPE, type);
                    startActivity(intent);

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }


            }
        });

    }

    void saveUser(String user){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("A",user);
        editor.apply();
    }


}
