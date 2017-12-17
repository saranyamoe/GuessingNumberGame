package com.saranyamoellers.guessingnumbergame.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saranyamoellers.guessingnumbergame.R;
import com.saranyamoellers.guessingnumbergame.model.Game;
import com.saranyamoellers.guessingnumbergame.model.GamePage;

import java.util.Stack;

public class GameActivity extends AppCompatActivity {

    //declare variables
    public static final String TAG = GameActivity.class.getSimpleName();
    private TextView questionTextView;
    private TextView hintTextView;
    private TextView answerTextView;
    private TextView numberOfGuess;
    private TextView resultTextView;
    private EditText numberEditText;
    private Button submitButton;
    private Button quitButton;
    private Button nextLevelButton;
    int answer;
    int countNumberOfGuess = 0 ;
    int pageIndex = 0;
    int lastIndex = 0;
    private Game game;
    String name;
    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //set variable to the layout
        questionTextView = findViewById(R.id.questionTextView);
        hintTextView = findViewById(R.id.hintTextView);
        answerTextView = findViewById(R.id.answerTextView);
        numberOfGuess = findViewById(R.id.numberOfGuessTextView);
        resultTextView = findViewById(R.id.resultTextView);
        numberEditText = findViewById(R.id.numberEditText);
        submitButton = findViewById(R.id.submitButton);
        quitButton = findViewById(R.id.quitButton);
        nextLevelButton = findViewById(R.id.nextLevelButton);

        Intent intent = getIntent();
        name = intent.getStringExtra( getString(R.string.key_name) );
        if (name == null || name.isEmpty() ) {
            name = "Friend";
        }
        //Toast.makeText(this, "Hello "+ name , Toast.LENGTH_SHORT).show();
        Log.d(TAG, name);
        //set value game variable
        game = new Game();
        //load page or data
        loadGamePage(pageIndex);
        playGame();
    }


    public void loadGamePage(final int pageNumber) {

        pageStack.push(pageNumber); // page loads and push a page into a stack
        final GamePage gameCurrentPage = game.getGamePage(pageNumber);
        questionTextView.setText(gameCurrentPage.getTextQuestionId());
       // hintTextView.setText(gameCurrentPage.getHintId());
        answer = gameCurrentPage.getAnswerNumber();  // answer key
        lastIndex = game.getLastIndex();

        //Toast.makeText(GameActivity.this, "Answer number is " + answer, Toast.LENGTH_SHORT).show();
        //Toast.makeText(GameActivity.this, "pageIndex is " + pageIndex, Toast.LENGTH_LONG).show();
        answerTextView.setText(R.string.question_mark);
        numberOfGuess.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        countNumberOfGuess = 0;

        //animateScale questionTextView
        Animation animation = new TranslateAnimation(0, 0,0 , 100);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        questionTextView.startAnimation(animation);

        questionTextView.setVisibility(View.VISIBLE);

        quitButton.setText(getString(R.string.quit_choice1 ));
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.this.finish();
                //Toast.makeText(GameActivity.this, "I am quit", Toast.LENGTH_SHORT).show();
            }
        });

        //nextLevelButton.setText(getString(R.string.next_level_choice2));
        nextLevelButton.setVisibility(View.GONE);
        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(GameActivity.this, pageNumber+1 +"", Toast.LENGTH_LONG).show();
                //create method to get index of gamePageList
                //int index = game.getIndex(gameCurrentPage);
                //Toast.makeText(GameActivity.this, answer+"", Toast.LENGTH_LONG).show();
                pageIndex = pageIndex + 1;
                loadGamePage(pageIndex);
                numberEditText.setText("");
            }
        });


    }


    private void playGame() {

        //if the submit button is clicked show number of guessed
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the answer from GamePageList
               // answerTextView.setText(answer +""); // to show a correct answer
                //get numberEditText
                String stringNumber = numberEditText.getText().toString();

                // check if user put in number in EditText
                if (stringNumber.isEmpty()) {

                    Toast.makeText(GameActivity.this, "Guess your number please", Toast.LENGTH_LONG).show();

                }
                // if user type in number
                else {

                    int intNumber = Integer.parseInt(stringNumber);
                    Log.d(TAG, "This is an user input = " + (intNumber + 7));
                    //if the answer is correct
                    if (intNumber == answer){
                        answerTextView.setText(answer +"");
                        resultTextView.setVisibility(View.VISIBLE);

                        resultTextView.setText("Correct !!");
                        resultTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                        animateScale();



                        // checking if is NOT the last page : display the correct text on
                        if(pageIndex  != lastIndex ) {
                            //Toast.makeText(GameActivity.this, "PageIndex is " + pageIndex +" ,lastIndex is "+ lastIndex, Toast.LENGTH_LONG).show();
                            nextLevelButton.setVisibility(View.VISIBLE);
                            nextLevelButton.setText(getString(R.string.next_level_choice2));
                        }
                        // else it is the last page
                        else {
                            Toast.makeText(GameActivity.this, "Bye " + name , Toast.LENGTH_LONG).show();
                        }

                    }
                    //if the answer is wrong
                    else {
                        numberEditText.setText("");
                        resultTextView.setVisibility(View.VISIBLE);
                        resultTextView.setText("Incorrect");
                        resultTextView.setTextColor(Color.parseColor("#e4ffffff"));
                        animateScale();
                    }
                    countNumberOfGuess = countNumberOfGuess + 1;
                    Log.d(TAG,"this is count of guess = "+ countNumberOfGuess);

                }
                numberOfGuess.setVisibility(View.VISIBLE);
                animateScale();
                numberOfGuess.setText(countNumberOfGuess + " guess/es");



            }
        });


    }


    private void animateScale() {
        ObjectAnimator scalex = ObjectAnimator.ofFloat(numberOfGuess, "scaleX", 0, 1);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(numberOfGuess, "scaleY", 0, 1);
        AnimatorSet scaleNumberOfGuess = new AnimatorSet();
        scaleNumberOfGuess.playTogether(scalex, scaley);

        ObjectAnimator scaleResultTextViewX = ObjectAnimator.ofFloat(resultTextView, "scaleX", 0, 1);
        ObjectAnimator scaleResultTextViewY = ObjectAnimator.ofFloat(resultTextView, "scaleY", 0, 1);
        AnimatorSet scaleResultTextView = new AnimatorSet();
        scaleResultTextView.playTogether(scaleResultTextViewX, scaleResultTextViewY);

       // int nextLevelButtonStartValue = nextLevelButton.getTop();
        //  int nextLevelButtonEndValue = nextLevelButton.getBottom();
        ObjectAnimator nextLevelButtonObjectX = ObjectAnimator.ofFloat(nextLevelButton, "rotationX", 0f, 360f);
        ObjectAnimator nextLevelButtonObjectY = ObjectAnimator.ofFloat(nextLevelButton, "rotationY", 0f, 360f);
        AnimatorSet nextLevelButtonObjectSet = new AnimatorSet();
        nextLevelButtonObjectSet.setStartDelay(1000);
        nextLevelButtonObjectSet.setDuration(600);
        nextLevelButtonObjectSet.playTogether(nextLevelButtonObjectX, nextLevelButtonObjectY);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(scaleNumberOfGuess, scaleResultTextView, nextLevelButtonObjectSet);
        set.start();
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if(pageStack.isEmpty()){
            super.onBackPressed();// if it is empty mean no page is stacked on MainActivity
        }
        else {
            loadGamePage(pageStack.pop()); //.pop means remove out of stack
        }

    }

}
