package wdxgz.googleplay.ui.fragment;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;

import wdxgz.googleplay.R;
import wdxgz.googleplay.adapter.HomeAdapter;
import wdxgz.googleplay.ui.activity.MainActivity;
import wdxgz.googleplay.ui.view.Page;
import wdxgz.googleplay.uitils.MyUtil;

/**
 * Created by Caiwnj on 2016/12/11.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<String> data;
    private ArrayList<String> data2;
    private View fragment_home;
    private RecyclerView list;
    private SwipeRefreshLayout srfLayout;

    public View initSuccessView() {//加载成功后，会调用这个方法
        fragment_home = MyUtil.inFlate(R.layout.fragment_home);
        list = (RecyclerView) fragment_home.findViewById(R.id.list);
        srfLayout = (SwipeRefreshLayout) fragment_home.findViewById(R.id.refresh);
        srfLayout.setOnRefreshListener(this);
        srfLayout.post(new Runnable() {
            @Override
            public void run() {
                srfLayout.setRefreshing(true);
                srfLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srfLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        list.setLayoutManager(new LinearLayoutManager(MyUtil.getContext()));
        HomeAdapter homeAdapter = new HomeAdapter(MyUtil.getContext(), data);

        final LoadMoreWrapper<String> loadMoreWrapper = new LoadMoreWrapper<>(homeAdapter);
        loadMoreWrapper.setLoadMoreView(R.layout.view_footfootview);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                MyUtil.getHandler().postDelayed(new Runnable() {
                    public void run() {
                        data2 = new ArrayList<String>();
                        for (int i = 0; i < 20; i++) {
                            data2.add("String" + i);
                        }
                        data.addAll(data2);
                        loadMoreWrapper.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });
        list.setAdapter(loadMoreWrapper);
        return fragment_home;
    }

    public Page.ResultState initData() {//已经封装在子线程中,获取数据的方法
        if (data == null) {
            data = new ArrayList<String>();
        }
        for (int i = 0; i < 20; i++) {
            data.add("String" + i);
        }
        return Page.ResultState.STATE_SUCCESS;
    }

    public void onRefresh() {
        //下拉刷新的方法
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                srfLayout.setRefreshing(false);
            }
        }, 1000); // 5秒后发送消息，停止刷新
    }


}
