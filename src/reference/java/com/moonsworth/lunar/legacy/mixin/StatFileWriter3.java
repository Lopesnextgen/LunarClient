package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.SkinLoadedEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(targets = "net/minecraft/client/network/NetworkPlayerInfo$1")
public abstract class StatFileWriter3 {
   @Inject(method = "skinAvailable", at = @At("TAIL"))
   private void lunar$postLoadPlayerTextures(Type type, ResourceLocation resourceLocation, MinecraftProfileTexture minecraftProfileTexture, CallbackInfo callbackInfo) {
      ThreadModuleDump63.method3()
         .bridge$submit(() -> ClientEventBus.method29().method12(SkinLoadedEvent.class, () -> new SkinLoadedEvent((ResourceLocationBridge)resourceLocation)));
   }
}
