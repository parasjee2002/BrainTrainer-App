package com.converter.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button gobtn, button2, button3, button4, button5, play;
    TextView sum, correct, score, timer;
    int locationofCorrectAnswer;
    ArrayList<Integer> answer = new ArrayList<>();
    int scr = 0;
    int numberofGamesPlayed = 0;

    public void chooseAnswer(View view) {

        if (String.valueOf(locationofCorrectAnswer).equals(view.getTag().toString())) {
//            Log.i("message","You got it");
            correct.setText("Correct!");
            scr++;
        } else {
//            Log.i("message","Wrong Answer");
            correct.setText("Wrong!");
        }
        numberofGamesPlayed++;
        score.setText(scr + "/" + numberofGamesPlayed);
        newQuestion();
    }
    public void playAgain(View view){

        scr=0;
        numberofGamesPlayed=0;x
        timer.setText("30s");
        score.setText(scr + "/" + numberofGamesPlayed);

        newQuestion();
        play.setVisibility(View.INVISIBLE);

        CountDownTimer countDownTimer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                correct.setText("Done");
                play.setVisibility(View.VISIBLE);
            }
        }.start();
    }


    public void newQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sum.setText(a + "+" + b);
        locationofCorrectAnswer = rand.nextInt(4);
        answer.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationofCorrectAnswer) {
                answer.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        button2.setText(String.valueOf(answer.get(0)));
        button3.setText(String.valueOf(answer.get(1)));
        button4.setText(String.valueOf(answer.get(2)));
        button5.setText(String.valueOf(answer.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gobtn = findViewById(R.id.gobtn);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        sum = findViewById(R.id.sum);
        correct = findViewById(R.id.correct);
        timer=findViewById(R.id.timer);
        play=findViewById(R.id.play);
//        newQuestion();
        playAgain(findViewById(R.id.correct));

    }
}