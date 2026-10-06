package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public abstract class GuiComponent {
   protected final GuiRenderer renderer;

   public GuiComponent(GuiRenderer mixinhelper1) {
      this.renderer = mixinhelper1;
   }

   protected GuiRenderState method1() {
      return this.renderer.currentState();
   }

   protected WidgetStateCache method2() {
      return this.renderer.getWidgetStateCache();
   }

   protected ScissorStack method3() {
      return this.renderer.getScissorStack();
   }

   protected MixinHelper29 method4() {
      return this.renderer.getTooltipRenderer();
   }

   protected ScaledTextRenderer method5() {
      return this.renderer.getTextRenderer();
   }

   protected RectRenderer method6() {
      return this.renderer.getRectRenderer();
   }

   protected TextFieldRenderer method7() {
      return this.renderer.getTextFieldRenderer();
   }

   protected ItemStackRenderer method8() {
      return this.renderer.getItemStackRenderer();
   }

   protected SlotRenderer method9() {
      return this.renderer.getSlotRenderer();
   }

   protected BoxRenderer method10() {
      return this.renderer.getBoxRenderer();
   }

   protected PanelRenderer method11() {
      return this.renderer.getPanelRenderer();
   }

   protected MouseInput method12() {
      return this.renderer.getMouseInput();
   }

   protected KeyboardInput method13() {
      return this.renderer.getKeyboardInput();
   }

   protected ScrollAnimator method14() {
      return this.renderer.getScrollAnimator();
   }

   protected String method15() {
      return this.renderer.getCurrentGuiId();
   }

   protected GuiTheme getTheme() {
      return this.renderer.getTheme();
   }

   protected MixinHelper_4 method16() {
      return this.renderer.getDrawContext();
   }

   public void start() {
   }

   public void end() {
   }
}
