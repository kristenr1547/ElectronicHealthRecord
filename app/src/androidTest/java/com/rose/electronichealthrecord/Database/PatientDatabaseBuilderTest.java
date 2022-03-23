package com.rose.electronichealthrecord.Database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.rose.electronichealthrecord.DAO.DiagnosisDAO;
import com.rose.electronichealthrecord.DAO.DoctorDAO;
import com.rose.electronichealthrecord.DAO.MedicationDAO;
import com.rose.electronichealthrecord.DAO.PatientDAO;
import com.rose.electronichealthrecord.DAO.ProgressNoteDAO;
import com.rose.electronichealthrecord.Entity.Diagnosis;
import com.rose.electronichealthrecord.Entity.Doctor;
import com.rose.electronichealthrecord.Entity.Medication;
import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.Entity.ProgressNote;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


@RunWith(AndroidJUnit4.class)
public class PatientDatabaseBuilderTest{
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private MedicationDAO medicationDAO;
    private ProgressNoteDAO noteDAO;
    private DiagnosisDAO diagnosisDAO;
    private PatientDatabaseBuilder db;


    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, PatientDatabaseBuilder.class).build();
        doctorDAO = db.doctorDAO();
        patientDAO = db.patientDAO();
        medicationDAO = db.medicationDAO();
        noteDAO = db.progressNoteDAO();
        diagnosisDAO = db.diagnosisDAO();
    }

    @After
    public void closeDB(){
        db.close();
    }

    @Test
    public void verifyUsername(){
        Doctor doctor = new Doctor("Kristen","435-764-1547",1,"kristen","test");
        doctorDAO.insert(doctor);
        List<Doctor> resultSet = doctorDAO.getAllDoctors();
        Doctor temp = resultSet.get(0);
        assertEquals("kristen",temp.getUsername());
    }
    @Test
    public void verifyPatientInsert(){
        Doctor doctor = new Doctor("Kristen","435-764-1547",1,"kristen","test");
        doctorDAO.insert(doctor);
        Patient testPatient = new Patient("Richard", "435-765-4813", 1, 25,"Roy", 1);
        Patient testPatient2 = new Patient("Michelle", "801-654-4561",2,30,"Roy", 1);
        patientDAO.insert(testPatient);
        patientDAO.insert(testPatient2);
        List<Patient> patients = patientDAO.getAllPatients();
        //verifies that db inserts 2 patients
        assertEquals(2,patients.size());
    }

    @Test
    public void verifyMedicationSearch(){
        Doctor doctor = new Doctor("Kristen","435-764-1547",1,"kristen","test");
        doctorDAO.insert(doctor);
        Patient testPatient = new Patient("Richard", "435-765-4813", 1, 25,"Roy", 1);
        patientDAO.insert(testPatient);
        Patient testPatient2 = new Patient("Michelle", "801-654-4561",2,30,"Roy", 1);
        patientDAO.insert(testPatient2);
        Medication m1 = new Medication(1, "tylenol",500,1);
        Medication m2 = new Medication(2,"tylenol",200,2);
        medicationDAO.insert(m1);
        medicationDAO.insert(m2);
        List<Patient> matchingPatients = patientDAO.getPatientsByMatchingMedicationName("tylenol");
        assertEquals("Richard",matchingPatients.get(0).getName());
        assertEquals("Michelle",matchingPatients.get(1).getName());
    }
@Test
    public void verifyReturnDiagnosesByPatient(){
    Doctor doctor = new Doctor("Kristen","435-764-1547",1,"kristen","test");
    doctorDAO.insert(doctor);
    Patient testPatient = new Patient("Richard", "435-765-4813", 1, 25,"Roy", 1);
    patientDAO.insert(testPatient);
    Diagnosis d1 = new Diagnosis(1,"Hypertension","11/01/2019",1);
    Diagnosis d2 = new Diagnosis(2,"Cardiovascular Disease", "12/01/2020",1);
    Diagnosis d3 = new Diagnosis(3,"Depression", "05/09/2020",1);
    diagnosisDAO.insert(d1);
    diagnosisDAO.insert(d2);
    diagnosisDAO.insert(d3);
    List<Diagnosis> patientDiagnoses = diagnosisDAO.getDiagnosesByPatient(1);
    assertEquals(3,patientDiagnoses.size());
}

@Test
    public void verifyReturnNotesByPatient(){
    Doctor doctor = new Doctor("Kristen","435-764-1547",1,"kristen","test");
    doctorDAO.insert(doctor);
    Patient testPatient = new Patient("Richard", "435-765-4813", 1, 25,"Roy", 1);
    patientDAO.insert(testPatient);
    ProgressNote p1 = new ProgressNote(1,"First Visit", "This was Richards first appointment","01/02/2020 13:30:00",1);
    ProgressNote p2 = new ProgressNote(2,"Second Visit", "This was Richards follow up appointment","01/05/2020 13:30:00",1);
    noteDAO.insert(p1);
    noteDAO.insert(p2);
    List<ProgressNote> patientNotes = noteDAO.getNotesByPatient(1);
    //should not equal 0, the list should have a size of 2
    assertNotEquals(0,patientNotes.size());
    }
}