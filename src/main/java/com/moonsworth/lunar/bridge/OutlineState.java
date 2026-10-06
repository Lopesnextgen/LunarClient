package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class OutlineState {
   private final boolean field1;
   private final boolean field2;
   private final boolean field3;
   @Nullable
   private final ResourceLocationBridge field4;

   public static OutlineState method1(ResourceLocationBridge horsestats140) {
      return new OutlineState(false, false, false, horsestats140);
   }

   public static OutlineState method2(ResourceLocationBridge horsestats140, boolean flag1, boolean flag2) {
      return new OutlineState(false, flag2, flag1, horsestats140);
   }

   public static OutlineState method3() {
      return new OutlineState(true, false, false, null);
   }

   public static OutlineState method4() {
      return new OutlineState(true, true, false, null);
   }

   @Generated
   public boolean method5() {
      return this.field1;
   }

   @Generated
   public boolean method6() {
      return this.field2;
   }

   @Generated
   public boolean method7() {
      return this.field3;
   }

   @Nullable
   @Generated
   public ResourceLocationBridge method8() {
      return this.field4;
   }

   @Generated
   private OutlineState(boolean flag1, boolean flag2, boolean flag, @Nullable ResourceLocationBridge horsestats144) {
      this.field1 = flag1;
      this.field2 = flag2;
      this.field3 = flag;
      this.field4 = horsestats144;
   }
}
