package com.nishant.wission.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.nishant.wission.R;
import com.nishant.wission.feed.FeedActivity;
import com.nishant.wission.preferences.Preferences;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText mEmailEt;
    private TextInputEditText mPasswordEt;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailEt.getText().toString().trim();
                String password = mPasswordEt.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmailEt.setError("Email can't be empty");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPasswordEt.setError("Password can't be empty");
                    return;
                }

                boolean emailMatched = email.equals(Preferences.getEmailLang(LoginActivity.this));
                boolean passwordMatched = password.equals(Preferences.getPasswordLang(LoginActivity.this));

                if (emailMatched && passwordMatched) {
                    startActivity(new Intent(LoginActivity.this, FeedActivity.class));
                    Preferences.saveLoggedInLang(LoginActivity.this, true);
                    finish();
                } else {
                    startActivity(new Intent(LoginActivity.this, FeedActivity.class));
                    Preferences.saveLoggedInLang(LoginActivity.this, true);
                    finish();
                }
            }
        });
    }

    private void initializeViews() {
        mEmailEt = findViewById(R.id.email_et);
        mPasswordEt = findViewById(R.id.password_et);
        mLoginBtn = findViewById(R.id.login_btn);
    }
}
