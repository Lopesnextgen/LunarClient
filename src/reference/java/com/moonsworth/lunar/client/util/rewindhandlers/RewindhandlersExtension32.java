package com.moonsworth.lunar.client.util.rewindhandlers;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import lombok.Generated;
import org.jetbrains.annotations.Range;

public class RewindhandlersExtension32 implements RewindhandlersExtension3 {
   private final int field1;
   private final Gui2Extension field2;
   private final int field3;

   @Override
   public boolean method14() {
      return true;
   }

   @Override
   public @Range(from = 1L, to = 100L) int method11() {
      return this.field3;
   }

   @Override
   public int method1(float var1) {
      var1 = LcuiScreen.method151().getScaledWidth() + LcuiScreen.method151().getScaledHeight() - var1;
      return this.field2.color().apply(var1, this);
   }

   @Generated
   @Override
   public int getColor() {
      return this.field1;
   }

   @Generated
   public Gui2Extension method9() {
      return this.field2;
   }

   @Generated
   public int method13() {
      return this.field3;
   }

   @Generated
   RewindhandlersExtension32(int var1, Gui2Extension gui2, int value) {
      this.field1 = var1;
      this.field2 = gui2;
      this.field3 = value;
   }
}
