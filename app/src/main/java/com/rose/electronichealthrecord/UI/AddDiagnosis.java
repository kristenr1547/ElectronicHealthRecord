package com.rose.electronichealthrecord.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rose.electronichealthrecord.Database.Repository;
import com.rose.electronichealthrecord.Entity.Diagnosis;
import com.rose.electronichealthrecord.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AddDiagnosis extends AppCompatActivity {
    EditText addDiagnosisName;
    EditText addDiagnosisDate;
    int patientID;
    Repository repo;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diagnosis);
        setTitle("ADD DIAGNOSIS");
        patientID = getIntent().getIntExtra("patientID",-1);
        repo = new Repository(getApplication());
        addDiagnosisName = findViewById(R.id.addDiagnosisName);
        addDiagnosisDate = findViewById(R.id.addDiagnosisDate);
    }

    public void onCancelAddDiagnosis(View view) {
        Intent i = new Intent(this, PatientDetails.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }

    public void onSaveAddDiagnosis(View view) {
        Diagnosis newDiagnosis;
        int diagnosisID;
        if(repo.getAllDiagnoses().size()==0){
            diagnosisID = 1;
        }else{
            diagnosisID = repo.getAllDiagnoses().get(repo.getAllDiagnoses().size()-1).getDiagnosisID() +1;
        }
        String name = addDiagnosisName.getText().toString().trim().toUpperCase();
        String date = addDiagnosisDate.getText().toString().trim();
        LocalDate dateEntered;
        Boolean errorFound;
        try{
            dateEntered = LocalDate.parse(date,formatter);
            System.out.println(dateEntered);
            errorFound = false;
        }catch (Exception e){
            Toast.makeText(this, "Please enter date in correct format.", Toast.LENGTH_LONG).show();
            errorFound = true;
        }
        if(name.matches("") || errorFound){
            Toast.makeText(this, "Please complete all fields.", Toast.LENGTH_LONG).show();
        }else{
            newDiagnosis = new Diagnosis(diagnosisID,name,date,patientID);
            repo.insert(newDiagnosis);
            Intent i = new Intent(this, PatientDetails.class);
            i.putExtra("patientID", patientID);
            startActivity(i);
        }

    }
}