package com.moonsworth.lunar.client.framework.feature.mod;

import java.awt.Color;

public enum GuiTheme {
   DEFAULT_LIGHT(-3750202),
   DEFAULT_DARK(-12566464),
   DARK_BLUE(-13617840);

   public final int mainColor;
   public final int searchNoMatchOverlay;
   public final int panelBackground;
   public final int panelBorderRight;
   public final int panelBorderLeft;
   public final int panelBorderOut;
   public final int insetBackground;
   public final int insetBorderRight;
   public final int insetBorderLeft;

   GuiTheme(int value) {
      Color color4 = new Color(value);
      this.mainColor = value;
      this.searchNoMatchOverlay = -1728053248;
      this.panelBorderOut = -16777216;
      this.panelBackground = color4.getRGB();
      this.panelBorderLeft = color4.brighter().getRGB();
      this.panelBorderRight = color4.darker().darker().darker().getRGB();
      this.insetBackground = color4.darker().getRGB();
      this.insetBorderLeft = color4.darker().darker().darker().getRGB();
      this.insetBorderRight = color4.brighter().getRGB();
   }
}
