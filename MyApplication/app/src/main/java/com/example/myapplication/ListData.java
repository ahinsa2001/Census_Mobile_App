package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListData extends AppCompatActivity {

    ArrayList<ModelClass> arrayList;
    private RecyclerView recyclerView1;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    MaterialButton upload;
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        upload = (MaterialButton)findViewById(R.id.pushToCloud);

        prf = getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prf.getString("data list",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){
        }.getType();

        arrayList = gson.fromJson(json,type);

        if(arrayList.isEmpty()){
            arrayList = new ArrayList<>();
        }

        recyclerView1 = findViewById(R.id.recyclerView);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(ListData.this));
        recyclerView1.setAdapter(new ModelAdapter(arrayList));


        upload.setOnClickListener(v -> {

            DatabaseReference obj = FirebaseDatabase.getInstance().getReference();

            for(ModelClass item : arrayList) {
                String name = item.getName();
                String age = item.getAge();
                String gender = item.getGender();
                String image = item.getImage();

                obj.child("UserDetails").child(name).child("Age").setValue(age);
                obj.child("UserDetails").child(name).child("Gender").setValue(gender);
                obj.child("UserDetails").child(name).child("Image").setValue(image);
            }

            SharedPreferences prf = getSharedPreferences("DATA", MODE_PRIVATE);
            prf.edit().clear().apply();
            ListData.this.finish();
            Toast.makeText(ListData.this,"Uploaded", Toast.LENGTH_SHORT).show();

        });
    }

}