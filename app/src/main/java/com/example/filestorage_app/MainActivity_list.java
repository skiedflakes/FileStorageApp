package com.example.filestorage_app;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainActivity_list {

    @SerializedName("user_list") //object name
    private ArrayList<MainActivity_model> FileList;

    public ArrayList<MainActivity_model> getUserList() {
        return FileList;
    }

    public void setFileList(ArrayList<MainActivity_model> userList) {
        FileList = userList;
    }
}
