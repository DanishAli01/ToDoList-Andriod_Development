package com.example.danishali.assignment01;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter extends BaseAdapter {


    CustomArrayAdapter(Context c, ArrayList<CustomItem> al) {
        context = c;
        al_items = al;
    }

    public View getView(int position, View convert_view, ViewGroup parent) {

        ViewHolder holder;

        if (convert_view == null) {
            holder = new ViewHolder();
            LayoutInflater inflator = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            assert inflator != null;
            convert_view = inflator.inflate(R.layout.custom_item_layout, parent, false);

            holder.tv_name = (TextView) convert_view.findViewById(R.id.tv_name);
            holder.tv_date = (TextView) convert_view.findViewById(R.id.tv_date);
            holder.iv_image = (ImageView) convert_view.findViewById(R.id.iv_image);
            holder.title_custom = (TextView)convert_view.findViewById(R.id.title_custom);

            convert_view.setTag(holder);

        }

        else {
            holder =	(ViewHolder)	convert_view.getTag();
        }

        holder.iv_image.setImageResource(R.drawable.ic_edit_black_24dp);
        holder.tv_name.setText(al_items.get(position).getName());
        holder.tv_date.setText(al_items.get(position).getDate());
        holder.title_custom.setText(al_items.get(position).getTitle());


        return convert_view;
    }

    public int getCount() {
        return al_items.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return al_items.get(position);
    }

    private Context context;
    private ArrayList<CustomItem> al_items;


    static class ViewHolder {
        public TextView tv_name;
        public TextView tv_date;
        public ImageView iv_image;
        public TextView title_custom;

    }


}


