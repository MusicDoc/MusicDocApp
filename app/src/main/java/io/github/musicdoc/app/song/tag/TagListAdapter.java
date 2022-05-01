package io.github.musicdoc.app.song.tag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import io.github.musicdoc.app.MusicDocListAdapter;
import io.github.musicdoc.app.R;
import io.github.musicdoc.app.song.Song;
import io.github.musicdoc.app.song.SongModel;
import io.github.musicdoc.app.song.list.SongListAdapter;

public class TagListAdapter extends MusicDocListAdapter<TagListAdapter.ViewHolder> {

  private TagModel model;

  private List<String> tags;

  private Set<String> selectedTags;

  public TagListAdapter(TagModel model, Set<String> selectedTags) {
    this.model = model;
    this.tags = model.getTagList();
    this.selectedTags = selectedTags;
  }

  public void setSelectedTags(Collection<String> selectedTags) {
    int size = this.tags.size();
    for (int i = 0; i < size; i++) {
      String tag = this.tags.get(i);
      boolean newSelection = selectedTags.contains(tag);
      boolean oldSelectionm = this.selectedTags.contains(tag);
      if (newSelection != oldSelectionm) {
        notifyItemChanged(i);
      }
    }
  }

  @NonNull
  @Override
  public TagListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View v = inflater.inflate(R.layout.tag_list_row, parent, false);
    return new TagListAdapter.ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull TagListAdapter.ViewHolder holder, int position) {
    String tag = this.tags.get(position);
    boolean selected = this.selectedTags.contains(tag);
    int color = getBackgroundColor(position, selected);
    holder.itemView.setBackgroundResource(color);
    holder.setTag(tag);
  }

  public Set<String> getSelectedTags() {
    return this.selectedTags;
  }

  @Override
  public int getItemCount() {
    return this.tags.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private String tag;
    private CheckBox checkBox;
    private TextView text;
    private View layout;

    public ViewHolder(View v) {
      super(v);
      this.layout = v;
      this.checkBox = (CheckBox) v.findViewById(R.id.taglist_checkbox);
      this.checkBox.setOnClickListener(view -> {onToggleSelection(false);});
      this.text = (TextView) v.findViewById(R.id.taglist_name);
      this.itemView.setOnClickListener(view -> {onToggleSelection(true);});
    }

    private void onToggleSelection(boolean toggle) {

      boolean selected = this.checkBox.isChecked();
      if (toggle) {
        selected = !selected;
        this.checkBox.setChecked(selected);
      }
      int color = getBackgroundColor(getAdapterPosition(), selected);
      this.itemView.setBackgroundResource(color);
      if (selected) {
        selectedTags.add(this.tag);
      } else {
        selectedTags.remove(this.tag);
      }
      // fireOnSelect(this.song);
    }

    public void setTag(String tag) {
      this.tag = tag;
      this.text.setText(tag);
    }
  }

}
