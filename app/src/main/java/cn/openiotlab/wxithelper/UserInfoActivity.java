package cn.openiotlab.wxithelper;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.openiotlab.wxithelper.Utils.SharePrefUtil;


public class UserInfoActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView stuName;
    private TextView stuMobile;
    private TextView stuEmail;
    private TextView stuAddress;
    private TextView stuSex;
    private ImageView userInfoBack;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_info);
        initView();
    }

    private void initView() {
        btnLogout =(Button)findViewById(R.id.btn_logout);
        stuName =(TextView)findViewById(R.id.stuName);
        stuMobile=(TextView)findViewById(R.id.phone);
        stuEmail=(TextView)findViewById(R.id.email);
        stuSex=(TextView)findViewById(R.id.sex);
        stuAddress=(TextView)findViewById(R.id.address);
        userInfoBack=(ImageView)findViewById(R.id.imageButton);
        stuSex.setText("");
        stuAddress.setText("");
        stuEmail.setText("");
        btnLogout.setOnClickListener(this);
        userInfoBack.setOnClickListener(this);
        stuName.setText(SharePrefUtil.getString(UserInfoActivity.this,"name",""));
        stuMobile.setText(SharePrefUtil.getString(UserInfoActivity.this,"mobile",""));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton:
                this.finish();
                break;
            case R.id.btn_logout:
                SharePrefUtil.saveBoolean(UserInfoActivity.this,"isLogin",false);
                SharePrefUtil.removeItem(UserInfoActivity.this,"name");
                this.finish();
                break;
            default:
                break;
        }
    }
}
