package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.world.BlockRenderTypeBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class DungeonWaypointRaycast {
   private static final double field1 = 60.0;
   private static final double field2 = 0.5;
   private static final double field3 = 5.0;
   private boolean field4;
   private boolean field5;
   private double field6;
   @Nullable
   private Vec3iBridge field7;

   public DungeonWaypointRaycast() {
   }

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

   public void method3(Vec3iBridge horsestats201) {
      this.field7 = horsestats201;
   }

   public boolean method4(@Nullable Vec3iBridge horsestats201) {
      if (this.field7 == null) {
         return false;
      }

      if (this.field7.method1(horsestats201)) {
         return true;
      }

      this.field7 = null;
      return false;
   }

   public void method5(double value1) {
      if (this.field4 && this.field5 && value1 != 0.0) {
         double value3 = value1 > 0.0 ? 0.5 : -0.5;
         this.field6 = Math.max(1.0, this.field6 + value3);
      }
   }

   @Nullable
   public Vec3iBridge method6() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      if (bridge5extension_51 != null && itemcounter6extension2 != null) {
         double value3 = this.field5 ? Math.max(1.0, this.field6) : 60.0;
         Vec3Bridge horsestats155 = bridge5extension_51.bridge$getEyePosition();
         Vec3iBridge[] items6 = new Vec3iBridge[1];
         ((MissResult)Ray.method9(Raycaster.field2)
               .method8(bridge5extension_51, value3, 0.0F)
               .method14((arg0, arg1x) -> !arg1x.bridge$getBlock().bridge$isAir() && arg1x.bridge$getRenderShape() != BlockRenderTypeBridge.INVISIBLE)
               .method18()
               .method8(itemcounter6extension2))
            .ORCHOHHCOHCORRICRIHCHHRORHHCHH(arg1x -> items6[0] = arg1x.method6());
         if (items6[0] != null) {
            if (this.field5) {
               double value13 = items6[0].bridge$getX() + 0.5 - horsestats155.bridge$xCoord();
               double value14 = items6[0].bridge$getY() + 0.5 - horsestats155.bridge$yCoord();
               double value11 = items6[0].bridge$getZ() + 0.5 - horsestats155.bridge$zCoord();
               this.field6 = Math.max(1.0, Math.min(this.field6, Math.sqrt(value13 * value13 + value14 * value14 + value11 * value11)));
            }

            return items6[0];
         } else {
            double value7 = this.field5 ? value3 : 5.0;
            Vec3Bridge horsestats159 = bridge5extension_51.bridge$getViewVector(1.0F);
            return Bridge.method8()
               .method4(
                  (int)Math.floor(horsestats155.bridge$xCoord() + horsestats159.bridge$xCoord() * value7),
                  (int)Math.floor(horsestats155.bridge$yCoord() + horsestats159.bridge$yCoord() * value7),
                  (int)Math.floor(horsestats155.bridge$zCoord() + horsestats159.bridge$zCoord() * value7)
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
