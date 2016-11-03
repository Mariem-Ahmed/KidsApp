package com.example.marim.kidsapp;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Locale;

public class AlphabetActivity extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    ImageButton next;
    ImageButton previous;
    TextToSpeech textToSpeech;

    String[] speakLetters = {
            "A for Apple", "B for Bird", "C for Cat", "D for Dog",
            "E for Elephant", "F for Flower", "G for Giraffe", "H for Hat",
            "I for Ice Cream", "J for Jar", "K for Kangaroo", "L for Lion",
            "M for Moon", "N for Notebook", "O for Orange", "P for Pig",
            "Q for Queen", "R for Rabbit", "S for Shark", "T for Turtle",
            "U for Umbrella", "V for Violin", "W for Watermelon", "X for Xylophone",
            "Y for Yacht", "Z for Zebra"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        next = (ImageButton) findViewById(R.id.next);
        previous = (ImageButton) findViewById(R.id.previous);

        next.setVisibility(View.VISIBLE);
        previous.setVisibility(View.INVISIBLE);

        next.setOnClickListener(this);
        previous.setOnClickListener(this);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.getVoice();
                    //textToSpeech.setSpeechRate(0.5f);
                    TextToSpeech();
                }
            }
        });
    }

    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        if (view == next) {
            viewFlipper.showNext();
            testVisiblity();
            TextToSpeech();

        } else if (view == previous) {
            viewFlipper.showPrevious();
            testVisiblity();
            TextToSpeech();
        }
    }

    public void testVisiblity() {
        if (viewFlipper.getDisplayedChild() == 25) {
            next.setVisibility(View.INVISIBLE);
            previous.setVisibility(View.VISIBLE);
        } else if (viewFlipper.getDisplayedChild() == 0) {
            next.setVisibility(View.VISIBLE);
            previous.setVisibility(View.INVISIBLE);
        } else {
            next.setVisibility(View.VISIBLE);
            previous.setVisibility(View.VISIBLE);
        }
    }

    public void TextToSpeech() {
        String speakNow = speakLetters[viewFlipper.getDisplayedChild()];
        textToSpeech.speak(speakNow, TextToSpeech.QUEUE_FLUSH, null);
    }
}
