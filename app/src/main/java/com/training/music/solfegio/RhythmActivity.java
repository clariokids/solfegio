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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rio on 5/27/2017.
 */

public class RhythmActivity extends AppCompatActivity {

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
    ImageView sample_1, sample_2, sample_3, sample_4;

    List<Integer> rhythmAnswerArray = new ArrayList<Integer>();
//    List<Integer> rhythmQuestionArray = new ArrayList<Integer>();
    List<Option> optionList = new ArrayList<>();

    List<Integer> userAnswer = new ArrayList<Integer>();
    List<Integer> compare = new ArrayList<Integer>();
    HashMap<Integer, Integer> rhythmNotationMap = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> rhythmQuestionMap = new HashMap<Integer, Integer>();
    HashMap<Integer, List<Integer>>  rhythmAnswerMap = new HashMap<Integer, List<Integer>>();
    HashMap<Integer, Integer> rhythmSoundMap = new HashMap<Integer, Integer>();
    HashMap<Integer, List<Integer>> answerMap = new HashMap<Integer, List<Integer>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        type = intent.getIntExtra(TYPE, 0);

        numberQuiz = 1;
        context = this;

        //map notasi
        rhythmNotationMap.put(1, R.mipmap.notasi_1);
        rhythmNotationMap.put(2, R.mipmap.notasi_2);
        rhythmNotationMap.put(3, R.mipmap.notasi_3);
        rhythmNotationMap.put(4, R.mipmap.notasi_4);
        //soal 1
        rhythmQuestionMap.put(1, R.mipmap.rhythm_question_1);
        rhythmAnswerMap.put(1, new ArrayList<Integer>());

        rhythmAnswerMap.get(1).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(1).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(1).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(1).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(1).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(1).add(R.mipmap.notasi_2);

        rhythmSoundMap.put(1, R.raw.ritme1);

        //soal 2
        rhythmQuestionMap.put(2, R.mipmap.rhythm_question_1);
        rhythmAnswerMap.put(2, new ArrayList<Integer>());
        rhythmAnswerMap.get(2).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(2).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(2).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(2).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(2).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(2).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(2).add(R.mipmap.notasi_2);

        rhythmSoundMap.put(2, R.raw.ritme2);

        //soal 3
        rhythmQuestionMap.put(3, R.mipmap.rhythm_question_2);
        rhythmAnswerMap.put(3, new ArrayList<Integer>());
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(3).add(R.mipmap.notasi_2);

        rhythmSoundMap.put(3, R.raw.ritme3);

        //soal 4
        rhythmQuestionMap.put(4, R.mipmap.rhythm_question_2);
        rhythmAnswerMap.put(4, new ArrayList<Integer>());
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(4).add(R.mipmap.notasi_2);

        rhythmSoundMap.put(4, R.raw.ritme4);

        //soal 5
        rhythmQuestionMap.put(5, R.mipmap.rhythm_question_3);
        rhythmAnswerMap.put(5, new ArrayList<Integer>());
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(5).add(R.mipmap.notasi_4);

        rhythmSoundMap.put(5, R.raw.ritme5);

        //soal 6
        rhythmQuestionMap.put(6, R.mipmap.rhythm_question_3);
        rhythmAnswerMap.put(6, new ArrayList<Integer>());
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(6).add(R.mipmap.notasi_4);

        rhythmSoundMap.put(6, R.raw.ritme6);

        //soal 7
        rhythmQuestionMap.put(7, R.mipmap.rhythm_question_4);
        rhythmAnswerMap.put(7, new ArrayList<Integer>());
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(7).add(R.mipmap.notasi_2);

        rhythmSoundMap.put(7, R.raw.ritme7);

        //soal 8
        rhythmQuestionMap.put(8, R.mipmap.rhythm_question_4);
        rhythmAnswerMap.put(8, new ArrayList<Integer>());
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(8).add(R.mipmap.notasi_2);

        rhythmSoundMap.put(8, R.raw.ritme8);

        //soal 9
        rhythmQuestionMap.put(9, R.mipmap.rhythm_question_4);
        rhythmAnswerMap.put(9, new ArrayList<Integer>());
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(9).add(R.mipmap.notasi_3);

        rhythmSoundMap.put(9, R.raw.ritme9);

        //soal 10
        rhythmQuestionMap.put(10, R.mipmap.rhythm_question_4);
        rhythmAnswerMap.put(10, new ArrayList<Integer>());
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_1);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_2);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_4);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_3);
        rhythmAnswerMap.get(10).add(R.mipmap.notasi_1);

        rhythmSoundMap.put(10, R.raw.ritme10);


        if (type == 2){
            setContentView(R.layout.activity_question_rhythm);

            builder = new AlertDialog.Builder(this);
            number_quiz = (TextView)findViewById(R.id.number_quiz);
            chance_quiz = (TextView)findViewById(R.id.chance_quiz);
            quiz_answer = (Button)findViewById(R.id.quiz_answer);
            quiz_backspace = (Button)findViewById(R.id.backspace_answer);
            quiz_clear = (Button)findViewById(R.id.clear_answer);
            answerOption = (EditText) findViewById(R.id.answer_option);
            listView = (ListView)findViewById(R.id.option_list);
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            number_quiz.setText("No. "+numberQuiz+" ");
            number_quiz.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, rhythmQuestionMap.get(numberQuiz));
            mediaPlayer = MediaPlayer.create(context, rhythmSoundMap.get(numberQuiz));
            chance_quiz.setText("Kesempatan : "+chance);

            compare = rhythmAnswerMap.get(numberQuiz);

            answerOption.setHint("Total Notasi = "+compare.size());
            answerOption.setFilters(new InputFilter[] {new InputFilter.LengthFilter(compare.size())});
            answerOption.setEnabled(false);

            mediaPlayer.start();

//            Collections.shuffle(rhythmQuestionArray);

//            questionArea.addView(rhythmQuestion);
            /*optionCounter = 0;
            for(int image: rhythmQuestionArray) {
                *//*rhythmOption = new ImageView(context);
                rhythmOption.setImageResource(image);*//*
                rhythmAnswerArray.add(image);
                optionCounter++;
                sortItem = new TextView(context);
                sortItem.setText(""+optionCounter);
                sortItem.setCompoundDrawablesWithIntrinsicBounds(image, 0, 0, 0);
                questionArea.addView(sortItem);
            }*/

            for(int i=1;i<5;i++){
                Option option = new Option();
                option.setNumber(String.valueOf(i));
                option.setImage(rhythmNotationMap.get(i));
                optionList.add(option);

            }
            Log.d("option", optionList.toString());
            OptionAdapter adapter = new OptionAdapter(getApplicationContext(), R.layout.option_list, optionList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Option option= optionList.get(position);
                    String answer = answerOption.getText().toString();
                    answerOption.setText(answer+option.getNumber());
                }
            });

            /*sortItem = new TextView(context);
            sortItem.setText("1");
            sortItem.setCompoundDrawablesWithIntrinsicBounds(rhythmNotationMap.get(1), 0, 0, 0);
            questionArea.addView(sortItem);

            sortItem = new TextView(context);
            sortItem.setText("2");
            sortItem.setCompoundDrawablesWithIntrinsicBounds(rhythmNotationMap.get(2), 0, 0, 0);
            questionArea.addView(sortItem);

            sortItem = new TextView(context);
            sortItem.setText("3");
            sortItem.setCompoundDrawablesWithIntrinsicBounds(rhythmNotationMap.get(3), 0, 0, 0);
            questionArea.addView(sortItem);

            sortItem = new TextView(context);
            sortItem.setText("4");
            sortItem.setCompoundDrawablesWithIntrinsicBounds(rhythmNotationMap.get(4), 0, 0, 0);
            questionArea.addView(sortItem);

            sortItem = new TextView(context);
            sortItem.setText("5");
            sortItem.setCompoundDrawablesWithIntrinsicBounds(rhythmNotationMap.get(5), 0, 0, 0);
            questionArea.addView(sortItem);*/


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
                            Log.d("option", number);
                            String[] digits2 = number.split("(?<=.)");
                            for(String indexOption: digits2){
                                int selector = Integer.parseInt(indexOption);
                                userAnswer.add(rhythmNotationMap.get(selector));
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
                                score++;
                            }
                            chance = 3;
                            right = 0;
                            numberQuiz++;
                            if(numberQuiz<11){
                                compare.clear();
                                compare = rhythmAnswerMap.get(numberQuiz);
                                try{
//                            questionArea.removeAllViews();

                                    number_quiz.setText("No. "+numberQuiz+" ");
                                    number_quiz.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, rhythmQuestionMap.get(numberQuiz));
                                    mediaPlayer = MediaPlayer.create(context, rhythmSoundMap.get(numberQuiz));
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
            setContentView(R.layout.activity_rhythm);

            sample_1 = (ImageView)findViewById(R.id.sample_1);
            sample_2 = (ImageView)findViewById(R.id.sample_2);
            sample_3 = (ImageView)findViewById(R.id.sample_3);
            sample_4 = (ImageView)findViewById(R.id.sample_4);

            sample_1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sample_rhythm_1);
                    mediaPlayer.start();
                }
            });

            sample_2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sample_rhythm_2);
                    mediaPlayer.start();
                }
            });

            sample_3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sample_rhythm_3);
                    mediaPlayer.start();
                }
            });

            sample_3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sample_rhythm_4);
                    mediaPlayer.start();
                }
            });
        }





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
        editor.putInt(user+".rhythm", score);
        editor.apply();
    }

    String getUser(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("A", "no_Name");
    }

}
