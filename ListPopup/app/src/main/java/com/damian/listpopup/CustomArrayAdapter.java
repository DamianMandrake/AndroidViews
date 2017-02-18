package com.damian.listpopup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Damian on 2/18/2017.
 */

public class CustomArrayAdapter extends BaseAdapter {

    private ArrayList<String> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;
    private int resourceId;//has to be row id
    public CustomArrayAdapter(ArrayList<String> arrayList, Context cont,int res){
        this.arrayList=arrayList;
        this.context=cont;
        this.resourceId=res;
        this.layoutInflater=LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public String getItem(int position) {
        return this.arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder viewHolder=null;

        if(row==null||row.getTag()==null){
            row=this.layoutInflater.inflate(resourceId,parent,false);
            viewHolder=new ViewHolder(row);
            row.setTag(viewHolder);
        }else
        viewHolder=(ViewHolder)row.getTag();

        viewHolder.text.setText(this.getItem(position));



        return row;
    }


    public void setFilter(ArrayList<String> arr){
        this.arrayList=new ArrayList<>();
        this.arrayList.addAll(arr);
        this.notifyDataSetChanged();
    }



    static class ViewHolder{
        TextView text;
        ViewHolder(View view){
            text=(TextView)view.findViewById(R.id.suggestion);

        }
    }











}
