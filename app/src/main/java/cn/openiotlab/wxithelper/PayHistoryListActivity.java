package cn.openiotlab.wxithelper;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.openiotlab.wxithelper.Beans.QueryPayListBeans;
import cn.openiotlab.wxithelper.Utils.CommandUtil;
import cn.openiotlab.wxithelper.Utils.ListViewAdapter;
import cn.openiotlab.wxithelper.Utils.NetTask;
import cn.openiotlab.wxithelper.Utils.NetUtils;
import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;


public class PayHistoryListActivity extends ActionBarActivity implements View.OnClickListener {
    ListViewAdapter adapter = null;
    private Handler handler = new Handler();
    private ZrcListView mListView;
    private int beginIndex = 0;
    private long time = 2000;
    List<QueryPayListBeans.BodyEntity.DataEntity> dataEntities = new ArrayList<QueryPayListBeans.BodyEntity.DataEntity>();
    List<QueryPayListBeans.BodyEntity.DataEntity> dataEntitiy = new ArrayList<QueryPayListBeans.BodyEntity.DataEntity>();
    QueryPayListBeans queryPayListBeans = new QueryPayListBeans();
    private TextView headerTitle;
    private Button btnBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pay_history_list);
        initView();
        initZrcLV();
        mListView.refresh();
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

    private void refresh() {
        if (NetUtils.isNetworkAvailable(PayHistoryListActivity.this) != 0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String cmdre = CommandUtil.QueryPayHistoryListCMD(CommandUtil.getBeginData(), CommandUtil.getEndData(), beginIndex = 0, 10);
                    NetTask.doNet(PayHistoryListActivity.this, cmdre, new NetTask.ResultTask() {
                        public void OnSuccess(int statusCode, String responseString) {
                            queryPayListBeans = new Gson().fromJson(responseString, QueryPayListBeans.class);
                            if (queryPayListBeans.getResultCode() == 0) {
                                dataEntities = queryPayListBeans.getBody().getData();
                                adapter = new ListViewAdapter(dataEntities, PayHistoryListActivity.this);
                                mListView.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();
                                mListView.setRefreshSuccess("加载成功"); // 通知加载成功
                                beginIndex = 10;
                                mListView.startLoadMore();
                            } else mListView.setRefreshFail("加载失败");
                        }

                        public void onFailure(int statusCode, String responseString) {
                            mListView.setRefreshFail("加载失败");
                        }
                    });
                }
            }, time);
        } else {
            Toast.makeText(PayHistoryListActivity.this, "请先打开网络!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadMore() {
        if (NetUtils.isNetworkAvailable(PayHistoryListActivity.this) != 0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String cmdload = CommandUtil.QueryPayHistoryListCMD("2015-05-01", "2015-05-31", beginIndex, 10);
                    NetTask.doNet(PayHistoryListActivity.this, cmdload, new NetTask.ResultTask() {
                        public void OnSuccess(int statusCode, String responseString) {
                            queryPayListBeans = new Gson().fromJson(responseString, QueryPayListBeans.class);
                            dataEntitiy = queryPayListBeans.getBody().getData();
                            if (dataEntitiy != null && !dataEntitiy.isEmpty() && dataEntitiy.size() > 0) {
                                if (dataEntitiy.size() < 10) {
                                    mListView.stopLoadMore();
                                }
                                dataEntities.addAll(dataEntitiy);
                                adapter.notifyDataSetChanged();
                                mListView.setLoadMoreSuccess();
                                beginIndex += 10;
                            } else {
                                mListView.stopLoadMore();
                            }
                        }

                        public void onFailure(int statusCode, String responseString) {
                            mListView.stopLoadMore();
                            Toast.makeText(PayHistoryListActivity.this, "请求网络超时!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }, time);
        } else {
            Toast.makeText(PayHistoryListActivity.this, "请先打开网络!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initZrcLV() {
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


    private void initView() {
        mListView = (ZrcListView) findViewById(R.id.zListView);
        headerTitle=(TextView)findViewById(R.id.header_title);
        headerTitle.setText("近30天消费记录");
        btnBack = (Button) findViewById(R.id.header_but);
        btnBack.setBackgroundResource(R.drawable.ic_chevron_left_white_48dp);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.header_but:
                this.finish();
                break;
            default:
                break;
        }
    }
}
