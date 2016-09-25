package com.debugandroid.headerviewexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PK on 9/20/2016.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean isHeader;
    private List<String> data;
    private  Context context;
    public static final int VIEW_HEADER = 0;
    public static final int VIEW_ITEM = 1;

    public Adapter(Context context, List<String> data, boolean isHeader){
        this.context=context;
        this.data=data;
        this.isHeader=isHeader;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==VIEW_HEADER) {
            return new headView(LayoutInflater.from(context).inflate(R.layout.header_layout, parent, false));
        }
        else
         return new itemView(LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof  headView){
            ((headView) holder).header.setText("This is Header");
            Log.d("Item View","Binding Item header at"+position);
        }
        else {
            ((itemView) holder).itemName.setText(getName(position));
            Log.d("Item View","Binding Item "+position);
            ///You can add your custom code here
        }
    }
// to Check the number of item
    @Override
    public int getItemCount() {
        int itemCount=data.size();
        //if header is required then increase the number of count by one
        if(isHeader){
            itemCount=itemCount+1;
        }
        return itemCount;
    }
    public String getName(int position){
        if(isHeader){
            return data.get(position-1);
        }
        else return data.get(position);
    }

    @Override
    public int getItemViewType(int position) {
//check if header required then position must be one and return the header view
        if (isHeader && isHeader(position))
            return VIEW_HEADER;
        else return VIEW_ITEM;
    }

    private boolean isHeader(int position) {
       //Check the position of item if item at position 0 then return true else false
            return position == 0;
    }

    public class headView extends RecyclerView.ViewHolder {

        protected TextView header;
        public headView(View v) {
            super(v);
            header =  (TextView) v.findViewById(R.id.header);
        }


    }

    public class itemView extends RecyclerView.ViewHolder {

        protected TextView itemName;
        public itemView(View v) {
            super(v);
            itemName =  (TextView) v.findViewById(R.id.item_name);
        }


    }
}
