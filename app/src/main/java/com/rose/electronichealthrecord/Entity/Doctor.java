package com.rose.electronichealthrecord.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName = "doctors")
public class Doctor extends Person{
    @PrimaryKey(autoGenerate = true)
    private int doctorID;
    private String username;
    private String password;

    public Doctor(String name, String phone, int doctorID, String username, String password) {
        super(name, phone);
        this.doctorID = doctorID;
        this.username = username;
        this.password = password;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
