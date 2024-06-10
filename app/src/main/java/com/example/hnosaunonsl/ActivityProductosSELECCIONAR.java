package com.example.hnosaunonsl;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hnosaunonsl.adaptador.ProductoAdapterSEL;
import com.example.hnosaunonsl.modelo.Producto;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ActivityProductosSELECCIONAR extends AppCompatActivity {

    RecyclerView mRecyclerProduS;
    ProductoAdapterSEL mAdapterProduS;
    FirebaseFirestore miFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_seleccionar);
        this.setTitle("PRODUCTOS");

        //IMPLEMENTADO EL RECYCLEVIEW
        miFirestore = FirebaseFirestore.getInstance();
        mRecyclerProduS = findViewById(R.id.recycleVProductoS);
        mRecyclerProduS.setLayoutManager(new LinearLayoutManager(this));
        Query query = miFirestore.collection("productos"); //CONSULTA SIMPLE

        FirestoreRecyclerOptions<Producto> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Producto>().setQuery(query, Producto.class).build();
        mAdapterProduS = new ProductoAdapterSEL(firestoreRecyclerOptions,this, getSupportFragmentManager());
        mAdapterProduS.notifyDataSetChanged();
        mRecyclerProduS.setAdapter(mAdapterProduS);

    }

    protected void onStart() {
        super.onStart();
        mAdapterProduS.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapterProduS.stopListening();

    }
}
