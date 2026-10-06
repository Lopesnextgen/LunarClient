package com.moonsworth.lunar.v1_8.optifine.wrapper;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.bridge.slayer.Slayer5;
import com.moonsworth.lunar.bridge.slayer.Slayer6;
import com.moonsworth.lunar.bridge.slayer.Slayer8;
import java.util.Optional;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.optifine.Config;
import net.optifine.CustomColors;

public class Slayer3Impl2 implements Slayer2 {
   private final Slayer4 config;
   private final Slayer3 shaders;
   private final Slayer6 customItems;
   private final Slayer5 customColors;
   private final Slayer8 connectedTextures;
   private boolean reloading;

   public Slayer3Impl2() {
      try {
         Class.forName("net.optifine.Config");
      } catch (ClassNotFoundException var2) {
         Class.forName("Config");
      }

      this.config = new Wrapper22();
      this.shaders = new Slayer3Handler();
      this.customItems = new Wrapper2();
      this.customColors = new Slayer3Impl();
      this.connectedTextures = new Slayer3Impl22();
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
   public int getBossTextColor(int var1) {
      return !this.config.hasCustomColors() ? var1 : CustomColors.getBossTextColor(var1);
   }

   @Override
   public Optional<Slayer5> getCustomColors() {
      return Config.isCustomColors() ? Optional.of(this.customColors) : Optional.empty();
   }

   @Override
   public void waitOnAllChunksRendering(Bridge5_12 var1) {
      ((Minecraft)var1).entityRenderer.loadVisibleChunks = true;
   }

   @Override
   public void callSpriteUpdate(Bridge4_8 var1) {
   }

   @Override
   public void updateMultiTextureSprite(Bridge4_8 var1) {
      if (Config.isMultiTexture()) {
         TextureAtlasSprite var2 = (TextureAtlasSprite)var1;
         if (var2.spriteSingle != null) {
            var2.bindSpriteTexture();
            TextureUtil.uploadTextureMipmap(
               var2.spriteSingle.getFrameTextureData(0),
               var2.spriteSingle.getIconWidth(),
               var2.spriteSingle.getIconHeight(),
               var2.spriteSingle.getOriginX(),
               var2.spriteSingle.getOriginY(),
               false,
               true
            );
         }
      }
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
