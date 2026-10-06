package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;

public class TickPathFollower extends TurboPathFollower {
   public TickPathFollower(com.moonsworth.lunar.client.render.turbo.PathEntity var1, Itemcounter6 var2) {
      super(var1, var2);
   }

   @Override
   public com.moonsworth.lunar.client.render.turbo.PathSearch method2(int var1) {
      this.field16 = new SwimmingPathFinder();
      return new com.moonsworth.lunar.client.render.turbo.PathSearch(this.field16, var1);
   }

   @Override
   public boolean method27(Vec3Bridge var1, Vec3Bridge var2) {
      return true;
   }

   @Override
   public boolean method21() {
      return !this.field4.method6() || this.method21();
   }

   @Override
   public Vec3Bridge method20() {
      return Vec3Bridge.method2(
         this.field4.bridge$getPosX(),
         this.field4.bridge$getPosY(),
         this.field4.bridge$getPosZ()
      );
   }

   @Override
   public void tick() {
      this.tick++;
      if (this.field14) {
         this.method3();
      }

      if (!this.isDone()) {
         if (this.method21()) {
            this.method15();
         } else if (this.field6 != null && !this.field6.isDone()) {
            Vec3Bridge var1 = this.field6.method11(this.field4);
            Horsestats20Extension2 var2 = this.field4.method8();
            if (var2 != null
               && var2.bridge$getX() == (int)Math.floor(var1.bridge$xCoord())
               && var2.bridge$getY() == (int)Math.floor(var1.bridge$yCoord())
               && var2.bridge$getZ() == (int)Math.floor(var1.bridge$zCoord())) {
               this.field6.advance();
            }
         }

         if (!this.isDone()) {
            if (this.field6 != null) {
               Vec3Bridge var3 = this.field6.method11(this.field4);
               this.field4.method10(var3.method6());
            }
         } else {
            this.field4.method10(null);
         }
      }
   }
}
