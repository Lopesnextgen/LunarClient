package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.BlockModelRotationBridge;
import com.moonsworth.lunar.bridge.horsestats.FacingIndexBridge;
import org.joml.Vector3f;

public interface QuadFactoryExtension {
   BakedQuadExtension bridge$makeBakedQuad(Vector3f var1, Vector3f var2, BlockPartFaceExtension var3, Bridge4_8 var4, FacingIndexBridge var5, BlockModelRotationBridge var6);
}
