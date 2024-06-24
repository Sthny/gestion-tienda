package com.example.gestiontienda;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearProveedorFragment extends DialogFragment {
    String id_proveedor;
    Button bagregarF;
    EditText nombre, cif, correo;

    private FirebaseFirestore miFirestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_proveedor = getArguments().getString("id_proveedor");
        }
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

        if (id_proveedor == null || id_proveedor == "") {
            bagregarF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n = nombre.getText().toString().trim();
                    String ci = cif.getText().toString().trim();
                    String c = correo.getText().toString().trim();

                    miFirestore = FirebaseFirestore.getInstance();

                    if (n.isEmpty() && ci.isEmpty() && c.isEmpty()) {
                        Toast.makeText(getContext(), "Ingresa los datos", Toast.LENGTH_LONG).show();
                    } else if (n.isEmpty()) {
                        Toast.makeText(getContext(), "Campo nombre es obligatorio", Toast.LENGTH_LONG).show();
                    } else if (ci.isEmpty() || !ci.matches("^([ABCDEFGHJNPQRSUVWabcdefghjnpqrsuvw])([0-9]{7})([0-9A-Ja])|(^[0-9]{8}[A-Za-z])$")) { //NIFyDNI
                        Toast.makeText(getContext(), "Obligatorio NIF con formato correcto", Toast.LENGTH_LONG).show();
                    } else if (c.isEmpty() || !c.matches("^[\\w._-]+@([\\w.-]+\\.)+[A-Za-z]{2,4}$")) {
                        Toast.makeText(getContext(), "Obligatorio correo con formato correcto", Toast.LENGTH_LONG).show(); //0-9A-Ja
                    } else {
                        enviarProveedores(n, ci, c);
                    }
                }
            });
        } else { //BOTON ACTUALIZAR.fragment dentro del boton EDITAR
            obtenerProveedores();
            bagregarF.setText("Actualizar");
            bagregarF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n = nombre.getText().toString().trim();
                    String ci = cif.getText().toString().trim();
                    String c = correo.getText().toString().trim();

                    miFirestore = FirebaseFirestore.getInstance();

                    if (n.isEmpty() && ci.isEmpty() && c.isEmpty()) {
                        Toast.makeText(getContext(), "Ingresa los datos", Toast.LENGTH_LONG).show();
                    } else if (n.isEmpty()) {
                        Toast.makeText(getContext(), "Campo nombre es obligatorio", Toast.LENGTH_LONG).show();
                    }else if (ci.isEmpty() || !ci.matches("^([ABCDEFGHJNPQRSUVWabcdefghjnpqrsuvw])([0-9]{7})([0-9A-Ja])|(^[0-9]{8}[A-Za-z])$")) { //NIFyDNI
                            Toast.makeText(getContext(), "Obligatorio NIF con formato correcto", Toast.LENGTH_LONG).show();
                    } else if (c.isEmpty() || !c.matches("^[\\w._-]+@([\\w.-]+\\.)+[A-Za-z]{2,4}$")) {
                        Toast.makeText(getContext(), "Obligatorio correo con formato correcto", Toast.LENGTH_LONG).show();
                    } else {
                        actualizarProveedores(n, ci, c);
                    }
                }
            });
        }

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

    private void actualizarProveedores(String n, String ci, String c) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", n);
        map.put("cif", ci);
        map.put("correo", c);

        miFirestore.collection("proveedores").document(id_proveedor).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    private void obtenerProveedores() {
        miFirestore.collection("proveedores").document(id_proveedor).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String no = documentSnapshot.getString("nombre");
                String ci = documentSnapshot.getString("cif");
                String co = documentSnapshot.getString("correo");
                nombre.setText(no);
                cif.setText(ci);
                correo.setText(co);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });

    }
}