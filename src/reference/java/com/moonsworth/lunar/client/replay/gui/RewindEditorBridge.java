package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.replay.gui.KeybindCombination;
import com.moonsworth.lunar.client.replay.gui.TimelineSelection;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.gui.RewindPropertiesBridge;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.IOException;
import java.util.Map.Entry;

public class RewindEditorBridge extends RewindEditorContext implements DriverGuiExtension, Extension {
   private boolean wasInputFocused;
   private static boolean settingsVisible;
   private static boolean inputFocused;

   public RewindEditorBridge() {
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
      ReplayTimeline highlight_30 = getTimeline();
      if (highlight_30 != null) {
         refreshTimeline();
         refreshProperties();
      }
   }

   public GuiIterator getProvider() {
      return !getRewind().method19() ? null : getRewindHandlers().method28();
   }

   public void updatePreviewWindow(double value1, double value3) {
      if (com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown() && !getRewindHandlers().method62()) {
         if (value3 < 0.0) {
            DriverViewportLegacy.method50().method15("rewind-scroll", true);
         } else {
            DriverViewportLegacy.method50().method15("rewind-scroll", false);
         }
      }
   }

   public void method2(KeyCode bridgetype_81, int number2, int number3, int number4, int number5) {
      if (!field14 && !field15) {
         RewindHandlers rewindhandlers6 = getRewindHandlers();
         if (rewindhandlers6 != null && !rewindhandlers6.method57().method25()) {
            if (number4 != 1 && number4 != 2) {
               if (!this.wasInputFocused && number4 == 0 && bridgetype_81 == KeyCode.KEY_ESCAPE && rewindhandlers6.method40().method31()) {
                  getRewindHandlers().method24();
               }

               this.wasInputFocused = getRewindHandlers().method62();
            } else {
               ReplayTimeline highlight_37 = getTimeline();
               if (highlight_37 != null) {
                  UndoRedoManager nameplate28 = rewindhandlers6.method40().method40();
                  Track gui_29 = getSelectedLayer() == null ? null : findTrackOfLayer(getSelectedLayer().getId());
                  if (gui_29 == null) {
                     method2(null);
                  }

                  Entry entry10 = gui_29 != null && getSelectedLayer() != null ? highlight_37.method1().get(getSelectedLayer().getId()) : null;
                  if (entry10 == null) {
                     method2(null);
                  }

                  this.wasInputFocused = rewindhandlers6.method62();
                  if (!rewindhandlers6.method62() || bridgetype_81 == KeyCode.KEY_ESCAPE) {
                     rewindhandlers6.method56()
                        .method3(
                           new KeybindCombination(
                              bridgetype_81,
                              com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown(),
                              com.moonsworth.lunar.client.ui.LcuiScreen.isShiftKeyDown(),
                              com.moonsworth.lunar.client.ui.LcuiScreen.method124()
                           ),
                           new TimelineSelection(rewindhandlers6, highlight_37, nameplate28, gui_29, entry10),
                           number4 == 2
                        );
                  }
               }
            }
         }
      }
   }

   @CallbackJS("updatePreviewWindow")
   public static void updatePreviewWindow(Float value0, Float value1) {
      RewindHandlers rewindhandlers2 = getRewindHandlers();
      if (rewindhandlers2 != null) {
         rewindhandlers2.method20(value0, value1);
      }
   }

   @CallbackJS("updateEditorLayout")
   public static void updateEditorLayout(Float value0, Float value1) {
      LoadingStageImpl fogloader22 = Ref.method4().method90();
      if (value0 != null) {
         fogloader22.method16().method1(value0);
      }

      if (value1 != null) {
         fogloader22.method17().method1(value1);
      }
   }

   @CallbackJS("exit")
   public static void exit() {
      getRewindHandlers().method24();
   }

   @CallbackJS("pause")
   public static void pause(boolean flag0) {
      RewindHandlers rewindhandlers1 = getRewindHandlers();
      if (!rewindhandlers1.method57().method25()) {
         ReplayTimeline highlight_32 = getTimeline();
         if (highlight_32 != null) {
            if (!flag0) {
               method2(null);
               refreshTimeline();
               if (getRewindHandlers().method25()) {
                  highlight_32.method8(0);
               }
            }

            highlight_32.setPaused(flag0);
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("save")
   public static void save() {
      try {
         getProject().method17(false);
         Ref.method4().method69().method6(NotificationType.SUCCESS, "Rewind", "Project saved!").method9(NotificationAnchor.BOTTOM_RIGHT);
      } catch (IOException exception1) {
         Ref.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while saving replay: " + exception1.getMessage())
            .method9(NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(exception1);
      }
   }

   @CallbackJS("undo")
   public static void undo() {
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      if (!rewindhandlers0.method57().method25()) {
         getRewindHandlers().method40().method40().undo();
         getRewindHandlers().method56(true);
         getRewindHandlers().method27();
         RewindPropertiesBridge.beginUndoBatch();
         RewindPropertiesBridge.endUndoBatch();
      }
   }

   @CallbackJS("redo")
   public static void redo() {
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      if (!rewindhandlers0.method57().method25()) {
         getRewindHandlers().method40().method40().redo();
         getRewindHandlers().method56(true);
         getRewindHandlers().method27();
         RewindPropertiesBridge.beginUndoBatch();
         RewindPropertiesBridge.endUndoBatch();
      }
   }

   @CallbackJS("forward")
   public static void forward() {
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      if (!rewindhandlers0.method57().method25()) {
         ReplayTimeline highlight_31 = getTimeline();
         if (highlight_31 != null) {
            highlight_31.method8(highlight_31.method15() + 1);
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("backward")
   public static void backward() {
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      if (!rewindhandlers0.method57().method25()) {
         ReplayTimeline highlight_31 = getTimeline();
         if (highlight_31 != null) {
            highlight_31.method8(Math.max(0, highlight_31.method15() - 1));
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("goStart")
   public static void goStart() {
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      if (!rewindhandlers0.method57().method25()) {
         ReplayTimeline highlight_31 = getTimeline();
         if (highlight_31 != null) {
            highlight_31.method8(0);
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("reload")
   public static void reload() {
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      if (!rewindhandlers0.method57().method25()) {
         Ref.method3().bridge$schedule(() -> {
            try {
               rewindhandlers0.method3(rewindhandlers0.method41().method9());
            } catch (IOException exception2) {
               throw new RuntimeException(exception2);
            }
         });
         refreshPlaybackState();
      }
   }

   @CallbackJS("goEnd")
   public static void goEnd() {
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      if (!rewindhandlers0.method57().method25()) {
         ReplayTimeline highlight_31 = getTimeline();
         if (highlight_31 != null) {
            highlight_31.method8(highlight_31.method3() - 1);
            refreshPlaybackState();
         }
      }
   }

   @CallbackJS("setPlayhead")
   public static void setPlayhead(Integer number0) {
      RewindHandlers rewindhandlers1 = getRewindHandlers();
      if (!rewindhandlers1.method57().method25()) {
         Ref.method3()
            .bridge$schedule(
               () -> {
                  ReplayTimeline highlight_32 = getTimeline();
                  if (highlight_32 != null) {
                     boolean flag3 = (Boolean)Ref.method4().method90().method20().get()
                        && rewindhandlers1.method40().method35() != null
                        && rewindhandlers1.method40().method35().method14();
                     if (flag3 || !isDragging() || number0 > highlight_32.method15()) {
                        highlight_32.method8(Math.max(0, number0));
                     }

                     if (isDragging()) {
                        setScrubFrame(number0);
                     }

                     refreshPlaybackState();
                     if (getSelectedKeyframe() != null) {
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
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      if (!rewindhandlers0.method57().method25()) {
         setDragging(true);
      }
   }

   @CallbackJS("stopPlayheadDrag")
   public static void stopPlayheadDrag() {
      setDragging(false);
      ReplayTimeline highlight_30 = getTimeline();
      if (highlight_30 != null) {
         if (getScrubFrame() >= 0) {
            highlight_30.method8(getScrubFrame());
            setScrubFrame(-1);
         }

         refreshPlaybackState();
      }
   }

   @CallbackJS("setPreviewMode")
   public static void setPreviewMode(Boolean flag0) {
      RewindHandlers rewindhandlers1 = getRewindHandlers();
      if (!rewindhandlers1.method57().method25()) {
         getRewindHandlers().method34(flag0);
         getRewindHandlers().method45().update();
         refreshPlaybackState();
      }
   }

   @CallbackJS("clickPreview")
   public static void clickPreview(int number0) {
      RewindHandlers rewindhandlers1 = getRewindHandlers();
      if (rewindhandlers1 != null && !rewindhandlers1.method57().method25()) {
         if (!rewindhandlers1.method49().method14()) {
            if (!rewindhandlers1.method50().method1(number0)) {
               if (!rewindhandlers1.method62() && !rewindhandlers1.method45().method15().isFixedToPlayer() && rewindhandlers1.method26()) {
                  ReplayTimeline highlight_32 = getTimeline();
                  if (highlight_32 == null) {
                     return;
                  }

                  if (rewindhandlers1.method44()) {
                     UndoRedoManager nameplate23 = rewindhandlers1.method40().method40();
                     nameplate23.method1();
                  }

                  Ref.method3().bridge$getGameSettings().bridge$unpressAllKeys();
                  rewindhandlers1.method54(true);
                  Ref.method3().bridge$setInGameFocus(true);
                  GuiScreenBridge bridge5extension64 = Ref.method3().bridge$getCurrentScreen();
                  if (bridge5extension64 != null) {
                     bridge5extension64.bridge$setAllowUserInput(true);
                  }
               }
            }
         }
      }
   }

   @CallbackJS("setZoom")
   public static void getProvider(float value0) {
      RewindEditorContext.setZoom(value0);
      refreshTimeline();
      DriverViewportLegacy.method50().tick();
   }

   @CallbackJS("setRuleOfThirdsOverlay")
   public static void setRuleOfThirdsOverlay(boolean flag0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 != null) {
         highlight_31.method19(flag0);
         refreshPlaybackState();
      }
   }

   @CallbackJS("setQuartersOverlay")
   public static void setQuartersOverlay(boolean flag0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 != null) {
         highlight_31.method21(flag0);
         refreshPlaybackState();
      }
   }

   @CallbackJS("showSettings")
   public static void showSettings(boolean flag0) {
      field14 = flag0;
   }

   @CallbackJS("setInputFocused")
   public static void setInputFocused(boolean flag0) {
      field15 = flag0;
   }
}
