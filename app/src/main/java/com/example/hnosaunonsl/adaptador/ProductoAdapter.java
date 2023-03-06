package com.example.hnosaunonsl.adaptador;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hnosaunonsl.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.example.hnosaunonsl.modelo.Producto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductoAdapter extends FirestoreRecyclerAdapter<Producto, ProductoAdapter.ViewHolder> {
    private FirebaseFirestore miFirestore = FirebaseFirestore.getInstance();
    Activity activity;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapter(@NonNull FirestoreRecyclerOptions<Producto> options, Activity activity) {
        super(options);
        this.activity = activity;
    }
    @Override //LEYENDO DE LA BASE DE DATOS
    protected void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position, @NonNull Producto Produ) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAbsoluteAdapterPosition());
        final String id = documentSnapshot.getId();

        holder.nombre.setText(Produ.getNombre());
        holder.precio.setText(Produ.getPrecio());
        holder.caducidad.setText(Produ.getCaducidad());

        holder.b_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProducto(id);
            }
        });
    }

    @NonNull   //MOSTRANDO
    @Override //conectando el adaptador con el view single
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_producto_single, parent, false);
        return new ProductoAdapter.ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, caducidad;
        ImageView b_eliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.no);
            precio = itemView.findViewById(R.id.pre);
            caducidad = itemView.findViewById(R.id.ca);
            b_eliminar = itemView.findViewById(R.id.b_eli);
        }
    }


    private void deleteProducto(String id) { //BORRAR
        miFirestore.collection("productos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
