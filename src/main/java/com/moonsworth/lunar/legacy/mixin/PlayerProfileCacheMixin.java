package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Collections;
import net.minecraft.server.management.PlayerProfileCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerProfileCache.class)
public class PlayerProfileCacheMixin {
   public PlayerProfileCacheMixin() {
   }

   @VersionGate(1)
   @ModifyExpressionValue(
      method = "load()V",
      at = @At(value = "INVOKE", target = "Lcom/google/gson/Gson;fromJson(Ljava/io/Reader;Ljava/lang/reflect/Type;)Ljava/lang/Object;")
   )
   private Object lunar$fromJson(Object object) {
      return object != null ? object : Collections.emptyList();
   }
}
