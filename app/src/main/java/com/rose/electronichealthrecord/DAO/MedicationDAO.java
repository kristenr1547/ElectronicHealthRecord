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

import com.rose.electronichealthrecord.Entity.Medication;
import com.rose.electronichealthrecord.Entity.Patient;


import java.util.List;
@Dao
public interface MedicationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Medication medication);

    @Update
    void update(Medication medication);

    @Delete
    void delete(Medication medication);

    @Query("SELECT * FROM medications ORDER BY medicationID")
    List<Medication> getAllMedications();

    @Query("SELECT * FROM medications WHERE patientID = :patientID")
    List<Medication> getMedicationsByPatient(int patientID);
}
