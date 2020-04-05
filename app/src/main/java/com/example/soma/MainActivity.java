package com.example.soma;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<uploadfile> options;
    FirebaseRecyclerAdapter<uploadfile, MyViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("SOMA").child("Users profile");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        Button profile = (Button)  findViewById(R.id.btn_profile);

        Toolbar mytool = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytool);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClassName("com.example.soma","com.example.soma.tutor_profile");
                startActivity(i);

            }
        });
        
        loadData();
        
    }

    private void loadData() {
options = new FirebaseRecyclerOptions.Builder<uploadfile>().setQuery(databaseReference,uploadfile.class).build();
adapter = new FirebaseRecyclerAdapter<uploadfile, MyViewHolder>(options) {
    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull uploadfile model) {
holder.fullname.setText(model.getName());
Picasso.get().load(model.getmImageUrl()).into(holder.profile_image_show);
holder.email.setText(model.getEmail());
holder.skillset.setText(model.getSkillset());

holder.fullprofile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getFullProfile();
    }

    private void getFullProfile() {
        

    }
});
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_view,parent,false);
        return new MyViewHolder(v);
    }
};
adapter.startListening();
recyclerView.setAdapter(adapter);
    }

}




