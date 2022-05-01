package io.github.musicdoc.app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.github.musicdoc.app.song.Song;
import io.github.musicdoc.app.song.SongLoader;
import io.github.musicdoc.app.song.SongModel;
import io.github.musicdoc.app.song.tag.TagLoader;
import io.github.musicdoc.app.song.tag.TagModel;

public class MusicDocViewModel extends ViewModel {

  private MutableLiveData<SongModel> songModel;

  private MutableLiveData<TagModel> tagModel;

  private final MutableLiveData<Song> selectedSong;

  private final MutableLiveData<String> search;

  public MusicDocViewModel() {
    this.selectedSong = new MutableLiveData<>();
    this.search = new MutableLiveData<>();
  }

  public LiveData<SongModel> getSongModelData() {
    if (this.songModel == null) {
      this.songModel = new MutableLiveData<>();
      SongLoader loader = new SongLoader();
      this.songModel.setValue(loader.load());
    }
    return this.songModel;
  }

  public LiveData<TagModel> getTagModelData() {
    if (this.tagModel == null) {
      this.tagModel = new MutableLiveData<>();
      TagLoader loader = new TagLoader();
      this.tagModel.setValue(loader.load());
    }
    return this.tagModel;
  }

  public LiveData<Song> getSelectedSongData() {
    return this.selectedSong;
  }

  public Song getSelectedSong() {
    return this.selectedSong.getValue();
  }

  public void setSelectedSong(Song song) {
    this.selectedSong.setValue(song);
  }

  public LiveData<String> getSearchData() {
    return this.search;
  }

  public String getSearch() {
    return this.search.getValue();
  }

  public void setSearch(String searchText) {
    this.search.setValue(searchText);
  }
}
