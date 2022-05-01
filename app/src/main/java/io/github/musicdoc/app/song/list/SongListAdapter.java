package io.github.musicdoc.app.song.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;
import java.util.List;

import io.github.musicdoc.app.MusicDocListAdapter;
import io.github.musicdoc.app.R;
import io.github.musicdoc.app.song.Song;
import io.github.musicdoc.app.song.SongFilter;
import io.github.musicdoc.app.song.SongModel;

public class SongListAdapter extends MusicDocListAdapter<SongListAdapter.ViewHolder> {

    private SongModel model;

    private List<Song> songs;

    private Comparator<Song> sorting;

    private SongSelectionListener selectionListener;

    public SongListAdapter(SongModel model) {
        super();
        this.model = model;
        this.sorting = SongModel.SORT_BY_TITLE;
        this.songs = this.model.getSongs(null, this.sorting);
    }

    public void add(Song song) {
        add(this.songs.size(), song);
    }

    public void add(int position, Song song) {
        songs.add(position, song);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        songs.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.song_list_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int color = getBackgroundColor(position);
        holder.itemView.setBackgroundResource(color);
        Song song = songs.get(position);
        holder.setSong(song);
    }

    public void setSelectionListener(SongSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    void fireOnSelect(Song song) {
        if (this.selectionListener == null) {
            return;
        }
        this.selectionListener.onSelection(song);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public void onSearch(String search) {
        this.songs = this.model.getSongs(SongFilter.of(search), this.sorting);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Song song;
        private TextView textTitle;
        private TextView textArtist;
        private View layout;

        public ViewHolder(View v) {
            super(v);
            this.layout = v;
            this.textTitle = (TextView) v.findViewById(R.id.songlist_title);
            this.textArtist = (TextView) v.findViewById(R.id.songlist_artist);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fireOnSelect(this.song);
        }

        public void setSong(Song song) {
            this.song = song;
            this.textTitle.setText(song.getTitle());
            this.textArtist.setText(song.getArtist());
        }
    }

}
