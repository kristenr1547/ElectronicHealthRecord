package com.rose.electronichealthrecord.Entity;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "diagnoses", foreignKeys = @ForeignKey(entity = Patient.class, parentColumns = "patientID", childColumns = "patientID", onDelete = CASCADE))

public class Diagnosis {
    @PrimaryKey(autoGenerate = true)
    private int diagnosisID;
    private String diagnosisName;
    private String diagnosisDate;
    private int patientID;

    public Diagnosis(int diagnosisID, String diagnosisName, String diagnosisDate, int patientID) {
        this.diagnosisID = diagnosisID;
        this.diagnosisName = diagnosisName;
        this.diagnosisDate = diagnosisDate;
        this.patientID = patientID;
    }

    public int getDiagnosisID() {
        return diagnosisID;
    }

    public void setDiagnosisID(int diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(String diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
}
