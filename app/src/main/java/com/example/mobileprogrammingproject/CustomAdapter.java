package com.example.mobileprogrammingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<Kullanicilar> kullanicilarArrayList=new ArrayList<>();
    ArrayList<Kullanicilar> listContact=new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    private VeriTabani mDatabase;
    public CustomAdapter(ArrayList<Kullanicilar> kullanicilarArrayList, Context context) {
        this.context = context;
        this.kullanicilarArrayList = kullanicilarArrayList;
        //bu yeni eklendi.
        this.listContact=kullanicilarArrayList;
        mDatabase=new VeriTabani(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.row_list,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUsername.setText(kullanicilarArrayList.get(position).username);
        holder.txtPassword.setText(kullanicilarArrayList.get(position).password);
        holder.imageView.setImageResource(kullanicilarArrayList.get(position).imgSrc);
        holder.linearLayout.setTag(holder);


    }

    @Override
    public int getItemCount() {
        return kullanicilarArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtUsername,txtPassword;
        ImageView imageView;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername=itemView.findViewById(R.id.txtUsername);
            txtPassword=itemView.findViewById(R.id.txtPassword);
            imageView=itemView.findViewById(R.id.image);
            linearLayout=itemView.findViewById(R.id.linear);

        }
    }
}
