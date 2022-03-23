package com.rose.electronichealthrecord.Entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "patients", foreignKeys = @ForeignKey(entity = Doctor.class, parentColumns = "doctorID", childColumns = "doctorID", onDelete = CASCADE))

public class Patient extends Person{
    @PrimaryKey(autoGenerate = true)
    private int patientID;
    private int age;
    private String location;
    private int doctorID;

    public Patient(String name, String phone, int patientID, int age, String location, int doctorID) {
        super(name, phone);
        this.patientID = patientID;
        this.age = age;
        this.location = location;
        this.doctorID = doctorID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        location = location;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }
}
