package com.example.empal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empal.model.Student;
import com.example.empal.remote.ApiUtils;
import com.example.empal.remote.UserService;
import com.example.empal.session.Save;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtCompany;
    TextView txtGender;
    TextView txtDateofBirth;
    TextView txtContactNumber;
    TextView txtEmail;
    TextView txtNIP;
    TextView txtFunctionProfile;
    String username;
    String imgProfileUrl;
    UserService userService;
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        txtName = (TextView) findViewById(R.id.txtName);
        txtCompany = (TextView) findViewById(R.id.txtCompany);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtDateofBirth = (TextView) findViewById(R.id.txtDateofBirth);
        txtContactNumber = (TextView) findViewById(R.id.txtContactNumber);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtNIP = (TextView) findViewById(R.id.txtNIP);
        txtFunctionProfile = (TextView) findViewById(R.id.txtFunctionProfile);
        final android.text.format.DateFormat df = new android.text.format.DateFormat();
        userService = ApiUtils.getUserService();

        username = String.valueOf(Save.Read(getApplicationContext(),"username",null));
        imgProfileUrl = String.valueOf(Save.Read(getApplicationContext(),"imgProfileUrl",null));

        Call<Student> call = userService.getUserProfile(username);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful()){
                    Student students = response.body();
                    new ProfileActivity.DownloadImageTask((ImageView) findViewById(R.id.imgProfile))
                            .execute(imgProfileUrl);
                    txtName.setText(students.getName());
                    txtCompany.setText(students.getCompany());
                    txtGender.setText(students.getGender());
                    txtDateofBirth.setText(students.getStringDateOfBirth());
                    txtContactNumber.setText(students.getContactNumber());
                    txtEmail.setText(students.getEmail());
                    txtNIP.setText(students.getNIP());
                    txtFunctionProfile.setText(students.getFunctionProfile());
                }else {
                    Toast.makeText(getApplicationContext(), "Something wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Student>call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
