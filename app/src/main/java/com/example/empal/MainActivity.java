package com.example.empal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empal.model.ResObj;
import com.example.empal.remote.ServiceGenerator;
import com.example.empal.session.Save;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView usernameId;
    Boolean session;
    ImageView btnProfile;
    ImageView btnSilabus;
    ImageView btnSchedule;
    ImageView btnAssignment;
    ImageView btnTranscript;
    ImageView btnLogout;
    String username;
    String name;
    private ServiceGenerator serviceGenerator = ServiceGenerator.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(instance == null){
            instance = this;
        }

        usernameId = (TextView) findViewById(R.id.usernameId);
        btnLogout = findViewById(R.id.btnLogout);
        btnProfile = findViewById(R.id.btnProfile);

        SESSION();

        username = String.valueOf(Save.Read(getApplicationContext(),"username",null));
        name = String.valueOf(Save.Read(getApplicationContext(),"name",null));
        usernameId.setText(name);

        Call<ResObj> call = serviceGenerator.getApi().getImage(username);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
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
                    ResObj resObj = response.body();
                    new DownloadImageTask((ImageView) findViewById(R.id.imgProfile))
                            .execute(resObj.getMessage());
                    Save.Save(getApplicationContext(),"imgProfileUrl", resObj.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "You are logged out.", Toast.LENGTH_SHORT).show();
                Save.Save(getApplicationContext(),"session", "false");
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
                finish();
                Save.Save(getApplicationContext(),"username", username);
            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public void SESSION(){
        session = Boolean.valueOf(Save.Read(getApplicationContext(), "session", "false"));
        if(!session){
            Intent firstlogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(firstlogin);
            finish();
        }
        else {
        }
    }

    private static MainActivity instance;

    public static MainActivity getInstance(){
        return instance;
    }

    public static boolean hasNetwork(){
        return instance.isNetworkConnected();
    }

    private boolean isNetworkConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
