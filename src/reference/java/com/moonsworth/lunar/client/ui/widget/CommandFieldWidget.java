package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.Generated;

public class CommandFieldWidget extends TextLabelWidget {
   private BooleanSupplier field28 = () -> false;
   private Consumer<Boolean> field29 = var0 -> {};
   private BooleanSupplier field30 = () -> true;
   private Supplier<String> field31 = () -> "";
   private Consumer<String> field32 = var0 -> {};
   private String field33 = "/Command";
   private ThreadModuleDump51 field34;
   private IconTextButton field35;

   public CommandFieldWidget(GuiWidget var1, String var2) {
      super(var1, var2, FontRegistry.field8, false);
      this.method3((var1x, var2x) -> {
         if (this.isActive()) {
            boolean var3 = this.field35.method3(var1x);
            this.method1(var1x, var2x, var3);
            return var3;
         } else {
            return this.field35.method2(var1x, var2x);
         }
      });
      this.method4((var1x, var2x) -> {
         if (this.method3(var1x) && var2x == 1) {
            this.field35.method17(false);
            this.field29.accept(false);
            this.field32.accept(this.field33);
            this.method1(this.field31.get(), true);
            this.setActive(this.field28.getAsBoolean());
            Bridge.method18().method3(this.field28.getAsBoolean());
            return true;
         }

         if (this.method3(var1x) && this.field30.getAsBoolean()) {
            this.method1(var1x, var2x, true);
            if (this.field34 != null) {
               this.field34.accept(var1x, var2x);
            }

            return true;
         } else {
            return false;
         }
      });
      this.field35 = new IconTextButton(this, null, FontRegistry.field8, "", 544831865, 1081702777);
   }

   private void method1(MarkerModel.Data2 var1, int var2, boolean var3) {
      if (var3) {
         this.field35.method6(var1, var2);
      } else {
         this.field35.method17(false);
      }

      this.field29.accept(this.field35.method18());
      Bridge.method18().method3(this.field28.getAsBoolean());
      this.method1(this.field31.get(), true);
      this.setActive(this.field28.getAsBoolean());
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      this.field35.method2(var1, var2, var3, var4);
   }

   @Override
   public void setX(float var1) {
      this.field35.setX(var1);
      super.setX(var1);
   }

   @Override
   public void setY(float var1) {
      this.field35.setY(var1);
      super.setY(var1);
   }

   @Override
   public void setWidth(float var1) {
      this.field35.setWidth(var1);
      super.setWidth(var1);
   }

   @Override
   public void setHeight(float var1) {
      this.field35.setHeight(var1);
      super.setHeight(var1);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      this.field35.method3(var1, var2, var3);
   }

   @Override
   public void update() {
      if (this.isActive() && !this.field28.getAsBoolean()) {
         Bridge.method18().method3(false);
         this.setActive(false);
      }

      String var1 = this.field31.get();
      if (!this.field35.getText().equals(var1)) {
         this.field35.setText(var1);
         this.field35.method31(0);
      }

      this.field35.update();
      super.update();
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field28.getAsBoolean()) {
         if (var2 != KeyCode.KEY_ESCAPE && var2 != KeyCode.KEY_RETURN) {
            this.field35.method4(var1, var2);
            this.field32.accept(this.field35.getText());
         } else {
            Bridge.method18().method3(false);
            this.field29.accept(false);
            this.setActive(false);
            this.field35.method17(false);
         }
      }
   }

   @Override
   public void setActive(boolean var1) {
      this.method25().RRCCIHHHIHORIHCRRRIRHCIOCRCRIR(var1);
      super.setActive(var1);
   }

   @Generated
   public BooleanSupplier method7() {
      return this.field28;
   }

   @Generated
   public Consumer<Boolean> method8() {
      return this.field29;
   }

   @Generated
   public BooleanSupplier method9() {
      return this.field30;
   }

   @Generated
   public Supplier<String> method10() {
      return this.field31;
   }

   @Generated
   public Consumer<String> method14() {
      return this.field32;
   }

   @Generated
   public String method15() {
      return this.field33;
   }

   @Generated
   public ThreadModuleDump51 method17() {
      return this.field34;
   }

   @Generated
   public void method14(BooleanSupplier var1) {
      this.field28 = var1;
   }

   @Generated
   public void method15(Consumer<Boolean> var1) {
      this.field29 = var1;
   }

   @Generated
   public void method16(BooleanSupplier var1) {
      this.field30 = var1;
   }

   @Generated
   public void method17(Supplier<String> var1) {
      this.field31 = var1;
   }

   @Generated
   public void method18(Consumer<String> var1) {
      this.field32 = var1;
   }

   @Generated
   public void method19(String var1) {
      this.field33 = var1;
   }

   @Generated
   public void method20(ThreadModuleDump51 var1) {
      this.field34 = var1;
   }

   @Generated
   public void method21(IconTextButton var1) {
      this.field35 = var1;
   }

   @Generated
   public IconTextButton method25() {
      return this.field35;
   }
}
