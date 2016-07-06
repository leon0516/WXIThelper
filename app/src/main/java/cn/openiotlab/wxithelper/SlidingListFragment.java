package cn.openiotlab.wxithelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class SlidingListFragment extends ListFragment implements OnItemClickListener {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_left_fragment, container, false);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
                break;
            case 3:

                break;
            case 4:
                showShare();
                break;
            case 5:
                break;
            default:
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://wxit.wxitsky.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("无锡职院这么实用的软件，你值得拥有，查成绩、一卡通消费记录，一个软件统统搞定！");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://wxit.wxitsky.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("可以查成绩和一卡通真的太方便了！！");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://wxit.wxitsky.com");
        // 启动分享GUI
        oks.show(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getListView();
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                this.getData(),
                R.layout.menu_left_list,
                new String[]{"image", "text"},
                new int[]{R.id.menu_list_image, R.id.menu_list_text});
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setOnItemClickListener(this);
    }

    private ListView listView;

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int[] images = {
                R.drawable.menu_home,
                R.drawable.menu_management,
                R.drawable.menu_collect,
                R.drawable.menu_message,
                R.drawable.share,
                R.drawable.menu_setting
        };
        String[] textStrings = getResources().getStringArray(R.array.menuList);
        for (int i = 0; i < textStrings.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", images[i]);
            map.put("text", textStrings[i]);
            list.add(map);
        }
        return list;
    }
}
