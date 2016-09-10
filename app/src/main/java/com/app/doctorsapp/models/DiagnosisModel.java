package com.app.doctorsapp.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by faisal on 9/10/16.
 */
public class DiagnosisModel extends RealmObject {

    @PrimaryKey
    int diagnosisId;
    boolean migraineTest;
    boolean ageTest;
    boolean sexTest;
    boolean drugTest;
    String result;

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public boolean isMigraineTest() {
        return migraineTest;
    }

    public void setMigraineTest(boolean migraineTest) {
        this.migraineTest = migraineTest;
    }

    public boolean isAgeTest() {
        return ageTest;
    }

    public void setAgeTest(boolean ageTest) {
        this.ageTest = ageTest;
    }

    public boolean isSexTest() {
        return sexTest;
    }

    public void setSexTest(boolean sexTest) {
        this.sexTest = sexTest;
    }

    public boolean isDrugTest() {
        return drugTest;
    }

    public void setDrugTest(boolean drugTest) {
        this.drugTest = drugTest;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    //Saves the diagnosis in the database
    public void saveDiagnosis(Realm realm) {
        realm.beginTransaction();
        DiagnosisModel diagnosisModel = realm.createObject(DiagnosisModel.class);

        int nextID = diagnosisModel.getNextKey(realm);
        diagnosisModel.setDiagnosisId(nextID);
        diagnosisModel.setMigraineTest(this.isMigraineTest());
        diagnosisModel.setAgeTest(this.isAgeTest());
        diagnosisModel.setDrugTest(this.isDrugTest());
        diagnosisModel.setSexTest(this.isSexTest());
        diagnosisModel.setResult(this.getResult());

        realm.commitTransaction();
    }

    //Generates next available primary key
    public int getNextKey(Realm realm) {
        return realm.where(DiagnosisModel.class).max("diagnosisId").intValue() + 1;
    }


    //deletes all diagnosis/truncates diagnosis table
    public static void deleteAllDiagnosis(Realm realm){
        realm.beginTransaction();
        realm.where(DiagnosisModel.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }
}
