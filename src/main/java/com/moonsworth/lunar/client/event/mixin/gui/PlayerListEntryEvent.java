package com.moonsworth.lunar.client.event.mixin.gui;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class PlayerListEntryEvent extends Highlight {
   private final GameProfile gameProfile;

   @Generated
   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   @Generated
   protected PlayerListEntryEvent(GameProfile var1) {
      this.gameProfile = var1;
   }

   public static class PlayerListRemoveEvent extends PlayerListEntryEvent {
      public PlayerListRemoveEvent(GameProfile var1) {
         super(var1);
      }
   }

   public static class PlayerListAddEvent extends PlayerListEntryEvent {
      public PlayerListAddEvent(GameProfile var1) {
         super(var1);
      }
   }
}
