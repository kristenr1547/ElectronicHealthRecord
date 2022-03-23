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

import com.rose.electronichealthrecord.Entity.Diagnosis;
import com.rose.electronichealthrecord.Entity.Medication;
import com.rose.electronichealthrecord.Entity.Patient;

import java.util.List;
@Dao
public interface DiagnosisDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Diagnosis diagnosis);

    @Update
    void update(Diagnosis diagnosis);

    @Delete
    void delete(Diagnosis diagnosis);

    @Query("SELECT * FROM diagnoses ORDER BY diagnosisID")
    List<Diagnosis> getAllDiagnoses();

    @Query("SELECT * FROM diagnoses WHERE patientID = :patientID")
    List<Diagnosis> getDiagnosesByPatient(int patientID);

}
