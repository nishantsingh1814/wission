package com.nishant.wission.signup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nishant.wission.R;
import com.nishant.wission.feed.FeedActivity;
import com.nishant.wission.login.LoginActivity;
import com.nishant.wission.preferences.Preferences;

public class SignupActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 123;
    private static final int READ_STORAGE = 100;
    private TextInputEditText mNameEt;
    private TextInputEditText mAgeEt;
    private TextInputEditText mEmailEt;
    private TextInputEditText mPasswordEt;
    private RadioGroup mGenderRg;
    private Button mSignupBtn;
    private String gender;
    private ImageView mProfileIv;
    private Bitmap bitmap;
    private String selectedImagePath;
    private String mProfilePicPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (Preferences.getNameLang(this) != null) {
            if (Preferences.getLoggedInLang(this)) {
                startActivity(new Intent(SignupActivity.this, FeedActivity.class));
                finish();
            } else {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        }
        initializeViews();
        mProfileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(SignupActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(SignupActivity.this,
                            Manifest.permission.READ_CONTACTS)) {

                    } else {

                        ActivityCompat.requestPermissions(SignupActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_STORAGE);


                    }
                } else {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_REQUEST);
                }
            }
        });
        mGenderRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton genderRb = mGenderRg.findViewById(mGenderRg.getCheckedRadioButtonId());
                gender = genderRb.getText().toString();
            }
        });
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameEt.getText().toString().trim();
                String age = mAgeEt.getText().toString().trim();
                String email = mEmailEt.getText().toString().trim();
                String password = mPasswordEt.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    mNameEt.setError("Name can't be empty");
                    return;
                }

                if (TextUtils.isEmpty(age)) {
                    mAgeEt.setError("Age can't be empty");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmailEt.setError("Email can't be empty");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPasswordEt.setError("Password can't be empty");
                    return;
                }
                Preferences.saveAgeLang(SignupActivity.this, age);
                Preferences.saveEmailLang(SignupActivity.this, email);
                Preferences.saveGenderLang(SignupActivity.this, gender);
                Preferences.saveNameLang(SignupActivity.this, name);
                Preferences.savePasswordLang(SignupActivity.this, password);
                Preferences.saveProfilePicLang(SignupActivity.this, selectedImagePath);
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_REQUEST);
                }
                return;
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        bitmap = null;
        selectedImagePath = null;


        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            mProfileIv.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            selectedImagePath = picturePath;
        }


    }

    private void initializeViews() {
        mNameEt = findViewById(R.id.name_et);
        mAgeEt = findViewById(R.id.age_et);
        mEmailEt = findViewById(R.id.email_et);
        mProfileIv = findViewById(R.id.profile_iv);
        mPasswordEt = findViewById(R.id.password_et);
        mSignupBtn = findViewById(R.id.signup_btn);
        mGenderRg = findViewById(R.id.gender_rg);
        gender="Male";
        ((RadioButton) mGenderRg.getChildAt(0)).setChecked(true);
    }

}
