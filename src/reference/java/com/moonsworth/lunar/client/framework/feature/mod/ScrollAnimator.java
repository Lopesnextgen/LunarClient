package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.util.math.IntRectangle;
import com.moonsworth.lunar.client.util.math.WeightedQuadtree.Data;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public class ScrollAnimator extends GuiComponent {
   private Data field2;

   public ScrollAnimator(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   @Override
   public void start() {
      this.field2 = new Data(0.0F);
   }

   public void method1(IntRectangle threadmoduledump701, float value) {
      this.method16().method44(arg3 -> {
         float value4 = this.field2.method2(threadmoduledump701);
         this.field2.method1(threadmoduledump701, value4 + value);
      });
   }

   public void method2(IntRectangle threadmoduledump701, Runnable runnable2) {
      this.method16().method45(arg1x -> runnable2.run());
      this.method16().method44(arg3 -> {
         float value4 = this.field2.method2(threadmoduledump701);
         arg3.method38(0.0F, 0.0F, value4);
         runnable2.run();
         arg3.method29().method5(arg0 -> arg0.method48());
         arg3.method38(0.0F, 0.0F, -value4);
      });
   }
}
