package com.moonsworth.lunar.client.replay.export;

import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.replay.gui.AlertImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.replay.timeline.TimelineRenderJob;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.export.ExportSettingsSection;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.replay.export.FFmpegDownloader;
import com.moonsworth.lunar.client.framework.feature.screenshot.ScreenshotCapture;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.UUID;

public class ExportSettingsJsApi extends RewindEditorContext implements DriverGuiExtension, Extension {
   public ExportSettingsJsApi() {
   }

   public com.moonsworth.lunar.client.driver.core.gui.GuiIterator getProvider() {
      return !getRewind().method19() ? null : getRewindHandlers().method36();
   }

   @CallbackJS("updatePropertyValue")
   public static void updatePropertyValue(String text0, String text1, String text2, boolean flag3) {
      ExportSettingsPanel rewindhandlers_34 = (ExportSettingsPanel)getRewindHandlers().method36();

      label26:
      for (ExportSettingsSection coordinates26 : rewindhandlers_34.field27) {
         if (coordinates26.type().equals(text0)) {
            for (ClientOption lightingextension8 : coordinates26.options()) {
               if (lightingextension8.getId().equals(text1)) {
                  lightingextension8.method21(text2);
                  break label26;
               }
            }
            break;
         }
      }

      refreshExportSettings();
      refreshTimeline();
   }

   @CallbackJS("resetPropertyValue")
   public static void resetPropertyValue(String text0, String text1) {
      ExportSettingsPanel rewindhandlers_32 = (ExportSettingsPanel)getRewindHandlers().method36();
      if (rewindhandlers_32.method1(text1)) {
         refreshExportSettings();
      } else {
         label30:
         for (ExportSettingsSection coordinates24 : rewindhandlers_32.field27) {
            if (coordinates24.type().equals(text0)) {
               for (ClientOption lightingextension6 : coordinates24.options()) {
                  if (lightingextension6.getId().equals(text1)) {
                     lightingextension6.reset();
                     break label30;
                  }
               }
               break;
            }
         }

         refreshExportSettings();
      }
   }

   @CallbackJS("setRenderRegion")
   public static void setRenderRegion(int number0, int number1) {
      ReplayTimeline highlight_32 = getTimeline();
      if (highlight_32 != null) {
         ExportSettings rewindhandlersnameplate3 = highlight_32.method13();
         rewindhandlersnameplate3.method22(Math.max(0, number0));
         rewindhandlersnameplate3.method23(Math.max(0, number1));
         refreshExportSettings();
      }
   }

   @CallbackJS("export")
   public static void export() {
      try {
         DriverViewportLegacy.method50().method14("rewind-render");
         getRewindHandlers().method15();
      } catch (IOException exception1) {
         Ref.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: " + exception1.getMessage())
            .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(exception1);
      }
   }

   @CallbackJS("addToRenderQueue")
   public static void addToRenderQueue() {
      ReplayTimeline highlight_30 = getTimeline();
      if (highlight_30 != null) {
         getRewindHandlers().method57().method4(highlight_30);
         refreshExportSettings();
      }
   }

   @CallbackJS("startRenderQueue")
   public static void startRenderQueue() {
      try {
         DriverViewportLegacy.method50().method14("rewind-render");
         getRewindHandlers().method57().method22();
      } catch (IOException exception1) {
         Ref.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: " + exception1.getMessage())
            .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(exception1);
      }

      refreshExportSettings();
   }

   @CallbackJS("renderTimeline")
   public static void renderTimeline(UUID uuid0) {
      try {
         DriverViewportLegacy.method50().method14("rewind-render");
         Queue list1 = getRewindHandlers().method57().method28();
         TimelineRenderJob highlightimpl2 = list1.stream().filter(arg1x -> arg1x.getId().equals(uuid0)).findFirst().orElse(null);
         if (highlightimpl2 == null) {
            Ref.method4()
               .method69()
               .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: Timeline not found")
               .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
            return;
         }

         list1.remove(highlightimpl2);
         getRewindHandlers().method57().method12(highlightimpl2);
      } catch (IOException exception3) {
         Ref.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: " + exception3.getMessage())
            .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(exception3);
      }

      refreshExportSettings();
   }

   @CallbackJS("removeTimeline")
   public static void removeTimeline(UUID uuid0) {
      for (TimelineRenderJob highlightimpl3 : getRewindHandlers().method57().method28()) {
         if (highlightimpl3.getId().equals(uuid0)) {
            getRewindHandlers().method57().method5(highlightimpl3);
            break;
         }
      }

      refreshExportSettings();
   }

   @CallbackJS("exportProject")
   public static void exportProject() {
      try {
         File file0 = Gui4.method9(
            null,
            new File(
               com.moonsworth.lunar.client.replay.project.RewindPaths.field10, getRewindHandlers().method40().method32().getName() + ".zip"
            ),
            null,
            "zip"
         );
         if (file0 != null) {
            getRewindHandlers().method40().method19(file0);
            BrowserUtils.method10(file0.getParentFile());
         }
      } catch (IOException exception1) {
         Ref.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: " + exception1.getMessage())
            .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(exception1);
      }
   }

   @CallbackJS("exportScreenshot")
   public static void exportScreenshot() {
      com.moonsworth.lunar.client.replay.project.RewindPaths.field12.mkdirs();
      File file0 = Gui4.method9(null, new File(getRewindHandlers().method57().method15(), "screenshot.png"), "PNG", "png");
      if (file0 != null) {
         Ref.method4().method90().method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(file0.getParent());
         AlertImpl alertimpl1 = getRewindHandlers().method48().method24();
         ScreenshotCapture screenshot22 = new ScreenshotCapture();
         int number3 = alertimpl1.method11().bridge$framebufferTextureWidth();
         int number4 = alertimpl1.method11().bridge$framebufferTextureHeight();
         screenshot22.method3(null, file0, number3, number4, alertimpl1.method11(), false);
         BrowserUtils.method10(file0.getParentFile());
      }
   }

   @CallbackJS("downloadFfmpeg")
   public static void downloadFfmpeg() {
      ExportSettingsPanel rewindhandlers_30 = (ExportSettingsPanel)getRewindHandlers().method36();
      FFmpegDownloader rewindhandlersnameplate21 = new FFmpegDownloader(rewindhandlers_30.method9());
      new Thread(() -> rewindhandlersnameplate21.method1(getRewindHandlers(), com.moonsworth.lunar.client.replay.project.RewindPaths.field13)).start();
   }

   @CallbackJS("stopRendering")
   public static void stopRendering() {
      getRewindHandlers().method57().method23();
   }
}
