package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.UUID;
import java.util.function.IntConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ScreenshotThread2 extends Thread {
   private final Path field1;
   @Nullable
   private final UUID field2;
   private final boolean field3;
   private final boolean field4;
   private final String field5;
   private final IntConsumer field6;
   private final String field7;
   private final String field8;

   @Override
   public void run() {
      try {
         if (this.field4) {
            boolean var6 = ThreadModuleDump68.copyImageToClipboard(null, this.field1);
            String var8 = ThreadModuleDump63.method4().method40().method32().method1(var6 ? "copiedScreenshot" : "copyFailed", new Object[0]);
            ThreadModuleDump63.method3()
               .bridge$submit(() -> ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$addMessage(Bridge.method8().method13(var8)));
            return;
         }

         String var1 = new ScreenshotCallable(this.field1, this.field5, this.field2, this.field6, this.field7, this.field8).method1();
         if (this.field3) {
            String var2 = "Look what I am up to %F0%9F%91%80";
            var2 = var2.replace(" ", "%20");
            String var3 = "https://twitter.com/intent/tweet?url=" + URLEncoder.encode(var1, StandardCharsets.UTF_8) + "&via=LunarClient&text=" + var2;
            URI var4 = new URI(var3);
            ThreadModuleDump63.method3()
               .bridge$submit(() -> ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method33(null, var3, var4, false)));
            return;
         }

         ThreadModuleDump61.method7(var1, Initiator.INITIATOR_SCREENSHOT_UPLOAD);
         ThreadModuleDump63.method4().method69().method7(NotificationType.INFO, NotificationManager.method15("screenshotOpened", new Object[0]));
         ThreadModuleDump63.method3().bridge$submit(() -> ThreadModuleDump63.method3().bridge$displayScreen(null));
      } catch (Exception var5) {
         if (this.field6 != null) {
            this.field6.accept(100);
         }

         var5.printStackTrace();
      }
   }

   @Generated
   public ScreenshotThread2(Path var1, @Nullable UUID var2, boolean var3, boolean var4, String var5, IntConsumer var6, String var7, String var8) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
   }
}
