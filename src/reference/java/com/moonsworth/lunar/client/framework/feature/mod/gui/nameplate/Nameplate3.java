package com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.ui.hud.row.Gui2Extension;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public class Nameplate3 extends HudElementBase {
   private static final float field9 = 100.0F;
   private static final float field10 = 8.0F;
   private static final float field11 = 1.5F;
   private static final float field12 = 97.0F;
   private static final float field13 = 5.0F;
   private static final int field14 = 5;
   private final Nameplate3.Data2 field15;
   private final Supplier<List<Nameplate3.Data>> field16;
   private List<Nameplate3.Data> field17;

   public Nameplate3(float var1, float var2, @NotNull HudAnchor var3, Nameplate3.Data2 var4, Supplier<List<Nameplate3.Data>> var5) {
      super(var1, var2, var3);
      this.field15 = var4;
      this.field16 = var5;
   }

   @Override
   public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      this.method2(var1, var2, var3, var4 ? this.field16.get() : this.field17);
   }

   private void method2(HudBaseRenderEvent var1, float var2, float var3, List<Nameplate3.Data> var4) {
      List var5 = this.method5(var4);
      this.method6(var4, var5);
      MixinHelper_4 var6 = var1.method2();
      Bridge10_2 var7 = ThreadModuleDump63.method10();
      float var8 = var2 + 5.0F;
      float var9 = var3 + 5.0F;
      if (this.field15.field4.get()) {
         this.field15.field5.method11(var6, var2, var3, this.getWidth(), this.getHeight());
      }

      for (Component var11 : var5) {
         float var12 = var7.bridge$getStringWidth(var11);
         float var13 = this.method4(var8, var12);
         var6.method11(var7, var11, var13, var9, -1, true);
         var9 += var7.method19() + 2;
      }

      if (this.method7(var4)) {
         Nameplate3.Data var14 = (Nameplate3.Data)var4.get(0);
         float var15 = this.method4(var8, 100.0F);
         this.method3(var6, var15, var9, var14);
      }
   }

   private void method3(MixinHelper_4 var1, float var2, float var3, Nameplate3.Data var4) {
      LcuiScreen.method117(var1, var2, var3, 100.0F, 8.0F, 7.0F, 267386880);
      float var5 = var2 + 1.5F;
      float var6 = var3 + 1.5F;
      LcuiScreen.method117(var1, var5, var6, 97.0F, 5.0F, 5.0F, -13421773);
      LcuiScreen.method117(var1, var5, var6, 97.0F * var4.method2(), 5.0F, 5.0F, 0xFF000000 | var4.field2);
   }

   private float method4(float var1, float var2) {
      float var3 = this.getWidth() - 10.0F;

      return switch ((Gui2Extension)this.field15.field6.get()) {
         case LEFT -> var1;
         case CENTER -> var1 + (var3 - var2) / 2.0F;
         case RIGHT -> var1 + var3 - var2;
      };
   }

   private List<Component> method5(List<Nameplate3.Data> var1) {
      if (!this.field15.field1.get()) {
         return List.of();
      }

      ArrayList var2 = new ArrayList();
      NumberFormat var3 = NumberFormat.getIntegerInstance(Locale.ROOT);

      for (Nameplate3.Data var5 : var1) {
         Object var6 = Component.text(var3.format(var5.field1));
         if (var5.method1()) {
            var6 = var6.append(Component.text("/" + var3.format(var5.max)));
         }

         if (this.field15.field2.get()) {
            var6 = var6.append(Component.text(var5.field3));
         }

         Component var10 = var6.color(TextColor.color(var5.field2));
         var2.add(var10);
      }

      if (this.field15.field7.get()) {
         Object var7 = Component.empty();

         for (int var9 = 0; var9 < var2.size(); var9++) {
            var7 = var7.append((Component)var2.get(var9));
            if (var9 != var2.size() - 1) {
               var7 = var7.append(Component.text(" "));
            }
         }

         Component var8 = var7.compact();
         return List.of(var8);
      } else {
         return var2;
      }
   }

   private void method6(List<Nameplate3.Data> var1, List<Component> var2) {
      float var3 = 0.0F;
      float var4 = 0.0F;
      boolean var5 = this.method7(var1);
      if (var5) {
         var3 = 100.0F;
         var4 = 8.0F;
      }

      Bridge10_2 var6 = ThreadModuleDump63.method10();

      for (int var7 = 0; var7 < var2.size(); var7++) {
         Component var8 = (Component)var2.get(var7);
         float var9 = var6.bridge$getStringWidth(var8);
         if (var9 > var3) {
            var3 = var9;
         }

         var4 += var6.method19();
         if (var5 || var7 != var2.size() - 1) {
            var4 += 2.0F;
         }
      }

      this.method16(var3 + 10.0F, var4 + 10.0F);
   }

   private boolean method7(List<Nameplate3.Data> var1) {
      return this.field15.field3.get() && !var1.isEmpty() && ((Nameplate3.Data)var1.get(0)).method1();
   }

   @Override
   public boolean method4(boolean var1) {
      return var1 ? true : this.field17 != null && !this.field17.isEmpty();
   }

   @Generated
   public void method9(List<Nameplate3.Data> var1) {
      this.field17 = var1;
   }

   public class Data {
      private final int field1;
      private final int max;
      private final int field2;
      private final char field3;

      public Data(int var1, int var2, int var3, char var4) {
         this.field1 = var1;
         this.max = var2;
         this.field2 = var3;
         this.field3 = var4;
      }

      public boolean method1() {
         return this.max != -1;
      }

      public float method2() {
         return this.field1 > this.max ? 1.0F : (float)this.field1 / this.max;
      }

      public int method3() {
         return this.field1;
      }

      public int method4() {
         return this.field2;
      }

      public char method5() {
         return this.field3;
      }
   }

   public class Data2 {
      private final ToggleOption field1;
      private final ToggleOption field2;
      private final ToggleOption field3;
      private final ToggleOption field4;
      private final ColorOption field5;
      private final EnumOption<Gui2Extension> field6;
      private final ToggleOption field7;

      public Data2(
         ToggleOption var1,
         ToggleOption var2,
         ToggleOption var3,
         ToggleOption var4,
         ColorOption var5,
         EnumOption<Gui2Extension> var6,
         ToggleOption var7
      ) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
      }

      public ToggleOption method1() {
         return this.field1;
      }

      public ToggleOption method2() {
         return this.field2;
      }

      public ToggleOption method3() {
         return this.field3;
      }

      public ToggleOption method4() {
         return this.field4;
      }

      public ColorOption method5() {
         return this.field5;
      }

      public EnumOption<Gui2Extension> method6() {
         return this.field6;
      }

      public ToggleOption method7() {
         return this.field7;
      }
   }
}
