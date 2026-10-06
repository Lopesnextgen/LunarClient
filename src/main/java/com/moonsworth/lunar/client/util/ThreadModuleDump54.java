package com.moonsworth.lunar.client.util;

import com.mojang.authlib.GameProfile;

public interface ThreadModuleDump54 {
   void incrementTicksExisted();

   ThreadModuleDump54.Type getDummyPlayerType();

   void setDummyPlayerType(ThreadModuleDump54.Type var1);

   void setRenderNametag(boolean var1);

   boolean shouldRenderNametag();

   void setGameProfile(GameProfile var1);

   enum Type {
      SELF,
      MANNEQUIN;
   }
}
