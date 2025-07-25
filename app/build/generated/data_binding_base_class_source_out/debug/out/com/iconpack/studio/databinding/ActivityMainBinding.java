// Generated by view binder compiler. Do not edit!
package com.iconpack.studio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.iconpack.studio.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView appTitle;

  @NonNull
  public final Button applyButton;

  @NonNull
  public final GridView iconGrid;

  private ActivityMainBinding(@NonNull LinearLayout rootView, @NonNull TextView appTitle,
      @NonNull Button applyButton, @NonNull GridView iconGrid) {
    this.rootView = rootView;
    this.appTitle = appTitle;
    this.applyButton = applyButton;
    this.iconGrid = iconGrid;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_title;
      TextView appTitle = ViewBindings.findChildViewById(rootView, id);
      if (appTitle == null) {
        break missingId;
      }

      id = R.id.apply_button;
      Button applyButton = ViewBindings.findChildViewById(rootView, id);
      if (applyButton == null) {
        break missingId;
      }

      id = R.id.icon_grid;
      GridView iconGrid = ViewBindings.findChildViewById(rootView, id);
      if (iconGrid == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, appTitle, applyButton, iconGrid);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
