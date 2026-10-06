package com.moonsworth.lunar.client.cosmetics;

import com.google.common.util.concurrent.AtomicDouble;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Generated;
import org.joml.Vector3f;
import com.moonsworth.lunar.client.cosmetics.emote.PhysicsPoint;
import com.moonsworth.lunar.client.cosmetics.emote.DistanceConstraint;

public class ClothCloakSolver {
   public static final float field1 = 0.1F;
   public static final int field2 = 5;
   public static final int field3 = 8;
   private static final long field4 = TimeUnit.MILLISECONDS.convert(5L, TimeUnit.SECONDS);
   private static final long field5 = TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MILLISECONDS);
   private final PhysicsPoint[][] field6 = new PhysicsPoint[5][8];
   private final List<DistanceConstraint> field7 = new ArrayList<>();
   private final AtomicDouble field8 = new AtomicDouble();
   private final AtomicDouble field9 = new AtomicDouble();
   private final AtomicDouble field10 = new AtomicDouble();
   private final AtomicBoolean field11 = new AtomicBoolean();
   private boolean field12 = false;
   private long field13 = 0L;
   private long field14 = ThreadModuleDump63.method3().bridge$getSystemTime();
   private long lastUpdate = 0L;
   public static final Thread field15 = new Thread(ClothCloakSolver::method1, "Cloth Cloak Physics Thread");

   public static void method1() {
      try {
         while (true) {
            for (ClothCloakSolver var1 : CosmeticManager.method59().values()) {
               var1.method2();
            }

            Thread.sleep(10L);
         }
      } catch (Throwable var2) {
         throw var2;
      }
   }

   public ClothCloakSolver() {
      float var1 = -0.4F;

      for (int var2 = 0; var2 < 8; var2++) {
         float var3 = -0.25F;

         for (int var4 = 0; var4 < 5; var4++) {
            float var5 = 5.0E-7F * (float)Math.sin(var4 * 1.2F + var2 * 0.5F);
            Vector3f var6 = new Vector3f(var3, var1, var5);
            PhysicsPoint var7 = new PhysicsPoint(this, var6, var4, var2);
            this.field6[var4][var2] = var7;
            var3 += 0.1F;
         }

         var1 += 0.1F;
      }

      this.field6[0][0].lock();
      this.field6[4][0].lock();

      for (int var8 = 0; var8 < 5; var8++) {
         for (int var9 = 0; var9 < 8; var9++) {
            PhysicsPoint var10 = this.field6[var8][var9];
            if (var8 < 4) {
               this.field7.add(new DistanceConstraint(var10, this.field6[var8 + 1][var9], 0.1F));
            }

            if (var9 < 7) {
               this.field7.add(new DistanceConstraint(var10, this.field6[var8][var9 + 1], 0.1F));
            }

            if (var8 < 4 && var9 < 7) {
               this.field7.add(new DistanceConstraint(this.field6[var8][var9], this.field6[var8 + 1][var9 + 1], 0.14141999F));
            }

            if (var8 > 0 && var9 < 7) {
               this.field7.add(new DistanceConstraint(this.field6[var8][var9], this.field6[var8 - 1][var9 + 1], 0.14141999F));
            }
         }
      }
   }

   public void method2() {
      long var1 = ThreadModuleDump63.method3().bridge$getSystemTime();
      if (var1 - this.field14 <= field4) {
         long var3 = var1 - this.lastUpdate;
         if (var3 >= field5) {
            float var5 = (float)((double)var3 / field5);
            Vector3f var6 = new Vector3f((float)(-this.field8.get()), (float)this.field9.get(), (float)this.field10.get());
            var6.mul(0.1F);
            var6.mul(var5);
            float var7 = 0.2F;
            if (var6.length() > var7) {
               var6.normalize().mul(var7);
            }

            float var8 = 0.95F;
            if (this.field12 != this.field11.get()) {
               this.field12 = this.field11.get();
               this.field13 = var1;
               if (this.field12 && var6.length() <= 1.0E-4F) {
                  this.method6();
               }
            }

            if (var1 - this.field13 < 100L) {
               var8 = 0.75F;
            }

            float var9 = 0.015F;

            for (int var10 = 0; var10 < 2; var10++) {
               for (int var11 = 0; var11 < 5; var11++) {
                  for (int var12 = 0; var12 < 8; var12++) {
                     PhysicsPoint var13 = this.field6[var11][var12];
                     this.method3(var13, var6, var9);
                     this.method5(var13, var9);
                     this.method4(var13, var6, var11, var12, var9);
                     var13.method2(var8);
                     var13.method4(this.field11.get());
                     var13.method3(
                        var11 > 0 ? this.field6[var11 - 1][var12] : this.field6[var11 + 1][var12],
                        var12 > 0 ? this.field6[var11][var12 - 1] : this.field6[var11][var12 + 1],
                        var11 > 0 && var12 > 0 || var11 == 0 && var12 == 0
                     );
                  }
               }

               for (DistanceConstraint var15 : this.field7) {
                  var15.method1();
               }
            }

            this.lastUpdate = var1;
         }
      }
   }

   private void method3(PhysicsPoint var1, Vector3f var2, float var3) {
      Vector3f var4 = new Vector3f(var2);
      var4.mul(var3);
      var1.method1(var4);
   }

   private void method4(PhysicsPoint var1, Vector3f var2, int var3, int var4, float var5) {
      long var6 = ThreadModuleDump63.method3().bridge$getSystemTime() % 180000L;
      float var8 = (float)var6 / 1000.0F;
      float var9 = var2.length();
      float var10 = 2.0F * (1.0F + var9 * 4.0F);
      float var11 = 0.3F * (1.0F + var9 * 4.0F);
      float var12 = 2.0E-4F * (1.0F + var9);
      float var13 = var12 * (3.0F + var9 * 4000.0F);
      float var14 = var3 * 0.3F;
      float var15 = var4 * var11 + var8 * var10 + var14;
      Vector3f var16 = new Vector3f(0.0F, 0.0F, var13 * (float)Math.sin(var15));
      var16.mul(var5);
      var1.method1(var16);
   }

   private void method5(PhysicsPoint var1, float var2) {
      Vector3f var3 = new Vector3f();
      float var4 = (float)Math.toRadians(this.field11.get() ? -30.0 : 0.0);
      float var5 = 0.02F;
      float var6 = var5 * (float)Math.cos(var4);
      float var7 = var5 * (float)Math.sin(var4);
      var3.y += var6;
      var3.z += var7;
      var3.mul(var2);
      var1.method1(var3);
   }

   private void method6() {
      for (int var1 = 0; var1 < 5; var1++) {
         for (int var2 = 0; var2 < 8; var2++) {
            PhysicsPoint var3 = this.field6[var1][var2];
            Vector3f var4 = new Vector3f();
            float var5 = (float)Math.toRadians(var2 == 5 ? 0.0 : -30.0);
            float var6 = 4.5F;
            float var7 = var6 * (float)Math.sin(var5);
            var4.z += var7;
            float var8 = 0.015F;
            var4.mul(var8);
            var3.method1(new Vector3f(var4));
         }
      }
   }

   @Generated
   public List<DistanceConstraint> method7() {
      return this.field7;
   }

   @Generated
   public AtomicDouble method8() {
      return this.field8;
   }

   @Generated
   public AtomicDouble method9() {
      return this.field9;
   }

   @Generated
   public AtomicDouble method10() {
      return this.field10;
   }

   @Generated
   public AtomicBoolean method11() {
      return this.field11;
   }

   @Generated
   public boolean method12() {
      return this.field12;
   }

   @Generated
   public long method13() {
      return this.field13;
   }

   @Generated
   public long method14() {
      return this.field14;
   }

   @Generated
   public long getLastUpdate() {
      return this.lastUpdate;
   }

   @Generated
   public PhysicsPoint[][] method16() {
      return this.field6;
   }

   @Generated
   public void method17(long var1) {
      this.field14 = var1;
   }

   static {
      field15.setDaemon(true);
      field15.start();
   }
}
