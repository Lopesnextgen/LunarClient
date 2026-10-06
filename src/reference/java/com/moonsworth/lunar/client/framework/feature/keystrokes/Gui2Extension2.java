package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
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

   Gui2Extension2(float var3, float var4, float var5, float var6, String text) {
      this.p1 = new Vector3d(var3, var4, 0.0);
      this.p2 = new Vector3d(var5, var6, 0.0);
      this.id = text;
   }

   public float compute(float var1) {
      return ThreadModuleDump67.method1((float)this.cubic(p0, this.p1, this.p2, p3, var1).y(), 0.0F, 1.0F);
   }

   private Vector3dc cubic(Vector3dc var1, Vector3dc vector3dc, Vector3dc var3, Vector3dc var4, float var5) {
      double var6 = (1.0F - var5) * (1.0F - var5) * (1.0F - var5) * var1.x()
         + (1.0F - var5) * (1.0F - var5) * 3.0F * var5 * vector3dc.x()
         + (1.0F - var5) * 3.0F * var5 * var5 * var3.x()
         + var5 * var5 * var5 * var4.x();
      double var8 = (1.0F - var5) * (1.0F - var5) * (1.0F - var5) * var1.y()
         + (1.0F - var5) * (1.0F - var5) * 3.0F * var5 * vector3dc.y()
         + (1.0F - var5) * 3.0F * var5 * var5 * var3.y()
         + var5 * var5 * var5 * var4.y();
      double var10 = (1.0F - var5) * (1.0F - var5) * (1.0F - var5) * var1.z()
         + (1.0F - var5) * (1.0F - var5) * 3.0F * var5 * vector3dc.z()
         + (1.0F - var5) * 3.0F * var5 * var5 * var3.z()
         + var5 * var5 * var5 * var4.z();
      return new Vector3d(var6, var8, var10);
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }
}
