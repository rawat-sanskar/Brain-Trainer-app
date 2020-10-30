package com.example.rawat.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Integer z;
    int a;
    int b;
    int done;
    int correct;
    Button restart;
    Button go ;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ImageView imageView;
    CountDownTimer countDownTimer;
    TextView textView,textView2,textView3,textView4,textView5;
    int locationOfCorrectAnswer;
    int wrongAnswer;

    Random rand =new Random();

    public void question()
    {
        ArrayList<Integer> answers =new ArrayList<Integer>();
        a= rand.nextInt(30);
        b= rand.nextInt(30);
        textView4.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                wrongAnswer = rand.nextInt(60);
                while ((a + b) == wrongAnswer) {
                    wrongAnswer = rand.nextInt(60);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public  void  go (View view) {
        go.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        timer();
    }

     public void timer()
    {
        countDownTimer=new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {

                textView2.setText(String.valueOf(l/1000)+"s");
            }
            @Override
            public void onFinish() {

                  Toast.makeText(MainActivity.this, "OOPS! TIME'S  UP ", Toast.LENGTH_LONG).show();
                  if(correct==done)
                  {
                      textView5.setText("WELL  DONE!!");
                  }else{
                      textView5.setText("Better Luck Next Time!!!");
                }
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                restart.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song);
                mediaPlayer.start();
            }
        }.start();
    }
    public void chooseAnswer(View view)
    {
        done++;
        Integer tag =Integer.parseInt(view.getTag().toString());
        Log.i("number",view.getTag().toString());
       if(tag ==locationOfCorrectAnswer)
        {
            textView5.setText("Correct!");
            textView5.setVisibility(View.VISIBLE);
            question();
            correct++;
        }
        else{
            textView5.setText("Incorrect!");
            textView5.setVisibility(View.VISIBLE);
            question();
        }
        textView3.setText(Integer.toString(correct)+"/"+Integer.toString(done));

    }
    public  void  restart(View view)
    {
        correct=0;
        done=0;
        textView3.setText(Integer.toString(correct)+"/"+Integer.toString(done));
        question();
        timer();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=findViewById(R.id.go);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView= findViewById(R.id.textView);
        textView2= findViewById(R.id.textView2);
        textView4= findViewById(R.id.textView4);
        textView3= findViewById(R.id.textView3);
        textView5= findViewById(R.id.textView5);
        textView5.setVisibility(View.INVISIBLE);
        imageView.animate().rotationXBy(36000).setDuration(50000);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        restart = findViewById(R.id.restart);
        restart.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        correct=0;
        done=0;
       question();

    }
}
