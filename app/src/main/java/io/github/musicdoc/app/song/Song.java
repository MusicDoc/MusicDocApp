package io.github.musicdoc.app.song;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Song {

  private String title;

  private String artist;

  private Set<String> tags;

  private String searchText;

  public Song() {
    super();
    this.tags = new HashSet<>();
  }

  public Song(String title, String artist) {
    this();
    this.title = title;
    this.artist = artist;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
    this.searchText = null;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
    this.searchText = null;
  }

  public Set<String> getTags() {
    return this.tags;
  }

  public String getTagsAsString() {
    return getTagsAsString(this.tags);
  }

  public static String getTagsAsString(Set<String> tags) {
    StringBuilder sb = new StringBuilder();
    for (String tag : tags) {
      if (sb.length() > 0) {
        sb.append(",");
      }
      sb.append(tag);
    }
    return sb.toString();
  }
  public void setTagsAsString(String tags) {
    this.tags.clear();
    if (tags == null) {
      return;
    }
    for (String tag : tags.split(",")) {
      tag = tag.trim();
      if (!tag.isEmpty()) {
        this.tags.add(tag);
      }
    }
  }

  public String getSearchText() {
    if (this.searchText == null) {
      this.searchText = normalize(this.title + this.artist);
    }
    return this.searchText;
  }

  public static String normalize(String string) {
    return string.toLowerCase(Locale.ROOT);
  }
}
