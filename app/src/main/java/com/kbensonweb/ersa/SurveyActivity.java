package com.kbensonweb.ersa;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SurveyActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "2017-12-05";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void submitSurvey(View view) {
        RadioGridGroup glocation = (RadioGridGroup)findViewById(R.id.glocation);
        String location= ((RadioButton)findViewById(glocation.getCheckedRadioButtonId())).getText().toString();
        Intent d = getIntent();
        String vDate = d.getStringExtra("EXTRA_TEXT");
        Spinner spintin = findViewById(R.id.spin3);
        String tin = spintin.getSelectedItem().toString();
        Spinner spintout = findViewById(R.id.spin4);
        String tout = spintout.getSelectedItem().toString();
        Spinner spinrecommend = findViewById(R.id.spinq5);
        String recommend = spinrecommend.getSelectedItem().toString();
        Spinner spinsatis = findViewById(R.id.spinq6);
        String satisfaction = spinsatis.getSelectedItem().toString();
        CheckBox cbquick = findViewById(R.id.quick);
        CheckBox cbsatis = findViewById(R.id.satis);
        CheckBox cbthorough = findViewById(R.id.thorough);
        CheckBox cblong = findViewById(R.id.clong);
        CheckBox cbpoor = findViewById(R.id.poor);
        CheckBox cbunresolved = findViewById(R.id.unresolved);
        String words = "";
        if (cbquick.isChecked())
            words = words + "quick, ";
        if (cbsatis.isChecked())
            words = words + "satisfying, ";
        if (cbthorough.isChecked())
            words = words + "thorough, ";
        if (cblong.isChecked())
            words = words + "long, ";
        if (cbpoor.isChecked())
            words = words + "poor, ";
        if (cbunresolved.isChecked())
            words = words + "unresolved";
        Spinner spinnurse = findViewById(R.id.spin8);
        String nurses = spinnurse.getSelectedItem().toString();
        Spinner spindoc = findViewById(R.id.spin9);
        String doc = spindoc.getSelectedItem().toString();
        Spinner spinwait = findViewById(R.id.spin10);
        String waitroom = spinwait.getSelectedItem().toString();
        Spinner spintests = findViewById(R.id.spin11);
        String tests = spintests.getSelectedItem().toString();
        RadioGroup grespect = findViewById(R.id.grespect);
        String respect = ((RadioButton)findViewById(glocation.getCheckedRadioButtonId())).getText().toString();
        RadioGroup gclean = findViewById(R.id.gclean);
        String clean = ((RadioButton)findViewById(glocation.getCheckedRadioButtonId())).getText().toString();
        EditText ecomments = findViewById(R.id.comments);
        String comments = ecomments.getText().toString();


        AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... movieInfo) {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("name", movieInfo[0])
                        .add("image", movieInfo[1])
                        .add("genre", movieInfo[2])
                        .build();
                Request request = new Request.Builder()
                        .url("https://kbensonweb.com/movies_add.php")
                        .post(formBody)
                        .build();
                try {
                    client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        //asyncTask.execute(name,image,genre);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void submit(View view) {
        Intent intent = new Intent(this, SuccessActivity.class);
        startActivity(intent);
    }
}
