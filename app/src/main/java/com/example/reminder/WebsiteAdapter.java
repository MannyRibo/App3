package com.example.reminder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WebsiteAdapter extends RecyclerView.Adapter<WebsiteAdapter.ViewHolder> {

    private List<Website> mWebsites;

    public WebsiteAdapter(List<Website> mWebsites) {
        this.mWebsites = mWebsites;
    }

    @NonNull
    @Override
    public WebsiteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        // Return a new holder instance
        WebsiteAdapter.ViewHolder viewHolder = new WebsiteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteAdapter.ViewHolder viewHolder, int i) {
        Website website = mWebsites.get(i);
        viewHolder.textView.setText(website.getmWebsiteName());
    }

    @Override
    public int getItemCount() {
        return mWebsites.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }


}
