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

public class DiagnosisDetails extends AppCompatActivity {
    EditText detailsDiagnosisName;
    EditText detailsDiagnosisDate;
    int patientID;
    int diagnosisID;
    String diagnosisName;
    String diagnosisDate;
    Repository repo;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_details);
        setTitle("Diagnosis Information");
        //bind variables
        detailsDiagnosisName = findViewById(R.id.detailsDiagnosisName);
        detailsDiagnosisDate = findViewById(R.id.detailsDiagnosisDate);
        //get extra information
        patientID = getIntent().getIntExtra("patientID", -1);
        diagnosisID = getIntent().getIntExtra("diagnosisID", -1);
        diagnosisName = getIntent().getStringExtra("name");
        diagnosisDate = getIntent().getStringExtra("date");
        //set fields with the information
        detailsDiagnosisName.setText(diagnosisName);
        detailsDiagnosisDate.setText(diagnosisDate);
    }

    public void onDeleteDiagnosis(View view) {
        Diagnosis diagnosis = new Diagnosis(diagnosisID,diagnosisName,diagnosisDate,patientID);
        repo.delete(diagnosis);
        Intent i = new Intent(this, PatientDetails.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }

    public void onSaveDetailsDiagnosis(View view) {
        Diagnosis updateDiagnosis;
        String name = detailsDiagnosisName.getText().toString().trim().toUpperCase();
        String date = detailsDiagnosisDate.getText().toString().trim();
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
            updateDiagnosis = new Diagnosis(diagnosisID,name,date,patientID);
            repo.update(updateDiagnosis);
            Intent i = new Intent(this, PatientDetails.class);
            i.putExtra("patientID", patientID);
            startActivity(i);
        }
    }
}