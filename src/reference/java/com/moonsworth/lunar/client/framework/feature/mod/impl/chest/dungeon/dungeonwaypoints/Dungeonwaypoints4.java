package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.chest.SExtension;
import com.moonsworth.lunar.client.util.chest.SImpl;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Dungeonwaypoints4 {
   private static final double field1 = 60.0;
   private static final double field2 = 0.5;
   private static final double field3 = 5.0;
   private boolean field4;
   private boolean field5;
   private double field6;
   @Nullable
   private Vector3iBridge field7;

   public void method1() {
      this.field4 = !this.field4;
      this.field6 = 5.0;
      this.field7 = null;
   }

   public void method2() {
      this.field5 = !this.field5;
      this.field6 = 5.0;
   }

   public void reset() {
      this.field4 = false;
      this.field7 = null;
   }

   public void method3(Vector3iBridge var1) {
      this.field7 = var1;
   }

   public boolean method4(@Nullable Vector3iBridge var1) {
      if (this.field7 == null) {
         return false;
      }

      if (this.field7.method1(var1)) {
         return true;
      }

      this.field7 = null;
      return false;
   }

   public void method5(double var1) {
      if (this.field4 && this.field5 && var1 != 0.0) {
         double var3 = var1 > 0.0 ? 0.5 : -0.5;
         this.field6 = Math.max(1.0, this.field6 + var3);
      }
   }

   @Nullable
   public Vector3iBridge method6() {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var1 != null && var2 != null) {
         double var3 = this.field5 ? Math.max(1.0, this.field6) : 60.0;
         Vec3Bridge var5 = var1.bridge$getEyePosition();
         Vector3iBridge[] var6 = new Vector3iBridge[1];
         ((MissResult)SExtension.builder(SImpl.BLOCK_OR_MISS)
               .method8(var1, var3, 0.0F)
               .method14((var0, var1x) -> !var1x.bridge$getBlock().bridge$isAir() && var1x.bridge$getRenderShape() != ItemcounterType_4.INVISIBLE)
               .method18()
               .method8(var2))
            .method5(var1x -> var6[0] = var1x.method6());
         if (var6[0] != null) {
            if (this.field5) {
               double var13 = var6[0].bridge$getX() + 0.5 - var5.bridge$xCoord();
               double var14 = var6[0].bridge$getY() + 0.5 - var5.bridge$yCoord();
               double var11 = var6[0].bridge$getZ() + 0.5 - var5.bridge$zCoord();
               this.field6 = Math.max(1.0, Math.min(this.field6, Math.sqrt(var13 * var13 + var14 * var14 + var11 * var11)));
            }

            return var6[0];
         } else {
            double var7 = this.field5 ? var3 : 5.0;
            Vec3Bridge var9 = var1.bridge$getViewVector(1.0F);
            return Bridge.method8()
               .method4(
                  (int)Math.floor(var5.bridge$xCoord() + var9.bridge$xCoord() * var7),
                  (int)Math.floor(var5.bridge$yCoord() + var9.bridge$yCoord() * var7),
                  (int)Math.floor(var5.bridge$zCoord() + var9.bridge$zCoord() * var7)
               );
         }
      } else {
         return null;
      }
   }

   @Generated
   public boolean method7() {
      return this.field4;
   }

   @Generated
   public boolean method8() {
      return this.field5;
   }
}
