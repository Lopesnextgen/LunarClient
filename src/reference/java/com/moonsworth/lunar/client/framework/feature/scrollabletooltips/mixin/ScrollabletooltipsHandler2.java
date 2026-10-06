package com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin;

import com.moonsworth.lunar.client.cosmetics.emote.Direction2D;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips2;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent.Data;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;

public class ScrollabletooltipsHandler2 implements Scrollabletooltips {
   @Override
   public boolean method1(ScrollableTooltips var1, Scrollabletooltips2 var2) {
      return var2.method12().method9().method3();
   }

   @Override
   public void method2(Data var1, Scrollabletooltips2 var2) {
      com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips var3 = var2.method12();
      boolean var4 = var3.method9().method4() == Direction2D.UP;
      int var5 = var1.getX();
      int var6 = var1.getY();
      double var7 = var4 ? var5 + var3.method7().getValue() : var5;
      double var9 = var4 ? var6 + var3.method8().getValue() : var6;
      this.method1(var1, var2, var7, var9);
   }
}
