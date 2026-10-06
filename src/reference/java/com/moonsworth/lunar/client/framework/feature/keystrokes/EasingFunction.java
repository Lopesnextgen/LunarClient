package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.client.util.math.MathUtils;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public enum EasingFunction implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   EASE(0.25F, 0.1F, 0.25F, 1.0F, "ease"),
   LINEAR(0.0F, 0.0F, 1.0F, 1.0F, "linear"),
   EASE_IN(0.42F, 0.0F, 1.0F, 1.0F, "ease_in"),
   EASE_OUT(0.0F, 0.0F, 0.58F, 1.0F, "ease_out"),
   EASE_IN_OUT(0.42F, 0.0F, 0.58F, 1.0F, "ease_in_out");

   private static final Vector3dc p0 = new Vector3d(0.0, 0.0, 0.0);
   private static final Vector3dc p3 = new Vector3d(1.0, 1.0, 0.0);
   private final Vector3dc p1;
   private final Vector3dc p2;
   private final String id;

   EasingFunction(float value3, float value4, float value5, float value6, String text7) {
      this.p1 = new Vector3d(value3, value4, 0.0);
      this.p2 = new Vector3d(value5, value6, 0.0);
      this.id = text7;
   }

   public float compute(float value1) {
      return MathUtils.method1((float)this.cubic(p0, this.p1, this.p2, p3, value1).y(), 0.0F, 1.0F);
   }

   private Vector3dc cubic(Vector3dc vector3dc1, Vector3dc vector3dc2, Vector3dc vector3dc3, Vector3dc vector3dc4, float value5) {
      double value6 = (1.0F - value5) * (1.0F - value5) * (1.0F - value5) * vector3dc1.x()
         + (1.0F - value5) * (1.0F - value5) * 3.0F * value5 * vector3dc2.x()
         + (1.0F - value5) * 3.0F * value5 * value5 * vector3dc3.x()
         + value5 * value5 * value5 * vector3dc4.x();
      double value8 = (1.0F - value5) * (1.0F - value5) * (1.0F - value5) * vector3dc1.y()
         + (1.0F - value5) * (1.0F - value5) * 3.0F * value5 * vector3dc2.y()
         + (1.0F - value5) * 3.0F * value5 * value5 * vector3dc3.y()
         + value5 * value5 * value5 * vector3dc4.y();
      double value10 = (1.0F - value5) * (1.0F - value5) * (1.0F - value5) * vector3dc1.z()
         + (1.0F - value5) * (1.0F - value5) * 3.0F * value5 * vector3dc2.z()
         + (1.0F - value5) * 3.0F * value5 * value5 * vector3dc3.z()
         + value5 * value5 * value5 * vector3dc4.z();
      return new Vector3d(value6, value8, value10);
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }
}
