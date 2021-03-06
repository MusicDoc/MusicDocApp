package io.github.musicdoc.app.song.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import io.github.musicdoc.app.MusicDocFragment;
import io.github.musicdoc.app.MusicDocViewModel;
import io.github.musicdoc.app.R;
import io.github.musicdoc.app.databinding.FragmentSongViewBinding;
import io.github.musicdoc.app.song.Song;

public class SongViewFragment extends MusicDocFragment {

    private FragmentSongViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSongViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Song song = getViewModel().getSelectedSong();
        binding.songView.setSong(song);
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        binding.buttonEdit.setOnClickListener(this::onEdit);
    }

    private void onEdit(View view) {
        navigate(R.id.action_nav_to_song_edit);
    }

    @Override
    public void onResume() {
        setSongToActionBar(getViewModel().getSelectedSong());
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}