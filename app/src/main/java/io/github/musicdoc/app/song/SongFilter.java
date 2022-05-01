package io.github.musicdoc.app.song;

public interface SongFilter {

    boolean accept(Song song);

    static SongFilter of(String search) {

        if ((search == null) || search.isEmpty()) {
            return s -> true;
        }
        return new SongFilterDefault(search);
    }
}
