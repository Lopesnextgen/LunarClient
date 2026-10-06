package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;

@VersionGate(min = 6)
public interface Transformation {
   Matrix4fc bridge$getMatrix();

   boolean bridge$isDecomposed();

   Vector3fc bridge$getTranslation();

   Quaternionfc bridge$getLeftRotation();

   Quaternionfc bridge$getRightRotation();

   Vector3fc bridge$getScale();
}
