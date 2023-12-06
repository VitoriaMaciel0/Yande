package com.example.a0001;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;

public class PupolarAdapter extends RecyclerView.Adapter<PupolarAdapter.Viewholder> {
    ArrayList<PopularDomain> itens;
    Context context;

    public PupolarAdapter(ArrayList<PopularDomain> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public PupolarAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pup_list,parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.titleTxt.setText(itens.get(position).getTitle());
        holder.feeTxt.setText("$"+itens.get(position).getPrice());
        holder.scoreTxt.setText(""+itens.get(position).getScore());

        int drawableResourceId=holder.itemView.getResources().getIdentifier(itens.get(position).getPicUrl(),"drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30,0,0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(holder.itemView.getContext(),DetailActivity.class);
            intent.putExtra("object", itens.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return itens.size(); }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt,feeTxt,scoreTxt;
        ImageView pic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.TitleText);
            feeTxt=itemView.findViewById(R.id.feeTxt);
            scoreTxt=itemView.findViewById(R.id.scoreText);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
