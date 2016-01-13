package com.example.shobhit.login;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public class DisplayMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
    }

}
