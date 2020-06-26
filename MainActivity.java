package com.example.braintester;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView resultText;
    TextView questionText;
    int score = 0;
    int noOfQuestions = 0;
    TextView scoreText;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerText;
    Button playAgain;
    ConstraintLayout gameLayout;

    public void PlayAgain(View view) {
        score = 0;
        noOfQuestions = 0;
        timerText.setText("30s");
        scoreText.setText(Integer.toString(score) + " / " + Integer.toString(noOfQuestions));
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);
        resultText.setText("");

        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf((l / 1000)) + "s");
            }

            @Override
            public void onFinish() {
                resultText.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Your score is " + score, Toast.LENGTH_SHORT).show();
            }
        }.start();

    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        PlayAgain(playAgain);
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultText.setText("Correct!!");
            score++;
        } else {
            resultText.setText("Wrong :( ");
        }
        noOfQuestions++;
        scoreText.setText(Integer.toString(score) + " / " + Integer.toString(noOfQuestions));
        newQuestion();

    }

    public void newQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(21);
        int num2 = random.nextInt(21);
        String text1 = Integer.toString(num2);
        String text2 = " + ";
        String text3 = Integer.toString(num1);
        questionText.setText(text1 + text2 + text3);
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();


        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(num1 + num2);
            } else {
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == num1 + num2) {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(random.nextInt(41));
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionText = findViewById(R.id.questionText);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultText = findViewById(R.id.resultText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        goButton = findViewById(R.id.goButton);
        playAgain = findViewById(R.id.playAgain);
        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}