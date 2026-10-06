package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ModernGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.config.GeneralSettings.Type;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemCounterEntry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightTextSide;
import com.moonsworth.lunar.client.config.option.ItemSelectOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

@VersionGate(min = 33)
public class HighlightButton {
   public static final float field1 = 0.5F;
   public static final float field2 = 5.0F;
   private static final ItemStackBridge field3 = Bridge.method8().method38(Bridge.method28().method42());
   private static final int field4 = 2;
   private float x;
   private float y;
   private HudAnchor field5;
   private float scale;
   private float size;
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightButtonShape field6;
   private int field7;
   private int field8;
   private String command;
   private String field9;
   private HighlightTextSide field10;
   private String itemId;
   private boolean field11;
   private transient ItemStackBridge field12;
   private transient boolean field13;
   private transient float field14;
   private transient float field15;
   private transient boolean field16;

   public HighlightButton(
      float value1,
      float value2,
      HudAnchor gui2extension23,
      float value4,
      float value5,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightButtonShape gui2extension26,
      int number7,
      int number8,
      String text9,
      String text10,
      HighlightTextSide gui2extension11,
      String text12,
      boolean flag13
   ) {
      this.x = value1;
      this.y = value2;
      this.field5 = gui2extension23;
      this.size = value4;
      this.scale = value5;
      this.field6 = gui2extension26;
      this.field7 = number7;
      this.field8 = number8;
      this.command = text9;
      this.field9 = text10;
      this.field10 = gui2extension11;
      this.itemId = text12;
      this.field11 = flag13;
      this.method10();
   }

   public static HighlightButton method1(HighlightButton highlight50) {
      return new HighlightButton(
         highlight50.getX() + 2.0F,
         highlight50.getY() + 2.0F,
         highlight50.field5,
         highlight50.getSize(),
         highlight50.getScale(),
         highlight50.method17(),
         highlight50.method18(),
         highlight50.method19(),
         highlight50.getCommand(),
         highlight50.method20(),
         highlight50.method21(),
         highlight50.getItemId(),
         highlight50.method23()
      );
   }

   public float method2() {
      this.method7();
      return this.method4();
   }

   public float method3() {
      this.method7();
      return this.method5();
   }

   private float method4() {
      double value1 = MarkerModel.method1().OCRCCHICRRIROCIHCOROROHCIRCICO().HHHCHORHIHRCOHIOICICICHCRRICCI() * method6();
      return (float)(HudAnchor.anchorOriginX(this.field5, value1 / this.scale, this.size) * this.scale) + this.x;
   }

   private float method5() {
      double value1 = MarkerModel.method1().OCRCCHICRRIROCIHCOROROHCIRCICO().IHRCCHHROHIRCOOOHRRIHOORRHIOHO() * method6();
      return (float)(HudAnchor.anchorOriginY(this.field5, value1 / this.scale, this.size) * this.scale) + this.y;
   }

   static double method6() {
      GuiScreenBridge bridge5extension60 = Ref.method3().bridge$getCurrentScreen();
      float value1 = bridge5extension60 == null ? 1.0F : bridge5extension60.bridge$getInventoryScaleFactor();
      if (!(value1 <= 0.0F) && value1 != 1.0F) {
         GeneralSettings fogloader222 = Ref.method4().method41().method6();
         return fogloader222.method16() == Type.ALL ? value1 : 1.0;
      } else {
         return 1.0;
      }
   }

   private void method7() {
      if (HighlightSerializer.method1()) {
         this.field14 = this.method4();
         this.field15 = this.method5();
         this.field16 = true;
      }
   }

   boolean method8() {
      return this.field16;
   }

   void method9(float value1, float value2) {
      this.field14 = value1;
      this.field15 = value2;
      this.field16 = true;
   }

   public void method10() {
      if (this.itemId.startsWith("skyblock:")) {
         String text1 = this.itemId.substring(9);
         this.field12 = SkyblockItemRegistry.method1(text1);
      } else {
         ItemCounterEntry itemcounter_22 = (ItemCounterEntry)ItemSelectOption.method11().get(this.itemId);
         this.field12 = itemcounter_22 == null ? null : itemcounter_22.method4();
      }
   }

   public void method11(MixinHelper_4 mixinhelper_41, boolean flag2, float value3) {
      if (this.field11 || !flag2) {
         float value4 = this.method2();
         float value5 = this.method3();
         float value6 = this.getScale();
         float value7 = this.size / 128.0F * value6;
         float value8 = this.getSize();
         com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightButtonShape gui2extension29 = this.method17();
         mixinhelper_41.push();
         mixinhelper_41.method40(value3, value3);
         mixinhelper_41.method39(value4, value5);
         mixinhelper_41.method40(value7, value7);
         mixinhelper_41.method24(gui2extension29.getButtonTexture(), 0, 0, 128, 128, this.method18());
         mixinhelper_41.method24(gui2extension29.getBorderTexture(), 0, 0, 128, 128, this.method19());
         mixinhelper_41.pop();
         ItemStackBridge bridgeextension_410 = this.method24();
         if (!this.field13 && bridgeextension_410 == null && SkyblockItemRegistry.field2 != null) {
            this.method10();
            this.field13 = true;
         }

         mixinhelper_41.push();
         mixinhelper_41.scale(value3, value3, 1.0F);
         mixinhelper_41.method38(value4, value5, 0.0F);
         mixinhelper_41.scale(value6, value6, 1.0F);
         float value11 = (value8 - 16.0F) / 2.0F;
         mixinhelper_41.method38(value11, value11, 0.0F);
         mixinhelper_41.method34(bridgeextension_410 != null ? bridgeextension_410 : field3, 0, 0, Ref.method3());
         mixinhelper_41.pop();
      }
   }

   public void method12(AbstractRenderContext bridgeextension_91, boolean flag2, float value3) {
      ModernGuiGraphicsBridge mixinhelper6_54 = new ModernGuiGraphicsBridge((Bridge8_2)bridgeextension_91.method30().method45().orElseThrow());
      this.method13(mixinhelper6_54, flag2, value3);
   }

   public void method13(MixinHelper_4 mixinhelper_41, boolean flag2, float value3) {
      if (this.field11 || !flag2) {
         mixinhelper_41.push();
         mixinhelper_41.method40(value3, value3);
         float value4 = this.method2();
         float value5 = this.method3();
         float value6 = this.method15();
         TextComponent text7 = Component.text(this.method20());
         int number8 = Ref.method10().method19();
         boolean flag9 = false;
         switch (this.field10) {
            case ABOVE:
               flag9 = true;
               mixinhelper_41.method39(value4 + value6 / 2.0F, value5 - number8 - 2.0F);
               break;
            case LEFT:
               mixinhelper_41.method39(value4 - Ref.method10().bridge$getStringWidth(text7) - 2.0F, value5 + (value6 - number8) / 2.0F);
               break;
            case RIGHT:
               mixinhelper_41.method39(value4 + value6 + 2.0F, value5 + (value6 - number8) / 2.0F);
               break;
            case BELOW:
               flag9 = true;
               mixinhelper_41.method39(value4 + value6 / 2.0F, value5 + value6 + 2.0F);
         }

         if (flag9) {
            mixinhelper_41.method27(Ref.method10(), text7, 0, 0, -1, true);
         } else {
            mixinhelper_41.method10(Ref.method10(), text7, 0, 0, -1, true);
         }

         mixinhelper_41.pop();
      }
   }

   public boolean method14(Data2 data21) {
      float value2 = this.method2();
      float value3 = this.method3();
      float value4 = this.method15();
      if (this.field6 == com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightButtonShape.CIRCLE) {
         float value9 = value4 / 2.0F;
         float value10 = value9 * value9;
         float value7 = value2 + value9;
         float value8 = value3 + value9;
         return Math.pow(data21.HHHCHORHIHRCOHIOICICICHCRRICCI() - value7, 2.0) + Math.pow(data21.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - value8, 2.0) <= value10;
      } else {
         boolean flag5 = data21.HHHCHORHIHRCOHIOICICICHCRRICCI() >= value2 && data21.HHHCHORHIHRCOHIOICICICHCRRICCI() <= value2 + value4;
         boolean flag6 = data21.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= value3 && data21.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() <= value3 + value4;
         return flag5 && flag6;
      }
   }

   public float method15() {
      return this.getSize() * this.getScale();
   }

   @Generated
   public float getX() {
      return this.x;
   }

   @Generated
   public float getY() {
      return this.y;
   }

   @Generated
   public HudAnchor method16() {
      return this.field5;
   }

   @Generated
   public float getScale() {
      return this.scale;
   }

   @Generated
   public float getSize() {
      return this.size;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightButtonShape method17() {
      return this.field6;
   }

   @Generated
   public int method18() {
      return this.field7;
   }

   @Generated
   public int method19() {
      return this.field8;
   }

   @Generated
   public String getCommand() {
      return this.command;
   }

   @Generated
   public String method20() {
      return this.field9;
   }

   @Generated
   public HighlightTextSide method21() {
      return this.field10;
   }

   @Generated
   public String getItemId() {
      return this.itemId;
   }

   @Generated
   public boolean method23() {
      return this.field11;
   }

   @Generated
   public ItemStackBridge method24() {
      return this.field12;
   }

   @Generated
   public boolean method25() {
      return this.field13;
   }

   @Generated
   public void setX(float value1) {
      this.x = value1;
   }

   @Generated
   public void setY(float value1) {
      this.y = value1;
   }

   @Generated
   public void method26(HudAnchor gui2extension21) {
      this.field5 = gui2extension21;
   }

   @Generated
   public void setScale(float value1) {
      this.scale = value1;
   }

   @Generated
   public void setSize(float value1) {
      this.size = value1;
   }

   @Generated
   public void method28(com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightButtonShape gui2extension21) {
      this.field6 = gui2extension21;
   }

   @Generated
   public void method29(int number1) {
      this.field7 = number1;
   }

   @Generated
   public void method30(int number1) {
      this.field8 = number1;
   }

   @Generated
   public void setCommand(String text1) {
      this.command = text1;
   }

   @Generated
   public void method32(String text1) {
      this.field9 = text1;
   }

   @Generated
   public void method33(HighlightTextSide gui2extension1) {
      this.field10 = gui2extension1;
   }

   @Generated
   public void setItemId(String text1) {
      this.itemId = text1;
   }

   @Generated
   public void method35(boolean flag1) {
      this.field11 = flag1;
   }

   @Generated
   public void method36(ItemStackBridge bridgeextension_41) {
      this.field12 = bridgeextension_41;
   }

   @Generated
   public void method37(boolean flag1) {
      this.field13 = flag1;
   }

   @Generated
   float method38() {
      return this.field14;
   }

   @Generated
   float method39() {
      return this.field15;
   }
}
