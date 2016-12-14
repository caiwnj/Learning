package wdxgz.googleplay.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import wdxgz.googleplay.R;
import wdxgz.googleplay.uitils.MyUtil;

/**
 * Created by Caiwnj on 2016/12/11.
 * 根据当前状态来显示页面的情况的自定义界面：未加载，加载中，加载成功，加载失败
 * 请求加载数据的方法
 * 真正要开始显示成功的布局应该在界面被选中的时候，而不是在这个类被创建的时候就要调用
 */

public abstract class Page extends FrameLayout {
    //代表状态的标示：
    public static final int STATE_LOAD_UNDO = 1;
    public static final int STATE_LOAD_DOING = 2;
    public static final int STATE_LOAD_ERRO = 3;
    public static final int STATE_LOAD_EMPTY = 4;
    public static final int STATE_LOAD_SUCCESS = 5;
    //当前状态
    private int mCurrentState = STATE_LOAD_UNDO;
    private View page_loading;
    private View page_erro;
    private View page_empty;
    private View page_success;
    private View view;

    public Page(Context context) {
        super(context);
        view = initView();
    }

    public Page(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = initView();
    }

    public Page(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = initView();
    }

    protected View initView() {
        //初始化布局
        //首先初始化加载中的布局
        if (page_loading == null) {
            page_loading = MyUtil.inFlate(R.layout.page_loading);
            addView(page_loading);
        }
        if (page_erro == null) {
            page_erro = MyUtil.inFlate(R.layout.page_erro);
            addView(page_erro);
        }
        if (page_empty == null) {
            page_empty = MyUtil.inFlate(R.layout.page_empty);
            addView(page_empty);
        }
        showRightView(STATE_LOAD_UNDO);//初始化第一次的布局：使布局呈现加载状态
        return this;
    }


    public void showRightView(int state) {
        if (state == STATE_LOAD_UNDO || state == STATE_LOAD_DOING) {//展示加载中的布局
            page_erro.setVisibility(View.GONE);
            page_empty.setVisibility(View.GONE);
            page_loading.setVisibility(View.VISIBLE);
        }
        if (state == STATE_LOAD_ERRO) {
            page_empty.setVisibility(View.GONE);
            page_erro.setVisibility(View.VISIBLE);
            page_loading.setVisibility(View.GONE);
        }
        if (state == STATE_LOAD_EMPTY) {
            page_empty.setVisibility(View.VISIBLE);
            page_erro.setVisibility(View.GONE);
            page_loading.setVisibility(View.GONE);
        }
        if (state == STATE_LOAD_SUCCESS && page_success==null) {
            page_empty.setVisibility(View.GONE);
            page_erro.setVisibility(View.GONE);
            page_loading.setVisibility(View.GONE);
            page_success = initSuccessView();
            if (page_success != null) {
                addView(page_success);
            }
        }

        mCurrentState = state;
    }

    public abstract View initSuccessView();//加载成功后的布局

    public int getCurrentState() {
        return mCurrentState;
    }

    public void loadData() {//暴露给外界的界面被选中时应该被调用的加载数据和更新界面的方法
        if (mCurrentState != Page.STATE_LOAD_DOING) {//当当前状态不是加载状态的时候，才开始加载（已经加载不需要加载）
            mCurrentState = STATE_LOAD_DOING;
            new Thread() {
                private int state;

                public void run() {
                    super.run();
                    ResultState resultState = initData();
                    if (resultState != null) {
                        state = resultState.getState();
                        MyUtil.runOnUiThread(new Runnable() {
                            public void run() {
                                showRightView(state);
                            }
                        });
                    }
                }
            }.start();

        }

    }


    /////枚举类型
    public enum ResultState {
        STATE_SUCCESS(Page.STATE_LOAD_SUCCESS),
        STATE_EMPTY(Page.STATE_LOAD_EMPTY),
        STATE_ERRO(Page.STATE_LOAD_ERRO);
        private int state;

        public int getState() {
            return state;
        }

        private ResultState(int state) {
            this.state = state;
        }
    }

    public abstract ResultState initData();

    public View getPageView() {
        return view;
    }
}
