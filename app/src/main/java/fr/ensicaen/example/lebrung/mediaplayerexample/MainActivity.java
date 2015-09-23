package fr.ensicaen.example.lebrung.mediaplayerexample;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

// Tutorial de référence : http://code.tutsplus.com/tutorials/streaming-video-in-android-apps--cms-19888
// Des flux video pour faire des essais : http://www.tvonlinestreams.com/
public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener{

    private MediaPlayer mediaPlayer;
    private SurfaceHolder vidHolder;
    private SurfaceView vidSurface;
    // String vidAddress = "http://5.196.82.222:8000/live/test1/test1/3.ts";
    // String vidAddress = "http://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
    String vidAddress="http://37.187.248.101:8080/thevault.stream/index.m3u8";
    private boolean video_start = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vidSurface = (SurfaceView)findViewById(R.id.video_surface);
        vidHolder = vidSurface.getHolder();
        vidHolder.addCallback(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("MYVIDEO","surfaceCreated start");
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(vidHolder);
            mediaPlayer.setDataSource(vidAddress);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
        }
        catch(Exception e){
            Log.d("MYVIDEO", "surfaceCreated execption : " + e.getMessage() + "\n");
            e.printStackTrace();

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d("VIDEO","onPrepared");
        mediaPlayer.start();
    }

    public void onclick_start_pause(View v){
        // TODO changer le texte du bouton
        if (video_start){
            mediaPlayer.pause();
            video_start = false;
        } else {
            mediaPlayer.start();
            video_start = true;
        }

    }
}
