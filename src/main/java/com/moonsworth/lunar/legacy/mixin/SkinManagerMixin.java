package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.Bridge12_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Map;
import java.util.Optional;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SkinManager.class)
public abstract class SkinManagerMixin implements Bridge12_4 {
   public SkinManagerMixin() {
   }

   @Shadow
   public Map<Type, MinecraftProfileTexture> loadSkinFromCache(GameProfile gameprofile1) {
      throw new AssertionError();
   }

   @Shadow
   public ResourceLocation loadSkin(MinecraftProfileTexture minecraftprofiletexture1, Type type2) {
      throw new AssertionError();
   }

   public ResourceLocationBridge bridge$registerTexture(MinecraftProfileTexture minecraftprofiletexture1, Type type2) {
      return (ResourceLocationBridge)this.loadSkin(minecraftprofiletexture1, type2);
   }

   public Optional<ResourceLocationBridge> bridge$getSkinLocation(GameProfile gameprofile1, Type type2) {
      MinecraftProfileTexture minecraftprofiletexture3 = this.loadSkinFromCache(gameprofile1).get(type2);
      return minecraftprofiletexture3 != null ? Optional.of((ResourceLocationBridge)this.loadSkin(minecraftprofiletexture3, type2)) : Optional.empty();
   }

   public boolean bridge$isSkinLoaded(GameProfile gameprofile1) {
      return !this.loadSkinFromCache(gameprofile1).isEmpty();
   }
}
