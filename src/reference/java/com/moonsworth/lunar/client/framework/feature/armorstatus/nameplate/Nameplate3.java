package com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.lwjgl.opengl.GL11;

class Nameplate3 {
   private static final int field1 = 13;

   public static void method1(MixinHelper_4 var0, Bridge10_2 var1, ItemStackBridge var2, int var3, int var4, boolean var5, boolean var6) {
      if (var2 != null && (var5 || var6)) {
         var0.push();
         var0.method38(0.0F, 0.0F, 500.0F);
         if (var5 && var2.bridge$isItemDamaged()) {
            method2(var0, var2, var3, var4);
         }

         if (var6) {
            method3(var0, var1, var2, var3, var4);
         }

         var0.pop();
      }
   }

   private static void method2(MixinHelper_4 var0, ItemStackBridge var1, int var2, int var3) {
      boolean var4 = Bridge.getMinecraftVersion() == Config.field1;
      if (var4) {
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         GL11.glDisable(3553);
         GL11.glDisable(3008);
         GL11.glDisable(3042);
      }

      double var5 = (double)var1.bridge$getItemDamage() / var1.bridge$getMaxDamage();
      int var7 = (int)Math.round(13.0 - var5 * 13.0);
      int var8 = (int)Math.round(255.0 - var5 * 255.0);
      var0.method3(var2 + 2, var3 + 14, 13, 2, -16777216);
      int var9 = 0xFF000000 | (255 - var8) / 4 << 16 | 16128;
      var0.method3(var2 + 2, var3 + 14, 12, 1, var9);
      int var10 = 0xFF000000 | 255 - var8 << 16 | var8 << 8;
      var0.method3(var2 + 2, var3 + 14, var7, 1, var10);
      if (var4) {
         GL11.glEnable(2896);
         GL11.glEnable(2929);
         GL11.glEnable(3553);
         GL11.glEnable(3008);
         GL11.glEnable(3042);
      }
   }

   private static void method3(MixinHelper_4 var0, Bridge10_2 var1, ItemStackBridge var2, int var3, int var4) {
      Bridge5Extension_5 var5 = ThreadModuleDump63.method7();
      if (var5 != null) {
         Bridge6_4 var7 = var2.bridge$getItem();
         int var6;
         if (var2.bridge$getMaxStackSize() > 1) {
            var6 = method4(var5, var1x -> Objects.equals(var1x, var7), var2.bridge$getItemDamage());
         } else {
            if (!var7.equals(Bridge.method28().method8())) {
               return;
            }

            var6 = method4(var5, Bridge6_4::bridge$isItemArrow, -1);
         }

         if (var6 > 1) {
            String var8 = String.valueOf(var6);
            var0.method19(var1, var8, var3 + 17 - var1.bridge$getStringWidth(var8), var4 + 9, -1, true);
         }
      }
   }

   private static int method4(Bridge6_10 var0, Predicate<Bridge6_4> var1, int var2) {
      int var3 = 0;
      List var4 = var0.bridge$getInventory().bridge$getMainInventory();

      for (int var5 = 0; var5 < var4.size(); var5++) {
         ItemStackBridge var6 = (ItemStackBridge)var4.get(var5);
         if (method5(var6, var1, var2)) {
            var3 += var6.bridge$getStackSize();
         }
      }

      ItemStackBridge var7 = (ItemStackBridge)var0.bridge$getInventory().bridge$getOffhandInventory().get(0);
      if (method5(var7, var1, var2)) {
         var3 += var7.bridge$getStackSize();
      }

      return var3;
   }

   private static boolean method5(ItemStackBridge var0, Predicate<Bridge6_4> var1, int var2) {
      return var0 != null && var0.bridge$getItem() != null && var1.test(var0.bridge$getItem()) && (var2 == -1 || var0.bridge$getItemDamage() == var2);
   }
}
