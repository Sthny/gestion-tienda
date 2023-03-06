package com.example.hnosaunonsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.hnosaunonsl.adaptador.ProductoAdapter;
import com.example.hnosaunonsl.modelo.Producto;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ActivityProductos extends AppCompatActivity {
    Button bagregar;
    RecyclerView mRecyclerProdu;
    ProductoAdapter mAdapterProdu;
    FirebaseFirestore miFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        this.setTitle("PRODUCTOS");

        //IMPLEMENTADO EL RECYCLEVIEW
        miFirestore = FirebaseFirestore.getInstance();
        mRecyclerProdu = findViewById(R.id.recycleVProducto);
        mRecyclerProdu.setLayoutManager(new LinearLayoutManager(this));
        Query query = miFirestore.collection("productos"); //CONSULTA SIMPLE

        FirestoreRecyclerOptions<Producto> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Producto>().setQuery(query, Producto.class).build();
        mAdapterProdu = new ProductoAdapter(firestoreRecyclerOptions, this);
        mAdapterProdu.notifyDataSetChanged();
        mRecyclerProdu.setAdapter(mAdapterProdu);

        bagregar = findViewById((R.id.bAgregarProducto)); //BOTON PARA CREAR PRODUCTO

        bagregar.setOnClickListener(v -> { //Vamos al Fragment
            CrearProductoFragment fmprodu = new CrearProductoFragment();
            fmprodu.show(getSupportFragmentManager(), "Navegar a fragment");
        });
    }

    protected void onStart() {
        super.onStart();
        mAdapterProdu.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapterProdu.stopListening();

    }

}