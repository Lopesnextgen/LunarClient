package com.moonsworth.lunar.bridge.horsestats;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;

public interface GameProfilePropertyCopier {
   default GameProfile method1(GameProfile var1, PropertyMap var2) {
      var1.getProperties().putAll(var2);
      return var1;
   }

   static GameProfilePropertyCopier method2() {
      return new GameProfilePropertyCopier() {
         @Override
         public GameProfile method1(GameProfile var1, PropertyMap var2) {
            return GameProfilePropertyCopier.super.method1(var1, var2);
         }
      };
   }
}
