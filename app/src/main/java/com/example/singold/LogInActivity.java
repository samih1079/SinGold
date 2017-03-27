package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.singold.data.ConnectToServer;
import com.example.singold.data.MyUser;
import com.example.singold.data.Song;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class LogInActivity extends AppCompatActivity
{
    private Button login,sign;
    private EditText username,id;
    private  e;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        login=(Button)findViewById(R.id.login);
        sign=(Button)findViewById(R.id.sign);
        username=(EditText)findViewById(R.id.username);
        id=(EditText)findViewById(R.id.id1);
    }
    private void dataHandler()
    {
        String stUsername = username.getText().toString();
        String stId = id.getText().toString();

        boolean isok=true;
        if(stUsername.length()<3)
        {
            username.setError("Wrong username");
            isok=false;

        }
        if(stId.length()<=3)
        {
            id.setError("Wrong id");
            isok=false;
        }
        if (isok==true)
        {
            MyUser myUser=new MyUser();
            myUser.setUsername(stUsername);
            myUser.setId(stId);
            ConnectToServer.connet(this);
            ConnectToServer.addInTable(MyUser);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    public void onClick(View v)
    {
        if(v==login)
        {
            Intent intent=new Intent(getBaseContext(),PatientListActivity.class);
            startActivity(intent);

        }

        if (v==sign)
        {
            Intent intent=new Intent(getBaseContext(),SignUpActivity.class);
            startActivity(intent);
        }

    }



}
