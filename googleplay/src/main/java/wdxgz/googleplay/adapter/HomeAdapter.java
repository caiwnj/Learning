package wdxgz.googleplay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Caiwnj on 2016/12/13.
 */

public class HomeAdapter extends MultiItemTypeAdapter<String> {

    public HomeAdapter(Context context, List<String> datas) {
        super(context, datas);
        addItemViewDelegate(new ItemDelagate());
    }
}
