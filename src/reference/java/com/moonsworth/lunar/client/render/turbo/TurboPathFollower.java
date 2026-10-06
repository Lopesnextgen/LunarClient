package com.moonsworth.lunar.client.render.turbo;

import com.google.common.collect.ImmutableSet;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2_3;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

public abstract class TurboPathFollower {
   protected static long field1 = 20L;
   protected static int field2 = 100;
   protected static float field3 = 0.25F;
   protected com.moonsworth.lunar.client.render.turbo.PathEntity field4;
   protected Itemcounter6 field5;
   @Nullable
   protected TurboPath field6;
   protected int tick;
   protected int field7;
   protected Vec3Bridge field8 = Vec3Bridge.method1();
   protected Vector3iBridge field9 = Vector3iBridge.field1;
   protected long field10;
   protected long field11;
   protected double field12;
   protected float field13 = 0.5F;
   protected boolean field14;
   protected long field15;
   protected PathFinder field16;
   protected Vector3iBridge field17;
   protected int field18;
   protected float field19 = 1.0F;
   protected com.moonsworth.lunar.client.render.turbo.PathSearch field20;
   protected boolean field21;

   public TurboPathFollower(com.moonsworth.lunar.client.render.turbo.PathEntity var1, Itemcounter6 var2) {
      this(var1, var2, 16);
   }

   public TurboPathFollower(com.moonsworth.lunar.client.render.turbo.PathEntity var1, Itemcounter6 var2, int var3) {
      this.field4 = var1;
      this.field5 = var2;
      this.field20 = this.method2(var3 * 16);
   }

   public void method1() {
      this.field19 = 1.0F;
   }

   public abstract com.moonsworth.lunar.client.render.turbo.PathSearch method2(int var1);

   public void method3() {
      long var1 = this.field5.bridge$getWorldInfo().bridge$getGameTime();
      if (var1 - this.field15 > field1) {
         if (this.field17 != null) {
            this.field6 = null;
            this.field6 = this.method6(this.field17, this.field18);
            this.field15 = var1;
            this.field14 = false;
         }
      } else {
         this.field14 = true;
      }
   }

   @Nullable
   public TurboPath method4(double var1, double var3, double var5, int var7) {
      return this.method6(Bridge.method8().method6(var1, var3, var5), var7);
   }

   @Nullable
   public TurboPath method5(Set<Vector3iBridge> var1, int var2) {
      return this.method9(var1, var2, this.field20.field2);
   }

   @Nullable
   public TurboPath method6(Vector3iBridge var1, int var2) {
      return this.method5(ImmutableSet.of(var1), var2);
   }

   @Nullable
   public TurboPath method7(BridgeExtension var1, int var2) {
      return this.method5(ImmutableSet.of(var1.bridge$getBlockPos()), var2);
   }

   @Nullable
   public TurboPath method8(@NotNull Vector3iBridge var1, int var2, int var3) {
      return this.method9(ImmutableSet.of(var1), var2, var3);
   }

   @Nullable
   public TurboPath method9(Set<Vector3iBridge> var1, int var2, float var3) {
      if (var1.isEmpty()) {
         return null;
      }

      if (!this.method21()) {
         return null;
      }

      if (this.field6 != null && !this.field6.isDone() && var1.contains(this.field17)) {
         return this.field6;
      }

      TurboPath var4 = this.field20.method1(this.field5, this.field4, var1, var3, var2, this.field19);
      if (var4 != null && var4.method16() != null) {
         this.field17 = var4.method16();
         this.field18 = var2;
         this.method18();
      }

      this.field6 = var4;
      return var4;
   }

   public boolean method10(double var1, double var3, double var5, int var7) {
      return this.method14(this.method4(var1, var3, var5, var7));
   }

   public boolean method11(double var1, double var3, double var5) {
      return this.method14(this.method4(var1, var3, var5, 1));
   }

   public boolean method12(BridgeExtension var1) {
      return this.method13(var1, 1);
   }

   public boolean method13(BridgeExtension var1, int var2) {
      TurboPath var3 = this.method7(var1, var2);
      return var3 != null && this.method14(var3);
   }

   public boolean method14(@Nullable TurboPath var1) {
      if (var1 == null) {
         this.field6 = null;
         return false;
      }

      if (!var1.method1(this.field6)) {
         this.field6 = var1;
      }

      if (this.field6 == null) {
         return false;
      }

      if (this.isDone()) {
         return false;
      }

      if (this.field6.method8() <= 0) {
         return false;
      }

      Vec3Bridge var2 = this.method20();
      this.field7 = this.tick;
      this.field8 = var2;
      return true;
   }

   public void tick() {
      this.tick++;
      if (this.field14) {
         this.method3();
      }

      if (!this.isDone()) {
         if (this.method21()) {
            this.method15();
         } else if (this.field6 != null && !this.field6.isDone()) {
            Vec3Bridge var1 = this.method20();
            Vec3Bridge var2 = this.field6.method11(this.field4);
            if (var1.bridge$yCoord() > var2.bridge$yCoord()
               && !this.field4.isOnGround()
               && Math.floor(var1.bridge$xCoord()) == Math.floor(var2.bridge$xCoord())
               && Math.floor(var1.bridge$zCoord()) == Math.floor(var2.bridge$zCoord())) {
               this.field6.advance();
            }
         }

         if (!this.isDone()) {
            if (this.field6 != null) {
               Horsestats20Extension2 var3 = this.field6.method12();
               this.field4.method10(new Vector3d(var3.bridge$getX() + 0.5, var3.bridge$getY(), var3.bridge$getZ() + 0.5));
            }
         } else {
            this.field4.method10(null);
         }
      }
   }

   public void method15() {
      if (this.field6 != null) {
         Vec3Bridge var1 = this.method20();
         double var2 = this.field4.bridge$getWidth();
         this.field13 = (float)(var2 > 0.75 ? var2 / 2.0 : 0.75 - var2 / 2.0);
         Horsestats20Extension2 var4 = this.field6.method12();
         double var5 = Math.abs(this.field4.bridge$getPosX() - (var4.bridge$getX() + 0.5));
         double var7 = Math.abs(this.field4.bridge$getPosY() - var4.bridge$getY());
         double var9 = Math.abs(this.field4.bridge$getPosZ() - (var4.bridge$getZ() + 0.5));
         boolean var11 = var5 < this.field13 && var9 < this.field13 && var7 < 1.0;
         if (var11 || this.method29(this.field6.method13().field8) && this.method16(var1)) {
            this.field6.advance();
         }

         this.method17(var1);
      }
   }

   public boolean method16(Vec3Bridge var1) {
      if (this.field6 == null) {
         return false;
      }

      if (this.field6.method19() + 1 >= this.field6.method8()) {
         return false;
      }

      Horsestats20Extension2 var2 = this.field6.method12();
      Vec3Bridge var3 = Vec3Bridge.method3(var2);
      if (!var1.method9(var3, 2.0)) {
         return false;
      }

      if (this.method27(var1, this.field6.method11(this.field4))) {
         return true;
      }

      Vec3Bridge var4 = Vec3Bridge.method3(this.field6.method10(this.field6.method19() + 1));
      Vec3Bridge var5 = var3.bridge$subtract(var1);
      Vec3Bridge var6 = var4.bridge$subtract(var1);
      double var7 = var5.method13();
      double var9 = var6.method13();
      boolean var11 = var9 < var7;
      boolean var12 = var7 < 0.5;
      if (!var11 && !var12) {
         return false;
      }

      Vec3Bridge var13 = var5.bridge$normalize();
      Vec3Bridge var14 = var6.bridge$normalize();
      return var14.bridge$dotProduct(var13) < 0.0;
   }

   public void method17(Vec3Bridge var1) {
      if (this.tick - this.field7 > field2) {
         float var2 = (float)Math.sqrt(this.field4.method2());
         float var3 = var2 >= 1.0F ? var2 : (float)this.field4.method2();
         float var4 = var3 * 100.0F * field3;
         if (var1.method8(this.field8) < var4 * var4) {
            this.field21 = true;
            this.stop();
         } else {
            this.field21 = false;
         }

         this.field7 = this.tick;
         this.field8 = var1;
      }

      if (this.field6 != null && !this.field6.isDone()) {
         Horsestats20Extension2 var8 = this.field6.method12();
         long var9 = this.field5.bridge$getWorldInfo().bridge$getGameTime();
         if (var8.equals(this.field9)) {
            this.field10 = this.field10 + (var9 - this.field11);
         } else {
            this.field9 = var8;
            double var5 = var1.method7(Vec3Bridge.method3(this.field9));
            float var7 = (float)Math.sqrt(this.field4.method2());
            this.field12 = var7 > 0.0F ? var5 / var7 * 20.0 : 0.0;
         }

         if (this.field12 > 0.0 && this.field10 > this.field12 * 3.0) {
            this.method25();
         }

         this.field11 = var9;
      }
   }

   public void method18() {
      this.field9 = Vector3iBridge.field1;
      this.field10 = 0L;
      this.field12 = 0.0;
      this.field21 = false;
   }

   public boolean isDone() {
      return this.field6 == null || this.field6.isDone();
   }

   public boolean method19() {
      return !this.isDone();
   }

   public void stop() {
      this.field6 = null;
   }

   public abstract Vec3Bridge method20();

   public abstract boolean method21();

   public boolean method22(Horsestats20Extension2 var1) {
      Horsestats20Extension2 var2 = var1.bridge$below();
      return this.field5.method2(var2).bridge$isSolid();
   }

   public void method23(boolean var1) {
      this.field16.method10(var1);
   }

   public boolean method24() {
      return this.field16.method11();
   }

   public void method25() {
      this.method18();
      this.stop();
   }

   public double method26(Vec3Bridge var1) {
      Horsestats20Extension2 var2 = Bridge.method8().method7(var1);
      return this.field5.method4(var2.bridge$below()).bridge$isAir() ? var1.bridge$yCoord() : GroundPathFinder.method7(this.field5, var2);
   }

   public boolean method27(Vec3Bridge var1, Vec3Bridge var2) {
      return false;
   }

   public boolean method28(Vector3iBridge var1) {
      if (this.field14) {
         return false;
      }

      if (this.field6 != null && !this.field6.isDone() && this.field6.method8() != 0) {
         com.moonsworth.lunar.client.render.turbo.PathNode var2 = this.field6.method4();
         if (var2 == null) {
            return false;
         }

         Vec3Bridge var3 = Vec3Bridge.method2(
            (var2.x + this.field4.bridge$getPosX()) / 2.0, (var2.y + this.field4.bridge$getPosY()) / 2.0, (var2.z + this.field4.bridge$getPosZ()) / 2.0
         );
         return var1.method6(var3, this.field6.method8() - this.field6.method19());
      } else {
         return false;
      }
   }

   public boolean method29(ItemcounterType2_3 var1) {
      return var1 != ItemcounterType2_3.DANGER_FIRE && var1 != ItemcounterType2_3.DANGER_OTHER && var1 != ItemcounterType2_3.WALKABLE_DOOR;
   }

   @Nullable
   @Generated
   public TurboPath method30() {
      return this.field6;
   }

   @Generated
   public float method31() {
      return this.field13;
   }

   @Generated
   public PathFinder method32() {
      return this.field16;
   }

   @Generated
   public Vector3iBridge method33() {
      return this.field17;
   }

   @Generated
   public void method34(Vector3iBridge var1) {
      this.field17 = var1;
   }

   @Generated
   public void method35(float var1) {
      this.field19 = var1;
   }

   @Generated
   public boolean method36() {
      return this.field21;
   }
}
