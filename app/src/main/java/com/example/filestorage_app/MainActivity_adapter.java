package com.example.filestorage_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_adapter extends RecyclerView.Adapter<MainActivity_adapter.UsersViewHolder> {
    private ArrayList<MainActivity_model> dataList;
    private Context context;
    JSONArray jsonArray;
    JSONObject jsonObject;

    public MainActivity_adapter(Context context, ArrayList<MainActivity_model> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.users_card_row, parent, false);

        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder,final int position) {
        final String filename = dataList.get(position).getfilename();
        final String filepath = dataList.get(position).getfilepath();

        holder.btn_users.setText(filename);
        holder.btn_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test download", Toast.LENGTH_SHORT).show();
            }
        });

//        if(employee_id.equals("0")){
//            holder.btn_users.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setMessage("Are you sure to delete user: " + name + " ?")
//                            .setCancelable(false)
//                            .setTitle("DELETE...")
//                            .setIcon(R.drawable.delete)
//                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    delete_user(user_id,position);
//                                }
//                            })
//                            .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//
//                                }
//                            });
//                    AlertDialog alert = builder.create();
//                    alert.show();
//                    return false;
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        Button btn_users;
        UsersViewHolder(View itemView) {
            super(itemView);
            btn_users = itemView.findViewById(R.id.btn_users);
        }
    }

//    public void delete_user(final String user_id,final int position){
//        String URL = context.getString(R.string.schednotes)+"users/deleteUsers.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    jsonObject = new JSONObject(response);
//                    jsonArray = jsonObject.getJSONArray("responseList");
//                    String status;
//                    JSONObject jo = jsonArray.getJSONObject(0);
//                    status = jo.getString("status");
//
//                    if(status.equals("success")){ //success delete
//
//                        Toast.makeText(context, "successfully deleted", Toast.LENGTH_SHORT).show();
//                        dataList.remove(position);
//                        notifyDataSetChanged();
//
//                    }else{ //failed delete
//                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {}
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put("user_id", user_id);
//                return hashMap;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(stringRequest);
//    }

}
