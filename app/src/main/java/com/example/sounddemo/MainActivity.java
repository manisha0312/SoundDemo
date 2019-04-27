package com.example.sounddemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    //soundbile.com
    public void Play(View view){
        // mediaPlayer=MediaPlayer.create(this,R.raw.heavysimon);
        mediaPlayer.start();

    }
    public void Pause(View view){

        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);//setting value of audio manager
        int maxvolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// for max volume
        int currentVol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer=MediaPlayer.create(this,R.raw.heavysimon);// we can use this above alse but here this is better because if someone click pause before play the app wil not crash
        SeekBar volumeControl= (SeekBar)findViewById(R.id.seekBar);// this is for volume
        volumeControl.setMax(maxvolume);
        volumeControl.setProgress(currentVol);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("value of seekbar",Integer.toString(progress));//gives the value of seekbar
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // this seekbar is for song jitna song chala utna show
       final  SeekBar vol2=(SeekBar)findViewById(R.id.seekBar2);
        vol2.setMax(mediaPlayer.getDuration());
        vol2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("value of seekbar",Integer.toString(progress));
                mediaPlayer.seekTo(progress);// this to move the song by moving seekbar 2
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // to update seekbar every second for song update
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                vol2.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,300);
    }
}
