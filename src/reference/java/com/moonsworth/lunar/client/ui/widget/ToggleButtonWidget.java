package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.FadeAnimation;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class ToggleButtonWidget extends GuiWidget {
   private final FadeAnimation field16 = new FadeAnimation(125L);
   private final ResourceLocationBridge field17;
   private final String field18;
   private Supplier<String> field19;

   public ToggleButtonWidget(GuiWidget var1, String var2, @NotNull ResourceLocationBridge var3) {
      super(var1);
      this.field18 = var2;
      this.field17 = var3;
   }

   @Override
   public void update() {
   }

   @Override
   public void close() {
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      boolean var4 = var3 && this.method3(var2);
      float var5 = 0.65F + 0.25F * this.field16.method1(var4);
      LcuiScreen.method31(
         var1, this.field17, this.x + 1.0F, this.y + 1.0F, this.width - 2.0F, this.height - 2.0F, ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, var5)
      );
      if (var4) {
         String var6 = this.field19 != null ? this.field19.get() : this.method1(this.field18, new Object[0]);
         String[] var7 = var6.split("\n");
         float var8 = 0.0F;

         for (String var12 : var7) {
            var8 = Math.max(var8, FontRegistry.method8().method4(var12));
         }

         float var14 = var8 + 10.0F;
         float var15 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 8.0F;
         float var16 = ThreadModuleDump63.method31(ThreadModuleDump63.method3().bridge$getCurrentScreen()) instanceof LcuiScreen var17
            ? var17.method22()
            : 0.0F;
         if (var16 > 0.0F && var15 + var14 > var16) {
            var15 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() - 8.0F - var14;
         }

         LcuiScreen.method54(var1, var15, var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 6.0F, var14, 5.0F + var7.length * 9, 4.0F, -1879048192);

         for (int var18 = 0; var18 < var7.length; var18++) {
            FontRegistry.method8().method13(var1, var7[var18], var15 + 4.5F, var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F + var18 * 9, -1);
         }
      }
   }

   @Generated
   public void method3(Supplier<String> var1) {
      this.field19 = var1;
   }
}
