package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.horsestats.Horsestats$Type;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.chest.SExtension;
import com.moonsworth.lunar.client.util.chest.SImpl;
import com.moonsworth.lunar.files.Files6_2;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public class Holograms {
   public static Files6_2<Holograms.Data, Holograms.Data> method1(Vector3d var0) {
      double var1 = method5(var0);
      int var3 = 0;
      int var4 = 0;
      Vector3d var5 = null;
      Vector3d var6 = null;
      double var7 = 0.0;
      double var9 = Double.MAX_VALUE;

      for (int var11 = 150; var11 < 250; var11++) {
         double var12 = Math.PI * var11 / 200.0;
         Files6_2 var14 = method2(var1, var12, var0.y);
         double var15 = var0.distanceSquared((Vector3dc)var14.field1);
         if (var15 < var9) {
            var9 = var15;
            var7 = var12;
            var3 = (Integer)var14.field2;
            var5 = (Vector3d)var14.field1;
         }
      }

      double var27 = 0.0;
      double var13 = Double.MAX_VALUE;

      for (int var28 = 250; var28 < 300; var28++) {
         double var16 = Math.PI * var28 / 200.0;
         Files6_2 var18 = method2(var1, var16, var0.y);
         double var19 = var0.distanceSquared((Vector3dc)var18.field1);
         if (var19 < var13) {
            var13 = var19;
            var27 = var16;
            var4 = (Integer)var18.field2;
            var6 = (Vector3d)var18.field1;
         }
      }

      double var29 = -Math.sin(var1) * Math.cos(var7);
      double var17 = -Math.sin(var7);
      double var30 = Math.cos(var1) * Math.cos(var7);
      double var21 = -Math.sin(var1) * Math.cos(var27);
      double var23 = -Math.sin(var27);
      double var25 = Math.cos(var1) * Math.cos(var27);
      return Files6_2.method1(
         new Holograms.Data(new Vector3d(var29 * 10.0, var17 * 10.0, var30 * 10.0), var3, var5),
         new Holograms.Data(new Vector3d(var21 * 10.0, var23 * 10.0, var25 * 10.0), var4, var6)
      );
   }

   public static Files6_2<Vector3d, Integer> method2(double var0, double var2, double var4) {
      double var6 = 1.5;
      double var8 = -Math.sin(var0) * Math.cos(var2);
      double var10 = -Math.sin(var2);
      double var12 = Math.cos(var0) * Math.cos(var2);
      Vector3d var14 = new Vector3d(var8, var10, var12).normalize().mul(var6);
      double var15 = ThreadModuleDump63.method7().bridge$getPosX();
      double var17 = ThreadModuleDump63.method7().bridge$getPosY() + 1.7;
      double var19 = ThreadModuleDump63.method7().bridge$getPosZ();
      double var21 = var14.x;
      double var23 = var14.y;
      double var25 = var14.z;
      int var27 = 0;

      while (var17 > var4 || var23 > 0.0) {
         var27++;
         var15 += var21;
         var17 += var23;
         var19 += var25;
         var21 *= 0.99;
         var23 *= 0.99;
         var25 *= 0.99;
         var23 -= 0.03;
      }

      return Files6_2.method1(new Vector3d(var15, var17, var19), var27);
   }

   public static Holograms.Data method3(Vector3d var0, boolean var1) {
      double var2 = method5(var0);
      int var4 = 0;
      Vector3d var5 = null;
      double var6 = 0.0;
      double var8 = Double.MAX_VALUE;

      for (byte var10 = 100; var10 < 300; var10 += 10) {
         double var11 = Math.PI * var10 / 200.0;
         Files6_2 var13 = method4(var2, var11, var1);
         double var14 = var0.distanceSquared((Vector3dc)var13.field1);
         if (var14 < var8) {
            var8 = var14;
            var6 = var10;
            var5 = (Vector3d)var13.field1;
            var4 = (Integer)var13.field2;
         }
      }

      for (double var18 = var6 - 5.0; var18 <= var6 + 5.0; var18++) {
         double var12 = Math.PI * var18 / 200.0;
         Files6_2 var23 = method4(var2, var12, var1);
         double var15 = var0.distanceSquared((Vector3dc)var23.field1);
         if (var15 < var8) {
            var8 = var15;
            var6 = var18;
            var5 = (Vector3d)var23.field1;
            var4 = (Integer)var23.field2;
         }
      }

      for (double var19 = var6 - 0.5; var19 <= var6 + 0.5; var19 += 0.2) {
         double var21 = Math.PI * var19 / 200.0;
         Files6_2 var24 = method4(var2, var21, var1);
         double var26 = var0.distanceSquared((Vector3dc)var24.field1);
         if (var26 < var8) {
            var8 = var26;
            var6 = var19;
            var5 = (Vector3d)var24.field1;
            var4 = (Integer)var24.field2;
         }
      }

      double var20 = Math.PI * var6 / 200.0;
      double var22 = -Math.sin(var2) * Math.cos(var20);
      double var25 = -Math.sin(var20);
      double var16 = Math.cos(var2) * Math.cos(var20);
      return new Holograms.Data(new Vector3d(var22 * 10.0, var25 * 10.0, var16 * 10.0), var4, var5);
   }

   public static Files6_2<Vector3d, Integer> method4(double var0, double var2, boolean var4) {
      double var5 = 1.5;
      double var7 = -Math.sin(var0) * Math.cos(var2);
      double var9 = -Math.sin(var2);
      double var11 = Math.cos(var0) * Math.cos(var2);
      Vector3d var13 = new Vector3d(var7, var9, var11).normalize().mul(var5);
      double var14 = ThreadModuleDump63.method7().bridge$getPosX();
      double var16 = ThreadModuleDump63.method7().bridge$getPosY() + 1.7;
      double var18 = ThreadModuleDump63.method7().bridge$getPosZ();
      double var20 = var13.x;
      double var22 = var13.y;
      double var24 = var13.z;
      int var26 = 0;
      Vector3d var27 = new Vector3d(var14, var16, var18);

      while (var16 > 0.0) {
         if (!var4 || ++var26 % 3 == 0) {
            Vector3d var28 = new Vector3d(var14 + var20, var16 + var22, var18 + var24);
            MissResult var29 = (MissResult)SExtension.builder(SImpl.BLOCK)
               .method3(var27, var28)
               .method15(Bridge2_17::bridge$isSolid)
               .method18()
               .method8(ThreadModuleDump63.method8());
            var27 = var28;
            if (var29 != null && var29.method1() == Horsestats$Type.BLOCK) {
               var14 = var29.method5().bridge$xCoord();
               var16 = var29.method5().bridge$yCoord();
               var18 = var29.method5().bridge$zCoord();
               break;
            }
         }

         var14 += var20;
         var16 += var22;
         var18 += var24;
         var20 *= 0.99;
         var22 *= 0.99;
         var24 *= 0.99;
         var22 -= 0.03;
      }

      return Files6_2.method1(new Vector3d(var14, var16, var18), var26);
   }

   public static double method5(Vector3d var0) {
      double var1 = Math.atan((var0.x - ThreadModuleDump63.method7().bridge$getPosX()) / (var0.z - ThreadModuleDump63.method7().bridge$getPosZ()));
      var1 = Math.PI - var1;
      if (ThreadModuleDump63.method7().bridge$getPosZ() > var0.z) {
         var1 += Math.PI;
      }

      return var1;
   }

   public class Data {
      private final Vector3d field1;
      private final int field2;
      private final Vector3d field3;

      public Data(Vector3d var1, int var2, Vector3d var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public Vector3d method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public Vector3d method3() {
         return this.field3;
      }
   }
}
