package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.ArrayList;
import java.util.List;

public class MultiNumberOptionWidget<T extends Number & Comparable<T>> extends OptionWidget<MultiNumberOption<T>> implements EditState {
   private final List<CommandFieldWidget> field16 = new ArrayList<>();

   public MultiNumberOptionWidget(MultiNumberOption<T> var1, GuiWidget var2) {
      super(var1, var2);

      for (int var3 = 0; var3 < ((List)var1.get()).size(); var3++) {
         CommandFieldWidget var4 = new CommandFieldWidget(this, this.option.method2(var3));
         var4.method17(var4::getText);
         var4.method18(var2x -> {
            int var3x = var2x.indexOf(46);
            if (var3x >= 0) {
               int var4x = var2x.indexOf(46, var3x + 1);
               if (var4x >= 0) {
                  return;
               }
            }

            var2x = (var2x.startsWith("-") ? "-" : "") + var2x.replaceAll("[^0-9.]", "");
            var2x = FontRegistry.field8.method21(var2x, 50.0);
            var4.method1(var2x, true);
            this.method2();
         });
         var4.method15(var2x -> {
            if (!var2x) {
               try {
                  ArrayList var3x = new ArrayList();

                  for (CommandFieldWidget var5 : this.field16) {
                     var3x.add(Double.parseDouble(var5.getText()));
                  }

                  var1.method1(var3x);
               } catch (NumberFormatException var6) {
               }
            }
         });
         this.field16.add(var4);
      }

      this.method4((var2x, var3x) -> {
         int var4x = 0;

         for (CommandFieldWidget var6 : this.field16) {
            if (var6.isActive()) {
               if (var6.method3(var2x)) {
                  var6.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var2x, var3x);
                  return true;
               }

               var6.method14(() -> false);
               var6.method8().accept(false);
               return true;
            }

            if (var6.method3(var2x) && !var6.isActive()) {
               var6.method1(var1.method2(var4x), true);
               var6.method8().accept(true);
               var6.method14(() -> true);
               var6.setActive(true);
               return true;
            }

            var4x++;
         }

         return false;
      });
      this.method3((var1x, var2x) -> {
         for (CommandFieldWidget var4x : this.field16) {
            if (var4x.isActive() && !var4x.method3(var1x)) {
               var4x.method14(() -> false);
               var4x.method8().accept(false);
               return true;
            }
         }

         return false;
      });
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1, var2, var3);
      int var4 = Math.min((int)this.height, 12);

      for (CommandFieldWidget var6 : this.field16) {
         var6.method2(var1 + var3 / 3.0F, var2 + (this.height - var4) / 2.0F, 16.0F, var4);
         var1 += 20.0F;
      }

      this.method2();
   }

   private void method2() {
      int var1 = 0;

      for (CommandFieldWidget var3 : this.field16) {
         float var4 = FontRegistry.field8.method4(var3.getText()) + 12.0F;
         if (var4 < 16.0F) {
            var4 = 16.0F;
         }

         if (var4 > 60.0F) {
            var4 = 60.0F;
         }

         var3.setWidth(var4);
         var3.setX(var1 + this.x + this.width / 3.0F);
         var3.method25().CIRIRHORCIORHICHOCOIRICHROCIIH(0);
         var1 += (int)(var3.getWidth() + 4.0F);
      }
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public void update() {
      for (CommandFieldWidget var2 : this.field16) {
         var2.update();
         if (var2.isActive() && !var2.method7().getAsBoolean()) {
            Bridge.method18().method3(false);
            var2.setActive(false);
         }
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      int var4 = 0;
      float var5 = this.x;

      for (CommandFieldWidget var7 : this.field16) {
         if (var7.isActive()) {
            var7.method3(var1, var2, var3);
         } else {
            CachedFontImpl var8 = FontRegistry.method13();
            String var9 = this.option.method2(var4);
            float var10 = var8.method4(var9);
            var8.method13(var1, var9, var5 + this.width / 3.0F, this.y + this.height / 2.0F - var8.getHeight() / 2 - 2.0F, -1879048193);
            var5 += var10 + 10.0F;
         }

         var4++;
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      for (CommandFieldWidget var4 : this.field16) {
         var4.method4(var1, var2);
      }
   }

   @Override
   public boolean isEditing() {
      for (CommandFieldWidget var2 : this.field16) {
         if (var2.isActive()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void close() {
   }
}
