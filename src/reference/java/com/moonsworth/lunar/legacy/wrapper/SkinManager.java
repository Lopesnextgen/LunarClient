package com.moonsworth.lunar.legacy.wrapper;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.resources.SkinManager.SkinAvailableCallback;
import net.minecraft.util.ResourceLocation;

@VersionGate(min = 1)
public class SkinManager implements SkinAvailableCallback {
   private NetworkPlayerInfoImpl field1;

   public SkinManager(NetworkPlayerInfoImpl networkplayerinfoimpl1) {
      this.field1 = networkplayerinfoimpl1;
   }

   public void skinAvailable(Type type1, ResourceLocation location2, MinecraftProfileTexture minecraftprofiletexture3) {
      if (type1 == Type.SKIN) {
         if (Ref.MC_VERSION >= 5) {
            this.field1.playerTextures$v1_12.put(Type.SKIN, location2);
         } else {
            this.field1.locationSkin = location2;
         }

         this.field1.skinType = minecraftprofiletexture3.getMetadata("model");
         if (this.field1.skinType == null) {
            this.field1.skinType = "default";
         }
      } else if (Ref.MC_VERSION >= 5) {
         this.field1.playerTextures$v1_12.put(Type.CAPE, location2);
      } else {
         this.field1.locationCape = location2;
      }
   }

   public void onSkinAvailable$v1_7(Type type1, ResourceLocation location2) {
   }
}
