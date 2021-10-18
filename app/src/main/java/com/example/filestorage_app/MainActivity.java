package com.example.filestorage_app;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MainActivity_adapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview_users);

        GET_FILES();
    }

    ArrayList<MainActivity_model> file_list;

    void GET_FILES() {
        String URL = "http://192.168.0.40/prototype_project_board/prototype_board/api/get_files.php";;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //((Main)getActivity()).alert_debug(response);
                    file_list = new ArrayList<MainActivity_model>();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);

                        file_list.add(new MainActivity_model(
                                jsonObject1.getString("file_path"),jsonObject1.getString("filename")));
                    }

                    adapter = new MainActivity_adapter(MainActivity.this, file_list);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);


                }
                catch (JSONException e){}
                catch (Exception e){}
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {

                } catch (Exception e){}
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("province", "");

                return hashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
        AppController.getInstance().setVolleyDuration(stringRequest);
    }




}