package com.example.admin.simplesharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String url = "http://www.mocky.io/v2/57a4dfb40f0000821dc9a3b8";
    private static final String TAG = MainActivity.class.getSimpleName() + "TAG_";
    public static final String KEY_STUDENT = "KEYVALUE";

    private EditText mName;
    private EditText mPass;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
    }

    public void doMagic(View view) {
        mName = (EditText) findViewById(R.id.a_main_name);
        mPass = (EditText) findViewById(R.id.a_main_pass);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Response response = client.newCall(request).execute(); --> synchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new GsonBuilder().create();
                Type listType = new TypeToken<List<Student>>() {}.getType();
                ArrayList<Student> students = gson.fromJson(json, listType);
                boolean authenticated = false;

                for (Student student : students) {
                    if (compareUserCredentials(student, mName.getText().toString(), mPass.getText().toString())) {
                        goToDetailsActivity(student);
                        authenticated = true;
                        break;
                    }
                }
                if (!authenticated) {
                    showFailedCredentials();
                }
            }
        });
    }

    private void showFailedCredentials() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToDetailsActivity(Student student) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(KEY_STUDENT, student);
        startActivity(intent);
    }

    private boolean compareUserCredentials(Student student, String user, String password) {
        if (!student.name.equals(user)) {
            return false;
        }
        if (!student.password.equals(password)) {
            return false;
        }
        return true;
    }

}