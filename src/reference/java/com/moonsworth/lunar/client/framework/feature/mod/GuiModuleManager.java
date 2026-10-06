package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.listener.RefCountedListener;
import com.moonsworth.lunar.client.event.screen.EventScreenUpdate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class GuiModuleManager extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   public static final GuiModuleManager field7 = (GuiModuleManager)HICCRORORCRIHCORCCIORIOROORIHR(GuiModuleManager.class);
   private final GuiEventDispatcher dispatcher = new GuiEventDispatcher(this);
   private final List<ModuleBase> modules = new ArrayList<>();
   private final List<OverlayModule> overlays = new ArrayList<>();
   private final List<OverlayModule> activeOverlays = new ArrayList<>();
   private ModuleBase activeModule = null;

   public GuiModuleManager() {
      this.handle(EventTick.class, this::method9);
      this.handle(EventScreenUpdate.class, this::method8);
   }

   public static boolean isMouseInputConsumed() {
      return field7.method11().method1();
   }

   public void registerModule(final ModuleBase modulebase1, ModLifecycle framework10extension2) {
      framework10extension2.method2(new RefCountedListener() {
         protected void onEnable() {
            GuiModuleManager.this.modules.add(modulebase1);
         }

         protected void onDisable() {
            GuiModuleManager.this.modules.remove(modulebase1);
         }
      });
   }

   public void registerOverlay(final OverlayModule modulebase21, ModLifecycle framework10extension2) {
      framework10extension2.method2(new RefCountedListener() {
         protected void onEnable() {
            GuiModuleManager.this.overlays.add(modulebase21);
         }

         protected void onDisable() {
            GuiModuleManager.this.overlays.remove(modulebase21);
         }
      });
   }

   private void updateActiveModule() {
      GuiScreenBridge bridge5extension61 = Ref.method3().bridge$getCurrentScreen();
      if (this.activeModule != null) {
         if (bridge5extension61 == null) {
            this.activeModule.onClose();
            this.activeModule = null;
         } else {
            boolean flag2 = this.activeModule.method2(bridge5extension61);
            if (!flag2) {
               this.activeModule.onClose();
               this.activeModule = null;
            }
         }
      }

      if (this.activeModule == null) {
         if (bridge5extension61 == null) {
            return;
         }

         for (ModuleBase modulebase3 : this.modules) {
            if (modulebase3.method2(bridge5extension61)) {
               this.activeModule = modulebase3;
               this.activeModule.onOpen();
               return;
            }
         }
      }
   }

   public boolean isScreenActive() {
      return this.activeModule != null && Ref.method3().bridge$getCurrentScreen() != null;
   }

   public boolean method8() {
      return !this.isScreenActive() ? true : this.activeModule.method3();
   }

   public ModuleBase method9() {
      return this.activeModule;
   }

   private void method8(EventScreenUpdate highlightimpl161) {
      this.updateActiveModule();
      this.updateActiveOverlays();
   }

   private void method9(EventTick highlightimpl21) {
      this.updateActiveModule();
      this.updateActiveOverlays();

      for (ModuleBase modulebase3 : this.modules) {
         modulebase3.method8();
      }

      for (OverlayModule modulebase26 : this.overlays) {
         modulebase26.method8();
      }

      for (OverlayModule modulebase27 : this.activeOverlays) {
         modulebase27.method7();
      }

      if (this.isScreenActive()) {
         this.method9().method7();
      }
   }

   private void updateActiveOverlays() {
      HashSet set1 = new HashSet<>(this.activeOverlays);
      HashSet set2 = new HashSet();
      GuiScreenBridge bridge5extension63 = Ref.method3().bridge$getCurrentScreen();
      if (bridge5extension63 != null && !this.isScreenActive()) {
         for (OverlayModule modulebase25 : this.overlays) {
            if (modulebase25.RHIRRICCRHHHIIHHIHHOHRCHIOORCC(bridge5extension63)) {
               set2.add(modulebase25);
            }
         }
      }

      HashSet set6 = new HashSet(set2);
      set6.removeAll(set1);
      set6.forEach(GuiModule::onOpen);
      this.activeOverlays.addAll(set6);
      HashSet set7 = new HashSet(set1);
      set7.removeAll(set2);
      set7.forEach(GuiModule::onClose);
      this.activeOverlays.removeAll(set7);
   }

   public GuiEventDispatcher method11() {
      return this.dispatcher;
   }

   Collection<OverlayModule> getActiveOverlays() {
      return this.activeOverlays;
   }

   public boolean isOverlayActive(OverlayModule modulebase21) {
      return this.getActiveOverlays().contains(modulebase21);
   }
}
