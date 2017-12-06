package com.kbensonweb.ersa;

import android.content.Intent;
import android.os.AsyncTask;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class SurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }

    public void submitSurvey(View view) {
        Spinner spinmonth = findViewById(R.id.month);
        String month = spinmonth.getSelectedItem().toString();
        Spinner spinday = findViewById(R.id.day);
        String day = spinday.getSelectedItem().toString();
        String vDate = "2017-" + month + "-" + day;
        RadioGridGroup glocation = findViewById(R.id.glocation);
        String location= ((RadioButton)findViewById(glocation.getCheckedRadioButtonId())).getText().toString();
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
        String respect = ((RadioButton)findViewById(grespect.getCheckedRadioButtonId())).getText().toString();
        RadioGroup gclean = findViewById(R.id.gclean);
        String clean = ((RadioButton)findViewById(gclean.getCheckedRadioButtonId())).getText().toString();
        EditText ecomments = findViewById(R.id.comments);
        String comments = ecomments.getText().toString();

        /*String errythang = vDate + "\n" + location + "\n" + tin + "\n" + tout + "\n" + recommend + "\n" +
                satisfaction + "\n" + words + "\n" + nurses + "\n" + doc + "\n" + waitroom + "\n" +
                tests + "\n" + respect + "\n" + clean + "\n" + comments;

        Intent intent = new Intent(view.getContext(), SuccessActivity.class);
        intent.putExtra("EXTRA_TEXT",errythang);
        startActivity(intent);*/

        AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... survey) {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("location", survey[0])
                        .add("date", survey[1])
                        .add("time_in", survey[2])
                        .add("time_out", survey[3])
                        .add("recommend", survey[4])
                        .add("overall", survey[5])
                        .add("words", survey[6])
                        .add("nurse", survey[7])
                        .add("doctor", survey[8])
                        .add("waiting_room", survey[9])
                        .add("tests", survey[10])
                        .add("respect", survey[11])
                        .add("clean", survey[12])
                        .add("comments", survey[13])
                        .build();
                Request request = new Request.Builder()
                        .url("https://ersa.kbensonweb.com/ersa_api.php")
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

        asyncTask.execute(location,vDate,tin,tout,recommend,satisfaction,words,nurses,doc,waitroom,
                tests,respect,clean,comments);

        Intent intent = new Intent(this, SuccessActivity.class);
        startActivity(intent);

    }
}
