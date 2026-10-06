package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class KeybindResetWidget extends KeybindOptionWidget<SimpleKeybindOption> implements EditState {
   private final ToggleButtonWidget field17;
   private boolean field18;

   public KeybindResetWidget(SimpleKeybindOption var1, GuiWidget var2) {
      super(var1, var2);
      this.field16 = new TextLabelWidget(this, this.getName());
      this.field16.method15(() -> !this.field18 && !var1.isUnique() ? -2538942 : -4275267);
      this.field17 = new ToggleButtonWidget(this, "resetToDefaults", LcuiScreen.field1);
      this.field17.method4((var1x, var2x) -> {
         this.field18 = false;
         this.field16.setActive(false);
         this.RCOIRCICIOICICIHCRHRHRRHCROHOI();
         this.field16.setText(this.getName());
         return true;
      });
      this.method4((var2x, var3) -> {
         if ((var3 == 4 || var3 == 5 || var3 == 3 || var3 == 2) && this.field18) {
            this.field18 = false;
            this.field16.setActive(false);
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(KeyCode.valueOf("KEY_MOUSE" + (var3 + 1)));
            this.field16.setText(((KeyCode)var1.get()).name());
            return true;
         }

         if (this.field17.method3(var2x)) {
            return this.field17.method6(var2x, var3);
         }

         if (!this.field16.method3(var2x)) {
            return false;
         }

         if (var3 == 1 && !this.field18) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(KeyCode.KEY_NONE);
            this.field16.setText(this.getName());
         } else {
            this.field18 = !this.field18;
            if (this.field18) {
               this.field16.setText("?");
            } else {
               this.field16.setText(this.getName());
            }
         }

         return true;
      });
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public void update() {
      this.field16.update();
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method2(var1, var2, var3, this.getHeight());
      this.field16.method2(var1 + var3 - 82.0F, var2 + 2.0F, 70.0F, 12.0F);
      this.field17.method2(var1 + var3 - 9.0F, var2 + 3.0F, 8.0F, 8.0F);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      this.field16.method3(var1, var2, var3);
      if (!this.option.isDefault()) {
         this.field17.method3(var1, var2, var3);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field18) {
         if (var2 == KeyCode.KEY_ESCAPE) {
            this.option.OIRHOOIICOCIOOHICRRRICORIHHIHC(KeyCode.KEY_NONE);
            this.field16.setText(this.getName());
            this.field18 = false;
            return;
         }

         this.field18 = false;
         this.option.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2);
         this.field16.setText(this.getName());
      }
   }

   @Override
   public void close() {
   }

   private String getName() {
      return ((KeyCode)this.option.get()).getName();
   }

   @Override
   public boolean method5(MarkerModel.Data2 var1) {
      return super.method5(var1) && !this.field16.method3(var1) && !this.field17.method3(var1);
   }

   @Override
   public boolean isEditing() {
      return this.field18;
   }

   @Override
   public boolean method1(MarkerModel.Data2 var1) {
      return super.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1) || this.isEditing();
   }
}
