package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.feature.screenshot.ProgressNotification;
import com.moonsworth.lunar.client.framework.feature.screenshot.ScreenshotShareThread;
import com.moonsworth.lunar.client.driver.DriverContext;
import com.moonsworth.lunar.client.network.websocket.AssetServerClient;
import com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.nio.file.Path;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ScreenshotUploadBridge implements com.moonsworth.lunar.client.driver.DriverGuiExtension {
   public ScreenshotUploadBridge() {
   }

   @CallbackJS("upload")
   public static void method1(ScreenshotUploadBridge.Data data0) {
      Screenshot screenshot1 = Ref.method4().method40().method32();
      screenshot1.method21().HIHRRHRROIRRHIRCOIRCCCHHHOCIII(data0.field6);
      screenshot1.method22().OIRHOOIICOCIOOHICRRRICORIHHIHC(data0.field7);
      ProgressNotification gui2iterator2 = new ProgressNotification(NotificationManager.method15("uploadProgress", new Object[0]));
      Ref.method4().method69().method10(gui2iterator2);
      ((AssetServerClient)Ref.method5().get()).method115().method1("GAME_SCREENSHOT_UPLOAD", arg2x -> {
         UUID uuid3 = !data0.field4.isEmpty() ? UUID.fromString(data0.field4) : null;
         Path path4 = Path.of(data0.field3);
         new ScreenshotShareThread(path4, uuid3, data0.field5, false, arg2x, gui2iterator2::method4, data0.field1, data0.field6).start();
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

   public static class Data extends DriverContext {
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
      protected void method2(JsonObject json1) {
         json1.addProperty("title", this.field1);
         json1.addProperty("path", this.field2);
         json1.addProperty("toUpload", this.field3);
         json1.addProperty("localId", this.field4);
         json1.addProperty("tweetMessage", this.field5);
         if (this.field6 != null) {
            json1.addProperty("privacy", this.field6);
         }

         json1.addProperty("worldDetails", this.field7);
      }

      @Generated
      public Data(String text1, String text2, String text3, String text4, boolean flag5, String text6, boolean flag7) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = text3;
         this.field4 = text4;
         this.field5 = flag5;
         this.field6 = text6;
         this.field7 = flag7;
      }
   }
}
