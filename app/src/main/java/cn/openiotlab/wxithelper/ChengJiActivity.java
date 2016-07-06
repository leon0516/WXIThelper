package cn.openiotlab.wxithelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.openiotlab.wxithelper.Beans.ChenJiBeans;
import cn.openiotlab.wxithelper.Utils.ChengJiAdatper;
import cn.openiotlab.wxithelper.Utils.CommandUtil;
import cn.openiotlab.wxithelper.Utils.NetTask;
import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;


public class ChengJiActivity extends Activity implements View.OnClickListener {


    ChengJiAdatper adapter = null;
    private ZrcListView mListView;
    ChenJiBeans chengjibeans;
    List<ChenJiBeans.CjEntity> beans = new ArrayList<ChenJiBeans.CjEntity>();
    private TextView headerTitle;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheng_ji);
        initView();
        getData();
        mListView.refresh();
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
        NetTask.getChengJi(this, CommandUtil.chaXunChengJi(), new NetTask.ResultTask() {
            @Override
            public void OnSuccess(int statusCode, String responseString) {
                if (statusCode == 200) {
                    if (responseString.trim().isEmpty()){
                        Toast.makeText(ChengJiActivity.this, "error", Toast.LENGTH_SHORT).show();
                    } else {
                        chengjibeans = new Gson().fromJson(responseString, ChenJiBeans.class);
                        if (chengjibeans.getResult() == 0) {
                            beans = chengjibeans.getCj();
                            adapter = new ChengJiAdatper(beans, ChengJiActivity.this);
                            mListView.setAdapter(adapter);
                            mListView.setRefreshSuccess("加载成功");
                        } else
                            Toast.makeText(ChengJiActivity.this, responseString, Toast.LENGTH_SHORT).show();

                    }

                } else {
                    mListView.setRefreshFail("加载失败");
                    Toast.makeText(ChengJiActivity.this, "出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, String responseString) {
                mListView.setRefreshFail("加载失败");
                Toast.makeText(ChengJiActivity.this, "出错" + statusCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mListView = (ZrcListView) findViewById(R.id.chengji_listView);
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
        headerTitle=(TextView)findViewById(R.id.header_title);
        headerTitle.setText("成绩查询结果");
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
