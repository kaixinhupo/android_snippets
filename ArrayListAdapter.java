package cn.lxw.scenedeer.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Lianxw on 2015/7/17.
 * 使BaseAdapter更容易使用
 */
public abstract class ArrayListAdapter<T> extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private int layoutResId;
    private List<T> data;

    public ArrayListAdapter(Context context, int layoutResId) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.layoutResId = layoutResId;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (data != null && position < data.size() && position >= 0) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object viewHolder;
        if (convertView == null) {
            convertView = getInflater().inflate(layoutResId, parent, false);
            viewHolder = createViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder =  convertView.getTag();
        }
        bindView(position, viewHolder);
        return convertView;
    }

    public abstract void bindView(int position, Object viewHolder);

    public abstract Object createViewHolder(View convertView);

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        super.notifyDataSetChanged();
    }

    protected LayoutInflater getInflater() {
        return inflater;
    }

    protected Context getContext() {
        return context;
    }
}
