package com.rose.electronichealthrecord.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rose.electronichealthrecord.Database.Repository;
import com.rose.electronichealthrecord.Entity.Medication;
import com.rose.electronichealthrecord.R;

public class MedicationDetails extends AppCompatActivity {
    EditText detailsMedicationNameField;
    EditText DetailsDosageField;
    int patientID;
    int medicationID;
    int medicationDosage;
    String medicationName;
    Repository repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_details);
        setTitle("Medication Details");
        //binding fields
        DetailsDosageField = findViewById(R.id.DetailsDosageField);
        detailsMedicationNameField = findViewById(R.id.detailsMedicationNameField);
        //getting intent information sent
        patientID = getIntent().getIntExtra("patientID",-1);
        medicationID = getIntent().getIntExtra("medicationID",-1);
        medicationDosage = getIntent().getIntExtra("dosage",-1);
        medicationName = getIntent().getStringExtra("name");
        //setting text to fields
        DetailsDosageField.setText(String.valueOf(medicationDosage));
        detailsMedicationNameField.setText(medicationName);
        //initializing repository
        repo = new Repository(getApplication());

    }

    public void onSaveDetailsMedication(View view) {
        Medication updatedMed;
        medicationName = detailsMedicationNameField.getText().toString().toUpperCase().trim();
        try{
            medicationDosage = Integer.valueOf(DetailsDosageField.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
            medicationDosage = -1;
            Toast.makeText(this, "Please enter valid integer for medication dose", Toast.LENGTH_LONG).show();
        }

        if(medicationName.matches("")||medicationDosage<0){

        }else{
            updatedMed = new Medication(medicationID,medicationName,medicationDosage,patientID);
            repo.update(updatedMed);
            Intent i = new Intent(this, PatientDetails.class);
            i.putExtra("patientID", patientID);
            startActivity(i);
        }

    }

    public void onDeleteMedication(View view) {
        Medication med = new Medication(medicationID,medicationName,medicationDosage,patientID);
        repo.delete(med);
        Intent i = new Intent(this, PatientDetails.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }
}