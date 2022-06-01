package io.github.musicdoc.app.song.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import io.github.musicdoc.app.R;
import io.github.musicdoc.app.song.Song;

public class SongView extends View {

  public static final String G_CLEF = "\uD834\uDD1E";
  public static final String STAVE_LINES = "\uD834\uDD1A";
  public static final String NOTE_4_4 = "\uD834\uDD5D";
  public static final String NOTE_2_4 = "\uD834\uDD5E";
  public static final String NOTE_1_4 = "\uD834\uDD5F";
  public static final String NOTE_1_8 = "\uD834\uDD60";
  public static final String NOTE_1_16 = "\uD834\uDD61";
  public static final String NOTE_1_32 = "\uD834\uDD62";
  public static final String NOTE_1_64 = "\uD834\uDD63";
  public static final String NOTE_1_128 = "\uD834\uDD64";

  private Song song;

  private float zoom;

  private float scale;

  private int w;

  private int h;

  private String staveLines;

  private final Paint paint;

  private final Paint paintText;

  public SongView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    Typeface font = ResourcesCompat.getFont(getContext(), R.font.notomusic);
    //this.font = ResourcesCompat.getFont(getContext(), R.font.bravura);
    this.paint = new Paint();
    this.paint.setAntiAlias(true);
    this.paint.setTypeface(font);
    int color = ContextCompat.getColor(context, R.color.text);
    this.paint.setColor(color);
    this.paintText = new Paint();
    this.paintText.setAntiAlias(true);
    this.paintText.setColor(color);
    setZoom(1);
  }

  public void setZoom(float zoom) {
    this.zoom = zoom;
    this.scale = 0;
    this.staveLines = null;
  }

  private float getScale() {
    if (this.scale == 0) {
      float density = getResources().getDisplayMetrics().density;
      float ratio = ((float) this.w) / 1024.0F;
      this.scale = zoom * density * ratio;
      this.paint.setTextSize(32 * this.scale);
      this.paintText.setTextSize(16 * this.scale);
    }
    return this.scale;
  }

  public void setSong(Song song) {
    this.song = song;
  }

  private String getStaveLines() {
    if (this.staveLines == null) {
      Rect staveLineSize = new Rect();
      this.paint.getTextBounds(STAVE_LINES, 0, 2, staveLineSize);
      int count = this.w / staveLineSize.width();
      StringBuilder sb = new StringBuilder(count);
      while (count > 0) {
        sb.append(STAVE_LINES);
        count--;
      }
      this.staveLines = sb.toString();
    }
    return this.staveLines;
  }

  private void drawStave(Canvas canvas, float y) {
    this.paint.setLetterSpacing(-0.1F);
    float x = 0;
    canvas.drawText(getStaveLines(), x, y, this.paint);
    this.paint.setLetterSpacing(0);
    x += getScale() * 10;
    canvas.drawText(G_CLEF, x, y, this.paint);
    x += getScale() * 40;
    String[] glyphs = new String[] {NOTE_4_4, NOTE_2_4, NOTE_1_4, NOTE_1_8, NOTE_1_16, NOTE_1_32, NOTE_1_64, NOTE_1_128};
    for (int i = 0; i < glyphs.length; i++) {
      canvas.drawText(glyphs[i], x, y - (i * getScale() * 4.0F), this.paint);
      x += getScale() * 20;
    }
  }

  private float getStaveHeight() {
    return this.paint.descent() + this.paint.getTextSize();
  }

  private float getTextHeight() {
    return this.paintText.descent() + this.paintText.getTextSize();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    float s = getScale();
    float y = getPaddingTop();
    float vSpace = 10 * s;
    y += getStaveHeight() + vSpace;
    drawStave(canvas, y);
    y += getTextHeight() + vSpace;
    canvas.drawText(this.song.getTitle(), 50 * s, y, this.paintText);
    y += getStaveHeight() + vSpace;
    drawStave(canvas, y);
    y += getTextHeight() + vSpace;
    canvas.drawText(this.song.getArtist(), 50 * s, y, this.paintText);
  }

  private int getTextWidth(String text) {
    Rect rect = new Rect();
    this.paint.getTextBounds(text, 0, text.length(), rect);
    return rect.width();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    this.w = MeasureSpec.getSize(widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
    // TODO compute according to the song data
    this.h = MeasureSpec.getSize(heightMeasureSpec) + getPaddingBottom() + getPaddingTop();
    h = 3*h;
    setMeasuredDimension(this.w, this.h);
  }
}
