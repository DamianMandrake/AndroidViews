package com.damian.listpopup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Damian on 2/18/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewGroup> {

    private Context context;
    private ArrayList<String> arrayList;
    public RecyclerAdapter(ArrayList<String> arrayList,Context ctx){
        this.arrayList=arrayList;this.context=ctx;
    }



    @Override
    public MyViewGroup onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(this.context).inflate(R.layout.recycler_item,parent,false);

        return new MyViewGroup(view);
    }

    @Override
    public void onBindViewHolder(MyViewGroup holder, int position) {
        holder.textView.setText(this.arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    static class MyViewGroup extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        static MakeSnackBar makeSnackBar;
        static GetFromList getFromList;
         MyViewGroup(View view){
            super(view);
             view.setOnClickListener(this);
             textView=(TextView)view.findViewById(R.id.textView);
        }
        @Override
        public void onClick(View view){
            makeSnackBar.showMsg(getFromList.getDataFromOgList(getAdapterPosition()));
        }



        public static void setMakeSnackBar(MakeSnackBar ref){
            MyViewGroup.makeSnackBar=ref;
        }
        public static void setGetFromList(GetFromList get){
            MyViewGroup.getFromList=get;
        }



        interface MakeSnackBar{
            public void showMsg(String msg);
        }
        interface GetFromList{
            public String getDataFromOgList(int position);
            public String getDataFromTempList(int position);
        }




    }




}
