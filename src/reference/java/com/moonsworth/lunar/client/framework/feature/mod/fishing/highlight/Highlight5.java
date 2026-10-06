package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ModernGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.config.GeneralSettings.Type;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.itemcounter.Itemcounter_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension;
import com.moonsworth.lunar.client.config.option.ItemSelectOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

@Annotation2(min = 33)
public class Highlight5 {
   public static final float field1 = 0.5F;
   public static final float field2 = 5.0F;
   private static final ItemStackBridge field3 = Bridge.method8().method38(Bridge.method28().method42());
   private static final int field4 = 2;
   private float x;
   private float y;
   private HudAnchor field5;
   private float scale;
   private float size;
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension2 field6;
   private int field7;
   private int field8;
   private String command;
   private String field9;
   private Gui2Extension field10;
   private String itemId;
   private boolean field11;
   private transient ItemStackBridge field12;
   private transient boolean field13;
   private transient float field14;
   private transient float field15;
   private transient boolean field16;

   public Highlight5(
      float var1,
      float var2,
      HudAnchor var3,
      float var4,
      float var5,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension2 var6,
      int var7,
      int var8,
      String var9,
      String var10,
      Gui2Extension var11,
      String var12,
      boolean var13
   ) {
      this.x = var1;
      this.y = var2;
      this.field5 = var3;
      this.size = var4;
      this.scale = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
      this.command = var9;
      this.field9 = var10;
      this.field10 = var11;
      this.itemId = var12;
      this.field11 = var13;
      this.method10();
   }

   public static Highlight5 method1(Highlight5 var0) {
      return new Highlight5(
         var0.getX() + 2.0F,
         var0.getY() + 2.0F,
         var0.field5,
         var0.getSize(),
         var0.getScale(),
         var0.method17(),
         var0.method18(),
         var0.method19(),
         var0.getCommand(),
         var0.method20(),
         var0.method21(),
         var0.getItemId(),
         var0.method23()
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
      double var1 = MarkerModel.method1().method5().HHHCHORHIHRCOHIOICICICHCRRICCI() * method6();
      return (float)(HudAnchor.anchorOriginX(this.field5, var1 / this.scale, this.size) * this.scale) + this.x;
   }

   private float method5() {
      double var1 = MarkerModel.method1().method5().IHRCCHHROHIRCOOOHRRIHOORRHIOHO() * method6();
      return (float)(HudAnchor.anchorOriginY(this.field5, var1 / this.scale, this.size) * this.scale) + this.y;
   }

   static double method6() {
      Bridge5Extension6 var0 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      float var1 = var0 == null ? 1.0F : var0.bridge$getInventoryScaleFactor();
      if (!(var1 <= 0.0F) && var1 != 1.0F) {
         GeneralSettings var2 = ThreadModuleDump63.method4().method41().method6();
         return var2.method16() == Type.ALL ? var1 : 1.0;
      } else {
         return 1.0;
      }
   }

   private void method7() {
      if (Highlight4.method1()) {
         this.field14 = this.method4();
         this.field15 = this.method5();
         this.field16 = true;
      }
   }

   boolean method8() {
      return this.field16;
   }

   void method9(float var1, float var2) {
      this.field14 = var1;
      this.field15 = var2;
      this.field16 = true;
   }

   public void method10() {
      if (this.itemId.startsWith("skyblock:")) {
         String var1 = this.itemId.substring(9);
         this.field12 = Gui2.method1(var1);
      } else {
         Itemcounter_2 var2 = (Itemcounter_2)ItemSelectOption.method11().get(this.itemId);
         this.field12 = var2 == null ? null : var2.method4();
      }
   }

   public void method11(MixinHelper_4 var1, boolean var2, float var3) {
      if (this.field11 || !var2) {
         float var4 = this.method2();
         float var5 = this.method3();
         float var6 = this.getScale();
         float var7 = this.size / 128.0F * var6;
         float var8 = this.getSize();
         com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension2 var9 = this.method17();
         var1.push();
         var1.method40(var3, var3);
         var1.method39(var4, var5);
         var1.method40(var7, var7);
         var1.method24(var9.getButtonTexture(), 0, 0, 128, 128, this.method18());
         var1.method24(var9.getBorderTexture(), 0, 0, 128, 128, this.method19());
         var1.pop();
         ItemStackBridge var10 = this.method24();
         if (!this.field13 && var10 == null && Gui2.field2 != null) {
            this.method10();
            this.field13 = true;
         }

         var1.push();
         var1.scale(var3, var3, 1.0F);
         var1.method38(var4, var5, 0.0F);
         var1.scale(var6, var6, 1.0F);
         float var11 = (var8 - 16.0F) / 2.0F;
         var1.method38(var11, var11, 0.0F);
         var1.method34(var10 != null ? var10 : field3, 0, 0, ThreadModuleDump63.method3());
         var1.pop();
      }
   }

   public void method12(AbstractRenderContext var1, boolean var2, float var3) {
      ModernGuiGraphicsBridge var4 = new ModernGuiGraphicsBridge((Bridge8_2)var1.method30().method45().orElseThrow());
      this.method13(var4, var2, var3);
   }

   public void method13(MixinHelper_4 var1, boolean var2, float var3) {
      if (this.field11 || !var2) {
         var1.push();
         var1.method40(var3, var3);
         float var4 = this.method2();
         float var5 = this.method3();
         float var6 = this.method15();
         TextComponent var7 = Component.text(this.method20());
         int var8 = ThreadModuleDump63.method10().method19();
         boolean var9 = false;
         switch (this.field10) {
            case ABOVE:
               var9 = true;
               var1.method39(var4 + var6 / 2.0F, var5 - var8 - 2.0F);
               break;
            case LEFT:
               var1.method39(var4 - ThreadModuleDump63.method10().bridge$getStringWidth(var7) - 2.0F, var5 + (var6 - var8) / 2.0F);
               break;
            case RIGHT:
               var1.method39(var4 + var6 + 2.0F, var5 + (var6 - var8) / 2.0F);
               break;
            case BELOW:
               var9 = true;
               var1.method39(var4 + var6 / 2.0F, var5 + var6 + 2.0F);
         }

         if (var9) {
            var1.method27(ThreadModuleDump63.method10(), var7, 0, 0, -1, true);
         } else {
            var1.method10(ThreadModuleDump63.method10(), var7, 0, 0, -1, true);
         }

         var1.pop();
      }
   }

   public boolean method14(Data2 var1) {
      float var2 = this.method2();
      float var3 = this.method3();
      float var4 = this.method15();
      if (this.field6 == com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension2.CIRCLE) {
         float var9 = var4 / 2.0F;
         float var10 = var9 * var9;
         float var7 = var2 + var9;
         float var8 = var3 + var9;
         return Math.pow(var1.HHHCHORHIHRCOHIOICICICHCRRICCI() - var7, 2.0) + Math.pow(var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - var8, 2.0) <= var10;
      } else {
         boolean var5 = var1.HHHCHORHIHRCOHIOICICICHCRRICCI() >= var2 && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() <= var2 + var4;
         boolean var6 = var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= var3 && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() <= var3 + var4;
         return var5 && var6;
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
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension2 method17() {
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
   public Gui2Extension method21() {
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
   public void setX(float var1) {
      this.x = var1;
   }

   @Generated
   public void setY(float var1) {
      this.y = var1;
   }

   @Generated
   public void method26(HudAnchor var1) {
      this.field5 = var1;
   }

   @Generated
   public void setScale(float var1) {
      this.scale = var1;
   }

   @Generated
   public void setSize(float var1) {
      this.size = var1;
   }

   @Generated
   public void method28(com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension2 var1) {
      this.field6 = var1;
   }

   @Generated
   public void method29(int var1) {
      this.field7 = var1;
   }

   @Generated
   public void method30(int var1) {
      this.field8 = var1;
   }

   @Generated
   public void setCommand(String var1) {
      this.command = var1;
   }

   @Generated
   public void method32(String var1) {
      this.field9 = var1;
   }

   @Generated
   public void method33(Gui2Extension var1) {
      this.field10 = var1;
   }

   @Generated
   public void setItemId(String var1) {
      this.itemId = var1;
   }

   @Generated
   public void method35(boolean var1) {
      this.field11 = var1;
   }

   @Generated
   public void method36(ItemStackBridge var1) {
      this.field12 = var1;
   }

   @Generated
   public void method37(boolean var1) {
      this.field13 = var1;
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
