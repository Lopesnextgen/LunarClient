package com.moonsworth.lunar.client.event.mixin.gui;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class EventPlayerListEntry extends LunarEvent {
   private final GameProfile gameProfile;

   @Generated
   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   @Generated
   protected EventPlayerListEntry(GameProfile gameprofile1) {
      this.gameProfile = gameprofile1;
   }

   public static class EventPlayerListRemove extends EventPlayerListEntry {
      public EventPlayerListRemove(GameProfile gameprofile1) {
         super(gameprofile1);
      }
   }

   public static class EventPlayerListAdd extends EventPlayerListEntry {
      public EventPlayerListAdd(GameProfile gameprofile1) {
         super(gameprofile1);
      }
   }
}
