package com.example.soma;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder2  extends RecyclerView.ViewHolder {
    ImageView fullprofileimage;
    TextView fullprofilename;
    TextView  fullprofilemail;
    TextView  fullprofilephone;
    TextView fullprofileskill;
    ImageView editfullprofilename, editfullprofileskill,editfullprofilephone;


    public MyViewHolder2(@NonNull View itemView) {
        super(itemView);

        fullprofilename = itemView.findViewById(R.id.tutor_profile_name);
        fullprofilemail = itemView.findViewById(R.id.tutor_profile_email);
        fullprofilephone = itemView.findViewById(R.id.tutor_profile_phone);
        fullprofileskill =itemView.findViewById(R.id.tutor_profile_skill);
        fullprofileimage = itemView.findViewById(R.id.profile_image_edit);
        editfullprofilename = itemView.findViewById(R.id.edit_profile_name);
        editfullprofileskill = itemView.findViewById(R.id.edit_profile_skill);
        editfullprofilephone= itemView.findViewById(R.id.edit_profile_phone);


    }
}
