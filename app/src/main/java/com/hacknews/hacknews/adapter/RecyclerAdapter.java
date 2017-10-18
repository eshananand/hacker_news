package com.hacknews.hacknews.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hacknews.hacknews.R;
import com.hacknews.hacknews.models.Hit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eshan on 10/18/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private List<Hit> hitList;
    private Activity mContext;

    public RecyclerAdapter(Activity context, List<Hit> hits)
    {
        mContext = context;
        hitList = hits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {

        holder.tv_title.setText(hitList.get(position).getTitle());
        holder.tv_points.setText(hitList.get(position).getPoints() + "");
        holder.tv_author.setText(hitList.get(position).getAuthor());
        holder.tv_comments_no.setText(hitList.get(position).getNumComments() + "");
        holder.tv_url.setText(hitList.get(position).getUrl());
    }

    @Override
    public int getItemCount()
    {
        if(hitList != null)
            return hitList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_points)
        TextView tv_points;
        @BindView(R.id.tv_author)
        TextView tv_author;
        @BindView(R.id.tv_comments_no)
        TextView tv_comments_no;
        @BindView(R.id.tv_url)
        TextView tv_url;
        @BindView(R.id.container)
        RelativeLayout container;


        public ViewHolder(View v)
        {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}