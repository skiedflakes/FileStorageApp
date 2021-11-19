package com.example.filestorage_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DocumentProfile_adapter extends RecyclerView.Adapter<DocumentProfile_adapter.UsersViewHolder> {
    private ArrayList<DocumentProfile_model> dataList;
    private Context context;
    JSONArray jsonArray;
    JSONObject jsonObject;

    public DocumentProfile_adapter(Context context, ArrayList<DocumentProfile_model> dataList){
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
        final String folder_id = dataList.get(position).getFolder_id();

        holder.btn_users.setText(filename);
        holder.btn_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DocumentProfileDetails.class);
                i.putExtra("folder_id",folder_id);
                i.putExtra("filename",filename);
                context.startActivity(i);

            }
        });

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

}
