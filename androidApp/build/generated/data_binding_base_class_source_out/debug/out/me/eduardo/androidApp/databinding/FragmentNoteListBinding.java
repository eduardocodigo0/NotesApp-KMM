// Generated by view binder compiler. Do not edit!
package me.eduardo.androidApp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import me.eduardo.androidApp.R;

public final class FragmentNoteListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton fabNewNote;

  @NonNull
  public final RecyclerView rvNotes;

  @NonNull
  public final TextView tvNoMsg;

  private FragmentNoteListBinding(@NonNull ConstraintLayout rootView,
      @NonNull FloatingActionButton fabNewNote, @NonNull RecyclerView rvNotes,
      @NonNull TextView tvNoMsg) {
    this.rootView = rootView;
    this.fabNewNote = fabNewNote;
    this.rvNotes = rvNotes;
    this.tvNoMsg = tvNoMsg;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNoteListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNoteListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_note_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNoteListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fab_new_note;
      FloatingActionButton fabNewNote = rootView.findViewById(id);
      if (fabNewNote == null) {
        break missingId;
      }

      id = R.id.rv_notes;
      RecyclerView rvNotes = rootView.findViewById(id);
      if (rvNotes == null) {
        break missingId;
      }

      id = R.id.tv_no_msg;
      TextView tvNoMsg = rootView.findViewById(id);
      if (tvNoMsg == null) {
        break missingId;
      }

      return new FragmentNoteListBinding((ConstraintLayout) rootView, fabNewNote, rvNotes, tvNoMsg);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}