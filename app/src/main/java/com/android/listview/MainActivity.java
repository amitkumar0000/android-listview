package com.android.listview;

import android.app.ListActivity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.listview.adapter.ArrayAdapterClass;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView nameList;
    ArrayList<String> list;
    ArrayAdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList = findViewById(R.id.namelist);

        setList();

    }

    private void setList() {
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile","Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

         adapter = new ArrayAdapterClass(this,1,R.layout.listrow,list);
//        final ArrayAdapter adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, list);

        nameList.setAdapter(adapter);
//        setListAdapter(adapter);

        nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,final View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
                Snackbar.make(view, "Here's a Snackbar with name"+list.get(position), Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                final String item = (String) adapterView.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }
        });

    }

    public void click(View view){
       list.add("Amit");

        adapter.notifyDataSet(list);


    }
}
