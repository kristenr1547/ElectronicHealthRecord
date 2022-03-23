package com.rose.electronichealthrecord.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rose.electronichealthrecord.Adapters.DiagnosisAdapter;
import com.rose.electronichealthrecord.Adapters.MedicationAdapter;
import com.rose.electronichealthrecord.Adapters.NoteAdapter;
import com.rose.electronichealthrecord.Adapters.PatientAdapter;
import com.rose.electronichealthrecord.Database.Repository;
import com.rose.electronichealthrecord.Entity.Diagnosis;
import com.rose.electronichealthrecord.Entity.Medication;
import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.Entity.ProgressNote;
import com.rose.electronichealthrecord.R;

import java.util.List;

public class PatientDetails extends AppCompatActivity {
    EditText patientDetailsName;
    EditText patientDetailsPhone;
    EditText patientDetailsAge;
    EditText patientDetailsCity;
    Patient currentPatient;
    int patientID;
    int doctorID;
    Repository repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        setTitle("Patient Information");
        repo = new Repository(getApplication());
        //binding fields
        patientDetailsName = findViewById(R.id.patientDetailNameField);
        patientDetailsPhone = findViewById(R.id.patientDetailPhoneField);
        patientDetailsAge = findViewById(R.id.patientDetailAgeField);
        patientDetailsCity = findViewById(R.id.patientDetailCityField);

        //initializing fields to display information passed from adapter
        doctorID = getIntent().getIntExtra("doctorID",-1);
        patientID = getIntent().getIntExtra("patientID",-1);
        List<Patient> patientSearch = repo.getPatientByID(patientID);
        currentPatient = patientSearch.get(0);
        patientDetailsName.setText(currentPatient.getName());
        patientDetailsAge.setText(String.valueOf(currentPatient.getAge()));
        patientDetailsCity.setText(currentPatient.getLocation());
        patientDetailsPhone.setText(currentPatient.getPhone());
        //recyclerview for medications
        RecyclerView medicationRecyclerView = findViewById(R.id.patientDetailsMedicationRecycler);
        List<Medication> medications = repo.getMedicationsByPatient(patientID);
        final MedicationAdapter medAdapter = new MedicationAdapter(this);
        medicationRecyclerView.setAdapter(medAdapter);
        medicationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        medAdapter.setMedications(medications);
        //recyclerview for notes
        RecyclerView noteRecyclerView = findViewById(R.id.patientDetailsProgressRecycler);
        List<ProgressNote> notes = repo.getNotesByPatient(patientID);
        final NoteAdapter noteAdapter = new NoteAdapter(this);
        noteRecyclerView.setAdapter(noteAdapter);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter.setNotes(notes);
        //recyclerview for diagnoses
        RecyclerView diagnosesRecyclerView = findViewById(R.id.patientDetailsDiagnosesRecycler);
        List<Diagnosis> diagnoses = repo.getDiagnosesByPatient(patientID);
        final DiagnosisAdapter diagnosesAdapter = new DiagnosisAdapter(this);
        diagnosesRecyclerView.setAdapter(diagnosesAdapter);
        diagnosesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        diagnosesAdapter.setDiagnoses(diagnoses);

    }

    public void onAddMedication(View view) {
        Intent i = new Intent(this, AddMedication.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }

    public void onAddDiagnosis(View view) {
        Intent i = new Intent(this, AddDiagnosis.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }

    public void onAddNote(View view) {
        Intent i = new Intent(this, AddProgressNote.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }


    public void onSavePatientDetails(View view) {
        Patient updatePatient;
        int age;
        String phone = patientDetailsPhone.getText().toString().toUpperCase().trim();
        String city = patientDetailsCity.getText().toString().toUpperCase().trim();
        String name = patientDetailsName.getText().toString().toUpperCase().trim();
        try{age = Integer.valueOf(patientDetailsAge.getText().toString());}
        catch (NumberFormatException e){
            Toast.makeText(this, "Please enter valid integer for age", Toast.LENGTH_LONG).show();
            age = -1;
        }
        if(phone.matches("") || city.matches("") || name.matches("") || age < 0){
            Toast.makeText(this, "Please complete all required fields.", Toast.LENGTH_LONG).show();
            System.out.println("Please complete all required fields");
        }else{
            updatePatient = new Patient(name,
                    phone,
                    patientID,
                    age,
                    city,
                    doctorID);
            repo.update(updatePatient);
            Intent i = new Intent(this, PatientList.class);
            i.putExtra("doctorID",doctorID);
            startActivity(i);
        }
    }

    public void onDeletePatient(View view) {
        Patient deletePatient = new Patient(currentPatient.getName(), currentPatient.getPhone(), currentPatient.getPatientID(),currentPatient.getAge(),currentPatient.getLocation(),currentPatient.getDoctorID());
        repo.delete(deletePatient);
        Intent i = new Intent(this, PatientList.class);
        i.putExtra("doctorID",doctorID);
        startActivity(i);
    }
}