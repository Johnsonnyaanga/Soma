package com.example.soma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class fullprofile extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    DatabaseReference userid;
    FirebaseAuth auth;
    String authenthicated;
    TextView savename;
    ImageView fullprofileimage;
    TextView fullprofilename, fullprofilemail,fullprofileskill,fullprofilephone;
    FirebaseRecyclerOptions<uploadfile> options;
    FirebaseRecyclerAdapter<uploadfile, MyViewHolder2> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullprofile);
        authenthicated = auth.getCurrentUser().getEmail().trim();
        fullprofilename = findViewById(R.id.tutor_profile_name);
        fullprofilemail = findViewById(R.id.tutor_profile_email);
        fullprofilephone = findViewById(R.id.tutor_profile_phone);
        fullprofileskill =findViewById(R.id.tutor_profile_skill);
        fullprofileimage = findViewById(R.id.profile_image_edit);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("SOMA").child("Users profile").child(authenthicated);
        databaseReference.keepSynced(true);
        recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
    }
    private void loadData() {
        options = new FirebaseRecyclerOptions.Builder<uploadfile>().setQuery(databaseReference,uploadfile.class).build();
        adapter = new FirebaseRecyclerAdapter<uploadfile, MyViewHolder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder2 holder, int position, @NonNull uploadfile model) {
                holder.fullprofilename.setText(model.getName());
                Picasso.get().load(model.getmImageUrl()).into(holder.fullprofileimage);
                holder.fullprofilemail.setText(model.getEmail());
                holder.fullprofileskill.setText(model.getSkillset());
                holder.fullprofilephone.setText(model.getPhonenumber());


                //editing profile data
                holder.editfullprofilename.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        editProfileName();


                    }

                    private void editProfileName() {

                        AlertDialog.Builder mydialog = new AlertDialog.Builder(fullprofile.this);
                        LayoutInflater inflater  = LayoutInflater.from(fullprofile.this);
                        View myview = inflater.inflate(R.layout.edit_profile_name, null);
                        final AlertDialog dialog = mydialog.create();
                        dialog.setView(myview);
                         savename = (TextView) findViewById(R.id.save_edit_profile_name);
                        TextView cancelname = (TextView) findViewById(R.id.cancel_edit_profile_name);

                        cancelname.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        savename.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                            }
                        });
                    }
                });


            }

            @NonNull
            @Override
            public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_fullprofile,parent,false);
                return new MyViewHolder2(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
