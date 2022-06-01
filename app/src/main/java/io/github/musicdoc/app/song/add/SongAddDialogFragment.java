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

import io.github.musicdoc.app.MusicDocDialogFragment;
import io.github.musicdoc.app.R;
import io.github.musicdoc.app.databinding.FragmentSongAddDialogBinding;
import io.github.musicdoc.app.databinding.FragmentSongEditBinding;

public class SongAddDialogFragment extends MusicDocDialogFragment {

  private FragmentSongAddDialogBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    binding = FragmentSongAddDialogBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    this.binding.buttonCreateSong.setOnClickListener(this::onCreateSong);
    super.onViewCreated(view, savedInstanceState);
  }

  private void onCreateSong(View view) {
    getViewModel().setCreateSong(true);
    dismiss();
    navigate(R.id.action_nav_to_song_edit);
  }
}
