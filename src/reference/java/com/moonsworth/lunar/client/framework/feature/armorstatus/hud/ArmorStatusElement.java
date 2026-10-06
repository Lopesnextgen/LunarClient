package com.moonsworth.lunar.client.framework.feature.armorstatus.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusSlot;
import com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition;
import com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityDisplayMode;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.FormattingCodes;
import lombok.Generated;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

public class ArmorStatusElement {
   public static final int field1 = 16;
   private static final int field2 = 2;
   private final Armorstatus field3;
   private final ArmorStatusSlot field4;
   @Nullable
   private final ItemStackBridge field5;
   private final boolean field6;
   private String name = "";
   private String field7 = "";
   private ColorOption field8;
   private int field9;
   private int field10;
   private int width;
   private int height;
   private int field11;
   private int field12;
   private int field13;
   private int field14;
   private int field15;
   private int field16;

   public ArmorStatusElement(Armorstatus armorstatus1, ArmorStatusSlot armorstatustype2, @Nullable ItemStackBridge bridgeextension_43, boolean flag4) {
      this.field3 = armorstatus1;
      this.field4 = armorstatustype2;
      this.field5 = bridgeextension_43;
      this.field6 = !armorstatustype2.isHeld();
      if (flag4) {
         this.init();
      }
   }

   private void init() {
      Bridge10_2 bridge10_21 = Ref.method10();
      this.name = this.field5 != null && this.field3.field8.get() ? this.field5.bridge$getDisplayName() : "";
      this.field9 = (int)bridge10_21.bridge$getStringWidth(FormattingCodes.getTextWithoutFormattingCodes(this.name));
      Pair pair2 = this.method1((Boolean)this.field3.field16.get());
      this.field7 = (String)pair2.getKey();
      this.field8 = (ColorOption)pair2.getValue();
      this.field10 = (int)bridge10_21.bridge$getStringWidth(FormattingCodes.getTextWithoutFormattingCodes(this.field7));
      this.method2((DurabilityPosition)this.field3.field18.get());
   }

   protected Pair<String, ColorOption> method1(boolean flag1) {
      boolean flag2 = this.field6 ? (Boolean)this.field3.field15.get() : (Boolean)this.field3.field14.get();
      if (flag2
         && this.field5 != null
         && this.field5.bridge$isItemStackDamageableNoUnbr()
         && (this.field5.bridge$isItemStackDamageable() || !(Boolean)this.field3.field17.get())) {
         int number3 = this.field5.bridge$getMaxDamage();
         int number4 = number3 - this.field5.bridge$getItemDamage();
         int number5 = number4 * 100 / number3;

         String text6 = switch ((DurabilityDisplayMode)this.field3.field20.get()) {
            case VALUE -> number4 + (flag1 ? "/" + number3 : "");
            case PERCENT -> number5 + "%";
            case NONE -> "";
         };
         return Pair.of(text6, this.field3.method6(number5));
      } else {
         return Pair.of("", this.field3.method6(100));
      }
   }

   public void method2(DurabilityPosition gui2extension21) {
      if (this.field5 == null) {
         this.width = 0;
         this.height = 0;
      } else {
         if (gui2extension21 != DurabilityPosition.TOP && gui2extension21 != DurabilityPosition.BOTTOM) {
            this.method3(gui2extension21 == DurabilityPosition.LEFT);
         } else {
            this.method4(gui2extension21 == DurabilityPosition.TOP);
         }
      }
   }

   private void method3(boolean flag1) {
      int number2 = Ref.method10().method19();
      int number3 = Math.max(this.field9, this.field10);
      int number4 = number2 * ((this.name.isEmpty() ? 0 : 1) + (this.field7.isEmpty() ? 0 : 1));
      this.width = 16 + (number3 == 0 ? 0 : 2 + number3);
      this.height = Math.max(16, number4);
      if (flag1) {
         this.field11 = this.width - 16;
         this.field13 = number3 - this.field9;
         this.field15 = number3 - this.field10;
      } else {
         this.field11 = 0;
         this.field13 = this.width - number3;
         this.field15 = this.width - number3;
      }

      this.field12 = 0;
      this.field14 = this.method5(this.height, number4);
      this.field16 = this.field14 + (this.name.isEmpty() ? 0 : number2);
   }

   private void method4(boolean flag1) {
      int number2 = Ref.method10().method19();
      int number3 = 16 + (this.field9 == 0 ? 0 : 2 + this.field9);
      int number4 = this.field7.isEmpty() ? 0 : 2 + number2;
      this.width = Math.max(number3, this.field10);
      this.height = 16 + number4;
      this.field11 = this.method5(this.width, number3);
      this.field12 = flag1 ? number4 : 0;
      this.field13 = this.field11 + 16 + 2;
      this.field14 = this.field12 + this.method5(16, number2);
      this.field15 = 0;
      this.field16 = flag1 ? 3 : 16;
   }

   private int method5(int number1, int number2) {
      return Math.round((number1 - number2) / 2.0F);
   }

   public void method6(MixinHelper_4 mixinhelper_41, RenderItemBridge bridge5_192, float value3, float value4) {
      if (this.field5 != null) {
         this.method7(mixinhelper_41, bridge5_192, value3 + this.field11, value4 + this.field12);
         this.method8(mixinhelper_41, value3 + this.field13, value4 + this.field14);
         this.method9(mixinhelper_41, value3 + this.field15, value4 + this.field16);
      }
   }

   private void method7(MixinHelper_4 mixinhelper_41, RenderItemBridge bridge5_192, float value3, float value4) {
      mixinhelper_41.method44(arg0 -> {
         arg0.method29().method22();
         Bridge.method14().method2();
      });
      mixinhelper_41.method37(this.field5, value3, value4, Ref.method3());
      ArmorStatusDurabilityRenderer.method1(
         mixinhelper_41, Ref.method10(), this.field5, (int)value3, (int)value4, (Boolean)this.field3.field13.get(), (Boolean)this.field3.field9.get()
      );
      mixinhelper_41.method44(arg0 -> {
         Bridge.method14().method3();
         arg0.method29().method23();
         arg0.method29().method15();
      });
   }

   private void method8(MixinHelper_4 mixinhelper_41, float value2, float value3) {
      if (!this.name.isEmpty()) {
         this.field3.field26.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, this.name, value2, value3, (Boolean)this.field3.field12.get());
      }
   }

   private void method9(MixinHelper_4 mixinhelper_41, float value2, float value3) {
      if (this.field8 != null && !this.field7.isEmpty()) {
         this.field8.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, this.field7, value2, value3, (Boolean)this.field3.field12.get());
      }
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   @Generated
   public ArmorStatusSlot method12() {
      return this.field4;
   }

   @Nullable
   @Generated
   public ItemStackBridge method13() {
      return this.field5;
   }
}
