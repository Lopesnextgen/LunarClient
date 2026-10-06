package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.KeyEventBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class GuiEventDispatcher {
   private final GuiModuleManager moduleManager;
   private final List<Runnable> deferredTasks = new ArrayList<>();

   public boolean method1() {
      return this.moduleManager.isScreenActive() ? this.moduleManager.method9().method4() : false;
   }

   public boolean method2() {
      return this.moduleManager.isScreenActive() ? this.moduleManager.method9().method5() : false;
   }

   public void render(GuiScreenBridge bridge5extension61, MixinHelper_4 mixinhelper_42, int number3, int number4, float value5) {
      if (this.moduleManager.isScreenActive()) {
         this.moduleManager.method9().method5(bridge5extension61, mixinhelper_42, number3, number4, value5);
         this.deferredTasks.forEach(Runnable::run);
         this.deferredTasks.clear();
      } else {
         for (OverlayModule modulebase27 : this.moduleManager.getActiveOverlays()) {
            modulebase27.method5(bridge5extension61, mixinhelper_42, number3, number4, value5);
         }

         this.deferredTasks.forEach(Runnable::run);
         this.deferredTasks.clear();
      }
   }

   public boolean method4(GuiScreenBridge bridge5extension61, double value2) {
      if (this.moduleManager.isScreenActive()) {
         this.moduleManager.method9().method5(bridge5extension61, value2);
         return true;
      }

      for (OverlayModule modulebase25 : this.moduleManager.getActiveOverlays()) {
         if (modulebase25.method5(bridge5extension61, value2)) {
            return true;
         }
      }

      return false;
   }

   public boolean method5(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      if (this.moduleManager.isScreenActive()) {
         this.moduleManager.method9().method3(bridge5extension61, number2, number3, number4);
         return true;
      }

      for (OverlayModule modulebase26 : this.moduleManager.getActiveOverlays()) {
         if (modulebase26.method3(bridge5extension61, number2, number3, number4)) {
            return true;
         }
      }

      return false;
   }

   public boolean method6(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      if (this.moduleManager.isScreenActive()) {
         this.moduleManager.method9().method4(bridge5extension61, number2, number3, number4);
         return true;
      }

      for (OverlayModule modulebase26 : this.moduleManager.getActiveOverlays()) {
         if (modulebase26.method4(bridge5extension61, number2, number3, number4)) {
            return true;
         }
      }

      return false;
   }

   public boolean method7(GuiScreenBridge bridge5extension61, int number2, int number3, int number4, long number5) {
      if (this.moduleManager.isScreenActive()) {
         this.moduleManager.method9().method6(bridge5extension61, number2, number3, number4, number5);
         return true;
      }

      for (OverlayModule modulebase28 : this.moduleManager.getActiveOverlays()) {
         if (modulebase28.method6(bridge5extension61, number2, number3, number4, number5)) {
            return true;
         }
      }

      return false;
   }

   public boolean method8(GuiScreenBridge bridge5extension61, KeyEventBridge bridge_72) {
      if (this.moduleManager.isScreenActive()) {
         return this.moduleManager.method9().method10(bridge5extension61, bridge_72);
      }

      for (OverlayModule modulebase24 : this.moduleManager.getActiveOverlays()) {
         if (modulebase24.method10(bridge5extension61, bridge_72)) {
            return true;
         }
      }

      return false;
   }

   public void runOrDefer(Runnable runnable1) {
      if (this.isGuiActive()) {
         this.deferredTasks.add(runnable1);
      } else {
         runnable1.run();
      }
   }

   public boolean isGuiActive() {
      return this.moduleManager.isScreenActive() || !this.moduleManager.getActiveOverlays().isEmpty();
   }

   @Generated
   public GuiEventDispatcher(GuiModuleManager guirewindhandlershandler21) {
      this.moduleManager = guirewindhandlershandler21;
   }
}
