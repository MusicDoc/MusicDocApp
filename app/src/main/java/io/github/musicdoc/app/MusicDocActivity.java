package io.github.musicdoc.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import io.github.musicdoc.app.databinding.ActivityMusicDocBinding;
import io.github.musicdoc.app.nav.NavigateBackHandler;
import io.github.musicdoc.app.nav.NavigateBackHandlerSupport;
import io.github.musicdoc.app.song.Song;
import io.github.musicdoc.app.song.add.SongAddDialogFragment;
import io.github.musicdoc.app.song.list.SongListFragment;
import io.github.musicdoc.app.song.view.SongViewFragment;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MusicDocActivity extends AppCompatActivity implements NavigateBackHandlerSupport {

  private AppBarConfiguration appBarConfiguration;
  private ActivityMusicDocBinding binding;
  private MusicDocViewModel viewModel;
  private NavigateBackHandler navigateBackHandler;

  private MusicDocViewModel getViewModel() {
    if (this.viewModel == null) {
      this.viewModel = new ViewModelProvider(this).get(MusicDocViewModel.class);
    }
    return this.viewModel;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityMusicDocBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    setSupportActionBar(binding.toolbar);

    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    customizeSearch(menu);
    return true;
  }

  private void customizeSearch(Menu menu) {
    MenuItem searchItem = menu.findItem(R.id.app_bar_search);
    final SearchView searchView = (SearchView) searchItem.getActionView();
    searchView.setQueryHint("Search View Hint");
    searchView.setOnSearchClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        if ((fragment != null)) {
          NavController navController = NavHostFragment.findNavController(fragment);
          navController.navigateUp();
          navController.navigate(R.id.action_nav_to_song_list);
        }
      }
    });
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

      @Override
      public boolean onQueryTextChange(String newText) {

        getViewModel().setSearch(newText);
        return false;
      }

      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

    });

    getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {

      @Override
      public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentResumed(fm, f);
        boolean isSongList = f instanceof SongListFragment;
        boolean isSongView = f instanceof SongViewFragment;
        if (isSongList) {
          getSupportActionBar().setSubtitle("");
        } else {
          searchView.setQuery("", false);
          searchView.setIconified(true);
        }
      }
    }, true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.app_bar_settings) {
      navigate(R.id.action_nav_to_settings);
      return true;
    } else if (id == R.id.app_bar_add) {
      new SongAddDialogFragment().show(getSupportFragmentManager(), "");
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void navigate(int navId) {
    Fragment fragment = getSupportFragmentManager().getPrimaryNavigationFragment();
    final NavController navController = NavHostFragment.findNavController(fragment);
    navController.navigate(navId);
  }

  @Override
  public void setNavigateBackHandler(NavigateBackHandler navigateBackHandler) {
    this.navigateBackHandler = navigateBackHandler;
  }

  @Override
  public boolean onSupportNavigateUp() {
    // TODO hook callback here from fragment
    if (this.navigateBackHandler != null) {
      boolean navigateBack = this.navigateBackHandler.onNavigateBack();
      if (!navigateBack) {
        return false;
      }
    }
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    return NavigationUI.navigateUp(navController, appBarConfiguration)
      || super.onSupportNavigateUp();
  }

}