package com.example.singold.MyActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.singold.R;
import com.example.singold.data.MatchingSurvey;
import com.example.singold.data.PatientDetails;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button playList;
    private Button newMatching;
    private PatientDetails patientDetails;
    private TextView tvname;
    private Button Questionaire;
    private ImageButton btnMatch;
    private ImageButton btnMusic;
    private ImageButton btnQuest;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        tvname= (TextView) findViewById(R.id.tvname);

         btnMusic = (ImageButton) findViewById(R.id.btnMusic);
         btnMatch = (ImageButton) findViewById(R.id.newMatching);
         btnQuest = (ImageButton) findViewById(R.id.Questionaire);
        Intent i=getIntent();
        if(i!=null)
        {
            patientDetails = (PatientDetails) i.getExtras().get("patient");
            tvname.setText(patientDetails.getfName());

        }

        btnMusic.setOnClickListener(this);
        btnMatch.setOnClickListener(this);
        btnQuest.setOnClickListener(this);

    }
    public void onClick(View v)
    {
        if(v==btnMusic)
        {
            Intent intent = new Intent(getBaseContext(), PlaylistActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);
        }
        if(v==btnMatch)
        {
            Intent intent = new Intent(getBaseContext(), MatchingSurveyActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);
        }
        if(v==btnQuest)
        {
            Intent intent = new Intent(getBaseContext(), QuestionaireActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnItmLogOutOut)
        {
            SharedPreferences preferences=getSharedPreferences("myfile",MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent=new Intent(getBaseContext(),LogInActivity.class);
            startActivity(intent);
            finish();


        }
        return true;
    }
}
