package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

interface AttackIndicatorProvider {
   int field1 = 0;
   int field2 = 0;
   int field3 = 1;

   default boolean isVanilla() {
      return false;
   }

   boolean method1(@NotNull Bridge5Extension_5 bridge5extension_51);

   float method2(@NotNull Bridge5Extension_5 bridge5extension_51);

   default boolean method3(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method2(bridge5extension_51) < 1.0F;
   }

   default boolean method4(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return false;
   }

   default int method5() {
      return 0;
   }

   @Nullable
   ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51);

   default void method7(@NotNull Bridge5Extension_5 bridge5extension_51) {
      bridge5extension_51.method1("entity.experience_orb.pickup", 0.25F, 1.0F);
   }

   default void method8(@Nullable Bridge5Extension_5 bridge5extension_51) {
   }
}
