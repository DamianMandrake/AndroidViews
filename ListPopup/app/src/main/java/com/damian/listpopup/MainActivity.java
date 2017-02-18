package com.damian.listpopup;

import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.MyViewGroup.MakeSnackBar,RecyclerAdapter.MyViewGroup.GetFromList{


    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<String> arrayList,tempList;
    private CustomArrayAdapter customArrayAdapter;
    private SearchView searchView;
    private ListPopupWindow listPopupWindow;


    static int MAX_HEGIGHT=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.arrayList=new ArrayList<>();
        this.tempList=new ArrayList<>();
        initArrayList();
        setCallbacks();

        MAX_HEGIGHT= (int)(getResources().getDisplayMetrics().heightPixels*0.30);

        this.toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);

        this.recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        this.recyclerAdapter=new RecyclerAdapter(this.arrayList,getApplicationContext());
        this.recyclerView.setAdapter(this.recyclerAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    private void initArrayList(){
        for(int i=0;i<100;i++)
            this.arrayList.add("hello world "+i);
    }
    private void setCallbacks(){
        RecyclerAdapter.MyViewGroup.setGetFromList(this);
        RecyclerAdapter.MyViewGroup.setMakeSnackBar(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){

        MenuItem menuItem=menu.findItem(R.id.action_search);


        listPopInit();



        this.searchView=(SearchView)MenuItemCompat.getActionView(menuItem);
        this.listPopupWindow.setAnchorView(searchView);
        this.listPopupWindow.setModal(false);



        int resId=android.support.v7.appcompat.R.id.search_bar;
        //int breadth=((View)(findViewById(resId))).getWidth();
        //this.listPopupWindow.setWidth(breadth);
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(MainActivity.this.listPopupWindow!=null&& newText.length()>0)
                MainActivity.this.listPopupWindow.show();
                MainActivity.this.tempList=new ArrayList<String>();
                for(String s:MainActivity.this.arrayList)
                    if(s.contains(newText))
                        MainActivity.this.tempList.add(s);

                MainActivity.this.customArrayAdapter.setFilter(MainActivity.this.tempList);


                return true;
            }
        });


        return true;

    }
    private void listPopInit(){
        this.listPopupWindow=new ListPopupWindow(this);
        this.customArrayAdapter=new CustomArrayAdapter(this.arrayList,this,R.layout.list_item);
        this.listPopupWindow.setWidth(RecyclerView.LayoutParams.MATCH_PARENT);
        this.listPopupWindow.setHeight(MainActivity.MAX_HEGIGHT);
        this.listPopupWindow.setDropDownGravity(Gravity.TOP);

        this.listPopupWindow.setAdapter(this.customArrayAdapter);//make custom array adapter and call filter method just like you did witht he recyclyer adapter
        this.listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //code to move recycler adapter to particular index


                showMsg(getDataFromTempList(position));
                if(MainActivity.this.listPopupWindow.isShowing())
                    MainActivity.this.listPopupWindow.dismiss();
            }
        });
    }













    @Override
    public void showMsg(String msg){
        Snackbar.make(findViewById(android.R.id.content),msg,Snackbar.LENGTH_SHORT).show();
    }
    @Override
    public String getDataFromOgList(int position){
        return this.arrayList.get(position);
    }
    public String getDataFromTempList(int position){
        return this.tempList.get(position);
    }


}
