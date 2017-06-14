package com.training.music.solfegio;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rio on 5/27/2017.
 */

public class MelodyActivity extends AppCompatActivity {

    private final static String TYPE = "type";
    int numberQuiz;
    int type;
    int chance = 3;
    int right = 0;
    int score = 0;

    int optionCounter;
    Context context;
    LinearLayout.LayoutParams layoutParams;
    MediaPlayer mediaPlayer;
    AlertDialog.Builder builder;
    //    ImageView image_quiz;
//    ImageView rhythmQuestion, rhythmOption;
    TextView number_quiz, chance_quiz;
    ListView listView;
    EditText answerOption;
    Button quiz_answer, quiz_backspace, quiz_clear;
    ImageView sample_1, sample_2;

    List<Integer> rhythmAnswerArray = new ArrayList<Integer>();
    //    List<Integer> rhythmQuestionArray = new ArrayList<Integer>();
    List<Option> optionList = new ArrayList<>();

    List<Integer> userAnswer = new ArrayList<Integer>();
    List<Integer> compare = new ArrayList<Integer>();
    HashMap<Integer, List<Integer>> melodyNotationMap = new HashMap<Integer, List<Integer>>();
    HashMap<Integer, Integer> melodyQuestionMap = new HashMap<Integer, Integer>();
    HashMap<Integer, List<Integer>>  melodyAnswerMap = new HashMap<Integer, List<Integer>>();
    HashMap<Integer, Integer> melodySoundMap = new HashMap<Integer, Integer>();
    HashMap<Integer, List<Integer>> answerMap = new HashMap<Integer, List<Integer>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        type = intent.getIntExtra(TYPE, 0);

        numberQuiz = 1;
        context = this;

        melodyQuestionMap.put(1, R.mipmap.melody_question_1);//question
        melodyAnswerMap.put(1, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(1).add(R.mipmap.melody_1_1);
        melodyAnswerMap.get(1).add(R.mipmap.melody_1_2);
        melodyAnswerMap.get(1).add(R.mipmap.melody_1_3);
        melodyNotationMap.put(1, new ArrayList<Integer>());//option
        melodyNotationMap.get(1).add(R.mipmap.melody_1_1);
        melodyNotationMap.get(1).add(R.mipmap.melody_1_2);
        melodyNotationMap.get(1).add(R.mipmap.melody_1_3);
        Collections.shuffle(melodyNotationMap.get(1));
        melodySoundMap.put(1, R.raw.melodi1);//sound


        melodyQuestionMap.put(2, R.mipmap.melody_question_2);
        melodyAnswerMap.put(2, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(2).add(R.mipmap.melody_2_1);
        melodyAnswerMap.get(2).add(R.mipmap.melody_2_2);
        melodyAnswerMap.get(2).add(R.mipmap.melody_2_3);
        melodyAnswerMap.get(2).add(R.mipmap.melody_2_4);
        melodyNotationMap.put(2, new ArrayList<Integer>());//option
        melodyNotationMap.get(2).add(R.mipmap.melody_2_1);
        melodyNotationMap.get(2).add(R.mipmap.melody_2_2);
        melodyNotationMap.get(2).add(R.mipmap.melody_2_3);
        melodyNotationMap.get(2).add(R.mipmap.melody_2_4);
        Collections.shuffle(melodyNotationMap.get(2));
        melodySoundMap.put(2, R.raw.melodi2);//sound


        melodyQuestionMap.put(3, R.mipmap.melody_question_3);
        melodyAnswerMap.put(3, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(3).add(R.mipmap.melody_3_1);
        melodyAnswerMap.get(3).add(R.mipmap.melody_3_2);
        melodyAnswerMap.get(3).add(R.mipmap.melody_3_3);
        melodyAnswerMap.get(3).add(R.mipmap.melody_3_4);
        melodyNotationMap.put(3, new ArrayList<Integer>());//option
        melodyNotationMap.get(3).add(R.mipmap.melody_3_1);
        melodyNotationMap.get(3).add(R.mipmap.melody_3_2);
        melodyNotationMap.get(3).add(R.mipmap.melody_3_3);
        melodyNotationMap.get(3).add(R.mipmap.melody_3_4);
        Collections.shuffle(melodyNotationMap.get(3));
        melodySoundMap.put(3, R.raw.melodi3);//sound


        melodyQuestionMap.put(4, R.mipmap.melody_question_3);
        melodyAnswerMap.put(4, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(4).add(R.mipmap.melody_4_1);
        melodyAnswerMap.get(4).add(R.mipmap.melody_4_2);
        melodyAnswerMap.get(4).add(R.mipmap.melody_4_3);
        melodyAnswerMap.get(4).add(R.mipmap.melody_4_4);
        melodyNotationMap.put(4, new ArrayList<Integer>());//option
        melodyNotationMap.get(4).add(R.mipmap.melody_4_1);
        melodyNotationMap.get(4).add(R.mipmap.melody_4_2);
        melodyNotationMap.get(4).add(R.mipmap.melody_4_3);
        melodyNotationMap.get(4).add(R.mipmap.melody_4_4);
        Collections.shuffle(melodyNotationMap.get(4));
        melodySoundMap.put(4, R.raw.melodi4);//sound


        melodyQuestionMap.put(5, R.mipmap.melody_question_3);
        melodyAnswerMap.put(5, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(5).add(R.mipmap.melody_5_1);
        melodyAnswerMap.get(5).add(R.mipmap.melody_5_2);
        melodyAnswerMap.get(5).add(R.mipmap.melody_5_3);
        melodyAnswerMap.get(5).add(R.mipmap.melody_5_4);
        melodyNotationMap.put(5, new ArrayList<Integer>());//option
        melodyNotationMap.get(5).add(R.mipmap.melody_5_1);
        melodyNotationMap.get(5).add(R.mipmap.melody_5_2);
        melodyNotationMap.get(5).add(R.mipmap.melody_5_3);
        melodyNotationMap.get(5).add(R.mipmap.melody_5_4);
        Collections.shuffle(melodyNotationMap.get(5));
        melodySoundMap.put(5, R.raw.melodi5);//sound


        melodyQuestionMap.put(6, R.mipmap.melody_question_1);
        melodyAnswerMap.put(6, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(6).add(R.mipmap.melody_6_1);
        melodyAnswerMap.get(6).add(R.mipmap.melody_6_2);
        melodyAnswerMap.get(6).add(R.mipmap.melody_6_3);
        melodyAnswerMap.get(6).add(R.mipmap.melody_6_4);
        melodyNotationMap.put(6, new ArrayList<Integer>());//option
        melodyNotationMap.get(6).add(R.mipmap.melody_6_1);
        melodyNotationMap.get(6).add(R.mipmap.melody_6_2);
        melodyNotationMap.get(6).add(R.mipmap.melody_6_3);
        melodyNotationMap.get(6).add(R.mipmap.melody_6_4);
        Collections.shuffle(melodyNotationMap.get(6));
        melodySoundMap.put(6, R.raw.melodi6);//sound


        melodyQuestionMap.put(7, R.mipmap.melody_question_1);
        melodyAnswerMap.put(7, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(7).add(R.mipmap.melody_7_1);
        melodyAnswerMap.get(7).add(R.mipmap.melody_7_2);
        melodyAnswerMap.get(7).add(R.mipmap.melody_7_3);
        melodyAnswerMap.get(7).add(R.mipmap.melody_7_4);
        melodyNotationMap.put(7, new ArrayList<Integer>());//option
        melodyNotationMap.get(7).add(R.mipmap.melody_7_1);
        melodyNotationMap.get(7).add(R.mipmap.melody_7_2);
        melodyNotationMap.get(7).add(R.mipmap.melody_7_3);
        melodyNotationMap.get(7).add(R.mipmap.melody_7_4);
        Collections.shuffle(melodyNotationMap.get(7));
        melodySoundMap.put(7, R.raw.melodi7);//sound


        melodyQuestionMap.put(8, R.mipmap.melody_question_3);
        melodyAnswerMap.put(8, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(8).add(R.mipmap.melody_8_1);
        melodyAnswerMap.get(8).add(R.mipmap.melody_8_2);
        melodyAnswerMap.get(8).add(R.mipmap.melody_8_3);
        melodyAnswerMap.get(8).add(R.mipmap.melody_8_4);
        melodyNotationMap.put(8, new ArrayList<Integer>());//option
        melodyNotationMap.get(8).add(R.mipmap.melody_8_4);
        melodyNotationMap.get(8).add(R.mipmap.melody_8_4);
        melodyNotationMap.get(8).add(R.mipmap.melody_8_4);
        melodyNotationMap.get(8).add(R.mipmap.melody_8_4);
        Collections.shuffle(melodyNotationMap.get(8));
        melodySoundMap.put(8, R.raw.melodi8);//sound


        melodyQuestionMap.put(9, R.mipmap.melody_question_3);
        melodyAnswerMap.put(9, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(9).add(R.mipmap.melody_9_1);
        melodyAnswerMap.get(9).add(R.mipmap.melody_9_2);
        melodyAnswerMap.get(9).add(R.mipmap.melody_9_3);
        melodyAnswerMap.get(9).add(R.mipmap.melody_9_4);
        melodyNotationMap.put(9, new ArrayList<Integer>());//option
        melodyNotationMap.get(9).add(R.mipmap.melody_9_1);
        melodyNotationMap.get(9).add(R.mipmap.melody_9_2);
        melodyNotationMap.get(9).add(R.mipmap.melody_9_3);
        melodyNotationMap.get(9).add(R.mipmap.melody_9_4);
        Collections.shuffle(melodyNotationMap.get(9));
        melodySoundMap.put(9, R.raw.melodi9);//sound


        melodyQuestionMap.put(10, R.mipmap.melody_question_2);
        melodyAnswerMap.put(10, new ArrayList<Integer>());//answer
        melodyAnswerMap.get(10).add(R.mipmap.melody_10_1);
        melodyAnswerMap.get(10).add(R.mipmap.melody_10_2);
        melodyAnswerMap.get(10).add(R.mipmap.melody_10_3);
        melodyAnswerMap.get(10).add(R.mipmap.melody_10_4);
        melodyNotationMap.put(10, new ArrayList<Integer>());//option
        melodyNotationMap.get(10).add(R.mipmap.melody_10_1);
        melodyNotationMap.get(10).add(R.mipmap.melody_10_2);
        melodyNotationMap.get(10).add(R.mipmap.melody_10_3);
        melodyNotationMap.get(10).add(R.mipmap.melody_10_4);
        Collections.shuffle(melodyNotationMap.get(10));
        melodySoundMap.put(10, R.raw.melodi10);//sound

        if (type == 2) {
            setContentView(R.layout.activity_question_melody);

            builder = new AlertDialog.Builder(this);
            number_quiz = (TextView)findViewById(R.id.number_quiz);
            chance_quiz = (TextView)findViewById(R.id.chance_quiz);
            quiz_answer = (Button)findViewById(R.id.quiz_answer);
            quiz_backspace = (Button)findViewById(R.id.backspace_answer);
            quiz_clear = (Button)findViewById(R.id.clear_answer);
            answerOption = (EditText) findViewById(R.id.answer_option);
            listView = (ListView)findViewById(R.id.option_list);

            number_quiz.setText("No. "+numberQuiz+" ");
            number_quiz.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, melodyQuestionMap.get(numberQuiz));
            mediaPlayer = MediaPlayer.create(context, melodySoundMap.get(numberQuiz));
            chance_quiz.setText("Kesempatan : "+chance);

            compare = melodyAnswerMap.get(numberQuiz);

            answerOption.setHint("Total Notasi = "+compare.size());
            answerOption.setFilters(new InputFilter[] {new InputFilter.LengthFilter(compare.size())});
            answerOption.setEnabled(false);

            mediaPlayer.start();

            buildList(numberQuiz);


            number_quiz.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mediaPlayer.start();
                }
            });

            quiz_clear.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    answerOption.setText("");
                }
            });

            quiz_backspace.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String answer = answerOption.getText().toString();
                    try{
                        answer = answer.substring(0, answer.length() - 1);
                    }catch (Exception e){
                        Log.e("exception", e.toString());
                    }
                    answerOption.setText(answer);
                }
            });

            quiz_answer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(numberQuiz<11){
                        try {
                            String number = answerOption.getText().toString();
                            List<Integer> melodyOptionNotation = melodyNotationMap.get(numberQuiz);
                            Log.d("option", number);
                            String[] digits2 = number.split("(?<=.)");
                            for(String indexOption: digits2){
                                int selector = Integer.parseInt(indexOption);
                                userAnswer.add(melodyOptionNotation.get(selector-1));
                            }
                            Log.d("option1", compare.toString());
                            Log.d("option2", userAnswer.toString());



                            for(int i=0; i<userAnswer.size(); i++){
                                Log.d("option3", userAnswer.get(i)+"");
                                Log.d("option4", compare.get(i)+"");
                                if(compare.get(i).equals(userAnswer.get(i))){
                                    Log.d("option5", "right answer");
                                    right++;
                                }else {
                                    Toast.makeText(context, "Salah", Toast.LENGTH_SHORT).show();
                                    chance--;
                                    break;
                                }
                            }
                        }catch (Exception e){
                            Toast.makeText(context, "Salah", Toast.LENGTH_SHORT).show();
                            chance--;
                            Log.e("error", e.toString());
                        }

                    /*try{
                        for(String indexOption: answerOption.getText().toString().split("")){
                            int selector = Integer.parseInt(indexOption);
                            userAnswer.add(rhythmAnswerArray.get(selector-1));
                        }
                        List<Integer> compare = rhythmAnswerMap.get(numberQuiz);
                        for(int key : compare){
                            if(compare.get(key) == userAnswer.get(key)){
                                Log.d("option", "right answer");
                            }
                        }
                    }catch (Exception e){
                        Log.d("error", e.toString());
                    }*/
                        userAnswer.clear();
                        answerOption.setText("");
                        chance_quiz.setText("Kesempatan : "+chance);
                    /*rhythmQuestionArray
                    userAnswer*/

                        if(chance<1 || right==compare.size()){
                            Log.d("chance", chance+"");
                            Log.d("right", right+"");
                            if(mediaPlayer.isPlaying()){
                                mediaPlayer.stop();
                            }
                            if(right==compare.size()){
                                Toast.makeText(context, "Benar", Toast.LENGTH_SHORT).show();
                                score++;
                            }
                            chance = 3;
                            right = 0;
                            numberQuiz++;

                            if(numberQuiz<11){
                                compare.clear();
                                compare = melodyAnswerMap.get(numberQuiz);
                                try{
//                            questionArea.removeAllViews();
                                    buildList(numberQuiz);
                                    number_quiz.setText("No. "+numberQuiz+" ");
                                    number_quiz.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, melodyQuestionMap.get(numberQuiz));
                                    mediaPlayer = MediaPlayer.create(context, melodySoundMap.get(numberQuiz));
                                    chance_quiz.setText("Kesempatan : "+chance);

                                    answerOption.setHint("Total Notasi = "+compare.size());
                                    answerOption.setFilters(new InputFilter[] {new InputFilter.LengthFilter(compare.size())});
                                    mediaPlayer.start();

                                }catch (Exception e){
                                    Log.d("error", e.toString());
                                }
                            }else {
                                try{
                                    saveScoreUser(getUser(), score);
                                }catch (Exception e){
                                    Log.e("preference", e.toString());
                                }
                                builder.setMessage(getUser()+"\nTotal Benar "+score)
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //do things
                                                Intent activity = new Intent(context, MainActivity.class);
                                                startActivity(activity);
                                                finish();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }

                        }

                    }


                }
            });



        }else{
            setContentView(R.layout.activity_melody);

            sample_1 = (ImageView)findViewById(R.id.sample_1);
            sample_2 = (ImageView)findViewById(R.id.sample_2);

            sample_1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sample_melody_1);
                    mediaPlayer.start();
                }
            });

            sample_2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sample_melody_1);
                    mediaPlayer.start();
                }
            });

        }



    }

    void buildList(int numberQuiz){
        List<Integer> optionListMelody = melodyNotationMap.get(numberQuiz);
        optionList.clear();
        for(int i=0;i<optionListMelody.size();i++){
            Option option = new Option();
            option.setNumber(String.valueOf(i+1));
            option.setImage(optionListMelody.get(i));
            optionList.add(option);

        }

        OptionAdapter adapter = new OptionAdapter(getApplicationContext(), R.layout.option_list, optionList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Option option= optionList.get(position);
                String answer = answerOption.getText().toString();
                answerOption.setText(answer+option.getNumber());
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }catch (Exception e){
            Log.e("error", e.toString());
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        try{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }catch (Exception e){
            Log.e("error", e.toString());
        }
    }


    @Override
    protected void onPause(){
        super.onPause();
        try{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }catch (Exception e){
            Log.e("error", e.toString());
        }
    }


    void saveScoreUser(String user, int score){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(user+".melody", score);
        editor.apply();
    }

    String getUser(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("A", "no_Name");
    }

}
