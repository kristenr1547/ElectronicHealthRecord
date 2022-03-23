package com.rose.electronichealthrecord.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rose.electronichealthrecord.Database.Repository;
import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.R;

public class AddPatient extends AppCompatActivity {
    EditText addPatientNameField;
    EditText addPatientAgeField;
    EditText addPatientCityField;
    EditText addPatientPhoneField;
    int doctorID;
    Repository repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        setTitle("ADD PATIENT");
        addPatientNameField = findViewById(R.id.addPatientNameField);
        addPatientAgeField = findViewById(R.id.addPatientAgeField);
        addPatientCityField = findViewById(R.id.addPatientCityField);
        addPatientPhoneField = findViewById(R.id.addPatientPhoneField);
        doctorID = getIntent().getIntExtra("doctorID", -1);
        repo = new Repository(getApplication());
    }

    public void onSaveAddPatient(View view) {
        Patient newPatient;
        int patientID;
        if(repo.getAllPatients().size()==0){
            patientID = 1;
        }else{
            patientID = repo.getAllPatients().get(repo.getAllPatients().size()-1).getPatientID() +1;
        }
        int age;
        String phone = addPatientPhoneField.getText().toString().toUpperCase().trim();
        String city = addPatientCityField.getText().toString().toUpperCase().trim();
        String name = addPatientNameField.getText().toString().toUpperCase().trim();
        try{age = Integer.valueOf(addPatientAgeField.getText().toString());}
        catch (NumberFormatException e){
            Toast.makeText(this, "Please enter valid integer for age", Toast.LENGTH_LONG).show();
            age = -1;
        }
        if(phone.matches("") || city.matches("") || name.matches("") || age < 0){
            Toast.makeText(this, "Please complete all required fields.", Toast.LENGTH_LONG).show();
        }else{
            newPatient = new Patient(name,
                    phone,
                    patientID,
                    age,
                    city,
                    doctorID);
            repo.insert(newPatient);
            Intent i = new Intent(this, PatientList.class);
            i.putExtra("doctorID",doctorID);
            startActivity(i);
        }
    }

    public void onCancelAddPatient(View view) {
        Intent i = new Intent(this, PatientList.class);
        i.putExtra("doctorID",doctorID);
        startActivity(i);
    }
}