package com.moonsworth.lunar.v1_7.optifine.wrapper;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.bridge.slayer.Slayer6;
import com.moonsworth.lunar.bridge.slayer.Slayer8;
import java.util.Optional;
import lombok.Generated;
import net.optifine.Config;

public class Slayer6Renderer2 implements Slayer2 {
   private final Slayer4 config;
   private final Slayer3 shaders;
   private final Slayer6 customItems;
   private final Slayer8 connectedTextures;
   private boolean reloading;

   public Slayer6Renderer2() {
      try {
         Class.forName("net.optifine.Config");
      } catch (ClassNotFoundException var2) {
         Class.forName("Config");
      }

      this.config = new Slayer6Renderer22();
      this.shaders = new Slayer3Handler();
      this.customItems = new Slayer6Renderer();
      this.connectedTextures = new Slayer7Impl();
   }

   @Override
   public Optional<Slayer6> getCustomItems() {
      return Config.isCustomItems() ? Optional.of(this.customItems) : Optional.empty();
   }

   @Override
   public Slayer8 getConnectedTextures() {
      return this.connectedTextures;
   }

   @Override
   public void waitOnAllChunksRendering(Bridge5_12 var1) {
   }

   @Override
   public void callSpriteUpdate(Bridge4_8 var1) {
   }

   @Override
   public void updateMultiTextureSprite(Bridge4_8 var1) {
   }

   @Generated
   @Override
   public Slayer4 getConfig() {
      return this.config;
   }

   @Generated
   @Override
   public Slayer3 getShaders() {
      return this.shaders;
   }

   @Generated
   @Override
   public boolean isReloading() {
      return this.reloading;
   }

   @Generated
   @Override
   public void setReloading(boolean var1) {
      this.reloading = var1;
   }
}
