package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Bridge;
import org.jetbrains.annotations.Contract;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public interface Vector3iBridge {
   Vector3iBridge field1 = Bridge.method8().method4(0, 0, 0);

   int bridge$getX();

   int bridge$getY();

   int bridge$getZ();

   @Contract("_,_,_ -> new")
   default Vector3iBridge bridge$offset(int var1, int var2, int var3) {
      return this.bridge$add(new Vector3i(var1, var2, var3));
   }

   @Contract("_ -> new")
   Vector3iBridge bridge$add(Vector3ic var1);

   default Vector3i bridge$toJoml() {
      return new Vector3i(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ());
   }

   default boolean method1(Vector3iBridge var1) {
      return var1 == null
         ? false
         : this.bridge$getX() == var1.bridge$getX() && this.bridge$getY() == var1.bridge$getY() && this.bridge$getZ() == var1.bridge$getZ();
   }

   default double method2(double var1, double var3, double var5) {
      double var7 = this.bridge$getX() - var1;
      double var9 = this.bridge$getY() - var3;
      double var11 = this.bridge$getZ() - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   @Contract("_ -> new")
   default Vector3iBridge method3(Vector3iBridge var1) {
      return Bridge.method8()
         .method4(
            this.bridge$getY() * var1.bridge$getZ() - this.bridge$getZ() * var1.bridge$getY(),
            this.bridge$getZ() * var1.bridge$getX() - this.bridge$getX() * var1.bridge$getZ(),
            this.bridge$getX() * var1.bridge$getY() - this.bridge$getY() * var1.bridge$getX()
         );
   }

   default boolean method4(Vector3iBridge var1, double var2) {
      return this.method7(var1) < var2 * var2;
   }

   default boolean method5(Vector3iBridge var1, double var2) {
      return this.method8(var1) < var2 * var2;
   }

   default boolean method6(Vec3Bridge var1, double var2) {
      return this.method9(var1) < var2 * var2;
   }

   default double method7(Vector3iBridge var1) {
      return this.method11(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   default double method8(Vector3iBridge var1) {
      return this.method10(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   default double method9(Vec3Bridge var1) {
      return this.method10(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord());
   }

   default double method10(double var1, double var3, double var5) {
      double var7 = this.bridge$getX() + 0.5 - var1;
      double var9 = this.bridge$getY() + 0.5 - var3;
      double var11 = this.bridge$getZ() + 0.5 - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   default double method11(double var1, double var3, double var5) {
      double var7 = this.bridge$getX() - var1;
      double var9 = this.bridge$getY() - var3;
      double var11 = this.bridge$getZ() - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   default int method12(Vector3iBridge var1) {
      float var2 = Math.abs(var1.bridge$getX() - this.bridge$getX());
      float var3 = Math.abs(var1.bridge$getY() - this.bridge$getY());
      float var4 = Math.abs(var1.bridge$getZ() - this.bridge$getZ());
      return (int)(var2 + var3 + var4);
   }

   default Vector3iBridge method13(HorsestatsType_2 var1) {
      return this.method14(var1, 1);
   }

   default Vector3iBridge method14(HorsestatsType_2 var1, int var2) {
      return var2 == 0
         ? this
         : Bridge.method8()
            .method4(
               this.bridge$getX() + var1.getOffsetX() * var2, this.bridge$getY() + var1.getOffsetY() * var2, this.bridge$getZ() + var1.getOffsetZ() * var2
            );
   }

   default Vector3iBridge method15(HorsestatsType$Type var1, int var2) {
      return var2 == 0
         ? this
         : Bridge.method8()
            .method4(
               this.bridge$getX() + (var1 == HorsestatsType$Type.X ? var2 : 0),
               this.bridge$getY() + (var1 == HorsestatsType$Type.Y ? var2 : 0),
               this.bridge$getZ() + (var1 == HorsestatsType$Type.Z ? var2 : 0)
            );
   }

   @Contract("-> new")
   default Vector3iBridge bridge$above() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() + 1, this.bridge$getZ());
   }

   @Contract("_ -> new")
   default Vector3iBridge method16(int var1) {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() + var1, this.bridge$getZ());
   }

   @Contract("-> new")
   default Vector3iBridge bridge$below() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() - 1, this.bridge$getZ());
   }

   @Contract("_ -> new")
   default Vector3iBridge method17(int var1) {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() - var1, this.bridge$getZ());
   }

   @Contract("-> new")
   default Vector3iBridge method18() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ() - 1);
   }

   @Contract("_ -> new")
   default Vector3iBridge method19(int var1) {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ() - var1);
   }

   @Contract("-> new")
   default Vector3iBridge method20() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ() + 1);
   }

   @Contract("_ -> new")
   default Vector3iBridge method21(int var1) {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ() + var1);
   }

   @Contract("-> new")
   default Vector3iBridge method22() {
      return Bridge.method8().method4(this.bridge$getX() - 1, this.bridge$getY(), this.bridge$getZ());
   }

   @Contract("_ -> new")
   default Vector3iBridge method23(int var1) {
      return Bridge.method8().method4(this.bridge$getX() - var1, this.bridge$getY(), this.bridge$getZ());
   }

   @Contract("-> new")
   default Vector3iBridge method24() {
      return Bridge.method8().method4(this.bridge$getX() + 1, this.bridge$getY(), this.bridge$getZ());
   }

   @Contract("_ -> new")
   default Vector3iBridge method25(int var1) {
      return Bridge.method8().method4(this.bridge$getX() + var1, this.bridge$getY(), this.bridge$getZ());
   }

   interface Extension extends Vector3iBridge {
      void bridge$setPos(int var1, int var2, int var3);

      default void method1(double var1, double var3, double var5) {
         this.bridge$setPos((int)Math.floor(var1), (int)Math.floor(var3), (int)Math.floor(var5));
      }

      default void method2(Vector3iBridge var1, int var2, int var3, int var4) {
         this.bridge$setPos(var1.bridge$getX() + var2, var1.bridge$getY() + var3, var1.bridge$getZ() + var4);
      }

      default Horsestats20Extension2 method3() {
         return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ());
      }

      @Contract("-> this")
      @Override
      default Vector3iBridge bridge$above() {
         this.bridge$setPos(this.bridge$getX(), this.bridge$getY() + 1, this.bridge$getZ());
         return this;
      }

      @Contract("-> this")
      @Override
      default Vector3iBridge bridge$below() {
         this.bridge$setPos(this.bridge$getX(), this.bridge$getY() - 1, this.bridge$getZ());
         return this;
      }
   }
}
