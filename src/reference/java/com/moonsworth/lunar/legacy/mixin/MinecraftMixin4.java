package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.optifine.wrapper.Slayer3Renderer22;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, priority = 9999)
public class MinecraftMixin4 {
   @Annotation2(min = 5)
   @Inject(method = "init$v1_12", at = @At("HEAD"))
   public void impl$init(CallbackInfo var1) {
      try {
         Bridge.method38(new Slayer3Renderer22());
         System.out.println("[Bridge] Using OptiFine wrapper");
      } catch (ClassNotFoundException var3) {
         System.out.println("[Bridge] Not using OptiFine wrapper");
      }
   }
}
