package wdxgz.googleplay.uitils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;

import wdxgz.googleplay.global.GooglePlayApplication;

import static wdxgz.googleplay.global.GooglePlayApplication.*;

/**
 * Created by Caiwnj on 2016/12/11.
 * 封装UI的一些方法
 */

public class MyUtil {
    /////////////////封装一些在Application中定义好的方法//////////////////
    public static Context getContext()

    {
        return GooglePlayApplication.getContext();
    }

    public static Handler getHandler() {
        return GooglePlayApplication.getHandler();
    }

    public static int getMainThreadId() {
        return GooglePlayApplication.getMainThreadId();
    }

    ///////////////////获取资源文件中的一些东西///////////////
    public static String getString(int id) {
        // 获取字符串
        return getContext().getResources().getString(id);
    }

    public static String[] getStringArray(int id) {
        //获取字符串数组
        return getContext().getResources().getStringArray(id);
    }

    public static Drawable getDrawable(int id) {
        //获取图片
        return getContext().getResources().getDrawable(id);
    }

    public static int getColor(int id) {
        //获取颜色
        return getContext().getResources().getColor(id);
    }
    public static int getDimen(int id){
        //获取尺寸
        return getContext().getResources().getDimensionPixelSize(id);
    }
    ///////////尺寸变换/////////
    public  static int dip2px(float dip){
        //dp转换成px
        float density = getContext().getResources().getDisplayMetrics().density;//屏幕密度
        return (int) (dip*density+0.5f);
    }
    public  static  float px2dip(int px){
        //px转换成dp
        float density = getContext().getResources().getDisplayMetrics().density;//屏幕密度
        return px/density;
    }
    //////////和UI有关的其他封装//////
    public  static View inFlate(int id){
        return View.inflate(getContext(),id,null);
    }
    //////判断是否运行在主线程///
    public  static  boolean isRunOnUiThread(){
    //先获取当前线程id
        int currentThreadId = Process.myTid();
        int mainThreadId = getMainThreadId();
        if(currentThreadId==mainThreadId){
            return true;
        }else {
            return false;
        }
    }
    /////方便更新UI的操作///////
    public static void runOnUiThread(Runnable r){
        if (isRunOnUiThread()){
            r.run();
        }else {
            getHandler().post(r);
        }
    }

}
