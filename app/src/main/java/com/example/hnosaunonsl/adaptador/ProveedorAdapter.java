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
import com.example.hnosaunonsl.modelo.Proveedor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProveedorAdapter extends FirestoreRecyclerAdapter<Proveedor, ProveedorAdapter.ViewHolder> {
    private FirebaseFirestore miFirestore = FirebaseFirestore.getInstance();
    Activity activity;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProveedorAdapter(@NonNull FirestoreRecyclerOptions<Proveedor> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override //LEYENDO DE LA BASE DE DATOS
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Proveedor Provee) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAbsoluteAdapterPosition());
        final String id = documentSnapshot.getId();

        holder.nombre.setText(Provee.getNombre());
        holder.cif.setText(Provee.getCif());
        holder.correo.setText(Provee.getCorreo());

        holder.b_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProveedor(id);
            }
        });
    }

    @NonNull   //MOSTRANDO
    @Override //conectando el adaptador con el view single
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_proveedor_single, parent, false);
        return new ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, cif, correo;
        ImageView b_eliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.no);
            cif = itemView.findViewById(R.id.ci);
            correo = itemView.findViewById(R.id.pre);
            b_eliminar = itemView.findViewById(R.id.b_eli);
        }
    }


    private void deleteProveedor(String id) { //BORRAR
        miFirestore.collection("proveedores").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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
