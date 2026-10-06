package com.moonsworth.lunar.bridge;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Optional;

public interface Bridge12_4 {
   ResourceLocationBridge bridge$registerTexture(MinecraftProfileTexture minecraftprofiletexture1, Type type2);

   Optional<ResourceLocationBridge> bridge$getSkinLocation(GameProfile gameprofile1, Type type2);

   boolean bridge$isSkinLoaded(GameProfile gameprofile1);
}
