package io.github.musicdoc.app.song;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SongFilterDefault implements SongFilter {

  private final String[] tokens;

  public SongFilterDefault(String search) {
    super();
    this.tokens = Song.normalize(search).split(" ");
  }

  @Override
  public boolean accept(Song song) {
    String searchText = song.getSearchText();
    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      if (!searchText.contains(token)) {
        return false;
      }
    }
    return true;
  }

}

