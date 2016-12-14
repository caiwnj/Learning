package wdxgz.googleplay.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import wdxgz.googleplay.R;
import wdxgz.googleplay.ui.fragment.BaseFragment;
import wdxgz.googleplay.ui.fragment.FragmentFactory;
import wdxgz.googleplay.uitils.MyUtil;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] mTabNames;
    private Toolbar toolBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabNames = MyUtil.getStringArray(R.array.tab_names);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                FragmentFactory.getFragment(position).loadData();
            }

            public void onPageSelected(int position) {

            }

            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            //得到页面的Fragment
            BaseFragment fragment = FragmentFactory.getFragment(position);
            return fragment;
        }

        public int getCount() {
            //得到数量
            return mTabNames.length;
        }

        public CharSequence getPageTitle(int position) {
            //设置tab的标题
            return mTabNames[position];
        }
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}
