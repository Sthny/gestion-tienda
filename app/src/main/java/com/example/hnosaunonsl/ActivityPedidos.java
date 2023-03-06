package com.example.hnosaunonsl;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ActivityPedidos extends AppCompatActivity {
    Button boton;
    EditText prueba1, prueba2;
    FirebaseFirestore miFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        boton = findViewById((R.id.botonprueba));
        prueba1 = findViewById(R.id.prueba1);
        prueba2 = findViewById(R.id.prueba2);

        miFirestore = FirebaseFirestore.getInstance();

        boton.setOnClickListener(v -> {
           DocumentReference docRef = miFirestore.collection("pedidos").document("enfin");
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot doc = task.getResult();
                        if (doc.exists()) {
                            String texton = doc.getString("su");
                            Log.d(TAG, "HOLA, DocumentSnapshot data: " + doc.getData()+texton);
                        } else {
                            Log.d(TAG, "HOLA No such document");
                        }
                    } else {
                        Log.d(TAG, " HOLA get failed with ", task.getException());
                    }
                }
            });
        });
    }
}