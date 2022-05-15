package io.github.musicdoc.app.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import io.github.musicdoc.app.R;

public class SettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.root_preferences, rootKey);
  }
}