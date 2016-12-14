package wdxgz.googleplay.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by Caiwnj on 2016/12/11.
 * 进行一些全局的初始化。
 * 注意在配置完成之后要在Manifest中设置
 */

public class GooglePlayApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = Process.myTid();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }


}
