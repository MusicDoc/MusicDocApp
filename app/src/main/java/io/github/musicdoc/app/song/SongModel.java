package io.github.musicdoc.app.song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SongModel {

    public static final Comparator<Song> SORT_BY_TITLE = (s1, s2) -> s1.getTitle().compareTo(s2.getTitle());

    public static final Comparator<Song> SORT_BY_ARTIST = (s1, s2) -> {
        int result = s1.getArtist().compareTo(s2.getArtist());
        if (result == 0) {
            result = s1.getTitle().compareTo(s2.getTitle());
        }
        return result;
    };

    private List<Song> songs;

    private Song selectedSong;

    public SongModel() {
        this.songs = new ArrayList<>();
    }

    public void add(Song song) {
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    public List<Song> getSongs(SongFilter filter, Comparator<Song> comparator) {

        List<Song> result = new ArrayList<>(this.songs.size());
        for (Song song : this.songs) {
            if ((filter == null) || filter.accept(song)) {
                result.add(song);
            }
        }
        Collections.sort(result, comparator);
        return result;
    }

}
