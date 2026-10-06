package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2_3;
import java.util.Random;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

public interface PathEntity {
   double method2();

   default float method2(ItemcounterType2_3 var1) {
      return var1.getMalus();
   }

   float method3();

   float method4();

   boolean isOnGround();

   boolean isInWater();

   boolean method6();

   default AxisAlignedBBBridge method7() {
      double var1 = this.bridge$getWidth() / 2.0;
      double var3 = this.bridge$getPosX() - var1;
      double var5 = this.bridge$getPosY();
      double var7 = this.bridge$getPosZ() - var1;
      double var9 = this.bridge$getPosX() + var1;
      double var11 = this.bridge$getPosY() + this.bridge$getHeight();
      double var13 = this.bridge$getPosZ() + var1;
      return AxisAlignedBBBridge.method2(var3, var5, var7, var9, var11, var13);
   }

   double bridge$getPosX();

   double bridge$getPosY();

   double bridge$getPosZ();

   @Nullable
   Horsestats20Extension2 method8();

   float bridge$getWidth();

   float bridge$getHeight();

   Random method9();

   void method10(Vector3d var1);
}
