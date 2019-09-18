package com.example.mathgame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

@SuppressLint("SetTextI18n")
public class GameActivity extends Activity implements View.OnClickListener {

    int correctAnswer;
    Button firstButton;
    Button secondButton;
    Button thirdButton;
    TextView firstNumber;
    TextView secondNumber;
    TextView operand;
    TextView score;
    TextView level;
    int currentScore = 0;
    int currentLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        operand = findViewById(R.id.operand);
        firstButton = findViewById(R.id.button1);
        secondButton = findViewById(R.id.button2);
        thirdButton = findViewById(R.id.button3);
        score = findViewById(R.id.score);
        level = findViewById(R.id.level);

        firstButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        thirdButton.setOnClickListener(this);

        setQuestion();
    }

    @Override
    public void onClick(View view) {
        int answerGiven = 0;
        switch (view.getId()){
            case R.id.button1:
                answerGiven = Integer.parseInt("" + firstButton.getText());
                break;
            case R.id.button2:
                answerGiven = Integer.parseInt("" + secondButton.getText());
                break;
            case R.id.button3:
                answerGiven = Integer.parseInt("" + thirdButton.getText());
                break;
        }

        updateScoreAndLevel(answerGiven);
        setQuestion();
    }

    public void updateScoreAndLevel(int answerGiven){
        if (isCorrect(answerGiven)){
            for (int i = 1; i <= currentLevel; i++) {
                currentScore = currentScore + i;
            }
            currentLevel++;
        } else {
            currentScore = 0;
            currentLevel = 1;
        }

        score.setText("Score: " + currentScore);
        level.setText("Level: " + currentLevel);
    }

    public boolean isCorrect(int answerGiven){
        boolean correctTrueOrFalse;
        if (answerGiven == correctAnswer){
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = true;
        } else {
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = false;
        }

        return correctTrueOrFalse;
    }


    public void setQuestion(){
        int numberRange = currentLevel * 3;
        Random random = new Random();
        int partA = random.nextInt(numberRange);
        partA++;
        int partB = random.nextInt(numberRange);
        partB++;

        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer - 2;
        int wrongAnswer2 = correctAnswer + 2;

        firstNumber.setText("" + partA);
        secondNumber.setText("" + partB);
        operand.setText("x");

        int buttonLayout = random.nextInt(3);
        switch (buttonLayout){
            case 0:
                firstButton.setText("" + correctAnswer);
                secondButton.setText("" + wrongAnswer1);
                thirdButton.setText("" + wrongAnswer2);
                break;
            case 1:
                firstButton.setText("" + wrongAnswer2);
                secondButton.setText("" + correctAnswer);
                thirdButton.setText("" + wrongAnswer1);
                break;
            case 2:
                firstButton.setText("" + wrongAnswer1);
                secondButton.setText("" + wrongAnswer2);
                thirdButton.setText("" + correctAnswer);
                break;
        }
    }

}
