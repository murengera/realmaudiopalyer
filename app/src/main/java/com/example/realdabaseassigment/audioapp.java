package com.example.realdabaseassigment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class audioapp extends AppCompatActivity implements View.OnClickListener {
    private Button btnplay,btnback,btnfor;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private  Runnable runnable;
    private Handler handler;
    private Toolbar toolbar;


    private  SharedPreferenceConfig preferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioapp);
        btnplay=findViewById(R.id.btnplay);
        btnback=findViewById(R.id.btnback);
        btnfor=findViewById(R.id.btnFor);
        seekBar=findViewById(R.id.seekbar);
        toolbar=findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);
        handler=new Handler();

        preferenceConfig=new SharedPreferenceConfig(getApplicationContext());

        mediaPlayer=MediaPlayer.create(this,R.raw.france);

        btnfor.setOnClickListener(this);
        btnback.setOnClickListener(this);
        btnplay.setOnClickListener(this);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            seekBar.setMax(mediaPlayer.getDuration());
            mediaPlayer.start();
            changeSeekbar();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                {

                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.appbar_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {

            case  R.id.logout:

                preferenceConfig.writeloginstatus(false);
               startActivity(new Intent(audioapp.this,Login.class));
               finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




    private void changeSeekbar() {
       seekBar.setProgress(mediaPlayer.getCurrentPosition());
       if (mediaPlayer.isPlaying())
       {
           runnable=new Runnable() {
               @Override
               public void run() {
   changeSeekbar();
               }
           };
           handler.postDelayed(runnable,1000);
       }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnplay:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnplay.setText(">");
                }
                else {
                    mediaPlayer.start();
                    btnplay.setText("||");
                    changeSeekbar();
                }
                break;
            case  R.id.btnFor:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
                break;
            case  R.id.btnback:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                break;

        }
    }
}
