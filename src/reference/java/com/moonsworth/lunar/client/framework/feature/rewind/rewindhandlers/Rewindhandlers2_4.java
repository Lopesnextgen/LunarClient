package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.AlertImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HighlightImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate2;
import com.moonsworth.lunar.client.framework.feature.screenshot.Screenshot2;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.UUID;

public class Rewindhandlers2_4 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public com.moonsworth.lunar.client.driver.core.gui.GuiIterator getProvider() {
      return !method16().method19() ? null : method17().method36();
   }

   @CallbackJS("updatePropertyValue")
   public static void updatePropertyValue(String var0, String var1, String var2, boolean var3) {
      Rewindhandlers_3 var4 = (Rewindhandlers_3)method17().method36();

      label26:
      for (Coordinates2 var6 : var4.sections) {
         if (var6.type().equals(var0)) {
            for (ClientOption var8 : var6.options()) {
               if (var8.getId().equals(var1)) {
                  var8.method21(var2);
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
   public static void resetPropertyValue(String var0, String var1) {
      Rewindhandlers_3 var2 = (Rewindhandlers_3)method17().method36();
      if (var2.refresh(var1)) {
         refreshExportSettings();
      } else {
         label30:
         for (Coordinates2 var4 : var2.sections) {
            if (var4.type().equals(var0)) {
               for (ClientOption var6 : var4.options()) {
                  if (var6.getId().equals(var1)) {
                     var6.reset();
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
   public static void setRenderRegion(int var0, int var1) {
      Highlight_3 var2 = method19();
      if (var2 != null) {
         RewindhandlersNameplate var3 = var2.refresh3();
         var3.method22(Math.max(0, var0));
         var3.method23(Math.max(0, var1));
         refreshExportSettings();
      }
   }

   @CallbackJS("export")
   public static void export() {
      try {
         DriverViewportLegacy.method50().method14("rewind-render");
         method17().method15();
      } catch (IOException var1) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: " + var1.getMessage())
            .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(var1);
      }
   }

   @CallbackJS("addToRenderQueue")
   public static void addToRenderQueue() {
      Highlight_3 var0 = method19();
      if (var0 != null) {
         method17().method57().method4(var0);
         refreshExportSettings();
      }
   }

   @CallbackJS("startRenderQueue")
   public static void startRenderQueue() {
      try {
         DriverViewportLegacy.method50().method14("rewind-render");
         method17().method57().method22();
      } catch (IOException var1) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: " + var1.getMessage())
            .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(var1);
      }

      refreshExportSettings();
   }

   @CallbackJS("renderTimeline")
   public static void renderTimeline(UUID var0) {
      try {
         DriverViewportLegacy.method50().method14("rewind-render");
         Queue var1 = method17().method57().method28();
         HighlightImpl var2 = var1.stream().filter(var1x -> var1x.getId().equals(var0)).findFirst().orElse(null);
         if (var2 == null) {
            ThreadModuleDump63.method4()
               .method69()
               .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: Timeline not found")
               .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
            return;
         }

         var1.remove(var2);
         method17().method57().method12(var2);
      } catch (IOException var3) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: " + var3.getMessage())
            .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(var3);
      }

      refreshExportSettings();
   }

   @CallbackJS("removeTimeline")
   public static void removeTimeline(UUID var0) {
      for (HighlightImpl var3 : method17().method57().method28()) {
         if (var3.getId().equals(var0)) {
            method17().method57().method5(var3);
            break;
         }
      }

      refreshExportSettings();
   }

   @CallbackJS("exportProject")
   public static void exportProject() {
      try {
         File var0 = Gui4.method9(
            null,
            new File(
               com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field10, method17().method40().method32().getName() + ".zip"
            ),
            null,
            "zip"
         );
         if (var0 != null) {
            method17().method40().method19(var0);
            ThreadModuleDump61.method10(var0.getParentFile());
         }
      } catch (IOException var1) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Error while starting renderer: " + var1.getMessage())
            .method9(com.moonsworth.lunar.client.gui.notification.NotificationAnchor.BOTTOM_RIGHT);
         throw new RuntimeException(var1);
      }
   }

   @CallbackJS("exportScreenshot")
   public static void exportScreenshot() {
      com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field12.mkdirs();
      File var0 = Gui4.method9(null, new File(method17().method57().method15(), "screenshot.png"), "PNG", "png");
      if (var0 != null) {
         ThreadModuleDump63.method4().method90().method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(var0.getParent());
         AlertImpl var1 = method17().method48().method24();
         Screenshot2 var2 = new Screenshot2();
         int var3 = var1.method11().bridge$framebufferTextureWidth();
         int var4 = var1.method11().bridge$framebufferTextureHeight();
         var2.method3(null, var0, var3, var4, var1.method11(), false);
         ThreadModuleDump61.method10(var0.getParentFile());
      }
   }

   @CallbackJS("downloadFfmpeg")
   public static void downloadFfmpeg() {
      Rewindhandlers_3 var0 = (Rewindhandlers_3)method17().method36();
      RewindhandlersNameplate2 var1 = new RewindhandlersNameplate2(var0.getProgressListener());
      new Thread(() -> var1.method1(method17(), com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field13)).start();
   }

   @CallbackJS("stopRendering")
   public static void stopRendering() {
      method17().method57().method23();
   }
}
