package com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;

public class GuiExtension4 implements DriverGuiExtensionLegacy {
   @CallbackJS("updateWaypointSortIndexes")
   public static void method1(GuiExtension4.Data[] var0) {
      for (GuiExtension4.Data var4 : var0) {
         method3(var4.field1, var4.field2, var4.field3).ifPresent(var1 -> var1.method31(var4.field4));
      }

      Client.method109().method48().method20();
   }

   @CallbackJS("setActiveSortIndex")
   public static void method2(Integer var0) {
      Client.method109().method48().method23(var0);
   }

   private static Optional<GuiHandler2> method3(String var0, String var1, String var2) {
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

   public class Data {
      @SerializedName("server")
      private final String field1;
      @SerializedName("world")
      private final String field2;
      @SerializedName("name")
      private final String field3;
      @SerializedName("sortIndex")
      private final Integer field4;

      public Data(String var1, String var2, String var3, Integer var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
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

      @SerializedName("sortIndex")
      public Integer method3() {
         return this.field4;
      }
   }
}
