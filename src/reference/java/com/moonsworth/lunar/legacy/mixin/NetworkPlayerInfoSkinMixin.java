package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.HighlightIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(targets = "net/minecraft/client/network/NetworkPlayerInfo$1")
public abstract class NetworkPlayerInfoSkinMixin {
   public NetworkPlayerInfoSkinMixin() {
   }

   @Inject(method = "skinAvailable", at = @At("TAIL"))
   private void lunar$postLoadPlayerTextures(Type type, ResourceLocation location2, MinecraftProfileTexture minecraftprofiletexture3, CallbackInfo callback4) {
      Ref.method3()
         .bridge$submit(() -> LunarEventBus.method29().method12(HighlightIterator.class, () -> new HighlightIterator((ResourceLocationBridge)location2)));
   }
}
