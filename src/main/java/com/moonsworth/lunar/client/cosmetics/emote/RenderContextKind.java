package com.moonsworth.lunar.client.cosmetics.emote;

import lombok.Generated;

public enum RenderContextKind {
   IN_WORLD(false, false),
   IN_WORLD_PLAYER_MODEL(true, false),
   IN_COSMETIC_PLAYER_MODEL(true, false),
   IN_COSMETIC_PLAYER_GUI_MODEL(true, true),
   IN_COSMETIC_PLAYER_GUI(false, true);

   private final boolean usesPlayer;
   private final boolean inGui;

   @Generated
   public boolean isUsesPlayer() {
      return this.usesPlayer;
   }

   @Generated
   public boolean isInGui() {
      return this.inGui;
   }

   @Generated
   RenderContextKind(boolean flag, boolean flag2) {
      this.usesPlayer = flag;
      this.inGui = flag2;
   }
}
