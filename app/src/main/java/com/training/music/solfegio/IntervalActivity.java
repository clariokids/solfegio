package com.training.music.solfegio;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rio on 5/27/2017.
 */

public class IntervalActivity extends AppCompatActivity {

    private final static String TYPE = "type";
    int numberQuiz;
    int type;
    int score = 0;

    MediaPlayer mediaPlayer;
    Context context;
    AlertDialog.Builder builder;


    HashMap<Integer, Integer> intervalAnswerMap = new HashMap<Integer, Integer>();
    List<Integer> quizPerfect = new ArrayList<Integer>();
    List<Integer> quizSoundPerfect = new ArrayList<Integer>();
    List<Integer> quizMajor = new ArrayList<Integer>();
    List<Integer> quizSoundMajor = new ArrayList<Integer>();
    List<Option> optionList = new ArrayList<>();
    List<Integer> questionCollection = new ArrayList<Integer>();


    ListView listView;
    Button number_quiz, perfect_btn, major_btn;
    ImageView sample_1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        type = intent.getIntExtra(TYPE, 0);

        numberQuiz = 0;
        context=this;

        //mappping right answer
        //perfect
        intervalAnswerMap.put(R.raw.perfect_1, R.mipmap.perfect_1);
        intervalAnswerMap.put(R.raw.perfect_2, R.mipmap.perfect_2);
        intervalAnswerMap.put(R.raw.perfect_3, R.mipmap.perfect_3);
        intervalAnswerMap.put(R.raw.perfect_4, R.mipmap.perfect_4);
        intervalAnswerMap.put(R.raw.perfect_5, R.mipmap.perfect_5);
        intervalAnswerMap.put(R.raw.perfect_6, R.mipmap.perfect_6);
        intervalAnswerMap.put(R.raw.perfect_7, R.mipmap.perfect_7);
        intervalAnswerMap.put(R.raw.perfect_8, R.mipmap.perfect_8);
        intervalAnswerMap.put(R.raw.perfect_9, R.mipmap.perfect_9);
        intervalAnswerMap.put(R.raw.perfect_10, R.mipmap.perfect_10);


        //major
        intervalAnswerMap.put(R.raw.mayor_1, R.mipmap.mayor_1);
        intervalAnswerMap.put(R.raw.mayor_2, R.mipmap.mayor_2);
        intervalAnswerMap.put(R.raw.mayor_3, R.mipmap.mayor_3);
        intervalAnswerMap.put(R.raw.mayor_4, R.mipmap.mayor_4);
        intervalAnswerMap.put(R.raw.mayor_5, R.mipmap.mayor_5);
        intervalAnswerMap.put(R.raw.mayor_6, R.mipmap.mayor_6);
        intervalAnswerMap.put(R.raw.mayor_7, R.mipmap.mayor_7);
        intervalAnswerMap.put(R.raw.mayor_8, R.mipmap.mayor_8);
        intervalAnswerMap.put(R.raw.mayor_9, R.mipmap.mayor_9);
        intervalAnswerMap.put(R.raw.mayor_10, R.mipmap.mayor_10);


        //add number of quiz
        //perfect
        quizPerfect.add(R.mipmap.perfect_1);
        quizPerfect.add(R.mipmap.perfect_2);
        quizPerfect.add(R.mipmap.perfect_3);
        quizPerfect.add(R.mipmap.perfect_4);
        quizPerfect.add(R.mipmap.perfect_5);
        quizPerfect.add(R.mipmap.perfect_6);
        quizPerfect.add(R.mipmap.perfect_7);
        quizPerfect.add(R.mipmap.perfect_8);
        quizPerfect.add(R.mipmap.perfect_9);
        quizPerfect.add(R.mipmap.perfect_10);

        quizSoundPerfect.add(R.raw.perfect_1);
        quizSoundPerfect.add(R.raw.perfect_2);
        quizSoundPerfect.add(R.raw.perfect_3);
        quizSoundPerfect.add(R.raw.perfect_4);
        quizSoundPerfect.add(R.raw.perfect_5);
        quizSoundPerfect.add(R.raw.perfect_6);
        quizSoundPerfect.add(R.raw.perfect_7);
        quizSoundPerfect.add(R.raw.perfect_8);
        quizSoundPerfect.add(R.raw.perfect_9);
        quizSoundPerfect.add(R.raw.perfect_10);

        //major
        quizMajor.add(R.mipmap.mayor_1);
        quizMajor.add(R.mipmap.mayor_2);
        quizMajor.add(R.mipmap.mayor_3);
        quizMajor.add(R.mipmap.mayor_4);
        quizMajor.add(R.mipmap.mayor_5);
        quizMajor.add(R.mipmap.mayor_6);
        quizMajor.add(R.mipmap.mayor_7);
        quizMajor.add(R.mipmap.mayor_8);
        quizMajor.add(R.mipmap.mayor_9);
        quizMajor.add(R.mipmap.mayor_10);

        quizSoundMajor.add(R.raw.mayor_1);
        quizSoundMajor.add(R.raw.mayor_2);
        quizSoundMajor.add(R.raw.mayor_3);
        quizSoundMajor.add(R.raw.mayor_4);
        quizSoundMajor.add(R.raw.mayor_5);
        quizSoundMajor.add(R.raw.mayor_6);
        quizSoundMajor.add(R.raw.mayor_7);
        quizSoundMajor.add(R.raw.mayor_8);
        quizSoundMajor.add(R.raw.mayor_9);
        quizSoundMajor.add(R.raw.mayor_10);

        //build question
        Collections.shuffle(quizSoundPerfect);
        Collections.shuffle(quizSoundMajor);

        for(int perfect:quizSoundPerfect){
            questionCollection.add(perfect);
        }
        /*questionCollection.add(quizSoundPerfect.get(0));
        questionCollection.add(quizSoundPerfect.get(1));
        questionCollection.add(quizSoundPerfect.get(2));
        questionCollection.add(quizSoundPerfect.get(3));
        questionCollection.add(quizSoundPerfect.get(4));*/

        for(int major:quizSoundMajor){
            questionCollection.add(major);
        }

        Collections.shuffle(questionCollection);


//        rhythmNotationMap.put(1, R.mipmap.notasi_1);

        if (type == 2) {
            setContentView(R.layout.activity_question_interval);

            builder = new AlertDialog.Builder(this);
            listView = (ListView)findViewById(R.id.option_list);
            number_quiz = (Button)findViewById(R.id.number_quiz);
            perfect_btn = (Button)findViewById(R.id.perfect_btn);
            major_btn = (Button)findViewById(R.id.major_btn);

            number_quiz.setText("Soal "+(numberQuiz+1));
//            buildList(quizPerfect);
            /*for(int i=0;i<10;i++){
                Option option = new Option();
                option.setNumber(String.valueOf(i+1));
                option.setImage(quizPerfect.get(i));
                optionList.add(option);

            }

            OptionAdapter adapter = new OptionAdapter(getApplicationContext(), R.layout.option_list, optionList);
            listView.setAdapter(adapter);*/

            mediaPlayer = MediaPlayer.create(context, questionCollection.get(numberQuiz));
            mediaPlayer.start();



            number_quiz.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                }
            });

            perfect_btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                  /*  perfect_btn.setBackgroundResource(R.color.colorPrimary);
                    perfect_btn.setTextColor(Color.WHITE);
                    major_btn.setBackgroundResource(android.R.drawable.btn_default);*/
                  if(numberQuiz<10){
                      boolean mark = false;
                      int compare = intervalAnswerMap.get(questionCollection.get(numberQuiz));
                      for(int checkAnswer:quizPerfect){
                          Log.d("compare", compare+"");
                          Log.d("checkAnswer", checkAnswer+"");
                          if(compare == checkAnswer){
                              mark = true;
                              score++;
                              break;
                          }
                      }
                      if(mark){
                          Toast.makeText(context, "Benar", Toast.LENGTH_SHORT).show();
                      }else {
                          Toast.makeText(context, "Salah", Toast.LENGTH_SHORT).show();
                      }
                      numberQuiz++;

                      buildList(numberQuiz);
                      if(numberQuiz<10){
                          number_quiz.setText("Soal "+(numberQuiz+1));
                          mediaPlayer = MediaPlayer.create(context, questionCollection.get(numberQuiz));
                          mediaPlayer.start();
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
                                          finish();
                                      }
                                  });
                          AlertDialog alert = builder.create();
                          alert.show();
                      }
                  }


//                    buildList(quizPerfect);
                }
            });

            major_btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    /*major_btn.setBackgroundResource(R.color.colorPrimary);
                    major_btn.setTextColor(Color.WHITE);
                    perfect_btn.setBackgroundResource(android.R.drawable.btn_default);*/
                    if(numberQuiz<10){
                        boolean mark = false;
                        int compare = intervalAnswerMap.get(questionCollection.get(numberQuiz));
                        for(int checkAnswer:quizMajor){
                            Log.d("compare", compare+"");
                            Log.d("checkAnswer", checkAnswer+"");
                            if(compare == checkAnswer){
                                mark = true;
                                score++;
                                break;
                            }
                        }
                        if(mark){
                            Toast.makeText(context, "Benar", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Salah", Toast.LENGTH_SHORT).show();
                        }
                        numberQuiz++;
                        buildList(numberQuiz);
                        if(numberQuiz<10){
                            number_quiz.setText("Soal "+(numberQuiz+1));
                            mediaPlayer = MediaPlayer.create(context, questionCollection.get(numberQuiz));
                            mediaPlayer.start();
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
//                    buildList(quizMajor);
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    /*if(numberQuiz<10){
                        Log.d("index", numberQuiz+"");
                        try {
                            int keyAnswer = questionCollection.get(numberQuiz);

                            if(quizPerfect.get(position).equals(intervalAnswerMap.get(keyAnswer))){
                                score++;
                            }
                            if(quizMajor.get(position).equals(intervalAnswerMap.get(keyAnswer))){
                                score++;
                            }
                            numberQuiz++;
                            if(numberQuiz<10){
                                number_quiz.setText("Soal "+(numberQuiz+1));
                                mediaPlayer = MediaPlayer.create(context, questionCollection.get(numberQuiz));
                                mediaPlayer.start();
                            }else {
                                Toast.makeText(context, "Benar "+score, Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Log.e("exc", e.toString());
                        }

                    }else {
                        Toast.makeText(context, "Benar "+score, Toast.LENGTH_SHORT).show();
                    }*/

                }
            });

        }else {
            setContentView(R.layout.activity_interval);

            sample_1 = (ImageView)findViewById(R.id.sample_1);

            sample_1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sample_interval_1);
                    mediaPlayer.start();
                }
            });

        }



    }

    void buildList(int numberQuiz){
//        optionList.clear();
        int quizIndex = questionCollection.get(numberQuiz-1);
        Option option = new Option();
        option.setNumber("Jawaban Nomor "+String.valueOf(numberQuiz)+" ");
        option.setImage(intervalAnswerMap.get(quizIndex));
        optionList.add(option);
        /*for(int i=0;i<numberQuiz;i++){
            Option option = new Option();
            option.setNumber(String.valueOf(i+1));
            option.setImage(resourceData.get(i));
            optionList.add(option);

        }*/

        OptionAdapter adapter = new OptionAdapter(getApplicationContext(), R.layout.option_list, optionList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setSelection(numberQuiz-1);
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
        editor.putInt(user+".interval", score);
        editor.apply();
    }

    String getUser(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("A", "no_Name");
    }
}
