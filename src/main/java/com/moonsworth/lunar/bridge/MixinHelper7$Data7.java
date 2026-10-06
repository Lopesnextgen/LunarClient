package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class MixinHelper7$Data7 {
   private final boolean field1;
   private final boolean field2;
   private final boolean field3;
   @Nullable
   private final ResourceLocationBridge field4;

   public static MixinHelper7$Data7 method1(ResourceLocationBridge var0) {
      return new MixinHelper7$Data7(false, false, false, var0);
   }

   public static MixinHelper7$Data7 method2(ResourceLocationBridge var0, boolean var1, boolean var2) {
      return new MixinHelper7$Data7(false, var2, var1, var0);
   }

   public static MixinHelper7$Data7 method3() {
      return new MixinHelper7$Data7(true, false, false, null);
   }

   public static MixinHelper7$Data7 method4() {
      return new MixinHelper7$Data7(true, true, false, null);
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
   private MixinHelper7$Data7(boolean var1, boolean var2, boolean flag, @Nullable ResourceLocationBridge var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = flag;
      this.field4 = var4;
   }
}
