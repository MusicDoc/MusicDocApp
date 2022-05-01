package io.github.musicdoc.app.song.edit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.HashSet;
import java.util.Set;

import io.github.musicdoc.app.MusicDocFragment;
import io.github.musicdoc.app.MusicDocViewModel;
import io.github.musicdoc.app.R;
import io.github.musicdoc.app.databinding.FragmentSongEditBinding;
import io.github.musicdoc.app.song.Song;
import io.github.musicdoc.app.song.add.SongAddDialogFragment;
import io.github.musicdoc.app.song.tag.TagEditorDialogFragment;
import io.github.musicdoc.app.song.view.SongViewFragment;

public class SongEditFragment extends MusicDocFragment {

  private FragmentSongEditBinding binding;

  private Song song;

  private Set<String> tags;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    binding = FragmentSongEditBinding.inflate(inflater, container, false);
    binding.editTags.setOnClickListener(this::onClickTags);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    inflater.inflate(R.menu.menu_edit, menu);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setHomeAsUpIndicator(R.drawable.ic_cancel);

    MenuItem saveItem = menu.findItem(R.id.edit_bar_save);
    for (int i = menu.size() - 1; i >= 0; i--) {
      MenuItem item = menu.getItem(i);
      if (item != saveItem) {
        item.setVisible(false);
      }
    }
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.edit_bar_save) {
      this.song.setTitle(binding.editTitle.getText().toString());
      this.song.setArtist(binding.editArtist.getText().toString());
      this.song.getTags().clear();
      this.song.getTags().addAll(this.tags);
      getViewModel().setSelectedSong(this.song);
      navigateBack();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onResume() {
    super.onResume();
    this.song = getViewModel().getSelectedSong();
    if (this.song == null) {
      this.song = new Song();
    }
    binding.editTitle.setText(this.song.getTitle());
    binding.editArtist.setText(this.song.getArtist());
    binding.editTags.setText(this.song.getTagsAsString());
    if (this.tags == null) {
      this.tags = new HashSet<>();
    } else {
      this.tags.clear();
    }
    this.tags.addAll(this.song.getTags());
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  private void onClickTags(View view) {
    if (this.tags == null) {
      return;
    }
    TagEditorDialogFragment tagEditor = new TagEditorDialogFragment(this.tags);
    tagEditor.setOnDismissListener(this::onCloseTagEditor);
    tagEditor.show(getSupportFragmentManager(), "");
  }

  private void onCloseTagEditor(DialogInterface dialogInterface) {
    binding.editTags.setText(Song.getTagsAsString(this.tags));
  }

}