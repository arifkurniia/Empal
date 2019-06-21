package com.mtsindonesia.empal;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mtsindonesia.empal.adapter.SilabusTabsAdapter;
import com.mtsindonesia.empal.model.CompetencyBook;
import com.mtsindonesia.empal.model.CompetencyGroup;
import com.mtsindonesia.empal.model.CompetencyRegister;
import com.mtsindonesia.empal.remote.ServiceGenerator;
import com.mtsindonesia.empal.session.Save;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SilabusActivity extends AppCompatActivity {

    private static final String TAG = "SilabusActivity";
    Spinner dropdownCompetencyBook;
    Spinner dropdownCompetencyGroup;
    Spinner dropdownCompetencyRegister;
    ProgressBar progressBar;
    String username;
    private ServiceGenerator serviceGenerator = ServiceGenerator.getInstance();
    private TabLayout tabSilabus;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silabus);

        dropdownCompetencyBook = (Spinner) findViewById(R.id.spinnerCompetencyBook);
        dropdownCompetencyGroup = (Spinner) findViewById(R.id.spinnerCompetencyGroup);
        dropdownCompetencyRegister = (Spinner) findViewById(R.id.spinnerCompetencyRegister);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tabSilabus = (TabLayout) findViewById(R.id.tabSilabus);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        dropdownCompetencyGroup.setEnabled(false);
        dropdownCompetencyRegister.setEnabled(false);

        username = String.valueOf(Save.Read(getApplicationContext(),"username",null));

        progressBar.setVisibility(View.VISIBLE);

        Call<List<CompetencyBook>> call = serviceGenerator.getApi().getCompetencyBookSilabus(username);
        call.enqueue(new Callback<List<CompetencyBook>>() {
            @Override
            public void onResponse(Call<List<CompetencyBook>> call, Response<List<CompetencyBook>> response) {
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
                    progressBar.setVisibility(View.GONE);
                    final List<CompetencyBook> competencyBookList = response.body();
                    List<String> listSpinnerCompetencyBook = new ArrayList<>();
                    for(int i = 0; i < competencyBookList.size(); i++){
                        listSpinnerCompetencyBook.add(competencyBookList.get(i).getCompetencyBookName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listSpinnerCompetencyBook);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dropdownCompetencyBook.setAdapter(adapter);

                    dropdownCompetencyBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            progressBar.setVisibility(View.VISIBLE);
                            ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER_VERTICAL);
                            String selectedCompetencyBookId = adapterView.getItemAtPosition(i).toString();
                            String competencyBookId = competencyBookList.get(i).getCompetencyBookId();
                            Log.d("CompetencyBookId", competencyBookId);

                            if (selectedCompetencyBookId != null){
                                dropdownCompetencyGroup.setEnabled(true);
                                loadCompetencyGroup(competencyBookId);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<CompetencyBook>> call, Throwable t) {
                Toast.makeText(SilabusActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCompetencyGroup(String competencyBookId){
        Call<List<CompetencyGroup>> call = serviceGenerator.getApi().getCompetencyGroupSilabus(username, competencyBookId);
        call.enqueue(new Callback<List<CompetencyGroup>>() {
            @Override
            public void onResponse(Call<List<CompetencyGroup>> call, Response<List<CompetencyGroup>> response) {
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
                    progressBar.setVisibility(View.GONE);
                    final List<CompetencyGroup> competencyGroupList = response.body();
                    List<String> listSpinnerCompetencyGroup = new ArrayList<>();
                    for(int i = 0; i < competencyGroupList.size(); i++){
                        listSpinnerCompetencyGroup.add(competencyGroupList.get(i).getCompetencyGroupName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listSpinnerCompetencyGroup);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dropdownCompetencyGroup.setAdapter(adapter);

                    dropdownCompetencyGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            progressBar.setVisibility(View.VISIBLE);
                            ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER_VERTICAL);
                            String selectedCompetencyGroupId = adapterView.getItemAtPosition(i).toString();
                            String competencyGroupId = competencyGroupList.get(i).getCompetencyGroupId();
                            Log.d("CompetencyGroupId", competencyGroupId);

                            if (selectedCompetencyGroupId != null){
                                dropdownCompetencyRegister.setEnabled(true);
                                loadCompetencyRegister(competencyGroupId);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<CompetencyGroup>> call, Throwable t) {
                Toast.makeText(SilabusActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCompetencyRegister(String competencyGroupId){
        Call<List<CompetencyRegister>> call = serviceGenerator.getApi().getCompetencyRegisterSilabus(username, competencyGroupId);
        call.enqueue(new Callback<List<CompetencyRegister>>() {
            @Override
            public void onResponse(Call<List<CompetencyRegister>> call, Response<List<CompetencyRegister>> response) {
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
                    progressBar.setVisibility(View.GONE);
                    final List<CompetencyRegister> competencyRegisterList = response.body();
                    List<String> listSpinnerCompetencyRegister = new ArrayList<>();
                    for(int i = 0; i < competencyRegisterList.size(); i++){
                        listSpinnerCompetencyRegister.add(competencyRegisterList.get(i).getCompetencyRegisterName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listSpinnerCompetencyRegister);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dropdownCompetencyRegister.setAdapter(adapter);

                    dropdownCompetencyRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            progressBar.setVisibility(View.VISIBLE);
                            ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER_VERTICAL);
                            String selectedCompetencyRegisterId = adapterView.getItemAtPosition(i).toString();
                            String competencyRegisterId = competencyRegisterList.get(i).getCompetencyRegisterId();
                            Log.d("CompetencyRegisterId", competencyRegisterId);

                            if (selectedCompetencyRegisterId != null){
                                progressBar.setVisibility(View.GONE);
                                Save.Save(getApplicationContext(),"competencyRegisterId", competencyRegisterId);

                                SilabusTabsAdapter adapter = new SilabusTabsAdapter(getSupportFragmentManager());

                                //Add Fragment
                                adapter.AddFragment(new SilabusTrainerFragment(), "Silabus");
                                adapter.AddFragment(new CoachingGuidelineFragment(), "Coaching Guideline");
                                adapter.AddFragment(new MentoringGuidelineFragment(), "Mentoring Guideline");

                                //adapter Setup
                                viewPager.setAdapter(adapter);
                                tabSilabus.setupWithViewPager(viewPager);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<CompetencyRegister>> call, Throwable t) {
                Toast.makeText(SilabusActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
