package com.example.recyclerview_check;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HorizontalAdapter_new horizontalAdapter;
    private int positions = -1;//设置初始化选中的item
    private List<String> mList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        horizontalAdapter = new HorizontalAdapter_new(mList);

      /*  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/

        SmoothScrollLayoutManager linearLayoutManager = new SmoothScrollLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(horizontalAdapter);

        setListener();
        initData();
    }


    //监听
    private void setListener() {
        horizontalAdapter.setOnItemClickListener(new HorizontalAdapter_new.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                horizontalAdapter.setList(mList, position);

            }
        });
    }

    //数据
    private void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add("第一" + i + "中学");
        }
        horizontalAdapter.setList(mList, positions);

    }

    //点击后最后显示的item
    public void send(View view) {
        //50就是整个屏幕显示，最后显示的item
        recyclerView.smoothScrollToPosition(50);

    }

    //自定义Manager,修改滑动速度((如果项目需要sm什么manger就继承什么manger))
    public class SmoothScrollLayoutManager extends LinearLayoutManager {

        public SmoothScrollLayoutManager(Context context) {
            super(context);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, final int position) {

            LinearSmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
                // 返回：滑过1px时经历的时间(ms)。
                @Override
                protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return 150f / displayMetrics.densityDpi;
                }
            };

            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }
    }
}
