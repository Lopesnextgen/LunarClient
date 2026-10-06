package com.moonsworth.lunar.legacy.wrapper;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;

@VersionGate(min = 1)
public class NetworkPlayerInfoImpl extends NetworkPlayerInfo {
   private final EntityPlayerSPImpl field1;

   public NetworkPlayerInfoImpl(EntityPlayerSPImpl entityplayerspimpl1, GameProfile gameprofile2) {
      super(gameprofile2);
      this.field1 = entityplayerspimpl1;
   }

   public void loadPlayerTextures() {
      synchronized (this) {
         if (!this.playerTexturesLoaded) {
            this.playerTexturesLoaded = true;
            Minecraft.getMinecraft().getSkinManager().loadProfileTextures(this.field1.mc.getSession().getProfile(), new SkinManager(this), true);
         }
      }
   }
}
