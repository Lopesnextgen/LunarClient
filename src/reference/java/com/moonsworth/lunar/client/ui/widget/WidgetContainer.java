package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.AttachedPanel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public abstract class WidgetContainer extends com.moonsworth.lunar.client.ui.widget.SelectionContainer {
   protected List<GuiWidget> field18 = Collections.synchronizedList(this.method1());
   protected AttachedPanel field19;

   public WidgetContainer(GuiWidget var1) {
      super(var1);
      this.method4(
         (var1x, var2) -> {
            for (GuiWidget var4 : this.field18) {
               if (var4.getScaledWidth_double(var1x) && this.method6(var4, var1x) && var4.method6(var1x, var2)) {
                  LcuiScreen.method15();
                  return true;
               }
            }

            if (this.field19 != null
               && this.field19.method3(var1x)
               && this.method6(this.field19, var1x)
               && this.field19.method6(var1x, var2)) {
               LcuiScreen.method15();
               return true;
            } else {
               return true;
            }
         }
      );
      this.method3((var1x, var2) -> {
         if (this.field19 != null && !this.field19.method3(var1x)) {
            this.field19 = null;
         }

         for (GuiWidget var4 : this.field18) {
            if ((!var4.getScaledWidth_double(var1x) || !this.method6(var4, var1x)) && var4.method8(var1x, var2)) {
               return true;
            }
         }

         return false;
      });
   }

   protected abstract List<GuiWidget> method1();

   @Override
   protected void method2(MarkerModel.Data2 var1) {
      if (this.field17.get()) {
         if (!Bridge.method20().method1(0)) {
            this.field17.set(false);
            return;
         }

         float var2 = var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() - this.field16.x;
         float var3 = var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - this.field16.y;
         ThreadModuleDump71 var4 = LcuiScreen.method151();
         if (var2 >= var4.getScaledWidth_double() - this.width / 2.0F) {
            var2 = (float)var4.getScaledWidth_double() - this.width / 2.0F;
         } else if (var2 <= -this.width / 2.0F) {
            var2 = -this.width / 2.0F;
         }

         if (var3 >= var4.getScaledHeight_double() - this.height / 2.0F) {
            var3 = (float)var4.getScaledHeight_double() - this.height / 2.0F;
         } else if (var3 <= -this.height / 2.0F) {
            var3 = -this.height / 2.0F;
         }

         float var5 = this.x - var2;
         float var6 = this.y - var3;
         this.method2(var2, var3, this.width, this.height);

         for (GuiWidget var8 : this.field18) {
            var8.method2(var8.getX() - var5, var8.getY() - var6, var8.getWidth(), var8.getHeight());
         }
      }
   }

   @Override
   public void update() {
      for (GuiWidget var2 : this.field18) {
         var2.update();
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      for (GuiWidget var5 : this.field18) {
         var5.method3(var1, var2, var3 && this.method6(var5, var2));
      }

      if (this.field19 != null) {
         this.field19.method3(var1, var2, var3 && this.method6(this.field19, var2));
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      for (GuiWidget var4 : this.field18) {
         var4.method4(var1, var2);
      }
   }

   @Override
   public boolean method5(int var1) {
      if (super.method5(var1)) {
         return true;
      }

      for (GuiWidget var3 : this.field18) {
         if (var3.method5(var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void close() {
      for (GuiWidget var2 : this.field18) {
         var2.close();
      }
   }

   protected boolean method6(GuiWidget var1, MarkerModel.Data2 var2, GuiWidget... var3) {
      List var4 = Arrays.asList(var3);
      if (var1 == this.field19) {
         return true;
      }

      boolean var5 = true;

      for (int var6 = this.field18.size() - 1; var6 >= 0; var6--) {
         GuiWidget var7 = this.field18.get(var6);
         if (var7 == var1) {
            break;
         }

         if (!var4.contains(var7)) {
            if (this.field19 != null && this.field19.method3(var2)) {
               var5 = false;
               break;
            }

            if (var7.method1(var2)) {
               var5 = false;
               break;
            }
         }
      }

      return var5;
   }

   @Generated
   public List<GuiWidget> method7() {
      return this.field18;
   }

   @Generated
   public void method8(AttachedPanel var1) {
      this.field19 = var1;
   }

   @Generated
   public AttachedPanel method9() {
      return this.field19;
   }
}
