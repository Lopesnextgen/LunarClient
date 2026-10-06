package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemStackSizeRenderEvent;
import com.moonsworth.lunar.client.mod.skyblock.raritybackground.SkyblockRarityBackground;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump70;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Locale;

@Annotation2(min = 1)
public class MixinHelper25 extends MixinHelper2_3 {
   public MixinHelper25(MixinHelper var1) {
      super(var1);
   }

   public void method1(ItemStackBridge var1, int var2, int var3) {
      if (!var1.bridge$isEmpty() && this.method1().field7) {
         SkyblockRarityBackground var4 = ThreadModuleDump63.method4().method40().method82().method96();
         if (var4.isEnabled()) {
            Integer var5 = var4.method5(var1);
            if (var5 != null) {
               this.field1.method43(var2, var3, 16, 16, var5);
            }
         }
      }
   }

   public int method2(ItemStackBridge var1, int var2, int var3, boolean var4, boolean var5) {
      if (this.method3().method5(var2, var3, 16, 16)) {
         return -1;
      }

      this.method6().method17();
      int var6 = 16;
      int var7 = var2;
      int var8 = var3;
      if (var4) {
         var6++;
         var7--;
         var8--;
         if (!var5) {
            this.method1(var1, var2, var3);
         }
      }

      if (var1.bridge$isEmpty()) {
         return this.field1.method41(var7, var8, var6, var6);
      }

      if (!var1.bridge$isEmpty()) {
         if (this.method1().field7) {
            ThreadModuleDump70 var9 = ThreadModuleDump70.of(var2, var3, 16, 16);
            this.method6().method17();
            this.method14().method2(var9, () -> {
               this.method16().method38(0.0F, 0.0F, ThreadModuleDump63.MC_VERSION > 5 ? -100.0F : 35.0F);
               if (ThreadModuleDump63.MC_VERSION <= 5) {
                  Bridge.method14().method2();
               }

               this.method16().method34(var1, var2, var3, ThreadModuleDump63.method3());
               this.method16().method38(0.0F, 0.0F, ThreadModuleDump63.MC_VERSION > 5 ? 100.0F : -35.0F);
            });
            this.method14().method1(var9, 70.0F);
         }

         if (this.method1().field1 && this.field1.method42(var7, var8, var6, var6)) {
            this.field1.method56(var1);
         }
      }

      if (this.method1().field7) {
         String var19 = var1.bridge$getStackSize() == 1 ? null : String.valueOf(var1.bridge$getStackSize());
         ItemStackSizeRenderEvent var10 = (ItemStackSizeRenderEvent)ClientEventBus.method29().method12(ItemStackSizeRenderEvent.class, () -> new ItemStackSizeRenderEvent(var19, var1));
         Object var11 = var10 != null && var10.method1() ? var10.getText() : var19;
         if (var11 != null) {
            int var12 = var2 + 17 - this.field1.getStringWidth((String)var11);
            int var13 = var3 + 9;
            this.field1.method26(AdventureChatFormatting.WHITE + var11, var12, var13, true);
         }

         if (!this.method9().method19()
            && var4
            && this.field1.method42(var2, var3, 16, 16)
            && this.method1().field3) {
            this.field1.method43(var2, var3, 16, 16, -2130706433);
         }

         if (this.method1().field5 != null
            && !method3(var1, this.method1().field5, this.method1().field6)) {
            this.field1.method43(var2, var3, 16, 16, this.getTheme().searchNoMatchOverlay);
         }

         if (!var1.bridge$isEmpty()) {
            double var20 = 1.0 - (var1.bridge$isItemDamaged() ? (double)var1.bridge$getItemDamage() / var1.bridge$getMaxDamage() : 0.0);
            if (ThreadModuleDump63.MC_VERSION >= 26) {
               double var14 = var20;
               com.moonsworth.lunar.client.event.render.ItemDurabilityRenderEvent var16 = (com.moonsworth.lunar.client.event.render.ItemDurabilityRenderEvent)ClientEventBus.method29()
                  .method12(
                     com.moonsworth.lunar.client.event.render.ItemDurabilityRenderEvent.class, () -> new com.moonsworth.lunar.client.event.render.ItemDurabilityRenderEvent(var1, var14)
                  );
               if (var16 != null) {
                  var20 = var16.method1();
               }
            }

            if (var20 < 1.0) {
               int var21 = var2 + 2;
               int var15 = var3 + 13;
               double var22 = Math.max(0.0, var20) / 3.0;
               int var18 = ThreadModuleDump23.method49((float)var22, 1.0F, 1.0F);
               this.field1.method43(var21, var15, 13, 2, -16777216);
               this.field1.method43(var21, var15, (int)(var20 * 13.0), 1, var18 | 0xFF000000);
            }
         }
      }

      return this.field1.method41(var7, var8, var6, var6);
   }

   public static boolean method3(ItemStackBridge var0, String var1, boolean var2) {
      if (var0.bridge$isEmpty()) {
         return false;
      }

      if (AdventureChatFormatting.getTextWithoutFormattingCodes(var0.bridge$getDisplayName()).toLowerCase(Locale.ROOT).contains(var1)) {
         return true;
      }

      if (var2) {
         for (String var5 : var0.bridge$getTooltip(
            ThreadModuleDump63.method7(), ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isAdvancedItemTooltips()
         )) {
            if (AdventureChatFormatting.getTextWithoutFormattingCodes(var5).toLowerCase(Locale.ROOT).contains(var1)) {
               return true;
            }
         }
      }

      return false;
   }

   public void drawCursorStack() {
      if (this.field1.method44() instanceof Bridge5Extension_3 var1) {
         ItemStackBridge var7 = var1.bridge$getCursor();
         this.field1.method3();
         this.field1.method16(false);
         this.field1.method11(false);
         String var3 = null;
         int var4 = this.method9().method18();
         if (var4 != -1) {
            var7 = var7.bridge$copy();
            var7.bridge$setStackSize(var4);
            if (var4 == 0) {
               var7.bridge$setStackSize(1);
               var3 = AdventureChatFormatting.YELLOW + "0";
            }
         }

         this.field1
            .method21(var7, this.method12().method18() - 8, this.method12().method19() - 8);
         if (var3 != null) {
            int var5 = this.method12().method18() + 9 - this.field1.getStringWidth(var3);
            int var6 = this.method12().method19() + 1;
            this.field1.method26(var3, var5, var6, true);
         }

         this.field1.method4();
      }
   }
}
