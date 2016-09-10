package com.app.doctorsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.doctorsapp.R;
import com.app.doctorsapp.adapters.DiagnosisAdapter;
import com.app.doctorsapp.models.DiagnosisModel;
import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.FadeEffect;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    @Bind(R.id.main_list)
    JazzyListView diagnosisListView;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Bind(R.id.empty_view)
    TextView textViewEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        //Setting empty list view and transition effect
        diagnosisListView.setEmptyView(textViewEmptyView);
        diagnosisListView.setTransitionEffect(new FadeEffect());

        //populating already done diagnosis from the database
        RealmResults<DiagnosisModel> results = realm.where(DiagnosisModel.class).findAll();
        DiagnosisAdapter diagnosisAdapter = new DiagnosisAdapter(this, results);
        diagnosisListView.setAdapter(diagnosisAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DiagnosisActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_all) {
            //Delete all the diagnosis from database
            DiagnosisModel.deleteAllDiagnosis(realm);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
