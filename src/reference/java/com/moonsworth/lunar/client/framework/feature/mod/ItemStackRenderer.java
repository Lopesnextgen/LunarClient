package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize;
import com.moonsworth.lunar.client.mod.skyblock.raritybackground.SkyblockRarityBackground;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.IntRectangle;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Locale;

@VersionGate(min = 1)
public class ItemStackRenderer extends GuiComponent {
   public ItemStackRenderer(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   public void method1(ItemStackBridge bridgeextension_41, int number2, int number3) {
      if (!bridgeextension_41.bridge$isEmpty() && this.method1().field7) {
         SkyblockRarityBackground skyblockraritybackground4 = Ref.method4().method40().method82().method96();
         if (skyblockraritybackground4.isEnabled()) {
            Integer number5 = skyblockraritybackground4.method5(bridgeextension_41);
            if (number5 != null) {
               this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2, number3, 16, 16, number5);
            }
         }
      }
   }

   public int drawItemStack(ItemStackBridge bridgeextension_41, int number2, int number3, boolean flag4, boolean flag5) {
      if (this.method3().method5(number2, number3, 16, 16)) {
         return -1;
      }

      this.method6().method17();
      int index6 = 16;
      int index7 = number2;
      int index8 = number3;
      if (flag4) {
         index6++;
         index7--;
         index8--;
         if (!flag5) {
            this.method1(bridgeextension_41, number2, number3);
         }
      }

      if (bridgeextension_41.bridge$isEmpty()) {
         return this.HRROORRCRHHHCCIORROORCIHOHRIHH.method41(index7, index8, index6, index6);
      }

      if (!bridgeextension_41.bridge$isEmpty()) {
         if (this.method1().field7) {
            IntRectangle threadmoduledump709 = IntRectangle.method3(number2, number3, 16, 16);
            this.method6().method17();
            this.method14().method2(threadmoduledump709, () -> {
               this.method16().method38(0.0F, 0.0F, Ref.MC_VERSION > 5 ? -100.0F : 35.0F);
               if (Ref.MC_VERSION <= 5) {
                  Bridge.method14().method2();
               }

               this.method16().method34(bridgeextension_41, number2, number3, Ref.method3());
               this.method16().method38(0.0F, 0.0F, Ref.MC_VERSION > 5 ? 100.0F : -35.0F);
            });
            this.method14().method1(threadmoduledump709, 70.0F);
         }

         if (this.method1().field1 && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method42(index7, index8, index6, index6)) {
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method56(bridgeextension_41);
         }
      }

      if (this.method1().field7) {
         String text19 = bridgeextension_41.bridge$getStackSize() == 1 ? null : String.valueOf(bridgeextension_41.bridge$getStackSize());
         EventRenderItemStackSize highlightimpl1210 = (EventRenderItemStackSize)LunarEventBus.method29().method12(EventRenderItemStackSize.class, () -> new EventRenderItemStackSize(text19, bridgeextension_41));
         Object obj11 = highlightimpl1210 != null && highlightimpl1210.method1() ? highlightimpl1210.getText() : text19;
         if (obj11 != null) {
            int number12 = number2 + 17 - this.HRROORRCRHHHCCIORROORCIHOHRIHH.getStringWidth((String)obj11);
            int number13 = number3 + 9;
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method26(ChatFormatting.WHITE + obj11, number12, number13, true);
         }

         if (!this.method9().method19()
            && flag4
            && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method42(number2, number3, 16, 16)
            && this.method1().field3) {
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2, number3, 16, 16, -2130706433);
         }

         if (this.method1().field5 != null
            && !method3(bridgeextension_41, this.method1().field5, this.method1().field6)) {
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number2, number3, 16, 16, this.getTheme().searchNoMatchOverlay);
         }

         if (!bridgeextension_41.bridge$isEmpty()) {
            double value20 = 1.0 - (bridgeextension_41.bridge$isItemDamaged() ? (double)bridgeextension_41.bridge$getItemDamage() / bridgeextension_41.bridge$getMaxDamage() : 0.0);
            if (Ref.MC_VERSION >= 26) {
               double value14 = value20;
               com.moonsworth.lunar.client.event.render.EventRenderItemDurability highlightimpl1216 = (com.moonsworth.lunar.client.event.render.EventRenderItemDurability)LunarEventBus.method29()
                  .method12(
                     com.moonsworth.lunar.client.event.render.EventRenderItemDurability.class, () -> new com.moonsworth.lunar.client.event.render.EventRenderItemDurability(bridgeextension_41, value14)
                  );
               if (highlightimpl1216 != null) {
                  value20 = highlightimpl1216.method1();
               }
            }

            if (value20 < 1.0) {
               int number21 = number2 + 2;
               int number15 = number3 + 13;
               double value22 = Math.max(0.0, value20) / 3.0;
               int number18 = ColorUtils.method49((float)value22, 1.0F, 1.0F);
               this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number21, number15, 13, 2, -16777216);
               this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number21, number15, (int)(value20 * 13.0), 1, number18 | 0xFF000000);
            }
         }
      }

      return this.HRROORRCRHHHCCIORROORCIHOHRIHH.method41(index7, index8, index6, index6);
   }

   public static boolean method3(ItemStackBridge bridgeextension_40, String text1, boolean flag2) {
      if (bridgeextension_40.bridge$isEmpty()) {
         return false;
      }

      if (ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_40.bridge$getDisplayName()).toLowerCase(Locale.ROOT).contains(text1)) {
         return true;
      }

      if (flag2) {
         for (String text5 : bridgeextension_40.bridge$getTooltip(
            Ref.method7(), Ref.method3().bridge$getGameSettings().bridge$isAdvancedItemTooltips()
         )) {
            if (ChatFormatting.getTextWithoutFormattingCodes(text5).toLowerCase(Locale.ROOT).contains(text1)) {
               return true;
            }
         }
      }

      return false;
   }

   public void drawCursorStack() {
      if (this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_31) {
         ItemStackBridge bridgeextension_47 = bridge5extension_31.bridge$getCursor();
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method3();
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method16(false);
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method11(false);
         String text3 = null;
         int number4 = this.method9().method18();
         if (number4 != -1) {
            bridgeextension_47 = bridgeextension_47.bridge$copy();
            bridgeextension_47.bridge$setStackSize(number4);
            if (number4 == 0) {
               bridgeextension_47.bridge$setStackSize(1);
               text3 = ChatFormatting.YELLOW + "0";
            }
         }

         this.HRROORRCRHHHCCIORROORCIHOHRIHH
            .method21(bridgeextension_47, this.method12().method18() - 8, this.method12().method19() - 8);
         if (text3 != null) {
            int number5 = this.method12().method18() + 9 - this.HRROORRCRHHHCCIORROORCIHOHRIHH.getStringWidth(text3);
            int number6 = this.method12().method19() + 1;
            this.HRROORRCRHHHCCIORROORCIHOHRIHH.method26(text3, number5, number6, true);
         }

         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method4();
      }
   }
}
