package com.rose.electronichealthrecord.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.R;
import com.rose.electronichealthrecord.UI.PatientDetails;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder>{

    class PatientViewHolder extends RecyclerView.ViewHolder{
    private final TextView patientItemView;
    private PatientViewHolder(View itemView){
        super(itemView);
        patientItemView = itemView.findViewById(R.id.patientListItem);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                final Patient current = mPatients.get(position);
                Intent i = new Intent(context, PatientDetails.class);
                i.putExtra("patientID",current.getPatientID());
                i.putExtra("name", current.getName());
                i.putExtra("phone", current.getPhone());
                i.putExtra("age", current.getAge());
                i.putExtra("location", current.getLocation());
                i.putExtra("doctorID", current.getDoctorID());
                context.startActivity(i);
            }
        });
    }
}
    private List<Patient> mPatients;
    private final Context context;
    private final LayoutInflater mInflater;
    public PatientAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public PatientAdapter.PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_patient,parent,false);
        return new PatientViewHolder(itemView);
    }

    //this is where you put things onto the textview
    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.PatientViewHolder holder, int position) {
        if(mPatients != null){
            Patient currentPatient = mPatients.get(position);
            String name = currentPatient.getName();
            int age = currentPatient.getAge();
            int patientID = currentPatient.getPatientID();
            holder.patientItemView.setText("ID: "+patientID+ " Name: "+ name +" Age: " +age+"");

        }else{
            holder.patientItemView.setText("No Patient Title");
        }
    }

    public void setPatients(List<Patient> patients) {
        mPatients = patients;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mPatients != null){
            return mPatients.size();
        }else{
            return 0;
        }

    }
}