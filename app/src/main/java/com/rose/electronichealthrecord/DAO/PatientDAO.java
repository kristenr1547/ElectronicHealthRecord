package com.rose.electronichealthrecord.DAO;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rose.electronichealthrecord.Entity.Doctor;
import com.rose.electronichealthrecord.Entity.Patient;

import java.util.List;

@Dao
public interface PatientDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("SELECT * FROM patients ORDER BY patientID")
    List<Patient> getAllPatients();

    @Query("SELECT * FROM patients WHERE doctorID = :doctorID")
    List<Patient> getPatientsByDoctor(int doctorID);

    //returns patient in a specified city for report
    @Query("SELECT * FROM patients WHERE location = :city")
    List<Patient> getPatientsByLocation(String city);

    //returns patients that have a medication name in common
    @Query("SELECT * FROM patients " +
            "INNER JOIN medications ON medications.patientID = patients.patientID " +
            "WHERE medications.medicationName LIKE :medicationName")
    public List<Patient> getPatientsByMatchingMedicationName(String medicationName);

    @Query("SELECT * FROM patients WHERE patientID = :patientId")
    List<Patient> getPatientByID(int patientId);

}
