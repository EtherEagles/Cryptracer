package com.example.antonio.cryptracer.Adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.antonio.cryptracer.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Currency_selection_adapter extends RecyclerView.Adapter<Currency_selection_adapter.selectionViewholder>{

    private ArrayList<String> coinNames = new ArrayList<>();
    private ArrayList<String> coinImages = new ArrayList<>();
    private Context context;

    public Currency_selection_adapter(Context context, ArrayList<String> currency_names, ArrayList<String> currency_images){
        coinNames = currency_names;
        coinImages = currency_images;
        this.context = context;
    }

    @Override
    public selectionViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_selector_cardview, parent, false);
        selectionViewholder holder = new selectionViewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(selectionViewholder holder, final int position) {
        Glide.with(context)
          .asBitmap()
          .load(coinImages.get(position))
          .into(holder.coinImage);
        Log.e("Image", coinImages.get(position));
        holder.coinName.setText(coinNames.get(position));
    }

    @Override
    public int getItemCount() {
        return coinNames.size();
    }

    public class selectionViewholder extends RecyclerView.ViewHolder{

        CircleImageView coinImage;
        TextView coinName;
        RelativeLayout parentLayout;

        public selectionViewholder(View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.image);
            coinName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
