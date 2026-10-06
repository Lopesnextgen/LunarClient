package com.moonsworth.lunar.client.fog.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats12;
import com.moonsworth.lunar.bridge.horsestats.Horsestats15;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20.Extension;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType.Type3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2_3;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_3;
import com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7Impl;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class HologramsIterator extends Holograms_7 {
   private final Object2BooleanMap<Horsestats12> field9 = new Object2BooleanOpenHashMap();
   private final Long2ObjectMap<ItemcounterType2_3> field10 = new Long2ObjectOpenHashMap();
   private final com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7[] field11 = new com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7[Type3.HORIZONTAL
      .size()];

   public void method1(Itemcounter6Extension var1, com.moonsworth.lunar.client.fog.holograms.mixin.Holograms3 var2) {
      super.method1(var1, var2);
   }

   @Override
   public void done() {
      this.field10.clear();
      this.field9.clear();
      super.done();
   }

   @NotNull
   @Override
   public Holograms7Impl method5(int var1, int var2, int var3) {
      return new Holograms7Impl(this.OORHIHOOORHHIRORRICRRORCHRRIIC(var1, var2, var3));
   }

   @Override
   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method6() {
      Extension var1 = Bridge.method8().method9(0, 0, 0);
      Horsestats20Extension2 var2 = this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method8();
      int var3;
      int var4;
      int var5;
      if (var2 == null) {
         var3 = (int)Math.floor(this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosX());
         var4 = (int)Math.floor(this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosY());
         var5 = (int)Math.floor(this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosZ());
      } else {
         var3 = var2.bridge$getX();
         var4 = var2.bridge$getY();
         var5 = var2.bridge$getZ();
      }

      var1.method1(this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosX(), var4, this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosZ());
      Bridge2_17 var6 = this.CHCHORRRHIOCCCRHICROCIRIOOCCII.method1(var1);
      if (this.ROHICROIRIOOHHHRIIOHOCHICHRRCR && this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.isInWater()) {
         while (var6.bridge$getBlock().bridge$isWater()) {
            var1.method1(this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosX(), ++var4, this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosZ());
            var6 = this.CHCHORRRHIOCCCRHICROCIRIOOCCII.method1(var1);
         }

         var4--;
      } else if (this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.isOnGround()) {
         var4 = (int)Math.floor(this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosY() + 0.5);
      } else {
         var1.method1(
            this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosX(),
            this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosY() + 1.0,
            this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosZ()
         );

         while (var1.bridge$getY() > this.CHCHORRRHIOCCCRHICROCIRIOOCCII.method2().bridge$getMinBuildHeight()) {
            var4 = var1.bridge$getY();
            var1.bridge$setPos(var1.bridge$getX(), var1.bridge$getY() - 1, var1.bridge$getZ());
            Bridge2_17 var7 = this.CHCHORRRHIOCCCRHICROCIRIOOCCII.method1(var1);
            if (!var7.bridge$getBlock().bridge$isAir()
               && !var7.bridge$isPathfindable(this.CHCHORRRHIOCCCRHICROCIRIOOCCII.method2(), var1, ItemcounterType_3.LAND)) {
               break;
            }
         }
      }

      var1.bridge$setPos(var3, var4, var5);
      if (!this.method16(var1)) {
         Horsestats12 var8 = this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method7();
         var1.method1(var8.bridge$getMinX(), var4, var8.bridge$getMinZ());
         if (this.method16(var1)) {
            return this.method12(var1);
         }

         var1.method1(var8.bridge$getMinX(), var4, var8.bridge$getMaxX());
         if (this.method16(var1)) {
            return this.method12(var1);
         }

         var1.method1(var8.bridge$getMaxX(), var4, var8.bridge$getMinZ());
         if (this.method16(var1)) {
            return this.method12(var1);
         }

         var1.method1(var8.bridge$getMaxX(), var4, var8.bridge$getMaxZ());
         if (this.method16(var1)) {
            return this.method12(var1);
         }
      }

      return this.method12(Bridge.method8().method4(var2.bridge$getX(), var4, var2.bridge$getZ()));
   }

   @Override
   public int method7(com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7[] var1, com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var2) {
      int var3 = 0;
      int var4 = 0;
      ItemcounterType2_3 var5 = this.method24(var2.x, var2.y + 1, var2.z);
      ItemcounterType2_3 var6 = this.method24(var2.x, var2.y, var2.z);
      if (this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method2(var5) >= 0.0F && var6 != ItemcounterType2_3.STICKY_HONEY) {
         var4 = (int)Math.floor(Math.max(1.0F, this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method3()));
      }

      double var7 = this.method9(Bridge.method8().method4(var2.x, var2.y, var2.z));

      for (HorsestatsType_2 var10 : Type3.HORIZONTAL) {
         com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var11 = this.method11(
            var2.x + var10.getOffsetX(), var2.y, var2.z + var10.getOffsetZ(), var4, var7, var10, var6
         );
         this.field11[var10.getHorizontal()] = var11;
         if (this.method5(var11, var2)) {
            var1[var3++] = var11;
         }
      }

      for (HorsestatsType_2 var14 : Type3.HORIZONTAL) {
         HorsestatsType_2 var15 = var14.rotateYClockwise();
         if (this.method18(var2, this.field11[var14.getHorizontal()], this.field11[var15.getHorizontal()])) {
            com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var12 = this.method11(
               var2.x + var14.getOffsetX() + var15.getOffsetX(), var2.y, var2.z + var14.getOffsetZ() + var15.getOffsetZ(), var4, var7, var14, var6
            );
            if (this.method19(var12)) {
               var1[var3++] = var12;
            }
         }
      }

      return var3;
   }

   public boolean method5(
      @Nullable com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var1, com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var2
   ) {
      return var1 != null && !var1.closed && (var1.field7 >= 0.0F || var2.field7 < 0.0F);
   }

   public boolean method6(com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var1) {
      Horsestats12 var2 = this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method7();
      Horsestats15 var3 = Horsestats15.method2(
         var1.x - this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosX() + var2.method14() / 2.0,
         var1.y - this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosY() + var2.method15() / 2.0,
         var1.z - this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.bridge$getPosZ() + var2.method16() / 2.0
      );
      int var4 = (int)Math.ceil(var3.bridge$lengthVector() / var2.bridge$getSize());
      var3 = var3.method5(1.0F / var4);

      for (int var5 = 1; var5 <= var4; var5++) {
         var2 = var2.method4(var3);
         if (this.method8(var2)) {
            return false;
         }
      }

      return true;
   }

   public static double method7(Itemcounter6 var0, Horsestats20 var1) {
      Horsestats20 var2 = var1.bridge$below();
      Horsestats20Extension2 var3;
      if (var2 instanceof Horsestats20Extension2 var4) {
         var3 = var4;
      } else {
         var3 = Bridge.method8().method4(var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ());
      }

      double var6 = var0.method2(var2).bridge$getCollisionHeight(var0, var3);
      return var2.bridge$getY() + var6;
   }

   public boolean method8(Horsestats12 var1) {
      return this.field9.computeIfAbsent(var1, var1x -> !this.CHCHORRRHIOCCCRHICROCIRIOOCCII.method2().bridge$noCollision(null, (Horsestats12)var1x));
   }

   public double method9(Horsestats20 var1) {
      Itemcounter6 var2 = this.CHCHORRRHIOCCCRHICROCIRIOOCCII.method2();
      return (this.ROHICROIRIOOHHHRIIOHOCHICHRRCR || this.method10()) && var2.method4(var1).bridge$isWater() ? var1.bridge$getY() + 0.5 : method7(var2, var1);
   }

   public boolean method10() {
      return false;
   }

   @Nullable
   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method11(
      int var1, int var2, int var3, int var4, double var5, HorsestatsType_2 var7, ItemcounterType2_3 var8
   ) {
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var9 = null;
      Extension var10 = Bridge.method8().method9(var1, var2, var3);
      double var11 = this.method9(var10);
      if (var11 - var5 > this.method17()) {
         return null;
      }

      ItemcounterType2_3 var13 = this.method24(var1, var2, var3);
      float var14 = this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method2(var13);
      if (var14 >= 0.0F) {
         var9 = this.method14(var1, var2, var3, var13, var14);
      }

      if (method13(var8) && var9 != null && var9.field7 >= 0.0F && !this.method6(var9)) {
         var9 = null;
      }

      if (var13 != ItemcounterType2_3.WALKABLE && (!this.method10() || var13 != ItemcounterType2_3.WATER)) {
         if ((var9 == null || var9.field7 < 0.0F)
            && var4 > 0
            && (var13 != ItemcounterType2_3.FENCE || this.COROCRIHRRHRHRIHORHRIIOCHIRCOC())
            && var13 != ItemcounterType2_3.UNPASSABLE_RAIL
            && var13 != ItemcounterType2_3.TRAPDOOR
            && var13 != ItemcounterType2_3.POWDER_SNOW) {
            var9 = this.method21(var1, var2, var3, var4, var5, var7, var8, var10);
         } else if (!this.method10() && var13 == ItemcounterType2_3.WATER && !this.ROHICROIRIOOHHHRIIOHOCHICHRRCR) {
            var9 = this.method22(var1, var2, var3, var9);
         } else if (var13 == ItemcounterType2_3.OPEN) {
            var9 = this.method23(var1, var2, var3);
         } else if (method13(var13) && var9 == null) {
            var9 = this.method20(var1, var2, var3, var13);
         }
      }

      return var9;
   }

   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method12(Horsestats20 var1) {
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var2 = this.method24(var1);
      var2.field8 = this.method24(var2.x, var2.y, var2.z);
      var2.field7 = this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method2(var2.field8);
      return var2;
   }

   public static boolean method13(ItemcounterType2_3 var0) {
      return var0 == ItemcounterType2_3.FENCE || var0 == ItemcounterType2_3.DOOR_CLOSED;
   }

   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method14(int var1, int var2, int var3, ItemcounterType2_3 var4, float var5) {
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var6 = this.OORHIHOOORHHIRORRICRRORCHRRIIC(var1, var2, var3);
      var6.field8 = var4;
      var6.field7 = Math.max(var6.field7, var5);
      return var6;
   }

   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method15(int var1, int var2, int var3) {
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var4 = this.OORHIHOOORHHIRORRICRRORCHRRIIC(var1, var2, var3);
      var4.field8 = ItemcounterType2_3.BLOCKED;
      var4.field7 = -1.0F;
      return var4;
   }

   public boolean method16(Horsestats20 var1) {
      ItemcounterType2_3 var2 = this.method24(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
      return var2 != ItemcounterType2_3.OPEN && this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method2(var2) >= 0.0F;
   }

   public double method17() {
      return Math.max(1.125, this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method3());
   }

   public boolean method18(
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var1,
      @Nullable com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var2,
      @Nullable com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var3
   ) {
      if (var3 != null
         && var2 != null
         && var3.y <= var1.y
         && var2.y <= var1.y
         && var2.field8 != ItemcounterType2_3.WALKABLE_DOOR
         && var3.field8 != ItemcounterType2_3.WALKABLE_DOOR) {
         boolean var4 = var3.field8 == ItemcounterType2_3.FENCE
            && var2.field8 == ItemcounterType2_3.FENCE
            && this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method7().method14() < 0.5;
         return (var3.y < var1.y || var3.field7 >= 0.0F || var4) && (var2.y < var1.y || var2.field7 >= 0.0F || var4);
      } else {
         return false;
      }
   }

   public boolean method19(@Nullable com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var1) {
      if (var1 == null || var1.closed) {
         return false;
      } else {
         return var1.field8 == ItemcounterType2_3.WALKABLE_DOOR ? false : var1.field7 >= 0.0F;
      }
   }

   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method20(int var1, int var2, int var3, ItemcounterType2_3 var4) {
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var5 = this.OORHIHOOORHHIRORRICRRORCHRRIIC(var1, var2, var3);
      var5.closed = true;
      var5.field8 = var4;
      var5.field7 = var4.getMalus();
      return var5;
   }

   @Nullable
   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method21(
      int var1, int var2, int var3, int var4, double var5, HorsestatsType_2 var7, ItemcounterType2_3 var8, Extension var9
   ) {
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var10 = this.method11(var1, var2 + 1, var3, var4 - 1, var5, var7, var8);
      if (var10 == null) {
         return null;
      }

      if (this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method7().method14() >= 1.0) {
         return var10;
      }

      if (var10.field8 != ItemcounterType2_3.OPEN && var10.field8 != ItemcounterType2_3.WALKABLE) {
         return var10;
      }

      double var11 = var1 - var7.getOffsetX() + 0.5;
      double var13 = var3 - var7.getOffsetZ() + 0.5;
      double var15 = this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method7().method14() / 2.0;
      var9.method1(var11, var2 + 1, var13);
      double var17 = this.method9(var9) + 0.001;
      var9.bridge$setPos(var10.x, var10.y, var10.z);
      double var19 = this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method7().method15() + this.method9(var9) - 0.002;
      Horsestats12 var21 = Horsestats12.method2(var11 - var15, var17, var13 - var15, var11 + var15, var19, var13 + var15);
      return this.method8(var21) ? null : var10;
   }

   @Nullable
   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method22(
      int var1, int var2, int var3, @Nullable com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 var4
   ) {
      var2--;

      while (var2 > ThreadModuleDump63.method8().bridge$getMinBuildHeight()) {
         ItemcounterType2_3 var5 = this.method24(var1, var2, var3);
         if (var5 != ItemcounterType2_3.WATER) {
            return var4;
         }

         var4 = this.method14(var1, var2, var3, var5, this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method2(var5));
         var2--;
      }

      return var4;
   }

   public com.moonsworth.lunar.client.fog.holograms.mixin.Holograms7 method23(int var1, int var2, int var3) {
      for (int var4 = var2 - 1; var4 >= ThreadModuleDump63.method8().bridge$getMinBuildHeight(); var4--) {
         if (var2 - var4 > this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method4()) {
            return this.method15(var1, var4, var3);
         }

         ItemcounterType2_3 var5 = this.method24(var1, var4, var3);
         float var6 = this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC.method2(var5);
         if (var5 != ItemcounterType2_3.OPEN) {
            if (var6 >= 0.0F) {
               return this.method14(var1, var4, var3, var5, var6);
            }

            return this.method15(var1, var4, var3);
         }
      }

      return this.method15(var1, var2, var3);
   }

   public ItemcounterType2_3 method24(int var1, int var2, int var3) {
      Horsestats20Extension2 var4 = Bridge.method8().method4(var1, var2, var3);
      return (ItemcounterType2_3)this.field10
         .computeIfAbsent(
            var4.bridge$asLong(), var4x -> this.method25(this.CHCHORRRHIOCCCRHICROCIRIOOCCII, var1, var2, var3, this.CRHRCHHIIIHRIIOCHOORHOIHIICOHC)
         );
   }

   public ItemcounterType2_3 method25(
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms6 var1,
      int var2,
      int var3,
      int var4,
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms3 var5
   ) {
      Set var6 = this.method26(var1, var2, var3, var4);
      if (var6.contains(ItemcounterType2_3.FENCE)) {
         return ItemcounterType2_3.FENCE;
      }

      if (var6.contains(ItemcounterType2_3.UNPASSABLE_RAIL)) {
         return ItemcounterType2_3.UNPASSABLE_RAIL;
      }

      ItemcounterType2_3 var7 = ItemcounterType2_3.BLOCKED;

      for (ItemcounterType2_3 var9 : var6) {
         if (var5.method2(var9) < 0.0F) {
            return var9;
         }

         if (var5.method2(var9) >= var5.method2(var7)) {
            var7 = var9;
         }
      }

      return this.OIIRHRHOOCIIHRCRCOICRCIHICCRIC <= 1
            && var7 != ItemcounterType2_3.OPEN
            && var5.method2(var7) == 0.0F
            && this.method27(var1, var2, var3, var4) == ItemcounterType2_3.OPEN
         ? ItemcounterType2_3.OPEN
         : var7;
   }

   public Set<ItemcounterType2_3> method26(com.moonsworth.lunar.client.fog.holograms.mixin.Holograms6 var1, int var2, int var3, int var4) {
      EnumSet var5 = EnumSet.noneOf(ItemcounterType2_3.class);

      for (int var6 = 0; var6 < this.OIIRHRHOOCIIHRCRCOICRCIHICCRIC; var6++) {
         for (int var7 = 0; var7 < this.CORIICCHOHRHIRCIIIHCCCCHHICOCO; var7++) {
            for (int var8 = 0; var8 < this.RHOCHCOOOIRHRIOOIIRHRIRICORRRH; var8++) {
               int var9 = var6 + var2;
               int var10 = var7 + var3;
               int var11 = var8 + var4;
               ItemcounterType2_3 var12 = this.method27(var1, var9, var10, var11);
               var5.add(var12);
            }
         }
      }

      return var5;
   }

   public ItemcounterType2_3 method27(com.moonsworth.lunar.client.fog.holograms.mixin.Holograms6 var1, int var2, int var3, int var4) {
      return method28(var1, Bridge.method8().method9(var2, var3, var4));
   }

   public static ItemcounterType2_3 method28(com.moonsworth.lunar.client.fog.holograms.mixin.Holograms6 var0, Extension var1) {
      int var2 = var1.bridge$getX();
      int var3 = var1.bridge$getY();
      int var4 = var1.bridge$getZ();
      ItemcounterType2_3 var5 = method30(var0, var2, var3, var4);
      if (var5 == ItemcounterType2_3.OPEN && var3 >= var0.method2().bridge$getMinBuildHeight() + 1) {
         return switch (method30(var0, var2, var3 - 1, var4)) {
            case OPEN, WATER, LAVA, WALKABLE -> ItemcounterType2_3.OPEN;
            case DAMAGE_FIRE -> ItemcounterType2_3.DAMAGE_FIRE;
            case DAMAGE_OTHER -> ItemcounterType2_3.DAMAGE_OTHER;
            case STICKY_HONEY -> ItemcounterType2_3.STICKY_HONEY;
            case POWDER_SNOW -> ItemcounterType2_3.DANGER_POWDER_SNOW;
            case DAMAGE_CAUTIOUS -> ItemcounterType2_3.DAMAGE_CAUTIOUS;
            case TRAPDOOR -> ItemcounterType2_3.DANGER_TRAPDOOR;
            default -> method29(var0, var2, var3, var4, ItemcounterType2_3.WALKABLE);
         };
      } else {
         return var5;
      }
   }

   public static ItemcounterType2_3 method29(
      com.moonsworth.lunar.client.fog.holograms.mixin.Holograms6 var0, int var1, int var2, int var3, ItemcounterType2_3 var4
   ) {
      for (int var5 = -1; var5 <= 1; var5++) {
         for (int var6 = -1; var6 <= 1; var6++) {
            for (int var7 = -1; var7 <= 1; var7++) {
               if (var5 != 0 || var7 != 0) {
                  ItemcounterType2_3 var8 = method30(var0, var1 + var5, var2 + var6, var3 + var7);
                  if (var8 == ItemcounterType2_3.DAMAGE_OTHER) {
                     return ItemcounterType2_3.DANGER_OTHER;
                  }

                  if (var8 == ItemcounterType2_3.DAMAGE_FIRE || var8 == ItemcounterType2_3.LAVA) {
                     return ItemcounterType2_3.DANGER_FIRE;
                  }

                  if (var8 == ItemcounterType2_3.WATER) {
                     return ItemcounterType2_3.WATER_BORDER;
                  }

                  if (var8 == ItemcounterType2_3.DAMAGE_CAUTIOUS) {
                     return ItemcounterType2_3.DAMAGE_CAUTIOUS;
                  }
               }
            }
         }
      }

      return var4;
   }

   public static ItemcounterType2_3 method30(com.moonsworth.lunar.client.fog.holograms.mixin.Holograms6 var0, int var1, int var2, int var3) {
      var0.method4().bridge$setPos(var1, var2, var3);
      return var0.method2().bridge$getPathTypeFromState(var0.method4());
   }
}
