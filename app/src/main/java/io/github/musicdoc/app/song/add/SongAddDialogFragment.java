package io.github.musicdoc.app.song.add;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import io.github.musicdoc.app.R;
import io.github.musicdoc.app.databinding.FragmentSongAddDialogBinding;
import io.github.musicdoc.app.databinding.FragmentSongEditBinding;

public class SongAddDialogFragment extends DialogFragment {

  private FragmentSongAddDialogBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    binding = FragmentSongAddDialogBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

}
