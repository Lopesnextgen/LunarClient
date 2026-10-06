package com.moonsworth.lunar.client.framework.feature.armorstatus.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.lwjgl.opengl.GL11;

class ArmorStatusDurabilityRenderer {
   private static final int field1 = 13;

   ArmorStatusDurabilityRenderer() {
   }

   public static void method1(MixinHelper_4 mixinhelper_40, Bridge10_2 bridge10_21, ItemStackBridge bridgeextension_42, int number3, int number4, boolean flag5, boolean flag6) {
      if (bridgeextension_42 != null && (flag5 || flag6)) {
         mixinhelper_40.push();
         mixinhelper_40.method38(0.0F, 0.0F, 500.0F);
         if (flag5 && bridgeextension_42.bridge$isItemDamaged()) {
            method2(mixinhelper_40, bridgeextension_42, number3, number4);
         }

         if (flag6) {
            method3(mixinhelper_40, bridge10_21, bridgeextension_42, number3, number4);
         }

         mixinhelper_40.pop();
      }
   }

   private static void method2(MixinHelper_4 mixinhelper_40, ItemStackBridge bridgeextension_41, int number2, int number3) {
      boolean flag4 = Bridge.getMinecraftVersion() == Config.field1;
      if (flag4) {
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         GL11.glDisable(3553);
         GL11.glDisable(3008);
         GL11.glDisable(3042);
      }

      double value5 = (double)bridgeextension_41.bridge$getItemDamage() / bridgeextension_41.bridge$getMaxDamage();
      int number7 = (int)Math.round(13.0 - value5 * 13.0);
      int number8 = (int)Math.round(255.0 - value5 * 255.0);
      mixinhelper_40.method3(number2 + 2, number3 + 14, 13, 2, -16777216);
      int number9 = 0xFF000000 | (255 - number8) / 4 << 16 | 16128;
      mixinhelper_40.method3(number2 + 2, number3 + 14, 12, 1, number9);
      int number10 = 0xFF000000 | 255 - number8 << 16 | number8 << 8;
      mixinhelper_40.method3(number2 + 2, number3 + 14, number7, 1, number10);
      if (flag4) {
         GL11.glEnable(2896);
         GL11.glEnable(2929);
         GL11.glEnable(3553);
         GL11.glEnable(3008);
         GL11.glEnable(3042);
      }
   }

   private static void method3(MixinHelper_4 mixinhelper_40, Bridge10_2 bridge10_21, ItemStackBridge bridgeextension_42, int number3, int number4) {
      Bridge5Extension_5 bridge5extension_55 = Ref.method7();
      if (bridge5extension_55 != null) {
         ItemBridge bridge6_47 = bridgeextension_42.bridge$getItem();
         int number6;
         if (bridgeextension_42.bridge$getMaxStackSize() > 1) {
            number6 = method4(bridge5extension_55, arg1x -> Objects.equals(arg1x, bridge6_47), bridgeextension_42.bridge$getItemDamage());
         } else {
            if (!bridge6_47.equals(Bridge.method28().method8())) {
               return;
            }

            number6 = method4(bridge5extension_55, ItemBridge::bridge$isItemArrow, -1);
         }

         if (number6 > 1) {
            String text8 = String.valueOf(number6);
            mixinhelper_40.method19(bridge10_21, text8, number3 + 17 - bridge10_21.bridge$getStringWidth(text8), number4 + 9, -1, true);
         }
      }
   }

   private static int method4(Bridge6_10 bridge6_100, Predicate<ItemBridge> predicate1, int number2) {
      int number3 = 0;
      List list4 = bridge6_100.bridge$getInventory().bridge$getMainInventory();

      for (int index5 = 0; index5 < list4.size(); index5++) {
         ItemStackBridge bridgeextension_46 = (ItemStackBridge)list4.get(index5);
         if (method5(bridgeextension_46, predicate1, number2)) {
            number3 += bridgeextension_46.bridge$getStackSize();
         }
      }

      ItemStackBridge bridgeextension_47 = (ItemStackBridge)bridge6_100.bridge$getInventory().bridge$getOffhandInventory().get(0);
      if (method5(bridgeextension_47, predicate1, number2)) {
         number3 += bridgeextension_47.bridge$getStackSize();
      }

      return number3;
   }

   private static boolean method5(ItemStackBridge bridgeextension_40, Predicate<ItemBridge> predicate1, int number2) {
      return bridgeextension_40 != null && bridgeextension_40.bridge$getItem() != null && predicate1.test(bridgeextension_40.bridge$getItem()) && (number2 == -1 || bridgeextension_40.bridge$getItemDamage() == number2);
   }
}
