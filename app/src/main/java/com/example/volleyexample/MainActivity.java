package com.example.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding views
        TextView valueUsername = findViewById(R.id.valueUsername);
        TextView valueID = findViewById(R.id.valueID);
        TextView valueRepositories = findViewById(R.id.valueRepositories);
        Button btnFetchData = findViewById(R.id.btnFetchData);

        //Initializing Volley
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.github.com/users/Patryk-Korczak";
        JsonObjectRequest jsonObjectRequest;

        //Creating request
        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        response -> {
                            try {
                                valueUsername.setText(response.getString("login"));
                                valueID.setText(response.getString("id"));
                                valueRepositories.setText(response.getString("public_repos"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> Toast.makeText(this, "Error while fetching data!", Toast.LENGTH_SHORT).show());

        //Setting up button listener to fetch data
        btnFetchData.setOnClickListener(v -> queue.add(jsonObjectRequest));
    }
}