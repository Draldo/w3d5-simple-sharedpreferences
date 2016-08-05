package com.example.admin.simplesharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTextview = (TextView) findViewById(R.id.detail_textview);

        Student student = getIntent().getExtras()
                .getParcelable(MainActivity.KEY_STUDENT);

        mTextview.setText("Welcome! " + student.name);
    }
}
