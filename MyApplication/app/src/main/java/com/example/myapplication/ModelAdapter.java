package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder> {

    private ArrayList<ModelClass> arrayList;

    public ModelAdapter(ArrayList<ModelClass> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ModelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_item,parent,false);
        return new ModelAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapter.MyViewHolder holder, int position) {
        ModelClass modelClass = arrayList.get(position);

        Bitmap bitmap;
        String encodedImage = modelClass.getImage();

        if (!encodedImage.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(encodedImage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            holder.image.setImageBitmap(bitmap);
        }

    holder.name.setText(modelClass.getName());
    holder.age.setText(modelClass.getAge());
    holder.gender.setText(modelClass.getGender());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,age,gender;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            age = itemView.findViewById(R.id.Age);
            gender = itemView.findViewById(R.id.Gender);
            image = itemView.findViewById(R.id.Photo);
        }
    }

    /*
    private ArrayList<ModelClass> arrayList;

    public static class ModelViewHolder extends RecyclerView.ViewHolder {
        public TextView textLine1;
        public TextView textLine2;
        public TextView textLine3;
        public ImageView imageView;

        public ModelViewHolder(View itemView) {
            super(itemView);
            textLine1 = itemView.findViewById(R.id.Name);
            textLine2 = itemView.findViewById(R.id.Age);
            textLine3 = itemView.findViewById(R.id.Gender);
            imageView = itemView.findViewById(R.id.Photo);
        }
    }

    public ModelAdapter(ArrayList<ModelClass> array) {
        arrayList = array;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ModelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.model_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder modelViewHolder, int position) {

        ModelClass item = arrayList.get(position);
        Bitmap bitmap;
        String encodedImage = item.getImage();

        if( !encodedImage.equalsIgnoreCase("") )
        {
            byte[] b = Base64.decode(encodedImage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            modelViewHolder.imageView.setImageBitmap(bitmap);
        }

        modelViewHolder.textLine1.setText(item.getName());
        modelViewHolder.textLine2.setText(item.getAge());
        modelViewHolder.textLine3.setText(item.getGender());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
*/
}
