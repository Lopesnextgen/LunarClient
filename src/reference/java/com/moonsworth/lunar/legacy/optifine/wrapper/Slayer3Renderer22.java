package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.bridge.slayer.Slayer5;
import com.moonsworth.lunar.bridge.slayer.Slayer6;
import com.moonsworth.lunar.bridge.slayer.Slayer8;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.optifine.Config;
import net.optifine.CustomColors;

@Annotation2(min = 5)
public class Slayer3Renderer22 implements Slayer2 {
   private final Slayer4 field1;
   private final Slayer3 field2;
   private final Slayer6 field3;
   private final Slayer5 field4;
   private final Slayer8 field5;
   private boolean reloading;

   public Slayer3Renderer22() {
      try {
         Class.forName("net.optifine.Config");
      } catch (ClassNotFoundException var2) {
         Class.forName("Config");
      }

      this.field1 = new Slayer3Renderer2222();
      this.field2 = new Slayer3Handler();
      this.field3 = new Slayer3Renderer();
      this.field4 = new Slayer3Renderer222();
      this.field5 = new Slayer3Renderer2();
   }

   @Override
   public Optional<Slayer6> getCustomItems() {
      return Config.isCustomItems() ? Optional.of(this.field3) : Optional.empty();
   }

   @Override
   public Slayer8 getConnectedTextures() {
      return this.field5;
   }

   @Override
   public int getBossTextColor(int var1) {
      return !this.field1.hasCustomColors() ? var1 : CustomColors.getBossTextColor(var1);
   }

   @Override
   public Optional<Slayer5> getCustomColors() {
      return Config.isCustomColors() ? Optional.of(this.field4) : Optional.empty();
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
      return this.field1;
   }

   @Generated
   @Override
   public Slayer3 getShaders() {
      return this.field2;
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
