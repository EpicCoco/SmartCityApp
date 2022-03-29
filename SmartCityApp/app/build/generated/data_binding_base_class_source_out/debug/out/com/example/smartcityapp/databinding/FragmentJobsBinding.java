// Generated by view binder compiler. Do not edit!
package com.example.smartcityapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.smartcityapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentJobsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText editTextTextPersonName;

  @NonNull
  public final Button jobsButton;

  @NonNull
  public final TextView t;

  private FragmentJobsBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText editTextTextPersonName, @NonNull Button jobsButton, @NonNull TextView t) {
    this.rootView = rootView;
    this.editTextTextPersonName = editTextTextPersonName;
    this.jobsButton = jobsButton;
    this.t = t;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentJobsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentJobsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_jobs, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentJobsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editTextTextPersonName;
      EditText editTextTextPersonName = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName == null) {
        break missingId;
      }

      id = R.id.jobsButton;
      Button jobsButton = ViewBindings.findChildViewById(rootView, id);
      if (jobsButton == null) {
        break missingId;
      }

      id = R.id.t;
      TextView t = ViewBindings.findChildViewById(rootView, id);
      if (t == null) {
        break missingId;
      }

      return new FragmentJobsBinding((ConstraintLayout) rootView, editTextTextPersonName,
          jobsButton, t);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}