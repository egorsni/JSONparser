package com.example.user.json.controller.httpTask;

import android.os.AsyncTask;
import android.util.Log;


import com.example.user.json.controller.MainActivity;
import com.example.user.json.model.api.JsonPlaceholderApi;
import com.example.user.json.model.entities.User;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class UserTask extends AsyncTask<Void, Void, ArrayList<User>>   {

 protected void onPreExecute(){
    }


    @Override
    protected ArrayList<User> doInBackground(Void... params) {
        ArrayList<User> user = new ArrayList<>();
        JsonPlaceholderApi api = new JsonPlaceholderApi("https://jsonplaceholder.typicode.com/users");

        try {
            user = api.getUserArray();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("MyAppError", "ErrorIOE");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("MyAppError", "ErrorJSON");
        }


        return user;
    }


    protected void onProgressUpdate(){
    }
    protected void onPostExecute(ArrayList<User> user){


    }


}
