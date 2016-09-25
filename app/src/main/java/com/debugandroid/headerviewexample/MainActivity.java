package com.debugandroid.headerviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<String> data=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        for (int i=0;i<=100;i++){
            data.add("Item "+i);
        }
        GridLayoutManager glm = new GridLayoutManager(this, 2);

        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {

                switch (adapter.getItemViewType(position)){
                    case 0: return 2;
                    case 1: return 1;
                    default: return 2;
                }

            }
        });
        recyclerView.setLayoutManager(glm);

        adapter=new Adapter(this,data,true);

        recyclerView.setAdapter(adapter);
    }
}
