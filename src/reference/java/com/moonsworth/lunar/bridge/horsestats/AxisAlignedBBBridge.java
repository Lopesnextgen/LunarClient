package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;
import com.moonsworth.lunar.bridge.Bridge;
import org.jetbrains.annotations.Contract;
import org.joml.Vector3d;
import org.joml.Vector3f;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/util/AxisAlignedBB")),
         @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("net/minecraft/util/math/AxisAlignedBB")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/world/phys/AABB"))
   }
)
public interface AxisAlignedBBBridge {
   static AxisAlignedBBBridge method1() {
      return AxisAlignedBBBridge.Data.field1;
   }

   @Annotation("<init>(DDDDDD)V")
   @Contract("_,_,_,_,_,_ -> new")
   static AxisAlignedBBBridge method2(double var0, double var2, double var4, double var6, double var8, double var10) {
      throw new AbstractMethodErrorImpl();
   }

   @Contract("_,_,_,_,_,_ -> new")
   static AxisAlignedBBBridge method3(double var0, double var2, double var4, double var6, double var8, double var10) {
      return method2(var0, var2, var4, var0 + var6, var2 + var8, var4 + var10);
   }

   double bridge$getMinX();

   double bridge$getMinY();

   double bridge$getMinZ();

   double bridge$getMaxX();

   double bridge$getMaxY();

   double bridge$getMaxZ();

   @Contract("_,_,_ -> new")
   AxisAlignedBBBridge bridge$expand(double var1, double var3, double var5);

   @Contract("_,_,_ -> new")
   AxisAlignedBBBridge bridge$offset(double var1, double var3, double var5);

   @Contract("_ -> new")
   default AxisAlignedBBBridge method4(Vec3Bridge var1) {
      return this.bridge$offset(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord());
   }

   @Contract("_ -> new")
   default AxisAlignedBBBridge method5(Vector3iBridge var1) {
      return this.bridge$offset(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   boolean bridge$intersectsWith(AxisAlignedBBBridge var1);

   @Contract("_ -> new")
   AxisAlignedBBBridge bridge$union(AxisAlignedBBBridge var1);

   @Contract("_ -> new")
   default AxisAlignedBBBridge method6(AxisAlignedBBBridge var1) {
      double var2 = Math.min(this.bridge$getMinX(), var1.bridge$getMinX());
      double var4 = Math.min(this.bridge$getMinY(), var1.bridge$getMinY());
      double var6 = Math.min(this.bridge$getMinZ(), var1.bridge$getMinZ());
      double var8 = Math.max(this.bridge$getMaxX(), var1.bridge$getMaxX());
      double var10 = Math.max(this.bridge$getMaxY(), var1.bridge$getMaxY());
      double var12 = Math.max(this.bridge$getMaxZ(), var1.bridge$getMaxZ());
      return Bridge.method8().method45(var2, var4, var6, var8, var10, var12);
   }

   @Contract("_ -> new")
   default AxisAlignedBBBridge method7(Vector3f var1) {
      return this.method9(var1.x, var1.y, var1.z);
   }

   @Contract("_ -> new")
   default AxisAlignedBBBridge method8(Vec3Bridge var1) {
      return this.method9(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord());
   }

   @Contract("_,_,_ -> new")
   default AxisAlignedBBBridge method9(double var1, double var3, double var5) {
      double var7 = Math.min(this.bridge$getMinX(), var1);
      double var9 = Math.min(this.bridge$getMinY(), var3);
      double var11 = Math.min(this.bridge$getMinZ(), var5);
      double var13 = Math.max(this.bridge$getMaxX(), var1);
      double var15 = Math.max(this.bridge$getMaxY(), var3);
      double var17 = Math.max(this.bridge$getMaxZ(), var5);
      return Bridge.method8().method45(var7, var9, var11, var13, var15, var17);
   }

   default boolean method10(double var1, double var3, double var5) {
      return var1 >= this.bridge$getMinX()
         && var1 <= this.bridge$getMaxX()
         && var3 >= this.bridge$getMinY()
         && var3 <= this.bridge$getMaxY()
         && var5 >= this.bridge$getMinZ()
         && var5 <= this.bridge$getMaxZ();
   }

   @Contract("_ -> new")
   default AxisAlignedBBBridge method11(double var1) {
      return this.method12(var1, var1, var1);
   }

   @Contract("_,_,_ -> new")
   default AxisAlignedBBBridge method12(double var1, double var3, double var5) {
      return method2(
         this.bridge$getMinX() - var1,
         this.bridge$getMinY() - var3,
         this.bridge$getMinZ() - var5,
         this.bridge$getMaxX() + var1,
         this.bridge$getMaxY() + var3,
         this.bridge$getMaxZ() + var5
      );
   }

   default String asString() {
      return String.format(
         "[%.2f, %.2f, %.2f, %.2f, %.2f, %.2f]",
         this.bridge$getMinX(),
         this.bridge$getMinY(),
         this.bridge$getMinZ(),
         this.bridge$getMaxX(),
         this.bridge$getMaxY(),
         this.bridge$getMaxZ()
      );
   }

   double bridge$calculateXOffset(AxisAlignedBBBridge var1, double var2);

   double bridge$calculateYOffset(AxisAlignedBBBridge var1, double var2);

   double bridge$calculateZOffset(AxisAlignedBBBridge var1, double var2);

   default Vector3d method13() {
      return new Vector3d(
         this.bridge$getMinX() + (this.bridge$getMaxX() - this.bridge$getMinX()) / 2.0,
         this.bridge$getMinY() + (this.bridge$getMaxY() - this.bridge$getMinY()) / 2.0,
         this.bridge$getMinZ() + (this.bridge$getMaxZ() - this.bridge$getMinZ()) / 2.0
      );
   }

   default double method14() {
      return this.bridge$getMaxX() - this.bridge$getMinX();
   }

   default double method15() {
      return this.bridge$getMaxY() - this.bridge$getMinY();
   }

   default double method16() {
      return this.bridge$getMaxZ() - this.bridge$getMinZ();
   }

   default Vector3d method17(HorsestatsType$Type var1, boolean var2) {
      return new Vector3d(
         var1 == HorsestatsType$Type.X
            ? (var2 ? this.bridge$getMinX() : this.bridge$getMaxX())
            : this.bridge$getMinX() + (this.bridge$getMaxX() - this.bridge$getMinX()) / 2.0,
         var1 == HorsestatsType$Type.Y
            ? (var2 ? this.bridge$getMinY() : this.bridge$getMaxY())
            : this.bridge$getMinY() + (this.bridge$getMaxY() - this.bridge$getMinY()) / 2.0,
         var1 == HorsestatsType$Type.Z
            ? (var2 ? this.bridge$getMinZ() : this.bridge$getMaxZ())
            : this.bridge$getMinZ() + (this.bridge$getMaxZ() - this.bridge$getMinZ()) / 2.0
      );
   }

   boolean bridge$hasNaN();

   double bridge$getSize();

   static AxisAlignedBBBridge method18() {
      return AxisAlignedBBBridge.Data.field2;
   }

   class Data {
      static final AxisAlignedBBBridge field1 = AxisAlignedBBBridge.method2(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
      static final AxisAlignedBBBridge field2 = AxisAlignedBBBridge.method2(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
   }
}
