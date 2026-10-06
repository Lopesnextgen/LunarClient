package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.render.RewindLoadingScreen;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
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

public class RewindFileCallbacks extends RewindEditorContext implements DriverGuiExtension, Extension {
   public RewindFileCallbacks() {
   }

   public com.moonsworth.lunar.client.driver.core.gui.GuiIterator getProvider() {
      return Ref.method4().method90().method15();
   }

   @CallbackJS("create")
   public static void method2(String text0) {
      method6(
         text0,
         (String)com.moonsworth.lunar.client.replay.gui.CreateProjectBridge.method7().get(),
         com.moonsworth.lunar.client.replay.gui.CreateProjectBridge.method2(),
         false
      );
   }

   @CallbackJS("open")
   public static void method3(String text0, boolean flag1) {
      method6(text0, null, null, flag1);
   }

   @CallbackJS("openLocation")
   public static void method4(String text0) {
      File file1 = new File(text0);
      if (file1.isFile()) {
         file1 = file1.getParentFile();
      }

      Bridge.method8().method64(file1.toURI());
   }

   @CallbackJS("delete")
   public static void delete(String text0) {
      try {
         File file1 = new File(text0);
         if (file1.isFile()) {
            Files.delete(file1.toPath());
         } else {
            FileUtils.deleteDirectory(file1);
         }
      } catch (IOException exception2) {
         throw new RuntimeException(exception2);
      }

      Ref.method4().method90().method15().method2();
   }

   @CallbackJS("getMissingMods")
   public static String method5(Map<String, String> map0) {
      IchorPipeline ichor71 = (IchorPipeline)IchorAPI.getPipeline(RewindFileCallbacks.class.getClassLoader()).orElseThrow(() -> new IllegalStateException("Pipeline not found!"));
      HashMap map2 = new HashMap();
      ichor71.method18().forEach(arg1x -> {
         if (!arg1x.getId().startsWith("fabric-") || !arg1x.getVersion().contains("+")) {
            map2.put(arg1x.getId(), arg1x.getVersion());
         }
      });
      HashMap map3 = new HashMap();

      for (Entry entry5 : map0.entrySet()) {
         String text6 = (String)entry5.getKey();
         String text7 = (String)entry5.getValue();
         if (!text7.equals(map2.get(text6))) {
            map3.put(text6, text7);
         }
      }

      return LunarConstants.field22.toJson(map3);
   }

   public static void method6(String text0, String text1, ExportSettings rewindhandlersnameplate2, boolean flag3) {
      DriverViewportLegacy.method50().method16(DriverRouteRegistry.field3);
      DriverViewportLegacy.method50().method75().add(() -> {
         Ref.method3().bridge$displayScreen(Bridge.method8().method18(new RewindLoadingScreen()));
         Ref.method3().bridge$schedule(() -> Ref.method4().method40().method85().method13(new File(text0), text1, rewindhandlersnameplate2, flag3));
      });
   }
}
