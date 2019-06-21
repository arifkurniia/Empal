package com.mtsindonesia.empal.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtsindonesia.empal.R;
import com.mtsindonesia.empal.model.Schedule;

import java.util.ArrayList;

public class ScheduleDialogAdapter extends BaseAdapter {

    private Activity context;
    private ArrayList<Schedule> scheduleArrayList;

    public ScheduleDialogAdapter(Activity context, ArrayList<Schedule> scheduleArrayList){
        this.context = context;
        this.scheduleArrayList = scheduleArrayList;
    }

    @Override
    public int getCount(){
        return scheduleArrayList.size();
    }

    public Object getItem(int position){
        return scheduleArrayList.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View listViewItem = layoutInflater.inflate(R.layout.schedule_row_adapter, null, true);

        TextView tvCompetencyRegisterName=(TextView)listViewItem.findViewById(R.id.tv_competencyRegisterName);
        TextView tvFacilitator=(TextView)listViewItem.findViewById(R.id.tv_facilitator);
        TextView tvScheduleType=(TextView)listViewItem.findViewById(R.id.tv_scheduleType);


        tvCompetencyRegisterName.setText("Competency Register : "+scheduleArrayList.get(position).getCompetencyRegisterName());
        tvFacilitator.setText("Facilitator : "+scheduleArrayList.get(position).getFacilitator());
        tvScheduleType.setText(scheduleArrayList.get(position).getScheduleType());

        return  listViewItem;
    }
}
