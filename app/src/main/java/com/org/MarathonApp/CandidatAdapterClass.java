package com.org.MarathonApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CandidatAdapterClass extends RecyclerView.Adapter<CandidatAdapterClass.ViewHolder> {

    List<CandidatModelClass> candidat;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public CandidatAdapterClass(List<CandidatModelClass> candidat, Context context) {
        this.candidat = candidat;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.candidat_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final CandidatModelClass candidatModelClass = candidat.get(position);

        holder.textViewID.setText(Integer.toString(candidatModelClass.getId()));
        holder.editText_Name.setText(candidatModelClass.getName());
        holder.editText_Email.setText(candidatModelClass.getEmail());
        holder.editText_Ville.setText(candidatModelClass.getVille());
        holder.editText_Age.setText(candidatModelClass.getAge());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringEmail = holder.editText_Email.getText().toString();
                String stringVille = holder.editText_Ville.getText().toString();
                String stringAge = holder.editText_Age.getText().toString();
                databaseHelperClass.updateCandidat(new CandidatModelClass(candidatModelClass.getId(),stringName,stringEmail,stringVille,stringAge));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteCandidat(candidatModelClass.getId());
                candidat.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return candidat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Name;
        EditText editText_Email;
        EditText editText_Ville;
        EditText editText_Age;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Email = itemView.findViewById(R.id.edittext_email);
            editText_Ville = itemView.findViewById(R.id.edittext_City);
            editText_Age = itemView.findViewById(R.id.edittext_age);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
