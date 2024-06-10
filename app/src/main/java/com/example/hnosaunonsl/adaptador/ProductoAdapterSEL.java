package com.example.hnosaunonsl.adaptador;
import static androidx.annotation.InspectableProperty.ValueType.RESOURCE_ID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hnosaunonsl.CrearPedidoFragment;
import com.example.hnosaunonsl.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.example.hnosaunonsl.modelo.Producto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.text.DecimalFormat;

public class ProductoAdapterSEL extends FirestoreRecyclerAdapter<Producto, ProductoAdapterSEL.ViewHolder> {
    private FirebaseFirestore miFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    FragmentManager fm;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapterSEL(@NonNull FirestoreRecyclerOptions<Producto> options, Activity activity, FragmentManager fm) {
        super(options);
        this.activity = activity;
        this.fm = fm;
    }

    @Override //LEYENDO DE LA BASE DE DATOS
    protected void onBindViewHolder(@NonNull ProductoAdapterSEL.ViewHolder holder, int position, @NonNull Producto Produ) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAbsoluteAdapterPosition());
        final String id = documentSnapshot.getId(); //referencia de ID
        String nombreP = documentSnapshot.getString("nombre");
        DecimalFormat format = new DecimalFormat("0.00");

        holder.nombre.setText(Produ.getNombre());
        holder.precio.setText(format.format(Produ.getPrecio()));
        holder.caducidad.setText(Produ.getCaducidad());

        holder.nombre.setOnClickListener(new View.OnClickListener() { //lISTAR PRODUCTO PARA SELECCIONAR NOMBRE
            @Override
            public void onClick(View v) {
                CrearPedidoFragment  pediFrag = new CrearPedidoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("nombre_p", nombreP);
                pediFrag.setArguments(bundle);
                pediFrag.show(fm, "open fragment");
            }
        });
    }

    @NonNull   //MOSTRANDO
    @Override //conectando el adaptador con el layout view single
    public ProductoAdapterSEL.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_producto_single_sel, parent, false);
        return new ProductoAdapterSEL.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, caducidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nameP);
            precio = itemView.findViewById(R.id.pri);
            caducidad = itemView.findViewById(R.id.cad);
        }
    }
}
