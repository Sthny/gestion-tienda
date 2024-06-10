package com.example.hnosaunonsl.adaptador;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hnosaunonsl.CrearPedidoFragment;
import com.example.hnosaunonsl.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.example.hnosaunonsl.modelo.Pedido;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PedidoAdapter extends FirestoreRecyclerAdapter<Pedido, PedidoAdapter.ViewHolder> {
    private FirebaseFirestore miFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    FragmentManager fm;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PedidoAdapter(@NonNull FirestoreRecyclerOptions<Pedido> options, Activity activity, FragmentManager fm) {
        super(options);
        this.activity = activity;
        this.fm = fm;
    }

    @Override //LEYENDO DE LA BASE DE DATOS
    protected void onBindViewHolder(@NonNull PedidoAdapter.ViewHolder holder, int position, @NonNull Pedido Pedi) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAbsoluteAdapterPosition());
        final String id = documentSnapshot.getId(); //referencia de ID


        holder.npedido.setText(String.valueOf(Pedi.getNpedido()));
        holder.fecha.setText(Pedi.getFecha());
        holder.cantidad.setText(String.valueOf(Pedi.getCantidad()));
        holder.productoagregado.setText(Pedi.getProductoagregado());
        holder.proveedoragregado.setText(Pedi.getProveedoragregado());

        holder.b_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePedido(id);
            }
        });

        holder.b_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CrearPedidoFragment pediFrag = new CrearPedidoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id_pedido", id);
                pediFrag.setArguments(bundle);
                pediFrag.show(fm, "open fragment");
            }
        });
    }

    @NonNull   //MOSTRANDO
    @Override //conectando el adaptador con el layout view single
    public PedidoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pedido_single, parent, false);
        return new PedidoAdapter.ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView npedido, fecha, cantidad, productoagregado, proveedoragregado;
        ImageView b_eliminar, b_editar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            npedido = itemView.findViewById(R.id.np);
            fecha = itemView.findViewById(R.id.fe);
            cantidad = itemView.findViewById(R.id.can);
            productoagregado = itemView.findViewById(R.id.produ);
            proveedoragregado = itemView.findViewById(R.id.prove);
            b_eliminar = itemView.findViewById(R.id.b_eli_pro);
            b_editar = itemView.findViewById(R.id.b_edi_Pe);

        }
    }


    private void deletePedido(String id) { //BORRAR
        miFirestore.collection("pedidos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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
