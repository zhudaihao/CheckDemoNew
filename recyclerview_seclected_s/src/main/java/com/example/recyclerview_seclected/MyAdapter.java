package com.example.recyclerview_seclected;


import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by Administrator on 2017/8/22.
 */

public class MyAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {



    public MyAdapter(List<UserBean> mList) {
        super(R.layout.item_layout, mList);
        this.mList = mList;
    }

    private int positions;
    private List<UserBean> mList;
    //重点 设置数据时把点击那里item获取
    public void setList(List<UserBean> mList, int positions) {
        this.mList = mList;
        this.positions = positions;
        notifyDataSetChanged();

    }


    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        TextView tvId = helper.itemView.findViewById(R.id.item_horizontaltext);
        tvId.setText(item.getName() + "");

        if (positions == helper.getPosition()) {
            tvId.setSelected(true);
        } else {
            tvId.setSelected(false);
        }

    }


}
