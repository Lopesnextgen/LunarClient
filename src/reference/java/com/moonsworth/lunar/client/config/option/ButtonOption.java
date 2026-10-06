package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.TextOptionWidget;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

public class ButtonOption extends AbstractValueOption<String> {
   private final Runnable field7;
   private final float field8;
   private final boolean field9;

   public ButtonOption(
      @Annotation(method1 = Annotation.Type.SETTING_BUTTONS) String var1, Codec<String> var2, Runnable var3, float var4, boolean var5
   ) {
      super(var1, var2, var1);
      this.field7 = var3;
      this.field8 = var4;
      this.field9 = var5;
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return !this.field9 ? new TextOptionWidget(this, var1, this.field8) : new TextOptionWidget(this, var1, this.field8) {
         @Override
         public boolean method3() {
            return true;
         }
      };
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + ".buttons";
   }

   @Generated
   public Runnable method7() {
      return this.field7;
   }

   @Generated
   public float getWidth() {
      return this.field8;
   }

   @Generated
   public boolean method8() {
      return this.field9;
   }

   public static class Data extends OptionBuilderBase<ButtonOption.Data, ButtonOption, String> {
      private Runnable field14;
      private float width = 70.0F;
      private boolean field15 = false;

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.BUTTON;
      }

      @Override
      protected @Nullable Codec<String> method3() {
         return null;
      }

      @Override
      protected boolean method7() {
         return true;
      }

      protected Data(@Annotation(method1 = Annotation.Type.SETTING_BUTTONS) String var1) {
         super(var1);
      }

      public ButtonOption.Data method4(Runnable var1) {
         this.field14 = var1;
         return this;
      }

      public ButtonOption.Data method5(@Range(from = 0L, to = 2147483647L) float var1) {
         this.width = var1;
         return this;
      }

      public ButtonOption.Data method10() {
         this.field15 = true;
         return this;
      }

      protected ButtonOption method11() {
         return new ButtonOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field14, this.width, this.field15);
      }
   }
}
