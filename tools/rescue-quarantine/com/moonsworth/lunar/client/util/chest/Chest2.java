package com.moonsworth.lunar.client.util.chest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeType2_4;
import com.moonsworth.lunar.bridge.Bridge_61;
import com.moonsworth.lunar.bridge.MixinHelper_14;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.bridge.horsestats.Horsestats12;
import com.moonsworth.lunar.bridge.horsestats.Horsestats14;
import com.moonsworth.lunar.bridge.horsestats.Horsestats15;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.bridge.horsestats.Horsestats30;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsHandler3;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsHandler4;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsHandler5;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsHandler6;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsHandler8;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_3;
import com.moonsworth.lunar.bridge.horsestats.Horsestats.Type;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20.Extension;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsHandler8.Data;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.feature.ModuleType;
import com.moonsworth.lunar.client.feature.ModuleType3;
import com.moonsworth.lunar.client.fog.holograms.Holograms12;
import com.moonsworth.lunar.client.fog.holograms.Holograms16;
import com.moonsworth.lunar.client.fog.holograms.click.Click;
import com.moonsworth.lunar.client.fog.holograms.nameplate.FogHandler;
import com.moonsworth.lunar.client.fog.holograms.nameplate.Nameplate;
import com.moonsworth.lunar.client.fov.Fov11;
import com.moonsworth.lunar.client.fov.Gui2Iterator;
import com.moonsworth.lunar.client.fov.mixin.Gui2Handler;
import com.moonsworth.lunar.client.fov.mixin.Gui2Handler3;
import com.moonsworth.lunar.client.fov.mixin.Gui2Type;
import com.moonsworth.lunar.client.inactive.mixin.holograms.Holograms;
import com.moonsworth.lunar.client.inactive.rewindhandlers.Rewindhandlers2_2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump91;
import com.moonsworth.lunar.client.util.chest.mixin.ChestHandler;
import com.moonsworth.lunar.client.util.chest.mixin.ChestHandler2;
import com.moonsworth.lunar.client.util.chest.mixin.ChestHandler3;
import com.moonsworth.lunar.client.util.click.BridgeExtension2;
import com.moonsworth.lunar.client.util.click.BridgeExtension3;
import com.moonsworth.lunar.client.util.click.Click8;
import com.moonsworth.lunar.client.util.colorsaturation.Colorsaturation;
import com.moonsworth.lunar.client.util.colorsaturation.Colorsaturation2;
import com.moonsworth.lunar.config.Config;
import it.unimi.dsi.fastutil.Pair;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lombok.Generated;
import org.joml.Matrix4f;
import org.joml.Vector2d;
import org.joml.Vector2i;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.joml.Vector4d;

public final class Chest2 {
   private static final double field1 = 16.0;

   public static <T extends Horsestats_3<T, ? extends T>> T method1(
      Itemcounter6 var0, Horsestats15 var1, Horsestats15 var2, @Nullable ChestHandler2 var3, BiFunction<Itemcounter6, Horsestats20, T> var4, Supplier<T> var5
   ) {
      if (var1.equals(var2)) {
         return (T)var5.get();
      }

      double var6 = Horsestats30.lerp(-1.0E-7, var2.bridge$xCoord(), var1.bridge$xCoord());
      double var8 = Horsestats30.lerp(-1.0E-7, var2.bridge$yCoord(), var1.bridge$yCoord());
      double var10 = Horsestats30.lerp(-1.0E-7, var2.bridge$zCoord(), var1.bridge$zCoord());
      double var12 = Horsestats30.lerp(-1.0E-7, var1.bridge$xCoord(), var2.bridge$xCoord());
      double var14 = Horsestats30.lerp(-1.0E-7, var1.bridge$yCoord(), var2.bridge$yCoord());
      double var16 = Horsestats30.lerp(-1.0E-7, var1.bridge$zCoord(), var2.bridge$zCoord());
      int var18 = Horsestats30.method4(var12);
      int var19 = Horsestats30.method4(var14);
      int var20 = Horsestats30.method4(var16);
      Extension var21 = Bridge.method8().method9(var18, var19, var20);
      Horsestats_3 var22 = (Horsestats_3)var4.apply(var0, var21);
      if (var22 != null) {
         return (T)var22;
      }

      double var23 = var6 - var12;
      double var25 = var8 - var14;
      double var27 = var10 - var16;
      int var29 = Horsestats30.sign(var23);
      int var30 = Horsestats30.sign(var25);
      int var31 = Horsestats30.sign(var27);
      double var32 = var29 == 0 ? Double.MAX_VALUE : var29 / var23;
      double var34 = var30 == 0 ? Double.MAX_VALUE : var30 / var25;
      double var36 = var31 == 0 ? Double.MAX_VALUE : var31 / var27;
      double var38 = var32 * (var29 > 0 ? 1.0 - Horsestats30.method10(var12) : Horsestats30.method10(var12));
      double var40 = var34 * (var30 > 0 ? 1.0 - Horsestats30.method10(var14) : Horsestats30.method10(var14));
      double var42 = var36 * (var31 > 0 ? 1.0 - Horsestats30.method10(var16) : Horsestats30.method10(var16));
      byte var44 = 0;
      boolean var45 = var3 != null && var3.method1();
      if (var45) {
         double var46 = Math.abs(var23);
         double var48 = Math.abs(var25);
         double var50 = Math.abs(var27);
         if (var46 == var50) {
            var44 = (byte)(var44 | 1);
         }

         if (var46 == var48) {
            var44 = (byte)(var44 | 2);
         }

         if (var50 == var48) {
            var44 = (byte)(var44 | 4);
         }
      }

      while (var38 <= 1.0 || var40 <= 1.0 || var42 <= 1.0) {
         if (var38 < var40) {
            if (var38 < var42) {
               var18 += var29;
               var38 += var32;
            } else {
               var20 += var31;
               var42 += var36;
            }
         } else if (var40 < var42) {
            var19 += var30;
            var40 += var34;
         } else {
            var20 += var31;
            var42 += var36;
         }

         var21.bridge$setPos(var18, var19, var20);
         var22 = (Horsestats_3)var4.apply(var0, var21);
         if (var45) {
            if (var22 == null && (var44 & 1L) != 0L) {
               var21.bridge$setPos(var20, var19, var18);
               var22 = (Horsestats_3)var4.apply(var0, var21);
            }

            if (var22 == null && (var44 & 2L) != 0L) {
               var21.bridge$setPos(var19, var18, var20);
               var22 = (Horsestats_3)var4.apply(var0, var21);
            }

            if (var22 == null && (var44 & 4L) != 0L) {
               var21.bridge$setPos(var18, var20, var19);
               var22 = (Horsestats_3)var4.apply(var0, var21);
            }
         }

         if (var22 != null) {
            return (T)var22;
         }
      }

      return (T)var5.get();
   }

   public static <T extends Horsestats_3<T, ? extends T>> T method2(Itemcounter6 var0, SExtension<Itemcounter6, Horsestats20, T, ChestHandler2> var1) {
      var1.method1();
      return method1(var0, var1.method4(), var1.method5(), (ChestHandler2)var1.method3(), var1.method6(), var1.method7());
   }

   public static HorsestatsHandler3 method3(
      Itemcounter6 var0,
      Horsestats15 var1,
      Horsestats15 var2,
      BiFunction<Itemcounter6, Itemcounter2, HorsestatsHandler3> var3,
      Supplier<HorsestatsHandler3> var4
   ) {
      if (var1.equals(var2)) {
         return (HorsestatsHandler3)var4.get();
      }

      double var5 = Horsestats30.lerp(-1.0E-7, var2.bridge$xCoord(), var1.bridge$xCoord());
      double var7 = Horsestats30.lerp(-1.0E-7, var2.bridge$zCoord(), var1.bridge$zCoord());
      double var9 = Horsestats30.lerp(-1.0E-7, var1.bridge$xCoord(), var2.bridge$xCoord());
      double var11 = Horsestats30.lerp(-1.0E-7, var1.bridge$zCoord(), var2.bridge$zCoord());
      int var13 = Horsestats30.method4(var9 / 16.0);
      int var14 = Horsestats30.method4(var11 / 16.0);
      Vector2i var15 = new Vector2i(var13, var14);
      Itemcounter2 var16 = var0.bridge$getChunk(var15.x, var15.y);
      HorsestatsHandler3 var17 = (HorsestatsHandler3)var3.apply(var0, var16);
      if (var17 != null) {
         return var17;
      }

      double var18 = var5 - var9;
      double var20 = var7 - var11;
      int var22 = Horsestats30.sign(var18);
      int var23 = Horsestats30.sign(var20);
      double var24 = var22 == 0 ? Double.MAX_VALUE : var22 * 16.0 / var18;
      double var26 = var23 == 0 ? Double.MAX_VALUE : var23 * 16.0 / var20;
      double var28 = var24 * (var22 > 0 ? 1.0 - Horsestats30.method10(var9 / 16.0) : Horsestats30.method10(var9 / 16.0));
      double var30 = var26 * (var23 > 0 ? 1.0 - Horsestats30.method10(var11 / 16.0) : Horsestats30.method10(var11 / 16.0));

      while (var28 <= 1.0 || var30 <= 1.0) {
         if (var28 < var30) {
            var13 += var22;
            var28 += var24;
         } else {
            var14 += var23;
            var30 += var26;
         }

         var15.set(var13, var14);
         var16 = var0.bridge$getChunk(var15.x, var15.y);
         var17 = (HorsestatsHandler3)var3.apply(var0, var16);
         if (var17 != null) {
            return var17;
         }
      }

      return (HorsestatsHandler3)var4.get();
   }

   public static HorsestatsHandler3 method4(
      Itemcounter6 var0, SExtension<Itemcounter6, Itemcounter2, HorsestatsHandler3, com.moonsworth.lunar.client.util.chest.mixin.Chest> var1
   ) {
      var1.method1();
      return method3(var0, var1.method4(), var1.method5(), var1.method6(), var1.method7());
   }

   @Nullable
   public static HorsestatsHandler6 method5(
      Itemcounter6 var0,
      Horsestats15 var1,
      Horsestats15 var2,
      BiFunction<Itemcounter6, BridgeExtension, HorsestatsHandler6> var3,
      @Nullable ChestHandler3 var4,
      Supplier<HorsestatsHandler6> var5
   ) {
      if (var1.equals(var2)) {
         return (HorsestatsHandler6)var5.get();
      }

      Horsestats15 var6 = method19(var1, var2);
      double var7 = var1.method7(var2);
      AtomicReference var9 = new AtomicReference();
      method3(
         var0,
         var1,
         var2,
         (var8, var9x) -> {
            if (!var8.bridge$isChunkLoaded(var9x.bridge$getX(), var9x.bridge$getZ())) {
               return HorsestatsHandler3.method5();
            }

            int var10x = var9x.bridge$getX() << 4;
            int var11 = var9x.bridge$getZ() << 4;
            Horsestats12 var12 = Bridge.method8()
               .method45(var10x, var0.bridge$getMinBuildHeight(), var11, var10x + 16, var0.bridge$getMaxBuildHeight(), var11 + 16);
            List var13 = var9x.bridge$getEntities(var12, var0xx -> true);
            float var14 = Float.MAX_VALUE;
            BridgeExtension var15 = null;

            for (BridgeExtension var17 : var13) {
               Horsestats12 var21;
               if (var4 != null) {
                  var21 = var17.bridge$getBoundingBox(var4.method3());
                  var21 = method6(var21, var4);
               } else {
                  var21 = var17.bridge$getBoundingBox();
               }

               float var19 = method21(var1, var6, var21);
               if (var19 != -1.0F && var19 <= var7) {
                  HorsestatsHandler6 var20 = (HorsestatsHandler6)var3.apply(var8, var17);
                  if (var20 != null && var20.RRCRHHOHORCIIHRIHRCHIRHRRIHIRR() != Type.MISS && var19 < var14) {
                     var14 = var19;
                     var15 = var17;
                  }
               }
            }

            if (var15 != null) {
               var9.set(HorsestatsHandler6.method3(var15));
               return HorsestatsHandler3.method5();
            } else {
               return null;
            }
         },
         HorsestatsHandler3::method5
      );
      HorsestatsHandler6 var10 = (HorsestatsHandler6)var9.get();
      return var10 == null ? HorsestatsHandler6.method6() : var10;
   }

   private static Horsestats12 method6(Horsestats12 var0, ChestHandler3 var1) {
      if (var1.method1()
         && var0.bridge$getMaxX() - var0.bridge$getMinX() == 0.0
         && var0.bridge$getMaxY() - var0.bridge$getMinY() == 0.0
         && var0.bridge$getMaxZ() - var0.bridge$getMinZ() == 0.0) {
         float var3 = Math.max(0.5F, var1.method2());
         return Bridge.method8()
            .method45(
               var0.bridge$getMinX() - var3,
               var0.bridge$getMinY() - var3,
               var0.bridge$getMinZ() - var3,
               var0.bridge$getMaxX() + var3,
               var0.bridge$getMaxY() + var3,
               var0.bridge$getMaxZ() + var3
            );
      } else if (var1.method2() != 0.0F) {
         float var2 = var1.method2();
         return Bridge.method8()
            .method45(
               var0.bridge$getMinX() - var2,
               var0.bridge$getMinY() - var2,
               var0.bridge$getMinZ() - var2,
               var0.bridge$getMaxX() + var2,
               var0.bridge$getMaxY() + var2,
               var0.bridge$getMaxZ() + var2
            );
      } else {
         return var0;
      }
   }

   public static HorsestatsHandler6 method7(
      Itemcounter6 var0, SExtension<Itemcounter6, BridgeExtension, HorsestatsHandler6, com.moonsworth.lunar.client.util.chest.mixin.Chest> var1
   ) {
      var1.method1();
      return method5(var0, var1.method4(), var1.method5(), var1.method6(), (ChestHandler3)var1.method3(), var1.method7());
   }

   @Nullable
   public static HorsestatsHandler8 method8(
      Bridge5_11 var0,
      Horsestats15 var1,
      Horsestats15 var2,
      @Nullable ChestHandler var3,
      BiFunction<Bridge5_11, HorsestatsHandler8, HorsestatsHandler8> var4,
      Supplier<HorsestatsHandler8> var5
   ) {
      if (var1.equals(var2)) {
         return (HorsestatsHandler8)var5.get();
      }

      if (ThreadModuleDump63.method4().method45().method9(var0)) {
         return (HorsestatsHandler8)var5.get();
      }

      UUID var6 = var0.bridge$getUniqueID();
      Holograms12 var7 = ThreadModuleDump63.method4().method53();
      List var8 = var7.method18(var6);
      BridgeExtension2_7 var9 = ((MixinHelper_6)Bridge.method9().bridge$getEntityRenderDispatcher().bridge$getSkinMap().get(var0.bridge$getSkinType()))
         .bridge$getMainModel();
      HorsestatsHandler8 var10 = null;
      double var11 = 0.0;
      double var13 = var1.method7(var2);

      for (Gui2Handler3 var16 : var8) {
         Gui2Iterator var17 = (Gui2Iterator)var16.method4();
         if (var17.method6().isPresent() && var17.RHIHHIHIIICHHCRHRCIRROIHOHRIIH().canShowCosmetic() && var17.method5().isPresent()) {
            Holograms var18 = var17.method5().get();
            if (var18.IIRCROCIRCCHHIHRIOCROOOCRHIHHO() != null) {
               Optional var19 = var18.RICOCIIIHIRIRHCIICHCIIRCRIRIRO(var18.method1(var17, var18.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator()));
               if (!var19.isEmpty()) {
                  Rewindhandlers2_2 var20 = (Rewindhandlers2_2)var19.get();
                  HorsestatsHandler8 var21 = method16(var16, var17, var20, var3, var9, var0, var1, var2, var4, var18);
                  if (var21 instanceof Data var22) {
                     double var23 = new Vector3d(var22.method9()).sub(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord()).length();
                     if (var23 <= var13 && (var10 == null || var23 < var11)) {
                        var10 = var21;
                        var11 = var23;
                     }
                  } else if (var21 != null && var10 == null) {
                     var10 = var21;
                     var11 = Double.MAX_VALUE;
                  }
               }
            }
         }
      }

      if (ThreadModuleDump63.MC_VERSION <= 25) {
         var9.bridge$setSneak(var0.bridge$isSneaking());
      }

      List var30 = var7.method19(var6);
      boolean var31 = false;
      boolean var32 = false;
      MixinHelper_14 var33 = var0.bridge$getArmor(ModuleType.BODY.getArmorSlot());
      if (var33 != null && var33.bridge$getLunarItemType() == BridgeType2_4.ELYTRA) {
         var32 = true;
      }

      if (var30 != null) {
         for (Gui2Handler3 var36 : var30) {
            if (var36 != null && var36.method4().method10().canShowCosmetic()) {
               Gui2Handler var38 = var36.method4();
               Horsestats12 var24;
               ModuleType3 var40;
               Colorsaturation2 var42;
               if (var38.method10() == Gui2Type.WINGS) {
                  if (var32) {
                     continue;
                  }

                  var40 = null;
                  var42 = null;
                  var24 = Bridge.method8().method45(-5.6, -2.4, -0.4, 5.6, 2.4, 5.8);
               } else if (var38.method10() == Gui2Type.CLOAK) {
                  if (var32) {
                     continue;
                  }

                  var31 = true;
                  var40 = null;
                  var42 = null;
                  var24 = Bridge.method8().method45(-0.3, 0.1, 0.15, 0.3, 1.0, 0.2);
               } else {
                  var40 = var7.method7(var38.method2());
                  if (var40 == null || !var40.method1() && var40.method5().hasArmorSlot() && var0.bridge$getArmor(var40.method5().getArmorSlot()) != null) {
                     continue;
                  }

                  FogHandler var25 = ThreadModuleDump63.method4().method96();
                  Optional var26 = var25.method1(var40.method2());
                  if (var26.flatMap(Nameplate::method2).isEmpty()) {
                     continue;
                  }

                  var42 = var26.flatMap(Nameplate::method2).get();
                  var24 = var42.method6();
               }

               HorsestatsHandler8 var45 = method15(var36, var24, var40, var42, var3, var9, var0, var1, var2, var4);
               if (var45 instanceof Data var47) {
                  double var27 = new Vector3d(var47.method9()).sub(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord()).length();
                  if (var27 <= var13 && (var10 == null || var27 < var11)) {
                     var10 = var45;
                     var11 = var27;
                  }
               } else if (var45 != null && var10 == null) {
                  var10 = var45;
                  var11 = Double.MAX_VALUE;
               }
            }
         }
      }

      if (!var32 && !var31) {
         Horsestats14 var35 = var0.bridge$getCapeLocation();
         if (var35 != null) {
            Horsestats12 var37 = Bridge.method8().method45(-0.3, 0.1, 0.15, 0.3, 1.0, 0.2);
            String var39 = var0.bridge$isOptifineCape() ? "Optifine Cape" : "Cape";
            Gui2Handler var41 = new Gui2Handler(
               -1L, var39, var35, Gui2Type.CLOAK, false, -1L, null, Collections.emptyList(), ImmutableList.of(), false, null, null, false
            );
            HorsestatsHandler8 var43 = method15(new Gui2Handler3(var41, new JsonObject()), var37, null, null, null, var9, var0, var1, var2, var4);
            if (var43 instanceof Data var44) {
               double var46 = new Vector3d(var44.method9()).sub(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord()).length();
               if (var46 <= var13 && (var10 == null || var46 < var11)) {
                  var10 = var43;
               }
            } else if (var43 != null && var10 == null) {
               var10 = var43;
               var11 = Double.MAX_VALUE;
            }
         }
      }

      return var10 != null ? var10 : (HorsestatsHandler8)var5.get();
   }

   public static HorsestatsHandler8 method9(Bridge5_11 var0, SExtension<Bridge5_11, HorsestatsHandler8, HorsestatsHandler8, ChestHandler> var1) {
      var1.method1();
      return method8(var0, var1.method4(), var1.method5(), (ChestHandler)var1.method3(), var1.method6(), var1.method7());
   }

   @Nullable
   public static HorsestatsHandler4 method10(
      List<? extends Click> var0,
      Horsestats15 var1,
      Horsestats15 var2,
      BiFunction<List<? extends Click>, HorsestatsHandler4, HorsestatsHandler4> var3,
      Supplier<HorsestatsHandler4> var4
   ) {
      if (var1.equals(var2)) {
         return (HorsestatsHandler4)var4.get();
      }

      HorsestatsHandler4 var5 = null;
      double var6 = 0.0;
      double var8 = var1.method7(var2);

      for (Click var11 : var0) {
         HorsestatsHandler4 var12 = method17(var11, var1, var2, var3);
         if (var12 instanceof com.moonsworth.lunar.bridge.horsestats.HorsestatsHandler4.Data var13) {
            double var14 = new Vector3d(var13.method8()).sub(var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord()).length();
            if (!(var14 > var8) && (var5 == null || var14 < var6)) {
               var5 = var12;
               var6 = var14;
            }
         } else if (var12 != null && var5 == null) {
            var5 = var12;
            var6 = Double.MAX_VALUE;
         }
      }

      return var5 != null ? var5 : (HorsestatsHandler4)var4.get();
   }

   public static HorsestatsHandler4 method11(
      List<? extends Click> var0,
      SExtension<List<? extends Click>, HorsestatsHandler4, HorsestatsHandler4, com.moonsworth.lunar.client.util.chest.mixin.Chest> var1
   ) {
      var1.method1();
      return method10(var0, var1.method4(), var1.method5(), var1.method6(), var1.method7());
   }

   @Nullable
   public static HorsestatsHandler5 method12(
      Collection<Bridge_61> var0,
      Horsestats15 var1,
      Horsestats15 var2,
      BiFunction<Collection<Bridge_61>, HorsestatsHandler5, HorsestatsHandler5> var3,
      Supplier<HorsestatsHandler5> var4
   ) {
      if (var1.equals(var2)) {
         return (HorsestatsHandler5)var4.get();
      }

      if (var0.isEmpty()) {
         return (HorsestatsHandler5)var4.get();
      }

      HorsestatsHandler5 var5 = null;
      double var6 = Double.MAX_VALUE;
      double var8 = var1.method7(var2);
      Horsestats15 var10 = method19(var1, var2);

      for (Bridge_61 var12 : var0) {
         float var13 = var12.bridge$getWidth() * 0.5F;
         float var14 = var12.bridge$getHeight() * 0.5F;
         double var15 = var12.bridge$getPosX();
         double var17 = var12.bridge$getPosY();
         double var19 = var12.bridge$getPosZ();
         Horsestats12 var21 = Horsestats12.method2(var15 - var13, var17, var19 - var13, var15 + var13, var17 + var14, var19 + var13).method11(0.2F);
         float var22 = method21(var1, var10, var21);
         if (var22 != -1.0F && var22 <= var8) {
            HorsestatsHandler5 var23 = (HorsestatsHandler5)var3.apply(null, HorsestatsHandler5.method3(var12));
            if (var23 instanceof com.moonsworth.lunar.bridge.horsestats.HorsestatsHandler5.Data) {
               if (var5 == null) {
                  var5 = var23;
                  var6 = var22;
               } else if (var22 < var6) {
                  var5 = var23;
                  var6 = var22;
               }
            }
         }
      }

      return var5 != null ? var5 : (HorsestatsHandler5)var4.get();
   }

   public static HorsestatsHandler5 method13(
      Collection<Bridge_61> var0,
      SExtension<Collection<Bridge_61>, HorsestatsHandler5, HorsestatsHandler5, com.moonsworth.lunar.client.util.chest.mixin.Chest> var1
   ) {
      var1.method1();
      return method12(var0, var1.method4(), var1.method5(), var1.method6(), var1.method7());
   }

   private static Matrix4f method14(Bridge5_11 var0) {
      Matrix4f var1 = new Matrix4f();
      if (var0.bridge$isSneaking() && !Bridge.getMinecraftVersion().equals(Config.field1)) {
         var1.translate(0.0F, 0.2F, 0.0F);
      }

      var1.translate((float)var0.bridge$getPosX(), (float)var0.bridge$getPosY(), (float)var0.bridge$getPosZ());
      var1.rotate((float)Math.toRadians(180.0F - var0.bridge$getBodyRot()), 0.0F, 1.0F, 0.0F);
      var1.scale(-1.0F, -1.0F, 1.0F);
      var1.translate(0.0F, -1.501F, 0.0F);
      return var1;
   }

   @Nullable
   private static HorsestatsHandler8 method15(
      Gui2Handler3 var0,
      Horsestats12 var1,
      ModuleType3 var2,
      Colorsaturation2 var3,
      @Nullable ChestHandler var4,
      BridgeExtension2_7 var5,
      Bridge5_11 var6,
      Horsestats15 var7,
      Horsestats15 var8,
      BiFunction<Bridge5_11, HorsestatsHandler8, HorsestatsHandler8> var9
   ) {
      Gui2Handler var10 = var0.method4();
      if (var1 == null) {
         return null;
      }

      Matrix4f var11 = method14(var6);
      if (var2 != null) {
         ModuleType var12 = var2.method5();
         var12.translateModern(var5).transform(var11, var6, 0.0F);
         boolean var13 = var12 == ModuleType.HEAD;
         if (var13) {
            var11.rotate(1.5708F, 0.0F, 1.0F, 0.0F);
            float var14 = var0.method6().getHatHeightOffset();
            if (Bridge.getMinecraftVersion().method23()) {
               var14 *= -1.0F;
            }

            var11.translate(0.0F, var14 * 0.1F, 0.0F);
         }

         for (ThreadModuleDump91 var15 : var2.method6()) {
            if (var15.getCondition().applies(var6, var0)) {
               var15.transform(var11, var6, 0.0F);
            }
         }

         var11.scale(var2.getScale(), var2.getScale(), var2.getScale());
      } else if (var10.method10() == Gui2Type.WINGS) {
         var11.scale(0.13F, 0.13F, 0.13F);
         var11.translate(0.0F, 0.75F, 1.0F);
      }

      Vector3d var17 = method24(var7, var8, var1, var11);
      if (var17 != null) {
         if (var2 != null && var3 != null && var4 != null && var4.method1()) {
            UnmodifiableIterator var18 = var3.method5().iterator();

            while (var18.hasNext()) {
               Colorsaturation var20 = (Colorsaturation)var18.next();
               Horsestats12 var21 = var20.method4();
               Vector3d var16 = method24(var7, var8, var21, var11);
               if (var16 != null) {
                  return (HorsestatsHandler8)var9.apply(var6, HorsestatsHandler8.method2(var0, var21, var17, var11));
               }
            }

            return null;
         } else {
            return (HorsestatsHandler8)var9.apply(var6, HorsestatsHandler8.method2(var0, var1, var17, var11));
         }
      } else {
         return null;
      }
   }

   @Nullable
   private static HorsestatsHandler8 method16(
      Gui2Handler3 var0,
      Gui2Iterator var1,
      Rewindhandlers2_2 var2,
      @Nullable ChestHandler var3,
      BridgeExtension2_7 var4,
      Bridge5_11 var5,
      Horsestats15 var6,
      Horsestats15 var7,
      BiFunction<Bridge5_11, HorsestatsHandler8, HorsestatsHandler8> var8,
      Holograms var9
   ) {
      Horsestats12 var10 = var2.method1();
      if (var10 == null) {
         return null;
      }

      Click8 var11 = Bridge.getMinecraftVersion().method21() ? new BridgeExtension3() : new BridgeExtension2();
      Fov11.method5(var5, var11);
      Fov11.method6(var11, var0, var1, var4, var5, var2, false, 0.0F, false, false, var9.IIRCROCIRCCHHIHRIOCROOOCRHIHHO(), var0x -> {});
      Matrix4f var12 = var11.method53();
      Vector3d var13 = method24(var6, var7, var10, var12);
      if (var13 != null) {
         if (var3 != null && var3.method1()) {
            AtomicReference var14 = new AtomicReference();
            var2.method4(var4x -> {
               Vector3d var5x = method24(var6, var7, var4x, var12);
               if (var5x != null) {
                  var14.set(Pair.of(var5x, var4x));
                  return true;
               } else {
                  return false;
               }
            });
            Pair var15 = (Pair)var14.get();
            return var15 != null
               ? (HorsestatsHandler8)var8.apply(var5, HorsestatsHandler8.method2(var0, (Horsestats12)var15.second(), (Vector3d)var15.first(), var12))
               : null;
         } else {
            return (HorsestatsHandler8)var8.apply(var5, HorsestatsHandler8.method2(var0, var10, var13, var12));
         }
      } else {
         return null;
      }
   }

   @Nullable
   private static HorsestatsHandler4 method17(
      Click var0, Horsestats15 var1, Horsestats15 var2, BiFunction<List<? extends Click>, HorsestatsHandler4, HorsestatsHandler4> var3
   ) {
      Matrix4f var4 = Holograms16.method28(var0.method2(), var0.method3(), var0.getRotation());
      com.moonsworth.lunar.client.fog.holograms.click.Gui2Handler var5 = var0.method1();
      Horsestats12 var6 = Horsestats12.method2(0.0, 0.0, 0.0, var5.getWidth(), var5.getHeight(), 0.0);
      Vector3d var7 = method24(var1, var2, var6, var4);
      return var7 != null ? (HorsestatsHandler4)var3.apply(null, HorsestatsHandler4.method2(var0, var7)) : null;
   }

   private static double method18(double var0, double var2, double var4) {
      return Math.sqrt(var0 * var0 + var2 * var2 + var4 * var4);
   }

   private static Horsestats15 method19(Horsestats15 var0, Horsestats15 var1) {
      Horsestats15 var2 = Horsestats15.method2(
         var1.bridge$xCoord() - var0.bridge$xCoord(), var1.bridge$yCoord() - var0.bridge$yCoord(), var1.bridge$zCoord() - var0.bridge$zCoord()
      );
      double var3 = method18(var2.bridge$xCoord(), var2.bridge$yCoord(), var2.bridge$zCoord());
      return Horsestats15.method2(var2.bridge$xCoord() / var3, var2.bridge$yCoord() / var3, var2.bridge$zCoord() / var3);
   }

   private static Vector3d method20(Vector3d var0, Vector3d var1, float var2) {
      return var1.mul(var2).add(var0);
   }

   private static float method21(Horsestats15 var0, Horsestats15 var1, Horsestats12 var2) {
      return method23(var0.bridge$xCoord(), var0.bridge$yCoord(), var0.bridge$zCoord(), var1.bridge$xCoord(), var1.bridge$yCoord(), var1.bridge$zCoord(), var2);
   }

   private static float method22(Vector3d var0, Vector3d var1, Horsestats12 var2) {
      return method23(var0.x, var0.y, var0.z, var1.x, var1.y, var1.z, var2);
   }

   private static float method23(double var0, double var2, double var4, double var6, double var8, double var10, Horsestats12 var12) {
      double var13 = 1.0 / var6;
      double var15 = 1.0 / var8;
      double var17 = 1.0 / var10;
      double var19 = (var12.bridge$getMinX() - var0) * var13;
      double var21 = (var12.bridge$getMaxX() - var0) * var13;
      double var23 = (var12.bridge$getMinY() - var2) * var15;
      double var25 = (var12.bridge$getMaxY() - var2) * var15;
      double var27 = (var12.bridge$getMinZ() - var4) * var17;
      double var29 = (var12.bridge$getMaxZ() - var4) * var17;
      double var31 = Math.min(Math.min(Math.max(var19, var21), Math.max(var23, var25)), Math.max(var27, var29));
      if (var31 < 0.0) {
         return -1.0F;
      }

      double var33 = Math.max(Math.max(Math.min(var19, var21), Math.min(var23, var25)), Math.min(var27, var29));
      return var33 > var31 ? -1.0F : (float)(var33 < 0.0 ? var31 : var33);
   }

   @Nullable
   private static Vector3d method24(Horsestats15 var0, Horsestats15 var1, Horsestats12 var2, Matrix4f var3) {
      Horsestats15 var4 = method19(var0, var1);
      return method25(
         new Vector3d(var0.bridge$xCoord(), var0.bridge$yCoord(), var0.bridge$zCoord()),
         new Vector3d(var4.bridge$xCoord(), var4.bridge$yCoord(), var4.bridge$zCoord()),
         var2,
         var3
      );
   }

   @Nullable
   private static Vector3d method25(Vector3d var0, Vector3d var1, Horsestats12 var2, Matrix4f var3) {
      Matrix4f var4 = new Matrix4f(var3).invert();
      Vector4d var5 = new Vector4d(var0, 1.0).mul(var4);
      Vector3d var6 = new Vector3d(var5.x, var5.y, var5.z);
      Vector4d var7 = new Vector4d(var1, 0.0).mul(var4);
      Vector3d var8 = new Vector3d(var7.x, var7.y, var7.z).normalize();
      float var9 = method22(var6, var8, var2);
      if (var9 == -1.0F) {
         return null;
      }

      Vector3d var10 = method20(var6, var8, var9);
      Vector4d var11 = new Vector4d(var10, 1.0).mul(var3);
      return new Vector3d(var11.x, var11.y, var11.z);
   }

   public static <H extends Horsestats_3<H, ? extends H>> H method26(Vector3d var0, Vector3d var1, double var2, Function<Vector3i, H> var4, Supplier<H> var5) {
      if (var0.equals(var1)) {
         return (H)var5.get();
      }

      double var6 = Horsestats30.lerp(-1.0E-7, var1.x(), var0.x());
      double var8 = Horsestats30.lerp(-1.0E-7, var1.y(), var0.y());
      double var10 = Horsestats30.lerp(-1.0E-7, var1.z(), var0.z());
      double var12 = Horsestats30.lerp(-1.0E-7, var0.x(), var1.x());
      double var14 = Horsestats30.lerp(-1.0E-7, var0.y(), var1.y());
      double var16 = Horsestats30.lerp(-1.0E-7, var0.z(), var1.z());
      int var18 = Horsestats30.method4(var12 / var2);
      int var19 = Horsestats30.method4(var14 / var2);
      int var20 = Horsestats30.method4(var16 / var2);
      Vector3i var21 = new Vector3i(var18, var19, var20);
      Horsestats_3 var22 = (Horsestats_3)var4.apply(var21);
      if (var22 != null) {
         return (H)var22;
      }

      double var23 = var6 - var12;
      double var25 = var8 - var14;
      double var27 = var10 - var16;
      int var29 = Horsestats30.sign(var23);
      int var30 = Horsestats30.sign(var25);
      int var31 = Horsestats30.sign(var27);
      double var32 = var29 == 0 ? Double.MAX_VALUE : var29 * var2 / var23;
      double var34 = var30 == 0 ? Double.MAX_VALUE : var30 * var2 / var25;
      double var36 = var31 == 0 ? Double.MAX_VALUE : var31 * var2 / var27;
      double var38 = var32 * (var29 > 0 ? 1.0 - Horsestats30.method10(var12 / var2) : Horsestats30.method10(var12 / var2));
      double var40 = var34 * (var30 > 0 ? 1.0 - Horsestats30.method10(var14 / var2) : Horsestats30.method10(var14 / var2));
      double var42 = var36 * (var31 > 0 ? 1.0 - Horsestats30.method10(var16 / var2) : Horsestats30.method10(var16 / var2));

      while (var38 <= 1.0 || var40 <= 1.0 || var42 <= 1.0) {
         if (var38 < var40) {
            if (var38 < var42) {
               var18 += var29;
               var38 += var32;
            } else {
               var20 += var31;
               var42 += var36;
            }
         } else if (var40 < var42) {
            var19 += var30;
            var40 += var34;
         } else {
            var20 += var31;
            var42 += var36;
         }

         var22 = (Horsestats_3)var4.apply(var21.set(var18, var19, var20));
         if (var22 != null) {
            return (H)var22;
         }
      }

      return (H)var5.get();
   }

   public static <H extends Horsestats_3<H, ? extends H>> H method27(Vector2d var0, Vector2d var1, double var2, Function<Vector2i, H> var4, Supplier<H> var5) {
      if (var0.equals(var1)) {
         return (H)var5.get();
      }

      double var6 = Horsestats30.lerp(-1.0E-7, var1.x(), var0.x());
      double var8 = Horsestats30.lerp(-1.0E-7, var1.y(), var0.y());
      double var10 = Horsestats30.lerp(-1.0E-7, var0.x(), var1.x());
      double var12 = Horsestats30.lerp(-1.0E-7, var0.y(), var1.y());
      int var14 = Horsestats30.method4(var10 / var2);
      int var15 = Horsestats30.method4(var12 / var2);
      Vector2i var16 = new Vector2i(var14, var15);
      Horsestats_3 var17 = (Horsestats_3)var4.apply(var16);
      if (var17 != null) {
         return (H)var17;
      }

      double var18 = var6 - var10;
      double var20 = var8 - var12;
      int var22 = Horsestats30.sign(var18);
      int var23 = Horsestats30.sign(var20);
      double var24 = var22 == 0 ? Double.MAX_VALUE : var22 * var2 / var18;
      double var26 = var23 == 0 ? Double.MAX_VALUE : var23 * var2 / var20;
      double var28 = var24 * (var22 > 0 ? 1.0 - Horsestats30.method10(var10 / var2) : Horsestats30.method10(var10 / var2));
      double var30 = var26 * (var23 > 0 ? 1.0 - Horsestats30.method10(var12 / var2) : Horsestats30.method10(var12 / var2));

      while (var28 <= 1.0 || var30 <= 1.0) {
         if (var28 < var30) {
            var14 += var22;
            var28 += var24;
         } else {
            var15 += var23;
            var30 += var26;
         }

         var17 = (Horsestats_3)var4.apply(var16.set(var14, var15));
         if (var17 != null) {
            return (H)var17;
         }
      }

      return (H)var5.get();
   }

   @Generated
   private Chest2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
