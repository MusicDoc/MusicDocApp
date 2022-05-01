package io.github.musicdoc.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

public abstract class MusicDocDialogFragment extends DialogFragment {

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

  protected void navigate(int navId) {
    NavHostFragment.findNavController(this).navigate(navId);
  }

  protected AppCompatActivity getCompatActivity() {
    return (AppCompatActivity) super.getActivity();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    this.viewModel = null;
  }
}
