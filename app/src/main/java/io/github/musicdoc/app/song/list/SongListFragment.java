package io.github.musicdoc.app.song.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import io.github.musicdoc.app.MusicDocFragment;
import io.github.musicdoc.app.MusicDocViewModel;
import io.github.musicdoc.app.R;
import io.github.musicdoc.app.song.Song;
import io.github.musicdoc.app.databinding.FragmentSongListBinding;
import io.github.musicdoc.app.song.SongLoader;
import io.github.musicdoc.app.song.SongModel;

public class SongListFragment extends MusicDocFragment implements SongSelectionListener {

  private FragmentSongListBinding binding;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSongListBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    binding.songListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    SongListAdapter songListAdapter = new SongListAdapter(getViewModel().getSongModelData().getValue());
    songListAdapter.setSelectionListener(this);
    binding.songListView.setAdapter(songListAdapter);
    binding.songListView.setHasFixedSize(true);
    getViewModel().getSearchData().observe(getActivity(), songListAdapter::onSearch);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }

  @Override
  public void onSelection(Song song) {
    navigate(R.id.action_nav_to_song_view);
    getViewModel().setSelectedSong(song);
  }
}