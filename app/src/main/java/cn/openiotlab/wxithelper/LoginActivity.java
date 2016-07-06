package cn.openiotlab.wxithelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.Gson;


import cn.openiotlab.wxithelper.Beans.LoginDataBeans;
import cn.openiotlab.wxithelper.Utils.CommandUtil;
import cn.openiotlab.wxithelper.Utils.NetTask;
import cn.openiotlab.wxithelper.Utils.NetUtils;
import cn.openiotlab.wxithelper.Utils.SharePrefUtil;


public class LoginActivity extends Activity implements View.OnClickListener {

    ProgressDialog prgDialog;
    private Button btnHead;
    private FormEditText edittextStuId;
    private FormEditText edittextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edittextStuId = (FormEditText) findViewById(R.id.editText_stu_id);
        edittextPassword = (FormEditText) findViewById(R.id.editText_passwd);
        btnHead = (Button) findViewById(R.id.header_but);
        btnHead.setVisibility(View.GONE);
        Button btn_login = (Button) findViewById(R.id.button_login);
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("登陆中...");
        prgDialog.setCancelable(false);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FormEditText[] allFields = {edittextStuId, edittextPassword};
        if (v.getId() == R.id.button_login) {
            boolean allValid = true;
            for (FormEditText field : allFields) {
                allValid = field.testValidity() && allValid;
            }
            if (edittextPassword.getText().toString().trim().length() <= 1 || edittextStuId.getText().toString().trim().length() <= 1) {
            } else {
                denglu();
            }
        }
    }

    private void denglu() {
        if (NetUtils.isNetworkAvailable(LoginActivity.this) != 0) {
            prgDialog.show();
            SharePrefUtil.saveString(LoginActivity.this, "user", edittextStuId.getText().toString());
            SharePrefUtil.saveString(LoginActivity.this, "password", edittextPassword.getText().toString());
            String cmd = CommandUtil.loginCMD(edittextStuId.getText().toString().trim(), edittextPassword.getText().toString().trim());
            NetTask.doNet(LoginActivity.this, cmd, new NetTask.ResultTask() {
                public void OnSuccess(int statusCode, String responseString) {
                    LoginDataBeans root = new Gson().fromJson(responseString, LoginDataBeans.class);
                    SharePrefUtil.saveString(LoginActivity.this, "baseInfo", responseString);
                    if (root.getResultCode() == 0) {
                        SharePrefUtil.saveString(LoginActivity.this, "name", root.getBody().getData().getName());
                        SharePrefUtil.saveString(LoginActivity.this, "IdNo", root.getBody().getData().getIdNo());
                        SharePrefUtil.saveString(LoginActivity.this, "MainFare", root.getBody().getData().getMainFare());
                        SharePrefUtil.saveString(LoginActivity.this, "DataResultCode", root.getBody().getData().getResultCode());
                        SharePrefUtil.saveInt(LoginActivity.this, "CardSn", root.getBody().getData().getCardSn());
                        SharePrefUtil.saveString(LoginActivity.this, "Asn", root.getBody().getData().getAsn());
                        SharePrefUtil.saveString(LoginActivity.this, "SessionId", root.getSessionId());
                        SharePrefUtil.saveInt(LoginActivity.this, "RootResultCode", root.getResultCode());
                        SharePrefUtil.saveString(LoginActivity.this, "mobile", root.getUserInfo().getMobile());
                        SharePrefUtil.saveBoolean(LoginActivity.this, "isLogin", true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        prgDialog.dismiss();
                        startActivity(intent);
                    } else if (root.getResultCode() == 2) {
                        prgDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "密码错误！请重试！", Toast.LENGTH_SHORT).show();
                    } else {
                        prgDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "登陆失败！", Toast.LENGTH_SHORT).show();
                    }
                }

                public void onFailure(int statusCode, String responseString) {
                    prgDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "网络请求超时！", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "请先开启网络！", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }
}