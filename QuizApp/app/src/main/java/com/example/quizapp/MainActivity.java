package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView question, score, title;
    Button option1, option2, option3, option4, next;

    ArrayList<Questions> questionList = new ArrayList<>();
    Questions questionTxt;
    Questions currentQuestion;
    static ArrayList<Integer> previouslyUsedNumbers = new ArrayList<>();
    int numberOfQuestionPerRound = 5;

    public static int correctAnswers =0;
    public static int incorrectAnswers =0;

    Random random = new Random();
    String correct = "Correct!";
    String incorrect = "Incorrect!";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.tvQuestion);
        score = findViewById(R.id.tvScore);
        title = findViewById(R.id.tvTitle);
        option1 = findViewById(R.id.btn1);
        option2 = findViewById(R.id.btn2);
        option3 = findViewById(R.id.btn3);
        option4 = findViewById(R.id.btn4);
        next = findViewById(R.id.btnNext);
        fillData();
        displayQuestion();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //if the question is last question then after clicking it will show score
                if (isGameOver())
                {
                    displayScore();
                }

                //if the question is not the last question then after clicking it will show next question
                else
                {
                    displayQuestion();
                    option1.setBackgroundColor(0x9AD5EF); // 0xAARRGGBB
                    option2.setBackgroundColor(0x9AD5EF);
                    option3.setBackgroundColor(0x9AD5EF);
                    option4.setBackgroundColor(0x9AD5EF);
                }
            }
        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionTxt = currentQuestion;
                String answer = (String) option1.getText();
                option1.setBackgroundColor(0x4CAF50);
                //if selected option is right answer then it will increase 1 correct point and background color of button will turn into green
                if (questionTxt.validateAnswer(answer))
                {
                    incrementCorrectAnswers();

                    option1.setBackgroundColor(0x4CAF50);
                    option2.setBackgroundColor(0x9AD5EF);
                    option3.setBackgroundColor(0x9AD5EF);
                    option4.setBackgroundColor(0x9AD5EF);
                }

                //if selected option is wrong answer then it will increase 1 incorrect point and background color of button will turn into red
                else
                {

                    incrementIncorrectAnswers();

                    option1.setBackgroundColor(0xF44336);

                }


                //after click, all option will be disabled, correct/incorrect message will shown and next button will be enable
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                next.setEnabled(true);

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionTxt = currentQuestion;
                String answer = (String) option2.getText();

                //if selected option is right answer then it will increase 1 correct point and background color of button will turn into green
                if (questionTxt.validateAnswer(answer))
                {
                    incrementCorrectAnswers();

                    option1.setBackgroundColor(0x9AD5EF);
                    option2.setBackgroundColor(0x4CAF50);
                    option3.setBackgroundColor(0x9AD5EF);
                    option4.setBackgroundColor(0x9AD5EF);
                }

                //if selected option is wrong answer then it will increase 1 incorrect point and background color of button will turn into red
                else
                {
                    incrementIncorrectAnswers();
                    option2.setBackgroundColor(0xF44336);

                }

                //after click, all option will be disabled, correct/incorrect message will shown and next button will be enable
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                next.setEnabled(true);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionTxt = currentQuestion;
                String answer = (String) option3.getText();

                //if selected option is right answer then it will increase 1 correct point and background color of button will turn into green
                if (questionTxt.validateAnswer(answer))
                {
                    incrementCorrectAnswers();

                    option1.setBackgroundColor(0x9AD5EF);
                    option2.setBackgroundColor(0x9AD5EF);
                    option3.setBackgroundColor(0x4CAF50);
                    option4.setBackgroundColor(0x9AD5EF);

                }

                //if selected option is wrong answer then it will increase 1 incorrect point and background color of button will turn into red
                else
                {
                    incrementIncorrectAnswers();
                    option3.setBackgroundColor(0xF44336);

                }

                //after click, all option will be disabled, correct/incorrect message will shown and next button will be enable
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                next.setEnabled(true);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionTxt = currentQuestion;
                String answer = (String) option4.getText();

                //if selected option is right answer then it will increase 1 correct point and background color of button will turn into green
                if (questionTxt.validateAnswer(answer))
                {
                    incrementCorrectAnswers();

                    option1.setBackgroundColor(0x9AD5EF);
                    option2.setBackgroundColor(0x9AD5EF);
                    option3.setBackgroundColor(0x9AD5EF);
                    option4.setBackgroundColor(0x4CAF50);
                }

                //if selected option is wrong answer then it will increase 1 incorrect point and background color of button will turn into red
                else
                {
                    incrementIncorrectAnswers();

                    option4.setBackgroundColor(0xF44336);

                }

                //after click, all option will be disabled, correct/incorrect message will shown and next button will be enable
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                next.setEnabled(true);
            }
        });

    }

    void fillData()
    {
        questionList.add(new Questions( "iOS stands for?", new String[]{"A) Inter network Operating System", "B) IPhone Operating System", "C) Internet Operating System", "D) None Of Them"},  1));
        questionList.add(new Questions( "The IDE Used In Swift Is _____", new String[]{"A) Swift", "B) Gas", "C) Xcode", "D) Ld"},  2));
        questionList.add(new Questions( "To Create Mutable Object _____ Is Used", new String[]{"A) Let", "B) Var", "C) Both A&B", "D) None"},  1));
        questionList.add(new Questions( "To Create Constants In Swift We Use Keyword _____", new String[]{"A) Const", "B) Let", "C) Constants", "D) None Of Above"},  1));
        questionList.add(new Questions( "Double Has A Precision Of At Least _____ Decimal Digits In Swift.", new String[]{"A) 15", "B) 20", "C) 17", "D) None Of Above"},  0));
        questionList.add(new Questions( "First IOS Was Written In _____", new String[]{"A) 1984", "B) 1985", "C) 1986", "D) 1987"},  2));
        questionList.add(new Questions( "We Can Return Multiple Values In Swift From Function By Using?", new String[]{"A) Array", "B) Tuple", "C) Both A&B", "D) None Of Above"},  1));
        questionList.add(new Questions( "Which Of The Following Is Incorrect Data Type In SWIFT ?", new String[]{"A) UInt", "B) Double", "C) Char", "D) Optional"},  2));
        questionList.add(new Questions( "To Initialize Variable With Null Require ______", new String[]{"A) ?", "B) !", "C) _", "D) Null"},  0));
        questionList.add(new Questions( "For Unwrapping Value Inside Optional What Should We Use?", new String[]{"A) ?", "B) !", "C) @", "D) None Of Above"},  1));
    }



    @SuppressLint("SetTextI18n")
    void displayQuestion()
    {
        title.setText("Quizz");
        score.setText("");
        //it will display random question
        currentQuestion = getRandomQuestion();

        Questions questionTxt = currentQuestion;
        {
            String[] choices = questionTxt.getChoices();
            question.setText(questionTxt.getQuestionTitle());
            option1.setText(choices[0]);
            option2.setText(choices[1]);
            option3.setText(choices[2]);
            option4.setText(choices[3]);

            // if the question is last question then button will be turn into End Quiz button
            if(numberOfQuestionsAsked() == 4)
            {
                next.setText ("End Quiz");
            }

            // if question is not the last question then it will be set to next question
            else
            {
                next.setText("Next Question");
            }
        }

        // while displaying option all option will be enable for click but next button will be disable
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);

        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);

        next.setEnabled(true);
    }

    Questions getRandomQuestion()
    {
        if (previouslyUsedNumbers.size() == questionList.size())
        {
            previouslyUsedNumbers = null;
        }
        int randomNumber = random.nextInt(questionList.size());

        while(previouslyUsedNumbers.contains(randomNumber))
        {
            randomNumber = random.nextInt(questionList.size());
        }

        previouslyUsedNumbers.add(randomNumber);
        return questionList.get(randomNumber);
    }

    // function to know that the question is last question or not
    boolean isGameOver()
    {
        return numberOfQuestionsAsked() >= numberOfQuestionPerRound;
    }

    // function for display the score
    @SuppressLint("SetTextI18n")
    void displayScore()
    {
        score.setText(getScore());
        question.setText("");
        int finalScore = totalScore();
        title.setText("");

        // if final score is 0,1 or 2 then user can start again
        if (finalScore==0 || finalScore==1 || finalScore==2)
        {
            next.setText("Start Again");
            reset();

        }

        // if final score is 3,4 or 5 then user cannot start again
        else
        {
            next.setVisibility(View.INVISIBLE);
        }

        // while showing score all button and label will be hidden except question label

        option1.setVisibility(View.INVISIBLE);
        option2.setVisibility(View.INVISIBLE);
        option3.setVisibility(View.INVISIBLE);
        option4.setVisibility(View.INVISIBLE);

    }



    // for restart game score will be reset
    static void reset ()
    {
        correctAnswers = 0;
        incorrectAnswers = 0;
        previouslyUsedNumbers.clear();
    }

    // 1 correct point will given upon right answer
    static void incrementCorrectAnswers()
    {
        correctAnswers += 1;
    }

    // 1 incorrect point will given upon wrong answer
    static void incrementIncorrectAnswers ()
    {
        incorrectAnswers += 1;
    }

    // total asked question will be return using total correct and incorrect point
    static int numberOfQuestionsAsked ()
    {
        return correctAnswers + incorrectAnswers;
    }

    // Score and message will be shown upon correct answers
    static String getScore()
    {
        if (correctAnswers == 5)
        {
            return "Your Score : "+correctAnswers +" / 5"+"\n\n\n"+" You are a genius! ";

        }

        else if (correctAnswers == 4)
        {
            return "Your Score : "+correctAnswers +" / 5"+"\n\n\n"+"Excellent Work! ";
        }

        else if (correctAnswers == 3)
        {
            return "Your Score : "+correctAnswers +" / 5"+"\n\n\n"+" Good Job! ";

        }

        else
        {
            return "Your Score : " +correctAnswers + " / 5" + "\n\n\n" + "Please Try Again!";
        }
    }

    // function will return total number of correct answers
    static int totalScore()
    {
        return correctAnswers;
    }

}