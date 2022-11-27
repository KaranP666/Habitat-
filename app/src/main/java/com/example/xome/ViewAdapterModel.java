package com.example.xome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapterModel extends RecyclerView.Adapter<ViewAdapterModel.MyViewholder> {

    Context context;
    ArrayList<DataRetrieving> list;

    public ViewAdapterModel(Context context, ArrayList<DataRetrieving> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.viewmade,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        DataRetrieving property_data=list.get(position);
        holder.SellorRent.setText(property_data.getType());
        holder.PropName.setText(property_data.getPropname());
        holder.City.setText(property_data.getCityofprop());
        holder.Price.setText(property_data.getPriceofprop());
        holder.NumBHK.setText(property_data.getBhks());
        holder.Address.setText(property_data.getAddressofprop());
        holder.Pincode.setText(property_data.getPincodeofprop());
        holder.State.setText(property_data.getStateofprop());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder{
        public ImageView bgimg;
        public TextView SellorRent,PropName,City,Price,NumBHK,Address,Pincode,State;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            bgimg=itemView.findViewById(R.id.bgIMG);
            SellorRent=itemView.findViewById(R.id.SaleorRent);
            PropName=itemView.findViewById(R.id.Prop_name1);
            City=itemView.findViewById(R.id.City1);
            Price=itemView.findViewById(R.id.Price1);
            NumBHK=itemView.findViewById(R.id.no_Of_bhk);
            Address=itemView.findViewById(R.id.Address1);
            Pincode=itemView.findViewById(R.id.Pincode1);
            State=itemView.findViewById(R.id.State1);
        }
    }

}
