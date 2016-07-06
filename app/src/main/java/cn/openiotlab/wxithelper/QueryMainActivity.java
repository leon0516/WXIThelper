package cn.openiotlab.wxithelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import cn.openiotlab.wxithelper.Beans.QueryPayListBeans;
import cn.openiotlab.wxithelper.Utils.CommandUtil;
import cn.openiotlab.wxithelper.Utils.NetTask;
import cn.openiotlab.wxithelper.Utils.SharePrefUtil;


public class QueryMainActivity extends ActionBarActivity implements View.OnClickListener {
    private String[] titles = {"账户余额", "近期消费", "消费统计", "修改证件", "挂失", "辅助挂失", "修改手机", "修改密码", "充值汇总"};
    private int[] img = {R.drawable.money, R.drawable.xiaofei, R.drawable.xiaofeitongj, R.drawable.id, R.drawable.warn, R.drawable.help, R.drawable.phone, R.drawable.mima, R.drawable.xiaofeitongj};
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_query_main);
        initView();
        initNINE();
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.header_but);
        btnBack.setBackgroundResource(R.drawable.ic_chevron_left_white_48dp);
        btnBack.setOnClickListener(this);
    }

    private void initNINE() {
        GridView gridview = (GridView) findViewById(R.id.gv_query_menu);
        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < 9; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", img[i]);
            map.put("ItemText", titles[i]);
            meumList.add(map);
        }

        SimpleAdapter saMenuItem = new SimpleAdapter(this,
                meumList, //数据源
                R.layout.menuitem, //xml实现
                new String[]{"ItemImage", "ItemText"}, //对应map的Key
                new int[]{R.id.ItemImage, R.id.ItemText});  //对应R的Id

//添加Item到网格中
        gridview.setAdapter(saMenuItem);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                                if (arg2 == 1) {
                                                    Intent intent = new Intent(QueryMainActivity.this, PayHistoryListActivity.class);
                                                    startActivity(intent);
                                                } else if (arg2 == 0) {
                                                    getMainFare();
                                                } else if (arg2 == 2) {
                                                    getTotal();
                                                } else if (arg2 == 3) {
                                                    getMainFare();
                                                } else if (arg2 == 4) {
                                                    getMainFare();
                                                } else if (arg2 == 5) {
                                                    getMainFare();
                                                } else if (arg2 == 6) {
                                                    getMainFare();
                                                } else if (arg2 == 7) {
                                                    getMainFare();
                                                } else if (arg2 == 8) {
                                                    getMainFare();
                                                } else
                                                    Toast.makeText(QueryMainActivity.this, arg2 + "", Toast.LENGTH_SHORT).show();
                                            }
                                        }
        );
    }

    private void getTotal() {
        String cmd = CommandUtil.QueryPayOneMonthMoneyTotalCMD();
        NetTask.doNet(QueryMainActivity.this, cmd, new NetTask.ResultTask() {
            @Override
            public void OnSuccess(int statusCode, String responseString) {
                if (statusCode == 200) {
                    QueryPayListBeans root = new Gson().fromJson(responseString, QueryPayListBeans.class);
                    String a = root.getBody().getMainFare();
                    dialog("查询结果", "近30天消费:", a, "元");
                } else Toast.makeText(QueryMainActivity.this, "请求出错!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, String responseString) {
                Toast.makeText(QueryMainActivity.this, "", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getMainFare() {
        String mainFare = SharePrefUtil.getString(QueryMainActivity.this, "MainFare", "");
        dialog("查询结果", "账户余额:", mainFare, "元");
    }

    private void dialog(String title, String tipsBegin, String mainfare, String tipsEnd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QueryMainActivity.this);
        builder.setMessage(tipsBegin + mainfare + tipsEnd);
        builder.setTitle(title);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                     QueryMainActivity.this.finish();
            }
        });
//          builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//             @Override
//               public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                   }
//              });
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_but:
                this.finish();
                break;
            default:
                break;
        }
    }
}
