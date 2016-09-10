package com.app.doctorsapp.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.app.doctorsapp.R;
import com.app.doctorsapp.models.DiagnosisModel;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by faisal on 9/10/16.
 */
public class DiagnosisActivity extends AppCompatActivity {

    Realm realm;
    Resources resources;

    @Bind(R.id.migrane_test)
    CheckBox checkBoxMigrane;

    @Bind(R.id.age_test)
    CheckBox checkBoxAge;

    @Bind(R.id.sex_test)
    CheckBox checkBoxSex;

    @Bind(R.id.drugs_test)
    CheckBox checkBoxDrug;

    @Bind(R.id.diagnosis_result)
    TextView textViewResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        ButterKnife.bind(this);
        resources = getResources();
        realm = Realm.getDefaultInstance();

    }


    //On click method when calculate button is clicked. This method calculates results and stores in database
    @OnClick(R.id.calculate_result)
    public void submit(View view) {
        // TODO submit data to server...
        int intWeight = 0;
        String strResult = "";
        if (checkBoxAge.isChecked()) {
            intWeight++;
        }
        if (checkBoxMigrane.isChecked()) {
            intWeight++;
        }
        if (checkBoxDrug.isChecked()) {
            intWeight++;
        }
        if (checkBoxSex.isChecked()) {
            intWeight++;
        }

        if (intWeight == 0)
            strResult = resources.getString(R.string.diagnosis_test_result_0_percent);
        if (intWeight == 1)
            strResult = resources.getString(R.string.diagnosis_test_result_25_percent);
        if (intWeight == 2)
            strResult = resources.getString(R.string.diagnosis_test_result_50_percent);
        if (intWeight == 3)
            strResult = resources.getString(R.string.diagnosis_test_result_75_percent);
        if (intWeight == 4)
            strResult = resources.getString(R.string.diagnosis_test_result_100_percent);

        YoYo.with(Techniques.StandUp)
                .duration(700)
                .playOn(textViewResults);

        textViewResults.setText(strResult);

        //Save the diagnosis in database
        DiagnosisModel diagnosisModel = new DiagnosisModel();
        diagnosisModel.setSexTest(checkBoxSex.isChecked());
        diagnosisModel.setDrugTest(checkBoxDrug.isChecked());
        diagnosisModel.setAgeTest(checkBoxAge.isChecked());
        diagnosisModel.setMigraineTest(checkBoxMigrane.isChecked());
        diagnosisModel.setResult(strResult);

        diagnosisModel.saveDiagnosis(realm);



    }
}
