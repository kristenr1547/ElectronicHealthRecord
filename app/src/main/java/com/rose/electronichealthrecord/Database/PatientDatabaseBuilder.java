package com.rose.electronichealthrecord.Database;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


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

@Database(entities = {Diagnosis.class, Doctor.class, Medication.class, Patient.class, ProgressNote.class}, version = 9 , exportSchema = false )
public abstract class PatientDatabaseBuilder extends RoomDatabase{
    public abstract DiagnosisDAO diagnosisDAO();
    public abstract DoctorDAO doctorDAO();
    public abstract MedicationDAO medicationDAO();
    public abstract PatientDAO patientDAO();
    public abstract ProgressNoteDAO progressNoteDAO();


    private static volatile PatientDatabaseBuilder INSTANCE;

    static PatientDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PatientDatabaseBuilder.class) {
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PatientDatabaseBuilder.class, "PatientDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
