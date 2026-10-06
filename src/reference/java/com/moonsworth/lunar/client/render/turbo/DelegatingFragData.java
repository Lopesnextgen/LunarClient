package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import lombok.Generated;

public class DelegatingFragData implements FragData {
   private final FragData field1;
   private final long field2;

   public DelegatingFragData(FragData holograms_3) {
      this.field1 = holograms_3;
      this.field2 = System.currentTimeMillis();
   }

   @Generated
   public FragData method4() {
      return this.field1;
   }

   @Generated
   public long method5() {
      return this.field2;
   }

   @Generated
   @Override
   public int getCount() {
      return this.method4().getCount();
   }

   @Generated
   @Override
   public AxisAlignedBBBridge method1() {
      return this.method4().method1();
   }

   @Generated
   @Override
   public boolean method2() {
      return this.method4().method2();
   }

   @Generated
   @Override
   public TransparencyLayerMap method3() {
      return this.method4().method3();
   }
}
