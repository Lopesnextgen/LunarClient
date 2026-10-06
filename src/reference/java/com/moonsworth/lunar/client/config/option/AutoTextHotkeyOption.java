package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindCaptureWidget;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.mixin.MixinHelper2;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class AutoTextHotkeyOption extends AbstractValueOption<String> {
   private final ModifierKeybindOption field7;
   private final int field8;
   private boolean field9;

   public AutoTextHotkeyOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<String> var2,
      String var3,
      int var4,
      ModifierKeybindOption var5
   ) {
      super(var1, var2, var3);
      this.field8 = var4;
      this.field7 = var5;
   }

   @Override
   protected void method11(MixinHelper2<ClientOption<?>> var1) {
      super.method11(var1);
      var1.method2(new ClientOption[]{this.field7});
   }

   @Override
   public String getName() {
      return this.method1("autoHotkey", new Object[]{this.field8});
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new KeybindCaptureWidget(this, var1);
   }

   @Override
   public void reset() {
      super.reset();
      this.field7.method9(KeyCode.KEY_NONE);
   }

   @Override
   public boolean isDefault() {
      return super.isDefault() && this.field7.isDefault();
   }

   @Generated
   public ModifierKeybindOption method7() {
      return this.field7;
   }

   @Generated
   public int getIndex() {
      return this.field8;
   }

   @Generated
   public boolean method8() {
      return this.field9;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof AutoTextHotkeyOption var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (!super.equals(var1)) {
            return false;
         }

         if (this.getIndex() != var2.getIndex()) {
            return false;
         }

         if (this.method8() != var2.method8()) {
            return false;
         }

         ModifierKeybindOption var3 = this.method7();
         ModifierKeybindOption var4 = var2.method7();
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof AutoTextHotkeyOption;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = super.hashCode();
      var2 = var2 * 59 + this.getIndex();
      var2 = var2 * 59 + (this.method8() ? 79 : 97);
      ModifierKeybindOption var3 = this.method7();
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }

   @Generated
   public void method5(boolean var1) {
      this.field9 = var1;
   }

   public static class Data extends DefaultValueBuilder<AutoTextHotkeyOption.Data, AutoTextHotkeyOption, String> {
      private int index = Integer.MIN_VALUE;
      private KeyCode field14;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.KEYBIND;
      }

      @Override
      protected @Nullable Codec<String> method3() {
         return Codec.STRING;
      }

      @Contract("_->this")
      public AutoTextHotkeyOption.Data method3(int var1) {
         this.index = var1;
         return this;
      }

      @Contract("_->this")
      public AutoTextHotkeyOption.Data method4(KeyCode var1) {
         this.field14 = var1;
         return this;
      }

      protected AutoTextHotkeyOption method11() {
         if (this.index == Integer.MIN_VALUE) {
            throw new OptionConfigException(this, "Index must be set!");
         }

         ModifierKeybindOption.Data var1 = OptionFactory.method18(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC)
            .method5(this.field14 == null ? KeyCode.KEY_NONE : this.field14);
         if (this.HHOHIICRCOIICHIRCCOCRRIOHHICIO != null) {
            var1.method11(this.HHOHIICRCOIICHIRCCOCRRIOHHICIO);
         }

         return new AutoTextHotkeyOption(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.index, (ModifierKeybindOption)var1.RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
         );
      }
   }
}
