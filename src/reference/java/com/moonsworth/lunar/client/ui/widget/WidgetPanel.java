package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.AttachedPanel;
import com.moonsworth.lunar.client.ui.widget.ListOptionWidget;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public abstract class WidgetPanel<T extends GuiWidget> extends GuiWidget implements EditState {
   protected List<T> field16 = Collections.synchronizedList(this.method5());
   private boolean field17;
   protected AttachedPanel field18;

   public WidgetPanel(GuiWidget var1) {
      super(var1);
      this.method17((var1x, var2) -> {
         if (this.method1(var1x, var2)) {
            return true;
         }

         for (GuiWidget var4 : this.field16) {
            if (var4.method1(var1x) && this.method9(var4, var1x) && var4.method7(var1x, var2)) {
               LcuiScreen.method15();
               return true;
            }
         }

         return false;
      });
      this.method4((var1x, var2) -> {
         if (this.method2(var1x, var2)) {
            return true;
         }

         for (GuiWidget var4 : this.field16) {
            if (var4.method1(var1x) && this.method9(var4, var1x) && var4.method6(var1x, var2)) {
               LcuiScreen.method15();
               return true;
            }
         }

         return false;
      });
      this.method3((var1x, var2) -> {
         if (this.method3(var1x, var2)) {
            return true;
         }

         for (GuiWidget var4 : this.field16) {
            if (!var4.method1(var1x) && var4.method8(var1x, var2)) {
               return true;
            }
         }

         return false;
      });
   }

   protected boolean method1(MarkerModel.Data2 var1, int var2) {
      if (this.field18 != null && this.field18.method3(var1) && this.method9(this.field18, var1) && this.field18.method7(var1, var2)) {
         LcuiScreen.method15();
         if (this.field17) {
            this.field18 = null;
         }

         return true;
      } else {
         return false;
      }
   }

   protected boolean method2(MarkerModel.Data2 var1, int var2) {
      if (this.field18 != null && this.field18.method3(var1) && this.method9(this.field18, var1) && this.field18.method6(var1, var2)) {
         LcuiScreen.method15();
         if (this.field17) {
            this.field18 = null;
         }

         return true;
      } else {
         return false;
      }
   }

   protected boolean method3(MarkerModel.Data2 var1, int var2) {
      if (this.field18 != null) {
         if (!this.method3(var1) && this.field18.method3(var1) && this.method9(this.field18, var1) && this.field18.method6(var1, var2)) {
            LcuiScreen.method15();
            if (this.field17) {
               this.field18 = null;
            }

            return true;
         }

         if (!this.field18.method3(var1)) {
            this.field18 = null;
            this.field17 = true;
         }
      }

      return false;
   }

   public void method4(AttachedPanel var1) {
      this.field18 = var1;
   }

   protected abstract List<T> method5();

   @Override
   public void update() {
      for (GuiWidget var2 : this.field16) {
         if (var2.method24()) {
            var2.update();
         }
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      for (GuiWidget var5 : this.field16) {
         if (var5.method24()) {
            var5.method3(var1, var2, var3 && this.method9(var5, var2));
         }
      }

      if (this.field18 != null) {
         this.field18.method3(var1, var2, var3 && this.method9(this.field18, var2));
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      for (GuiWidget var4 : this.field16) {
         if (var4.method24()) {
            var4.method4(var1, var2);
         }
      }
   }

   @Override
   public boolean method5(int var1) {
      if (super.method5(var1)) {
         return true;
      }

      for (GuiWidget var3 : this.field16) {
         if (var3.method24() && var3.method5(var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void close() {
      for (GuiWidget var2 : this.field16) {
         if (var2.method24()) {
            var2.close();
         }
      }
   }

   protected boolean method9(GuiWidget var1, MarkerModel.Data2 var2, GuiWidget... var3) {
      List var4 = Arrays.asList(var3);
      if (var1 == this.field18) {
         return true;
      }

      boolean var5 = true;

      for (int var6 = this.field16.size() - 1; var6 >= 0; var6--) {
         GuiWidget var7 = this.field16.get(var6);
         if (var7 == var1) {
            break;
         }

         if (!var4.contains(var7)) {
            if (this.field18 != null && this.field18.method3(var2)) {
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

   @Override
   public boolean isEditing() {
      return this.field16.stream().filter(var0 -> var0 instanceof EditState).anyMatch(var0 -> ((EditState)var0).isEditing());
   }

   public float method10() {
      float var1 = 0.0F;
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 var2) {
         if (var2.method2() instanceof LcuiScreen var6) {
            var1 = var6.method23();
         } else if (var2.method2() instanceof MainMenuButton var4) {
            var1 = var4.method13();
         } else {
            var1 = var2.method2().getHeight();
         }
      }

      return var1;
   }

   public <C> List<C> method11(Class<C> var1) {
      return this.method12(this.field16, var1);
   }

   private <C> List<C> method12(List<? extends GuiWidget> var1, Class<C> var2) {
      ArrayList var3 = new ArrayList();

      for (GuiWidget var5 : var1) {
         if (var5.getClass().equals(var2)) {
            var3.add(var5);
         }

         if (var5 instanceof ListOptionWidget var6) {
            var3.addAll(this.method12(var6.method1(), var2));
         }
      }

      return var3;
   }

   @Generated
   public List<T> method14() {
      return this.field16;
   }

   @Generated
   public void method14(List<T> var1) {
      this.field16 = var1;
   }

   @Generated
   public void method15(boolean var1) {
      this.field17 = var1;
   }

   @Generated
   public AttachedPanel method16() {
      return this.field18;
   }
}
