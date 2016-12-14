package wdxgz.googleplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wdxgz.googleplay.ui.view.Page;
import wdxgz.googleplay.uitils.MyUtil;

/**
 * Created by Caiwnj on 2016/12/11.
 * 共性的布局：加载失败，正在加载，返回为空.未加载
 */

public abstract class BaseFragment extends Fragment {
    private Page mPage;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPage = new Page(MyUtil.getContext()) {
            public View initSuccessView() {
                return BaseFragment.this.initSuccessView();
            }

            public ResultState initData() {
                return BaseFragment.this.initData();
            }

        };
        return mPage;
    }

    public abstract View initSuccessView();//交给子类的初始化控件的方法

    public abstract Page.ResultState initData();//交给子类的初始化数据的方法，已經放在子線程中，可以直接做

    public void loadData() {
        if (mPage != null) {
            mPage.loadData();
        }
    }
}
