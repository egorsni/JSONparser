package com.example.user.json.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import com.example.user.json.R;
import com.example.user.json.controller.httpTask.UserTask;
import com.example.user.json.model.entities.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity    {
    private ListView usersListView;

    ArrayList<User> list;
    ArrayList<User> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new UserTask() {
            @Override
            protected void onPostExecute(ArrayList<User> user) {
                super.onPostExecute(user);
                ArrayList<User> results = user;
                Log.d("Size:","" + results.size());
                RecyclerView usersRecycler = findViewById(R.id.usersRecycler);
                usersRecycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
                usersRecycler.setAdapter(new UsersRecycleAdapter(results));

            }
        }.execute();

    }

}
