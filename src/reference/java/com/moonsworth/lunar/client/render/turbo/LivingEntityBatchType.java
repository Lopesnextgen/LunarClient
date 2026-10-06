package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Comparator;
import java.util.List;
import org.joml.Vector3d;

@Annotation2(min = 8)
public class LivingEntityBatchType extends EntityBatchType<Horsestats20Extension> {
   public Horsestats20Extension method2(Vec3Bridge var1) {
      return Horsestats20Extension.method5(var1);
   }

   @Override
   public boolean method2(Vec3Bridge var1, Vector3d var2) {
      double var3 = var1.bridge$xCoord() - var2.x();
      double var5 = var1.bridge$yCoord() - var2.y();
      double var7 = var1.bridge$zCoord() - var2.z();
      return var3 * var3 + var5 * var5 + var7 * var7 > 1.0;
   }

   @Override
   public void method3(List<Horsestats20Extension> var1, Vec3Bridge var2) {
      var1.sort(Comparator.comparingDouble(var2x -> this.method12(var2, var2x.method21())));
   }

   public AxisAlignedBBBridge method4(Horsestats20Extension var1) {
      return var1.method23();
   }

   public Vector3iBridge method5(Horsestats20Extension var1) {
      return var1.method21();
   }

   public Vector3iBridge method6(Horsestats20Extension var1) {
      return var1.method19();
   }

   public boolean method7(Bridge14_3 var1, Horsestats20Extension var2, AxisAlignedBBBridge var3) {
      return !var1.bridge$isVisible(var3);
   }

   public boolean method8(Bridge14_3 var1, Horsestats20Extension var2, AxisAlignedBBBridge var3, int var4) {
      return var4 != -1 && method11(var2, var3, var4);
   }

   @Override
   public void method9(TurboEngineManager var1) {
      var1.method1();
   }

   @Override
   public void method10(TurboEngineManager var1) {
      var1.method23().method9();
   }

   public static boolean method11(Horsestats20Extension var0, AxisAlignedBBBridge var1, int var2) {
      int var3 = var2 + 1;
      int var4 = Math.min(
         Math.abs(var0.bridge$getX() - Horsestats20Extension.method16(var1.bridge$getMinX())),
         Math.abs(var0.bridge$getX() - Horsestats20Extension.method16(var1.bridge$getMaxX()))
      );
      if (var4 > var3) {
         return true;
      }

      int var5 = Math.min(
         Math.abs(var0.bridge$getZ() - Horsestats20Extension.method16(var1.bridge$getMinZ())),
         Math.abs(var0.bridge$getZ() - Horsestats20Extension.method16(var1.bridge$getMaxZ()))
      );
      if (var5 > var3) {
         return true;
      }

      int var6 = Math.min(
         Math.abs(var0.bridge$getY() - Horsestats20Extension.method16(var1.bridge$getMinY())),
         Math.abs(var0.bridge$getY() - Horsestats20Extension.method16(var1.bridge$getMaxY()))
      );
      return var6 > var3;
   }

   private double method12(Vec3Bridge var1, Vector3iBridge var2) {
      double var3 = var1.bridge$xCoord() - var2.bridge$getX();
      double var5 = var1.bridge$yCoord() - var2.bridge$getY();
      double var7 = var1.bridge$zCoord() - var2.bridge$getZ();
      return var3 * var3 + var5 * var5 + var7 * var7;
   }
}
