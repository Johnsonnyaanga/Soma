package com.example.soma;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
    StorageReference mstorageref;
    DatabaseReference mDatabaseRef;
    ProgressBar mprogressbar;
    EditText tname,temail,tphonenumber,tacademicstatus;
    ImageView mImageView;
    Spinner tskillset;
    int PICK_IMAGE_REQUEST=1;




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
         mstorageref = FirebaseStorage.getInstance().getReference("uploads");
         mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }
private String getFileExtension(Uri uri){
    ContentResolver cR = getContentResolver();
    MimeTypeMap mime = MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(cR.getType(uri));
}
    private void uploadImage() {
if(mImageUri != null){
StorageReference fileReference = mstorageref.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
fileReference.putFile(mImageUri)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
Toast.makeText(getApplicationContext(),"upload succesiful",Toast.LENGTH_SHORT).show();
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


    }else{
    Toast.makeText(this,"no file selected",Toast.LENGTH_SHORT).show();
    }
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("/image*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                intent,PICK_IMAGE_REQUEST
               );

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

    }

