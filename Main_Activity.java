package com.example.music;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnPlay, btnNext, btnPrev;
    TextView songTitle;

    MediaPlayer mediaPlayer;

    int currentSong = 0;

    int[] songs = {R.raw.song1, R.raw.song2};

    String[] songNames = {"Song 1", "Song 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        songTitle = findViewById(R.id.songTitle);

        mediaPlayer = MediaPlayer.create(this, songs[currentSong]);
        songTitle.setText(songNames[currentSong]);

        btnPlay.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnPlay.setText("▶");
            } else {
                mediaPlayer.start();
                btnPlay.setText("⏸");
            }
        });

        btnNext.setOnClickListener(v -> {
            mediaPlayer.stop();
            mediaPlayer.release();

            currentSong = (currentSong + 1) % songs.length;

            mediaPlayer = MediaPlayer.create(this, songs[currentSong]);
            songTitle.setText(songNames[currentSong]);
            mediaPlayer.start();
            btnPlay.setText("⏸");
        });

        btnPrev.setOnClickListener(v -> {
            mediaPlayer.stop();
            mediaPlayer.release();

            currentSong = (currentSong - 1 + songs.length) % songs.length;

            mediaPlayer = MediaPlayer.create(this, songs[currentSong]);
            songTitle.setText(songNames[currentSong]);
            mediaPlayer.start();
            btnPlay.setText("⏸");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
