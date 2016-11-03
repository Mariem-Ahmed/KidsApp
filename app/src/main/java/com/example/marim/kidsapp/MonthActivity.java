package com.example.marim.kidsapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

public class MonthActivity extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    ImageButton next;
    ImageButton previous;
    TextToSpeech textToSpeech;

    String[] speakMonths = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

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
        if (viewFlipper.getDisplayedChild() == 11) {
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
        String speakNow = speakMonths[viewFlipper.getDisplayedChild()];
        textToSpeech.speak(speakNow, TextToSpeech.QUEUE_FLUSH, null);
    }
}
