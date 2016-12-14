package wdxgz.googleplay.holder;

import android.view.View;

/**
 * Created by Caiwnj on 2016/12/11.
 * 和View有关的都要在这里
 * 1.inflate
 * 2.findViewById
 * 3.setTag
 * 4.refreshView
 */

public abstract class MyBaseHolder {

    private final View mRootView;

    public MyBaseHolder() {
        //1,2步
        mRootView = initView();//初始化布局
        //3步
        mRootView.setTag(this);
    }
    public abstract void refreshView();//刷新各个控件的方法
    public abstract View initView();//inflate and findViewById交给子类去进行
    public View getRootView(){
        return mRootView;
    }
}


