package io.github.musicdoc.app.nav;

/**
 * Handler for back navigation events - both from the action bar as well as from the back button of the device.
 */
public interface NavigateBackHandler {

  /**
   * @return {@code true} if the back navigation should continue, {@code false} otherwise (the back
   * navigation has been handled and further processing should be aborted at this point).
   */
  boolean onNavigateBack();

}
