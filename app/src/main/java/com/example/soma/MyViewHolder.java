package com.example.soma;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class MyViewHolder  extends RecyclerView.ViewHolder {
    ImageView profile_image_show;
    TextView  fullname;
    TextView  email;
    TextView  skillset;
    TextView fullprofile;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        profile_image_show = itemView.findViewById(R.id.profile_image_show_id);
        fullname = itemView.findViewById(R.id.fullname_show);
        email = itemView.findViewById(R.id.tutor_email_show);
        skillset = itemView.findViewById(R.id.user_skillset_show);
        fullprofile = itemView.findViewById(R.id.show_tutor_full_profile);

    }
}
