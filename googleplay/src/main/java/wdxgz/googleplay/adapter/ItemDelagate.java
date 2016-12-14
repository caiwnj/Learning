package wdxgz.googleplay.adapter;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import wdxgz.googleplay.R;

/**
 * Created by Caiwnj on 2016/12/13.
 */

public class ItemDelagate implements ItemViewDelegate<String> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item;
    }

    @Override
    public boolean isForViewType(String item, int position) {
        return true;
    }

    public void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.textview, s);
    }
}
