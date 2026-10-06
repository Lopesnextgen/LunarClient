package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.EntityEnderPearlBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.PickaxeTier;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.mod.mixin.MixinHelper;
import com.moonsworth.lunar.client.event.entity.EventEntityJoinWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.files.ValuePair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class RouteTracker {
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
   private static final List<RouteSectionEntity> field2 = new ArrayList<>();
   private static int field3 = 0;
   private static DungeonRoomTracker field4 = null;

   public RouteTracker() {
   }

   public static void method1(AbstractRenderContext bridgeextension_90, RouteRenderer.Type type1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      List list3 = holograms3_32.method29();
      holograms3_32.method7().forEach(arg4 -> holograms3_32.method28().method1(bridgeextension_90, type1, arg4, list3));
      holograms3_32.method24().ifPresent(arg4 -> arg4.getSections().forEach(arg4x -> holograms3_32.method28().method1(bridgeextension_90, type1, arg4x, list3)));
   }

   public static void method2() {
      WorldBridgeExtension itemcounter6extension0 = Ref.method8();
      if (itemcounter6extension0 == null) {
         method3();
      } else if (Ref.method7() != null) {
         RouteManager holograms3_31 = SkyblockDungeonRoutes.method13().method16();
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         DungeonRoomTracker holograms4iterator3 = holograms3_31.method16().orElse(null);
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34 = holograms4iterator3 != null ? holograms4iterator3.method23().orElse(null) : null;
         if (holograms4iterator3 != field4) {
            field4 = holograms4iterator3;
            method3();
         }

         holograms3_31.method27().method8();
         field3++;
         if (field3 > 100) {
            holograms3_31.method1();
            field3 = 0;
         }

         if (field3 % 5 == 0) {
            holograms3_31.method7().forEach(arg1x -> holograms3_31.method28().method4(arg1x));
            holograms3_31.method24().ifPresent(arg1x -> arg1x.getSections().forEach(arg1xx -> holograms3_31.method28().method4(arg1xx)));
            if (!holograms3_31.method27().method10() && holograms34 != null) {
               holograms3_31.method7()
                  .forEach(
                     arg4x -> {
                        if (!arg4x.method9().isEmpty()) {
                           ValuePair files6_25x = arg4x.method9().get(arg4x.method9().size() - 1);
                           NameplateType nameplatetype6x = (NameplateType)files6_25x.field1;
                           boolean flag7x = nameplatetype6x == NameplateType.SKULL;
                           if (nameplatetype6x == NameplateType.ITEM_DROP || flag7x) {
                              int[] items8 = holograms34.method8((int[])files6_25x.field2);
                              double value9 = Math.pow(bridge5extension_52.bridge$getPosX() - items8[0], 2.0)
                                 + Math.pow(bridge5extension_52.bridge$getPosY() - items8[1], 2.0)
                                 + Math.pow(bridge5extension_52.bridge$getPosZ() - items8[2], 2.0);
                              if (value9 < 25.0) {
                                 if (flag7x) {
                                    BlockStateBridge bridge2_1711 = itemcounter6extension0.bridge$getBlockState(items8[0], items8[1], items8[2]);
                                    if (!bridge2_1711.bridge$getBlock().bridge$isAir()) {
                                       return;
                                    }
                                 }

                                 holograms3_31.method12(items8, flag7x);
                              }
                           }
                        }
                     }
                  );
            }
         }

         Iterator iterator5 = field2.iterator();

         while (iterator5.hasNext()) {
            RouteSectionEntity holograms$data26 = (RouteSectionEntity)iterator5.next();
            if (holograms$data26.method1().bridge$isRemoved()) {
               iterator5.remove();
               int[] items7 = new int[]{(int)Math.floor(bridge5extension_52.bridge$getPosX()), (int)bridge5extension_52.bridge$getPosY(), (int)Math.floor(bridge5extension_52.bridge$getPosZ())};
               method15(items7, holograms$data26.method2());
            }
         }
      }
   }

   public static void method3() {
      RouteManager holograms3_30 = SkyblockDungeonRoutes.method13().method16();
      if (holograms3_30.method27().method10()) {
         holograms3_30.method27().method1();
         if (Ref.method7() != null) {
            TextComponent text1 = TextBridge.asAdventure(MixinHelper.method1("cancelledNotInRoom", ChatFormatting.AQUA, ChatFormatting.RED));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text1);
         }
      }

      holograms3_30.method15();
      holograms3_30.method30(null);
   }

   public static void method4(Horsestats20Extension2 horsestats20extension20, String text1) {
      if (text1.equals("DUNGEONBREAKER") && Ref.method8().RHIRRICCRHHHIIHHIHHOHRCHIOORCC(horsestats20extension20) != Bridge.method34().method109()) {
         method5(horsestats20extension20);
      }
   }

   public static void method5(Vec3iBridge horsestats200) {
      RouteManager holograms3_31 = SkyblockDungeonRoutes.method13().method16();
      int[] items2 = new int[]{horsestats200.bridge$getX(), horsestats200.bridge$getY(), horsestats200.bridge$getZ()};
      holograms3_31.method27().method3(items2, NameplateType.BREAK_BLOCK);
      holograms3_31.method27().method5(PickaxeTier.DUNGEONBREAKER);
      holograms3_31.method11(items2);
   }

   public static void method6(Vec3iBridge horsestats200, String text1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         RouteManager holograms3_33 = SkyblockDungeonRoutes.method13().method16();
         if (SkyblockItemUtil.method21(text1)) {
            holograms3_33.method27().method6();
         }

         if (horsestats200 != null) {
            BlockStateBridge bridge2_174 = Ref.method8().HIOROOCORHICHIRIHIRCCORCIIICCH(horsestats200);
            Bridge3_23 bridge3_235 = bridge2_174.bridge$getBlock();
            int[] items6 = new int[]{horsestats200.bridge$getX(), horsestats200.bridge$getY(), horsestats200.bridge$getZ()};
            if (bridge3_235.bridge$isAnyChest()) {
               holograms3_33.method27().method3(items6, NameplateType.CHEST);
               holograms3_33.method12(items6, true);
            }

            if (bridge3_235.bridge$isSkull()) {
               holograms3_33.method27().method3(items6, NameplateType.SKULL);
               holograms3_33.method12(items6, true);
            }

            if (bridge3_235 == Bridge.method34().method47()) {
               holograms3_33.method27().method3(items6, NameplateType.LEVER);
               holograms3_33.method11(items6);
            }

            if (bridge3_235.bridge$isMushroom()) {
               holograms3_33.method27().method3(items6, NameplateType.MUSHROOM);
               holograms3_33.method11(items6);
            }
         }
      }
   }

   public static void method7(BridgeExtension bridgeextension0) {
      if (bridgeextension0 instanceof EntityItemBridge bridgeextension521) {
         Component component2 = bridgeextension521.bridge$getCustomName();
         if (component2 != null) {
            String text3 = TextBridge.getTextContent(bridgeextension521.bridge$getCustomName());

            for (String text5 : field1) {
               if (text3.contains(text5)) {
                  method8(bridgeextension0);
               }
            }
         }
      }
   }

   public static void method8(BridgeExtension bridgeextension0) {
      int[] items1 = new int[]{(int)bridgeextension0.bridge$getPosX(), (int)bridgeextension0.bridge$getPosY(), (int)bridgeextension0.bridge$getPosZ()};
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      holograms3_32.method27().method3(items1, NameplateType.ITEM_DROP);
      DungeonRoomTracker holograms4iterator3 = holograms3_32.method16().orElse(null);
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34 = holograms4iterator3 != null ? holograms4iterator3.method23().orElse(null) : null;
      if (!holograms3_32.method27().method10() && holograms34 != null) {
         int[][] items5 = new int[][]{null};
         double[] items6 = new double[]{100.0};
         holograms3_32.method7().forEach(arg4x -> {
            if (!arg4x.method9().isEmpty()) {
               ValuePair files6_25x = arg4x.method9().get(arg4x.method9().size() - 1);
               if (files6_25x.field1 == NameplateType.ITEM_DROP) {
                  int[] items6x = holograms34.method8((int[])files6_25x.field2);
                  double value7 = Math.pow(items1[0] - items6x[0], 2.0) + Math.pow(items1[1] - items6x[1], 2.0) + Math.pow(items1[2] - items6x[2], 2.0);
                  if (value7 < items6[0]) {
                     items5[0] = items6x;
                     items6[0] = value7;
                  }
               }
            }
         });
         if (items5[0] == null) {
            holograms3_32.method11(items1);
         } else {
            holograms3_32.method11(items5[0]);
         }
      }
   }

   public static void method9(EventPlaySound highlightimpl130) {
      String text1 = highlightimpl130.method3().bridge$getPath();
      if (highlightimpl130.getVolume() == 1.0F
         && MathUtils.method18(highlightimpl130.getPitch(), 0.53968, 1.0E-4)
         && (text1.equals("mob.enderdragon.hit") || text1.equals("entity.ender_dragon.hurt") || text1.equals("entity.enderdragon.hurt"))) {
         int[] items2 = new int[]{Math.round(highlightimpl130.method5()) - 1, (int)highlightimpl130.method6() - 1, Math.round(highlightimpl130.method7()) - 1};
         method12(items2);
      }

      if (highlightimpl130.getVolume() == 1.0F
         && highlightimpl130.getPitch() != 1.0F
         && highlightimpl130.getPitch() > 0.76
         && (text1.equals("random.explode") || text1.equals("entity.generic.explode"))) {
         int[] items3 = new int[]{Math.round(highlightimpl130.method5()) - 1, (int)highlightimpl130.method6(), Math.round(highlightimpl130.method7()) - 1};
         method13(items3);
      }

      if (text1.equals("mob.bat.death") || text1.equals("entity.bat.death")) {
         int[] items4 = new int[]{Math.round(highlightimpl130.method5()) - 1, (int)highlightimpl130.method6(), Math.round(highlightimpl130.method7()) - 1};
         method14(items4);
      }
   }

   public static void method10() {
      RouteManager holograms3_30 = SkyblockDungeonRoutes.method13().method16();
      holograms3_30.method14();
   }

   public static void method11(EventEntityJoinWorld highlightimpl200) {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      BridgeExtension bridgeextension2 = highlightimpl200.field1;
      if (bridgeextension2 instanceof EntityEnderPearlBridge && bridge5extension_51 != null) {
         if (!(bridgeextension2.method13(bridge5extension_51) > 25.0)) {
            RouteManager holograms3_33 = SkyblockDungeonRoutes.method13().method16();
            holograms3_33.method27().method11().ifPresent(arg1x -> field2.add(new RouteSectionEntity(bridgeextension2, arg1x)));
         }
      }
   }

   private static void method12(int[] items0) {
      RouteManager holograms3_31 = SkyblockDungeonRoutes.method13().method16();
      holograms3_31.method27().method3(items0, NameplateType.ETHERWARP);
      holograms3_31.method11(items0);
   }

   private static void method13(int[] items0) {
      RouteManager holograms3_31 = SkyblockDungeonRoutes.method13().method16();
      holograms3_31.method27().method3(items0, NameplateType.TNT);
      holograms3_31.method11(items0);
   }

   private static void method14(int[] items0) {
      RouteManager holograms3_31 = SkyblockDungeonRoutes.method13().method16();
      holograms3_31.method27().method3(items0, NameplateType.BAT);
      holograms3_31.method11(items0);
   }

   private static void method15(int[] items0, RouteSection holograms71) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      holograms3_32.method27().method4(items0, NameplateType.PEARL, holograms71);
      holograms3_32.method11(items0);
      holograms3_32.method27().method7();
   }
}
