package com.example.basti.mytimer;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;


import static android.os.SystemClock.elapsedRealtime;

public class MainActivity extends AppCompatActivity {

    // Composants
    Chronometer chrono = null;
    EditText frequence = null;
    Button bouton = null;

    // Variables
    SoundPool soundPool= new SoundPool(20,AudioManager.STREAM_MUSIC,0);
    int son;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Bouton */
        bouton = (Button)findViewById(R.id.bouton);
        bouton.setOnClickListener(clickListener);

        /* Son */
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        son = soundPool.load(MainActivity.this,R.raw.robot_blip,1);


        /* Chrono */
        chrono = (Chronometer)findViewById(R.id.timer);
        chrono.setOnChronometerTickListener(tick);

    }


    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            frequence = (EditText)findViewById(R.id.frequence);
            if(frequence.getText().toString().equals("")){

            }

            chrono = (Chronometer)findViewById(R.id.timer);
            chrono.setBase(elapsedRealtime());
            chrono.start();

        }
    };

    private Chronometer.OnChronometerTickListener tick = new Chronometer.OnChronometerTickListener(){

        @Override
        public void onChronometerTick(Chronometer c){

            if(!frequence.getText().toString().equals("")) {
                int freq = Integer.valueOf(frequence.getText().toString());
                int time = (int) (elapsedRealtime() - chrono.getBase()) / 1000; // Conversion du temps écoulé en secondes
                if ((time % freq) == 0) {
                    soundPool.play(son, 1, 1, 0, 1, 2);
                    Toast.makeText(MainActivity.this, "Chrono:" + time, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };





}
