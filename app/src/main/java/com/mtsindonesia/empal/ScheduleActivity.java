package com.mtsindonesia.empal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mtsindonesia.empal.adapter.CalendarAdapter;
import com.mtsindonesia.empal.model.Schedule;
import com.mtsindonesia.empal.remote.ServiceGenerator;
import com.mtsindonesia.empal.session.Save;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {

    private static final String TAG = "ScheduleActivity";
    public GregorianCalendar calendar_month, calendar_month_clone;
    private CalendarAdapter calendarAdapter;
    private TextView tv_month;
    String username;
    private ServiceGenerator serviceGenerator = ServiceGenerator.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        username = String.valueOf(Save.Read(getApplicationContext(),"username",null));
        ScheduleCollection.scheduleCollectionArrayList = new ArrayList<ScheduleCollection>();
//        ScheduleCollection.scheduleCollectionArrayList.add( new ScheduleCollection("SERP" ,"Training","Agus Supriadi","C000000001", "2019-06-30"));

        Call<List<Schedule>> call = serviceGenerator.getApi().getSchedule(username);
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                Log.e(TAG, "log: -----------------------------");
                Log.d(TAG, "onResponse: " + response.body());

                if(response.raw().networkResponse() != null){
                    Log.d(TAG, "onResponse: response is from NETWORK...");
                }
                else if(response.raw().cacheResponse() != null
                        && response.raw().networkResponse() == null){
                    Log.d(TAG, "onResponse: response is from CACHE...");
                }

                if(response.isSuccessful()){
                    final List<Schedule> scheduleList = response.body();
                    for (int i = 0; i < scheduleList.size(); i++ ){
                        try {
                            Date startDate = formatter.parse(scheduleList.get(i).getStart());
                            Date endDate = formatter.parse(scheduleList.get(i).getEnd());

                            Calendar start = Calendar.getInstance();
                            start.setTime(startDate);

                            Calendar end = Calendar.getInstance();
                            end.setTime(endDate);

                            while(!start.after(end)){
                                ScheduleCollection.scheduleCollectionArrayList.add(new ScheduleCollection(scheduleList.get(i).getCompetencyRegisterName(),scheduleList.get(i).getScheduleType(), scheduleList.get(i).getFacilitator(), scheduleList.get(i).getClassId(), formatter.format(start.getTime())));
                                start.add(Calendar.DATE,1);
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    refreshCalendar();
                }
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                Toast.makeText(ScheduleActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        calendar_month = (GregorianCalendar) GregorianCalendar.getInstance();
        calendar_month_clone = (GregorianCalendar) calendar_month.clone();
        calendarAdapter = new CalendarAdapter(this, calendar_month, ScheduleCollection.scheduleCollectionArrayList);

        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", calendar_month));

        ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });
        ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();
            }
        });
        GridView gridview = (GridView) findViewById(R.id.gv_calendar);
        gridview.setAdapter(calendarAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedGridDate = CalendarAdapter.day_string.get(position);
                ((CalendarAdapter) parent.getAdapter()).getPositionList(selectedGridDate, ScheduleActivity.this);
            }

        });
    }

    protected void setNextMonth() {
        if (calendar_month.get(GregorianCalendar.MONTH) == calendar_month.getActualMaximum(GregorianCalendar.MONTH)) {
            calendar_month.set((calendar_month.get(GregorianCalendar.YEAR) + 1), calendar_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            calendar_month.set(GregorianCalendar.MONTH,
            calendar_month.get(GregorianCalendar.MONTH) + 1);
        }
    }

    protected void setPreviousMonth() {
        if (calendar_month.get(GregorianCalendar.MONTH) == calendar_month.getActualMinimum(GregorianCalendar.MONTH)) {
            calendar_month.set((calendar_month.get(GregorianCalendar.YEAR) - 1), calendar_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            calendar_month.set(GregorianCalendar.MONTH,
            calendar_month.get(GregorianCalendar.MONTH) - 1);
        }
    }

    public void refreshCalendar() {
        calendarAdapter.refreshDays();
        calendarAdapter.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", calendar_month));
    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
