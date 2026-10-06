package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public abstract class MixinHelper2_3 {
   protected final MixinHelper field1;

   public MixinHelper2_3(MixinHelper mixinHelper) {
      this.field1 = mixinHelper;
   }

   protected MixinHelper3 method1() {
      return this.field1.currentState();
   }

   protected MixinHelper2 method2() {
      return this.field1.getWidgetStateCache();
   }

   protected MixinHelper211 method3() {
      return this.field1.getScissorStack();
   }

   protected MixinHelper29 method4() {
      return this.field1.getTooltipRenderer();
   }

   protected MixinHelper27 method5() {
      return this.field1.getTextRenderer();
   }

   protected MixinHelper210 method6() {
      return this.field1.getRectRenderer();
   }

   protected MixinHelper28 method7() {
      return this.field1.getTextFieldRenderer();
   }

   protected MixinHelper25 method8() {
      return this.field1.getItemStackRenderer();
   }

   protected MixinHelper23 method9() {
      return this.field1.getSlotRenderer();
   }

   protected MixinHelper26 method10() {
      return this.field1.getBoxRenderer();
   }

   protected MixinHelper24 method11() {
      return this.field1.getPanelRenderer();
   }

   protected MixinHelper2_2 method12() {
      return this.field1.getMouseInput();
   }

   protected MixinHelper22 method13() {
      return this.field1.getKeyboardInput();
   }

   protected MixinHelper22_2 method14() {
      return this.field1.getScrollAnimator();
   }

   protected String method15() {
      return this.field1.getCurrentGuiId();
   }

   protected MixinHelperType getTheme() {
      return this.field1.getTheme();
   }

   protected MixinHelper_4 method16() {
      return this.field1.getDrawContext();
   }

   public void start() {
   }

   public void end() {
   }
}
