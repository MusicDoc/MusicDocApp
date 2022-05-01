package io.github.musicdoc.app;

import androidx.recyclerview.widget.RecyclerView;

import io.github.musicdoc.app.song.list.SongListAdapter;

public abstract class MusicDocListAdapter<H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {


  protected static int getBackgroundColor(int position) {
    return getBackgroundColor(position, false);
  }

    protected static int getBackgroundColor(int position, boolean selected) {
    if (selected) {
      if ((position % 2) == 0) {
        return R.color.list_background_even_selected;
      } else {
        return R.color.list_background_odd_selected;
      }
    } else {
      if ((position % 2) == 0) {
        return R.color.list_background_even;
      } else {
        return R.color.list_background_odd;
      }
    }
  }
}