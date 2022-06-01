package io.github.musicdoc.app;

import android.app.AlertDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import io.github.musicdoc.app.nav.NavigateBackHandler;
import io.github.musicdoc.app.nav.NavigateBackHandlerSupport;
import io.github.musicdoc.app.song.Song;

public abstract class MusicDocFragment extends Fragment {

  private MusicDocViewModel viewModel;

  protected MusicDocViewModel getViewModel() {
    if (this.viewModel == null) {
      this.viewModel = new ViewModelProvider(getActivity()).get(MusicDocViewModel.class);
    }
    return this.viewModel;
  }

  protected AppCompatActivity getSupportActivity() {
    return (AppCompatActivity) getActivity();
  }

  protected FragmentManager getSupportFragmentManager() {
    return getActivity().getSupportFragmentManager();
  }

  protected ActionBar getSupportActionBar() {
    return getSupportActivity().getSupportActionBar();
  }

  protected NavController getNavController() {
    return NavHostFragment.findNavController(this);
  }

  protected void navigate(int navId) {
    getNavController().navigate(navId);
  }

  protected void navigateBack() {
    getNavController().navigateUp();
  }

  protected AppCompatActivity getCompatActivity() {
    return (AppCompatActivity) super.getActivity();
  }

  protected void setNavigateBackHandler(NavigateBackHandler handler) {
    NavigateBackHandlerSupport support = (NavigateBackHandlerSupport) getActivity();
    support.setNavigateBackHandler(handler);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    this.viewModel = null;
  }

  protected void setSongToActionBar(Song song) {
    ActionBar actionBar = getSupportActionBar();
    String title = song.getTitle();
    String subtitle = song.getArtist();
    actionBar.setTitle(title);
    actionBar.setSubtitle(subtitle);
  }

  protected void showAlertDialog(int title, int message, final Runnable positiveAction) {
    showAlertDialog(title, message, android.R.string.yes, positiveAction, android.R.string.no);
  }

  protected void showAlertDialog(int title, int message, int positiveButton, final Runnable positiveAction) {
    showAlertDialog(title, message, positiveButton, positiveAction, R.string.cancel);
  }

  protected void showAlertDialog(int title, int message, int positiveButton, final Runnable positiveAction, int negativeButton) {
    showAlertDialog(title, message, positiveButton, positiveAction, negativeButton, () -> {});
  }

  protected void showAlertDialog(int title, int message, int positiveButton, final Runnable positiveAction, int negativeButton, final Runnable negativeAction) {
    showAlertDialog(title, message, positiveButton, positiveAction, negativeButton, negativeAction, android.R.drawable.ic_dialog_alert);
  }

  protected void showAlertDialog(int title, int message, int positiveButton, final Runnable positiveAction, int negativeButton, final Runnable negativeAction, int icon) {
    new AlertDialog.Builder(getContext())
      .setTitle(title)
      .setMessage(message)
      .setIcon(icon)
      .setPositiveButton(positiveButton, (d, b) -> positiveAction.run())
      .setNegativeButton(negativeButton, (d, b) -> negativeAction.run()).show();
  }

}
