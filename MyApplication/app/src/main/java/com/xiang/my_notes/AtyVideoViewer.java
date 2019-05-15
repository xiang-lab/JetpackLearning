package com.xiang.my_notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class AtyVideoViewer extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vv = new VideoView(this);
        setContentView(vv);

        String path = getIntent().getStringExtra(EXTRA_PATH);
        if (path != null) {
            vv.setVideoPath(path);
        } else {
            finish();
        }

    }

    private VideoView vv;

    public static final String EXTRA_PATH = "path";
}
