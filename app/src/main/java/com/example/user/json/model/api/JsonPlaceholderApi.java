package com.example.user.json.model.api;

import android.util.Log;


import com.example.user.json.model.entities.Address;
import com.example.user.json.model.entities.Company;
import com.example.user.json.model.entities.Geo;
import com.example.user.json.model.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class JsonPlaceholderApi {
    private String urlPath;

    public JsonPlaceholderApi(String urlPath) {
        this.urlPath = urlPath;
    }

    public ArrayList<User> getUserArray() throws IOException, JSONException {
        ArrayList<User> contactList = new ArrayList<User>();
        String userJson = getJsonFromServer(urlPath, 0);


        JSONArray contacts = new JSONArray(userJson);
//        Log.d("MyAppError", "Size:" + contacts.length());
if(contacts!=null) {
    for (int i = 1; i <= contacts.length(); i++) {
        String userJsonStroke = getJsonFromServer(String.format("%s/%d", urlPath, i), 0);
        JSONObject userRootStroke = new JSONObject(userJsonStroke);

        JSONObject userAddress = userRootStroke.getJSONObject("address");
        JSONObject userCompany = userRootStroke.getJSONObject("company");
        JSONObject addressGeo = userAddress.getJSONObject("geo");

        int userId = userRootStroke.getInt("id");
        String userName = userRootStroke.getString("name");
        String userNameName = userRootStroke.getString("username");
        String userEmail = userRootStroke.getString("email");
        String userPhone = userRootStroke.getString("phone");
        String userWebSite = userRootStroke.getString("website");

        String addressStreet = userAddress.getString("street");
        String addressSuite = userAddress.getString("suite");
        String addressCity = userAddress.getString("city");
        String addressZipcode = userAddress.getString("zipcode");

        double geoLat = addressGeo.getDouble("lat");
        double getLon = addressGeo.getDouble("lng");

        String companyName = userCompany.getString("name");
        String companyCatchPhrase = userCompany.getString("catchPhrase");
        String companyBs = userCompany.getString("bs");

        Geo geo = new Geo(geoLat, getLon);
        Address address = new Address(addressStreet, addressSuite, addressCity, addressZipcode, geo);
        Company company = new Company(companyName, companyCatchPhrase, companyBs);

        // tmp hash map for single contact
        User newUser = new User(userId, userName, userNameName, userEmail, address, userPhone, userWebSite, company);

        // adding each child node to HashMap key => value


        // adding contact to contact list
        contactList.add(newUser);
    }
}


        return contactList;
    }

    private String getJsonFromServer(String urlPath, int timeout) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.connect();

        int serverResponseCode = connection.getResponseCode();
        switch (serverResponseCode) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String tmpLine;
                while ((tmpLine = br.readLine()) != null) {
                    sb.append(tmpLine).append("\n");
                }
                br.close();
                return sb.toString();
            case 404:
                Log.d(JsonPlaceholderApi.class.getName(), "page not found!");
                break;
            case 400:
                Log.d(JsonPlaceholderApi.class.getName(), "Bad request!");
                break;
            case 500:
                Log.d(JsonPlaceholderApi.class.getName(), "Internal server error");
                break;
        }

        return null;
    }
}
