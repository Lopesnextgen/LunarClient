package com.moonsworth.lunar.client.driver.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.feature.screenshot.Gui2Iterator;
import com.moonsworth.lunar.client.framework.feature.screenshot.ScreenshotThread2;
import com.moonsworth.lunar.client.driver.DriverContextLegacy;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.nio.file.Path;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class GuiExtension implements com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy {
   @CallbackJS("upload")
   public static void method1(GuiExtension.Data var0) {
      Screenshot var1 = ThreadModuleDump63.method4().method40().method32();
      var1.method21().method21(var0.field6);
      var1.method22().OIRHOOIICOCIOOHICRRRICORIHHIHC(var0.field7);
      Gui2Iterator var2 = new Gui2Iterator(NotificationManager.method15("uploadProgress"));
      ThreadModuleDump63.method4().method69().method10(var2);
      ((EntityRenderer4)ThreadModuleDump63.method5().get()).method115().method1("GAME_SCREENSHOT_UPLOAD", var2x -> {
         UUID var3 = !var0.field4.isEmpty() ? UUID.fromString(var0.field4) : null;
         Path var4 = Path.of(var0.field3);
         new ScreenshotThread2(var4, var3, var0.field5, false, var2x, var2::method4, var0.field1, var0.field6).start();
      });
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

   public static class Data extends DriverContextLegacy {
      @SerializedName("title")
      private final String field1;
      @SerializedName("path")
      private final String field2;
      @SerializedName("toUpload")
      private final String field3;
      @SerializedName("localId")
      private final String field4;
      @SerializedName("tweetMessage")
      private final boolean field5;
      @SerializedName("privacy")
      private final String field6;
      @SerializedName("worldDetails")
      private final boolean field7;

      @Override
      protected void method2(JsonObject var1) {
         var1.addProperty("title", this.field1);
         var1.addProperty("path", this.field2);
         var1.addProperty("toUpload", this.field3);
         var1.addProperty("localId", this.field4);
         var1.addProperty("tweetMessage", this.field5);
         if (this.field6 != null) {
            var1.addProperty("privacy", this.field6);
         }

         var1.addProperty("worldDetails", this.field7);
      }

      @Generated
      public Data(String var1, String var2, String var3, String var4, boolean var5, String var6, boolean var7) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
      }
   }
}
