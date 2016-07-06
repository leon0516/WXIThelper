package cn.openiotlab.wxithelper;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.openiotlab.wxithelper.Utils.NetTask;
import cn.openiotlab.wxithelper.Utils.SharePrefUtil;

/**
 * 8. * @{#} SplashActivity.java Create on 2013-5-2 下午9:10:01
 * 9. *
 * 10. * class desc: 启动画面
 * 11. *
 * 12. * <p>Copyright: Copyright(c) 2013 </p>
 * 13. * @Version 1.0
 * 14. * @Author <a href="mailto:gaolei_xj@163.com">Leo</a>
 * 15. *
 * 16. *
 * 17.
 */
public class SplashActivity extends Activity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    //延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSliderInfo();
        if (SharePrefUtil.getBoolean(this, "firststart", true)) {
            //将登录标志位设置为false，下次登录时不在显示首次登录界面
            SharePrefUtil.saveBoolean(this, "firststart", false);
            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);
            this.finish();
        } else if (SharePrefUtil.getBoolean(SplashActivity.this, "isAutoLogin", false)
                && !SharePrefUtil.getString(SplashActivity.this, "user", "").isEmpty()
                && !SharePrefUtil.getString(SplashActivity.this, "password", "").isEmpty()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    gomain();
                }
            }, SPLASH_DELAY_MILLIS);
        } else {
                        // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    gomain();
                }
            }, SPLASH_DELAY_MILLIS);
        }
    }

    private void getSliderInfo() {
        NetTask.getSliderInfo(new NetTask.ResultTask() {
            @Override
            public void OnSuccess(int statusCode, String responseString) {
                if (statusCode != 200) {
                    Toast.makeText(SplashActivity.this, "获取幻灯片数据错误：" + statusCode, Toast.LENGTH_SHORT).show();
                } else
                    SharePrefUtil.saveString(SplashActivity.this, "slider", responseString);
            }

            @Override
            public void onFailure(int statusCode, String responseString) {
                Toast.makeText(SplashActivity.this, "获取幻灯片数据错误：" + statusCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gomain() {
        Intent main = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(main);
        this.finish();
    }

    private void goLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
        int version = Integer.valueOf(android.os.Build.VERSION.SDK);
        if (version >= 5) {
            overridePendingTransition(R.anim.fade, R.anim.hold);  //此为自定义的动画效果，下面两个为系统的动画效果
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}

