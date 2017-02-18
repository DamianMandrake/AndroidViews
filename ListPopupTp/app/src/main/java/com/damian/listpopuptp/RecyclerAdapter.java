package com.damian.listpopuptp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Damian on 2/17/2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    private ArrayList<String> arrayList;
    private Context context;
    public RecyclerAdapter(ArrayList<String> arrayList,Context c){
        System.out.println("IN ADAPTER CTOR");
        this.arrayList=arrayList;
        this.context=c;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(this.context).inflate(R.layout.recycler_item,parent,false);
        System.out.println("IN ON CREAETEVIEWHOLDER ABT TO RETURN SOME STUFF");

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText(this.arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }




    public void setFilter(ArrayList<String> a){
        this.arrayList=new ArrayList<>();
        this.arrayList.addAll(a);

        this.notifyDataSetChanged();
    }











    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;

        static MakeSnackbar ref;
        static GetText getTextRef;
        public MyViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
            this.textView=(TextView)v.findViewById(R.id.textView);

        }


        @Override
        public void onClick(View view){
            System.out.println("INSIDE ONCLCIK");
            if(getTextRef.getTempListSize()!=0)
                ref.showSnackBar(getTextRef.getTextFromList(getAdapterPosition()));//dont need to clear list since its being reinited in SearchViews OnQueryTextChange callback
            else
                ref.showSnackBar(getTextRef.getTextFromMainList(getAdapterPosition()));

        }

        public static void setMakeSnackBar(MakeSnackbar m){
            MyViewHolder.ref=m;

        }
        public static void setGetTextRef(GetText get){
            MyViewHolder.getTextRef=get;
        }


        interface MakeSnackbar{
            public void showSnackBar(String msg);
        }
        interface GetText{//to not make lists static

            public String getTextFromList(int p);
            public String getTextFromMainList(int p);
            public int getTempListSize();

        }




    }


}
