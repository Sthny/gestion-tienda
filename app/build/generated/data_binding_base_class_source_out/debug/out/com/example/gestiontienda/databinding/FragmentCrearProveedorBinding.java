// Generated by view binder compiler. Do not edit!
package com.example.gestiontienda.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.gestiontienda.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCrearProveedorBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button bAgregarF;

  @NonNull
  public final EditText editTextTextPersonName;

  @NonNull
  public final EditText editTextTextPersonName2;

  @NonNull
  public final EditText editTextTextPersonName3;

  @NonNull
  public final LinearLayout linearLayout;

  private FragmentCrearProveedorBinding(@NonNull CardView rootView, @NonNull Button bAgregarF,
      @NonNull EditText editTextTextPersonName, @NonNull EditText editTextTextPersonName2,
      @NonNull EditText editTextTextPersonName3, @NonNull LinearLayout linearLayout) {
    this.rootView = rootView;
    this.bAgregarF = bAgregarF;
    this.editTextTextPersonName = editTextTextPersonName;
    this.editTextTextPersonName2 = editTextTextPersonName2;
    this.editTextTextPersonName3 = editTextTextPersonName3;
    this.linearLayout = linearLayout;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCrearProveedorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCrearProveedorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_crear_proveedor, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCrearProveedorBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bAgregarF;
      Button bAgregarF = ViewBindings.findChildViewById(rootView, id);
      if (bAgregarF == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName;
      EditText editTextTextPersonName = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName2;
      EditText editTextTextPersonName2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName2 == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName3;
      EditText editTextTextPersonName3 = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName3 == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      return new FragmentCrearProveedorBinding((CardView) rootView, bAgregarF,
          editTextTextPersonName, editTextTextPersonName2, editTextTextPersonName3, linearLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}