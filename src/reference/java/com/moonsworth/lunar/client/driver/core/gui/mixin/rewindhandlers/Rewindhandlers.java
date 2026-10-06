package com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.rewindhandlers.ConfigureWaypointPropsLegacy;
import com.moonsworth.lunar.client.mod.render.waypoints.Waypoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;

public class Rewindhandlers implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method48().method15();
   }

   @CallbackJS("setEnabled")
   public static void method2() {
      Waypoints var0 = Client.method109().method40().method20();
      var0.method3(Framework.field6).ifPresent(var0x -> var0x.setEnabled(true));
   }

   @CallbackJS("editWaypoint")
   public static void method3(String var0, String var1, String var2) {
      method8(var0, var1, var2)
         .ifPresent(
            var0x -> DriverViewportLegacy.method50()
               .method17(
                  DriverRouteRegistryLegacy.field18, ConfigureWaypointPropsLegacy.method2().method1(var0x.getServer()).method2(var0x.getWorld()).method3(var0x.getName()).method7()
               )
         );
   }

   @CallbackJS("toggleVisibility")
   public static void method4(String var0, String var1, String var2) {
      method8(var0, var1, var2).ifPresent(var0x -> {
         var0x.setVisible(!var0x.isVisible());
         ThreadModuleDump63.method4().method48().method20();
         Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      });
   }

   @CallbackJS("deleteWaypoint")
   public static void method5(String var0, String var1, String var2) {
      method8(var0, var1, var2).ifPresent(var0x -> Client.method109().method48().method9(var0x));
   }

   @CallbackJS("teleportToWaypoint")
   public static void method6(String var0, String var1, String var2) {
      method8(var0, var1, var2).ifPresent(var0x -> {
         Vec3Bridge var1x = var0x.method35();
         ThreadModuleDump63.method7().bridge$sendChatMessage("/tp " + var1x.bridge$xCoord() + " " + var1x.bridge$yCoord() + " " + var1x.bridge$zCoord());
      });
   }

   @CallbackJS("addWaypoint")
   public static void method7(
      String var0,
      String var1,
      Rewindhandlers.Data17 var2,
      String var3,
      Integer var4,
      String var5,
      Integer var6,
      Boolean var7,
      Boolean var8,
      Boolean var9,
      Boolean var10,
      String var11,
      String var12
   ) {
      UUID var13 = var5.isEmpty() ? null : UUID.fromString(var5);
      String var14 = null;

      for (Entry var16 : ThreadModuleDump63.method4().method48().method30().entrySet()) {
         if (Objects.equals(var16.getValue(), var4)) {
            var14 = (String)var16.getKey();
            break;
         }
      }

      if (var14 == null && (var4 < -1 || var4 > 1) && var4 == ThreadModuleDump63.method8().bridge$getDimensionId()) {
         var14 = ThreadModuleDump63.method8().bridge$getDimensionKey();
      }

      GuiHandler2 var17 = GuiHandler2.method18()
         .method2(var1)
         .method12(var3)
         .method4(var2.field4.field1)
         .method3(Vec3Bridge.method2(var2.x(), var2.y(), var2.z()))
         .method5(var4)
         .method9(var14)
         .method16(Client.method109().method48().method27(var13).orElse(null))
         .method13(false)
         .method7(var11 != null && Click3.hasIsland())
         .method6(var11 != null && Click3.hasIsland() ? Gui2Extension3.valueOf(var11) : Gui2Extension3.NONE)
         .method18(new GuiLoader(var8, PhosphorIconLegacy.values()[var6], var9, var7, var10))
         .method19();
      var17.method46().method4().method10(var12);
      method8(var3, var2.field4.field1, var0).ifPresentOrElse(var1x -> var1x.method2(var17), () -> Client.method109().method48().method6(var17));
      Client.method109().method48().method20();
      Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   private static Optional<GuiHandler2> method8(String var0, String var1, String var2) {
      return Client.method109().method48().method24(var0, var1, var2);
   }

   public class Data16 {
      @SerializedName("name")
      private final String field1;

      public Data16(String var1) {
         this.field1 = var1;
      }

      @SerializedName("name")
      public String name() {
         return this.field1;
      }
   }

   public class Data17 {
      @SerializedName("x")
      private final float field1;
      @SerializedName("y")
      private final float field2;
      @SerializedName("z")
      private final float field3;
      @SerializedName("world")
      private final Rewindhandlers.Data16 field4;

      public Data17(float var1, float var2, float var3, Rewindhandlers.Data16 var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      @SerializedName("x")
      public float x() {
         return this.field1;
      }

      @SerializedName("y")
      public float y() {
         return this.field2;
      }

      @SerializedName("z")
      public float z() {
         return this.field3;
      }

      @SerializedName("world")
      public Rewindhandlers.Data16 method1() {
         return this.field4;
      }
   }
}
