package wdxgz.googleplay.ui.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by Caiwnj on 2016/12/11.
 */

public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> fragmentHashMap = new HashMap<Integer, BaseFragment>();

    public static BaseFragment getFragment(int pos) {
        //做一个内存缓存，提高效率
        BaseFragment fragment = fragmentHashMap.get(pos);
        if (fragment == null) {
            switch (pos) {
                case 0://首页
                    fragment = new HomeFragment();

                    break;
                case 1://应用
                    fragment = new AppFragment();
                    break;
                case 2://游戏
                    fragment = new GameFragment();
                    break;
                case 3://专题
                    fragment = new SubjectFragment();
                    break;
                case 4://推荐
                    fragment = new RecommendFragment();
                    break;
                case 5://分类
                    fragment = new CategoryFragment();
                    break;
                case 6://排行
                    fragment = new HotFragment();
                    break;
                default:
                    break;

            }
            fragmentHashMap.put(pos,fragment);
        }
        return  fragment;

    }
}
