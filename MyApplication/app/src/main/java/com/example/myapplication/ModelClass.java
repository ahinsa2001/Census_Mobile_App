package com.example.myapplication;

public class ModelClass {

    public String name;
    public String age;
    public String gender;
    public String image;

    public ModelClass(String name,String age,String gender,String image){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }
}
