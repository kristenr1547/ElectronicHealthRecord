package com.rose.electronichealthrecord.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rose.electronichealthrecord.Entity.Diagnosis;
import com.rose.electronichealthrecord.Entity.Patient;
import com.rose.electronichealthrecord.R;
import com.rose.electronichealthrecord.UI.DiagnosisDetails;
import com.rose.electronichealthrecord.UI.PatientDetails;

import java.util.List;

public class DiagnosisAdapter extends RecyclerView.Adapter<DiagnosisAdapter.DiagnosisViewHolder> {
    class DiagnosisViewHolder extends RecyclerView.ViewHolder{
        private final TextView diagnosisItemView;
        private DiagnosisViewHolder(View itemView){
            super(itemView);
            diagnosisItemView = itemView.findViewById(R.id.diagnosisListItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Diagnosis current = mDiagnoses.get(position);
                    Intent i = new Intent(context, DiagnosisDetails.class);
                    i.putExtra("patientID",current.getPatientID());
                    i.putExtra("diagnosisID",current.getDiagnosisID());
                    i.putExtra("name", current.getDiagnosisName());
                    i.putExtra("date", current.getDiagnosisDate());
                    context.startActivity(i);
                }
            });
        }
    }
    private List<Diagnosis> mDiagnoses;
    private final Context context;
    private final LayoutInflater mInflater;
    public DiagnosisAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public DiagnosisAdapter.DiagnosisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_diagnosis,parent,false);
        return new DiagnosisAdapter.DiagnosisViewHolder(itemView);
    }

    //this is where you put things onto the textview
    @Override
    public void onBindViewHolder(@NonNull DiagnosisAdapter.DiagnosisViewHolder holder, int position) {
        if(mDiagnoses != null){
            Diagnosis currentDiagnosis = mDiagnoses.get(position);
            String name = currentDiagnosis.getDiagnosisName();
            String diagnosisDate = currentDiagnosis.getDiagnosisDate();
            holder.diagnosisItemView.setText(name + " " + diagnosisDate);
        }else{
            holder.diagnosisItemView.setText("No Diagnosis Title");
        }
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        mDiagnoses = diagnoses;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mDiagnoses != null){
            return mDiagnoses.size();
        }else{
            return 0;
        }

    }
}
