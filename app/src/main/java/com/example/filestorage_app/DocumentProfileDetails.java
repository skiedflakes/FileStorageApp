package com.example.filestorage_app;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class DocumentProfileDetails extends AppCompatActivity {

    private DocumentProfileDetails_adapter adapter;
    private RecyclerView recyclerView;

    SessionManager session;
    String user,folder_id,filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             filename = extras.getString("filename");
             folder_id = extras.getString("folder_id");

            Log.e("Testfolder",folder_id+" - "+filename);
            //The key argument here must match that used in the other activity
        }

        recyclerView = findViewById(R.id.recyclerview_users);
        GET_FILES();
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user_account = session.getUserDetails();

        user = user_account.get(SessionManager.KEY_USERID);
    }

    ArrayList<MainActivity_model> file_list;

    void GET_FILES() {
        String URL = getString(R.string.URL)+"get_files_by_folder.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //((Main)getActivity()).alert_debug(response);
                    file_list = new ArrayList<MainActivity_model>();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.i("jr",response);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);

                        file_list.add(new MainActivity_model(
                                jsonObject1.getString("file_path"),jsonObject1.getString("filename")));
                    }

                    adapter = new DocumentProfileDetails_adapter(DocumentProfileDetails.this, file_list);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DocumentProfileDetails.this);
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
                hashMap.put("user_id", user);
                hashMap.put("folder_id", folder_id);

                return hashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
        AppController.getInstance().setVolleyDuration(stringRequest);
    }




}