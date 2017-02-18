package com.damian.listpopuptp;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.MyViewHolder.GetText{

    private ArrayList<String> arrayList,tempList;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private SearchView searchView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.arrayList=new ArrayList<>();
        this.tempList=new ArrayList<>();
        this.initArrayList();
        System.out.println("array list got set");
        this.recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        this.searchView=(SearchView)findViewById(R.id.searchView);
        this.toolbar=(Toolbar)findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);

        this.recyclerAdapter=new RecyclerAdapter(this.arrayList,this.getApplicationContext());
        this.recyclerView.setAdapter(this.recyclerAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println("RecyclerViews adapter got set ");
        RecyclerAdapter.MyViewHolder.setGetTextRef(this);
        RecyclerAdapter.MyViewHolder.setMakeSnackBar(new RecyclerAdapter.MyViewHolder.MakeSnackbar() {
            @Override
            public void showSnackBar(String msg) {
               Snackbar.make(findViewById(android.R.id.content) ,msg,Snackbar.LENGTH_SHORT).show();
            }
        });

        this.initSearchViewCallback();




    }
    void initSearchViewCallback(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MainActivity.this.tempList=new ArrayList<String>();
                for(String x:MainActivity.this.arrayList)
                    if(x.contains(newText))
                        MainActivity.this.tempList.add(x);


                MainActivity.this.recyclerAdapter.setFilter(tempList);

                return true;



            }
        });
    }
    public void initArrayList(){
        for(int i=0;i<100;i++)
            this.arrayList.add("hello world "+i);
    }




    @Override
    public String getTextFromList(int position){
        System.out.println("FROM templist");
        return this.tempList.get(position);
    }
    @Override
    public String getTextFromMainList(int position){
        System.out.println("FROM mainList");
        return this.arrayList.get(position);
    }
    @Override
    public int getTempListSize(){return tempList.size();}
}
