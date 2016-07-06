package cn.openiotlab.wxithelper.Utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.openiotlab.wxithelper.Beans.QueryPayListBeans;
import cn.openiotlab.wxithelper.R;

/**
 * Created by Leon on 2015/5/16.
 */
public class ListViewAdapter extends BaseAdapter {

    private List<QueryPayListBeans.BodyEntity.DataEntity> beans = new ArrayList<>();
    private Context context;

    public ListViewAdapter(List<QueryPayListBeans.BodyEntity.DataEntity> beans, Context context) {
        this.beans = beans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item, null);
            holder.mTV_Date = (TextView) convertView.findViewById(R.id.tv_item_date);
            holder.mTV_Desc = (TextView) convertView.findViewById(R.id.tv_item_desc);
            holder.mTV_Fate = (TextView) convertView.findViewById(R.id.tv_item_opfare);
            holder.mIM_pic = (ImageView) convertView.findViewById(R.id.imageView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.mTV_Date.setText(beans.get(position).getOpdt());
        holder.mTV_Desc.setText(beans.get(position).getDescription());
        holder.mTV_Fate.setText(beans.get(position).getOpfare());
        if (beans.get(position).getDescription().toString().trim().equals("商场购物")) {
            holder.mIM_pic.setImageResource(R.drawable.shop);
        }else if(beans.get(position).getDescription().toString().trim().equals("餐费支出")) {
            holder.mIM_pic.setImageResource(R.drawable.food);
        }else if(beans.get(position).getDescription().toString().trim().equals("淋浴支出")) {
            holder.mIM_pic.setImageResource(R.drawable.bath);
        }else if(beans.get(position).getDescription().toString().trim().equals("购热水支出")) {
            holder.mIM_pic.setImageResource(R.drawable.water);
        }else {
            holder.mIM_pic.setImageResource(R.drawable.wallet);
        }

        return convertView;
    }

    public static class ViewHolder {
        public TextView mTV_Date;
        public TextView mTV_Desc;
        public TextView mTV_Fate;
        public ImageView mIM_pic;
    }
}
