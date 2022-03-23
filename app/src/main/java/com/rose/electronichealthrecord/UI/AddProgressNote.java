package com.rose.electronichealthrecord.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rose.electronichealthrecord.Database.Repository;
import com.rose.electronichealthrecord.Entity.ProgressNote;
import com.rose.electronichealthrecord.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AddProgressNote extends AppCompatActivity {
    EditText addNoteTitle;
    EditText addNoteBody;
    int patientID;
    Repository repo;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_progress_note);
        setTitle("ADD NOTE");
        addNoteBody = findViewById(R.id.addNoteBody);
        addNoteTitle = findViewById(R.id.addNoteTitle);
        patientID = getIntent().getIntExtra("patientID",-1);
        repo = new Repository(getApplication());

    }

    public void onCancelAddNote(View view) {
        Intent i = new Intent(this, PatientDetails.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }

    public void onSaveAddNote(View view) {
        ProgressNote newNote;
        int noteID;
        if(repo.getAllNotes().size()==0){
            noteID = 1;
        }else{
            noteID = repo.getAllNotes().get(repo.getAllNotes().size()-1).getNoteID() +1;
        }
        String noteTitle = addNoteTitle.getText().toString().toUpperCase().trim();
        String noteBody = addNoteBody.getText().toString().toUpperCase().trim();
        LocalDateTime lclDateTime = LocalDateTime.now();
        String noteDateTime = lclDateTime.format(formatter);


        if(noteTitle.matches("")||noteBody.matches("")){
            Toast.makeText(this, "Please complete all fields.", Toast.LENGTH_LONG).show();
        }else{
            newNote = new ProgressNote(noteID,noteTitle,noteBody,noteDateTime,patientID);
            repo.insert(newNote);
            Intent i = new Intent(this, PatientDetails.class);
            i.putExtra("patientID", patientID);
            startActivity(i);
        }

    }
}