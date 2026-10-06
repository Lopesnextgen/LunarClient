package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import com.moonsworth.lunar.bridge.EntityEnderPearlBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.mixin.MixinHelper;
import com.moonsworth.lunar.client.event.entity.EventEntityWorldJoin;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSoundPlay;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.files.Files6_2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class Holograms_10 {
   public static final Set<String> field1 = Set.of(
      "Decoy",
      "Defuse Kit",
      "Dungeon Chest Key",
      "Health Potion VIII",
      "Inflatable Jerry",
      "Spirit Leap",
      "Training Weights",
      "Trap",
      "Treasure Talisman",
      "Revive Stone",
      "Candycomb",
      "Secret Dye"
   );
   private static final List<Holograms$Data2> field2 = new ArrayList<>();
   private static int field3 = 0;
   private static Holograms4Iterator field4 = null;

   public static void method1(AbstractRenderContext var0, Holograms9.Type var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      List var3 = var2.method29();
      var2.method7().forEach(var4 -> var2.method28().method1(var0, var1, var4, var3));
      var2.method24().ifPresent(var4 -> var4.getSections().forEach(var4x -> var2.method28().method1(var0, var1, var4x, var3)));
   }

   public static void method2() {
      Itemcounter6Extension var0 = ThreadModuleDump63.method8();
      if (var0 == null) {
         method3();
      } else if (ThreadModuleDump63.method7() != null) {
         Holograms3_3 var1 = SkyblockDungeonRoutes.method13().method16();
         Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
         Holograms4Iterator var3 = var1.method16().orElse(null);
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4 = var3 != null ? var3.method23().orElse(null) : null;
         if (var3 != field4) {
            field4 = var3;
            method3();
         }

         var1.method27().method8();
         field3++;
         if (field3 > 100) {
            var1.method1();
            field3 = 0;
         }

         if (field3 % 5 == 0) {
            var1.method7().forEach(var1x -> var1.method28().method4(var1x));
            var1.method24().ifPresent(var1x -> var1x.getSections().forEach(var1xx -> var1.method28().method4(var1xx)));
            if (!var1.method27().method10() && var4 != null) {
               var1.method7()
                  .forEach(
                     var4x -> {
                        if (!var4x.method9().isEmpty()) {
                           Files6_2 var5x = var4x.method9().get(var4x.method9().size() - 1);
                           NameplateType var6x = (NameplateType)var5x.field1;
                           boolean var7x = var6x == NameplateType.SKULL;
                           if (var6x == NameplateType.ITEM_DROP || var7x) {
                              int[] var8 = var4.method8((int[])var5x.field2);
                              double var9 = Math.pow(var2.bridge$getPosX() - var8[0], 2.0)
                                 + Math.pow(var2.bridge$getPosY() - var8[1], 2.0)
                                 + Math.pow(var2.bridge$getPosZ() - var8[2], 2.0);
                              if (var9 < 25.0) {
                                 if (var7x) {
                                    Bridge2_17 var11 = var0.bridge$getBlockState(var8[0], var8[1], var8[2]);
                                    if (!var11.bridge$getBlock().bridge$isAir()) {
                                       return;
                                    }
                                 }

                                 var1.method12(var8, var7x);
                              }
                           }
                        }
                     }
                  );
            }
         }

         Iterator var5 = field2.iterator();

         while (var5.hasNext()) {
            Holograms$Data2 var6 = (Holograms$Data2)var5.next();
            if (var6.method1().bridge$isRemoved()) {
               var5.remove();
               int[] var7 = new int[]{(int)Math.floor(var2.bridge$getPosX()), (int)var2.bridge$getPosY(), (int)Math.floor(var2.bridge$getPosZ())};
               method15(var7, var6.method2());
            }
         }
      }
   }

   public static void method3() {
      Holograms3_3 var0 = SkyblockDungeonRoutes.method13().method16();
      if (var0.method27().method10()) {
         var0.method27().method1();
         if (ThreadModuleDump63.method7() != null) {
            TextComponent var1 = AdventureTextBridge.asAdventure(MixinHelper.method1("cancelledNotInRoom", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
            ThreadModuleDump63.method7().method1(var1);
         }
      }

      var0.method15();
      var0.method30(null);
   }

   public static void method4(Horsestats20Extension2 var0, String var1) {
      if (var1.equals("NONE") && ThreadModuleDump63.method8().method4(var0) != Bridge.method34().method109()) {
         method5(var0);
      }
   }

   public static void method5(Vector3iBridge var0) {
      Holograms3_3 var1 = SkyblockDungeonRoutes.method13().method16();
      int[] var2 = new int[]{var0.bridge$getX(), var0.bridge$getY(), var0.bridge$getZ()};
      var1.method27().method3(var2, NameplateType.BREAK_BLOCK);
      var1.method27().method5(NameplateType2.NONE);
      var1.method11(var2);
   }

   public static void method6(Vector3iBridge var0, String var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         Holograms3_3 var3 = SkyblockDungeonRoutes.method13().method16();
         if (Gui3.method21(var1)) {
            var3.method27().method6();
         }

         if (var0 != null) {
            Bridge2_17 var4 = ThreadModuleDump63.method8().method2(var0);
            Bridge3_23 var5 = var4.bridge$getBlock();
            int[] var6 = new int[]{var0.bridge$getX(), var0.bridge$getY(), var0.bridge$getZ()};
            if (var5.bridge$isAnyChest()) {
               var3.method27().method3(var6, NameplateType.CHEST);
               var3.method12(var6, true);
            }

            if (var5.bridge$isSkull()) {
               var3.method27().method3(var6, NameplateType.SKULL);
               var3.method12(var6, true);
            }

            if (var5 == Bridge.method34().method47()) {
               var3.method27().method3(var6, NameplateType.LEVER);
               var3.method11(var6);
            }

            if (var5.bridge$isMushroom()) {
               var3.method27().method3(var6, NameplateType.MUSHROOM);
               var3.method11(var6);
            }
         }
      }
   }

   public static void method7(BridgeExtension var0) {
      if (var0 instanceof ItemEntityBridge var1) {
         Component var2 = var1.bridge$getCustomName();
         if (var2 != null) {
            String var3 = AdventureTextBridge.getTextContent(var1.bridge$getCustomName());

            for (String var5 : field1) {
               if (var3.contains(var5)) {
                  method8(var0);
               }
            }
         }
      }
   }

   public static void method8(BridgeExtension var0) {
      int[] var1 = new int[]{(int)var0.bridge$getPosX(), (int)var0.bridge$getPosY(), (int)var0.bridge$getPosZ()};
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      var2.method27().method3(var1, NameplateType.ITEM_DROP);
      Holograms4Iterator var3 = var2.method16().orElse(null);
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4 = var3 != null ? var3.method23().orElse(null) : null;
      if (!var2.method27().method10() && var4 != null) {
         int[][] var5 = new int[][]{null};
         double[] var6 = new double[]{100.0};
         var2.method7().forEach(var4x -> {
            if (!var4x.method9().isEmpty()) {
               Files6_2 var5x = var4x.method9().get(var4x.method9().size() - 1);
               if (var5x.field1 == NameplateType.ITEM_DROP) {
                  int[] var6x = var4.method8((int[])var5x.field2);
                  double var7 = Math.pow(var1[0] - var6x[0], 2.0) + Math.pow(var1[1] - var6x[1], 2.0) + Math.pow(var1[2] - var6x[2], 2.0);
                  if (var7 < var6[0]) {
                     var5[0] = var6x;
                     var6[0] = var7;
                  }
               }
            }
         });
         if (var5[0] == null) {
            var2.method11(var1);
         } else {
            var2.method11(var5[0]);
         }
      }
   }

   public static void method9(EventSoundPlay var0) {
      String var1 = var0.method3().bridge$getPath();
      if (var0.getVolume() == 1.0F
         && ThreadModuleDump67.method18(var0.getPitch(), 0.53968, 1.0E-4)
         && (var1.equals("mob.enderdragon.hit") || var1.equals("entity.ender_dragon.hurt") || var1.equals("entity.enderdragon.hurt"))) {
         int[] var2 = new int[]{Math.round(var0.method5()) - 1, (int)var0.method6() - 1, Math.round(var0.method7()) - 1};
         method12(var2);
      }

      if (var0.getVolume() == 1.0F
         && var0.getPitch() != 1.0F
         && var0.getPitch() > 0.76
         && (var1.equals("random.explode") || var1.equals("entity.generic.explode"))) {
         int[] var3 = new int[]{Math.round(var0.method5()) - 1, (int)var0.method6(), Math.round(var0.method7()) - 1};
         method13(var3);
      }

      if (var1.equals("mob.bat.death") || var1.equals("entity.bat.death")) {
         int[] var4 = new int[]{Math.round(var0.method5()) - 1, (int)var0.method6(), Math.round(var0.method7()) - 1};
         method14(var4);
      }
   }

   public static void method10() {
      Holograms3_3 var0 = SkyblockDungeonRoutes.method13().method16();
      var0.method14();
   }

   public static void method11(EventEntityWorldJoin var0) {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      BridgeExtension var2 = var0.field1;
      if (var2 instanceof EntityEnderPearlBridge && var1 != null) {
         if (!(var2.method13(var1) > 25.0)) {
            Holograms3_3 var3 = SkyblockDungeonRoutes.method13().method16();
            var3.method27().method11().ifPresent(var1x -> field2.add(new Holograms$Data2(var2, var1x)));
         }
      }
   }

   private static void method12(int[] var0) {
      Holograms3_3 var1 = SkyblockDungeonRoutes.method13().method16();
      var1.method27().method3(var0, NameplateType.ETHERWARP);
      var1.method11(var0);
   }

   private static void method13(int[] var0) {
      Holograms3_3 var1 = SkyblockDungeonRoutes.method13().method16();
      var1.method27().method3(var0, NameplateType.TNT);
      var1.method11(var0);
   }

   private static void method14(int[] var0) {
      Holograms3_3 var1 = SkyblockDungeonRoutes.method13().method16();
      var1.method27().method3(var0, NameplateType.BAT);
      var1.method11(var0);
   }

   private static void method15(int[] var0, Holograms7 var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      var2.method27().method4(var0, NameplateType.PEARL, var1);
      var2.method11(var0);
      var2.method27().method7();
   }
}
