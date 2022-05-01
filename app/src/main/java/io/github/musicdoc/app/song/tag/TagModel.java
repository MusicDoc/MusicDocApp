package io.github.musicdoc.app.song.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagModel {

  private final Set<String> tags;

  private final List<String> tagList;

  private final List<String> tagListView;

  public TagModel() {
    this.tags = new HashSet<>();
    this.tagList = new ArrayList<>();
    this.tagListView = Collections.unmodifiableList(this.tagList);
  }

  public void add(String tag) {
    boolean isNew = this.tags.add(tag);
    if (isNew) {
      if (this.tagList.isEmpty()) {
        this.tagList.add(tag);
      } else {
        int index = Collections.binarySearch(this.tagList, tag, String::compareTo);
        if (index < 0) {
          index = 0;
        }
        this.tagList.add(index, tag);
      }
    }
  }

  public boolean contains(String tag) {
    return this.tags.contains(tag);
  }

  public List<String> getTagList() {
    return this.tagListView;
  }
}
