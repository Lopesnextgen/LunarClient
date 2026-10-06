package com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin;

import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips2;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent.Data;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;

public class ScrollabletooltipsHandler3 implements Scrollabletooltips {
   @Override
   public boolean method1(ScrollableTooltips var1, Scrollabletooltips2 var2) {
      return !var2.method16() || !(Boolean)var2.method10().get();
   }

   @Override
   public void method2(Data var1, Scrollabletooltips2 var2) {
      com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips var3 = var2.method12();
      double var4 = var1.getX() + var3.method7().getValue();
      double var6 = var1.getY() + var3.method8().getValue();
      this.method1(var1, var2, var4, var6);
   }
}
