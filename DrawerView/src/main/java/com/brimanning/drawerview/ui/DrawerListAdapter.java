package com.brimanning.drawerview.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brimanning.drawerview.MainActivity;
import com.brimanning.drawerview.R;

import java.util.List;

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {
    private List<DrawerItem> items;
    private int resource;

    public DrawerListAdapter(Context context, int resource, List<DrawerItem> objects) {
        super(context, resource, objects);

        this.items = objects;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        DrawerItem item = items.get(position);

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }

        ((ImageView)view.findViewById(R.id.drawer_image)).setImageBitmap(item.getImage());
        ((TextView)view.findViewById(R.id.drawer_label)).setText(item.getLabel());

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //I know this could probably be better, but I find it far more understandable than the given drawer listener handling
                ((MainActivity)getContext()).selectSection(position);
            }
        });

        return view;
    }
}
