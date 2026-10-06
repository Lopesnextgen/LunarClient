package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.click;

import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HighlightImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Click2 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public GuiIterator getProvider() {
      return !method16().method19() ? null : method17().method34();
   }

   @CallbackJS("selectTimeline")
   public static void selectTimeline(UUID var0) {
      Highlight_3 var1 = getProvider(var0);
      if (var1 != null) {
         method17().method40().method14(var1);
         selectTimeline(null);
         selectTimeline(null);
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Switched timeline to " + var1.getName())
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      }
   }

   @CallbackJS("editTimelineName")
   public static void editTimelineName(UUID var0, String var1) {
      Highlight_3 var2 = getProvider(var0);
      if (var2 != null) {
         var2.setName(var1);

         try {
            method18().method17(false);
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }

         refreshTimelinesList();
      }
   }

   @CallbackJS("duplicateTimeline")
   public static void duplicateTimeline(UUID var0) {
      Highlight_3 var1 = getProvider(var0);
      if (var1 != null) {
         Rewind2_3 var2 = method18();
         File var3 = new File(var2.method32(), "timelines");
         var3.mkdirs();
         if (!var3.isDirectory()) {
            throw new RuntimeException("Failed to create timelines folder");
         }

         UUID var4 = UUID.randomUUID();
         File var5 = var2.method23(var3, var1, String.valueOf(var4));

         HighlightImpl var6;
         try {
            var6 = var2.method12(var5, HighlightImpl.class);
         } catch (IOException var9) {
            throw new RuntimeException(var9);
         }

         var6.setId(var4);
         var6.setName(var1.getName() + " (Copy)");
         var2.method36().add(var6);

         try {
            var2.method17(false);
         } catch (IOException var8) {
            throw new RuntimeException(var8);
         }

         refreshTimelinesList();
      }
   }

   @CallbackJS("deleteTimeline")
   public static void deleteTimeline(UUID var0) {
      Highlight_3 var1 = getProvider(var0);
      if (var1 != null) {
         method17().method40().method6(var1);
         method17().method27();
         selectTimeline(null);
         selectTimeline(null);
      }
   }

   @CallbackJS("newTimeline")
   public static void newTimeline() {
      selectTimeline(null);
      selectTimeline(null);
      method17().method40().method5(com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.fishing.GuiIterator.method2());
      method17().method27();
   }
}
