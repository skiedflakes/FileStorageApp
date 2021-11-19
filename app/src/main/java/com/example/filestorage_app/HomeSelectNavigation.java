package com.example.filestorage_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeSelectNavigation extends AppCompatActivity {

    Button shared_files_btn,files_btn,document_profile_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_select_navigation);
        document_profile_btn = findViewById(R.id.document_profile_btn);
        shared_files_btn=findViewById(R.id.shared_files_btn);
        files_btn = findViewById(R.id.files_btn);

        document_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(HomeSelectNavigation.this, DocumentProfile.class);
                startActivity(i);

            }
        });

        shared_files_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeSelectNavigation.this, SharedFiles.class);
                startActivity(i);


            }
        });

        files_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeSelectNavigation.this, MainActivity.class);
                startActivity(i);

            }
        });
    }


}