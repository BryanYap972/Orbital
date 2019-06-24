package com.example.orb;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

    }

    public void setDetails(Context ctx, String title, String description, String image) {

        TextView Title = mView.findViewById(R.id.Title);
        TextView Description = mView.findViewById(R.id.Description);
        ImageView Image = mView.findViewById(R.id.ImageView);

        Title.setText(title);
        Description.setText(description);
        Picasso.get().load(image).into(Image);

    }
}