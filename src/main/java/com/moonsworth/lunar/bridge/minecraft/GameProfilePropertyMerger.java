package com.moonsworth.lunar.bridge.minecraft;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;

public interface GameProfilePropertyMerger {
   default GameProfile method1(GameProfile gameprofile1, PropertyMap propertymap2) {
      gameprofile1.getProperties().putAll(propertymap2);
      return gameprofile1;
   }

   static GameProfilePropertyMerger method2() {
      return new GameProfilePropertyMerger() {
         @Override
         public GameProfile method1(GameProfile gameprofile1, PropertyMap propertymap2) {
            return GameProfilePropertyMerger.super.method1(gameprofile1, propertymap2);
         }
      };
   }
}
