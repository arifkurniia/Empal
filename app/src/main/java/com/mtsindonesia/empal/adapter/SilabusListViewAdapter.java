package com.mtsindonesia.empal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mtsindonesia.empal.R;

import java.util.ArrayList;

public class SilabusListViewAdapter extends ArrayAdapter<String> {
    int groupid;
    String[] item_list;
    ArrayList<String> desc;
    Context context;
    public SilabusListViewAdapter(Context context, int vg, int id, String[] item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;

    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView textview;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // Inflate the list_item.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textview= (TextView) rowView.findViewById(R.id.txtList);
            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.textview.setText(item_list[position]);
        return rowView;
    }
}
