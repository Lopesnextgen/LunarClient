package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Bridge;
import org.jetbrains.annotations.Contract;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;

public interface Vec3iBridge {
   Vec3iBridge field1 = Bridge.method8().method4(0, 0, 0);

   int bridge$getX();

   int bridge$getY();

   int bridge$getZ();

   @Contract("_,_,_ -> new")
   default Vec3iBridge bridge$offset(int number1, int number2, int number3) {
      return this.bridge$add(new Vector3i(number1, number2, number3));
   }

   @Contract("_ -> new")
   Vec3iBridge bridge$add(Vector3ic vector3ic1);

   default Vector3i bridge$toJoml() {
      return new Vector3i(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ());
   }

   default boolean method1(Vec3iBridge horsestats201) {
      return horsestats201 == null
         ? false
         : this.bridge$getX() == horsestats201.bridge$getX() && this.bridge$getY() == horsestats201.bridge$getY() && this.bridge$getZ() == horsestats201.bridge$getZ();
   }

   default double method2(double value1, double value3, double value5) {
      double value7 = this.bridge$getX() - value1;
      double value9 = this.bridge$getY() - value3;
      double value11 = this.bridge$getZ() - value5;
      return value7 * value7 + value9 * value9 + value11 * value11;
   }

   @Contract("_ -> new")
   default Vec3iBridge method3(Vec3iBridge horsestats201) {
      return Bridge.method8()
         .method4(
            this.bridge$getY() * horsestats201.bridge$getZ() - this.bridge$getZ() * horsestats201.bridge$getY(),
            this.bridge$getZ() * horsestats201.bridge$getX() - this.bridge$getX() * horsestats201.bridge$getZ(),
            this.bridge$getX() * horsestats201.bridge$getY() - this.bridge$getY() * horsestats201.bridge$getX()
         );
   }

   default boolean method4(Vec3iBridge horsestats201, double value2) {
      return this.method7(horsestats201) < value2 * value2;
   }

   default boolean method5(Vec3iBridge horsestats201, double value2) {
      return this.method8(horsestats201) < value2 * value2;
   }

   default boolean method6(Vec3Bridge horsestats151, double value2) {
      return this.method9(horsestats151) < value2 * value2;
   }

   default double method7(Vec3iBridge horsestats201) {
      return this.method11(horsestats201.bridge$getX(), horsestats201.bridge$getY(), horsestats201.bridge$getZ());
   }

   default double method8(Vec3iBridge horsestats201) {
      return this.method10(horsestats201.bridge$getX(), horsestats201.bridge$getY(), horsestats201.bridge$getZ());
   }

   default double method9(Vec3Bridge horsestats151) {
      return this.method10(horsestats151.bridge$xCoord(), horsestats151.bridge$yCoord(), horsestats151.bridge$zCoord());
   }

   default double method10(double value1, double value3, double value5) {
      double value7 = this.bridge$getX() + 0.5 - value1;
      double value9 = this.bridge$getY() + 0.5 - value3;
      double value11 = this.bridge$getZ() + 0.5 - value5;
      return value7 * value7 + value9 * value9 + value11 * value11;
   }

   default double method11(double value1, double value3, double value5) {
      double value7 = this.bridge$getX() - value1;
      double value9 = this.bridge$getY() - value3;
      double value11 = this.bridge$getZ() - value5;
      return value7 * value7 + value9 * value9 + value11 * value11;
   }

   default int method12(Vec3iBridge horsestats201) {
      float value2 = Math.abs(horsestats201.bridge$getX() - this.bridge$getX());
      float value3 = Math.abs(horsestats201.bridge$getY() - this.bridge$getY());
      float value4 = Math.abs(horsestats201.bridge$getZ() - this.bridge$getZ());
      return (int)(value2 + value3 + value4);
   }

   default Vec3iBridge method13(HorsestatsType_2 horsestatstype_21) {
      return this.method14(horsestatstype_21, 1);
   }

   default Vec3iBridge method14(HorsestatsType_2 horsestatstype_21, int number2) {
      return number2 == 0
         ? this
         : Bridge.method8()
            .method4(
               this.bridge$getX() + horsestatstype_21.getOffsetX() * number2, this.bridge$getY() + horsestatstype_21.getOffsetY() * number2, this.bridge$getZ() + horsestatstype_21.getOffsetZ() * number2
            );
   }

   default Vec3iBridge method15(HorsestatsType$Type horsestatstype$type1, int number2) {
      return number2 == 0
         ? this
         : Bridge.method8()
            .method4(
               this.bridge$getX() + (horsestatstype$type1 == HorsestatsType$Type.X ? number2 : 0),
               this.bridge$getY() + (horsestatstype$type1 == HorsestatsType$Type.Y ? number2 : 0),
               this.bridge$getZ() + (horsestatstype$type1 == HorsestatsType$Type.Z ? number2 : 0)
            );
   }

   @Contract("-> new")
   default Vec3iBridge bridge$above() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() + 1, this.bridge$getZ());
   }

   @Contract("_ -> new")
   default Vec3iBridge method16(int number1) {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() + number1, this.bridge$getZ());
   }

   @Contract("-> new")
   default Vec3iBridge bridge$below() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() - 1, this.bridge$getZ());
   }

   @Contract("_ -> new")
   default Vec3iBridge method17(int number1) {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() - number1, this.bridge$getZ());
   }

   @Contract("-> new")
   default Vec3iBridge method18() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ() - 1);
   }

   @Contract("_ -> new")
   default Vec3iBridge method19(int number1) {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ() - number1);
   }

   @Contract("-> new")
   default Vec3iBridge method20() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ() + 1);
   }

   @Contract("_ -> new")
   default Vec3iBridge method21(int number1) {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ() + number1);
   }

   @Contract("-> new")
   default Vec3iBridge method22() {
      return Bridge.method8().method4(this.bridge$getX() - 1, this.bridge$getY(), this.bridge$getZ());
   }

   @Contract("_ -> new")
   default Vec3iBridge method23(int number1) {
      return Bridge.method8().method4(this.bridge$getX() - number1, this.bridge$getY(), this.bridge$getZ());
   }

   @Contract("-> new")
   default Vec3iBridge method24() {
      return Bridge.method8().method4(this.bridge$getX() + 1, this.bridge$getY(), this.bridge$getZ());
   }

   @Contract("_ -> new")
   default Vec3iBridge method25(int number1) {
      return Bridge.method8().method4(this.bridge$getX() + number1, this.bridge$getY(), this.bridge$getZ());
   }

   interface Extension extends Vec3iBridge {
      void bridge$setPos(int number1, int number2, int number3);

      default void method1(double value1, double value3, double value5) {
         this.bridge$setPos((int)Math.floor(value1), (int)Math.floor(value3), (int)Math.floor(value5));
      }

      default void method2(Vec3iBridge horsestats201, int number2, int number3, int value) {
         this.bridge$setPos(horsestats201.bridge$getX() + number2, horsestats201.bridge$getY() + number3, horsestats201.bridge$getZ() + value);
      }

      default Horsestats20Extension2 method3() {
         return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ());
      }

      @Contract("-> this")
      @Override
      default Vec3iBridge bridge$above() {
         this.bridge$setPos(this.bridge$getX(), this.bridge$getY() + 1, this.bridge$getZ());
         return this;
      }

      @Contract("-> this")
      @Override
      default Vec3iBridge bridge$below() {
         this.bridge$setPos(this.bridge$getX(), this.bridge$getY() - 1, this.bridge$getZ());
         return this;
      }
   }
}
