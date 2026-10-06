package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

interface Attackindicator2 {
   int field1 = 0;
   int field2 = 0;
   int field3 = 1;

   default boolean isVanilla() {
      return false;
   }

   boolean method1(@NotNull Bridge5Extension_5 var1);

   float method2(@NotNull Bridge5Extension_5 var1);

   default boolean method3(@NotNull Bridge5Extension_5 var1) {
      return this.method2(var1) < 1.0F;
   }

   default boolean method4(@NotNull Bridge5Extension_5 var1) {
      return false;
   }

   default int method5() {
      return 0;
   }

   @Nullable
   ItemStackBridge method6(@NotNull Bridge5Extension_5 var1);

   default void method7(@NotNull Bridge5Extension_5 var1) {
      var1.method1("entity.experience_orb.pickup", 0.25F, 1.0F);
   }

   default void method8(@Nullable Bridge5Extension_5 var1) {
   }
}
