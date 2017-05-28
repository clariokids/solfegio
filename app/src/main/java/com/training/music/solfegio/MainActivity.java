package com.training.music.solfegio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String TYPE = "type";
    private LinearLayout infoText, menuContent, menuButton;
    private TextView labelContent, helpText;
    private Button rhythm, melody, interval;
    private int type;

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

                    type = 1;
                    return true;
                case R.id.navigation_question:
                    infoText.setVisibility(View.VISIBLE);
                    menuContent.setVisibility(View.GONE);
                    helpText.setVisibility(View.GONE);
                    menuButton.setVisibility(View.VISIBLE);

                    labelContent.setText(R.string.label_content_question);
                    type = 2;
                    return true;
                case R.id.navigation_help:
                    infoText.setVisibility(View.VISIBLE);
                    menuContent.setVisibility(View.GONE);
                    helpText.setVisibility(View.VISIBLE);
                    menuButton.setVisibility(View.GONE);

                    labelContent.setText(R.string.label_content_help);
                    helpText.setText(R.string.contact_help);
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

        infoText = (LinearLayout) findViewById(R.id.info_text);
        menuContent = (LinearLayout) findViewById(R.id.menu_content);
        menuButton = (LinearLayout) findViewById(R.id.menu_button);

        labelContent = (TextView) findViewById(R.id.label_content_menu);
        helpText = (TextView) findViewById(R.id.help_text);

        rhythm = (Button) findViewById(R.id.btn_rhythm);
        melody = (Button) findViewById(R.id.btn_melody);
        interval = (Button) findViewById(R.id.btn_interval);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        rhythm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(getApplicationContext(), RhythmActivity.class);
                intent.putExtra(TYPE, type);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        melody.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(getApplicationContext(), MelodyActivity.class);
                intent.putExtra(TYPE, type);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        interval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(getApplicationContext(), IntervalActivity.class);
                intent.putExtra(TYPE, type);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

    }

}
