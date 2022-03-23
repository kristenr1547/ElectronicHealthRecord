package com.rose.electronichealthrecord.Database;
import android.app.Application;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rose.electronichealthrecord.DAO.DiagnosisDAO;
import com.rose.electronichealthrecord.DAO.DoctorDAO;
import com.rose.electronichealthrecord.DAO.MedicationDAO;
import com.rose.electronichealthrecord.DAO.PatientDAO;
import com.rose.electronichealthrecord.DAO.ProgressNoteDAO;
import com.rose.electronichealthrecord.Entity.Diagnosis;
import com.rose.electronichealthrecord.Entity.Doctor;
import com.rose.electronichealthrecord.Entity.Medication;
import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.Entity.ProgressNote;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class Repository {

    private DiagnosisDAO mDiagnosisDao;
    private DoctorDAO mDoctorDao;
    private MedicationDAO mMedicationDAO;
    private PatientDAO mPatientDAO;
    private ProgressNoteDAO mProgressNoteDAO;
    private List<Doctor> mAllDoctors;
    private List<Patient> mAllPatients;
    private List<Medication> mAllMedications;
    private List<ProgressNote> mAllNotes;
    private List<Diagnosis> mAllDiagnoses;


    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        PatientDatabaseBuilder db = PatientDatabaseBuilder.getDatabase(application);
        mDiagnosisDao = db.diagnosisDAO();
        mDoctorDao = db.doctorDAO();
        mMedicationDAO = db.medicationDAO();
        mPatientDAO = db.patientDAO();
        mProgressNoteDAO = db.progressNoteDAO();
    }

    //doctor queries
    //doctor insert
    public void insert(Doctor doctor){
        databaseExecutor.execute(()->{
            mDoctorDao.insert(doctor);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //update doctor
    public void update(Doctor doctor){
        databaseExecutor.execute(()->{
            mDoctorDao.update(doctor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//doctor delete
public void delete(Doctor doctor){
    databaseExecutor.execute(()->{
        mDoctorDao.delete(doctor);
    });
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
    //get doctors
    public List<Doctor> getAllDoctors(){
        databaseExecutor.execute(()->{
            mAllDoctors = mDoctorDao.getAllDoctors();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllDoctors;
    }

    //Medication queries

    //medication insert
    public void insert(Medication medication){
        databaseExecutor.execute(()->{
            mMedicationDAO.insert(medication);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //update medication
    public void update(Medication medication){
        databaseExecutor.execute(()->{
            mMedicationDAO.update(medication);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete medication
    public void delete(Medication medication){
        databaseExecutor.execute(()->{
            mMedicationDAO.delete(medication);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //get all medications
    public List<Medication> getAllMedications(){
        databaseExecutor.execute(()->{
            mAllMedications = mMedicationDAO.getAllMedications();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllMedications;
    }

    //get medications by patientID
    public List<Medication> getMedicationsByPatient(int patientID){
        databaseExecutor.execute(()->{
            mAllMedications = mMedicationDAO.getMedicationsByPatient(patientID);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllMedications;
    }

    //diagnoses queries

    //diagnoses insert
    public void insert(Diagnosis diagnosis){
        databaseExecutor.execute(()->{
            mDiagnosisDao.insert(diagnosis);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //update diagnosis
    public void update(Diagnosis diagnosis){
        databaseExecutor.execute(()->{
            mDiagnosisDao.update(diagnosis);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete diagnosis
    public void delete(Diagnosis diagnosis){
        databaseExecutor.execute(()->{
            mDiagnosisDao.delete(diagnosis);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //get all diagnoses
    public List<Diagnosis> getAllDiagnoses(){
        databaseExecutor.execute(()->{
            mAllDiagnoses = mDiagnosisDao.getAllDiagnoses();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllDiagnoses;
    }

    //get diagnoses by patientID
    public List<Diagnosis> getDiagnosesByPatient(int patientID){
        databaseExecutor.execute(()->{
            mAllDiagnoses = mDiagnosisDao.getDiagnosesByPatient(patientID);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllDiagnoses;
    }

    //progress note queries

    //note insert
    public void insert(ProgressNote progressNote){
        databaseExecutor.execute(()->{
            mProgressNoteDAO.insert(progressNote);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //update note
    public void update(ProgressNote progressNote){
        databaseExecutor.execute(()->{
            mProgressNoteDAO.update(progressNote);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete note
    public void delete(ProgressNote progressNote){
        databaseExecutor.execute(()->{
            mProgressNoteDAO.delete(progressNote);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //get all notes
    public List<ProgressNote> getAllNotes(){
        databaseExecutor.execute(()->{
            mAllNotes = mProgressNoteDAO.getAllNotes();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllNotes;
    }

    //get notes by patientID
    public List<ProgressNote> getNotesByPatient(int patientID){
        databaseExecutor.execute(()->{
            mAllNotes = mProgressNoteDAO.getNotesByPatient(patientID);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllNotes;
    }

    //patient queries
    //patient insert
    public void insert(Patient patient){
        databaseExecutor.execute(()->{
            mPatientDAO.insert(patient);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //update note
    public void update(Patient patient){
        databaseExecutor.execute(()->{
            mPatientDAO.update(patient);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete note
    public void delete(Patient patient){
        databaseExecutor.execute(()->{
            mPatientDAO.delete(patient);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //get all notes
    public List<Patient> getAllPatients(){
        databaseExecutor.execute(()->{
            mAllPatients = mPatientDAO.getAllPatients();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllPatients;
    }

    //get notes by patientID
    public List<Patient> getPatientsByDoctor(int doctorID){
        databaseExecutor.execute(()->{
            mAllPatients = mPatientDAO.getPatientsByDoctor(doctorID);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllPatients;
    }

    // list patients by city
    public List<Patient> getPatientsByLocation(String city){
        databaseExecutor.execute(()->{
            mAllPatients = mPatientDAO.getPatientsByLocation(city);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllPatients;
    }
    //list patients by medication name
    public List<Patient> getPatientsByMatchingMedicationName(String medication){
        databaseExecutor.execute(()->{
            mAllPatients = mPatientDAO.getPatientsByMatchingMedicationName(medication);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllPatients;
    }

    //get patient by id
    public List<Patient> getPatientByID(int id){
        databaseExecutor.execute(()->{
            mAllPatients = mPatientDAO.getPatientByID(id);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllPatients;
    }


}
