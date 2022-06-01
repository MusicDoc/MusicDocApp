package io.github.musicdoc.app.nav;

/**
 * Interface for object supporting a {@link NavigateBackHandler}.
 */
public interface NavigateBackHandlerSupport {

  /**
   * @param handler the {@link NavigateBackHandler} to set. May be {@code null} to remove.
   */
  void setNavigateBackHandler(NavigateBackHandler handler);
}
