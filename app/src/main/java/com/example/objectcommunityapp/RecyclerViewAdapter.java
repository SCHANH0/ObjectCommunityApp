package com.example.objectcommunityapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class ViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView number;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
