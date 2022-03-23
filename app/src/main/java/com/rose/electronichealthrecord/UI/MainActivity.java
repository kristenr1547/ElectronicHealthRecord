package com.rose.electronichealthrecord.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rose.electronichealthrecord.Database.Repository;
import com.rose.electronichealthrecord.Entity.Diagnosis;
import com.rose.electronichealthrecord.Entity.Doctor;
import com.rose.electronichealthrecord.Entity.Medication;
import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.Entity.ProgressNote;
import com.rose.electronichealthrecord.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Repository repo;
    List<Doctor> doctors;
    EditText usernameField;
    EditText passwordField;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Patient Care Portal");
        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);

        //inserting doctor to create login
        Doctor kristen = new Doctor("kristen", "7607641547", 1, "test", "test");
        repo = new Repository(getApplication());
        repo.insert(kristen);
        //creating patients
        Patient alex = new Patient("ALEX","435-764-1547",1,25,"ROY",1);
        Patient wade = new Patient("WADE","435-555-1547",2,30,"LOGAN",1);
        Patient p1 = new Patient("RICK","435-555-1547",3,50,"LOGAN",1);
        Patient p2 = new Patient("MICHELLE","123-555-1547",4,15,"HYRUM",1);
        Patient p3 = new Patient("HAILEE","456-555-1547",5,10,"SALT LAKE",1);
        Patient p4 = new Patient("SHAUNA","789-555-1547",6,35,"ROY",1);
        Patient p5 = new Patient("CHRIS","123-555-1547",7,80,"ROY",1);
        repo.insert(alex);
        repo.insert(wade);
        repo.insert(p1);
        repo.insert(p2);
        repo.insert(p3);
        repo.insert(p4);
        repo.insert(p5);
        //CREATING AND INSERTING MEDICATIONS
        Medication alexMed = new Medication(1,"SYNTHROID", 200,1);
        Medication alexMed1 = new Medication(2,"TYLENOL", 100,1);
        Medication alexMed2 = new Medication(3,"ASPIRIN", 400,1);
        Medication alexMed3 = new Medication(4,"LIPITOR", 200,2);
        Medication alexMed4 = new Medication(5,"LIPITOR", 100,3);
        Medication alexMed5 = new Medication(6,"TYLENOL", 400,4);
        repo.insert(alexMed);
        repo.insert(alexMed1);
        repo.insert(alexMed3);
        repo.insert(alexMed4);
        repo.insert(alexMed5);
        repo.insert(alexMed2);
        //CREATING AND INSERTING PROGRESS NOTES
        ProgressNote alexNote1 = new ProgressNote(1,"FIRST","this is the first visit.","05/20/2020 16:15:10",1);
        ProgressNote alexNote2 = new ProgressNote(2,"SECOND","this is the second visit.","05/20/2020 12:15:20",1);
        ProgressNote note1 = new ProgressNote(2,"CHECK UP","this is the CHECK UP visit.","01/01/2020 01:15:30",2);
        repo.insert(alexNote1);
        repo.insert(alexNote2);
        repo.insert(note1);
        //CREATING AND INSERTING DIAGNOSES
        Diagnosis alexDiagnosis = new Diagnosis(1,"HYPERTENSION","02/26/202",1);
        Diagnosis D1 = new Diagnosis(1,"DIABETES","01/24/2020",2);
        Diagnosis D2 = new Diagnosis(1,"HYPOTHYROIDISM","03/15/2020",3);
        Diagnosis D3 = new Diagnosis(1,"HYPOTENSION","05/09/2021",4);
        repo.insert(alexDiagnosis);
        repo.insert(D1);
        repo.insert(D2);
        repo.insert(D3);
        doctors = repo.getAllDoctors();
    }

    public void enterProgram(View view) {
        username = usernameField.getText().toString();
        password = passwordField.getText().toString();
       for(int i = 0; i < doctors.size();i++){
           Doctor tempDoc = doctors.get(i);
           if(tempDoc.getUsername().equals(username) && tempDoc.getPassword().equals(password)){
               Intent intent = new Intent(this, PatientList.class);
               intent.putExtra("doctorID",tempDoc.getDoctorID());
               startActivity(intent);
           }else{
               Toast.makeText(this, "Please enter correct username or password.", Toast.LENGTH_LONG).show();
           }

       }

    }
}