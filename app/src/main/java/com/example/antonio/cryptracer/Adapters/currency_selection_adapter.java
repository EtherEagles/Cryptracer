package com.example.antonio.cryptracer.Adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.antonio.cryptracer.MultichartPeriod;
import com.example.antonio.cryptracer.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class currency_selection_adapter extends RecyclerView.Adapter<currency_selection_adapter.ViewHolder>{

    private ArrayList<String> mcurrency_names = new ArrayList<>();
    private ArrayList<String> mcurrency_images = new ArrayList<>();
    private Context mContext;

    public currency_selection_adapter(Context context, ArrayList<String> currency_names, ArrayList<String> currency_images){
        mcurrency_names = currency_names;
        mcurrency_images = currency_images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mContext)
          .asBitmap()
          .load(mcurrency_images.get(position))
          .into(holder.currency);
        holder.currency_name.setText(mcurrency_names.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, mcurrency_names.get(position), Toast.LENGTH_SHORT).show();

                Intent Bitcoin = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Bitcoin.class);
                Intent Ether = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Ether.class);
                Intent Litecoin = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Litecoin.class);
                Intent Monero = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Monero.class);
                Intent Ripple = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Ripple.class);
                Intent Dogecoin = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Dogecoin.class);
                Intent Dash = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Dash.class);
                Intent MaidSafeCoin = new Intent(mContext, com.example.antonio.cryptracer.Currencies.MaidSafeCoin.class);
                Intent Lisk = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Lisk.class);
                Intent Storjcoin = new Intent(mContext, com.example.antonio.cryptracer.Currencies.Storjcoin.class);
                Intent Multichart =  new Intent(mContext, MultichartPeriod.class);

                if(mcurrency_names.get(position) == "Bitcoin"){
                    mContext.startActivity(Bitcoin);
                } if(mcurrency_names.get(position) == "Ether"){
                    mContext.startActivity(Ether);
                } if(mcurrency_names.get(position) == "Litecoin"){
                    mContext.startActivity(Litecoin);
                } if(mcurrency_names.get(position) == "Monero"){
                    mContext.startActivity(Monero);
                } if(mcurrency_names.get(position) == "Ripple"){
                    mContext.startActivity(Ripple);
                } if(mcurrency_names.get(position) == "Dogecoin"){
                    mContext.startActivity(Dogecoin);
                } if(mcurrency_names.get(position) == "Dash"){
                    mContext.startActivity(Dash);
                } if(mcurrency_names.get(position) == "MaidSafeCoin"){
                    mContext.startActivity(MaidSafeCoin);
                } if(mcurrency_names.get(position) == "Lisk"){
                    mContext.startActivity(Lisk);
                } if(mcurrency_names.get(position) == "Storjcoin"){
                    mContext.startActivity(Storjcoin);
                } if(mcurrency_names.get(position) == "Multichart"){
                    mContext.startActivity(Multichart);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mcurrency_names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView currency;
        TextView currency_name;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            currency = itemView.findViewById(R.id.image);
            currency_name = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
