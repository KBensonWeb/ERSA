package com.kbensonweb.ersa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

     /*   Intent intent = getIntent();
        String data = intent.getStringExtra("EXTRA_TEXT");
        TextView bioView = (TextView)findViewById(R.id.success);
        bioView.setText(data);*/
    }
}
