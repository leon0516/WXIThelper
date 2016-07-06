package cn.openiotlab.wxithelper.Utils;

/**
 * Created by Leon on 2015-4-23.
 */
import android.app.Application;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CommandUtil
{
    public static String ChangeMobileCMD(String mobile, String password)
    {
        String str = SharePrefUtil.getString(ContextApplication.getAppContext(), "SessionId", "");
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("mobile_", mobile);
        localHashMap1.put("password", password);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("body", localHashMap1);
        localHashMap2.put("command", "T01005");
        localHashMap2.put("sessionId", str);
        localHashMap2.put("type", "android");
        return new Gson().toJson(localHashMap2);
    }
    public static String chaXunChengJi()
    {
        String name = SharePrefUtil.getString(ContextApplication.getAppContext(), "user", "");
        String password = SharePrefUtil.getString(ContextApplication.getAppContext(), "password", "");
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("UserName", name);
        localHashMap1.put("Password", password);
        return new Gson().toJson(localHashMap1);
    }
    public static String ChangePasswordCMD(String newPwd, String password)
    {
        String str = SharePrefUtil.getString(ContextApplication.getAppContext(), "SessionId", "");
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("newPwd", newPwd);
        localHashMap1.put("password", password);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("body", localHashMap1);
        localHashMap2.put("command", "T01003");
        localHashMap2.put("sessionId", str);
        localHashMap2.put("type", "android");
        return new Gson().toJson(localHashMap2);
    }

    public static String ChangeidNoCMD(String idNo, String password)
    {
        String str = SharePrefUtil.getString(ContextApplication.getAppContext(), "SessionId", "");
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("idNo", idNo);
        localHashMap1.put("password", password);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("body", localHashMap1);
        localHashMap2.put("command", "T01006");
        localHashMap2.put("sessionId", str);
        localHashMap2.put("type", "android");
        return new Gson().toJson(localHashMap2);
    }

    public static String DeclareLoseCMD(String password)
    {
        String str = SharePrefUtil.getString(ContextApplication.getAppContext(), "SessionId", "");
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("password", password);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("body", localHashMap1);
        localHashMap2.put("command", "T01002");
        localHashMap2.put("sessionId", str);
        localHashMap2.put("type", "android");
        return new Gson().toJson(localHashMap2);
    }

    public static String DeclareOtherLoseCMD(String outid_, String password)
    {
        String str = SharePrefUtil.getString(ContextApplication.getAppContext(), "SessionId", "");
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("outid_", outid_);
        localHashMap1.put("password", password);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("body", localHashMap1);
        localHashMap2.put("command", "T01002");
        localHashMap2.put("sessionId", str);
        localHashMap2.put("type", "android");
        return new Gson().toJson(localHashMap2);
    }

    public static String QueryPayHistoryListCMD(String beginDate, String endDate, int beginIndex, int count)
    {
//        String str = ContextApplication.getAppContext().getSharedPreferences("baseInfo", 0).getString("sessionId", null);
        String str = SharePrefUtil.getString(ContextApplication.getAppContext(),"SessionId","");
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("count", Integer.valueOf(count));
        localHashMap1.put("beginIndex", Integer.valueOf(beginIndex));
        localHashMap1.put("endDate", endDate);
        localHashMap1.put("beginDate", beginDate);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("sessionId", str);
        localHashMap2.put("type", "android");
        localHashMap2.put("command", "S01008");
        localHashMap2.put("body", localHashMap1);
        return new Gson().toJson(localHashMap2);
    }

    public static String QueryPayOneMonthMoneyTotalCMD()
    {
        String str = SharePrefUtil.getString(ContextApplication.getAppContext(), "SessionId", "");
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date localDate = new Date();
        String str2 = localSimpleDateFormat.format(localDate);
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(localDate);
        localCalendar.add(Calendar.MONTH, -1);
        String str3 = localSimpleDateFormat.format(localCalendar.getTime());
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("beginDate", str3);
        localHashMap1.put("endDate", str2);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("body", localHashMap1);
        localHashMap2.put("command", "S01011");
        localHashMap2.put("sessionId", str);
        localHashMap2.put("type", "android");
        return new Gson().toJson(localHashMap2);
    }

    public static String QueryRechargeOneMonthMoneyTotalCMD()
    {
        String str1 = SharePrefUtil.getString(ContextApplication.getAppContext(), "SessionId", "");
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date localDate = new Date();
        String str2 = localSimpleDateFormat.format(localDate);
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(localDate);
        localCalendar.add(Calendar.MONTH, -1);
        String str3 = localSimpleDateFormat.format(localCalendar.getTime());
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("beginDate", str3);
        localHashMap1.put("endDate", str2);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("body", localHashMap1);
        localHashMap2.put("command", "S01010");
        localHashMap2.put("sessionId", str1);
        localHashMap2.put("type", "android");
        return new Gson().toJson(localHashMap2);
    }

    public static String QuerySubMoneyCMD()
    {
        String str1 = SharePrefUtil.getString(ContextApplication.getAppContext(), "SessionId", "");
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date localDate = new Date();
        String str2 = localSimpleDateFormat.format(localDate);
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(localDate);
        localCalendar.add(Calendar.MONTH, -1);
        String str3 = localSimpleDateFormat.format(localCalendar.getTime());
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("beginDate", str3);
        localHashMap1.put("endDate", str2);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("body", localHashMap1);
        localHashMap2.put("command", "S01014");
        localHashMap2.put("sessionId", str1);
        localHashMap2.put("type", "android");
        return new Gson().toJson(localHashMap2);
    }

    public static String getBeginData()
    {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date localDate = new Date();
        localSimpleDateFormat.format(localDate);
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(localDate);
        localCalendar.add(Calendar.MONTH, -1);
        return localSimpleDateFormat.format(localCalendar.getTime());
    }

    public static String getEndData()
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String loginCMD(String username, String password)
    {
        HashMap localHashMap = new HashMap();

        localHashMap.put("body", "");

        localHashMap.put("appCode", "ecard");
        localHashMap.put("roleType", Integer.valueOf(0));
        localHashMap.put("command", "login");

        localHashMap.put("username", username);

        localHashMap.put("password", password);

        return new Gson().toJson(localHashMap);
    }
}
