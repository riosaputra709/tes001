package com.example.androideatit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androideatit.Model.AppPopularCategoryModel;
import com.example.androideatit.R;
import com.example.androideatit.callback.IRecyclerClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyAppPopularCategoryAdapter extends RecyclerView.Adapter<MyAppPopularCategoryAdapter.MyViewHolder> {

    Context context;
    List<AppPopularCategoryModel> popularCategoryModelList;

    public MyAppPopularCategoryAdapter(Context context, List<AppPopularCategoryModel> popularCategoryModelList) {
        this.context = context;
        this.popularCategoryModelList = popularCategoryModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_app_popular_categories_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(popularCategoryModelList.get(position).getImage())
                .into(holder.category_image);
        holder.txt_category_name.setText(new StringBuilder(popularCategoryModelList.get(position).getName()));

        /*//event
        holder.setListener((view, pos) -> {
            Common.categoryChordSelected = popularCategoryModelList.get(pos);
            EventBus.getDefault().postSticky(new CategoryChordClick(true,popularCategoryModelList.get(pos)));
        });*/
    }

    @Override
    public int getItemCount() {
        return popularCategoryModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Unbinder unbinder;
        @BindView(R.id.category_image)
        CircleImageView category_image;
        @BindView(R.id.txt_category_name)
        TextView txt_category_name;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClickListener(v, getAdapterPosition());
        }
    }


}
