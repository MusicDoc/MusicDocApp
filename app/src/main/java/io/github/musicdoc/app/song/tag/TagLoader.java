package io.github.musicdoc.app.song.tag;

public class TagLoader {

  public TagModel load() {
    TagModel tagModel = new TagModel();
    tagModel.add("pop");
    tagModel.add("hit");
    tagModel.add("rock");
    tagModel.add("fun");
    return tagModel;
  }
}
