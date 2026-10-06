package com.moonsworth.lunar.v1_7.optifine.wrapper;

import com.moonsworth.lunar.bridge.slayer.Slayer4;
import net.minecraft.client.Minecraft;
import net.optifine.Config;

public class Slayer6Renderer22 implements Slayer4 {
   @Override
   public boolean hasDynamicFov() {
      return Config.isDynamicFov();
   }

   @Override
   public boolean hasSmoothBiomes() {
      return Config.isSmoothBiomes();
   }

   @Override
   public boolean hasCustomColors() {
      return Config.isCustomColors();
   }

   @Override
   public boolean hasCustomSky() {
      return Config.isCustomSky();
   }

   @Override
   public boolean hasCustomFonts() {
      return Config.isCustomFonts();
   }

   @Override
   public boolean hasCustomItems() {
      return Config.isCustomItems();
   }

   @Override
   public boolean hasShowCapes() {
      return Config.isShowCapes();
   }

   @Override
   public boolean hasConnectedTextures() {
      return Config.isConnectedTextures();
   }

   @Override
   public boolean hasNaturalTextures() {
      return Config.isNaturalTextures();
   }

   @Override
   public boolean hasConnectedTexturesFancy() {
      return Config.isConnectedTexturesFancy();
   }

   @Override
   public boolean hasFastRender() {
      return Config.isFastRender();
   }

   @Override
   public boolean hasTranslucentBlocksFancy() {
      return Config.isTranslucentBlocksFancy();
   }

   @Override
   public boolean hasShaders() {
      return Config.isShaders();
   }

   @Override
   public boolean hasAntiAliasing() {
      return Config.isAntialiasing();
   }

   @Override
   public int getAntialiasingLevel() {
      return Config.antialiasingLevel;
   }

   @Override
   public void setRenderRegions(boolean var1) {
   }

   @Override
   public boolean getRenderRegions() {
      return false;
   }

   @Override
   public boolean isZooming() {
      return Config.zoomMode;
   }

   @Override
   public void setZooming(boolean var1) {
      Config.zoomMode = var1;
   }

   @Override
   public void setFastRender(boolean var1) {
      Config.gameSettings.ofFastRender = var1;
   }

   @Override
   public void setAntialiasingLevel(int var1) {
      Config.antialiasingLevel = var1;
   }

   @Override
   public boolean isWeatherEnabled() {
      return Config.isWeatherEnabled();
   }

   @Override
   public void updateFramebufferSize() {
      Config.updateFramebufferSize();
   }

   @Override
   public void updateLevelRenderer() {
      Minecraft.getMinecraft().renderGlobal.loadRenderers();
   }

   @Override
   public boolean isFogOff() {
      return Config.isFogOff();
   }
}
