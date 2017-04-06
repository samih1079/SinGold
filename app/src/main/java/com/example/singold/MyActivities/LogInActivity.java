package com.example.singold.MyActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;

public class LogInActivity extends AppCompatActivity
{
    private Button login,sign;
    private EditText username,id;
    private CheckBox checkBox;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        login=(Button)findViewById(R.id.login);
        sign=(Button)findViewById(R.id.sign);
        username=(EditText)findViewById(R.id.username);
        id=(EditText)findViewById(R.id.id1);
        checkBox=(CheckBox) findViewById(R.id.checkBox);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void dataHandler() {
        String stUsername = username.getText().toString();
        String stId = id.getText().toString();

        boolean isok = true;

        if (stUsername.length() < 3) {
            username.setError("Wrong username");
            isok = false;

        }
        if (stId.length() <= 3) {
            id.setError("Wrong id");
            isok = false;
        }
        if (isok == true) {
            ConnectToServer.connect(this);
            ConnectToServer.login(stUsername,stId,(ProgressBar)findViewById(R.id.progressBar),checkBox.isChecked());

        }
    }
    public void onClick(View v)
    {
        if(v==login)
        {
//            Intent intent=new Intent(getBaseContext(),PatientListActivity.class);
//            startActivity(intent);
                dataHandler();
        }

        if (v==sign)
        {
            Intent intent=new Intent(getBaseContext(),SignUpActivity.class);
            startActivity(intent);
        }

    }

}
