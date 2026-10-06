package com.moonsworth.lunar.client.util.rewindhandlers;

import lombok.Generated;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;

public class RewindhandlersExtension2 implements RewindhandlersExtension {
   private final int field1;

   @Override
   public int method1(float var1) {
      return this.field1;
   }

   @Generated
   @Override
   public int getColor() {
      return this.field1;
   }

   @Generated
   RewindhandlersExtension2(int var1) {
      this.field1 = var1;
   }
}
