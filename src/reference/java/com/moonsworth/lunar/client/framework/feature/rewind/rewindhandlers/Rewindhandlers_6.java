package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.holograms.Bridge7Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;

public class Rewindhandlers_6 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public com.moonsworth.lunar.client.driver.core.gui.GuiIterator getProvider() {
      return ThreadModuleDump63.method4().method90().method15();
   }

   @CallbackJS("create")
   public static void create(String var0) {
      openReplay(
         var0,
         (String)com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.fishing.GuiIterator.method7().get(),
         com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.fishing.GuiIterator.method2(),
         false
      );
   }

   @CallbackJS("open")
   public static void open(String var0, boolean var1) {
      openReplay(var0, null, null, var1);
   }

   @CallbackJS("openLocation")
   public static void openLocation(String var0) {
      File var1 = new File(var0);
      if (var1.isFile()) {
         var1 = var1.getParentFile();
      }

      Bridge.method8().method64(var1.toURI());
   }

   @CallbackJS("delete")
   public static void delete(String var0) {
      try {
         File var1 = new File(var0);
         if (var1.isFile()) {
            Files.delete(var1.toPath());
         } else {
            FileUtils.deleteDirectory(var1);
         }
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      ThreadModuleDump63.method4().method90().method15().method2();
   }

   @CallbackJS("getMissingMods")
   public static String getMissingMods(Map<String, String> var0) {
      IchorPipeline var1 = (IchorPipeline)IchorAPI.getPipeline(Rewindhandlers_6.class.getClassLoader()).orElseThrow(() -> new IllegalStateException("Pipeline not found!"));
      HashMap var2 = new HashMap();
      var1.method18().forEach(var1x -> {
         if (!var1x.getId().startsWith("fabric-") || !var1x.getVersion().contains("+")) {
            var2.put(var1x.getId(), var1x.getVersion());
         }
      });
      HashMap var3 = new HashMap();

      for (Entry var5 : var0.entrySet()) {
         String var6 = (String)var5.getKey();
         String var7 = (String)var5.getValue();
         if (!var7.equals(var2.get(var6))) {
            var3.put(var6, var7);
         }
      }

      return ThreadModuleDump48.field22.toJson(var3);
   }

   public static void openReplay(String var0, String var1, RewindhandlersNameplate var2, boolean var3) {
      DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field3);
      DriverViewportLegacy.method50().method75().add(() -> {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Bridge7Iterator()));
         ThreadModuleDump63.method3().bridge$schedule(() -> ThreadModuleDump63.method4().method40().method85().method13(new File(var0), var1, var2, var3));
      });
   }
}
