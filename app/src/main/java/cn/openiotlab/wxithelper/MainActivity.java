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
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.igexin.sdk.PushManager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.xiaomi.market.sdk.XiaomiUpdateAgent;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import cn.openiotlab.wxithelper.Utils.SharePrefUtil;

public class MainActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener, View.OnClickListener {

    private SliderLayout mDemoSlider;
    private SlidingMenu slidingmenu;
    private String[] title = null;
    private String[] url = null;
    private int[] id = null;
    String[] titles = {"公告", "新闻", "一卡通", "教务", "馆藏查询", "失物招领", "跳蚤市场", "快递查询", "论坛"};
    int[] img = {R.drawable.gonggao, R.drawable.news, R.drawable.card, R.drawable.book, R.drawable.kebiao, R.drawable.zhaoling, R.drawable.tiaocao, R.drawable.wupin, R.drawable.luntan};
    private ImageView headImage;
    private TextView headText;
    private Button btnHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        PushManager.getInstance().initialize(this.getApplicationContext());
        getSlider();
        XiaomiUpdateAgent.update(this);
        initSlidingMenu();
        initView();
        initNINE();
    }

    private void getSlider() {
        String responseString = SharePrefUtil.getString(MainActivity.this, "slider", "");
        if (responseString == "") {
            Toast.makeText(MainActivity.this, "获取幻灯片数据异常！", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONArray data = new JSONArray(responseString);
                id = new int[data.length()];
                title = new String[data.length()];
                url = new String[data.length()];
                for (int i = 0; i <= data.length(); i++) {
                    id[i] = data.getJSONObject(i).getInt("id");
                    title[i] = data.getJSONObject(i).getString("title");
                    url[i] = data.getJSONObject(i).getString("url");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    private void dialog(String title, String tipsBegin, String mainfare, String tipsEnd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(tipsBegin + mainfare + tipsEnd);
        builder.setTitle(title);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void dialogLogin(String title, String tipsBegin, String mainfare, String tipsEnd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(tipsBegin + mainfare + tipsEnd);
        builder.setTitle(title);
        builder.setNegativeButton("去登陆", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("不登了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void initNINE() {
        GridView gridview = (GridView) findViewById(R.id.gv_menu);
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
                                                    Toast.makeText(MainActivity.this, "功能暂未开放", Toast.LENGTH_SHORT).show();
                                                } else if (arg2 == 0) {
                                                    Intent intent = new Intent(MainActivity.this, GongGaoActivity.class);
                                                    startActivity(intent);
                                                } else if (arg2 == 2) {
                                                    if (SharePrefUtil.getBoolean(MainActivity.this, "isLogin", false)) {
                                                        Intent intent = new Intent(MainActivity.this, QueryMainActivity.class);
                                                        startActivity(intent);
                                                    } else {
                                                        dialogLogin("提示", getString(R.string.denglutishi), "", "");
                                                    }

                                                } else if (arg2 == 3) {
                                                    if (SharePrefUtil.getBoolean(MainActivity.this, "isLogin", false)) {
                                                        Intent intent = new Intent(MainActivity.this, ChengJiActivity.class);
                                                        startActivity(intent);
                                                    } else {
                                                        dialogLogin("提示", getString(R.string.denglutishi), "", "");
                                                    }

                                                } else if (arg2 == 4) {
                                                    Intent intent = new Intent(MainActivity.this, TuShuChaXunActivity.class);
                                                    startActivity(intent);
                                                }else
                                                    Toast.makeText(MainActivity.this, arg2 + "", Toast.LENGTH_SHORT).show();
                                            }
                                        }
        );
    }

    private void initSlidingMenu() {
        slidingmenu = new SlidingMenu(this);
        slidingmenu.setMode(SlidingMenu.LEFT);
        slidingmenu.setBehindOffsetRes(R.dimen.slidingmenu);
        slidingmenu.setFadeDegree(0.5f);
        slidingmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingmenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        slidingmenu.setMenu(R.layout.sliding_menu);

    }

    private void initView() {
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        slider();
        headImage = (ImageView) findViewById(R.id.headImage);
        headText = (TextView) findViewById(R.id.headtext);
        if (SharePrefUtil.getBoolean(MainActivity.this, "isLogin", false) == true) {
            headText.setText(SharePrefUtil.getString(MainActivity.this, "name", "登陆账号"));
        } else {
            headText.setText("登陆账号");
        }
        headImage.setOnClickListener(this);
        headText.setOnClickListener(this);
        btnHead = (Button) findViewById(R.id.header_but);
        btnHead.setOnClickListener(this);
    }

    private void slider() {
        HashMap<String, String> url_maps = new HashMap<String, String>();
        for (int i = 0; i < id.length; i++) {
            url_maps.put(title[i], url[i]);
        }

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.headtext:
                if (headText.getText().toString().equals(SharePrefUtil.getString(MainActivity.this, "name", ""))) {
                    Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.headImage:
                if (headText.getText().toString().equals(SharePrefUtil.getString(MainActivity.this, "name", ""))) {
                    Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.header_but:
                slidingmenu.toggle();
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharePrefUtil.getBoolean(MainActivity.this, "isLogin", false) == true) {
            headText.setText(SharePrefUtil.getString(MainActivity.this, "name", "登陆账号"));
        } else {
            headText.setText("登陆账号");
        }
    }
}