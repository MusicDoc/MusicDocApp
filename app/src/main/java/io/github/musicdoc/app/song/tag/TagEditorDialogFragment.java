package io.github.musicdoc.app.song.tag;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Set;

import io.github.musicdoc.app.MusicDocDialogFragment;
import io.github.musicdoc.app.databinding.FragmentTagEditorDialogBinding;
import io.github.musicdoc.app.song.Song;
import io.github.musicdoc.app.song.list.SongListAdapter;

public class TagEditorDialogFragment extends MusicDocDialogFragment {

  private FragmentTagEditorDialogBinding binding;

  private TagListAdapter tagListAdapter;

  private Set<String> selectedTags;

  private DialogInterface.OnDismissListener dismissListener;

  public TagEditorDialogFragment(Set<String> selectedTags) {
    this.selectedTags = selectedTags;
  }

  public void setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
    this.dismissListener = dismissListener;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentTagEditorDialogBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    binding.tagListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    this.tagListAdapter = new TagListAdapter(getViewModel().getTagModelData().getValue(), this.selectedTags);
    binding.tagListView.setAdapter(this.tagListAdapter);
    binding.tagListView.setHasFixedSize(true);
    if (this.dismissListener != null) {
      getDialog().setOnDismissListener(this.dismissListener);
    }
    super.onViewCreated(view, savedInstanceState);
  }

}
