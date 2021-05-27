// Generated by view binder compiler. Do not edit!
package me.eduardo.androidApp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import me.eduardo.androidApp.R;

public final class FragmentNewNoteBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText etNewNoteBody;

  @NonNull
  public final EditText etNewNoteTitle;

  @NonNull
  public final FloatingActionButton fabSaveNote;

  private FragmentNewNoteBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText etNewNoteBody, @NonNull EditText etNewNoteTitle,
      @NonNull FloatingActionButton fabSaveNote) {
    this.rootView = rootView;
    this.etNewNoteBody = etNewNoteBody;
    this.etNewNoteTitle = etNewNoteTitle;
    this.fabSaveNote = fabSaveNote;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNewNoteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNewNoteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_new_note, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNewNoteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.et_new_note_body;
      EditText etNewNoteBody = rootView.findViewById(id);
      if (etNewNoteBody == null) {
        break missingId;
      }

      id = R.id.et_new_note_title;
      EditText etNewNoteTitle = rootView.findViewById(id);
      if (etNewNoteTitle == null) {
        break missingId;
      }

      id = R.id.fab_save_note;
      FloatingActionButton fabSaveNote = rootView.findViewById(id);
      if (fabSaveNote == null) {
        break missingId;
      }

      return new FragmentNewNoteBinding((ConstraintLayout) rootView, etNewNoteBody, etNewNoteTitle,
          fabSaveNote);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}