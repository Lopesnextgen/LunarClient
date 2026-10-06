package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionDisplay;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public abstract class OptionWidget<T extends ClientOption<?>> extends GuiWidget {
   protected T option;
   protected String title;

   public OptionWidget(T var1, GuiWidget var2) {
      super(var2);
      this.option = (T)var1;
      this.title = var1.getName().toUpperCase().replace("", " ").trim();
   }

   public void method1(float var1, float var2, float var3) {
      super.method2(var1, var2, var3, this.getHeight());
   }

   @Override
   public abstract float getHeight();

   public boolean method3() {
      return this.getOption().method3(OptionTraits.field2).<Boolean>map(OptionDisplay::method5).orElse(false);
   }

   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      String var3 = this.option.method3();
      String var4 = var3 + "Description";
      String var5 = this.option.method1(var4, new Object[0]);
      if (!var5.equals(var4)) {
         this.method4(var1, var2, var5);
      }
   }

   public void method4(MixinHelper_4 var1, MarkerModel.Data2 var2, String var3) {
      var1.method38(0.0F, 0.0F, 100.0F);
      boolean var4 = LcuiScreen.method107();
      int[] var5 = null;
      if (var4) {
         var5 = LcuiScreen.method112(var1);
      }

      List var6 = FontRegistry.method9().method25(var3, 150.0);
      float var7 = var6.size() > 1 ? 150.0F : FontRegistry.method8().method4(var3);
      LcuiScreen.method54(
         var1, var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 8.0F, var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 6.0F, var7 + 10.0F, 8 * var6.size() + 5, 4.0F, -1275068416
      );
      int var8 = 0;

      for (String var10 : var6) {
         FontRegistry.method8()
            .method17(var1, var10, var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 12.5F, var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F + var8 * 8, -1, false);
         var8++;
      }

      if (var4) {
         LcuiScreen.method110(var1, var5);
      }

      var1.method38(0.0F, 0.0F, -100.0F);
   }

   public boolean method5(MarkerModel.Data2 var1) {
      return this.method5(var1);
   }

   @Nullable
   public OptionWidget<?> method6(MarkerModel.Data2 var1) {
      return this.method5(var1) ? this : null;
   }

   protected void method7() {
      if (!this.option.isDefault()) {
         this.option.reset();
         if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 var1) {
            var1.method2().initGui();
         }
      }
   }

   @Generated
   public T getOption() {
      return this.option;
   }
}
