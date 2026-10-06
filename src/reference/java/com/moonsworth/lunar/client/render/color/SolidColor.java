package com.moonsworth.lunar.client.render.color;

import lombok.Generated;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;

public class SolidColor implements RewindhandlersExtension {
   private final int field1;

   @Override
   public int method1(float value) {
      return this.field1;
   }

   @Generated
   @Override
   public int getColor() {
      return this.field1;
   }

   @Generated
   SolidColor(int value) {
      this.field1 = value;
   }
}
