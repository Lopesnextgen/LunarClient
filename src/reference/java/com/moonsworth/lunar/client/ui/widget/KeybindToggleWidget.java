package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class KeybindToggleWidget extends KeybindOptionWidget<ModifierKeybindOption> implements EditState {
   private final ToggleButtonWidget field17;
   private boolean field18;
   private KeyCode field19 = null;
   private String name;

   public KeybindToggleWidget(ModifierKeybindOption var1, GuiWidget var2) {
      super(var1, var2);
      this.name = var1.getName();
      this.field16 = new TextLabelWidget(this, var1.method17());
      this.field16.method15(() -> !this.field18 && !var1.isUnique() ? -2538942 : -4275267);
      this.field17 = new ToggleButtonWidget(this, "resetToDefaults", LcuiScreen.field1);
      this.field17.method4((var2x, var3) -> {
         this.field18 = false;
         this.field16.setActive(false);
         this.RCOIRCICIOICICIHCRHRHRRHCROHOI();
         this.field16.setText(var1.method17());
         return true;
      });
      this.method4((var2x, var3) -> {
         if ((var3 == 4 || var3 == 5 || var3 == 3 || var3 == 2) && this.field18) {
            this.field18 = false;
            this.field16.setActive(false);
            var1.method9(KeyCode.valueOf("KEY_MOUSE" + (var3 + 1)));
            this.field16.setText(((KeyCombo)var1.get()).method8().getName());
            return true;
         }

         if (this.field17.method3(var2x)) {
            return this.field17.method6(var2x, var3);
         }

         if (!this.field16.method3(var2x)) {
            return false;
         }

         if (var3 == 1 && !this.field18) {
            var1.method9(KeyCode.KEY_NONE);
            this.field16.setText(((KeyCombo)var1.get()).method8().getName());
         } else {
            this.field18 = !this.field18;
            if (this.field18) {
               this.field16.setText("?");
            } else {
               this.field16.setText(var1.method17());
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
      if (this.field19 != null && this.field18 && !this.method3(this.field19)) {
         this.option.method9(this.field19);
         this.field18 = false;
         this.field19 = null;
         this.field16.setText(((KeyCombo)this.option.get()).method8().getName());
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.name, this.x, this.y + 1.5F, -4079426);
      this.field16.method2(this.x + this.width - 82.0F, this.y + 2.0F, 70.0F, 12.0F);
      this.field16.method3(var1, var2, var3);
      if (!this.option.isDefault()) {
         this.field17.method2(this.x + this.width - 9.0F, this.y + 3.0F, 8.0F, 8.0F);
         this.field17.method3(var1, var2, var3);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field18) {
         if (var2 == KeyCode.KEY_ESCAPE) {
            this.option.method9(KeyCode.KEY_NONE);
            this.field18 = false;
            this.field19 = null;
            this.field16.setText(((KeyCombo)this.option.get()).method8().getName());
            return;
         }

         if (var2 == KeyCode.KEY_LSHIFT || var2 == KeyCode.KEY_LCONTROL || var2 == KeyCode.KEY_LMENU) {
            this.field19 = var2;
            return;
         }

         this.option
            .method8(new KeyCombo(this.method3(KeyCode.KEY_LMENU), this.method3(KeyCode.KEY_LSHIFT), this.method3(KeyCode.KEY_LCONTROL), var2));
         this.field18 = false;
         this.field19 = null;
         this.field16.setText(this.option.method17());
      }
   }

   @Override
   public void close() {
   }

   private boolean method3(KeyCode var1) {
      if (Bridge.method18().method1(KeyCode.KEY_LSHIFT)) {
         return var1 == KeyCode.KEY_LSHIFT;
      } else if (LcuiScreen.isCtrlKeyDown()) {
         return var1 == KeyCode.KEY_LCONTROL;
      } else {
         return Bridge.method18().method1(KeyCode.KEY_LMENU) ? var1 == KeyCode.KEY_LMENU : Bridge.method18().method1(var1);
      }
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

   @Generated
   public void setName(String var1) {
      this.name = var1;
   }
}
