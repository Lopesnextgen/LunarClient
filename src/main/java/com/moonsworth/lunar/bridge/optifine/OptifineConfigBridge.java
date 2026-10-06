package com.moonsworth.lunar.bridge.optifine;

import com.moonsworth.lunar.ichor.VersionGate;

public interface OptifineConfigBridge {
   boolean hasDynamicFov();

   boolean hasSmoothBiomes();

   boolean hasCustomColors();

   boolean hasCustomSky();

   boolean hasCustomFonts();

   boolean hasCustomItems();

   boolean hasShowCapes();

   boolean hasConnectedTextures();

   boolean hasNaturalTextures();

   boolean hasConnectedTexturesFancy();

   boolean hasFastRender();

   boolean hasTranslucentBlocksFancy();

   boolean hasShaders();

   boolean hasAntiAliasing();

   int getAntialiasingLevel();

   void setFastRender(boolean flag1);

   void setAntialiasingLevel(int number1);

   @VersionGate(min = 1)
   void setRenderRegions(boolean flag1);

   @VersionGate(min = 1)
   boolean getRenderRegions();

   boolean isZooming();

   void setZooming(boolean flag1);

   boolean isWeatherEnabled();

   void updateFramebufferSize();

   void updateLevelRenderer();

   boolean isFogOff();
}
