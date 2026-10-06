package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.joml.Vector3dc;
import org.joml.Vector3i;

public class Holograms {
   private final GuiRewindhandlersHandler23 field1;
   private final GuiRewindhandlersHandler24 field2;
   private final Vector3dc field3;
   private final List<Vector3dc> points = new ArrayList<>();
   private final long field4;
   public Vector3i field5;
   public long field6;
   private Vector3dc field7;

   public Holograms(GuiRewindhandlersHandler24 var1, GuiRewindhandlersHandler23 var2, Vector3dc var3, Vector3dc var4) {
      this.field2 = var1;
      this.field1 = var2;
      this.field7 = var3;
      this.field3 = var4;
      this.field4 = ThreadModuleDump63.method3().bridge$getSystemTime();
   }

   public boolean method1(Vector3dc var1) {
      return var1.distanceSquared(this.field7) < 9.0;
   }

   public void method2(Vector3dc var1) {
      if (this.points.size() <= 7) {
         if (this.points.isEmpty() || !var1.equals(this.points.get(this.points.size() - 1))) {
            if (this.method1(var1)) {
               this.points.add(var1);
               if (this.points.size() > 3) {
                  this.method4();
               }
            }
         }
      }
   }

   public void method3(Vector3dc var1) {
      this.field6 = ThreadModuleDump63.method3().bridge$getSystemTime();
      this.field7 = var1;
   }

   private void method4() {
      this.field5 = null;
      Vector3i var1 = this.method6();
      if (!this.field1.method9().contains(var1) && this.field2.method5(var1)) {
         this.field5 = var1;
      }
   }

   private static double bezier(double var0, double var2, double var4, double var6, double var8) {
      double var10 = method5(var0, var2, var4, var6);
      double var12 = method5(var0, var4, var6, var8);
      return lerp(var0, var10, var12);
   }

   private static double method5(double var0, double var2, double var4, double var6) {
      return (var2 * (1.0 - var0) + var4 * var0) * (1.0 - var0) + (var4 * (1.0 - var0) + var6 * var0) * var0;
   }

   private static double lerp(double var0, double var2, double var4) {
      return var2 * (1.0 - var0) + var4 * var0;
   }

   private Vector3i method6() {
      double var1 = this.points.get(0).y();
      double var3 = var1 + this.field3.y() * 4.0 + 3.0;
      double var5 = Double.MAX_VALUE;
      int var7 = 80;
      double var8 = 0.0;

      for (int var10 = 70; var10 <= 100; var10++) {
         double var11 = var10 + 4;
         double var13 = var10 + 1;
         double var15 = 0.0;
         double var17 = 0.2;
         double var19 = Double.MAX_VALUE;
         double var21 = 0.0;

         label44:
         for (int var23 = 0; var23 < 10; var23++) {
            double var24 = (var17 - var15) / 1000.0;

            for (int var26 = 0; var26 <= 1000; var26++) {
               double var27 = var15 + var24 * var26;
               double var29 = 0.0;

               for (int var31 = 1; var31 < this.points.size(); var31++) {
                  double var32 = bezier(var27 * var31, var1, var3, var11, var13);
                  double var34 = Math.pow(this.points.get(var31).y() - var32, 2.0);
                  var29 += var34;
               }

               if (var29 < var19) {
                  var19 = var29;
                  var21 = var27;
                  if (var19 == 0.0) {
                     break label44;
                  }
               }
            }

            var15 = Math.max(0.0, var21 - var24);
            var17 = Math.min(0.2, var21 + var24);
         }

         if (var19 < var5) {
            var5 = var19;
            var7 = var10;
            var8 = var21;
         }
      }

      return new Vector3i(
         this.method7(
            var8, -283, 200, this.points.get(0).x(), this.points.get(0).x() + this.field3.x() * 4.0, this.points.stream().<Double>map(Vector3dc::x).toList()
         ),
         var7,
         this.method7(
            var8, -230, 200, this.points.get(0).z(), this.points.get(0).z() + this.field3.z() * 4.0, this.points.stream().<Double>map(Vector3dc::z).toList()
         )
      );
   }

   private int method7(double var1, int var3, int var4, double var5, double var7, List<Double> list) {
      double var10 = Double.MAX_VALUE;
      int var12 = 0;

      for (int var13 = var3; var13 < var4; var13++) {
         double var14 = var13 + 0.5;
         double var16 = 0.0;

         for (int var18 = 1; var18 < list.size(); var18++) {
            double var19 = bezier(var1 * var18, var5, var7, var14, var14);
            double var21 = Math.pow((Double)list.get(var18) - var19, 2.0);
            var16 += var21;
         }

         if (var16 < var10) {
            var10 = var16;
            var12 = var13;
         }
      }

      return var12;
   }

   public boolean method8() {
      return ThreadModuleDump63.method3().bridge$getSystemTime() - this.field4 < 2000L;
   }

   public boolean method9() {
      return ThreadModuleDump63.method3().bridge$getSystemTime() - this.field4 < 10L;
   }

   @Generated
   public void method10(Vector3i var1) {
      this.field5 = var1;
   }

   @Generated
   public void method11(long var1) {
      this.field6 = var1;
   }
}
