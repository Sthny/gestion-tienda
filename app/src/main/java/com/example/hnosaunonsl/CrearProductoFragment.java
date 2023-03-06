package com.example.hnosaunonsl;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearProductoFragment extends DialogFragment {
    Button bagregarF;
    EditText nombre, precio, caducidad;
    private FirebaseFirestore miFirestore;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_producto, container, false);

        miFirestore = FirebaseFirestore.getInstance();

        bagregarF = v.findViewById((R.id.bAgregarF));
        nombre = v.findViewById(R.id.editTextTextPersonName);
        precio = v.findViewById(R.id.editTextTextPersonName2);
        caducidad = v.findViewById(R.id.editTextTextPersonName3);

        bagregarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = nombre.getText().toString().trim();
                String p = precio.getText().toString().trim();
                String c = caducidad.getText().toString().trim();

                miFirestore = FirebaseFirestore.getInstance();

                if (n.isEmpty() && p.isEmpty() && c.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresa los datos pedidos", Toast.LENGTH_LONG).show();
                } else {
                    enviarProductos(n, p, c);
                }
            }
        });

        return v;
    }

    private void enviarProductos(String n, String p, String c) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", n);
        map.put("precio", p);
        map.put("caducidad", c);

        miFirestore.collection("productos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, String.valueOf(nombre.getId()));
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
}
