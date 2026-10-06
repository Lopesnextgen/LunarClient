package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;
import org.jetbrains.annotations.Contract;
import org.joml.Vector3d;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/util/Vec3")),
         @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("net/minecraft/util/math/Vec3d")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/world/phys/Vec3"))
   }
)
public interface Vec3Bridge {
   static Vec3Bridge method1() {
      return Vec3Bridge.Data.field1;
   }

   @Annotation("<init>(DDD)V")
   @Contract("_ -> new")
   static Vec3Bridge method2(double var0, double var2, double var4) {
      throw new AbstractMethodErrorImpl();
   }

   static Vec3Bridge method3(Vector3iBridge var0) {
      return method2(var0.bridge$getX() + 0.5, var0.bridge$getY(), var0.bridge$getZ() + 0.5);
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("xCoord")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("x"))
      }
   )
   double bridge$xCoord();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("yCoord")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("y"))
      }
   )
   double bridge$yCoord();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("zCoord")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("z"))
      }
   )
   double bridge$zCoord();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("lengthVector")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("length"))
      }
   )
   double bridge$lengthVector();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("dotProduct")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("dot"))
      }
   )
   double bridge$dotProduct(Vec3Bridge var1);

   @Annotation("normalize")
   Vec3Bridge bridge$normalize();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("crossProduct")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("cross"))
      }
   )
   Vec3Bridge bridge$crossProduct(Vec3Bridge var1);

   default double method4(double var1, double var3, double var5) {
      double var7 = this.bridge$xCoord() - var1;
      double var9 = this.bridge$yCoord() - var3;
      double var11 = this.bridge$zCoord() - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   default Vec3Bridge method5(double var1) {
      return method2(this.bridge$xCoord() * var1, this.bridge$yCoord() * var1, this.bridge$zCoord() * var1);
   }

   default Vector3d method6() {
      return new Vector3d(this.bridge$xCoord(), this.bridge$yCoord(), this.bridge$zCoord());
   }

   default double method7(Vec3Bridge var1) {
      double var2 = var1.bridge$xCoord() - this.bridge$xCoord();
      double var4 = var1.bridge$yCoord() - this.bridge$yCoord();
      double var6 = var1.bridge$zCoord() - this.bridge$zCoord();
      return Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6);
   }

   default double method8(Vec3Bridge var1) {
      double var2 = var1.bridge$xCoord() - this.bridge$xCoord();
      double var4 = var1.bridge$yCoord() - this.bridge$yCoord();
      double var6 = var1.bridge$zCoord() - this.bridge$zCoord();
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   default boolean method9(Vec3Bridge var1, double var2) {
      return this.method8(var1) < var2 * var2;
   }

   default boolean method10(Vec3Bridge var1, double var2, double var4) {
      double var6 = var1.bridge$xCoord() - this.bridge$xCoord();
      double var8 = var1.bridge$yCoord() - this.bridge$yCoord();
      double var10 = var1.bridge$zCoord() - this.bridge$zCoord();
      return var6 * var6 + var10 * var10 < var2 * var2 && Math.abs(var8) < var4;
   }

   default double method11(Vec3Bridge var1) {
      return this.bridge$xCoord() * var1.bridge$xCoord() + this.bridge$yCoord() * var1.bridge$yCoord() + this.bridge$zCoord() * var1.bridge$zCoord();
   }

   default double method12() {
      return Math.sqrt(this.bridge$xCoord() * this.bridge$xCoord() + this.bridge$yCoord() * this.bridge$yCoord() + this.bridge$zCoord() * this.bridge$zCoord());
   }

   default double method13() {
      return this.bridge$xCoord() * this.bridge$xCoord() + this.bridge$yCoord() * this.bridge$yCoord() + this.bridge$zCoord() * this.bridge$zCoord();
   }

   default double method14() {
      return Math.sqrt(this.bridge$xCoord() * this.bridge$xCoord() + this.bridge$zCoord() * this.bridge$zCoord());
   }

   default double method15() {
      return this.bridge$xCoord() * this.bridge$xCoord() + this.bridge$zCoord() * this.bridge$zCoord();
   }

   @Contract("_ -> new")
   default Vec3Bridge method16(Vec3Bridge var1) {
      return method2(
         this.bridge$yCoord() * var1.bridge$zCoord() - this.bridge$zCoord() * var1.bridge$yCoord(),
         this.bridge$zCoord() * var1.bridge$xCoord() - this.bridge$xCoord() * var1.bridge$zCoord(),
         this.bridge$xCoord() * var1.bridge$yCoord() - this.bridge$yCoord() * var1.bridge$xCoord()
      );
   }

   @Contract("_ -> new")
   default Vec3Bridge bridge$subtract(Vec3Bridge var1) {
      return this.method17(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord());
   }

   @Contract("_,_,_ -> new")
   default Vec3Bridge method17(double var1, double var3, double var5) {
      return this.method19(-var1, -var3, -var5);
   }

   @Contract("_ -> new")
   default Vec3Bridge method18(Vec3Bridge var1) {
      return this.method19(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord());
   }

   @Contract("_,_,_ -> new")
   default Vec3Bridge method19(double var1, double var3, double var5) {
      return method2(this.bridge$xCoord() + var1, this.bridge$yCoord() + var3, this.bridge$zCoord() + var5);
   }

   class Data {
      static final Vec3Bridge field1 = Vec3Bridge.method2(0.0, 0.0, 0.0);
   }
}
