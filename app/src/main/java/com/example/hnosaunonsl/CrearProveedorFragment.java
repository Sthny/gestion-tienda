package com.example.hnosaunonsl;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearProveedorFragment extends DialogFragment {
    Button bagregarF;
    EditText nombre, cif, correo;
    private FirebaseFirestore miFirestore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_proveedor, container, false);

        miFirestore = FirebaseFirestore.getInstance();

        bagregarF = v.findViewById((R.id.bAgregarF));
        nombre = v.findViewById(R.id.editTextTextPersonName);
        cif = v.findViewById(R.id.editTextTextPersonName2);
        correo = v.findViewById(R.id.editTextTextPersonName3);

        bagregarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = nombre.getText().toString().trim();
                String ci = cif.getText().toString().trim();
                String c = correo.getText().toString().trim();

                miFirestore = FirebaseFirestore.getInstance();

                if (n.isEmpty() && ci.isEmpty() && c.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresa los datos pedidos", Toast.LENGTH_LONG).show();
                } else {
                    enviarProveedores(n, ci, c);
                }
            }
        });


        return v;
    }

    private void enviarProveedores(String n, String ci, String c) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", n);
        map.put("cif", ci);
        map.put("correo", c);

        miFirestore.collection("proveedores").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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