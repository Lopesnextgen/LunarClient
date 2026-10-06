package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight;

import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator22;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2Impl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class Highlight2_2 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public GuiIterator getProvider() {
      return !method16().method19() ? null : method17().method30();
   }

   @CallbackJS("onClick")
   public static void onClick(UUID var0) {
      Highlight_3 var1 = method18().method37();
      if (var1 != null) {
         openMedia(var0, null, var1.method15());
      }
   }

   public static void openMedia(UUID var0, UUID var1, int var2) {
      Highlight_3 var3 = method18().method37();
      if (var3 != null) {
         if (!method18().method43().method5().method8(var0)) {
            Nameplate var4 = ((Nameplate4)method17().method42().get()).method9();
            var4.method2();
            var4.cleanup();
            Highlight_4 var5 = method18().method43().method5();
            Rewind var6 = method18().method43().method6();
            Nameplate2 var7 = method18().method40();
            var5.method16(
               method18(),
               var0,
               () -> {
                  try {
                     Rewind3 var5x = var6.method4(var0);
                     int var6x = var5x.method13().method5();
                     if (ThreadModuleDump63.method3().bridge$getProtocolVersion() != var6x) {
                        ThreadModuleDump63.method4()
                           .method69()
                           .method6(NotificationType.ERROR, "Rewind", "The rewind file was created in a different version of Minecraft.")
                           .method9(NotificationAnchor.BOTTOM_RIGHT);
                        return;
                     }

                     var7.method1();
                     method18().method7(var5x, var2, var1);
                     var7.endBatch();
                  } catch (IOException var7x) {
                     var7.method2();
                     var7x.printStackTrace();
                     ThreadModuleDump63.method4()
                        .method69()
                        .method6(NotificationType.ERROR, "Rewind", "Failed to load the media.")
                        .method9(NotificationAnchor.BOTTOM_RIGHT);
                  }
               },
               () -> {
                  try {
                     var7.method1();
                     RewindIterator22 var6x = new RewindIterator22(method18().method40());
                     Rewindhandlers2Impl var7x = new Rewindhandlers2Impl(
                        method18().method32(),
                        "media://" + var0,
                        method18().method42(),
                        var5,
                        method18().method44()
                     );
                     var7x.play();
                     var6x.method9(var7x);
                     int var8 = (int)(var7x.getDuration() / var3.method9());
                     Range var9 = Range.between(var2, var2 + var8);
                     GuiImpl2[] var10 = var3.method11().method4().toArray(new GuiImpl2[0]);
                     GuiImpl2 var11 = null;

                     for (GuiImpl2 var15 : var10) {
                        if (var15.getId().equals(var1) || var1 == null) {
                           var11 = var15;
                           break;
                        }
                     }

                     if (var11 == null) {
                        var11 = new GuiImpl2(method18().method40(), var3.method1());
                        var3.method11().method4().add(var11);
                     }

                     var11.method5().method1(var9, var6x);
                     var6x.method5(var9, method18().method38(), method18().method40());
                     method18().method45().method6(var9, var6x, var3.method9());
                     var7.endBatch();
                  } catch (IOException var16) {
                     var7.method2();
                     var16.printStackTrace();
                     ThreadModuleDump63.method4()
                        .method69()
                        .method6(NotificationType.ERROR, "Rewind", "Failed to load the media.")
                        .method9(NotificationAnchor.BOTTOM_RIGHT);
                  }
               },
               () -> {
                  var7.method1();
                  RewindImpl var5x = new RewindImpl(method18().method40());
                  var5x.method5(var0);
                  int var6x = (int)(10000.0 / var3.method9());
                  Range var7x = Range.between(var2, var2 + var6x);
                  GuiImpl[] var8 = var3.method11().method2().toArray(new GuiImpl[0]);
                  GuiImpl var9 = null;

                  for (GuiImpl var13 : var8) {
                     if (var13.getId().equals(var1) || var1 == null) {
                        var9 = var13;
                        break;
                     }
                  }

                  if (var9 == null) {
                     var9 = new GuiImpl(method18().method40(), var3.method1());
                     var3.method11().method2().add(var9);
                  }

                  var9.method5().method1(var7x, var5x);
                  var5x.method16(var7x, method18().method38(), method18().method40());
                  method18().method45().method6(var7x, var5x, var3.method9());
                  var7.endBatch();
               }
            );
            refreshTimeline();
         }
      }
   }

   @CallbackJS("importMedia")
   public static void importMedia(UUID var0) {
      Highlight_3 var1 = method19();
      if (var1 != null) {
         Highlight_4 var2 = method18().method43().method5();
         File[] var3 = Gui4.method8(null, Gui.field8, null, Highlight_4.field4);
         if (var3 != null) {
            for (File var7 : var3) {
               UUID var8 = var2.method2(var7);
               var2.method13(var8, var0);
            }

            refreshMediaExporter();
         }
      }
   }

   @CallbackJS("removeMedia")
   public static void removeMedia(UUID var0) {
      Highlight_4 var1 = method18().method43().method5();
      boolean var2 = var1.method8(var0);
      String var3 = var2 ? var1.method11(var0, null) : null;
      File var4 = var1.method3(var0);
      refreshMediaExporter();
      refreshTimeline();
      if (var2) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.SUCCESS, "Rewind", ThreadModuleDump63.method4().method67().method2("popups", "deletedFolder", new Object[]{var3}))
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      } else if (var4 != null) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.SUCCESS, "Rewind", ThreadModuleDump63.method4().method67().method2("popups", "deletedMedia", new Object[]{var4.getName()}))
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      }
   }

   @CallbackJS("newFolder")
   public static void newFolder(UUID var0) {
      Highlight_4 var1 = method18().method43().method5();
      var1.method10(uniqueFolderName(var1, var0), var0);
      refreshMediaExporter();
   }

   @CallbackJS("rename")
   public static void newFolder(UUID var0, String var1) {
      Highlight_4 var2 = method18().method43().method5();
      var2.method12(var0, var1);
      refreshMediaExporter();
      refreshTimeline();
   }

   @CallbackJS("moveMedia")
   public static void moveMedia(UUID var0, UUID var1) {
      Highlight_4 var2 = method18().method43().method5();
      var2.method13(var0, var1);
      refreshMediaExporter();
   }

   private static String uniqueFolderName(Highlight_4 var0, UUID var1) {
      HashSet var2 = new HashSet();

      for (Entry var4 : var0.method7()) {
         if (Objects.equals(var0.method9((UUID)var4.getKey()), var1)) {
            var2.add((String)var4.getValue());
         }
      }

      String var5 = "New Folder";
      if (!var2.contains(var5)) {
         return var5;
      }

      int var6 = 2;

      while (var2.contains(var5 + " " + var6)) {
         var6++;
      }

      return var5 + " " + var6;
   }
}
