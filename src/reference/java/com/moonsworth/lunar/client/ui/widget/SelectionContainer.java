package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump60;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SelectionContainer extends GuiWidget {
   protected ThreadModuleDump60 field16 = new ThreadModuleDump60();
   protected AtomicBoolean field17 = new AtomicBoolean();

   public SelectionContainer(GuiWidget var1) {
      super(var1);
   }

   protected void method2(MarkerModel.Data2 var1) {
      if (this.field17.get()) {
         if (!Bridge.method20().method1(0)) {
            this.field17.set(false);
            return;
         }

         double var2 = var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() - this.field16.x;
         double var4 = var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - this.field16.y;
         ThreadModuleDump71 var6 = LcuiScreen.method151();
         if (var2 >= var6.getScaledWidth_double() - this.width / 2.0F) {
            var2 = (float)var6.getScaledWidth_double() - this.width / 2.0F;
         } else if (var2 <= -this.width / 2.0F) {
            var2 = -this.width / 2.0F;
         }

         if (var4 >= var6.getScaledHeight_double() - this.height / 2.0F) {
            var4 = (float)var6.getScaledHeight_double() - this.height / 2.0F;
         } else if (var4 <= -this.height / 2.0F) {
            var4 = -this.height / 2.0F;
         }

         this.method2((float)var2, (float)var4, this.width, this.height);
      }
   }

   protected void method3(MarkerModel.Data2 var1) {
      this.field16.set(var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() - this.x, var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - this.y);
      this.field17.set(true);
   }

   protected void method3() {
      if (this.field17.get()) {
         this.field17.set(false);
      }
   }
}
