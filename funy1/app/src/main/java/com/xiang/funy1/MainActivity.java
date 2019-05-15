package com.xiang.funy1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person p = new Person();
        p.registKickItem(new Cat());
        p.registKickItem(new OnKickListner() {
            @Override
            public void onKick(int data) {
                //
            }
        });

        p.run();
    }
}
