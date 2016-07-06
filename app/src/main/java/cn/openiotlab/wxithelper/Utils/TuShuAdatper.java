package cn.openiotlab.wxithelper.Utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.openiotlab.wxithelper.Beans.TuShuBeans;
import cn.openiotlab.wxithelper.R;
import us.feras.mdv.MarkdownView;

/**
 * Created by Leon on 2015/6/1.
 */
public class TuShuAdatper  extends BaseAdapter {
    private List<TuShuBeans.JieguoEntity> beans = new ArrayList<>();
    private Context context;

    public TuShuAdatper(List<TuShuBeans.JieguoEntity> beans, Context context) {
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
            convertView = View.inflate(context, R.layout.tu_shu_xin_xi_item, null);
            holder.tv_tushumingcheng = (TextView) convertView.findViewById(R.id.tushu_mingcheng);
            holder.mListview = (TextView) convertView.findViewById(R.id.tushu_xiangqing);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tv_tushumingcheng.setText(beans.get(position).getTitle());
        holder.mListview.setText(beans.get(position).getDec());
        return convertView;
    }


    public static class ViewHolder {
        public TextView tv_tushumingcheng;
        public TextView mListview;
    }
}
