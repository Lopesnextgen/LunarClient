package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler5 implements Attackindicator2 {
   private final Predicate<Bridge6_4> field4;

   @Override
   public boolean isVanilla() {
      return true;
   }

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 var1) {
      ItemStackBridge var2 = this.method9(var1);
      return var2 != null && !var2.bridge$isEmpty() && this.field4.test(var2.bridge$getItem());
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 var1) {
      return Attackindicator2.super.method3(var1) || this.method4(var1);
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      return Math.min(1.0F, var1.bridge$getAttackStrengthScale());
   }

   @Override
   public boolean method4(@NotNull Bridge5Extension_5 var1) {
      return this.method2(var1) >= 1.0F && method10(var1);
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      return this.method9(var1);
   }

   @Override
   public int method5() {
      return 1;
   }

   private ItemStackBridge method9(@NotNull Bridge5Extension_5 var1) {
      ItemStackBridge var2 = var1.bridge$getHeldItem();
      return var2 != null && !var2.bridge$isEmpty() ? var2 : null;
   }

   public static boolean method10(@NotNull Bridge5Extension_5 var0) {
      return ThreadModuleDump63.method3().bridge$getPointedEntity().orElse(null) instanceof BridgeExtension2_5 var1
         && var0.bridge$getCurrentItemAttackStrengthDelay() > 5.0F
         && var1.bridge$isAlive();
   }

   @Generated
   public Attackindicator2Handler5(Predicate<Bridge6_4> var1) {
      this.field4 = var1;
   }
}
