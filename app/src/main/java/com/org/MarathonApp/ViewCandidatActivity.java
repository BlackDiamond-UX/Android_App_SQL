package com.org.MarathonApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewCandidatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_candidat);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<CandidatModelClass> candidatModelClasses = databaseHelperClass.getCandidatList();

        if (candidatModelClasses.size() > 0){
            CandidatAdapterClass candidatAdapterClass = new CandidatAdapterClass(candidatModelClasses, ViewCandidatActivity.this);
            recyclerView.setAdapter(CandidatAdapterClass);
        }else {
            Toast.makeText(this, "There is no candidat in the database", Toast.LENGTH_SHORT).show();
        }




    }
}
