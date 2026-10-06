package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.CrosshairPreviewWidget;
import com.moonsworth.lunar.client.ui.ColorAnimation;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.mod.render.crosshair.CrosshairStyle;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class CrosshairDrawOption extends com.moonsworth.lunar.client.config.option.AbstractBooleanOption {
   private final CrosshairStyle field8;
   private final ColorAnimation field9 = new ColorAnimation(200L);

   public CrosshairDrawOption(CrosshairStyle crosshairchildmod1) {
      super("crosshairDraw", Codec.BOOL, true);
      this.field8 = crosshairchildmod1;
      this.field9.start();
   }

   public float method9() {
      return this.get() ? this.field9.method9() : 1.0F - this.field9.method9();
   }

   public void method10() {
      this.method10(!this.get());
      this.field9.start();
   }

   @Nullable
   @Override
   protected OptionWidget<?> method25(GuiWidget calculator2handler1) {
      return new CrosshairPreviewWidget(this, calculator2handler1);
   }

   @Generated
   public CrosshairStyle method11() {
      return this.field8;
   }
}
