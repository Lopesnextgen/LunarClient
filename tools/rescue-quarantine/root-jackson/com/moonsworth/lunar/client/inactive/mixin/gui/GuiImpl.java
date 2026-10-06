package com.moonsworth.lunar.client.inactive.mixin.gui;

import com.eliotlash.molang.ast.Evaluatable;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fps.mixin.EvaluatableImpl;
import com.moonsworth.lunar.client.inactive.gui.GuiHandler;
import lombok.Generated;

public class GuiImpl extends GuiHandler {
   @Annotation27(value = "plays_when", required = true)
   private Evaluatable field5;
   @Annotation27("animation_speed")
   private Evaluatable field6 = new EvaluatableImpl(1.0);

   @Generated
   public Evaluatable method1() {
      return this.field5;
   }

   @Generated
   public Evaluatable method6() {
      return this.field6;
   }
}
