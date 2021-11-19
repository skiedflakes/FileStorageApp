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

public class DocumentProfile extends AppCompatActivity {

    private DocumentProfile_adapter adapter;
    private RecyclerView recyclerView;

    SessionManager session;
    String user,group_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_profile);
        recyclerView = findViewById(R.id.recyclerview_users);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user_account = session.getUserDetails();

        user = user_account.get(SessionManager.KEY_USERID);
        group_id = user_account.get(SessionManager.KEY_GROUP_ID);
        GET_FILES();
    }

    ArrayList<DocumentProfile_model> file_list;

    void GET_FILES() {
        Log.i("testing",group_id);
        String URL = getString(R.string.URL)+"get_document_profile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //((Main)getActivity()).alert_debug(response);
                    file_list = new ArrayList<DocumentProfile_model>();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);

                        file_list.add(new DocumentProfile_model(
                                jsonObject1.getString("file_path"),jsonObject1.getString("filename"),jsonObject1.getString("folder_id")));
                    }

                    adapter = new DocumentProfile_adapter(DocumentProfile.this, file_list);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DocumentProfile.this);
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
                hashMap.put("group_id", group_id);
                return hashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
        AppController.getInstance().setVolleyDuration(stringRequest);
    }




}