package com.rose.electronichealthrecord.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rose.electronichealthrecord.Entity.Doctor;

import java.util.List;

@Dao
public interface DoctorDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Doctor doctor);

    @Update
    void update(Doctor doctor);

    @Delete
    void delete(Doctor doctor);
    //used for logging into system
    @Query("SELECT * FROM doctors ORDER BY doctorID")
    List<Doctor> getAllDoctors();

}
