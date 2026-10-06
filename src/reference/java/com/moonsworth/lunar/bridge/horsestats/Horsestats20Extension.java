package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge_61;
import org.jetbrains.annotations.Contract;

@Annotation(mappings = @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/core/SectionPos")))
public interface Horsestats20Extension extends Vector3iBridge {
   @Annotation("<init>(III)V")
   @Contract("_,_,_ -> new")
   static Horsestats20Extension method1(int var0, int var1, int var2) {
      return Bridge.method8().method11(var0, var1, var2);
   }

   static Horsestats20Extension method2(int var0, int var1, int var2) {
      return method1(var0, var1, var2);
   }

   static Horsestats20Extension method4(Vector3iBridge var0) {
      return method1(method13(var0.bridge$getX()), method13(var0.bridge$getY()), method13(var0.bridge$getZ()));
   }

   static Horsestats20Extension method4(Bridge_61 var0) {
      return method1(method13(var0.bridge$getBlockX()), method13(var0.bridge$getBlockY()), method13(var0.bridge$getBlockZ()));
   }

   static Horsestats20Extension method5(Vec3Bridge var0) {
      return method1(method16(var0.bridge$xCoord()), method16(var0.bridge$yCoord()), method16(var0.bridge$zCoord()));
   }

   static Horsestats20Extension method6(long var0) {
      return method1(method10(var0), method11(var0), method12(var0));
   }

   static long method9(Vector3iBridge var0) {
      return method8(method13(var0.bridge$getX()), method13(var0.bridge$getY()), method13(var0.bridge$getZ()));
   }

   static long method8(int var0, int var1, int var2) {
      long var3 = 0L;
      var3 |= (var0 & 4194303L) << 42;
      var3 |= var1 & 1048575L;
      return var3 | (var2 & 4194303L) << 20;
   }

   static boolean method9(Vector3iBridge var0, Horsestats20Extension var1) {
      return method13(var0.bridge$getX()) == var1.bridge$getX()
         && method13(var0.bridge$getY()) == var1.bridge$getY()
         && method13(var0.bridge$getZ()) == var1.bridge$getZ();
   }

   static int method10(long var0) {
      return (int)(var0 >> 42);
   }

   static int method11(long var0) {
      return (int)(var0 << 44 >> 44);
   }

   static int method12(long var0) {
      return (int)(var0 << 22 >> 42);
   }

   static int method13(int var0) {
      return var0 >> 4;
   }

   static int method14(int var0) {
      return var0 << 4;
   }

   static int method15(float var0) {
      return (int)Math.floor(var0) >> 4;
   }

   static int method16(double var0) {
      return (int)Math.floor(var0) >> 4;
   }

   static int method18(int var0) {
      return var0 & 15;
   }

   default Vector3iBridge method19() {
      return Bridge.method8().method4(method14(this.bridge$getX()), method14(this.bridge$getY()), method14(this.bridge$getZ()));
   }

   default Vector3iBridge method21() {
      return this.method19().bridge$offset(8, 8, 8);
   }

   default AxisAlignedBBBridge method23() {
      int var1 = method14(this.bridge$getX());
      int var2 = method14(this.bridge$getY());
      int var3 = method14(this.bridge$getZ());
      return AxisAlignedBBBridge.method2(var1, var2, var3, var1 + 16, var2 + 16, var3 + 16);
   }

   default AxisAlignedBBBridge method25() {
      int var1 = method14(this.bridge$getX());
      int var2 = method14(this.bridge$getY());
      int var3 = method14(this.bridge$getZ());
      return AxisAlignedBBBridge.method2(var1 - 1, var2 - 1, var3 - 1, var1 + 17, var2 + 17, var3 + 17);
   }

   default long bridge$asLong() {
      return method8(this.bridge$getX(), this.bridge$getY(), this.bridge$getZ());
   }
}
