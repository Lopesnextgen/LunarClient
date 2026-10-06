package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class NumberOptionWidget<T extends Number & Comparable<T>> extends OptionWidget<ClientOption<T>> implements EditState {
   protected final NumberSliderWidget<T> field16;
   protected final CommandFieldWidget field17;
   @Nullable
   protected final ToggleButtonWidget field18;
   protected boolean field19 = true;

   public NumberOptionWidget(ClientOption<T> var1, GuiWidget var2) {
      this(var1, var2, true);
   }

   public NumberOptionWidget(ClientOption<T> var1, GuiWidget var2, boolean var3) {
      super(var1, var2);
      this.field16 = new NumberSliderWidget<>(var1, this);
      this.field18 = var3 ? new ToggleButtonWidget(this, "resetToDefaults", LcuiScreen.field1) : null;
      this.field17 = new CommandFieldWidget(this, this.option.getValueAsString());
      this.field17.method17(this.field17::getText);
      this.field17.method18(var1x -> {
         int var2x = var1x.indexOf(46);
         if (var2x >= 0) {
            int var3x = var1x.indexOf(46, var2x + 1);
            if (var3x >= 0) {
               return;
            }
         }

         var1x = (var1x.startsWith("-") ? "-" : "") + var1x.replaceAll("[^0-9.]", "");
         var1x = FontRegistry.field8.method21(var1x, 50.0);
         this.field17.method1(var1x, true);
         this.method5();
      });
      this.field17.method15(var1x -> {
         if (!var1x) {
            this.method4();
         }
      });
      if (this.field18 != null) {
         this.field18.method4((var1x, var2x) -> {
            this.RCOIRCICIOICICIHCRHRHRRHCROHOI();
            return true;
         });
      }

      this.method4(this::method1);
      this.method3((var1x, var2x) -> {
         if (this.field19 && this.field17.isActive() && !this.field17.method3(var1x)) {
            this.field17.method14(() -> false);
            this.field17.method8().accept(false);
         }

         return false;
      });
   }

   protected boolean method1(MarkerModel.Data2 var1, int var2) {
      if (this.field16.method6(var1)) {
         return this.field16.method13(var1, var2);
      }

      if (this.field18 != null && this.field18.method3(var1)) {
         return this.field18.method13(var1, var2);
      }

      if (this.field19 && this.field17.isActive()) {
         if (this.field17.method3(var1)) {
            this.field17.method13(var1, var2);
            return true;
         } else {
            this.field17.method14(() -> false);
            this.field17.method8().accept(false);
            return true;
         }
      } else if (this.field19 && this.field17.method3(var1) && !this.field17.isActive()) {
         this.field17.method1(this.option.getValueAsString(), true);
         this.field17.method8().accept(true);
         this.field17.method14(() -> true);
         this.field17.setActive(true);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1, var2, var3 + 8.0F);
      float var4 = FontRegistry.method14().method4(this.option.getName()) + this.method6() + 6.0F;
      float var5 = var3 - var3 / 3.0F;
      float var6 = var1 + var3 / 3.0F;
      if (var5 > var3 - (var4 + 15.0F)) {
         var5 = var3 - (var4 + 15.0F);
         var6 = var1 + var4 + 5.0F;
      }

      this.field16.method2(var6, var2, var5 - (this.field18 == null ? 0 : 10), this.height);
      if (this.field18 != null) {
         this.field18.method2(var1 + var3 - 9.0F, var2 + 3.0F, 8.0F, 8.0F);
      }

      int var7 = Math.min((int)this.height, 12);
      this.field17.method2(var6 - 20.0F, var2 + (this.height - var7) / 2.0F, 16.0F, var7);
      this.method5();
   }

   private void method4() {
      Number var1 = (Number)this.option.getDefaultValue();
      String var2 = this.field17.getText();

      Number var3;
      try {
         if (var1 instanceof Integer) {
            var3 = Integer.parseInt(var2);
         } else if (var1 instanceof Long) {
            var3 = Long.parseLong(var2);
         } else if (var1 instanceof Short) {
            var3 = Short.parseShort(var2);
         } else if (var1 instanceof Byte) {
            var3 = Byte.parseByte(var2);
         } else if (var1 instanceof Double) {
            var3 = Double.parseDouble(var2);
         } else {
            var3 = Float.parseFloat(var2);
         }
      } catch (NumberFormatException var5) {
         return;
      }

      this.option.method21(var3.toString());
   }

   private void method5() {
      float var1 = FontRegistry.field8.method4(this.field17.getText()) + 12.0F;
      if (var1 < 16.0F) {
         var1 = 16.0F;
      }

      if (var1 > 60.0F) {
         var1 = 60.0F;
      }

      this.field17.setWidth(var1);
      this.field17.setX(this.field16.getX() - 4.0F - var1);
      this.field17.method25().CIRIRHORCIORHICHOCOIRICHROCIIH(0);
   }

   private float method6() {
      NumberRule var1 = (NumberRule)this.option.RHRHIOOCICIORIOCIHHCIIRCRHHOII(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      CachedFontImpl var2 = FontRegistry.method7();
      return Math.max(var2.method4(var1.method7(var1.getMin())), var2.method4(var1.method7(var1.getMax())));
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public void update() {
      this.field17.update();
      this.field16.update();
      if (this.field17.isActive() && !this.field17.method7().getAsBoolean()) {
         Bridge.method18().method3(false);
         this.field17.setActive(false);
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      this.method7(var1, var2, var3);
      this.field16.method3(var1, var2, var3);
      if (this.field18 != null && !this.option.isDefault()) {
         this.field18.method3(var1, var2, var3);
      }
   }

   protected void method7(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      if (this.field19 && this.field17.isActive()) {
         this.field17.method3(var1, var2, var3);
      } else {
         CachedFontImpl var4 = FontRegistry.method7();
         String var5 = this.option.getValueAsString();
         float var6 = var4.method4(var5);
         var4.method13(var1, var5, this.field16.getX() - 8.0F - var6, this.y + this.height / 2.0F - var4.getHeight() / 2 - 2.0F, -1879048193);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      this.field17.method4(var1, var2);
   }

   @Override
   public boolean isEditing() {
      return this.field17.isActive();
   }

   @Override
   public void close() {
   }

   @Generated
   public NumberSliderWidget<T> method9() {
      return this.field16;
   }

   @Generated
   public boolean method10() {
      return this.field19;
   }

   @Generated
   public void method11(boolean var1) {
      this.field19 = var1;
   }
}
