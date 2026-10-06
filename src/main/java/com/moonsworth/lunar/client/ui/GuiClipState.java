package com.moonsworth.lunar.client.ui;
import com.moonsworth.lunar.client.util.math.IntRectangle;

public class GuiClipState {
   public static boolean field1 = true;
   public static IntRectangle field2 = null;
   public static boolean field3 = false;

   public GuiClipState() {
   }

   public static void reset() {
      field1 = true;
      field2 = null;
      field3 = false;
   }
}
