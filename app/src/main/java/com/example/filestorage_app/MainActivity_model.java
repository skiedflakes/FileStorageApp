package com.example.filestorage_app;

import com.google.gson.annotations.SerializedName;

public class MainActivity_model {

    @SerializedName("filepath")
    private String filepath;
    @SerializedName("filename")
    private String filename;


    public MainActivity_model(String filepath,String filename){
        this.filepath = filepath;
        this.filename = filename;

    }

    public String getfilename() {
        return filename;
    }

    public String getfilepath() {
        return filepath;
    }


}
