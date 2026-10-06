package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2_3;

public class PathFollower extends TurboPathFollower {
   public PathFollower(com.moonsworth.lunar.client.render.turbo.PathEntity var1, Itemcounter6 var2) {
      super(var1, var2);
   }

   public PathFollower(com.moonsworth.lunar.client.render.turbo.PathEntity var1, Itemcounter6 var2, int var3) {
      super(var1, var2, var3);
   }

   @Override
   public com.moonsworth.lunar.client.render.turbo.PathSearch method2(int var1) {
      this.field16 = new GroundPathFinder();
      return new com.moonsworth.lunar.client.render.turbo.PathSearch(this.field16, var1);
   }

   @Override
   public boolean method21() {
      return this.field4.isOnGround() || this.field4.method6();
   }

   @Override
   public Vec3Bridge method20() {
      return Vec3Bridge.method2(this.field4.bridge$getPosX(), this.method6(), this.field4.bridge$getPosZ());
   }

   @Override
   public TurboPath method6(Vector3iBridge var1, int var2) {
      if (this.field5.method4(var1).bridge$isAir()) {
         Vector3iBridge var3 = var1.bridge$below();

         while (
            var3.bridge$getY() > this.field5.bridge$getMinBuildHeight()
               && this.field5.method4(var3).bridge$isAir()
         ) {
            var3 = var3.bridge$below();
         }

         if (var3.bridge$getY() > this.field5.bridge$getMinBuildHeight()) {
            return super.method6(var3.bridge$above(), var2);
         }

         while (
            var3.bridge$getY() < this.field5.bridge$getMaxBuildHeight()
               && this.field5.method4(var3).bridge$isAir()
         ) {
            var3 = var3.bridge$above();
         }

         var1 = var3;
      }

      if (!this.field5.method2(var1).bridge$isSolid()) {
         return super.method6(var1, var2);
      }

      Vector3iBridge var4 = var1.bridge$above();

      while (
         var4.bridge$getY() < this.field5.bridge$getMaxBuildHeight()
            && this.field5.method2(var4).bridge$isSolid()
      ) {
         var4 = var4.bridge$above();
      }

      return super.method6(var4, var2);
   }

   @Override
   public TurboPath method7(BridgeExtension var1, int var2) {
      return this.method6(var1.bridge$getBlockPos(), var2);
   }

   public int method6() {
      if (this.field4.isInWater() && this.method21()) {
         int var1 = (int)Math.floor(this.field4.bridge$getPosY());
         Bridge2_17 var2 = this.field5
            .method2(Bridge.method8().method6(this.field4.bridge$getPosX(), var1, this.field4.bridge$getPosZ()));
         int var3 = 0;

         while (var2.bridge$isFluid()) {
            var2 = this.field5
               .method2(
                  Bridge.method8()
                     .method6(this.field4.bridge$getPosX(), var1 + var3, this.field4.bridge$getPosZ())
               );
            if (++var3 > 16) {
               return var1;
            }
         }

         return var1 + var3;
      } else {
         return (int)Math.floor(this.field4.bridge$getPosY() + 0.5);
      }
   }

   public boolean method7(ItemcounterType2_3 var1) {
      if (var1 == ItemcounterType2_3.WATER) {
         return false;
      } else {
         return var1 == ItemcounterType2_3.LAVA ? false : var1 != ItemcounterType2_3.OPEN;
      }
   }
}
