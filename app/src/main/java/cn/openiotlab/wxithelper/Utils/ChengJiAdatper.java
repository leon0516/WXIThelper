package cn.openiotlab.wxithelper.Utils;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.openiotlab.wxithelper.Beans.ChenJiBeans;
import cn.openiotlab.wxithelper.R;

/**
 * Created by Leon on 2015/5/31.
 */
public class ChengJiAdatper extends BaseAdapter {
    private List<ChenJiBeans.CjEntity> beans = new ArrayList<>();
    private Context context;

    public ChengJiAdatper(List<ChenJiBeans.CjEntity> beans, Context context) {
        this.beans = beans;
        this.context = context;
    }

    public int getCount() {
        return beans.size();
    }

    public Object getItem(int position) {
        return beans.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.cheng_ji_item, null);
            holder.tv_xueqi = (TextView) convertView.findViewById(R.id.chengji_xueqi);
            holder.tv_kechengmingcheng = (TextView) convertView.findViewById(R.id.chengji_kechengmingchen);
            holder.tv_fenshu = (TextView) convertView.findViewById(R.id.chengji_fenshu);
            holder.tv_leibie = (TextView) convertView.findViewById(R.id.chengji_leibie);
            holder.tv_xueshi = (TextView) convertView.findViewById(R.id.chengji_xueshi);
            holder.tv_xuefen = (TextView) convertView.findViewById(R.id.chengji_xuefen);
            holder.tv_kaoshixingzhi = (TextView) convertView.findViewById(R.id.chengji_kaoshixingzhi);
            holder.tv_jidian = (TextView) convertView.findViewById(R.id.chengji_jidian);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_xueqi.setText(beans.get(position).getXueqi());
        holder.tv_kechengmingcheng.setText(beans.get(position).getKechengmingcheng());
        holder.tv_kechengmingcheng.setMovementMethod(ScrollingMovementMethod.getInstance());
        holder.tv_fenshu.setText(beans.get(position).getChengji());
        holder.tv_leibie.setText(beans.get(position).getLeibie());
        holder.tv_xueshi.setText("学时" + beans.get(position).getXueshi());
        holder.tv_xuefen.setText("学分" + beans.get(position).getXuefen());
        if (beans.get(position).getJidian().trim().equals("")) {
            holder.tv_jidian.setText("无");
        } else
            holder.tv_jidian.setText(beans.get(position).getJidian());
        holder.tv_kaoshixingzhi.setText(beans.get(position).getKaoshixingzhi());

        return convertView;
    }


    public static class ViewHolder {
        public TextView tv_xueqi;
        public TextView tv_kechengmingcheng;
        public TextView tv_fenshu;
        public TextView tv_leibie;
        public TextView tv_xueshi;
        public TextView tv_xuefen;
        public TextView tv_kaoshixingzhi;
        public TextView tv_jidian;

    }
}
