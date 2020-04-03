package com.example.soma;

import android.net.Uri;

public class uploadfile {
    private String name;
    private String email;
    private String phonenumber;
    private String current_academic_status;
    private Uri mImageUrl;
    private String skillset;

    public  uploadfile(){

    }

    public uploadfile(String name, String email, String phonenumber, String current_academic_status, Uri mImageUrl, String skillset) {

        whenEmpty(name);
        whenEmpty(email);
        whenEmpty(phonenumber);
        whenEmpty(current_academic_status);
        whenEmpty(skillset);



        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.current_academic_status = current_academic_status;
        this.mImageUrl = mImageUrl;
        this.skillset = skillset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCurrent_academic_status() {
        return current_academic_status;
    }

    public void setCurrent_academic_status(String current_academic_status) {
        this.current_academic_status = current_academic_status;
    }

    public Uri getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(Uri mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }


    public void whenEmpty(String field){
        if (field.trim().isEmpty()){
            field ="null value";
        }

    }



}

