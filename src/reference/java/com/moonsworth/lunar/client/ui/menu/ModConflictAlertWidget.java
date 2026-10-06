package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import org.jetbrains.annotations.Nullable;

public class ModConflictAlertWidget extends SelectionWidget<Framework7Extension> {
   public static final float field17 = 22.0F;
   public static final float field18 = 115.0F;
   private final CogButtonWidget field19;
   private final AnimatedValue field20 = new AnimatedValue(542594903, 1348953959);
   private final AnimatedValue field21 = new AnimatedValue(1616336727, -1352177817);
   private final AnimatedValue field22 = new AnimatedValue(1893605714, -1327619758);
   private final AnimatedValue field23 = new AnimatedValue(1881790074, -1339435398);
   @Nullable
   private final Boolean field24;
   @Nullable
   private final AlertType field25;

   public ModConflictAlertWidget(GuiWidget var1, ModMenuWidget var2, ModSearchWidget var3, Framework7Extension var4) {
      super(var1, var4);
      this.setWidth(115.0F);
      this.setHeight(22.0F);
      this.field19 = new CogButtonWidget(this, var2, var4);
      Alert2 var5 = (Alert2)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
      if (var5 == null) {
         this.field24 = null;
         this.field25 = null;
      } else {
         this.field24 = (Boolean)var5.method3().orElse(null);
         this.field25 = var5.method2();
      }

      this.HRICOROOOCCOCOROCRHHCRRIRCOICO((var2x, var3x) -> {
         if (var3.method18() != null) {
            return false;
         }

         if (this.field19.method3(var2x)) {
            Framework10 var5x = (Framework10)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
            if (var5x != null) {
               var5x.setLastModified(System.currentTimeMillis());
            }

            return this.field19.method6(var2x, var3x);
         } else if (this.field24 != null) {
            return false;
         } else {
            ModEnabledState var4x = (ModEnabledState)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
            if (var4x != null) {
               var4x.method1().ifPresent(var0 -> var0.method10(!(Boolean)var0.get()));
               return true;
            } else {
               return false;
            }
         }
      });
   }

   @Override
   public void update() {
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      this.field19.method2(var1, var2 + var4 - 40.0F, var3, 20.0F);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      ModDetails var4 = (ModDetails)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      if (var4 != null && this.method3(var2) && var3 && this.field24 != null) {
         String var5;
         if (this.field25 == AlertType.SERVER) {
            var5 = this.field24 ? "modpackAllowedModDescription" : "modpackDisallowedModDescription";
         } else {
            var5 = this.field24 ? "allowedModDescription" : "disallowedModDescription";
         }

         String var6 = this.OHROCHICOIOICHOCRROORRCIIICIHO(var5, new Object[]{var4.getName()}).toUpperCase();
         float var7 = FontRegistry.method9().method4(var6) + 8.0F;
         float var8 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 12.5F;
         float var9 = var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F;
         var1.push();
         var1.method44(var0 -> var0.method29().method18());
         var1.method38(0.0F, 0.0F, 10.0F);
         int[] var10 = com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
         com.moonsworth.lunar.client.ui.LcuiScreen.method117(
            var1, var8, var9, var7, FontRegistry.method8().getHeight() * 2 + 5, 5.0F, Integer.MIN_VALUE
         );
         FontRegistry.method9().method17(var1, var6, var8 + 4.0F, var9 + 2.0F, Integer.MAX_VALUE, false);
         com.moonsworth.lunar.client.ui.LcuiScreen.method110(var1, var10);
         var1.pop();
      }

      this.field19.method2(this.x + this.width - 22.0F, this.y, 22.0F, 22.0F);
      int var14 = this.field20.method2(var3 && this.method3(var2));
      Alert2 var15 = (Alert2)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
      ModEnabledState var16 = (ModEnabledState)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
      int var17 = var15 != null && !var15.method3().isEmpty()
         ? -1346848584
         : (var16 != null && !var16.isEnabled() ? this.field22 : this.field23).method2(var3 && this.method3(var2));
      int var18 = this.field21.method2(var3 && this.method3(var2));
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x, this.y, this.width, this.height, var14);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, this.x, this.y, this.width, this.height, 3.0F, var17);
      String var19 = var4 != null ? var4.getName() : ((Framework7Extension)this.field16).getId();
      ResourceLocationBridge var11 = null;
      Framework10 var12 = (Framework10)((Framework7Extension)this.field16).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
      if (var12 != null) {
         var11 = var12.method1();
         if (var11 != null) {
            int var13 = var18 & 0xFF000000 | 16777215;
            com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, var11, this.x + 6.0F, this.y + 6.0F, 10.0F, 10.0F, var13);
         }

         if (var4 != null && var4.method1().contains(com.moonsworth.lunar.client.framework.mod.Calculator2Handler.field3) && !var12.method3()) {
            float var20 = FontRegistry.method10().method4(var19);
            FontRegistry.method7()
               .method14(
                  var1,
                  this.OHROCHICOIOICHOCRROORRCIIICIHO("new", new Object[0]).toUpperCase(),
                  this.x + (var11 == null ? 6.0F : 20.0F) + var20 + 4.0F,
                  this.y + this.height / 2.0F - 8.0F,
                  -1879104990
               );
         }
      }

      FontRegistry.method10().method13(var1, var19, this.x + (var11 == null ? 6.0F : 20.0F), this.y + this.height / 2.0F - 4.0F, -4868683);
      this.field19.method3(var1, var2, var3);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
