package com.example.minhnt.thibanglaixeoto.exam;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.util.Sound;

import pl.droidsonroids.gif.GifImageView;

public class CongratActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GifImageView imgSakura = (GifImageView) findViewById(R.id.givSakura);

        findViewById(R.id.ivShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Kỳ thi sát hạch ô tô");
                share.putExtra(Intent.EXTRA_TEXT, "Chúc mừng bạn đã hoàn thành đề thi sát hạch với số điểm tuyệt đối");
                startActivity(Intent.createChooser(share, "Chia sẻ niềm vui với bạn bè"));
            }
        });

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.win);
        mediaPlayer.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }
}
