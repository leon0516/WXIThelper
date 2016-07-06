package cn.openiotlab.wxithelper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import cn.openiotlab.wxithelper.Beans.GongGaoBeans;
import cn.openiotlab.wxithelper.Beans.GongGaoDataBean;
import cn.openiotlab.wxithelper.Utils.GongGaoAdatper;
import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;


public class GongGaoActivity extends ActionBarActivity {
    GongGaoAdatper adapter = null;
    private android.os.Handler handler = new android.os.Handler();
    private ZrcListView mListView;
    GongGaoBeans testbean;
    List<GongGaoDataBean> beans = new ArrayList<GongGaoDataBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_gong_gao);
        initView();
        getData();
        mListView.refresh();
        selecdItem();
    }

    private void selecdItem() {
        mListView.setOnItemClickListener(new ZrcListView.OnItemClickListener() {
            @Override
            public void onItemClick(ZrcListView parent, View view, int position, long id) {
                if (id == 0) {
                    Toast.makeText(GongGaoActivity.this, id + "," + id, Toast.LENGTH_SHORT).show();
                } else if (id == 1) {
                    Toast.makeText(GongGaoActivity.this, id + "," + id, Toast.LENGTH_SHORT).show();
                } else if (id == 2) {
                    Toast.makeText(GongGaoActivity.this, id + "," + id, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData() {
        mListView.setOnRefreshStartListener(new ZrcListView.OnStartListener() {
            @Override
            public void onStart() {
                refresh();
            }
        });
        mListView.setOnLoadMoreStartListener(new ZrcListView.OnStartListener() {
            @Override
            public void onStart() {
                loadMore();
            }
        });

    }

    private void loadMore() {

    }

    private void refresh() {
        AsyncHttpClient getClient = new AsyncHttpClient();
        getClient.setTimeout(2000);
        getClient.get("http://112.124.39.140:8888/gonggao", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                mListView.setRefreshFail("加载失败");
                Toast.makeText(GongGaoActivity.this, "出错" + statusCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                if (statusCode == 200) {
                    testbean = new Gson().fromJson(responseString, GongGaoBeans.class);
                    beans = testbean.getTestbean();
                    adapter = new GongGaoAdatper(beans, GongGaoActivity.this);
                    mListView.setAdapter(adapter);
                    mListView.setRefreshSuccess("加载成功");
//                    mListView.startLoadMore();
                } else {
                    mListView.setRefreshFail("加载失败");
                    Toast.makeText(GongGaoActivity.this, "出错", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        NetTask.getData(new NetTask.ResultTask() {
//            @Override
//            public void OnSuccess(int statusCode, String responseString) {
//                if (statusCode == 200) {
//                    testbean = new Gson().fromJson(responseString, GongGaoBeans.class);
//                    beans = testbean.getTestbean();
//                    adapter = new GongGaoAdatper(beans, GongGaoActivity.this);
//                    mListView.setAdapter(adapter);
//                    mListView.setRefreshSuccess("加载成功");
//                } else
//                    Toast.makeText(GongGaoActivity.this, "出错", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(int statusCode, String responseString) {
//                Toast.makeText(GongGaoActivity.this, "出错", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void initView() {
        mListView = (ZrcListView) findViewById(R.id.zListView);
        // 设置下拉刷新的样式（可选，但如果没有Header则无法下拉刷新）
        SimpleHeader header = new SimpleHeader(this);
        header.setTextColor(0xff0066aa);
        header.setCircleColor(0xff33bbee);
        mListView.setHeadable(header);

        // 设置加载更多的样式（可选）
        SimpleFooter footer = new SimpleFooter(this);
        footer.setCircleColor(0xff33bbee);
        mListView.setFootable(footer);

        // 设置列表项出现动画（可选）
        mListView.setItemAnimForTopIn(R.anim.topitem_in);
        mListView.setItemAnimForBottomIn(R.anim.bottomitem_in);
    }
}
