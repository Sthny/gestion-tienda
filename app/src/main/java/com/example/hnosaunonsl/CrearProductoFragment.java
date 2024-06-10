package com.example.hnosaunonsl;


import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CrearProductoFragment extends DialogFragment {
    String id_producto;
    Button bagregarF;
    EditText nombre, precio;
    TextView caducidad;
    ImageView foto;

    private FirebaseFirestore miFirestore;

    /*StorageReference storageReference; //PARA LAS FOTOS
    String storage_path = "productos/*";*/


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_producto = getArguments().getString("id_producto");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_producto, container, false);

        miFirestore = FirebaseFirestore.getInstance();

        bagregarF = v.findViewById((R.id.bAgregarF));
        nombre = v.findViewById(R.id.editTextTextPersonName);
        precio = v.findViewById(R.id.editTextTextPersonName2);
        caducidad = v.findViewById(R.id.textCalendario);
        foto = v.findViewById(R.id.imageViewfrag);//FOTOGRAFIA

        if (id_producto == null || id_producto == "") {
            bagregarF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String n = nombre.getText().toString().trim();
                        String c = caducidad.getText().toString().trim();
                        double p = Double.parseDouble(precio.getText().toString().trim());



                        if (n.isEmpty() || c.isEmpty()) {
                            Toast.makeText(getContext(), "Ingresa los datos", Toast.LENGTH_LONG).show();
                        } else {
                            enviarProductos(n, p, c);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Ingresa el precio", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            obtenerProductos();
            bagregarF.setText("Actualizar");
            bagregarF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String n = nombre.getText().toString().trim();
                        Double p = Double.parseDouble(precio.getText().toString().trim());
                        String c = caducidad.getText().toString().trim();

                        miFirestore = FirebaseFirestore.getInstance();

                        if (n.isEmpty() || c.isEmpty()) {
                            Toast.makeText(getContext(), "Ingresa los datos", Toast.LENGTH_LONG).show();
                        } else {
                            actualizarProductos(n, p, c);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Ingresa el precio", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        caducidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        caducidad.setText(fecha);
                    }
                }, anio, mes, dia);
                dpd.show();
            }
        });
        return v;

    }


    private void enviarProductos(String n, Double p, String c) {
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

    private void actualizarProductos(String n, Double p, String c) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", n);
        map.put("precio", p);
        map.put("caducidad", c);

        miFirestore.collection("productos").document(id_producto).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    private void obtenerProductos() {
        miFirestore.collection("productos").document(id_producto).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String n = documentSnapshot.getString("nombre");
                Double p = documentSnapshot.getDouble("precio");
                String c = documentSnapshot.getString("caducidad");
                String fp = new Double(p).toString();
                nombre.setText(n);
                precio.setText(fp);
                caducidad.setText(c);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
