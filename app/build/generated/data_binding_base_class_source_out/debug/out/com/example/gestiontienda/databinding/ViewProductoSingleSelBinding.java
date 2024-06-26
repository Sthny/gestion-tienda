// Generated by view binder compiler. Do not edit!
package com.example.gestiontienda.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.gestiontienda.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ViewProductoSingleSelBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView cad;

  @NonNull
  public final TextView nameP;

  @NonNull
  public final TextView pri;

  private ViewProductoSingleSelBinding(@NonNull CardView rootView, @NonNull TextView cad,
      @NonNull TextView nameP, @NonNull TextView pri) {
    this.rootView = rootView;
    this.cad = cad;
    this.nameP = nameP;
    this.pri = pri;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewProductoSingleSelBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewProductoSingleSelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.view_producto_single_sel, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewProductoSingleSelBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cad;
      TextView cad = ViewBindings.findChildViewById(rootView, id);
      if (cad == null) {
        break missingId;
      }

      id = R.id.nameP;
      TextView nameP = ViewBindings.findChildViewById(rootView, id);
      if (nameP == null) {
        break missingId;
      }

      id = R.id.pri;
      TextView pri = ViewBindings.findChildViewById(rootView, id);
      if (pri == null) {
        break missingId;
      }

      return new ViewProductoSingleSelBinding((CardView) rootView, cad, nameP, pri);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
