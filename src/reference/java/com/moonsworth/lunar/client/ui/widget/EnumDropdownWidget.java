package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EnumDropdownWidget<T extends Enum<T> & OptionEnumValue> extends OptionWidget<EnumOption<T>> {
   private static final CachedFontImpl field16 = FontRegistry.field6;
   private static final int field17 = 10;
   private final List<Enum<T>> field18 = new ArrayList<>();
   private final List<AnimatedValue> field19 = new ArrayList<>();
   private final List<AnimatedValue> field20 = new ArrayList<>();

   public EnumDropdownWidget(EnumOption<T> var1, GuiWidget var2) {
      super(var1, var2);
      Class var3 = ((Enum)var1.get()).getClass();
      Enum[] var4 = (Enum[])var3.getEnumConstants();

      for (Enum var8 : var4) {
         this.field18.add(var8);
         this.field19.add(new AnimatedValue(553648127, -1593835521));
         this.field20.add(new AnimatedValue(553648127, 1174405119));
      }

      this.method4((var2x, var3x) -> {
         float var4x = (this.width - 10 * (this.field18.size() - 1)) / this.field18.size();
         int var5 = (int)((var2x.method9() - this.x) / (var4x + 10.0F));
         if (var5 >= 0 && var5 < this.field18.size()) {
            if ((var2x.method9() - this.x) % (var4x + 10.0F) > var4x) {
               return false;
            }

            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(this.field18.get(var5));
            return true;
         } else {
            return false;
         }
      });
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method2(var1, var2, var3, this.getHeight());
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 15.0F;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = (this.width - 10 * (this.field18.size() - 1)) / this.field18.size();
      var3 = var3 && this.method3(var2);
      float var5 = this.y + 2.0F;
      float var6 = this.x;

      for (int var7 = 0; var7 < this.field18.size(); var7++) {
         Enum var8 = this.field18.get(var7);
         boolean var9 = var8 == this.getOption().get();
         boolean var10 = var3 && var2.method12() > var6 && var2.method12() < var6 + var4;
         int var11 = this.field19.get(var7).method2(var9);
         int var12 = this.field20.get(var7).method2(var10);
         LcuiScreen.method53(var1, var6, var5, var4, this.height - 3.0F, 4.0F, 1076176165, var11, var12);
         String var13 = var8.toString().toUpperCase(Locale.ROOT).replace("", " ").trim();
         float var14 = (int)(var6 + var4 / 2.0F - field16.method4(var13) / 2.0F);
         float var15 = (int)(var5 + (this.height - 3.0F) / 2.0F - field16.getHeight());
         field16.method13(var1, var13, var14 + 1.0F, var15 + 1.0F, 536870912);
         field16.method13(var1, var13, var14, var15, -4275267);
         var6 += var4 + 10.0F;
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Override
   public boolean method5(MarkerModel.Data2 var1) {
      return false;
   }
}
