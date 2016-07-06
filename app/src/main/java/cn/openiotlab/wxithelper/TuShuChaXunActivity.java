package cn.openiotlab.wxithelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.openiotlab.wxithelper.Beans.TuShuBeans;
import cn.openiotlab.wxithelper.Utils.InitViewUtils;
import cn.openiotlab.wxithelper.Utils.NetTask;
import cn.openiotlab.wxithelper.Utils.TuShuAdatper;
import zrc.widget.ZrcListView;


public class TuShuChaXunActivity extends Activity implements View.OnClickListener {

    TuShuAdatper adapter = null;
    private ZrcListView mListView;
    private TextView headerTitle;
    private Button btnBack;
    private EditText ed_book_search;
    List<TuShuBeans.JieguoEntity> dataEntities = new ArrayList<TuShuBeans.JieguoEntity>();
    List<TuShuBeans.JieguoEntity> dataEntitiy = new ArrayList<TuShuBeans.JieguoEntity>();
    TuShuBeans tushuDataBeans = new TuShuBeans();
    TuShuBeans tushuData = new TuShuBeans();
    private int page;
    private ImageButton btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_shu_cha_xun);
        initView();
        InitViewUtils.initZecListView(this, mListView);
        getData();
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
        NetTask.getTuShu(this,ed_book_search.getText().toString().trim() ,page, new NetTask.ResultTask() {
            @Override
            public void OnSuccess(int statusCode, String responseString) {
                if (statusCode == 200) {
                    tushuData = new Gson().fromJson(responseString.trim(), TuShuBeans.class);
                    if (tushuData.getResult() == 0) {
                        dataEntitiy = tushuData.getJieguo();
                        dataEntities.addAll(dataEntitiy);
                        adapter.notifyDataSetChanged();
                        mListView.setLoadMoreSuccess();
                        if (tushuData.getPages() - page <= 0) {
                            mListView.stopLoadMore();
                        }
                        page++;
                    } else if (tushuData.getResult() == 1) {
                        Toast.makeText(TuShuChaXunActivity.this, "未查询到相关书籍！", Toast.LENGTH_SHORT).show();
                        mListView.setRefreshFail("加载失败");
                    } else if (tushuData.getResult() == 2) {
                        Toast.makeText(TuShuChaXunActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                        mListView.setRefreshFail("加载失败");
                    }
                } else {
                    Toast.makeText(TuShuChaXunActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                    mListView.setRefreshFail("加载失败");
                }
            }

            @Override
            public void onFailure(int statusCode, String responseString) {
                mListView.setRefreshFail("加载失败");
                Toast.makeText(TuShuChaXunActivity.this, "出错" + statusCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refresh() {
        NetTask.getTuShu(this, ed_book_search.getText().toString().trim(), page = 1, new NetTask.ResultTask() {
            @Override
            public void OnSuccess(int statusCode, String responseString) {
                if (statusCode == 200) {
                    tushuDataBeans = new Gson().fromJson(responseString, TuShuBeans.class);
                    if (tushuDataBeans.getResult() == 0) {
                        dataEntities = tushuDataBeans.getJieguo();
                        adapter = new TuShuAdatper(dataEntities, TuShuChaXunActivity.this);
                        mListView.setAdapter(adapter);
                        mListView.setRefreshSuccess("加载成功"); // 通知加载成功
                        page++;
                        if (tushuDataBeans.getPages() > 1) {
                            mListView.startLoadMore();
                        } else {
                            mListView.stopLoadMore();
                        }
                    } else if (tushuDataBeans.getResult() == 1) {
                        Toast.makeText(TuShuChaXunActivity.this, "未查询到相关书籍！", Toast.LENGTH_SHORT).show();
                        mListView.setRefreshFail("加载失败");
                    } else if (tushuDataBeans.getResult() == 2) {
                        Toast.makeText(TuShuChaXunActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                        mListView.setRefreshFail("加载失败");
                    }
                } else {
                    Toast.makeText(TuShuChaXunActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                    mListView.setRefreshFail("加载失败");
                }

            }

            @Override
            public void onFailure(int statusCode, String responseString) {
                Toast.makeText(TuShuChaXunActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                mListView.setRefreshFail("加载失败");
            }
        });
    }

    private void initView() {
        mListView = (ZrcListView) findViewById(R.id.tushu_listView);
        headerTitle = (TextView) findViewById(R.id.header_title);
        headerTitle.setText("馆藏查询");
        btnBack = (Button) findViewById(R.id.header_but);
        btnBack.setBackgroundResource(R.drawable.ic_chevron_left_white_48dp);
        btnBack.setOnClickListener(this);
        ed_book_search = (EditText) findViewById(R.id.ed_book_search);
        btn_search = (ImageButton) findViewById(R.id.IB_btn_search);
        btn_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_but:
                this.finish();
                break;
            case R.id.IB_btn_search:
                refresh();
                break;
            default:
                break;
        }
    }
}
