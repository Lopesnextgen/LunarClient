package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge.Extension;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump82;
import java.util.List;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.account.LunarPlusManager;

public class SwimmingPathFinder extends GroundPathFinder {
   private static final float field12 = 1.0F;
   private static final float field13 = 1.1F;
   private static final int field14 = 10;

   @Override
   public com.moonsworth.lunar.client.render.turbo.PathNode method6() {
      int var1;
      if (this.field6 && this.field1.isInWater()) {
         if (this.field1.method8() == null) {
            var1 = (int)Math.floor(this.field1.bridge$getPosY() + 0.5);
         } else {
            var1 = this.field1.method8().bridge$getY();
         }

         Extension var2 = Bridge.method8()
            .method10(this.field1.bridge$getPosX(), var1, this.field1.bridge$getPosZ());

         for (Bridge2_17 var3 = this.field8.method1(var2);
            var3.bridge$getBlock().bridge$isWater();
            var3 = this.field8.method1(var2)
         ) {
            var2.method1(this.field1.bridge$getPosX(), ++var1, this.field1.bridge$getPosZ());
         }
      } else {
         var1 = (int)Math.floor(this.field1.bridge$getPosY() + 0.5);
      }

      Horsestats20Extension2 var5 = Bridge.method8()
         .method6(this.field1.bridge$getPosX(), var1, this.field1.bridge$getPosZ());
      if (!this.method16(var5)) {
         for (Horsestats20Extension2 var4 : this.method8(this.field1)) {
            if (this.method16(var4)) {
               return super.method12(var4);
            }
         }
      }

      return super.method12(var5);
   }

   @Override
   public boolean method16(Vector3iBridge var1) {
      ItemcounterType2_3 var2 = this.method24(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
      return var2.getMalus() >= 0.0F;
   }

   @Override
   public int method7(com.moonsworth.lunar.client.render.turbo.PathNode[] var1, com.moonsworth.lunar.client.render.turbo.PathNode var2) {
      int var3 = 0;
      com.moonsworth.lunar.client.render.turbo.PathNode var4 = this.method6(var2.x, var2.y, var2.z + 1);
      if (this.method5(var4)) {
         var1[var3++] = var4;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var5 = this.method6(var2.x - 1, var2.y, var2.z);
      if (this.method5(var5)) {
         var1[var3++] = var5;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var6 = this.method6(var2.x + 1, var2.y, var2.z);
      if (this.method5(var6)) {
         var1[var3++] = var6;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var7 = this.method6(var2.x, var2.y, var2.z - 1);
      if (this.method5(var7)) {
         var1[var3++] = var7;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var8 = this.method6(var2.x, var2.y + 1, var2.z);
      if (this.method5(var8)) {
         var1[var3++] = var8;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var9 = this.method6(var2.x, var2.y - 1, var2.z);
      if (this.method5(var9)) {
         var1[var3++] = var9;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var10 = this.method6(var2.x, var2.y + 1, var2.z + 1);
      if (this.method5(var10) && this.method4(var4) && this.method4(var8)) {
         var1[var3++] = var10;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var11 = this.method6(var2.x - 1, var2.y + 1, var2.z);
      if (this.method5(var11) && this.method4(var5) && this.method4(var8)) {
         var1[var3++] = var11;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var12 = this.method6(var2.x + 1, var2.y + 1, var2.z);
      if (this.method5(var12) && this.method4(var6) && this.method4(var8)) {
         var1[var3++] = var12;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var13 = this.method6(var2.x, var2.y + 1, var2.z - 1);
      if (this.method5(var13) && this.method4(var7) && this.method4(var8)) {
         var1[var3++] = var13;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var14 = this.method6(var2.x, var2.y - 1, var2.z + 1);
      if (this.method5(var14) && this.method4(var4) && this.method4(var9)) {
         var1[var3++] = var14;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var15 = this.method6(var2.x - 1, var2.y - 1, var2.z);
      if (this.method5(var15) && this.method4(var5) && this.method4(var9)) {
         var1[var3++] = var15;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var16 = this.method6(var2.x + 1, var2.y - 1, var2.z);
      if (this.method5(var16) && this.method4(var6) && this.method4(var9)) {
         var1[var3++] = var16;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var17 = this.method6(var2.x, var2.y - 1, var2.z - 1);
      if (this.method5(var17) && this.method4(var7) && this.method4(var9)) {
         var1[var3++] = var17;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var18 = this.method6(var2.x + 1, var2.y, var2.z - 1);
      if (this.method5(var18) && this.method4(var7) && this.method4(var6)) {
         var1[var3++] = var18;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var19 = this.method6(var2.x + 1, var2.y, var2.z + 1);
      if (this.method5(var19) && this.method4(var4) && this.method4(var6)) {
         var1[var3++] = var19;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var20 = this.method6(var2.x - 1, var2.y, var2.z - 1);
      if (this.method5(var20) && this.method4(var7) && this.method4(var5)) {
         var1[var3++] = var20;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var21 = this.method6(var2.x - 1, var2.y, var2.z + 1);
      if (this.method5(var21) && this.method4(var4) && this.method4(var5)) {
         var1[var3++] = var21;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var22 = this.method6(var2.x + 1, var2.y + 1, var2.z - 1);
      if (this.method5(var22)
         && this.method4(var18)
         && this.method4(var7)
         && this.method4(var6)
         && this.method4(var8)
         && this.method4(var13)
         && this.method4(var12)) {
         var1[var3++] = var22;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var23 = this.method6(var2.x + 1, var2.y + 1, var2.z + 1);
      if (this.method5(var23)
         && this.method4(var19)
         && this.method4(var4)
         && this.method4(var6)
         && this.method4(var8)
         && this.method4(var10)
         && this.method4(var12)) {
         var1[var3++] = var23;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var24 = this.method6(var2.x - 1, var2.y + 1, var2.z - 1);
      if (this.method5(var24)
         && this.method4(var20)
         && this.method4(var7)
         && this.method4(var5)
         && this.method4(var8)
         && this.method4(var13)
         && this.method4(var11)) {
         var1[var3++] = var24;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var25 = this.method6(var2.x - 1, var2.y + 1, var2.z + 1);
      if (this.method5(var25)
         && this.method4(var21)
         && this.method4(var4)
         && this.method4(var5)
         && this.method4(var8)
         && this.method4(var10)
         && this.method4(var11)) {
         var1[var3++] = var25;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var26 = this.method6(var2.x + 1, var2.y - 1, var2.z - 1);
      if (this.method5(var26)
         && this.method4(var18)
         && this.method4(var7)
         && this.method4(var6)
         && this.method4(var9)
         && this.method4(var17)
         && this.method4(var16)) {
         var1[var3++] = var26;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var27 = this.method6(var2.x + 1, var2.y - 1, var2.z + 1);
      if (this.method5(var27)
         && this.method4(var19)
         && this.method4(var4)
         && this.method4(var6)
         && this.method4(var9)
         && this.method4(var14)
         && this.method4(var16)) {
         var1[var3++] = var27;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var28 = this.method6(var2.x - 1, var2.y - 1, var2.z - 1);
      if (this.method5(var28)
         && this.method4(var20)
         && this.method4(var7)
         && this.method4(var5)
         && this.method4(var9)
         && this.method4(var17)
         && this.method4(var15)) {
         var1[var3++] = var28;
      }

      com.moonsworth.lunar.client.render.turbo.PathNode var29 = this.method6(var2.x - 1, var2.y - 1, var2.z + 1);
      if (this.method5(var29)
         && this.method4(var21)
         && this.method4(var4)
         && this.method4(var5)
         && this.method4(var9)
         && this.method4(var14)
         && this.method4(var15)) {
         var1[var3++] = var29;
      }

      return var3;
   }

   private boolean method4(@Nullable LunarPlusManager var1) {
      return var1 != null && var1.field7 >= 0.0F;
   }

   private boolean method5(@Nullable LunarPlusManager var1) {
      return var1 != null && !var1.closed;
   }

   protected @Nullable LunarPlusManager method6(int var1, int var2, int var3) {
      com.moonsworth.lunar.client.render.turbo.PathNode var4 = null;
      ItemcounterType2_3 var5 = this.method24(var1, var2, var3);
      float var6 = var5.getMalus();
      if (var6 >= 0.0F) {
         var4 = this.method6(var1, var2, var3);
         var4.field8 = var5;
         var4.field7 = Math.max(var4.field7, var6);
         if (var5 == ItemcounterType2_3.WALKABLE) {
            var4.field7++;
         }
      }

      return var4;
   }

   @Override
   public ItemcounterType2_3 method27(com.moonsworth.lunar.client.render.turbo.PathWorldView var1, int var2, int var3, int var4) {
      ItemcounterType2_3 var5 = method4(var1, var2, var3, var4);
      if (var5 == ItemcounterType2_3.OPEN && var3 >= var1.method2().bridge$getMinBuildHeight() + 1) {
         Horsestats20Extension2 var6 = Bridge.method8().method4(var2, var3 - 1, var4);
         ItemcounterType2_3 var7 = method4(var1, var6.bridge$getX(), var6.bridge$getY(), var6.bridge$getZ());
         if (var7 == ItemcounterType2_3.DAMAGE_FIRE || var7 == ItemcounterType2_3.LAVA) {
            var5 = ItemcounterType2_3.DAMAGE_FIRE;
         } else if (var7 == ItemcounterType2_3.DAMAGE_OTHER) {
            var5 = ItemcounterType2_3.DAMAGE_OTHER;
         } else if (var7 == ItemcounterType2_3.COCOA) {
            var5 = ItemcounterType2_3.COCOA;
         } else if (var7 == ItemcounterType2_3.FENCE) {
            if (!var6.equals(var1.method3())) {
               var5 = ItemcounterType2_3.FENCE;
            }
         } else {
            var5 = var7 != ItemcounterType2_3.WALKABLE && var7 != ItemcounterType2_3.OPEN && var7 != ItemcounterType2_3.WATER
               ? ItemcounterType2_3.WALKABLE
               : ItemcounterType2_3.OPEN;
         }
      }

      if (var5 == ItemcounterType2_3.WALKABLE || var5 == ItemcounterType2_3.OPEN) {
         var5 = method27(var1, var2, var3, var4, var5);
      }

      return var5;
   }

   private Iterable<Horsestats20Extension2> method8(com.moonsworth.lunar.client.render.turbo.PathEntity var1) {
      AxisAlignedBBBridge var2 = var1.method7();
      boolean var3 = var2.bridge$getSize() < 1.0;
      if (!var3) {
         BridgeImplementation var7 = Bridge.method8();
         double var5 = var1.bridge$getPosY();
         return List.of(
            var7.method6(var2.bridge$getMinX(), var5, var2.bridge$getMinZ()),
            var7.method6(var2.bridge$getMinX(), var5, var2.bridge$getMaxZ()),
            var7.method6(var2.bridge$getMaxX(), var5, var2.bridge$getMinZ()),
            var7.method6(var2.bridge$getMaxX(), var5, var2.bridge$getMaxZ())
         );
      } else {
         AxisAlignedBBBridge var4 = var2.method12(Math.max(0.0, 1.1F - var2.method14()), Math.max(0.0, 1.1F - var2.method15()), Math.max(0.0, 1.1F - var2.method16()));
         return ThreadModuleDump82.randomPositions(
            var1.method9(),
            10,
            (int)Math.floor(var4.bridge$getMinX()),
            (int)Math.floor(var4.bridge$getMinY()),
            (int)Math.floor(var4.bridge$getMinZ()),
            (int)Math.floor(var4.bridge$getMaxX()),
            (int)Math.floor(var4.bridge$getMaxY()),
            (int)Math.floor(var4.bridge$getMaxZ())
         );
      }
   }
}
