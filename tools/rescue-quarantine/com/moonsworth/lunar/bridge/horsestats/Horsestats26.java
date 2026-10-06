package com.moonsworth.lunar.bridge.horsestats;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;

public interface Horsestats26 {
   default GameProfile method1(GameProfile var1, PropertyMap var2) {
      var1.getProperties().putAll(var2);
      return var1;
   }

   static Horsestats26 method2() {
      return new Horsestats26() {
         @Override
         public GameProfile method1(GameProfile var1, PropertyMap var2) {
            return Horsestats26.super.method1(var1, var2);
         }
      };
   }
}
