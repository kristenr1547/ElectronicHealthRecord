package com.rose.electronichealthrecord.Entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "medications", foreignKeys = @ForeignKey(entity = Patient.class, parentColumns = "patientID", childColumns = "patientID", onDelete = CASCADE))

public class Medication {
    @PrimaryKey(autoGenerate = true)
    private int medicationID;
    private String medicationName;
    private int dosage;
    private int patientID;

    public Medication(int medicationID, String medicationName, int dosage, int patientID) {
        this.medicationID = medicationID;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.patientID = patientID;
    }

    public int getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(int medicationID) {
        this.medicationID = medicationID;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
}
