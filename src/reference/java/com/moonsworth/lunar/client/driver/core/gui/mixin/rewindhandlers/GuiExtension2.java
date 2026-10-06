package com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;

public class GuiExtension2 implements DriverGuiExtensionLegacy {
   private static final String field1 = "lcwaypoint";

   @Override
   public void method2(KeyCode var1, int var2, int var3, int var4, int var5) {
      if (var1 == KeyCode.KEY_V && LcuiScreen.isCtrlKeyDown() && var4 == 0) {
         method8();
      }
   }

   @CallbackJS("copyWaypoints")
   public static void method2(GuiExtension2.Data2[] var0) {
      AtomicInteger var1 = new AtomicInteger();
      JsonObject var2 = new JsonObject();
      JsonObject var3 = new JsonObject();
      var2.add("waypoints", var3);

      for (GuiExtension2.Data2 var7 : var0) {
         method10(var7.field1, var7.field2, var7.field3).ifPresent(var2x -> {
            var1.getAndIncrement();
            ThreadModuleDump63.method4().method48().method18(var3, var2x);
         });
      }

      ThreadModuleDump68.setClipboardString(var2.toString());
      if (var1.get() == 0) {
         ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Could not find waypoints to copy!");
      } else if (var1.get() == 1) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.SUCCESS, "Copied waypoint", "You can import this waypoint from the waypoint UI dropdown or by pressing CTRL + V");
      } else {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.SUCCESS, "Copied " + var1 + " waypoints", "You can import these waypoints from the waypoint UI dropdown or by pressing CTRL + V");
      }
   }

   @CallbackJS("exportWaypoints")
   public static void method3(GuiExtension2.Data2[] var0) {
      AtomicInteger var1 = new AtomicInteger();
      JsonObject var2 = new JsonObject();
      JsonObject var3 = new JsonObject();
      var2.add("waypoints", var3);

      for (GuiExtension2.Data2 var7 : var0) {
         method10(var7.field1, var7.field2, var7.field3).ifPresent(var2x -> {
            var1.getAndIncrement();
            ThreadModuleDump63.method4().method48().method18(var3, var2x);
         });
      }

      if (var3.isEmpty()) {
         ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Could not find waypoints to export!");
      } else {
         String var9 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
         File var10 = Gui4.method9("Save Waypoints", new File("export-" + var9), "lcwaypoint", "lcwaypoint");
         if (var10 != null) {
            try {
               String var11 = var10.getPath();
               if (!var11.toLowerCase().endsWith(".lcwaypoint")) {
                  var10 = new File(var11 + ".lcwaypoint");
               }

               FileUtils.write(var10, var2.toString(), Charset.defaultCharset());
               ThreadModuleDump63.method4().method69().method7(NotificationType.SUCCESS, "Successfully exported waypoints!");
            } catch (Exception var8) {
               ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Failed to export waypoints!");
            }
         }
      }
   }

   @CallbackJS("importWaypointsFromClipboard")
   public static String method4() {
      String var0 = ThreadModuleDump68.getClipboardString();
      if (var0 != null && !var0.isEmpty()) {
         try {
            List var6 = method9(JsonParser.parseString(var0).getAsJsonObject(), false);
            return method5(var6);
         } catch (Exception var5) {
            for (Gui2Loader var2 : ThreadModuleDump63.method4().method48().getDecoders()) {
               try {
                  Collection var3 = var2.method3(null, var0);
                  if (var3 != null && !var3.isEmpty()) {
                     return method5(var3);
                  }
               } catch (Gui2Loader.Data var4) {
               }
            }
         }
      }

      ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Failed to import waypoints from clipboard!");
      return null;
   }

   private static String method5(Collection<GuiHandler2> var0) {
      JsonObject var1 = ThreadModuleDump63.method4().method48().method21(var0);
      JsonObject var2 = new JsonObject();
      JsonObject var3 = new JsonObject();
      ThreadModuleDump63.method4().method48().method17(var3, var0);
      var2.add("waypoints", var3);
      var2.add("context", var1);
      return var2.toString();
   }

   @CallbackJS("saveImportedWaypoints")
   public static void method6(String var0) {
      if (var0 != null) {
         try {
            method9(JsonParser.parseString(var0).getAsJsonObject(), true);
         } catch (Exception var2) {
            var2.printStackTrace();
            ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Failed to import waypoint(s) from clipboard.");
         }
      }
   }

   @CallbackJS("importWaypointsFromFile")
   public static String method7() {
      File var0 = Gui4.method7("Select Waypoints File", null, "lcwaypoint", "lcwaypoint");
      if (var0 != null && var0.exists()) {
         try {
            String var1 = FileUtils.readFileToString(var0, Charset.defaultCharset());
            List var2 = method9(JsonParser.parseString(var1).getAsJsonObject(), false);
            return method5(var2);
         } catch (Exception var3) {
         }
      }

      ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Failed to import waypoints from file!");
      return null;
   }

   @CallbackJS("pasteWaypoint")
   public static void method8() {
      String var0 = ThreadModuleDump68.getClipboardString();
      if (var0 != null) {
         try {
            method9(JsonParser.parseString(var0).getAsJsonObject(), true);
         } catch (Exception var2) {
            ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Failed to import waypoint(s) from clipboard.");
         }
      }
   }

   private static List<GuiHandler2> method9(JsonObject var0, boolean var1) {
      WaypointStore var2 = ThreadModuleDump63.method4().method48();
      List var3 = var2.method13(var0);

      for (GuiHandler2 var5 : var3) {
         String var6 = var5.getName();

         for (int var7 = 1; var2.method24(var5.getServer(), var5.getWorld(), var5.getName()).isPresent(); var7++) {
            var5.setName(var6 + " (" + var7 + ")");
         }

         if (var1) {
            var2.method6(var5);
         }
      }

      if (var1) {
         ThreadModuleDump63.method4().method69().method7(NotificationType.SUCCESS, "Imported %s waypoint(s)!".formatted(var3.size()));
         Client.method109().method48().method20();
         Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      }

      return var3;
   }

   private static Optional<GuiHandler2> method10(String var0, String var1, String var2) {
      return Client.method109().method48().method24(var0, var1, var2);
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @Override
   public JsonElement provide() {
      return null;
   }

   public class Data2 {
      @SerializedName("server")
      private final String field1;
      @SerializedName("world")
      private final String field2;
      @SerializedName("name")
      private final String field3;

      public Data2(String var1, String var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @SerializedName("server")
      public String method1() {
         return this.field1;
      }

      @SerializedName("world")
      public String method2() {
         return this.field2;
      }

      @SerializedName("name")
      public String name() {
         return this.field3;
      }
   }
}
