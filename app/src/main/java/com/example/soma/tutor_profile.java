package com.example.soma;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class tutor_profile extends AppCompatActivity {
    private Button btn_select;
    private Button btn_upload;
    private Uri mImageUri;
    private String userid;
    StorageReference mstorageref;
    DatabaseReference mDatabaseRef;
    ProgressBar mprogressbar;
    EditText tname,temail,tphonenumber;
    Spinner tacademicstatus;
    ImageView mImageView;
    Spinner tskillset;
    int PICK_IMAGE_REQUEST=100;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);
        mImageView = findViewById(R.id.profile_image);
        mprogressbar = findViewById(R.id.progressbar);
        tname = findViewById(R.id.tutor_name);
        temail = findViewById(R.id.tutor_email);
        tphonenumber = findViewById(R.id.tutor_phone);
        btn_select = findViewById(R.id.tutor_profile_photo);
        btn_upload = findViewById(R.id.tutor_profile_photo_upload);
        tacademicstatus = findViewById(R.id.tutor_academic_status);
        tskillset = findViewById(R.id.skillsetspinner);


         mstorageref = FirebaseStorage.getInstance().getReference("SOMA");
         mDatabaseRef = FirebaseDatabase.getInstance().getReference("SOMA");
         userid = mDatabaseRef.push().getKey();

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfile();
            }
        });
    }
private String getFileExtension(Uri uri){
    ContentResolver cR = getContentResolver();
    MimeTypeMap mime = MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(cR.getType(uri));
}
    private void uploadProfile() {


if(mImageUri != null){
    //checkIfallFieldsFilled();
StorageReference fileReference = mstorageref.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
fileReference.putFile(mImageUri)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
Toast.makeText(getApplicationContext(),"upload succesiful",Toast.LENGTH_SHORT).show();

                addUser(tname.getText().toString().trim(),temail.getText().toString().trim(),
                        taskSnapshot.getUploadSessionUri().toString(),tphonenumber.getText().toString().trim(),
                        tacademicstatus.getSelectedItem().toString().trim(),tskillset.getSelectedItem().toString().trim());
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(tutor_profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }


}).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
double progress = 100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount();
mprogressbar.setProgress((int) progress);
    }
});
   // mDatabaseRef.child("Users profile").child(userid).child("profile_image").setValue(mImageUri);



    }else{
    Toast.makeText(this,"no file selected",Toast.LENGTH_SHORT).show();
    }

    }

    private void checkIfallFieldsFilled() {
        String name = tname.getText().toString().trim();
        String email = temail.getText().toString().trim();
        String phonenumber = tphonenumber.getText().toString().trim();
        String skill = tskillset.getSelectedItem().toString();
        String academic_status = tacademicstatus.getSelectedItem().toString();
        if (TextUtils.isEmpty(name)){
            tname.setError("name required");
        }else if (TextUtils.isEmpty(email)){
            temail.setError("email required");
        }
        else if (TextUtils.isEmpty(phonenumber)){
            tphonenumber.setError("phone number required");

        }
    }


    private void selectImage(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE_REQUEST);
    }











    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode== PICK_IMAGE_REQUEST && resultCode==RESULT_OK
        &&data!=null &&data.getData()!=null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(mImageView);



        }

    }




    //insert user profile data
    public void addUser(String name, String email,String mImageurl, String phonenumber,String current_academic_status,String skillset){
        uploadfile users = new uploadfile(name, email,mImageurl, phonenumber,current_academic_status,skillset);
        mDatabaseRef.child("Users profile").child(tname.getText().toString().trim()+userid).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "successful text upload",Toast.LENGTH_LONG).show();
            }
        });
    }

    }

