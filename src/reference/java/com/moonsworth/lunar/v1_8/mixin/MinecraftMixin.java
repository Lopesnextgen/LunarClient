package com.moonsworth.lunar.v1_8.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.v1_8.optifine.wrapper.Slayer3Impl2;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
   public MinecraftMixin() {
   }

   @Inject(method = "startGame", at = @At("HEAD"))
   public void impl$startGame(CallbackInfo callback1) {
      try {
         Bridge.method38(new Slayer3Impl2());
         System.out.println("[Bridge] Using OptiFine wrapper");
      } catch (ClassNotFoundException classnotfoundexception3) {
         System.out.println("[Bridge] Not using OptiFine wrapper");
      }
   }
}
