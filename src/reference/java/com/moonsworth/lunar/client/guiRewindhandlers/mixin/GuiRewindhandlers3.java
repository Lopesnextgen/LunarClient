package com.moonsworth.lunar.client.guiRewindhandlers.mixin;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension611;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension65;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.highlight.Fishing2Extension;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.render.ScreenInitEvent;
import com.moonsworth.lunar.client.event.screen.ScreenUpdateEvent;
import com.moonsworth.lunar.client.event.OutcomeEvent;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.CrosshairRenderEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.AccountBridgeLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class GuiRewindhandlers3 implements EventRegistrar {
   private final List<MixinHelper_15> field1 = ImmutableList.of(
      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindForward(),
      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindLeft(),
      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindRight(),
      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindBack(),
      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindSprint(),
      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindSneak(),
      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindJump()
   );
   private static long field2;
   private boolean first;
   private long field3 = 0L;
   private long field4 = 0L;

   public GuiRewindhandlers3() {
      this.handle(DisconnectEvent.class, this::method1);
      this.handle(ScreenChangeEvent.class, this::method9);
      this.handle(com.moonsworth.lunar.client.event.screen.ScreenCloseEvent.class, this::method3);
      this.handle(ScreenUpdateEvent.class, this::method8);
      this.handle(KeybindEvent.class, this::method7);
      this.handle(ScreenInitEvent.ScreenInitPostEvent.class, this::method10);
      this.handle(ScreenOpenEvent.class, this::method5);
      this.handle(CrosshairRenderEvent.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent.class, this::method11);
      this.first = true;
   }

   private void method1(DisconnectEvent var1) {
      ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$clearTitle();
   }

   private boolean method2(@NotNull LcuiScreen var1) {
      return var1.method148().stream().filter(var0 -> var0 instanceof EditState).anyMatch(var0 -> ((EditState)var0).isEditing());
   }

   private void method3(com.moonsworth.lunar.client.event.screen.ScreenCloseEvent var1) {
      if (var1.method1() instanceof LcuiScreen) {
         if (this.method2((LcuiScreen)var1.method1())) {
            var1.setCancelled(true);
         }
      }
   }

   public static boolean method4() {
      return field2 == 0L ? false : System.nanoTime() - field2 <= TimeUnit.MILLISECONDS.toNanos(500L);
   }

   private void method5(ScreenOpenEvent var1) {
      if (var1.method1() instanceof Bridge5Extension612) {
         field2 = System.nanoTime();
      }

      if (var1.method2() != null && var1.method1() instanceof Bridge5Extension62 && ((Bridge5Extension62)var1.method1()).method2().method7()) {
         ThreadModuleDump63.method3().bridge$getGameSettings().bridge$unpressAllKeys();
      }
   }

   private void method6(CrosshairRenderEvent var1) {
      if (!ThreadModuleDump63.method4().method40().method85().method19()
         && ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62
         && !Client.method109().method40().method33().isEnabled()) {
         var1.method2(OutcomeEvent.Type.DENY);
      }

      if (FramebufferCaptureTask.method6()) {
         var1.method2(OutcomeEvent.Type.DENY);
      }
   }

   private void method7(KeybindEvent var1) {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62
         && ((Bridge5Extension62)ThreadModuleDump63.method3().bridge$getCurrentScreen()).method2().method7()) {
         for (MixinHelper_15 var3 : this.field1) {
            if (var1.method10() == var3.bridge$getKey()) {
               boolean var4 = Bridge.method18().method1(var1.method10());
               if (!var4
                  && var1.method10() == ThreadModuleDump63.method3().bridge$getGameSettings().bridge$keyBindSprint().bridge$getKey()
                  && ThreadModuleDump63.method7() != null) {
                  ThreadModuleDump63.method7().bridge$setSprinting(false);
               }

               var3.bridge$setKeyBindState(var4);
            }
         }
      } else {
         if ((
               ThreadModuleDump63.method3().bridge$getCurrentScreen() == null
                  || ThreadModuleDump63.method3().bridge$getCurrentScreen().method1(com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen.class)
            )
            && var1.method11() == InputActionLegacy.DOWN
            && var1.method1(ThreadModuleDump63.method4().method41().method8().method15())
            && !method4()) {
            ThreadModuleDump63.method3()
               .bridge$displayScreen(Bridge.method8().method18(new com.moonsworth.lunar.client.ui.hud.HudEditorScreen()));
         }
      }
   }

   private void method8(ScreenUpdateEvent var1) {
      if (var1.method1() instanceof Bridge5Extension62 && ((Bridge5Extension62)var1.method1()).method2().method7()) {
         for (MixinHelper_15 var3 : this.field1) {
            if (Bridge.method18().method1(var3.bridge$getKey())) {
               ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setKeyBindState(var3.bridge$getKey(), true);
            }
         }
      }
   }

   private void method9(ScreenChangeEvent var1) {
      if (var1.method1() == null) {
         if (ThreadModuleDump63.method8() == null) {
            var1.setCancelled(true);
            Client.method109().method23();
         } else if (DriverViewportLegacy.method50().method61() != DriverRouteRegistryLegacy.field10) {
            DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field8);
         }
      } else if (var1.method1() instanceof Bridge5Extension65 && !ThreadModuleDump63.method4().method43().method23()) {
         var1.setCancelled(true);
         DriverViewportLegacy.method50().method18(DriverRouteRegistryLegacy.field4, true);
         AccountBridgeLegacy.method2();
      }
   }

   private void method10(ScreenInitEvent.ScreenInitPostEvent var1) {
      if (var1.method1().method1(Bridge5Extension611.class)) {
         if (this.first && ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getChatScale() == 0.0F) {
            ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setOptionFloatValue(25, 1.0F);
         }

         this.first = false;
      }
   }

   private void method11(com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent var1) {
      Fishing.method2(Fishing2Extension.class).ifPresent(var1x -> {
         if (var1x.method1() && var1x.method4()) {
            if (ThreadModuleDump63.method3().bridge$getCurrentScreen() != null && System.currentTimeMillis() - this.field3 >= 50L) {
               this.field3 = System.currentTimeMillis();
               ThreadModuleDump63.method3().bridge$getCurrentScreen().bridge$updateScreen();
            }

            if (System.currentTimeMillis() - this.field4 >= 1000L) {
               this.field4 = System.currentTimeMillis();
               ClientEventBus.method29().method12(EventEverySecond.class, EventEverySecond::new);
            }
         }
      });
   }
}
