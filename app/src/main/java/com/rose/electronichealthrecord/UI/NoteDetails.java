package com.rose.electronichealthrecord.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rose.electronichealthrecord.Database.Repository;
import com.rose.electronichealthrecord.Entity.ProgressNote;
import com.rose.electronichealthrecord.R;

import java.time.LocalDateTime;

public class NoteDetails extends AppCompatActivity {
    EditText detailsNoteTitle;
    EditText detailsNoteBody;
    TextView noteDateTime;
    int patientID;
    int noteID;
    String noteTitle;
    String noteBody;
    String dateTime;
    Repository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        setTitle("Note Details");
        detailsNoteBody = findViewById(R.id.detailsNoteBody);
        detailsNoteTitle = findViewById(R.id.detailsNoteTitle);
        noteDateTime = findViewById(R.id.noteDetailsDateTime);
        //get information from intent
        noteID = getIntent().getIntExtra("noteID",-1);
        patientID = getIntent().getIntExtra("patientID",-1);
        noteTitle = getIntent().getStringExtra("title");
        noteBody = getIntent().getStringExtra("body");
        dateTime = getIntent().getStringExtra("dateTime");

        repo = new Repository(getApplication());
        //set display information
        detailsNoteTitle.setText(noteTitle);
        detailsNoteBody.setText(noteBody);
        noteDateTime.setText(dateTime);

    }


    public void onSaveDetailsNote(View view) {
        ProgressNote updatedNote;
        String noteUpdatedTitle = detailsNoteTitle.getText().toString().toUpperCase().trim();
        String noteUpdatedBody = detailsNoteBody.getText().toString().toUpperCase().trim();
        String noteDateTimeUpdate = LocalDateTime.now().toString();
        if(noteUpdatedTitle.matches("")||noteUpdatedBody.matches("")){
            Toast.makeText(this, "Please complete all fields.", Toast.LENGTH_LONG).show();
        }else{
            updatedNote = new ProgressNote(noteID,noteUpdatedTitle,noteUpdatedBody,noteDateTimeUpdate,patientID);
            repo.insert(updatedNote);
            Intent i = new Intent(this, PatientDetails.class);
            i.putExtra("patientID", patientID);
            startActivity(i);
        }

    }

    public void deleteButton(View view) {
        ProgressNote note = new ProgressNote(noteID,noteTitle,noteBody,dateTime,patientID);
        repo.delete(note);
        Intent i = new Intent(this, PatientDetails.class);
        i.putExtra("patientID", patientID);
        startActivity(i);
    }
}