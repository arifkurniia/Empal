package com.mtsindonesia.empal;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mtsindonesia.empal.adapter.SilabusListViewAdapter;
import com.mtsindonesia.empal.model.Silabus;
import com.mtsindonesia.empal.remote.ServiceGenerator;
import com.mtsindonesia.empal.session.Save;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.DOWNLOAD_SERVICE;

public class SilabusTrainerFragment extends Fragment {

    private static final String TAG = "SilabusTrainer";
    View view;
    ListView listView;
    String competencyRegisterId;
    String username;
    String flag = "Trainer";
    private ServiceGenerator serviceGenerator = ServiceGenerator.getInstance();
    private String[] silabusName;
    private String[] fileUrl;
    private long downloadID;

    public SilabusTrainerFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstance){
        view = inflater.inflate(R.layout.silabus_trainer_layout, viewGroup, false);

        listView = (ListView)view.findViewById(R.id.lstSilabus);

        competencyRegisterId = String.valueOf(Save.Read(getContext(),"competencyRegisterId",null));
        username = String.valueOf(Save.Read(getContext(),"username",null));

        Call<List<Silabus>> call = serviceGenerator.getApi().getStudentSilabus(username, competencyRegisterId, flag);
        call.enqueue(new Callback<List<Silabus>>() {
            @Override
            public void onResponse(Call<List<Silabus>> call, Response<List<Silabus>> response) {
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
                    final List<Silabus> silabusList = response.body();

                    silabusName = new String[silabusList.size()];
                    fileUrl = new String[silabusList.size()];

                    for (int i = 0; i < silabusList.size(); i ++){
                        silabusName[i] = silabusList.get(i).getSilabusName();
                        fileUrl[i] = silabusList.get(i).getFileUrl();
                    }

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(silabusList.get(position).getFileUrl()))
                                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI)
                                    .setTitle(silabusList.get(position).getSilabusName())
                                    .setDescription("Downloading")
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                            DownloadManager downloadManager= (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
                            downloadID = downloadManager.enqueue(request);// enqueue puts the download request in the queue.

                        }
                    });

                    SilabusListViewAdapter adapter = new SilabusListViewAdapter(getContext(), R.layout.silabus_list_item, R.id.txtList, silabusName);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Silabus>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(getContext(), "Download Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(onDownloadComplete);

    }
}
