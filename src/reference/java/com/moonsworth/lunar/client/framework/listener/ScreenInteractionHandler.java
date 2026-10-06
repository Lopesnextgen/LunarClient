package com.moonsworth.lunar.client.framework.listener;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiIngameMenuBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.GuiMultiplayerBridge;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay;
import com.moonsworth.lunar.client.event.screen.EventScreenUpdate;
import com.moonsworth.lunar.client.event.ResultEvent;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderCrosshair;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.bridge.AccountBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class ScreenInteractionHandler implements EventBusAccess {
   private final List<KeyBindingBridge> field1 = ImmutableList.of(
      Ref.method3().bridge$getGameSettings().bridge$keyBindForward(),
      Ref.method3().bridge$getGameSettings().bridge$keyBindLeft(),
      Ref.method3().bridge$getGameSettings().bridge$keyBindRight(),
      Ref.method3().bridge$getGameSettings().bridge$keyBindBack(),
      Ref.method3().bridge$getGameSettings().bridge$keyBindSprint(),
      Ref.method3().bridge$getGameSettings().bridge$keyBindSneak(),
      Ref.method3().bridge$getGameSettings().bridge$keyBindJump()
   );
   private static long field2;
   private boolean first;
   private long field3 = 0L;
   private long field4 = 0L;

   public ScreenInteractionHandler() {
      this.handle(EventDisconnect.class, this::method1);
      this.handle(EventScreenChange.class, this::method9);
      this.handle(com.moonsworth.lunar.client.event.screen.EventScreenClose.class, this::method3);
      this.handle(EventScreenUpdate.class, this::method8);
      this.handle(EventKeybind.class, this::method7);
      this.handle(EventRenderScreenOverlay.EventScreenInitPost.class, this::method10);
      this.handle(EventScreenOpen.class, this::method5);
      this.handle(EventRenderCrosshair.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase.class, this::method11);
      this.first = true;
   }

   private void method1(EventDisconnect highlightimpl111) {
      Ref.method3().bridge$getGuiIngame().bridge$clearTitle();
   }

   private boolean method2(@NotNull LcuiScreen bridge7iterator1) {
      return bridge7iterator1.method148().stream().filter(arg0 -> arg0 instanceof EditState).anyMatch(arg0 -> ((EditState)arg0).isEditing());
   }

   private void method3(com.moonsworth.lunar.client.event.screen.EventScreenClose highlightimpl111) {
      if (highlightimpl111.method1() instanceof LcuiScreen) {
         if (this.method2((LcuiScreen)highlightimpl111.method1())) {
            highlightimpl111.setCancelled(true);
         }
      }
   }

   public static boolean method4() {
      return field2 == 0L ? false : System.nanoTime() - field2 <= TimeUnit.MILLISECONDS.toNanos(500L);
   }

   private void method5(EventScreenOpen highlightimpl91) {
      if (highlightimpl91.method1() instanceof Bridge5Extension612) {
         field2 = System.nanoTime();
      }

      if (highlightimpl91.method2() != null && highlightimpl91.method1() instanceof Bridge5Extension62 && ((Bridge5Extension62)highlightimpl91.method1()).method2().method7()) {
         Ref.method3().bridge$getGameSettings().bridge$unpressAllKeys();
      }
   }

   private void method6(EventRenderCrosshair highlightimpl221) {
      if (!Ref.method4().method40().method85().method19()
         && Ref.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62
         && !Client.method109().method40().method33().isEnabled()) {
         highlightimpl221.method2(ResultEvent.Outcome.DENY);
      }

      if (FramebufferCaptureTask.method6()) {
         highlightimpl221.method2(ResultEvent.Outcome.DENY);
      }
   }

   private void method7(EventKeybind highlightimpl1) {
      if (Ref.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62
         && ((Bridge5Extension62)Ref.method3().bridge$getCurrentScreen()).method2().method7()) {
         for (KeyBindingBridge mixinhelper_153 : this.field1) {
            if (highlightimpl1.method10() == mixinhelper_153.bridge$getKey()) {
               boolean flag4 = Bridge.method18().method1(highlightimpl1.method10());
               if (!flag4
                  && highlightimpl1.method10() == Ref.method3().bridge$getGameSettings().bridge$keyBindSprint().bridge$getKey()
                  && Ref.method7() != null) {
                  Ref.method7().bridge$setSprinting(false);
               }

               mixinhelper_153.bridge$setKeyBindState(flag4);
            }
         }
      } else {
         if ((
               Ref.method3().bridge$getCurrentScreen() == null
                  || Ref.method3().bridge$getCurrentScreen().method1(com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen.class)
            )
            && highlightimpl1.method11() == InputAction.DOWN
            && highlightimpl1.method1(Ref.method4().method41().method8().method15())
            && !method4()) {
            Ref.method3()
               .bridge$displayScreen(Bridge.method8().method18(new com.moonsworth.lunar.client.ui.hud.HudEditorScreen()));
         }
      }
   }

   private void method8(EventScreenUpdate highlightimpl161) {
      if (highlightimpl161.method1() instanceof Bridge5Extension62 && ((Bridge5Extension62)highlightimpl161.method1()).method2().method7()) {
         for (KeyBindingBridge mixinhelper_153 : this.field1) {
            if (Bridge.method18().method1(mixinhelper_153.bridge$getKey())) {
               Ref.method3().bridge$getGameSettings().bridge$setKeyBindState(mixinhelper_153.bridge$getKey(), true);
            }
         }
      }
   }

   private void method9(EventScreenChange highlightimpl71) {
      if (highlightimpl71.method1() == null) {
         if (Ref.method8() == null) {
            highlightimpl71.setCancelled(true);
            Client.method109().method23();
         } else if (DriverViewportLegacy.method50().method61() != DriverRouteRegistry.field10) {
            DriverViewportLegacy.method50().method16(DriverRouteRegistry.field8);
         }
      } else if (highlightimpl71.method1() instanceof GuiMultiplayerBridge && !Ref.method4().method43().method23()) {
         highlightimpl71.setCancelled(true);
         DriverViewportLegacy.method50().method18(DriverRouteRegistry.field4, true);
         AccountBridge.method2();
      }
   }

   private void method10(EventRenderScreenOverlay.EventScreenInitPost data51) {
      if (data51.IOIHCOOOCHCIROIHIHCIHCCRROOHOC().method1(GuiIngameMenuBridge.class)) {
         if (this.first && Ref.method3().bridge$getGameSettings().bridge$getChatScale() == 0.0F) {
            Ref.method3().bridge$getGameSettings().bridge$setOptionFloatValue(25, 1.0F);
         }

         this.first = false;
      }
   }

   private void method11(com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase highlightimpl1) {
      ExternalLinkRegistry.method2(RecordingExternalLink.class).ifPresent(arg1x -> {
         if (arg1x.method1() && arg1x.method4()) {
            if (Ref.method3().bridge$getCurrentScreen() != null && System.currentTimeMillis() - this.field3 >= 50L) {
               this.field3 = System.currentTimeMillis();
               Ref.method3().bridge$getCurrentScreen().bridge$updateScreen();
            }

            if (System.currentTimeMillis() - this.field4 >= 1000L) {
               this.field4 = System.currentTimeMillis();
               LunarEventBus.method29().method12(EventSecond.class, EventSecond::new);
            }
         }
      });
   }
}
