package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.ScaleTransform;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.Type;
import org.joml.Vector3f;

public enum ArmorSlot {
   HELMET(Type.HELMET),
   CHESTPLATE(Type.CHESTPLATE),
   LEGGINGS(Type.LEGGINGS),
   BOOTS(Type.BOOTS);

   private Type transformationCondition;

   ArmorSlot(Type type) {
      this.transformationCondition = type;
   }

   public ThreadModuleDump91 getTransformation() {
      return new ScaleTransform(new Vector3f(0.0F, 0.0F, 0.0F), this.transformationCondition);
   }
}
