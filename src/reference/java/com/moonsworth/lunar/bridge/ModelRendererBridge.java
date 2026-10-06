package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import org.joml.Matrix4f;

public interface ModelRendererBridge {
   void bridge$setTextureOffsetX(int number1);

   void bridge$setTextureOffsetY(int number1);

   void bridge$setRotateAngleX(float value1);

   void bridge$setRotateAngleY(float value1);

   void bridge$setRotateAngleZ(float value1);

   float bridge$getRotateAngleX();

   float bridge$getRotateAngleY();

   float bridge$getRotateAngleZ();

   void bridge$setRotatePointX(float value1);

   void bridge$setRotatePointY(float value1);

   void bridge$setRotatePointZ(float value1);

   float bridge$getRotatePointX();

   float bridge$getRotatePointY();

   float bridge$getRotatePointZ();

   boolean bridge$isVisible();

   void bridge$setVisible(boolean flag1);

   default void method1(MatrixStackBridge bridge_531) {
      float value2 = 0.0625F;
      float value3 = this.bridge$getRotateAngleX();
      float value4 = this.bridge$getRotateAngleY();
      float value5 = this.bridge$getRotateAngleZ();
      float value6 = this.bridge$getRotatePointX();
      float value7 = this.bridge$getRotatePointY();
      float value8 = this.bridge$getRotatePointZ();
      if (value3 != 0.0F || value4 != 0.0F || value5 != 0.0F) {
         bridge_531.method7(value6 * 0.0625F, value7 * 0.0625F, value8 * 0.0625F);
         if (value5 != 0.0F) {
            bridge_531.method6((float)Math.toDegrees(value5), 0.0F, 0.0F, 1.0F);
         }

         if (value4 != 0.0F) {
            bridge_531.method6((float)Math.toDegrees(value4), 0.0F, 1.0F, 0.0F);
         }

         if (value3 != 0.0F) {
            bridge_531.method6((float)Math.toDegrees(value3), 1.0F, 0.0F, 0.0F);
         }
      } else if (value6 != 0.0F || value7 != 0.0F || value8 != 0.0F) {
         bridge_531.method7(value6 * 0.0625F, value7 * 0.0625F, value8 * 0.0625F);
      }
   }

   default void method2(Matrix4f matrix4f1) {
      float value2 = 0.0625F;
      float value3 = this.bridge$getRotateAngleX();
      float value4 = this.bridge$getRotateAngleY();
      float value5 = this.bridge$getRotateAngleZ();
      float value6 = this.bridge$getRotatePointX();
      float value7 = this.bridge$getRotatePointY();
      float value8 = this.bridge$getRotatePointZ();
      if (value3 != 0.0F || value4 != 0.0F || value5 != 0.0F) {
         matrix4f1.translate(value6 * 0.0625F, value7 * 0.0625F, value8 * 0.0625F);
         if (value5 != 0.0F) {
            matrix4f1.rotate(value5, 0.0F, 0.0F, 1.0F);
         }

         if (value4 != 0.0F) {
            matrix4f1.rotate(value4, 0.0F, 1.0F, 0.0F);
         }

         if (value3 != 0.0F) {
            matrix4f1.rotate(value3, 1.0F, 0.0F, 0.0F);
         }
      } else if (value6 != 0.0F || value7 != 0.0F || value8 != 0.0F) {
         matrix4f1.translate(value6 * 0.0625F, value7 * 0.0625F, value8 * 0.0625F);
      }
   }

   default void bridge$postRender(float value1) {
   }

   default void bridge$translateAndRotate(Bridge5_16 bridge5_161) {
   }

   void bridge$render(float value1, ResourceLocationBridge horsestats142);

   default void bridge$render(Bridge5_16 bridge5_161, float value2, ResourceLocationBridge horsestats143) {
   }
}
