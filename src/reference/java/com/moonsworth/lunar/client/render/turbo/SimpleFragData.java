package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class SimpleFragData implements FragData {
   private final int field1;
   private final AxisAlignedBBBridge field2;
   private final boolean field3;
   @Nullable
   private final TransparencyLayerMap field4;

   @Override
   public boolean method2() {
      return this.field3;
   }

   @Generated
   @Override
   public int getCount() {
      return this.field1;
   }

   @Generated
   @Override
   public AxisAlignedBBBridge method1() {
      return this.field2;
   }

   @Generated
   public boolean method4() {
      return this.field3;
   }

   @Nullable
   @Generated
   @Override
   public TransparencyLayerMap method3() {
      return this.field4;
   }

   @Generated
   public SimpleFragData(int value, AxisAlignedBBBridge axisAlignedBBBridge, boolean flag, @Nullable TransparencyLayerMap var4) {
      this.field1 = value;
      this.field2 = axisAlignedBBBridge;
      this.field3 = flag;
      this.field4 = var4;
   }
}
