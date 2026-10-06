package com.moonsworth.lunar.client.cosmetics;

import com.mojang.authlib.GameProfile;

public interface DummyPlayer {
   void incrementTicksExisted();

   DummyPlayer.Type getDummyPlayerType();

   void setDummyPlayerType(DummyPlayer.Type type1);

   void setRenderNametag(boolean flag1);

   boolean shouldRenderNametag();

   void setGameProfile(GameProfile gameprofile1);

   enum Type {
      SELF,
      MANNEQUIN;

      Type() {
      }
   }
}
