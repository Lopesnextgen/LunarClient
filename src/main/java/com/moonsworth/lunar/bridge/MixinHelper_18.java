package com.moonsworth.lunar.bridge;

import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;

@com.moonsworth.lunar.ichor.Annotation2(min = 6)
public interface MixinHelper_18 {
   Matrix4fc bridge$getMatrix();

   boolean bridge$isDecomposed();

   Vector3fc bridge$getTranslation();

   Quaternionfc bridge$getLeftRotation();

   Quaternionfc bridge$getRightRotation();

   Vector3fc bridge$getScale();
}
