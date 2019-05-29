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
import com.example.antonio.cryptracer.Currencies.Currency;
import com.example.antonio.cryptracer.MultichartPeriod;
import com.example.antonio.cryptracer.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class currency_selection_adapter extends RecyclerView.Adapter<currency_selection_adapter.ViewHolder>{

    private ArrayList<String> coinNames = new ArrayList<>();
    private ArrayList<String> coinImages = new ArrayList<>();
    private Context context;

    public currency_selection_adapter(Context context, ArrayList<String> currency_names, ArrayList<String> currency_images){
        this.coinNames = currency_names;
        this.coinImages = currency_images;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context)
          .asBitmap()
          .load(coinImages.get(position))
          .into(holder.currency);
        holder.currency_name.setText(coinNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, coinNames.get(position), Toast.LENGTH_SHORT).show();

                Intent Currency = new Intent(context, com.example.antonio.cryptracer.Currencies.Currency.class);

                switch (coinNames.get(position)){
                    case "Bitcoin":
                        Currency.putExtra("coinName", "Bitcoin");
                        context.startActivity(Currency);
                        break;
                    case "Ether":
                        Currency.putExtra("coinName", "Ether");
                        context.startActivity(Currency);
                    case "Litecoin":
                        Currency.putExtra("coinName", "Litecoin");
                        context.startActivity(Currency);
                    case "Monero":
                        Currency.putExtra("coinName", "Monero");
                        context.startActivity(Currency);
                    case "Ripple":
                        Currency.putExtra("coinName", "Ripple");
                        context.startActivity(Currency);
                    case "Dogecoin":
                        Currency.putExtra("coinName", "Dogecoin");
                        context.startActivity(Currency);
                    case "Dash":
                        Currency.putExtra("coinName", "Dash");
                        context.startActivity(Currency);
                    case "MaidSafeCoin":
                        Currency.putExtra("coinName", "MaidSafeCoin");
                        context.startActivity(Currency);
                    case "Lisk":
                        Currency.putExtra("coinName", "Lisk");
                        context.startActivity(Currency);
                    case "Storjcoin":
                        Currency.putExtra("coinName", "Storjcoin");
                        context.startActivity(Currency);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return coinNames.size();
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
