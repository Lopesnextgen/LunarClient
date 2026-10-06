package com.moonsworth.lunar.bridge;

import org.joml.Matrix3x2fStack;
import org.joml.Quaternionf;

public interface Bridge5_16 {
   void bridge$translate(double var1, double var3, double var5);

   void bridge$scale(float var1, float var2, float var3);

   void bridge$rotateDegrees(float var1, float var2, float var3);

   void bridge$rotate(float var1, float var2, float var3);

   boolean bridge$clear();

   void bridge$popPose();

   void bridge$pushPose();

   Bridge3_10 bridge$last();

   Bridge5_16 bridge$copy();

   void bridge$mulPose(Quaternionf var1);

   void bridge$mulPose(MixinHelper_21 var1);

   void bridge$loadIdentity();

   void bridge$setPose(Bridge3_10 var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 30)
   default void bridge$bindToGuiGraphics(Matrix3x2fStack var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 30)
   default Matrix3x2fStack bridge$unbindGuiGraphics() {
      throw new AbstractMethodErrorImpl();
   }

   default Bridge_8 method1() {
      return new Bridge5$Data(this);
   }
}
