package com.moonsworth.lunar.client.framework.feature.quickplay.mixin;

import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Bridge7Iterator3 extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private final Quickplay field19;
   private final ResourceLocationBridge field20;

   public Bridge7Iterator3(Bridge7_8 var1, Quickplay var2, @NotNull ResourceLocationBridge var3) {
      this.field19 = var2;
      this.field20 = var3;
      this.field11
         .add(new Calculator2Updater(var1, null, null, "", "< " + this.method1("returnMenu", new Object[0])));
      if (!(var1 instanceof Bridge7Iterator3)) {
         this.field11
            .add(new Calculator2Updater(var1, null, this.field19, "/l " + var2.getKey(), this.method1("lobby", new Object[0])));
      }

      for (Quickplay var5 : this.field19.method4()) {
         if (!var5.isDisabled()) {
            this.field11.add(new Calculator2Updater(this, this.field19, var5, var5.method2(), var5.getName()));
         }
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
         if (var9 instanceof Calculator2Updater) {
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
      FontRegistry.method22().method15(var1, this.field19.getName(), var3, var4 - 10.0F, -1);
      FontRegistry.method16().method15(var1, this.method1("chooseGame", new Object[0]), var3, var4 + 15.0F, -1);
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
      return super.getLanguagePath() + ".quickPlay";
   }
}
