package com.robosense.customui.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robosense.customui.R;

import java.util.ArrayList;

/**
 * Created by maxwell on 17-5-12.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {


    private ArrayList<String>   datas;
    private Context        mContext;
    private LayoutInflater mLayoutInflater;

    public FilterAdapter(Context context, ArrayList<String> datas) {
        mContext = context;
        this.datas = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_filter_select, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvSelectedName.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView  tvSelectedName;
        private ImageView ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSelectedName = (TextView) itemView.findViewById(R.id.tv_select_name);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete_select);
        }
    }
}
