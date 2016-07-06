package cn.openiotlab.wxithelper.Utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.openiotlab.wxithelper.Beans.GongGaoDataBean;
import cn.openiotlab.wxithelper.R;
import us.feras.mdv.MarkdownView;

/**
 * Created by Leon on 2015/5/26.
 */
public class GongGaoAdatper extends BaseAdapter {
    private List<GongGaoDataBean> beans = new ArrayList<>();
    private Context context;

    public GongGaoAdatper(List<GongGaoDataBean> beans, Context context) {
        this.beans = beans;
        this.context = context;
    }

    public int getCount() {
        return beans.size();
    }

    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.gong_gao_item, null);
            holder.tv_outer = (TextView) convertView.findViewById(R.id.outher);
            holder.tv_postTime = (TextView) convertView.findViewById(R.id.postTime);
            holder.tv_commentCount = (TextView) convertView.findViewById(R.id.comments);
            holder.mIM_headImage = (ImageView) convertView.findViewById(R.id.imageView);
            holder.tv_md = (MarkdownView) convertView.findViewById(R.id.markdownView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tv_outer.setText(beans.get(position).getStu_id());
        holder.tv_postTime.setText(beans.get(position).getCreat_time());
        holder.tv_commentCount.setText(beans.get(position).getComments_count());
        holder.mIM_headImage.setImageResource(R.mipmap.ic_launcher);
        holder.tv_md.loadMarkdown(beans.get(position).getGong_gao_describe(),"file:///android_asset/markdown_css_themes/classic.css");

        return convertView;
    }


    public static class ViewHolder {
        public TextView tv_outer;
        public TextView tv_postTime;
        public TextView tv_title;
        public TextView tv_commentCount;
        public ImageView mIM_headImage;
        public MarkdownView tv_md;

    }

}
