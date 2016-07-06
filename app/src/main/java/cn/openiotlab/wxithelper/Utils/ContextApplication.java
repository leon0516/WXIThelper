package cn.openiotlab.wxithelper.Utils;

/**
 * Created by Leon on 2015-4-23.
 */
import android.app.Application;
import android.content.Context;

public class ContextApplication extends Application
{
    private static Context context;

    public static Context getAppContext()
    {
        return context;
    }

    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }
}
