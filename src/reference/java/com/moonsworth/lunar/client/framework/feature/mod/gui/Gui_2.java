package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_2;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Gui_2 extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private final Fishing_2 field19;
   private final ResourceLocationBridge field20;

   public Gui_2(Bridge7_8 var1, Fishing_2 var2, @NotNull ResourceLocationBridge var3) {
      this.field19 = var2;
      this.field20 = var3;
      this.field11.add(new Calculator2Updater2(var1, null, null));
      if (!(var1 instanceof Gui3)) {
         this.field11.add(new Calculator2Updater2(var1, null, this.field19));
      }

      for (Fishing_2 var5 : this.field19.method5()) {
         this.field11.add(new Calculator2Updater2(this, this.field19, var5));
      }
   }

   protected List<GuiWidget> method25() {
      return new ArrayList<>();
   }

   public void init() {
      byte var1 = 4;
      float var2 = 100.0F;
      float var3 = 16.0F;
      float var4 = 5.0F;
      float var5 = this.method22() / 2.0F - (var2 + var4) * var1 / 2.0F;
      float var6 = this.method23() / 2.0F - 60.0F;
      int var7 = 0;

      for (GuiWidget var9 : this.field11) {
         if (var9 instanceof Calculator2Updater2) {
            var9.method2(var5 + var7 * (var2 + var4), var6, var2, var3);
            if (var7 == var1 - 1) {
               var7 = 0;
               var6 += var3 + var4;
            } else {
               var7++;
            }
         }
      }
   }

   public void update() {
   }

   public void method10(MixinHelper_4 var1, Data2 var2) {
      float var3 = this.method22() / 2.0F;
      float var4 = this.method23() / 2.0F - 100.0F;
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, this.field20, var3 - 16.0F, var4 - 46.0F, 32.0F, 32.0F, -1);
      FontRegistry.method22().method15(var1, this.field19.getPrettyName(), var3, var4 - 10.0F, -1);
      FontRegistry.method16().method15(var1, this.method1("chooseCommand", new Object[0]), var3, var4 + 15.0F, -1);
      FontRegistry.method17().method15(var1, this.method1("keyBindHint", new Object[0]), var3, var4 + 25.0F, -536870913);
   }

   public void method11(Data2 var1, int var2) {
   }

   public void method12(Data2 var1, int var2) {
   }

   public void method14(char var1, KeyCode var2) {
   }

   public void close() {
   }

   public String getLanguagePath() {
      return super.getLanguagePath() + ".skyBlockCommands";
   }
}
