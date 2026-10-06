package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.FacingIndexBridge;

public interface ModelBuilderExtension {
   void bridge$setParticleTexture(Bridge4_8 var1);

   void impl$addFaceBreakingFours(BakedModelExtension var1, Bridge4_8 var2);

   void bridge$addGeneralQuad(BakedQuadExtension var1);

   void bridge$addFaceQuad(FacingIndexBridge var1, BakedQuadExtension var2);

   BakedModelExtension bridge$makeBakedModel();
}
