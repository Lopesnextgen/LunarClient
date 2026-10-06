package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Bridge_65;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.mod.misc.panoramamaker.PanoramaMaker;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;

public class MainMenuScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private ResourceLocationBridge field19;

   @Override
   protected List<GuiWidget> method25() {
      return List.of();
   }

   @Override
   public void init() {
      DualMarkerScreenLegacy.method18().init();
      if (MainMenuThemeManager.method12() != null && MainMenuThemeManager.method12().method2() != null) {
         this.field19 = MainMenuThemeManager.method12().method1();
      } else {
         this.field19 = MainMenuHomeScreen.field22.method1();
      }
   }

   @Override
   public void update() {
      if (PanoramaMaker.field12) {
         DualMarkerScreenLegacy.method18().method5();
      }

      if (this.method6()) {
         Bridge.method14().method7();
      }

      DualMarkerScreenLegacy.method18().method4();
   }

   @Override
   public void method1(MixinHelper_4 var1, Bridge_65 var2, float var3) {
      if (ThreadModuleDump63.method8() == null) {
         if (this.method6()) {
            Bridge.method14().method6(var1, false);
         } else if (ThreadModuleDump63.MC_VERSION >= 22) {
            Bridge.method14().method6(var1, true);
         } else {
            com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, this.field19, 0.0F, 0.0F, this.width, this.height, -1);
            MainMenuBackground.method3(var1.method46().method29(), this.width, this.height, var3);
         }
      }

      if (!PanoramaMaker.field12) {
         super.method1(var1, var2, var3);
      }

      var1.method44(var0 -> var0.method29().method19());
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
   }

   public boolean method5() {
      return !PanoramaMaker.field12;
   }

   private boolean method6() {
      return MainMenuThemeManager.method12() != null && MainMenuThemeManager.method12().method5();
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
   }

   @Override
   public void method12(MarkerModel.Data2 var1, int var2) {
   }

   @Override
   public void method14(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + ".mainmenu";
   }
}
