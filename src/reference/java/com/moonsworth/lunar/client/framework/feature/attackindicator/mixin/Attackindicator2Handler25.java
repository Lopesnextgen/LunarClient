package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler25 extends Attackindicator2Handler2 {
   private final Predicate<Bridge6_4> predicate;

   @Override
   protected boolean method3(@NotNull ItemStackBridge var1) {
      return this.predicate.test(var1.bridge$getItem());
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      float var2 = var1.bridge$getCooldown(this.method6());
      return var2 == -1.0F ? 1.0F : 1.0F - var2;
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 var1) {
      return var1.bridge$getCooldown(this.method6()) != -1.0F;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      return this.method6();
   }

   @Generated
   public Attackindicator2Handler25(Predicate<Bridge6_4> var1) {
      this.predicate = var1;
   }
}
