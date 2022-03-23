package com.rose.electronichealthrecord.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rose.electronichealthrecord.Entity.Medication;
import com.rose.electronichealthrecord.R;
import com.rose.electronichealthrecord.UI.MedicationDetails;

import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder> {
    class MedicationViewHolder extends RecyclerView.ViewHolder{
        private final TextView medicationItemView;
        private MedicationViewHolder(View itemView){
            super(itemView);
            medicationItemView = itemView.findViewById(R.id.medicationListItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Medication current = mMedications.get(position);
                    Intent i = new Intent(context, MedicationDetails.class);
                    i.putExtra("medicationID",current.getMedicationID());
                    i.putExtra("patientID",current.getPatientID());
                    i.putExtra("name", current.getMedicationName());
                    i.putExtra("dosage", current.getDosage());
                    context.startActivity(i);
                }
            });
        }
    }
    private List<Medication> mMedications;
    private final Context context;
    private final LayoutInflater mInflater;
    public MedicationAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public MedicationAdapter.MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_medication,parent,false);
        return new MedicationAdapter.MedicationViewHolder(itemView);
    }

    //this is where you put things onto the textview
    @Override
    public void onBindViewHolder(@NonNull MedicationAdapter.MedicationViewHolder holder, int position) {
        if(mMedications != null){
            Medication currentMedication = mMedications.get(position);
            String name = currentMedication.getMedicationName();
            int dosage = currentMedication.getDosage();
            holder.medicationItemView.setText(name + " " + dosage + "mg");
        }else{
            holder.medicationItemView.setText("No Medication Title");
        }
    }

    public void setMedications(List<Medication> medications) {
        mMedications = medications;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mMedications != null){
            return mMedications.size();
        }else{
            return 0;
        }

    }
}