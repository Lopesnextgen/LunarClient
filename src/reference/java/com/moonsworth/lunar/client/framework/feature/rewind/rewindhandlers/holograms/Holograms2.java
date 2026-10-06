package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind3_3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.colorsaturation.Colorsaturation2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.IOException;
import java.util.Map.Entry;

public class Holograms2 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   private boolean wasInputFocused;
   private static boolean settingsVisible;
   private static boolean inputFocused;

   public Holograms2() {
      reset();
      setDragging(false);
      field14 = false;
      field15 = false;
   }

   public static void reset() {
      method2(null);
      method2(null);
      setMultiSelectEnabled(false);
      setCopiedLayer(null);
      updatePreviewWindow(null);
      method2(null);
      Highlight_3 var0 = setInputFocused();
      if (var0 != null) {
         refreshTimeline();
         refreshProperties();
      }
   }

   public GuiIterator getProvider() {
      return !setRuleOfThirdsOverlay().method19() ? null : setQuartersOverlay().method28();
   }

   public void updatePreviewWindow(double var1, double var3) {
      if (com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown() && !setQuartersOverlay().method62()) {
         if (var3 < 0.0) {
            DriverViewportLegacy.method50().method15("rewind-scroll", true);
         } else {
            DriverViewportLegacy.method50().method15("rewind-scroll", false);
         }
      }
   }

   public void method2(KeyCode var1, int var2, int var3, int var4, int var5) {
      if (!field14 && !field15) {
         RewindHandlers var6 = setQuartersOverlay();
         if (var6 != null && !var6.method57().method25()) {
            if (var4 != 1 && var4 != 2) {
               if (!this.wasInputFocused && var4 == 0 && var1 == KeyCode.KEY_ESCAPE && var6.method40().method31()) {
                  setQuartersOverlay().method24();
               }

               this.wasInputFocused = setQuartersOverlay().method62();
            } else {
               Highlight_3 var7 = setInputFocused();
               if (var7 != null) {
                  Nameplate2 var8 = var6.method40().method40();
                  Gui_2 var9 = getSelectedLayer() == null ? null : findTrackOfLayer(getSelectedLayer().getId());
                  if (var9 == null) {
                     method2(null);
                  }

                  Entry var10 = var9 != null && getSelectedLayer() != null ? var7.method1().get(getSelectedLayer().getId()) : null;
                  if (var10 == null) {
                     method2(null);
                  }

                  this.wasInputFocused = var6.method62();
                  if (!var6.method62() || var1 == KeyCode.KEY_ESCAPE) {
                     var6.method56()
                        .method3(
                           new Rewind2(
                              var1,
                              com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown(),
                              com.moonsworth.lunar.client.ui.LcuiScreen.isShiftKeyDown(),
                              com.moonsworth.lunar.client.ui.LcuiScreen.method124()
                           ),
                           new Rewind3_3(var6, var7, var8, var9, var10),
                           var4 == 2
                        );
                  }
               }
            }
         }
      }
   }

   @CallbackJS("updatePreviewWindow")
   public static void updatePreviewWindow(Float var0, Float var1) {
      RewindHandlers var2 = setQuartersOverlay();
      if (var2 != null) {
         var2.method20(var0, var1);
      }
   }

   @CallbackJS("updateEditorLayout")
   public static void updateEditorLayout(Float var0, Float var1) {
      LoadingStageImpl var2 = ThreadModuleDump63.method4().method90();
      if (var0 != null) {
         var2.method16().method1(var0);
      }

      if (var1 != null) {
         var2.method17().method1(var1);
      }
   }

   @CallbackJS("exit")
   public static void exit() {
      setQuartersOverlay().method24();
   }

   @CallbackJS("pause")
   public static void pause(boolean var0) {
      RewindHandlers var1 = setQuartersOverlay();
      if (!var1.method57().method25()) {
         Highlight_3 var2 = setInputFocused();
         if (var2 != null) {
            if (!var0) {
               method2(null);
               refreshTimeline();
               if (setQuartersOverlay().method25()) {
                  var2.method8(0);
               }
            }

            var2.setPaused(var0);
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("save")
   public static void save() {
      try {
         showSettings().method17(false);
         ThreadModuleDump63.method4().method69().method6(NotificationType.SUCCESS, "Rewind", "Project saved!").method9(NotificationAnchor.BOTTOM_RIGHT);
      } catch (IOException var1) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while saving replay: " + var1.getMessage())
            .method9(NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(var1);
      }
   }

   @CallbackJS("undo")
   public static void undo() {
      RewindHandlers var0 = setQuartersOverlay();
      if (!var0.method57().method25()) {
         setQuartersOverlay().method40().method40().undo();
         setQuartersOverlay().method56(true);
         setQuartersOverlay().method27();
         Colorsaturation2.beginUndoBatch();
         Colorsaturation2.endUndoBatch();
      }
   }

   @CallbackJS("redo")
   public static void redo() {
      RewindHandlers var0 = setQuartersOverlay();
      if (!var0.method57().method25()) {
         setQuartersOverlay().method40().method40().redo();
         setQuartersOverlay().method56(true);
         setQuartersOverlay().method27();
         Colorsaturation2.beginUndoBatch();
         Colorsaturation2.endUndoBatch();
      }
   }

   @CallbackJS("forward")
   public static void forward() {
      RewindHandlers var0 = setQuartersOverlay();
      if (!var0.method57().method25()) {
         Highlight_3 var1 = setInputFocused();
         if (var1 != null) {
            var1.method8(var1.method15() + 1);
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("backward")
   public static void backward() {
      RewindHandlers var0 = setQuartersOverlay();
      if (!var0.method57().method25()) {
         Highlight_3 var1 = setInputFocused();
         if (var1 != null) {
            var1.method8(Math.max(0, var1.method15() - 1));
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("goStart")
   public static void goStart() {
      RewindHandlers var0 = setQuartersOverlay();
      if (!var0.method57().method25()) {
         Highlight_3 var1 = setInputFocused();
         if (var1 != null) {
            var1.method8(0);
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("reload")
   public static void reload() {
      RewindHandlers var0 = setQuartersOverlay();
      if (!var0.method57().method25()) {
         ThreadModuleDump63.method3().bridge$schedule(() -> {
            try {
               var0.method3(var0.method41().method9());
            } catch (IOException var2) {
               throw new RuntimeException(var2);
            }
         });
         refreshPlaybackState();
      }
   }

   @CallbackJS("goEnd")
   public static void goEnd() {
      RewindHandlers var0 = setQuartersOverlay();
      if (!var0.method57().method25()) {
         Highlight_3 var1 = setInputFocused();
         if (var1 != null) {
            var1.method8(var1.method3() - 1);
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("setPlayhead")
   public static void setPlayhead(Integer var0) {
      RewindHandlers var1 = setQuartersOverlay();
      if (!var1.method57().method25()) {
         ThreadModuleDump63.method3()
            .bridge$schedule(
               () -> {
                  Highlight_3 var2 = setInputFocused();
                  if (var2 != null) {
                     boolean var3 = (Boolean)ThreadModuleDump63.method4().method90().method20().get()
                        && var1.method40().method35() != null
                        && var1.method40().method35().method14();
                     if (var3 || !pause() || var0 > var2.method15()) {
                        var2.method8(Math.max(0, var0));
                     }

                     if (pause()) {
                        clickPreview(var0);
                     }

                     refreshPlaybackState();
                     if (method41() != null) {
                        method2(null);
                        refreshTimeline();
                     }

                     DriverViewportLegacy.method50().method4("rewind");
                  }
               }
            );
      }
   }

   @CallbackJS("startPlayheadDrag")
   public static void startPlayheadDrag() {
      RewindHandlers var0 = setQuartersOverlay();
      if (!var0.method57().method25()) {
         setDragging(true);
      }
   }

   @CallbackJS("stopPlayheadDrag")
   public static void stopPlayheadDrag() {
      setDragging(false);
      Highlight_3 var0 = setInputFocused();
      if (var0 != null) {
         if (method47() >= 0) {
            var0.method8(method47());
            clickPreview(-1);
         }

         refreshPlaybackState();
      }
   }

   @CallbackJS("setPreviewMode")
   public static void setPreviewMode(Boolean var0) {
      RewindHandlers var1 = setQuartersOverlay();
      if (!var1.method57().method25()) {
         setQuartersOverlay().method34(var0);
         setQuartersOverlay().method45().update();
         refreshPlaybackState();
      }
   }

   @CallbackJS("clickPreview")
   public static void clickPreview(int var0) {
      RewindHandlers var1 = setQuartersOverlay();
      if (var1 != null && !var1.method57().method25()) {
         if (!var1.method49().method14()) {
            if (!var1.method50().method1(var0)) {
               if (!var1.method62() && !var1.method45().method15().isFixedToPlayer() && var1.method26()) {
                  Highlight_3 var2 = setInputFocused();
                  if (var2 == null) {
                     return;
                  }

                  if (var1.method44()) {
                     Nameplate2 var3 = var1.method40().method40();
                     var3.method1();
                  }

                  ThreadModuleDump63.method3().bridge$getGameSettings().bridge$unpressAllKeys();
                  var1.method54(true);
                  ThreadModuleDump63.method3().bridge$setInGameFocus(true);
                  Bridge5Extension6 var4 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
                  if (var4 != null) {
                     var4.bridge$setAllowUserInput(true);
                  }
               }
            }
         }
      }
   }

   @CallbackJS("setZoom")
   public static void getProvider(float var0) {
      Coordinates.setZoom(var0);
      refreshTimeline();
      DriverViewportLegacy.method50().tick();
   }

   @CallbackJS("setRuleOfThirdsOverlay")
   public static void setRuleOfThirdsOverlay(boolean var0) {
      Highlight_3 var1 = setInputFocused();
      if (var1 != null) {
         var1.method19(var0);
         refreshPlaybackState();
      }
   }

   @CallbackJS("setQuartersOverlay")
   public static void setQuartersOverlay(boolean var0) {
      Highlight_3 var1 = setInputFocused();
      if (var1 != null) {
         var1.method21(var0);
         refreshPlaybackState();
      }
   }

   @CallbackJS("showSettings")
   public static void showSettings(boolean var0) {
      field14 = var0;
   }

   @CallbackJS("setInputFocused")
   public static void setInputFocused(boolean var0) {
      field15 = var0;
   }
}
