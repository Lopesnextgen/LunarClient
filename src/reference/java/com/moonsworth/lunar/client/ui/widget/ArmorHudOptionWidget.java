package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.List;

public class ArmorHudOptionWidget extends OptionWidget<ListOption<String>> {
   private final float field16 = 140.0F;
   private final float field17 = 17.0F;
   private final float field18 = 5.0F;
   private float field19;
   private final KeybindClearButton[] field20 = new KeybindClearButton[40 + (Bridge.getMinecraftVersion().method22() ? 0 : 1)];

   public ArmorHudOptionWidget(ListOption<String> var1, GuiWidget var2) {
      super(var1, var2);
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      this.field19 = (var3 - 28.0F) / 9.0F;
      float var4 = this.getHeight();
      if (var4 > 140.0F) {
         this.field19 = 11.625F;
         var3 = 10.0F + 9.0F * this.field19 + 18.0F;
         var4 = 140.0F;
      }

      this.method2(var1, var2, var3, var4);
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      int var5 = 0;
      this.method3(var5++, var1, var2 + 17.0F, "helmet");
      this.method3(var5++, var1, var2 + 17.0F + 2.0F + this.field19, "chestplate");
      this.method3(var5++, var1, var2 + 17.0F + 4.0F + 2.0F * this.field19, "leggings");
      this.method3(var5++, var1, var2 + 17.0F + 6.0F + 3.0F * this.field19, "boots");
      float var6 = 28.0F + 4.0F * this.field19;

      for (int var7 = 0; var7 < 3; var7++) {
         for (int var8 = 0; var8 < 9; var8++) {
            this.method3(var5++, var1 + var8 * 2 + var8 * this.field19, var2 + var6 + var7 * 2 + var7 * this.field19, "inv:" + (var7 + 1) + ":" + (var8 + 1));
         }
      }

      var6 += 9.0F + 3.0F * this.field19;

      for (int var14 = 0; var14 < 9; var14++) {
         this.method3(var5++, var1 + var14 * 2 + var14 * this.field19, var2 + var6, "hotbar:" + (var14 + 1));
      }

      if (this.field20.length == 41) {
         this.method3(var5, var1 + 6.0F + 3.0F * this.field19, var2 + 17.0F + 6.0F + 3.0F * this.field19, "offhand");
      }
   }

   public void method3(int var1, float var2, float var3, String var4) {
      KeybindClearButton var5 = new KeybindClearButton(this, ((List)this.option.get()).contains(var4));
      var5.method2(var2 + 5.0F, var3 + 5.0F, this.field19, this.field19);
      var5.method3(() -> {
         if (var5.isState()) {
            this.option.add(var4);
         } else {
            this.option.remove(var4);
         }
      });
      this.field20[var1] = var5;
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 27.0F + 8.0F * this.field19 + 20.0F;
   }

   @Override
   public boolean method3() {
      return true;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method12().method13(var1, this.option.getName(), this.x, this.y + 3.0F, -4079426);
      LcuiScreen.method100(var1, this.x, this.y + 17.0F, this.width, this.height - 17.0F, 0.25F, -820044001, -820175587);

      for (KeybindClearButton var7 : this.field20) {
         var7.method3(var1, var2, this.method3(var2));
      }

      LcuiScreen.method94(
         var1, this.x + 2.0F + this.field19 + 5.0F, this.y + 17.0F + 5.0F, this.field19 * 2.0F + 2.0F, this.field19 * 4.0F + 6.0F, -820504552
      );
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      for (KeybindClearButton var6 : this.field20) {
         if (var6.method1(var1) && var6.method6(var1, var2)) {
            return true;
         }
      }

      return super.method6(var1, var2);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
