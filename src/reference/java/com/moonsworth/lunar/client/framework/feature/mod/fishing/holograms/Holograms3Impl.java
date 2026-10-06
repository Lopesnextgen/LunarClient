package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import lombok.Generated;

public class Holograms3Impl extends Holograms3_5 {
   private final int field2;
   private final int field3;
   private final int field4;
   private final int field5;

   @Override
   public void method1(Holograms4Iterator var1) {
      var1.method30().method17(this.field3);
      var1.method30().method10(this.field5);
   }

   @Override
   public void method2(Holograms4Iterator var1) {
      var1.method30().method17(this.field2);
      var1.method30().method10(this.field4);
   }

   @Generated
   public Holograms3Impl(int var1, int value, int value2, int value3) {
      this.field2 = var1;
      this.field3 = value;
      this.field4 = value2;
      this.field5 = value3;
   }
}
