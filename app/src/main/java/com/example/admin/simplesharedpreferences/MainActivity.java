package com.example.admin.simplesharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    private EditText mName;
    private EditText mPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                for(Student student : students){

                }
            }
        });
    }
}
