package com.example.hnosaunonsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.hnosaunonsl.adaptador.PedidoAdapter;
import com.example.hnosaunonsl.modelo.Pedido;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ActivityPedidos extends AppCompatActivity {
    Button bagregar;
    RecyclerView mRecyclerPedi;
    PedidoAdapter mAdapterPedi;
    FirebaseFirestore miFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        this.setTitle("PEDIDOS");

        //IMPLEMENTADO EL RECYCLEVIEW

        miFirestore = FirebaseFirestore.getInstance();
        mRecyclerPedi = findViewById(R.id.recycleVPedido);
        mRecyclerPedi.setLayoutManager(new LinearLayoutManager(this));
        Query query = miFirestore.collection("pedidos");

        FirestoreRecyclerOptions<Pedido> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Pedido>().setQuery(query,Pedido.class).build();
        mAdapterPedi = new PedidoAdapter(firestoreRecyclerOptions, this, getSupportFragmentManager());
        mAdapterPedi.notifyDataSetChanged();
        mRecyclerPedi.setAdapter(mAdapterPedi);

        bagregar = findViewById((R.id.bAgregarPedidos));

        bagregar.setOnClickListener(v -> { //Vamos al Fragment
            CrearPedidoFragment fmpedi = new CrearPedidoFragment();
            fmpedi.show(getSupportFragmentManager(), "Navegar a fragment");
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        mAdapterPedi.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapterPedi.stopListening();
    }
}