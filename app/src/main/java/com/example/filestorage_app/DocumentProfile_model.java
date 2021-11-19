package com.example.filestorage_app;

import com.google.gson.annotations.SerializedName;

public class DocumentProfile_model {

    @SerializedName("filepath")
    private String filepath;
    @SerializedName("filename")
    private String filename;

    @SerializedName("folder_id")
    private String folder_id;

    public DocumentProfile_model(String filepath, String filename,String folder_id){
        this.filepath = filepath;
        this.filename = filename;
        this.folder_id = folder_id;

    }

    public String getfilename() {
        return filename;
    }

    public String getfilepath() {
        return filepath;
    }

    public String getFolder_id() {
        return folder_id;
    }
}
