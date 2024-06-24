package com.example.gestiontienda;

import static android.content.ContentValues.TAG;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearPedidoFragment extends DialogFragment {

    String id_pedido, nombre_p, nombre_pv;
    Button bagregarF;
    EditText npedido, fecha, cantidad;
    TextView agregarPro, agregarProvee;

    private FirebaseFirestore miFirestore;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_pedido = getArguments().getString("id_pedido");
        }

        if (getArguments() != null) {
            nombre_p = getArguments().getString("nombre_p");
        }

        if (getArguments() != null) {
            nombre_pv = getArguments().getString("nombre_pv");
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_pedidos, container, false);

        miFirestore = FirebaseFirestore.getInstance();

        bagregarF = v.findViewById((R.id.bAgregarF));
        npedido = v.findViewById(R.id.editTextTextPersonName);
        fecha = v.findViewById(R.id.editTextTextPersonName3);
        cantidad = v.findViewById(R.id.editTextTextPersonName2);
        agregarPro = v.findViewById((R.id.bagregarPro));
        agregarProvee = v.findViewById((R.id.bagregarProvee));

        if (nombre_p != null || nombre_p != "") {
            agregarPro.setText(nombre_p);
        }

        if (nombre_pv != null || nombre_pv != "") {
            agregarProvee.setText(nombre_pv);
        }

        if (id_pedido == null || id_pedido == "") { //SI el ID  NO EXISTE agrega al editText
            bagregarF.setOnClickListener(new View.OnClickListener() { //btn_add
                @Override
                public void onClick(View v) {
                    try {
                        int n = parseInt(npedido.getText().toString().trim());
                        int c = parseInt(cantidad.getText().toString().trim());
                        String f = fecha.getText().toString().trim();
                        String p = agregarPro.getText().toString().trim();
                        String pv = agregarProvee.getText().toString().trim();


                        if (f.isEmpty() && p.isEmpty()) {
                            Toast.makeText(getContext(), "Ingresa datos", Toast.LENGTH_LONG).show();
                        } else {
                            enviarPedidos(n, c, f, p, pv);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Ingresa nº de pedido y cantidad", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            obtenerPedidos();
            bagregarF.setText("Actualizar");
            bagregarF.setOnClickListener(new View.OnClickListener() { //btn_add SI el ID existe ACTUALIZA a la BD
                @Override
                public void onClick(View v) {
                    try {
                    Integer n = parseInt(npedido.getText().toString().trim());
                    Integer c = parseInt(cantidad.getText().toString().trim());
                    String f = fecha.getText().toString().trim();
                    String p = agregarPro.getText().toString().trim();
                    String pv = agregarProvee.getText().toString().trim();

                    if (f.isEmpty() && p.isEmpty()) {
                        Toast.makeText(getContext(), "Ingresa datos pedidos", Toast.LENGTH_LONG).show();
                    } else {
                        actualizarPedidos(n, c, f, p, pv);
                    }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Ingresa nº de pedido y cantidad", Toast.LENGTH_LONG).show();
                    }
                }

            });
        }

        agregarPro.setOnClickListener(new View.OnClickListener() { //agregar producto
            @Override
            public void onClick(View v) {
                Intent abrirActivity = new Intent(getActivity(), ActivityProductosSELECCIONAR.class);
                startActivity(abrirActivity);
            }
        });

        return v;
    }

    private void enviarPedidos(Integer n, Integer c, String f, String p, String pv) { //postpet
        Map<String, Object> map = new HashMap<>();
        map.put("npedido", n);
        map.put("cantidad", c);
        map.put("fecha", f);
        map.put("productoagregado", p);
        map.put("proveedoragregado", pv);

        miFirestore.collection("pedidos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, String.valueOf(npedido.getId()));
                getDialog().dismiss();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error al añadir el documento", e);
                    }
                });
    }

    private void actualizarPedidos(Integer n, Integer c, String f, String p, String pv) {
        Map<String, Object> map = new HashMap<>();
        map.put("npedido", n);
        map.put("cantidad", c);
        map.put("fecha", f);
        map.put("productoagregado", p);
        map.put("proveedoragregado", pv);

        miFirestore.collection("pedidos").document(id_pedido).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "Actualizado correctamente", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void obtenerPedidos() {
        miFirestore.collection("pedidos").document(id_pedido).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Integer np = documentSnapshot.getLong("npedido").intValue();
                Integer can = documentSnapshot.getLong("cantidad").intValue();
                String f = documentSnapshot.getString("fecha");
                String pa = documentSnapshot.getString("productoagregado");
                String pva = documentSnapshot.getString("proveedoragregado");
                String fnp = new Integer(np).toString();
                String fcan = new Integer(can).toString();

                npedido.setText(fnp);
                fecha.setText(f);
                cantidad.setText(fcan);
                agregarPro.setText(pa);
                agregarProvee.setText(pva);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
