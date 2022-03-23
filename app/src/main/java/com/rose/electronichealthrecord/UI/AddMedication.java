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

public class AddMedication extends AppCompatActivity {
    int patientID;
    Repository repo;
    EditText addMedicationNameField;
    EditText addDosageField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);
        setTitle("ADD MEDICATION");
        patientID = getIntent().getIntExtra("patientID",-1);
        repo = new Repository(getApplication());
        addDosageField = findViewById(R.id.addDosageField);
        addMedicationNameField = findViewById(R.id.addMedicationNameField);

    }

    public void onSaveAddMedication(View view) {
        Medication newMedication;
        int medicationID;
        if(repo.getAllMedications().size()==0){
            medicationID = 1;
        }else{
            medicationID = repo.getAllMedications().get(repo.getAllMedications().size()-1).getMedicationID() +1;
        }
        String medicationName = addMedicationNameField.getText().toString().toUpperCase().trim();
        int medicationDosage;
        try{
            medicationDosage = Integer.valueOf(addDosageField.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
            medicationDosage = -1;
            Toast.makeText(this, "Please enter valid integer for medication dose", Toast.LENGTH_LONG).show();
        }

        if(medicationName.matches("")||medicationDosage<0){

        }else{
            newMedication = new Medication(medicationID,medicationName,medicationDosage,patientID);
            repo.insert(newMedication);
            Intent i = new Intent(this, PatientDetails.class);
            i.putExtra("patientID", patientID);
            startActivity(i);
        }

    }

    public void onCancelAddmedication(View view) {
        Intent i = new Intent(this, PatientDetails.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }

}