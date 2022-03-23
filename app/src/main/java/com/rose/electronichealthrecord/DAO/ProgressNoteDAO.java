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

import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.Entity.ProgressNote;

import java.util.List;
@Dao
public interface ProgressNoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ProgressNote progressNote);

    @Update
    void update(ProgressNote progressNote);

    @Delete
    void delete(ProgressNote progressNote);

    @Query("SELECT * FROM notes ORDER BY noteID")
    List<ProgressNote> getAllNotes();

    @Query("SELECT * FROM notes WHERE patientID = :patientID")
    List<ProgressNote> getNotesByPatient(int patientID);
}
