package com.hxy.SwipeRefreshLayoutDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
   private SwipeRefreshLayout swipeLayout;
    private ListView listView;
    private ListviewAdapter adapter;
    private List<ItemInfo> infoList;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        swipeLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_refresh);
        swipeLayout.setOnRefreshListener(this);

        // 顶部刷新的样式
        swipeLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);

        infoList = new ArrayList<ItemInfo>();
        ItemInfo info = new ItemInfo();
        info.setName("coin");
        infoList.add(info);
        listView = (ListView) this.findViewById(R.id.listview);
        adapter = new ListviewAdapter(this, infoList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setRefreshing(false);
                ItemInfo info = new ItemInfo();
                info.setName("coin-refresh");
                infoList.add(info);
                adapter.notifyDataSetChanged();
            }
        }, 500);
    }
}
