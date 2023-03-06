package com.example.hnosaunonsl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstMenu extends AppCompatActivity {

   TextView bm_proveedores;
   TextView bm_productos;
    TextView bm_pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_menu);

        bm_proveedores = findViewById(R.id.m_proveedores);
        bm_productos = findViewById(R.id.m_productos);
        bm_pedidos = findViewById(R.id.m_pedidos);

        bm_proveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirActivity = new Intent(FirstMenu.this, MainActivity.class);
                startActivity(abrirActivity);
            }
        });

        bm_productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirActivity = new Intent(FirstMenu.this, ActivityProductos.class);
                startActivity(abrirActivity);
            }
        });

        bm_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirActivity = new Intent(FirstMenu.this, ActivityPedidos.class);
                startActivity(abrirActivity);
            }
        });
    }
}