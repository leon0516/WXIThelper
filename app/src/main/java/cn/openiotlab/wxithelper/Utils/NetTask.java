package cn.openiotlab.wxithelper.Utils;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import java.util.HashMap;

/**
 * Created by Leon on 2015/5/16.
 */
public class NetTask {

    public static void doNet(Context ct, String logincmd, final ResultTask task) {
        String login = null;
        try {
            login = AESForNodejs.encrypt(logincmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpEntity entity = null;
        try {
            entity = new StringEntity(login);

            AsyncHttpClient postClient = new AsyncHttpClient();
            postClient.setTimeout(5000);
            postClient.addHeader("Content-Type", "text/plain");
            postClient.post(ct, "http://112.124.39.140:8888/webservice", entity, "Content-Type:text/plain", new TextHttpResponseHandler() {
                //            postClient.post(ct, "http://192.168.31.246:8888/webservice", entity, "Content-Type:text/plain", new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                    String responseString = null;
                    try {
                        responseString = AESForNodejs.decrypt(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    task.onFailure(statusCode, responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    String responseString = null;
                    try {
                        responseString = AESForNodejs.decrypt(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    task.OnSuccess(statusCode, responseString);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getSliderInfo(final ResultTask task) {
        AsyncHttpClient getClient = new AsyncHttpClient(8888);
        getClient.setTimeout(2000);
        getClient.get("http://112.124.39.140:8888", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                task.onFailure(statusCode,responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                task.OnSuccess(statusCode,responseString);
            }
        });
    }

    public static void getChengJi(Context ct, String logincmd, final ResultTask task) {
        HttpEntity entity = null;
        try {
            entity = new StringEntity(logincmd);

            AsyncHttpClient postClient = new AsyncHttpClient();
            postClient.setTimeout(5000);
            postClient.addHeader("Content-Type", "text/plain");
            postClient.post(ct, "http://112.124.39.140:8888/chengji", entity, "Content-Type:text/plain", new TextHttpResponseHandler() {
                //            postClient.post(ct, "http://192.168.31.246:8888/webservice", entity, "Content-Type:text/plain", new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {

                    task.onFailure(statusCode, response);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {

                    task.OnSuccess(statusCode, response);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void getTuShu(Context ct, String keyWords,int pageNo, final ResultTask task) {
        HashMap postData = new HashMap();
        postData.put("keyWords", keyWords);
        postData.put("pageNo", pageNo);
        String logincmd=new Gson().toJson(postData);
        HttpEntity entity = null;
        try {
            entity = new StringEntity(logincmd,"utf-8");
            AsyncHttpClient postClient = new AsyncHttpClient();
            postClient.setTimeout(5000);
            postClient.addHeader("Content-Type", "text/plain");
            postClient.post(ct, "http://112.124.39.140:8888/tushu", entity, "Content-Type:text/plain", new TextHttpResponseHandler() {
                //            postClient.post(ct, "http://192.168.31.246:8888/webservice", entity, "Content-Type:text/plain", new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {

                    task.onFailure(statusCode, response);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    String a=response.trim().replace("&nbsp;","").replace(" ","").replace("\n","").replace("\t","");
                    task.OnSuccess(statusCode, a);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface ResultTask {
        public void OnSuccess(int statusCode, String responseString);

        public void onFailure(int statusCode, String responseString);
    }


}
