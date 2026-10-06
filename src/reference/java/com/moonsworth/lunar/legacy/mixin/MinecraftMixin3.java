package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.MixinSupport;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin3 {
   @Shadow
   public abstract void shutdown();

   @Annotation2(max = 1)
   @Inject(method = "startGame$v1_7", at = @At("RETURN"))
   public void test$start_v1_7(CallbackInfo var1) {
      this.test$start();
   }

   @Annotation2(min = 5)
   @Inject(method = "init$v1_12", at = @At("RETURN"))
   public void test$start_v1_12(CallbackInfo var1) {
      this.test$start();
   }

   @Unique
   public void test$start() {
      MixinSupport.method3(this);
      System.out.println("[Test] Returning early in Minecraft#startGame");
      this.shutdown();
   }
}
