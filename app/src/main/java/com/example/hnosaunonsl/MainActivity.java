package com.example.hnosaunonsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.hnosaunonsl.adaptador.ProveedorAdapter;
import com.example.hnosaunonsl.modelo.Proveedor;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
    Button bagregar;
    RecyclerView mRecyclerProvee;
    ProveedorAdapter mAdapterProvee;
    FirebaseFirestore miFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("PROVEEDORES");

        //IMPLEMENTADO EL RECYCLEVIEW
        miFirestore = FirebaseFirestore.getInstance();
        mRecyclerProvee = findViewById(R.id.recycleVProveedor);
        mRecyclerProvee.setLayoutManager(new LinearLayoutManager(this));
        Query query = miFirestore.collection("proveedores");

        FirestoreRecyclerOptions<Proveedor> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Proveedor>().setQuery(query,Proveedor.class).build();
       mAdapterProvee = new ProveedorAdapter(firestoreRecyclerOptions, this);
        mAdapterProvee.notifyDataSetChanged();
        mRecyclerProvee.setAdapter(mAdapterProvee);

        bagregar = findViewById((R.id.bAgregarProveedor));

        bagregar.setOnClickListener(v -> { //Vamos al Fragment
                CrearProveedorFragment fmprove = new CrearProveedorFragment();
                fmprove.show(getSupportFragmentManager(), "Navegar a fragment");
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapterProvee.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapterProvee.stopListening();

    }
}