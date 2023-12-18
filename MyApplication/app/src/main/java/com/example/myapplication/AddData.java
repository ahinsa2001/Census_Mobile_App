package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddData extends AppCompatActivity{

    String encodedImage;
    TextInputEditText name,age;
    MaterialButton saveButton,imgButton;
    ImageView photo;
    Intent camera;
    RadioGroup radio;
    RadioButton genderRadioButton;

    private static final int requestCamera = 12;
    SharedPreferences prf;
    SharedPreferences.Editor editor;
    ArrayList<ModelClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        name = (TextInputEditText) findViewById(R.id.name);
        age = (TextInputEditText) findViewById(R.id.age);
        saveButton = (MaterialButton) findViewById(R.id.saveButton);
        imgButton = (MaterialButton) findViewById(R.id.imgButton);
        photo = (ImageView) findViewById(R.id.photo);
        radio = (RadioGroup) findViewById(R.id.genderRadio);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

            imgButton.setOnClickListener(v -> {

                if((ActivityCompat.checkSelfPermission(
                        AddData.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        requestPermissions(new String[]{
                                Manifest.permission.CAMERA,
                        },123);
                    }
                }
                else
                {
                    camera = new Intent();
                    camera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera,118);
                }

            });

            saveButton.setOnClickListener(v -> {
                int selectedId = radio.getCheckedRadioButtonId();
                genderRadioButton = (RadioButton) findViewById(selectedId);

                if (selectedId == -1 && name.getEditableText().toString().isEmpty() && age.getEditableText().toString().isEmpty())
                {
                    Toast.makeText(AddData.this, "Complete all the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    prf = getSharedPreferences("DATA", MODE_PRIVATE);
                    String json = prf.getString("data list", null);
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<ModelClass>>() {
                    }.getType();

                    arrayList = gson.fromJson(json, type);
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }

                    saveNewData(name.getEditableText().toString(),age.getEditableText().toString(),genderRadioButton.getText().toString());
                    Toast.makeText(getApplicationContext(), "New Data Added Successfully", Toast.LENGTH_LONG).show();
                    name.getEditableText().clear();
                    age.getEditableText().clear();
                    radio.clearCheck();
                }

            });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 118 && resultCode == RESULT_OK)
        {
            Bitmap imgBitmap = (Bitmap)data.getExtras().get("data");
            photo.setImageBitmap(imgBitmap);

            ByteArrayOutputStream obj = new ByteArrayOutputStream();
            imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, obj);
            byte[] b = obj.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            Toast.makeText(AddData.this, "Image Saved Successfully!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(AddData.this, "Couldn't Captured", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveNewData(String name, String age,String gender) {

//        String addName = name.getEditableText().toString();
//        String addAge = age.getEditableText().toString();
//        String addGender = genderRadioButton.getText().toString();
        arrayList.add(new ModelClass(name,age,gender,encodedImage));

        SharedPreferences prfObj = getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editorObj = prfObj.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editorObj.putString("data list", json);
        editorObj.apply();

    }
}