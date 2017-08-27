package com.shopspreeng.sardart.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopspreeng.sardart.R;

/**
 * Created by jayson surface on 25/08/2017.
 */

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] text;
    private final int[] Imageid;

    public GridAdapter(Context mContext, String[] text, int[] imageid) {
        this.mContext = mContext;
        this.text = text;
        Imageid = imageid;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.activity_listview, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(text[i]);
            imageView.setImageResource(Imageid[i]);

        } else {
            grid = (View) view;
        }

        return grid;
    }
}
