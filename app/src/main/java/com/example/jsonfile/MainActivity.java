package com.example.jsonfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ModelAdapter modelAdapter;
    List<Model> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyleView);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/comments", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0;i<response.length();i++) {
                    JSONObject obj = null;
                    try {
                        obj = response.getJSONObject(i);
                        list.add(new Model(obj.getString("id"),obj.getString("name"),obj.getString("email"),obj.getString("body")));
                        Log.d("Json","id: "+obj.getString("id")+"\n"+obj.getString("name")+obj.getString("email")+obj.getString("body"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                modelAdapter = new ModelAdapter(MainActivity.this,list);
                recyclerView.setAdapter(modelAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Json","Something went Wrong");
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)); // Setting LinearLayoutManager

        requestQueue.add(jsonArrayRequest);

    }
}