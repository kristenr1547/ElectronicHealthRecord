package com.rose.electronichealthrecord.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.rose.electronichealthrecord.Adapters.PatientAdapter;
import com.rose.electronichealthrecord.Database.Repository;
import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.R;

import java.util.List;
import java.util.Locale;

public class PatientList extends AppCompatActivity {
    Repository repo;
    int doctorID;
    EditText searchField;
    String selectedSearchType;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        setTitle("DASHBOARD");
        //setting up recylcerview for patient list
        repo = new Repository(getApplication());
        recyclerView = findViewById(R.id.patientListRecyclerView);
        doctorID = getIntent().getIntExtra("doctorID",-1);
        List<Patient> patients = repo.getPatientsByDoctor(doctorID);
        final PatientAdapter adapter = new PatientAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setPatients(patients);
        searchField = findViewById(R.id.searchField);
        selectedSearchType = "none";

    }

    public void onAddPatient(View view) {
        Intent i = new Intent(this, AddPatient.class);
        i.putExtra("doctorID",doctorID);
        startActivity(i);
    }

    public void searchType(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.medRadio:
                if(checked)
                    selectedSearchType = "medication";
                break;
            case R.id.cityRadio:
                if(checked)
                    selectedSearchType = "city";
                break;
    }}


    public void onResetTable(View view) {
        List<Patient> patients = repo.getPatientsByDoctor(doctorID);
        final PatientAdapter adapter = new PatientAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setPatients(patients);

    }

    public void onPatientSearch(View view) {
        if(selectedSearchType.equals("none")){
            Toast.makeText(this, "Please select a search type.", Toast.LENGTH_LONG).show();
        }else if(searchField.getText().toString().matches("")){
            Toast.makeText(this, "Please enter city or medication name to search", Toast.LENGTH_LONG).show();
        }else if(selectedSearchType.equals("medication")){
            String medicationName = searchField.getText().toString().toUpperCase().trim();
            List<Patient> patients = repo.getPatientsByMatchingMedicationName(medicationName);
            final PatientAdapter adapter = new PatientAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setPatients(patients);
        }else if(selectedSearchType.equals("city")){
            String cityName = searchField.getText().toString().toUpperCase().trim();
            List<Patient> patients = repo.getPatientsByLocation(cityName);
            final PatientAdapter adapter = new PatientAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setPatients(patients);
        }
    }
}