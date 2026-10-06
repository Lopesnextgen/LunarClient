package com.moonsworth.lunar.bridge.slayer;

import com.moonsworth.lunar.ichor.Annotation2;

public interface Slayer4 {
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

   void setFastRender(boolean var1);

   void setAntialiasingLevel(int var1);

   @Annotation2(min = 1)
   void setRenderRegions(boolean var1);

   @Annotation2(min = 1)
   boolean getRenderRegions();

   boolean isZooming();

   void setZooming(boolean var1);

   boolean isWeatherEnabled();

   void updateFramebufferSize();

   void updateLevelRenderer();

   boolean isFogOff();
}
