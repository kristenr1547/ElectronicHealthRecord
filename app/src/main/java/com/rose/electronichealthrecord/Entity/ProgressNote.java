package com.rose.electronichealthrecord.Entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes", foreignKeys = @ForeignKey(entity = Patient.class, parentColumns = "patientID", childColumns = "patientID", onDelete = CASCADE))

public class ProgressNote {
    @PrimaryKey(autoGenerate = true)
    private int noteID;
    private String noteTitle;
    private String noteBody;
    private String noteDateTime;
    private int patientID;

    public ProgressNote(int noteID, String noteTitle, String noteBody, String noteDateTime, int patientID) {
        this.noteID = noteID;
        this.noteTitle = noteTitle;
        this.noteBody = noteBody;
        this.noteDateTime = noteDateTime;
        this.patientID = patientID;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getNoteDateTime() {
        return noteDateTime;
    }

    public void setNoteDateTime(String noteDateTime) {
        this.noteDateTime = noteDateTime;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
}
