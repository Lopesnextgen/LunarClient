package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler3;
import com.moonsworth.lunar.client.event.screen.ScreenUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   public static final GuiRewindhandlersHandler2 field7 = (GuiRewindhandlersHandler2)HICCRORORCRIHCORCCIORIOROORIHR(GuiRewindhandlersHandler2.class);
   private final Module2 dispatcher = new Module2(this);
   private final List<ModuleBase> modules = new ArrayList<>();
   private final List<ModuleBase2> overlays = new ArrayList<>();
   private final List<ModuleBase2> activeOverlays = new ArrayList<>();
   private ModuleBase activeModule = null;

   public GuiRewindhandlersHandler2() {
      this.handle(EventClientTick.class, this::method9);
      this.handle(ScreenUpdateEvent.class, this::method8);
   }

   public static boolean isMouseInputConsumed() {
      return field7.method11().method1();
   }

   public void registerModule(final ModuleBase var1, Framework10Extension var2) {
      var2.method2(new GuiRewindhandlersHandler3() {
         protected void onEnable() {
            GuiRewindhandlersHandler2.this.modules.add(var1);
         }

         protected void onDisable() {
            GuiRewindhandlersHandler2.this.modules.remove(var1);
         }
      });
   }

   public void registerOverlay(final ModuleBase2 var1, Framework10Extension var2) {
      var2.method2(new GuiRewindhandlersHandler3() {
         protected void onEnable() {
            GuiRewindhandlersHandler2.this.overlays.add(var1);
         }

         protected void onDisable() {
            GuiRewindhandlersHandler2.this.overlays.remove(var1);
         }
      });
   }

   private void updateActiveModule() {
      Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (this.activeModule != null) {
         if (var1 == null) {
            this.activeModule.onClose();
            this.activeModule = null;
         } else {
            boolean var2 = this.activeModule.method2(var1);
            if (!var2) {
               this.activeModule.onClose();
               this.activeModule = null;
            }
         }
      }

      if (this.activeModule == null) {
         if (var1 == null) {
            return;
         }

         for (ModuleBase var3 : this.modules) {
            if (var3.method2(var1)) {
               this.activeModule = var3;
               this.activeModule.onOpen();
               return;
            }
         }
      }
   }

   public boolean isScreenActive() {
      return this.activeModule != null && ThreadModuleDump63.method3().bridge$getCurrentScreen() != null;
   }

   public boolean method8() {
      return !this.isScreenActive() ? true : this.activeModule.method3();
   }

   public ModuleBase method9() {
      return this.activeModule;
   }

   private void method8(ScreenUpdateEvent var1) {
      this.updateActiveModule();
      this.updateActiveOverlays();
   }

   private void method9(EventClientTick var1) {
      this.updateActiveModule();
      this.updateActiveOverlays();

      for (ModuleBase var3 : this.modules) {
         var3.method8();
      }

      for (ModuleBase2 var6 : this.overlays) {
         var6.method8();
      }

      for (ModuleBase2 var7 : this.activeOverlays) {
         var7.method7();
      }

      if (this.isScreenActive()) {
         this.method9().method7();
      }
   }

   private void updateActiveOverlays() {
      HashSet var1 = new HashSet<>(this.activeOverlays);
      HashSet var2 = new HashSet();
      Bridge5Extension6 var3 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (var3 != null && !this.isScreenActive()) {
         for (ModuleBase2 var5 : this.overlays) {
            if (var5.method1(var3)) {
               var2.add(var5);
            }
         }
      }

      HashSet var6 = new HashSet(var2);
      var6.removeAll(var1);
      var6.forEach(Module_2::method9);
      this.activeOverlays.addAll(var6);
      HashSet var7 = new HashSet(var1);
      var7.removeAll(var2);
      var7.forEach(Module_2::onClose);
      this.activeOverlays.removeAll(var7);
   }

   public Module2 method11() {
      return this.dispatcher;
   }

   Collection<ModuleBase2> getActiveOverlays() {
      return this.activeOverlays;
   }

   public boolean isOverlayActive(ModuleBase2 var1) {
      return this.getActiveOverlays().contains(var1);
   }
}
